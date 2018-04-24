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

package com.initializr.constants;

/**
 * All constants related to the status of the processes(microservice) are held here.
 */
public final class ProcessStatusConstant implements Constant{

    /**
     * Regular expression pattern used to determine if the embedded tomcat in each spring boot microservice
     * has started.
     *
     * <p>This pattern is matched with the log files to determine that tomcat is started.</p>
     */
    public static final String TOMCAT_STARTED_REGEX = ".+Tomcat started on port\\(s\\):.+";

    /**
     * Regular expression pattern used to determine if the spring boot microserivce application has started.
     *
     * * <p>This pattern is matched with the log files to determine that application has started.</p>
     */
    public static final String PROCESS_COMPLETION_REGEX = ".+Started .+ in .+ \\(JVM running for .+\\)";

    /**
     * Regular expression pattern used to determine if the spring boot microservice application startup
     * has failed.<b>This variable is applicable only in case of maven buil;d type.</b>
     *
     * * <p>This pattern is matched with the log files to determine that startup has failed.</p>
     */
    public static final String PROCESS_FAIL_REGEX_MAVEN = ".+BUILD FAILURE";

    /**
     * Regular expression pattern used to determine if the spring boot microservice application startup
     * has failed.
     *
     * * <p>This pattern is matched with the log files to determine that startup has failed.</p>
     */
    public static final String PROCESS_FAIL_REGEX_GRADLE = ":bootRun.+FAILED";
}
