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

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * This class is used to create the chain and execute the chain.
 * @author Deepak Shajan
 */
public final class MonitorThreadCompletionConditionEvaluator {

    /**
     * This variable links all the chain links({@link MonitorThreadCompletionConditionChainLink}) and creates a chain.
     *
     * <p>The chain at present contains the following links in order
     * <ol>
     *     <li>{@link SuccessConditionChainLink}</li>
     *     <li>{@link FailureConditionChainLink}</li>
     * </ol></p>
     */
    @NotNull
    private List<MonitorThreadCompletionConditionChainLink> chain = Arrays.asList(new MonitorThreadCompletionConditionChainLink[]{new SuccessConditionChainLink(), new FailureConditionChainLink()});

    /**
     * Iterates over the chain to find each chain link and executes each chain link by calling the {@link MonitorThreadCompletionConditionChainLink#execute(EvaluatorRequest)}
     * @param evaluatorRequest Request on which the chain operates on, refer {@link EvaluatorRequest}
     * @return either true or false is returned depending on the chain link logic, ie {@link MonitorThreadCompletionConditionChainLink#logic(EvaluatorRequest)}. But for cases where result
     * is not applicable, returns null.
     * @throws IOException thrown since we read the log files
     */
    public final Boolean evaluate(EvaluatorRequest evaluatorRequest) throws IOException {

        for (MonitorThreadCompletionConditionChainLink evaluator : chain) {
            Boolean isCompleteOrFail = evaluator.execute(evaluatorRequest);
            if(isCompleteOrFail != null)
                return isCompleteOrFail;
        }
        return null;
    }

}
