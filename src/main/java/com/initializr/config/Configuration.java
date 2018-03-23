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
     * <p>Core thread count is the thread pool size which is allotted by default when the {@link org.springframework.core.task.TaskExecutor} is initiated.
     * We keep the initial thread pool size to be <code>2</code> considering the following threads which start for any call to
     * {@link com.initializr.service.StartProcessService#execute(Object)}.
     * <ol>
     *     <li>{@link com.initializr.process.thread.ProcessThread}</li>
     *     <li>{@link com.initializr.process.thread.MonitorThread}</li>
     * </ol>
     * <p><b> Note : It is recommended to provide an even value since the above mentioned threads always exist in pairs.</b></p>
     * </p>
     */
    private volatile int coreThreadCount = 2;

    /**
     * Defined the maximum thread pool size.
     *
     * <p>When the number of concurrent start process requests,ie number of hits on {@link com.initializr.service.StartProcessService#execute(Object)} increase, it is not advicible to
     * keep the thread pool size just to <code>2</code> as then many threads will have to wait for others to finish. Hence we increase the thread pool size to <code>32</code>.
     * This enables multiple spring boot microservices to start parallel to each other, <code>a maximum of 16 to be exact</code>.</p>
     * <p><b> Note : It is recommended to provide an even value for the same reasons mentioned for {@link #coreThreadCount}.</b></p>
     */
    private volatile int maxThreadCount = 32;

    /**
     * The monitoring pulse duration.
     *
     * <p>The monitoring thread does not run in a continuous fashion, but rather in a pulsed mode. This is done mainly for performance reasons. The monitor thread first sleeps
     * for <code>2 seconds</code> and then executes. This cycle continues until the thread is dead.</p>
     */
    private volatile long monitorThreadWaitInterval = 2000L;

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
}
