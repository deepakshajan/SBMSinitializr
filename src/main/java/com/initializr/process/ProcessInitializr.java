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

package com.initializr.process;

import com.initializr.backbone.SBMSServiceResponse;
import com.initializr.exception.DuplicateProcessException;
import com.initializr.process.operations.ProcessPoolOperations;
import com.initializr.service.request.StartProcessServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * Responsible for initializing the microservice as a system process, and also to build the response for every {@link com.initializr.service.StartProcessService} service hits.
 *@author Deepak Shajan
 */
@Component
@Scope("prototype")
public final class ProcessInitializr {

    @Autowired
    ProcessThreadManager processThreadManager;

    @Autowired
    ProcessPoolOperations processPoolOperations;

    /**
     * Initialize the system process and build the {@link SBMSServiceResponse}.
     * @param request the caller request which contains all the info regarding the microservice.Refer {@link StartProcessServiceRequest}.
     * @param response empty response to be sent to the caller.Refer {@link com.initializr.service.response.StartProcessServiceResponseImpl}
     * @return enriched response to be sent to the caller.Refer {@link com.initializr.service.response.StartProcessServiceResponseImpl}
     * @throws DuplicateProcessException when a process with the same module name already exists in the {@link com.initializr.process.pool.ProcessPoolProvider#pool}.
     */
    public SBMSServiceResponse initializeProcess(StartProcessServiceRequest request, SBMSServiceResponse response) {

        if(processPoolOperations.isProcessRunning(request.getModuleName())) {
            throw new DuplicateProcessException();
        }

        boolean processStarted = processThreadManager.startProcess(request);
        response = setExecutionCompleteToResponse(response, processStarted);
        return response;
    }

    /**
     * Enrich the response with the success status.
     * @param response empty response to be sent to the caller.Refer {@link com.initializr.service.response.StartProcessServiceResponseImpl}
     * @param processStarted whether the process start has been started.
     * @return enriched response to be sent to the caller. Refer {@link com.initializr.service.response.StartProcessServiceResponseImpl}
     */
    private SBMSServiceResponse setExecutionCompleteToResponse(SBMSServiceResponse response, boolean processStarted) {

        response.setSuccess(processStarted);
        return response;
    }
}
