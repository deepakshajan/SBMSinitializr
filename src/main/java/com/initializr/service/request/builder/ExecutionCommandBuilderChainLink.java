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


import com.initializr.service.request.StartProcessServiceRequest;

/**
 * An interface to be implemented by all links of the chain({@link ExecutionCommandBuilderChain}).
 *
 * <p>List of chain links : </p>
 * <ul>
 *     <li>{@link BuildTypeChainLink}</li>
 *     <li>{@link RunCleanChainLink}</li>
 *     <li>{@link RunTestChainLink}</li>
 *     <li>{@link RunBootChainLink}</li>
 * </ul>
 * @author Deepak Shajan
 * @param <RequestTypeT> the type of the request on which the chain operates.
 */
public interface ExecutionCommandBuilderChainLink<RequestTypeT extends StartProcessServiceRequest> {

    /**
     * This function should provide the conditions in which the chain link logic is to be applied.
     *
     * <p>The applies method should be implemented such that it returns true for all the conditions in which the chain link logic
     * ({@link ExecutionCommandBuilderChainLink#logic(StartProcessServiceRequest)}) is to be performed.In other words the {@link ExecutionCommandBuilderChainLink#logic(StartProcessServiceRequest)} is
     * executed only if {@link ExecutionCommandBuilderChainLink#applies(StartProcessServiceRequest)} returns <code>true</code></p>
     * @param request the caller request which contains all the info regarding the microservice. Refer {@link StartProcessServiceRequest}.
     * @return <code>true</code> if the chain link is to be applied. Else return <code>false</code>.
     */
    boolean applies(RequestTypeT request);

    /**
     * The {@link ExecutionCommandBuilderChainLink#execute(StartProcessServiceRequest)} method is the place where the {@link ExecutionCommandBuilderChainLink#applies(StartProcessServiceRequest)} and
     * {@link ExecutionCommandBuilderChainLink#logic(StartProcessServiceRequest)} methods are tied together.
     * @param request Request on which the chain operates on, refer {@link StartProcessServiceRequest}
     * @return result of execution
     */
    default String execute(RequestTypeT request) {
        if(applies(request))
            return logic(request) + " ";
        return "";
    }

    /**
     * This method implementation should provide whatever logic that the chain link should perform provided that the {@link ExecutionCommandBuilderChainLink#applies(StartProcessServiceRequest)} method returns true.
     * @param request Request on which the chain operates on, refer {@link StartProcessServiceRequest}
     * @return part of the execution command that the chain link implementation builds
     */
    String logic(RequestTypeT request);

}
