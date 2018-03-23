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

package com.initializr.service.request.builder;


import com.initializr.constants.BuildTypeConstant;
import com.initializr.exception.UnSupportedBuildTypeException;
import com.initializr.service.request.StartProcessServiceRequest;

/**
 * Chain link class responsible to build the Build Type execution command.
 *
 * <p>The application supports microservice modules with the following build types</p>
 * <ul>
 *     <li>Maven</li>
 *     <li>Gradle</li>
 * </ul>
 * @author Deepak Shajan
 * @param <RequestTypeT> The type of the request on which the chain link operates on.
 */
public final class BuildTypeChainLink<RequestTypeT extends StartProcessServiceRequest> implements ExecutionCommandBuilderChainLink<RequestTypeT> {


    /**
     * Checks if the build type specified in the request is either {@code maven} or {@code gradle}
     * @param request the caller request which contains all the info regarding the microservice. Refer {@link StartProcessServiceRequest}.
     * @return {@code} if the condition is satisfied, else returns {@code false}.
     */
    @Override
    public boolean applies(RequestTypeT request) {
        return BuildTypeConstant.BUILD_TYPE_MAVEN.equals(request.getBuildType()) || BuildTypeConstant.BUILD_TYPE_GRADLE.equals(request.getBuildType());
    }

    /**
     * Returns the command corresponding to the build type specified in the request.
     * @param request Request on which the chain operates on, refer {@link StartProcessServiceRequest}
     * @return exection command.
     * @throws UnSupportedBuildTypeException If the caller specifies a build type as a type not supported by the application then, this {@link RuntimeException} is thrown
     */
    @Override
    public String logic(RequestTypeT request) {

        switch (request.getBuildType()) {
            case BuildTypeConstant.BUILD_TYPE_MAVEN : return "mvn";
            case BuildTypeConstant.BUILD_TYPE_GRADLE : return "gradle";
        }
        throw new UnSupportedBuildTypeException();
    }
}
