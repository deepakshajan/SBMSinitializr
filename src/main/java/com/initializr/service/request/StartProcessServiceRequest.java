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


import com.initializr.backbone.SBMSServiceRequest;

/**
 * Interface representing the request to be triggered by the service invoker inorder to start a process.
 *
 * <p>Implementations of this interface contains info about the microservice to be started.</p>
 * @author Deepak Shajan
 */
public interface StartProcessServiceRequest extends SBMSServiceRequest {

    /**
     * Unique identifier for each microservice.
     * @return string
     */
    String getModuleName();

    /**
     * The actual command that needs to be executed for the process to be initiated.
     * @return string
     */
    String getExecutableCommand();

    /**
     * The path in which the microservice module is present.
     * @return directory path
     */
    String getPath();

    /**
     * The path in which the microservice module is present.
     * @return directory path
     */
    String getExecutionDirectory();

    /**
     * The build type using which the microservice is to be started.
     * @return  build type
     */
    String getBuildType();

    /**
     * Should the microservice start run the clean task
     * @return boolean
     */
    boolean isRunClean();

    /**
     * Should the microservice start run the test task
     * @return boolean
     */
    boolean isRunTests();

    /**
     * Should the microservice run the boot task
     * @return boolean
     */
    boolean isRunBoot();

    void setModuleName(String moduleName);

    void setPath(String path);

    void setBuildType(String buildType);

    void setRunClean(boolean runClean);

    void setRunTests(boolean runTest);

    void setRunBoot(boolean runBoot);

}
