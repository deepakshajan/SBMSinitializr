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

package com.initializr.config;


/**
 * Enum Singleton to define the configurable parameters in the application.
 *
 * <p>All configurations which are meant to be changeable during runtime should be defined here.</p>
 * @author Deepak Shajan
 */
public enum Configuration {

    /**
     * Singleton instance of {@link Configuration}. This singleton instance is thread safe.
     */
    INSTANCE;

    /**
     * Defines the initial thread pool size.
     *
     * <p>Core thread count is the thread pool size which is allotted for each task executor by default when the {@link org.springframework.core.task.TaskExecutor} is initiated.
     * We keep the initial thread pool size to be <code>1</code>. This ensures that at any given point of time a minimum of <code>1</code> threads can run simultaneously.
     * If a new task comes then it is added into a queue for future execution. This addition into queue continues upto {@link Configuration#threadQueueCapacity}<i> (which is {@code 0}
     *  in our case)</i> new tasks.
     * When the queue reaches its full capacity, then a new thread is added to the executor pool.<br/>
     * <b>NOTE : Only a maximum of {@link Configuration#maxThreadCount} number of threads can be simultaneously active in a task executor.Any more than that results in
     * {@link org.springframework.core.task.TaskRejectedException}.</b></p>
     */
    private volatile int coreThreadCount = 1;

    /**
     * Defined the maximum thread pool size.
     *
     * <p>When the number of concurrent start process requests,ie number of hits on {@link com.initializr.service.StartProcessService#execute(Object)} increase, it is not advicible to
     * keep the thread pool size just to <code>8</code> as then many threads will have to waitWithLock or wait in queue for others to finish. Hence if the queue is full then increases
     * the thread count to a maximum value of<code>256</code>.
     * This enables multiple spring boot microservices to start parallel to each other.
     * <b>Note : This value is the maximum number of simultaneous {@link Thread}'s ever to run at any instant. An attempt to execute more tasks by spawning new {@link Thread}'s
     * will result in {@link org.springframework.core.task.TaskRejectedException}</b>
     */
    private volatile int maxThreadCount = 256;

    /**
     * Defines the queue capacity of the {@link org.springframework.core.task.TaskExecutor}.
     *
     * <p>If all of {@link Configuration#coreThreadCount} number of threads are busy and a new task comes up, then It will keep the new tasks in queue. A value of {@code 4}
     * simply means that a maximum of 4 items can be kept in the queue. When the queue is full and another new task comes up, then a new thread is created by the task executor.
     * In our case we set value {@code 0} as we donnot want any task to be waiting in queue.</p>
     */
    private volatile int threadQueueCapacity = 0;

    /**
     * The monitoring pulse duration.
     *
     * <p>The monitoring thread does not run in a continuous fashion, but rather in a pulsed mode. This is done mainly for performance reasons. The monitor thread first sleeps
     * for <code>n seconds</code> and then executes. This cycle continues until the thread is dead.</p>
     */
    private volatile long monitorThreadWaitInterval = 5000L;

    /**
     * Defines the time gap that exists between the initialising the start of each microservice.
     *
     * <p>This configuration is intended to allow sufficient time in between each microservice start so that there are no interference between the corresponding threads.
     * Although there is thread safe implementations done, this time gap adds to the thread safety.</p>
     */
    private volatile long processStartTimeGap = 250L;

    /**
     * @return singleton instance of {@link Configuration}
     */
    public static Configuration getConfiguration() {
        return INSTANCE;
    }


    public int getCoreThreadCount() {
        return coreThreadCount;
    }

    public void setCoreThreadCount(int coreThreadCount) {
        this.coreThreadCount = coreThreadCount;
    }

    public int getMaxThreadCount() {
        return maxThreadCount;
    }

    public void setMaxThreadCount(int maxThreadCount) {
        this.maxThreadCount = maxThreadCount;
    }

    public long getMonitorThreadWaitInterval() {
        return monitorThreadWaitInterval;
    }

    public void setMonitorThreadWaitInterval(long monitorThreadWaitInterval) {
        this.monitorThreadWaitInterval = monitorThreadWaitInterval;
    }

    public int getThreadQueueCapacity() {
        return threadQueueCapacity;
    }

    public void setThreadQueueCapacity(int threadQueueCapacity) {
        this.threadQueueCapacity = threadQueueCapacity;
    }

    public long getProcessStartTimeGap() {
        return processStartTimeGap;
    }

    public void setProcessStartTimeGap(long processStartTimeGap) {
        this.processStartTimeGap = processStartTimeGap;
    }
}
