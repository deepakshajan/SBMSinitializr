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

package com.initializr.process.thread.cache;

import com.initializr.backbone.SBMSCache;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @author Deepak Shajan
 */
public enum ThreadCache implements SBMSCache {

    INSTANCE;

    private List<Future> threadCache = Collections.synchronizedList(new LinkedList<>());

    public static ThreadCache getInstance() {
        return INSTANCE;
    }

    public List<Future> getAllThreads() {
        return INSTANCE.threadCache;
    }

    public boolean addThreadToCache(Future thread) {
        return INSTANCE.threadCache.add(thread);
    }

    public boolean removeThreadFromCache(Future thread) {
        return INSTANCE.threadCache.remove(thread);
    }

    public void clearCache() {
        INSTANCE.threadCache.clear();
    }

}
