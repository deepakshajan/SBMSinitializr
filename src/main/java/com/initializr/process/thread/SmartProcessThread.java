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
import com.initializr.process.ProcessThreadManager;
import com.initializr.process.operations.ProcessPoolOperations;
import com.initializr.process.thread.lock.SmartProcessThreadLock;
import com.initializr.service.request.StartProcessServiceRequest;
import com.initializr.utils.FileUtils;
import com.initializr.utils.ThreadUtils;
import com.initializr.utils.WebSocketUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import java.io.IOException;
import java.util.List;


/**
 * @author Deepak Shajan
 */
@Component
@Scope(value = "prototype")
public class SmartProcessThread implements SBMSThread<Boolean>{

    private StartProcessServiceRequest request;

    private static final SmartProcessThreadLock THREAD_LOCK = SmartProcessThreadLock.getLock();

    @Autowired
    private ProcessThreadManager processThreadManager;

    @Autowired
    private FileUtils fileUtils;

    @Autowired
    private ThreadUtils threadUtils;

    @Autowired
    private WebSocketUtils webSocketUtils;

    @Autowired
    private ProcessPoolOperations processPoolOperations;

    @Override
    public Boolean call() throws IOException {

        synchronized (SmartProcessThreadLock.getLock()) {

            notifyClient();
            boolean isProcessRunning = processPoolOperations.isProcessRunning(request.getModuleName());
            if (!isProcessRunning) {
                waitUntilProcessCanBeStarted();
                processThreadManager.startProcess(request);
            }
        }
        return true;
    }

    public synchronized void setRequestToThread(StartProcessServiceRequest request) {
        this.request = request;
    }

    @Override
    public synchronized SmartProcessThread clone() {

        SmartProcessThread clone;
        try {
            clone = (SmartProcessThread) super.clone();
            clone.request = (StartProcessServiceRequest) SerializationUtils.deserialize(SerializationUtils.serialize(this.request));
            return clone;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void waitUntilProcessCanBeStarted () throws IOException {

        String executionDirectory = request.getExecutionDirectory();
        List<String> dependencies = fileUtils.parseDepsFile(executionDirectory);
        waitIfAllDependanciesNotStarted(dependencies);
    }

    private void waitIfAllDependanciesNotStarted(List<String> dependencies) {

        boolean isAllDependanciesComplete = isAllDependanciesComplete(dependencies);
        if(!isAllDependanciesComplete) {
            threadUtils.waitWithLock(THREAD_LOCK);
            waitIfAllDependanciesNotStarted(dependencies);
        }
    }

    private boolean isAllDependanciesComplete(List<String> dependencies) {

        for(String dependency : dependencies) {
            boolean isDependentProcessComplete = new ProcessPoolOperations().isProcessStarted(dependency);
            if(!isDependentProcessComplete)
                return false;
        }
        return true;
    }

    private synchronized void notifyClient() {

        webSocketUtils.sendMessageToAllWebSocketSessions("toBeStarted", this.request.getModuleName());
    }

}
