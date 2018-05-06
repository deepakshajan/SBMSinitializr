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

package com.initializr.service.request.adapter;

import com.initializr.backbone.SBMSAdapter;
import com.initializr.service.request.DeployServiceClusterServiceRequest;
import com.initializr.service.request.StartProcessServiceRequest;
import com.initializr.service.request.StartProcessServiceRequestImpl;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Deepak Shajan
 */
@Component
@Scope(value = "prototype")
public class DeployServiceClusterServiceRequestAdapter implements SBMSAdapter {

    public StartProcessServiceRequest convertToStartProcessServiceRequestImpl(DeployServiceClusterServiceRequest deployServiceClusterServiceRequest, String moduleName) {

        StartProcessServiceRequest request = new StartProcessServiceRequestImpl();
        String path = getPath(deployServiceClusterServiceRequest , moduleName);
        request.setModuleName(moduleName);
        request.setPath(path);
        request.setBuildType(deployServiceClusterServiceRequest.getBuildType());
        request.setRunBoot(deployServiceClusterServiceRequest.isRunBoot());
        request.setRunClean(deployServiceClusterServiceRequest.isRunClean());
        request.setRunTests(deployServiceClusterServiceRequest.isRunTests());
        return request;
    }

    private String getPath(DeployServiceClusterServiceRequest deployServiceClusterServiceRequest, String moduleName) {

        StringBuilder builder = new StringBuilder();
        builder.append(deployServiceClusterServiceRequest.getCluserPath());
        builder.append("\\");
        builder.append(moduleName);
        return builder.toString();
    }

}
