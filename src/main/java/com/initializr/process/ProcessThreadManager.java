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

import com.initializr.process.thread.ProcessThread;
import com.initializr.service.request.StartProcessServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * Serves the single purpose of starting the {@link ProcessThread}.
 * @author Deepak Shajan
 */
@Component
@Scope("prototype")
public final class ProcessThreadManager {

    @Autowired
    private ThreadPoolTaskExecutor processTaskExecutor;

    private ProcessThread processThread = null;

    /**
     * Initiates a new {@link Thread} which in our case is the {@link ProcessThread}.
     * @param request the caller request which contains all the info regarding the microservice.Refer {@link StartProcessServiceRequest}.
     * @return <code>true</code>
     */
    public boolean startProcess(StartProcessServiceRequest request) {

        this.processThread = new ProcessThread(request);
        processTaskExecutor.submit(processThread);
        return true;
    }

}