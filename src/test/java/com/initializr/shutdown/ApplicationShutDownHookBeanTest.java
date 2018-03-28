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

package com.initializr.shutdown;

import com.initializr.process.operations.ProcessPoolOperations;
import com.initializr.process.pool.ProcessPoolProvider;
import com.initializr.process.thread.ProcessThread;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link ApplicationShutDownHookBean}
 * @author Deepak Shajan
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplicationShutDownHookBeanTest {

    @Autowired
    ApplicationShutDownHookBean applicationShutDownHookBean;

    @Mock
    ProcessThread processThread1;

    @Mock
    ProcessThread processThread2;

    @Mock
    ProcessThread processThread3;

    @Mock
    Process process;

    @Before
    public void setUp() {
        when(processThread1.getProcessIdentifier()).thenReturn("testProcessId1");
        when(processThread1.getProcess()).thenReturn(process);
        when(processThread2.getProcessIdentifier()).thenReturn("testProcessId2");
        when(processThread2.getProcess()).thenReturn(process);
        when(processThread3.getProcessIdentifier()).thenReturn("testProcessId3");
        when(processThread3.getProcess()).thenReturn(process);
    }

    @After
    public void tearDown() {
        new ProcessPoolOperations().forceStopAllProcess();
    }

    /**
     * Should kill all active process and clear the {@link com.initializr.process.pool.ProcessPoolProvider#pool}
     */
    @Test
    public void testExecuteShouldClearPool() {
        new ProcessPoolOperations().addProcessToPool(processThread1);
        new ProcessPoolOperations().addProcessToPool(processThread2);
        new ProcessPoolOperations().addProcessToPool(processThread3);

        applicationShutDownHookBean.execute();
        int poolSize = new ProcessPoolOperations().getCurrentProcessPoolSize();
        assertEquals(0, poolSize);
    }

    /**
     * Should kill all active process and clear the {@link com.initializr.process.pool.ProcessPoolProvider#completedProcessIds}
     */
    @Test
    public void testExecuteShouldClearCompletedProcessId() {
        new ProcessPoolOperations().addProcessToPool(processThread1);
        new ProcessPoolOperations().markProcessAsCompleted(processThread1);
        new ProcessPoolOperations().addProcessToPool(processThread2);
        new ProcessPoolOperations().markProcessAsCompleted(processThread2);
        new ProcessPoolOperations().addProcessToPool(processThread3);
        new ProcessPoolOperations().markProcessAsCompleted(processThread3);

        applicationShutDownHookBean.execute();
        boolean isComplete = ProcessPoolProvider.getProcessPoolProvider().getProcessCompletionStatus(processThread1.getProcessIdentifier());
        assertEquals(false, isComplete);
        isComplete = ProcessPoolProvider.getProcessPoolProvider().getProcessCompletionStatus(processThread2.getProcessIdentifier());
        assertEquals(false, isComplete);
        isComplete = ProcessPoolProvider.getProcessPoolProvider().getProcessCompletionStatus(processThread3.getProcessIdentifier());
        assertEquals(false, isComplete);
    }
}