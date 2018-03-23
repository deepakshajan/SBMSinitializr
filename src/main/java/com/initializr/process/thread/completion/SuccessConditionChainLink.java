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

import java.io.IOException;

/**
 * The purpose of this chain link class is to evaluate if the microservice process has successfully started.
 * @author Deepak Shajan
 * @see FailureConditionChainLink
 */
final class SuccessConditionChainLink implements MonitorThreadCompletionConditionChainLink {

    /**
     * Check if the current log line to be evaluated matches with the {@link ProcessStatusConstant#TOMCAT_STARTED_REGEX}. If yes then check if the next log line matches the
     * {@link ProcessStatusConstant#PROCESS_COMPLETION_REGEX}. If that is also a yes then return true.
     * @param evaluatorRequest Request on which the chain operates, refer {@link EvaluatorRequest}
     * @return true if both the patterns match, else returns false.
     * @throws IOException
     */
    @Override
    public boolean applies(EvaluatorRequest evaluatorRequest) throws IOException {
        return evaluatorRequest.getLogLine().matches(ProcessStatusConstant.TOMCAT_STARTED_REGEX) && evaluatorRequest.getBufferedReader().readLine().matches(ProcessStatusConstant.PROCESS_COMPLETION_REGEX);
    }

    /**
     * Since the process has successfully started, we simply log the data and return true.
     * @param evaluatorRequest Request on which the chain operates, refer {@link EvaluatorRequest}
     * @return true
     */
    @Override
    public boolean logic(EvaluatorRequest evaluatorRequest) {
        LOGGER.info("***** " + evaluatorRequest.getRequest().getModuleName() + " started successfully *****");
        return true;
    }


}
