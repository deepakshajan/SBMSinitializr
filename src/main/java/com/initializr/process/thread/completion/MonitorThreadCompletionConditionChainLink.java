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

package com.initializr.process.thread.completion;

import com.initializr.service.request.StartProcessServiceRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/** Interface to be implemented by all the chain links.
 *
 * <p>Currently the chain has the following links</p>
 * <ul>
 *      <li>{@link SuccessConditionChainLink}</li>
 *      <li>{@link FailureConditionChainLink}</li>
 * </ul>
 * @author Deepak Shajan
 * @param <RequestTypeT> the type of the request on which the chain operates on.
 */
public interface MonitorThreadCompletionConditionChainLink<RequestTypeT extends StartProcessServiceRequest> {

    Logger LOGGER = LoggerFactory.getLogger(MonitorThreadCompletionConditionChainLink.class);

    /**
     * The {@link MonitorThreadCompletionConditionChainLink#applies(EvaluatorRequest)} method implementation should provide the conditions on which the chain link logic
     * has to be executed.
     *
     * <p>In other words, the chain link method {@link MonitorThreadCompletionConditionChainLink#logic(EvaluatorRequest)} executes only if this method returns a true.</p>
     * @param evaluatorRequest Request on which the chain operates, refer {@link EvaluatorRequest}
     * @return must return true if the logic is to be executed.In all other cases return false.
     * @throws IOException thrown from implementations
     */
    boolean applies(EvaluatorRequest evaluatorRequest) throws IOException;

    /**
     * The {@link MonitorThreadCompletionConditionChainLink#logic(EvaluatorRequest)} method implementation should provide what ever logic that the chain links should execute, given that the {@link MonitorThreadCompletionConditionChainLink#applies(EvaluatorRequest)}
     * method returns true.
     * @param evaluatorRequest Request on which the chain operates, refer {@link EvaluatorRequest}
     * @return the execution status
     */
    boolean logic(EvaluatorRequest evaluatorRequest);

    /**
     * The {@link MonitorThreadCompletionConditionChainLink#execute(EvaluatorRequest)} method is the place where the {@link MonitorThreadCompletionConditionChainLink#applies(EvaluatorRequest)} and
     * {@link MonitorThreadCompletionConditionChainLink#logic(EvaluatorRequest)} methods are tied together.
     * @param evaluatorRequest Request on which the chain operates on, refer {@link EvaluatorRequest}
     * @return result of execution
     * @throws IOException thrown from {@link MonitorThreadCompletionConditionChainLink#applies(EvaluatorRequest)}
     */
    default Boolean execute(EvaluatorRequest evaluatorRequest) throws IOException {
        if(applies(evaluatorRequest)) {
            return logic(evaluatorRequest);
        }
        return null;
    }


}
