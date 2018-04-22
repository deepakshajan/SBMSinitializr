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

import com.initializr.process.tree.ProcessTreeProvider;
import com.initializr.process.tree.api.DTree;
import com.initializr.service.request.DeployServiceClusterRequestImpl;
import com.initializr.service.response.DeployServiceClusterServiceResponseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author Deepak Shajan
 */
@RestController
@RequestMapping(value = "/initializr")
public class DeployServiceClusterService implements Service<DeployServiceClusterRequestImpl, DeployServiceClusterServiceResponseImpl> {

    @Autowired
    ProcessTreeProvider processTreeProvider;

    @Override
    @RequestMapping(value = "/deploy", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeployServiceClusterServiceResponseImpl execute(@RequestBody DeployServiceClusterRequestImpl request) throws Exception {

        DTree dTree = null;
        DeployServiceClusterServiceResponseImpl response = null;

        try {
            response = createServiceResponse();
            dTree = processTreeProvider.getProcessTree(request.getCluserPath());
            iterateOverTreeAndStartProcessInOrder(dTree);
        } catch (IOException e) {
            handleException(e, response);
        }
        return response;
    }

    private void handleException(Exception e, DeployServiceClusterServiceResponseImpl response) throws Exception {
        response.setSuccess(false);
        throw e;
    }

    private void iterateOverTreeAndStartProcessInOrder(DTree dTree) {
        //TODO iterate over the processtree and call the service to start each individual process

    }

    private DeployServiceClusterServiceResponseImpl createServiceResponse() {

        DeployServiceClusterServiceResponseImpl response = new DeployServiceClusterServiceResponseImpl();
        response.setSuccess(true);
        return response;
    }
}
