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

package com.initializr.service.request;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * @author Deepak Shajan
 */
@Component
@Scope(value = "prototype")
public class DeployServiceClusterServiceRequestImpl implements DeployServiceClusterServiceRequest {

    String clusterPath = null;

    @NotNull
    String buildType = null;

    boolean runClean = false;

    boolean runTests = false;

    boolean runBoot = false;

    @Override
    public String getClusterPath() {
        return clusterPath;
    }

    @Override
    public void setClusterPath(String clusterPath) {
        this.clusterPath = clusterPath;
    }

    @Override
    public String getBuildType() {
        return buildType;
    }

    @Override
    public boolean isRunClean() {
        return runClean;
    }

    @Override
    public boolean isRunTests() {
        return runTests;
    }

    @Override
    public boolean isRunBoot() {
        return runBoot;
    }

    public void setBuildType(String buildType) {
        this.buildType = buildType;
    }

    public void setRunClean(boolean runClean) {
        this.runClean = runClean;
    }

    public void setRunTests(boolean runTests) {
        this.runTests = runTests;
    }

    public void setRunBoot(boolean runBoot) {
        this.runBoot = runBoot;
    }

    @Override
    public boolean filterInvalidRequest() {
        return false;
    }


}
