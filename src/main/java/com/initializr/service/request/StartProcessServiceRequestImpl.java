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


import com.initializr.constants.BuildTypeConstant;
import com.initializr.service.request.builder.ExecutionCommandBuilderChainLink;
import com.initializr.service.request.builder.ExecutionCommandBuilderChain;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * Implementation for {@link StartProcessServiceRequest}. This is the request object accepted by the {@link com.initializr.service.StartProcessService} rest service.
 * @author Deepak Shajan
 */
@Component
@Scope(value = "singleton")
public class StartProcessServiceRequestImpl implements StartProcessServiceRequest {


    /**
     * Unique identifier for each microservice.
     */
    String moduleName = null;

    /**
     * The path in which the microservice module is present.
     */
    String path = null;

    /**
     * The build type using which the microservice is to be started.
     */
    @NotNull
    String buildType = null;

    /**
     * Should the microservice start run the clean task
     */
    boolean runClean = false;

    /**
     * Should the microservice start run the test task
     */
    boolean runTests = false;

    /**
     * Should the microservice run the boot task
     */
    boolean runBoot = false;


    @Override
    public String getModuleName() {
        return moduleName;
    }

    /**
     * The actual command that needs to be executed for the process to be initiated.
     * @return string
     */
    @Override
    public String getExecutableCommand() {

        filter();

        StringBuilder builder = new StringBuilder("");

        for(ExecutionCommandBuilderChainLink chainList : new ExecutionCommandBuilderChain())
            builder.append(chainList.execute(this));

        return builder.toString().trim();
    }

    /**
     *Method throws exception for invalid combination for the below list of values.
     *
     * <p>In this implementation we make sure that the {@link StartProcessServiceRequestImpl#buildType} is accompanied by atleast one among the following variables.</p>
     *     <ul>
     *         <li>{@link StartProcessServiceRequestImpl#runClean}</li>
     *         <li>{@link StartProcessServiceRequestImpl#runTests}</li>
     *         <li>{@link StartProcessServiceRequestImpl#runBoot}</li>
     *     </ul>
     */
    @Override
    public boolean filterInvalidRequest() {

        if(BuildTypeConstant.BUILD_TYPE_MAVEN.equals(buildType) || BuildTypeConstant.BUILD_TYPE_GRADLE.contentEquals(buildType)){
            return runClean == false && runTests == false && runBoot == false ? true : false;
        }
        return false;
    }

    @Override
    public String getPath() {
        return this.path;
    }

    /**
     * The path in which the microservice module is present.
     * @return directory path
     */
    @Override
    public String getExecutionDirectory() {
        return getPath();
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

    @Override
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    @Override
    public void setPath(String path) {
        this.path = path;
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
}
