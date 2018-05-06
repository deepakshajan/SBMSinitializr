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

package com.initializr.process.operations;

import com.initializr.constants.LogConstant;
import com.initializr.service.request.StartProcessServiceRequest;
import com.initializr.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;

/**
 * Provides various functions to operate on individual {@link com.initializr.process.thread.ProcessThread}'s.
 * @author Deepak Shajan
 */
@Component
public class ProcessThreadOperations {


    public static final Logger LOGGER = LoggerFactory.getLogger(ProcessPoolOperations.class);

    /**
     * Start the execution of the process, ie start the spring boot microservice.
     * @param request contains details of the microservice to be started.
     * @return the {@link Process} instance corresponding to microservice start operation.
     * @throws IOException any exception is thrown back to the user
     */
    public Process startProcess(@NotNull StartProcessServiceRequest request) throws IOException {

        Process process;
        ProcessBuilder processBuilder = new ProcessBuilder(new String[]{"cmd", "/c", request.getExecutableCommand()});
        processBuilder.directory(new File(request.getExecutionDirectory()));
        processBuilder.redirectOutput(getLogFile(request));
        clearOutputDirectory(request);
        process = processBuilder.start();
        LOGGER.info("***** "+ "Starting "+ request.getModuleName() +" *****");

        return process;
    }

    /**
     * Kill the process and its child processes.
     *
     * <p>Makes sure that the process or none of its child processes are alive.</p>
     * @param process
     */
    void destroyProcessForcibily(@NotNull Process process) {
        if(process.children() != null)
            process.children().forEach(this::destroyProcessForcibily);
        process.destroyForcibly();
    }

    /**
     * Refer {@link ProcessThreadOperations#destroyProcessForcibily(Process)}
     * @param processHandle
     */
    private void destroyProcessForcibily(ProcessHandle processHandle) {
        if(processHandle.children() != null)
            processHandle.children().forEach(this::destroyProcessForcibily);
        processHandle.destroyForcibly();
    }

    /**
     * Creates or replaces the log file corresponding to the request.
     *
     * <p>Each microservice is logged in its own log file.</p>
     * @param request contains details of the microservice to be started.
     * @return log file instance corresponding to the request
     * @throws IOException
     */
    private File getLogFile(StartProcessServiceRequest request) throws IOException {

        File file = new File(LogConstant.LOG_DIRECTORY);
        if(!file.exists())
            file.mkdir();
        String filePath = ".//" + LogConstant.LOG_DIRECTORY +"//" + request.getModuleName()+"."+LogConstant.LOG_FILE_EXTENSION_TYPE;
        file = new FileUtils().createNewFile(filePath);
        if(!file.exists())
            file.createNewFile();
        return  file;
    }

    private void clearOutputDirectory(StartProcessServiceRequest request) {
        new FileUtils().clearFolder(".//" + LogConstant.LOG_DIRECTORY);
    }

}