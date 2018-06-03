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

package com.initializr.utils;

import com.initializr.backbone.SBMSLock;
import com.initializr.backbone.SBMSUtils;
import com.initializr.process.thread.cache.ThreadCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * @author Deepak Shajan
 */
@Component
@Scope(value = "prototype")
public class ThreadUtils implements SBMSUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadUtils.class);

    public void sleep(long timeInMilliSeconds) {

        try {
            Thread.sleep(timeInMilliSeconds);
        } catch (InterruptedException e) {
            LOGGER.error(e.getStackTrace().toString());
        }
    }

    public void waitWithLock(SBMSLock lock) {

        try {
            synchronized (lock) {
                lock.wait();
            }
        } catch (InterruptedException e) {
            LOGGER.error(e.getStackTrace().toString());
        }
    }

    public void notifyAllWithLock(SBMSLock lock) {

        synchronized (lock) {
            lock.notifyAll();
        }
    }

    public boolean startThread(ThreadPoolTaskExecutor executor, Callable thread) {

        Future future = executor.submit(thread);
        return ThreadCache.getInstance().addThreadToCache(future);
    }

    private void stopThread(Future thread) {

        thread.cancel(true);
        ThreadCache.getInstance().removeThreadFromCache(thread);
    }

    public synchronized void shutdownAllThreads () {

        ThreadCache.getInstance().getAllThreads().forEach(this::stopThreadWithoutClearingCache);
        ThreadCache.getInstance().clearCache();
    }

    private void stopThreadWithoutClearingCache(Future thread) {

        thread.cancel(true);
    }
}
