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

package com.initializr.process.pool;


 import com.initializr.exception.ProcessNotFoundInPoolException;
 import com.initializr.process.thread.ProcessThread;

 import java.util.Collections;
 import java.util.HashSet;
 import java.util.Map;
 import java.util.Set;
 import java.util.concurrent.ConcurrentHashMap;

 /**
  * Enum singleton which holds the state of the application.
  *
  * <p>This singleton instance holds the process pool and the completed process list.</p>
  * @author Deepak Shajan
  */
 public enum ProcessPoolProvider {

     /**
      * Thread safe singleton instance of {@link ProcessPoolProvider}
      */
    INSTANCE;

     /**
      * This {@link Map} stores the microservices/processes currently active
      *
      * <p>Processes are held with key value objects as described below
      * <ul>
      *     <li><b><code>key</code></b> : unique identifier for the process, in this case the processIdentifier(same as module name)</li>
      *     <li><b><code>value</code></b> : actual operating system process instance tied to a microservice</li>
      * </ul>
      * </p>
      * <p><b>Note : Make sure to use a thread safe {@link Map} implemention as this will be concurrently accessed by many threads.</b></p>
      */
    private volatile Map<String, Process> pool = null;

     /**
      * This {@link Set} stores the list of all microservices/process that has successfully started.
      *
      * <p><b>Note : Use a synchronized version of the {@link Set} as this will be concurrently accessed by many threads.</b></p>
      */
    private volatile Set<String> completedProcessIds = null;

     /**
      * This {@link Set} stores the list of all microservices/process that has failed to start.
      *
      * <p><b>Note : Use a synchronized version of the {@link Set} as this will be concurrently accessed by many threads.</b></p>
      */
     private volatile Set<String> failedProcessIds = null;

     /**
      * @return singleton instance of {@link ProcessPoolProvider}
      */
    public static ProcessPoolProvider getProcessPoolProvider() {
        return INSTANCE;
    }

     /**
      * @return {@link ProcessPoolProvider#pool}
      */
    public Map<String, Process> getPool() {
        return this.pool != null ? this.pool : Collections.emptyMap();
    }

     /**
      * Add the {@link Process} contained in the {@link ProcessThread} parameter to the {@link ProcessPoolProvider#pool}
      * @param processThread the thread in which the process is running..
      */
    public void addProcessToPool(ProcessThread processThread) {
        if(pool==null)
            pool = new ConcurrentHashMap<String,Process>();
        pool.put(processThread.getProcessIdentifier(),processThread.getProcess());
    }

     /**
      * Marks the {@link Process}(microsercvice) as successfully started.
      * @param processThread The thread in which the process is running.
      * @throws ProcessNotFoundInPoolException Refer {@link ProcessNotFoundInPoolException}
      */
    public void markProcessAsCompleted(ProcessThread processThread) throws ProcessNotFoundInPoolException {
        markProcessAsCompleted(processThread.getProcessIdentifier());
    }

     /**
      * Marks the {@link Process}(microsercvice) as failed to start.
      * @param processThread The thread in which the process is running.
      * @throws ProcessNotFoundInPoolException Refer {@link ProcessNotFoundInPoolException}
      */
     public void markProcessAsFailed(ProcessThread processThread) throws ProcessNotFoundInPoolException {
         markProcessAsFailed(processThread.getProcessIdentifier());
     }

     /**
      * Refer to {@link ProcessPoolProvider#markProcessAsCompleted(ProcessThread)}
      * @param processIdentifier Unique identifier for each process, in our case the module name.
      * @throws ProcessNotFoundInPoolException Refer {@link ProcessNotFoundInPoolException}
      */
    public void markProcessAsCompleted(String processIdentifier) throws ProcessNotFoundInPoolException {
        if(null != getProcessFromPool(processIdentifier)) {
            if(completedProcessIds == null)
                completedProcessIds = Collections.synchronizedSet(new HashSet<String>());
            completedProcessIds.add(processIdentifier);
        } else {
            throw new ProcessNotFoundInPoolException();
        }
    }

     /**
      * Refer to {@link ProcessPoolProvider#markProcessAsFailed(ProcessThread)}
      * @param processIdentifier Unique identifier for each process, in our case the module name.
      * @throws ProcessNotFoundInPoolException Refer {@link ProcessNotFoundInPoolException}
      */
     public void markProcessAsFailed(String processIdentifier) throws ProcessNotFoundInPoolException {
         if(null != getProcessFromPool(processIdentifier)) {
             if(failedProcessIds == null)
                 failedProcessIds = Collections.synchronizedSet(new HashSet<String>());
             failedProcessIds.add(processIdentifier);
         } else {
             throw new ProcessNotFoundInPoolException();
         }
     }

     /**
      * Removes a process from the {@link ProcessPoolProvider#completedProcessIds}.
      * @param processIdentifer Unique identifier for each process, in our case the module name.
      */
    private void removeFromCompletedProcessIds(String processIdentifer) {
        if(null != completedProcessIds)
            completedProcessIds.remove(processIdentifer);
    }

     /**
      * Removes a process from the {@link ProcessPoolProvider#failedProcessIds}.
      * @param processIdentifer Unique identifier for each process, in our case the module name.
      */
     private void removeFromFailedProcessIds(String processIdentifer) {
         if(null != failedProcessIds)
             failedProcessIds.remove(processIdentifer);
     }

     /**
      * @param processIdentifer Unique identifier for each process, in our case the module name.
      * @return Process corresponding to the processIdentifier
      */
    public Process getProcessFromPool(String processIdentifer) {
        return this.pool!= null ? this.pool.get(processIdentifer) : null;
    }

     /**
      * Check if a process has been completed, ie if the microservice has successfully started.
      * @param processIdentifier Unique identifier for each process, in our case the module name.
      * @return true if completed, else returns false.
      */
    public synchronized boolean isProcessStarted(String processIdentifier) {

        if(completedProcessIds == null)
            return false;
        return completedProcessIds.contains(processIdentifier);
    }

     /**
      * Check if a process has been completed, ie if the microservice has successfully started.
      * @param processIdentifier Unique identifier for each process, in our case the module name.
      * @return true if completed, else returns false.
      */
     public synchronized boolean isProcessFailed(String processIdentifier) {

         if(failedProcessIds == null)
             return false;
         return failedProcessIds.contains(processIdentifier);
     }

     /**
      * Removes a process from {@link ProcessPoolProvider#pool}
      * @param processIdentifier Unique identifier for each process, in our case the module name.
      * @return true is removed, else return false
      */
    public boolean removeProcessFromPool(String processIdentifier) {

        if(getProcessFromPool(processIdentifier) != null) {
            int initialPoolSize = this.pool.size();
            pool.remove(processIdentifier);
            removeFromCompletedProcessIds(processIdentifier);
            removeFromFailedProcessIds(processIdentifier);
            int finalPoolSize = this.pool.size();
            return initialPoolSize == finalPoolSize + 1;
        }
        return false;
    }

}
