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

import com.initializr.SbmSinitializrApplication;
import com.initializr.backbone.SBMSConstant;

/**
 * All constants related to the {@link org.springframework.core.task.TaskExecutor}'s used are held here.
 * @author Deepak Shajan
 */
public final class TaskConstant implements SBMSConstant {

    /**
     *  Unique identifier for the {@link SbmSinitializrApplication#processTaskExecutor()}
     */
    public static final String PROCESS_TASK_EXECUTER_NAME = "processtask_task_executor";

    /**
     *  Unique identifier for the {@link SbmSinitializrApplication#monitorTaskExecutor()} ()}
     */
    public static final String MONITOR_TASK_EXECUTER_NAME = "monitor_task_executor";

    /**
     *  Unique identifier for the {@link SbmSinitializrApplication#smartProcessTaskExecutor()}
     */
    public static final String SMART_PROCESS_TASK_EXECUTER_NAME = "smart_process_task_executor";
}
