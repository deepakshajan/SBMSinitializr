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

package com.initializr.startup;

import com.initializr.context.ApplicationContextProvider;
import com.initializr.context.CloseApplicationContextThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Defines all the actions to be performed on startup of the application.
 * @author Deepak Shajan
 */
@Component
@Scope("prototype")
public final class ApplicationStartUpOperationsBean {

    @Autowired
    private ApplicationContextProvider applicationContextProvider;

    /**
     * Registers a shutdown hook to the {@link org.springframework.context.ApplicationContext}.
     *
     * <p>The shutdown hook is responsible for clean up of all process started using this application</p>
     */
    public void registerShutDownHook() {

        AbstractApplicationContext applicationContext = (AbstractApplicationContext) applicationContextProvider.getApplicationContext();
        Runtime.getRuntime().addShutdownHook(new CloseApplicationContextThread<AbstractApplicationContext>(applicationContext));
    }

}
