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


import com.initializr.constants.ProcessStatusConstant;

/**
 * The purpose of this chain link class is to evaluate if the microservice process has failed to boot up.
 * @author Deepak Shajan
 * @see SuccessConditionChainLink
 */
final class FailureConditionChainLink implements MonitorThreadCompletionConditionChainLink {

    /**
     *  Check if the current log line to be evaluated matches with the process fail regular expression patterns. If yes return true.
     * @param evaluatorRequest Request on which the chain operates, refer {@link EvaluatorRequest}
     * @return true if the pattern matches,else returns false.
     */
    @Override
    public boolean applies(EvaluatorRequest evaluatorRequest) {
        return evaluatorRequest.getLogLine().matches(ProcessStatusConstant.PROCESS_FAIL_REGEX_MAVEN)
                || evaluatorRequest.getLogLine().matches(ProcessStatusConstant.PROCESS_FAIL_REGEX_GRADLE);
    }

    /**
     * Since the process has failed to start, we simply log the data and return false.
     * @param evaluatorRequest Request on which the chain operates, refer {@link EvaluatorRequest}
     * @return false
     */
    @Override
    public boolean logic(EvaluatorRequest evaluatorRequest) {
        LOGGER.info("***** "+evaluatorRequest.getRequest().getModuleName()+" starting failed *****");
        return false;
    }

}
