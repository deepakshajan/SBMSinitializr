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


import com.initializr.exception.ProcessNotFoundInPoolException;
import com.initializr.process.pool.ProcessPoolProvider;
import com.initializr.process.thread.ProcessThread;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;

/**
 * Provides various functions to operate on the {@link ProcessPoolProvider#pool} object.
 * @author Deepak Shajan
 */
@Component
public final class ProcessPoolOperations {


    /**
     * Add a process to the processPool.
     * <p>Adding the process to the process pool does not mean that the process is complete. It simply means that the process start procedure has been initiated.</p>
     * @param processThread : The {@link ProcessThread} instance in which the process is booting up.
     */
    public void addProcessToPool(ProcessThread processThread) {

        ProcessPoolProvider processPoolProvider = ProcessPoolProvider.getProcessPoolProvider();
        processPoolProvider.addProcessToPool(processThread);
    }

    /**
     * Marks the process as successfully started.
     *
     * <p>The <code>processIdentifier</code> will be added to the {@link ProcessPoolProvider#completedProcessIds}</p>
     * @param processThread The {@link ProcessThread} instance in which the process is booting up.
     */
    public void markProcessAsCompleted(ProcessThread processThread) {

        markProcessAsCompleted(processThread.getProcessIdentifier());
    }

    /**
     * Marks the process as successfully started.
     *
     * <p>The <code>processIdentifier</code> will be added to the {@link ProcessPoolProvider#completedProcessIds}</p>
     * @param processIdentifier The module name of the process to be marked. Should be unique for each process.
     */
    public void markProcessAsCompleted(String processIdentifier) {

        ProcessPoolProvider processPoolProvider = ProcessPoolProvider.getProcessPoolProvider();
        processPoolProvider.markProcessAsCompleted(processIdentifier);
    }

    /**
     * Remove the process from the {@link ProcessPoolProvider#pool}
     * @param processThread The {@link ProcessThread} instance in which the process is booting up.
     * @return true if removed, else returns false.
     */
    public boolean removeProcessFromPool(ProcessThread processThread) {

        ProcessPoolProvider pool = ProcessPoolProvider.getProcessPoolProvider();
        return pool.removeProcessFromPool(processThread.getProcessIdentifier());
    }

    /**
     * Use this method to force stop any running process.
     * <p>This method makes sure that the underlying operating system process is killed</p>
     * @param processThread The {@link ProcessThread} instance in which the process was booting up.
     */
    public void forceStopProcess(ProcessThread processThread) {

        forceStopProcess(processThread.getProcessIdentifier());
    }

    /**
     * Use this method to force stop any running process.
     *
     * <p>This method makes sure that the underlying operating system process is killed. Also removes the same from the {@link ProcessPoolProvider#pool}
     * and {@link ProcessPoolProvider#completedProcessIds} if needed.</p>
     * @param processIdentifier The module name of the process to be marked. Should be unique for each process.
     * @return true if stop is successful.
     */
    public boolean forceStopProcess(String processIdentifier) {

        ProcessThreadOperations processThreadOperations = new ProcessThreadOperations();
        ProcessPoolProvider processPoolProvider = ProcessPoolProvider.getProcessPoolProvider();
        Map<String, Process> processPool = processPoolProvider.getPool();
        Process process = processPool.get(processIdentifier);
        if(process == null)
            throw new ProcessNotFoundInPoolException();
        process.destroyForcibly();
        processThreadOperations.destroyProcessForcibily(processPool.get(processIdentifier));
        return processPoolProvider.removeProcessFromPool(processIdentifier);
    }

    /**
     * Kills all the processes which has been started or is being started by this application.
     *
     * <p>After execution of this method the {@link ProcessPoolProvider#pool} and {@link ProcessPoolProvider#completedProcessIds} variable should be empty.</p>
     */
    public void forceStopAllProcess() {

        ProcessPoolProvider processPoolProvider = ProcessPoolProvider.getProcessPoolProvider();
        Map<String, Process> processPool = processPoolProvider.getPool();
        if(processPool != null) {
            Iterator<String> iter = processPool.keySet().iterator();
            iter.forEachRemaining(this::forceStopProcess);
        }
    }

    /**
     * Checks if the process is registered to be either started or starting.
     * @param processIdentifier The unique identifier for the process.
     * @return true if the process is either started or is starting.Else returns false.
     */
    public boolean isProcessRunning(String processIdentifier) {

        ProcessPoolProvider processPoolProvider = ProcessPoolProvider.getProcessPoolProvider();
        Map<String, Process> pool = processPoolProvider.getPool();
        if(pool != null && pool.size()>0)
            return pool.containsKey(processIdentifier);
        return false;
    }

    /**
     * Checks if the process has been started.
     * @param processIdentifier The unique identifier for the process.
     * @return true if the process has started successfully,else returns false.
     */
    public boolean isProcessCompleted(String processIdentifier) {

        ProcessPoolProvider processPoolProvider = ProcessPoolProvider.getProcessPoolProvider();
        boolean isCompleted = processPoolProvider.getProcessCompletionStatus(processIdentifier);
        return isCompleted;
    }

}