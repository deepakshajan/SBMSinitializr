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
import com.initializr.process.operations.ProcessPoolOperations;
import com.initializr.process.operations.ProcessThreadOperations;
import com.initializr.service.request.StartProcessServiceRequest;
import com.initializr.utils.WebSocketUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import java.io.IOException;

/**
 * Tread responsible for starting a microservice.
 *
 * <p>Whenever a new request is being sent by the caller, a thread, ie the {@link ProcessThread} is started. In other words, there is a one to one mapping between any microservice start operation and
 * .{@link ProcessThread}. The {@link ProcessThread} initiates the start operation of the process. Each {@link ProcessThread} starts only a single process.</p>
 * @author Deepak Shajan
 */
@Component
@Scope(value = "prototype")
public class ProcessThread implements SBMSThread<Boolean> {

    /**
     * The request sent by the caller on the service {@link com.initializr.service.StartProcessService}.
     * @see StartProcessServiceRequest
     */
    private StartProcessServiceRequest request = null;

    @Autowired
    private WebSocketUtils webSocketUtils;

    /**
     * The process instance which correspond to the system process of the microservice.
     */
    private Process process = null;

    /**
     * Initiates the system process and updates the {@link com.initializr.process.pool.ProcessPoolProvider#pool}.
     * @return <code>true</code>
     * @throws IOException thrown from {@link ProcessThreadOperations#startProcess(StartProcessServiceRequest)}.
     */
    @Override
    public Boolean call() throws IOException {

            ProcessThreadOperations processThreadOperations = new ProcessThreadOperations();
            ProcessPoolOperations processPoolOperations = new ProcessPoolOperations();

            process = processThreadOperations.startProcess(request);
            processPoolOperations.addProcessToPool(this);
            notifyClient();

        return true;
    }

    @Override
    public synchronized ProcessThread clone() {

        ProcessThread clone;
        try {
            clone = (ProcessThread) super.clone();
            clone.request = (StartProcessServiceRequest) SerializationUtils.deserialize(SerializationUtils.serialize(this.request));
            return clone;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    private synchronized void notifyClient() {
        webSocketUtils.sendMessageToAllWebSocketSessions("starting", this.getProcessIdentifier());
    }

    /**
     * @return the unique identifier corresponding to this thread, which is same as the module name.
     */
    public String getProcessIdentifier() {
        return request.getModuleName();
    }

    /**
     * @return the process instance corresponding to the system process started by this thread.
     */
    public Process getProcess() {
        return process;
    }


    public synchronized void setRequest(StartProcessServiceRequest request) {
        this.request = request;
    }
}
