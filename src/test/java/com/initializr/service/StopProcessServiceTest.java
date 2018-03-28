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

package com.initializr.service;

import com.initializr.exception.ProcessNotFoundInPoolException;
import com.initializr.process.operations.ProcessPoolOperations;
import com.initializr.process.pool.ProcessPoolProvider;
import com.initializr.process.thread.ProcessThread;
import com.initializr.service.request.StopProcessServiceRequestImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link StopProcessService}
 * @author Deepak Shajan
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class StopProcessServiceTest {

    @Autowired
    StopProcessService stopProcessService;

    @Mock
    StopProcessServiceRequestImpl request;

    @Mock
    ProcessThread processThread;

    @Mock
    Process process;

    @Before
    public void setUp() {
        when(processThread.getProcessIdentifier()).thenReturn("testProcessId");
        when(processThread.getProcess()).thenReturn(process);
    }

    /**
     * Should remove the process from {@link com.initializr.process.pool.ProcessPoolProvider#pool}
     */
    @Test
    public void testExecuteShouldRemoveProcessFromPool() {
        new ProcessPoolOperations().addProcessToPool(processThread);
        when(request.getModuleName()).thenReturn("testProcessId");
        int initialPoolSize = new ProcessPoolOperations().getCurrentProcessPoolSize();

        stopProcessService.execute(request);
        int finalPoolSize = new ProcessPoolOperations().getCurrentProcessPoolSize();
        assertEquals(initialPoolSize-1, finalPoolSize);
    }

    /**
     * Should remove the process from {@link com.initializr.process.pool.ProcessPoolProvider#completedProcessIds}
     */
    @Test
    public void testExecuteShouldRemoveProcessFromCompletedIds() {
        new ProcessPoolOperations().addProcessToPool(processThread);
        new ProcessPoolOperations().markProcessAsCompleted(processThread.getProcessIdentifier());
        when(request.getModuleName()).thenReturn("testProcessId");
        boolean isCompleted = new ProcessPoolOperations().isProcessCompleted(processThread.getProcessIdentifier());
        assertEquals(true, isCompleted);

        stopProcessService.execute(request);
        isCompleted = new ProcessPoolOperations().isProcessCompleted(processThread.getProcessIdentifier());
        assertEquals(false, isCompleted);
    }

    /**
     * Should throw {@link ProcessNotFoundInPoolException}
     */
    @Test(expected = ProcessNotFoundInPoolException.class)
    public void testExecuteShouldThrowException() {
        new ProcessPoolOperations().addProcessToPool(processThread);
        when(request.getModuleName()).thenReturn("anotherTestProcessId");

        stopProcessService.execute(request);
    }
}