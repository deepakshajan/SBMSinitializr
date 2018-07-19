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

package com.initializr.socket.request;

import com.initializr.backbone.SBMSWebSocketRequest;


/**
 * @author Deepak Shajan
 */
public class SBMSWebSocketRequestImpl implements SBMSWebSocketRequest {

    private String endPoint;

    private String clusterPath;

    private String buildType;

    private boolean runClean;

    private boolean runTests;

    private boolean runBoot;

    @Override
    public String getEndPoint() {
        return endPoint;
    }

    @Override
    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

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

    public void setBuildType(String buildType) {
        this.buildType = buildType;
    }

    @Override
    public boolean isRunClean() {
        return runClean;
    }

    @Override
    public void setRunClean(boolean runClean) {
        this.runClean = runClean;
    }

    @Override
    public boolean isRunTests() {
        return runTests;
    }

    @Override
    public void setRunTests(boolean runTests) {
        this.runTests = runTests;
    }

    @Override
    public boolean isRunBoot() {
        return runBoot;
    }

    @Override
    public void setRunBoot(boolean runBoot) {
        this.runBoot = runBoot;
    }
}
