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

package com.initializr.service;

import com.initializr.backbone.SBMSService;
import com.initializr.process.ProcessInitializr;
import com.initializr.process.thread.MonitorThread;
import com.initializr.service.request.StartProcessServiceRequestImpl;
import com.initializr.backbone.SBMSServiceResponse;
import com.initializr.service.response.StartProcessServiceResponseImpl;
import com.initializr.utils.ThreadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest service to be called to start a new spring boot microservice process.
 *
 * <p>The service operates accepts type {@link StartProcessServiceRequestImpl} as input and returns type {@link StartProcessServiceResponseImpl}.
 * Each call to this service will start two threads which eventually dies after completion</p>
 * <ol>
 *     <li><b>{@link com.initializr.process.thread.ProcessThread} : </b>This thread is responsible for starting the system process,ie the microservice</li>
 *     <li><b>{@link MonitorThread} : </b>This thread is responsible for monitoring the status of the microservice. This thread will check if the microservice start is success or fail</li>
 * </ol>
 * @author Deepak Shajan
 */
@RestController
@RequestMapping("/initializr")
public final class StartProcessService implements SBMSService<StartProcessServiceRequestImpl,StartProcessServiceResponseImpl> {

    @Autowired
    private ProcessInitializr processInitializr;

    @Autowired
    private ThreadPoolTaskExecutor monitorTaskExecutor;

    @Autowired
    private ThreadUtils threadUtils;


    /**
     *  Starts and monitors a new system process
     *
     *  <p>The system process that is started by this service method is a sping-boot microservice. The details of the microservice to be started should be passed.
     *  Refer {@link StartProcessServiceRequestImpl} for format of the request.</p>
     * @param request : Contains details about the microservice to be started. Refer {@link com.initializr.service.request.StartProcessServiceRequestImpl} for details.
     * @return response : Contains info whether the initiation of the process start is done.Does not mean that the process is started.Just means that we have started to start the
     * process. Refer {@link StartProcessServiceResponseImpl} for details.
     * @throws Exception : Any exception thrown from within is thrown to the caller.
     */
    @Override
    @RequestMapping(value = "/startProcess", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public StartProcessServiceResponseImpl execute(@RequestBody StartProcessServiceRequestImpl request) throws Exception {

        SBMSServiceResponse response = null;
        response = new StartProcessServiceResponseImpl();
        try {

            request.filter();
            processInitializr.initializeProcess(request, response);
            startProcessMonitorThread(request);

        } catch (Exception e) {
            handleException(e, response);
        }

         return (StartProcessServiceResponseImpl) response;
    }

    /**
     * Handles all the exceptions thrown from within the service.
     *
     * <p>We simply update the success status in response to <code>false</code> and throw the exception to the caller.</p>
     * @param e : exception thrown
     * @param response : success status of the response is set to <code>false</code>.
     * @throws Exception any exception thrown from within is thrown back to the caller
     */
    private void handleException(Exception e, SBMSServiceResponse response) throws Exception {

        response.setSuccess(false);
        throw e;
    }

    /**
     * Starts the monitor thread for this particular spring boot microservice. Refer {@link MonitorThread} for details.
     * @param request Contains details about the microservice to be started. Refer {@link com.initializr.service.request.StartProcessServiceRequestImpl} for details.
     */
    private void startProcessMonitorThread(StartProcessServiceRequestImpl request) {

        MonitorThread monitorThread = new MonitorThread(request);
        threadUtils.startThread(monitorTaskExecutor, monitorThread);

    }

}
