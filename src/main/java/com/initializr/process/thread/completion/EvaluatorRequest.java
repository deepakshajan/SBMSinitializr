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

import java.io.BufferedReader;

/**
 * Request to be fed into {@link MonitorThreadCompletionConditionEvaluator#evaluate}
 * @author Deepak Shajan
 * @param <RequestTypeT> The type of the request from the caller
 */
public class EvaluatorRequest<RequestTypeT extends StartProcessServiceRequest> {

    /**
     * POJO class, the actual request of type {@link StartProcessServiceRequest} which is obtained from the caller.
     */
    private RequestTypeT request;

    /**
     * The line in the log file on which the current iteration is on.It is on this line that the chain evaluation is performed.
     */
    private String logLine;

    /**
     * The entire {@link BufferedReader} instance used to receive the {@link EvaluatorRequest#logLine}.
     *
     * <p>This variable is usefull in cases in which we have to immediately identify the past and future lines in the log file for additional evaluations within an iteration
     * step.</p>
     */
    private BufferedReader bufferedReader;

    public EvaluatorRequest(RequestTypeT request, String logLine, BufferedReader bufferedReader) {
        this.request = request;
        this.logLine = logLine;
        this.bufferedReader = bufferedReader;
    }

    public RequestTypeT getRequest() {
        return request;
    }

    public void setRequest(RequestTypeT request) {
        this.request = request;
    }

    public String getLogLine() {
        return logLine;
    }

    public void setLogLine(String logLine) {
        this.logLine = logLine;
    }

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public void setBufferedReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }
}
