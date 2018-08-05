/**
 * MIT License
 Copyright (c) 2018 deepakshajan
 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:
 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.
 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
 */

package com.initializr.process.thread;

import com.initializr.backbone.SBMSThread;
import com.initializr.config.Configuration;
import com.initializr.constants.LogConstant;
import com.initializr.process.operations.ProcessPoolOperations;
import com.initializr.process.pool.ProcessPoolProvider;
import com.initializr.process.thread.completion.EvaluatorRequest;
import com.initializr.process.thread.completion.MonitorThreadCompletionConditionEvaluator;
import com.initializr.process.thread.lock.SmartProcessThreadLock;
import com.initializr.service.request.StartProcessServiceRequest;
import com.initializr.utils.ThreadUtils;
import com.initializr.utils.WebSocketUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Thread responsible for monitoring the status of the process.
 *
 * <p>Whenever a new {@link ProcessThread} is started, another thread, ie the {@link MonitorThread} is also started. In other words, there is a one to one mapping between the {@link ProcessThread}
 * and {@link MonitorThread}. The {@link MonitorThread} is alive until the process/microservice has successfully started or failed. Each {@link MonitorThread} monitors only a single process.</p>
 * <p>It is also worth to note that the monitor thread is not running in a continuous mode, rather it operates in a pulsed mode(Refer {{@link Configuration#monitorThreadWaitInterval}}). This mode
 * of operation is done for performance reasons.</p>
 * @author Deepak Shajan
 */
@Component
@Scope(value = "prototype")
public final class MonitorThread implements SBMSThread<Void> {

    /**
     * The request sent by the caller on the service {@link com.initializr.service.StartProcessService}.
     * @see StartProcessServiceRequest
     */
    private StartProcessServiceRequest request;

    @Autowired
    private WebSocketUtils webSocketUtils;

    /**
     * Performs the monitoring actions on a single process.
     *
     * <p>Checks in a pulsed fashion whether the process is completed.By completed means that either the microservice has started successfully or it has failed to start.</p>
     * @return nothing is expected as a return. So returns <code>null</code>
     * @throws InterruptedException because we sleep the thread for some time. Refer {@link MonitorThread#sleepThreadForSomeTime()}
     * @throws IOException Because we read the log files. Refer {@link MonitorThread#checkAndUpdateCompletion(File)}
     */
    @Override
    public Void call() throws InterruptedException, IOException {

        while (true) {

            synchronized (SBMSThread.class) {

                sleepThreadForSomeTime();

                File log = getProcessLog(request);
                boolean isCompleteOrFail = checkAndUpdateCompletion(log);
                if (isCompleteOrFail)
                    break;

            }
        }

        return null;
    }

    @Override
    public synchronized MonitorThread clone() {

        MonitorThread clone;
        try {
            clone = (MonitorThread) super.clone();
            clone.request = (StartProcessServiceRequest) SerializationUtils.deserialize(SerializationUtils.serialize(this.request));
            return clone;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Sleeps the monitor thread for certain time.
     *
     * <p>This function is responsible for making the {@link MonitorThread} operate in a discontinues pulsed mode. The time duration to which the thread sleeps
     * is configurable(Refer {@link Configuration#monitorThreadWaitInterval}.)</p>
     * @throws InterruptedException because we call sleep on this thread
     */
    private void sleepThreadForSomeTime() throws InterruptedException {
        new ThreadUtils().sleep(Configuration.getConfiguration().getMonitorThreadWaitInterval());
    }

    /**
     * Returns the log file corresponding to the caller request.
     * @param request the caller request which contains all the info regarding the microservice.Refer {@link StartProcessServiceRequest}.
     * @return log file corresponding to the request.
     */
    private synchronized File getProcessLog(StartProcessServiceRequest request) {

        File file = new File(LogConstant.LOG_DIRECTORY+"/"+request.getModuleName()+"."+LogConstant.LOG_FILE_EXTENSION_TYPE);
        if(file.exists()) {
            return file;
        }
        return null;
    }

    /**
     * Check for completion,ie successful start or failure of the microservice boot from the log and update the {@link ProcessPoolProvider#completedProcessIds} cache.
     * @param file The log file corresponding to the request.
     * @return true if process has started successfully.
     * @throws IOException thrown from {@link MonitorThread#checkAndUpdateCompletion(File)}
     */
    private boolean checkAndUpdateCompletion(File file) throws IOException {

        String processIdentifier = request.getModuleName();
        boolean isAlreadyStarted = ProcessPoolProvider.getProcessPoolProvider().isProcessStarted(processIdentifier);
        boolean isAlreadyFailed = ProcessPoolProvider.getProcessPoolProvider().isProcessFailed(processIdentifier);
        if(!isAlreadyStarted && !isAlreadyFailed)
            return readLogAndUpdateCompletion(file);
        return false;
    }

    /**
     * Updates the {@link ProcessPoolProvider#completedProcessIds} set if the process has successfully started.
     * @param log log file which corresponds to the process
     * @return true if process has started or failed successfully.
     * @throws IOException thrown from {@link MonitorThread#readLogAndUpdateCompletion(File)}
     */
    private boolean readLogAndUpdateCompletion(File log) throws IOException {

        Boolean completionStatus = checkForCompletionInLog(log);
        if(null != completionStatus && completionStatus) {
            new ProcessPoolOperations().markProcessAsCompleted(request.getModuleName());
            notifyAllSmartProcessThreads();
            notifyClientAboutCompletion();
        }
        else if(null != completionStatus && !completionStatus) {
            new ProcessPoolOperations().markProcessAsFailed(request.getModuleName());
            notifyAllSmartProcessThreads();
            notifyClientAboutFailure();
        }
        return completionStatus != null ? true : false;
    }


    /**
     * Parse the log file to check if the process has successfully started or failed.
     * @param log log corresponding to the process
     * @return <code>true</code> if the process has started successfully.Returns <code>false</code> if process has failed. Else returns {@code null}.
     * @throws IOException thrown from {@link BufferedReader}
     */
    private Boolean checkForCompletionInLog(File log) throws IOException {

        if(log.canRead()) {
            Boolean completionResult;
            String line;
            try(BufferedReader br = new BufferedReader(new FileReader(log))) {
                while ((line = br.readLine()) != null)
                    if(null != (completionResult = matchLinesForCompletion(line,br)))
                        return completionResult;
            }
        }
        return null;
    }

    /**
     * Line by line evaluation to check the completion status of a process is done here.
     * @param line the line to be evaluated on
     * @param br the {@link BufferedReader} instance which currently points to the line.
     * @return <code>true</code> if the line satisfies the condition for completion of process..Else returns <code>false</code>
     * @throws IOException thrown from {@link MonitorThreadCompletionConditionEvaluator#evaluate(EvaluatorRequest)}
     */
    private Boolean matchLinesForCompletion(String line, BufferedReader br) throws IOException {

        EvaluatorRequest evaluatorRequest = new EvaluatorRequest<StartProcessServiceRequest>(request, line, br);

        Boolean result = new MonitorThreadCompletionConditionEvaluator().evaluate(evaluatorRequest);
        return result;
    }

    private void notifyAllSmartProcessThreads() {

        SmartProcessThreadLock smartProcessThreadLock = SmartProcessThreadLock.getLock();
        new ThreadUtils().notifyAllWithLock(smartProcessThreadLock);
    }

    private synchronized void notifyClientAboutCompletion() {

        webSocketUtils.sendMessageToAllWebSocketSessions("completed", this.request.getModuleName());
    }

    private void notifyClientAboutFailure() {

        webSocketUtils.sendMessageToAllWebSocketSessions("failed", this.request.getModuleName());
    }

    public synchronized void setRequest(StartProcessServiceRequest request) {
        this.request = request;
    }
}
