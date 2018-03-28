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

import com.initializr.process.operations.ProcessPoolOperations;
import com.initializr.service.request.StopProcessServiceRequestImpl;
import com.initializr.service.response.StopProcessServiceResponseImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Rest service to be called to stop a spring boot microservice process which was already started using the {@link StartProcessService}.
 *
 * <p>Accepts type {@link StopProcessServiceRequestImpl} as input and returns type {@link StopProcessServiceResponseImpl} as response</p>
 * @author Deepak Shajan
 */
@RestController
@RequestMapping("/initializr")
public final class StopProcessService implements Service<StopProcessServiceRequestImpl, StopProcessServiceResponseImpl> {


    @Autowired
    private ProcessPoolOperations processPoolOperations;

    private static final Logger LOGGER = LoggerFactory.getLogger(StopProcessService.class);

    /**
     * Stops an already started process and its childs.
     *
     * <p>Can be used to stop any process started using this application. Also terminates all the child system processes
     * started as part of the microservice.</p>
     * @param request : The request object. Refer {@link StopProcessServiceRequestImpl}
     * @return response, Refer {@link StopProcessServiceResponseImpl}
     */
    @Override
    @RequestMapping(value = "/stopProcess",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public StopProcessServiceResponseImpl execute(@RequestBody StopProcessServiceRequestImpl request) {

        request.filter();
        boolean result = processPoolOperations.forceStopProcess(request.getModuleName());
        LOGGER.info("***** "+request.getModuleName()+" stopped successfully *****");
        return createResponse(result);
    }


    /**
     * Create and enrich the response with the success status.
     * @param processStarted whether the process has been stopped.
     * @return response to be sent to the caller. Refer {@link com.initializr.service.response.StopProcessServiceResponseImpl}
     */
    private StopProcessServiceResponseImpl createResponse(boolean processStarted) {

        StopProcessServiceResponseImpl response = new StopProcessServiceResponseImpl();
        response.setSuccess(processStarted);
        return response;
    }
}
