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
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.Assert.assertEquals;


/**
 * Test class for {@link ProcessPoolOperations}
 * @author Deepak Shajan
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ProcessPoolOperationsTest {


    @Mock
    ProcessThread processThread;

    @Mock
    Process process;

    @Before
    public void setUp() {
        Mockito.when(processThread.getProcessIdentifier()).thenReturn("testProcessIdentifier");
        Mockito.when(processThread.getProcess()).thenReturn(process);
    }

    /**
     * Clears the process pools after each test method.
     *
     * <p>This is very important as the {@link ProcessPoolProvider} instance is singleton. </p>
     */
    @After
    public void tearDown() {
        ProcessPoolProvider.getProcessPoolProvider().removeProcessFromPool(processThread.getProcessIdentifier());
    }

    /**
     * Should add the process to the {@link com.initializr.process.pool.ProcessPoolProvider#pool}
     */
    @Test
    public void testAddProcessToPool() {

        Map<String, Process> processPool = ProcessPoolProvider.getProcessPoolProvider().getPool();
        int initialPoolSize = processPool != null ? processPool.size() : 0;
        new ProcessPoolOperations().addProcessToPool(processThread);
        processPool = ProcessPoolProvider.getProcessPoolProvider().getPool();
        assertEquals(initialPoolSize+1,processPool.size());
    }

    /**
     * Should update the {@link ProcessPoolProvider#completedProcessIds} list
     */
    @Test
    public void testMarkProcessAsCompletedWhenProcessIsInPool() {
        ProcessPoolProvider.getProcessPoolProvider().addProcessToPool(processThread);

        boolean initialCompletionStatus = ProcessPoolProvider.getProcessPoolProvider().getProcessCompletionStatus(processThread.getProcessIdentifier());
        assertEquals(false, initialCompletionStatus);
        new ProcessPoolOperations().markProcessAsCompleted(processThread);
        boolean completionStatus = ProcessPoolProvider.getProcessPoolProvider().getProcessCompletionStatus(processThread.getProcessIdentifier());
        assertEquals(true, completionStatus);
    }

    /**
     * Should throw exception({@link com.initializr.exception.ProcessNotFoundInPoolException})
     */
    @Test(expected = ProcessNotFoundInPoolException.class)
    public void testMarkProcessAsCompletedWhenProcessIsNotInPool() {
        new ProcessPoolOperations().markProcessAsCompleted(processThread);
    }

    /**
     * Should update the {@link ProcessPoolProvider#completedProcessIds} list
     */
    @Test
    public void testMarkProcessAsCompleted1WhenProcessIsInPool() {
        ProcessPoolProvider.getProcessPoolProvider().addProcessToPool(processThread);

        boolean initialCompletionStatus = ProcessPoolProvider.getProcessPoolProvider().getProcessCompletionStatus(processThread.getProcessIdentifier());
        assertEquals(false, initialCompletionStatus);
        new ProcessPoolOperations().markProcessAsCompleted(processThread.getProcessIdentifier());
        boolean completionStatus = ProcessPoolProvider.getProcessPoolProvider().getProcessCompletionStatus(processThread.getProcessIdentifier());
        assertEquals(true, completionStatus);
    }

    /**
     * Should throw exception({@link com.initializr.exception.ProcessNotFoundInPoolException})
     */
    @Test(expected = ProcessNotFoundInPoolException.class)
    public void testMarkProcessAsCompleted1WhenProcessIsNotInPool() {
        new ProcessPoolOperations().markProcessAsCompleted(processThread.getProcessIdentifier());
    }

    /**
     * Should remove the process from {@link ProcessPoolProvider#pool}
     * @throws Exception
     */
    @Test
    public void testRemoveProcessFromPoolShouldRemoveFromPool() {
        new ProcessPoolOperations().addProcessToPool(processThread);
        new ProcessPoolOperations().markProcessAsCompleted(processThread);

        int initialSize = new ProcessPoolOperations().getCurrentProcessPoolSize();
        new ProcessPoolOperations().removeProcessFromPool(processThread);
        int finalSize = new ProcessPoolOperations().getCurrentProcessPoolSize();
        assertEquals(initialSize-1, finalSize);
    }

    /**
     * Should remove the process from {@link ProcessPoolProvider#completedProcessIds}
     */
    @Test
    public void testRemoveProcessFromPoolShouldRemoveFromCompletedIds() {
        new ProcessPoolOperations().addProcessToPool(processThread);
        new ProcessPoolOperations().markProcessAsCompleted(processThread);

        boolean initialCompletionStatus = ProcessPoolProvider.getProcessPoolProvider().getProcessCompletionStatus(processThread.getProcessIdentifier());
        assertEquals(true, initialCompletionStatus);
        new ProcessPoolOperations().removeProcessFromPool(processThread);
        boolean finalCompletionStatus = ProcessPoolProvider.getProcessPoolProvider().getProcessCompletionStatus(processThread.getProcessIdentifier());
        assertEquals(false, finalCompletionStatus);
    }

    /**
     * Should destroy the underlying system process.
     */
    @Test
    public void testForceStopProcessShouldDestroyProcess() {
        new ProcessPoolOperations().addProcessToPool(processThread);
        new ProcessPoolOperations().markProcessAsCompleted(processThread);

        new ProcessPoolOperations().forceStopProcess(processThread);
        Mockito.verify(process, Mockito.atLeast(1)).destroyForcibly();
    }

    /**
     * Should remove the process from {@link ProcessPoolProvider#pool}
     */
    @Test
    public void testForceStopProcessShouldRemoveFromPool() {
        new ProcessPoolOperations().addProcessToPool(processThread);
        new ProcessPoolOperations().markProcessAsCompleted(processThread);

        int initialSize = new ProcessPoolOperations().getCurrentProcessPoolSize();
        new ProcessPoolOperations().forceStopProcess(processThread);
        int finalSize = new ProcessPoolOperations().getCurrentProcessPoolSize();
        assertEquals(initialSize-1, finalSize);
    }

    /**
     * Should remove the process from {@link ProcessPoolProvider#completedProcessIds}
     */
    @Test
    public void testForceStopProcessShouldRemoveFromCompletedIds() {
        new ProcessPoolOperations().addProcessToPool(processThread);
        new ProcessPoolOperations().markProcessAsCompleted(processThread);

        boolean initialCompletionStatus = ProcessPoolProvider.getProcessPoolProvider().getProcessCompletionStatus(processThread.getProcessIdentifier());
        assertEquals(true, initialCompletionStatus);
        new ProcessPoolOperations().forceStopProcess(processThread);
        boolean finalCompletionStatus = ProcessPoolProvider.getProcessPoolProvider().getProcessCompletionStatus(processThread.getProcessIdentifier());
        assertEquals(false, finalCompletionStatus);
    }

    @Test(expected = ProcessNotFoundInPoolException.class)
    public void testForceStopProcessShouldThrowException() {
        new ProcessPoolOperations().forceStopProcess(processThread);
    }


    /**
     * Should destroy the underlying system process.
     */
    @Test
    public void testForceStopProcess1ShouldDestroyProcess() {
        new ProcessPoolOperations().addProcessToPool(processThread);
        new ProcessPoolOperations().markProcessAsCompleted(processThread);

        new ProcessPoolOperations().forceStopProcess(processThread);
        Mockito.verify(process, Mockito.atLeast(1)).destroyForcibly();
    }

    /**
     * Should remove the process from {@link ProcessPoolProvider#pool}
     */
    @Test
    public void testForceStopProcess1ShouldRemoveFromPool() {
        new ProcessPoolOperations().addProcessToPool(processThread);
        new ProcessPoolOperations().markProcessAsCompleted(processThread);

        int initialSize = new ProcessPoolOperations().getCurrentProcessPoolSize();
        new ProcessPoolOperations().forceStopProcess(processThread);
        int finalSize = new ProcessPoolOperations().getCurrentProcessPoolSize();
        assertEquals(initialSize-1, finalSize);
    }

    /**
     * Should remove the process from {@link ProcessPoolProvider#completedProcessIds}
     */
    @Test
    public void testForceStopProcess1ShouldRemoveFromCompletedIds() {
        new ProcessPoolOperations().addProcessToPool(processThread);
        new ProcessPoolOperations().markProcessAsCompleted(processThread);

        boolean initialCompletionStatus = ProcessPoolProvider.getProcessPoolProvider().getProcessCompletionStatus(processThread.getProcessIdentifier());
        assertEquals(true, initialCompletionStatus);
        new ProcessPoolOperations().forceStopProcess(processThread);
        boolean finalCompletionStatus = ProcessPoolProvider.getProcessPoolProvider().getProcessCompletionStatus(processThread.getProcessIdentifier());
        assertEquals(false, finalCompletionStatus);
    }

    /**
     * Should throw {@link ProcessNotFoundInPoolException}
     */
    @Test(expected = ProcessNotFoundInPoolException.class)
    public void testForceStopProcess1ShouldThrowException() {
        new ProcessPoolOperations().forceStopProcess(processThread);
    }

    /**
     * Should destroy all the process
     */
    @Test
    public void testForceStopAllProcessShouldStopAllProcess() {
        ProcessThread processThread1 = Mockito.mock(ProcessThread.class);
        ProcessThread processThread2 = Mockito.mock(ProcessThread.class);
        ProcessThread processThread3 = Mockito.mock(ProcessThread.class);
        ProcessThread processThread4 = Mockito.mock(ProcessThread.class);
        ProcessThread processThread5 = Mockito.mock(ProcessThread.class);
        Mockito.when(processThread1.getProcessIdentifier()).thenReturn("testProcessIdentifier1");
        Mockito.when(processThread1.getProcess()).thenReturn(process);
        Mockito.when(processThread2.getProcessIdentifier()).thenReturn("testProcessIdentifier2");
        Mockito.when(processThread2.getProcess()).thenReturn(process);
        Mockito.when(processThread3.getProcessIdentifier()).thenReturn("testProcessIdentifier3");
        Mockito.when(processThread3.getProcess()).thenReturn(process);
        Mockito.when(processThread4.getProcessIdentifier()).thenReturn("testProcessIdentifier4");
        Mockito.when(processThread4.getProcess()).thenReturn(process);
        Mockito.when(processThread5.getProcessIdentifier()).thenReturn("testProcessIdentifier5");
        Mockito.when(processThread5.getProcess()).thenReturn(process);
        new ProcessPoolOperations().addProcessToPool(processThread1);
        new ProcessPoolOperations().markProcessAsCompleted(processThread1);
        new ProcessPoolOperations().addProcessToPool(processThread2);
        new ProcessPoolOperations().markProcessAsCompleted(processThread2);
        new ProcessPoolOperations().addProcessToPool(processThread3);
        new ProcessPoolOperations().markProcessAsCompleted(processThread3);
        new ProcessPoolOperations().addProcessToPool(processThread4);
        new ProcessPoolOperations().markProcessAsCompleted(processThread4);
        new ProcessPoolOperations().addProcessToPool(processThread5);
        new ProcessPoolOperations().markProcessAsCompleted(processThread5);

        new ProcessPoolOperations().forceStopAllProcess();
        Mockito.verify(process, Mockito.atLeast(5)).destroyForcibly();
    }

    /**
     * Should clear {@link ProcessPoolProvider#pool}
     */
    @Test
    public void testForceStopAllProcessShouldClearProcessPool() {
        ProcessThread processThread1 = Mockito.mock(ProcessThread.class);
        ProcessThread processThread2 = Mockito.mock(ProcessThread.class);
        ProcessThread processThread3 = Mockito.mock(ProcessThread.class);
        ProcessThread processThread4 = Mockito.mock(ProcessThread.class);
        ProcessThread processThread5 = Mockito.mock(ProcessThread.class);
        Mockito.when(processThread1.getProcessIdentifier()).thenReturn("testProcessIdentifier1");
        Mockito.when(processThread1.getProcess()).thenReturn(process);
        Mockito.when(processThread2.getProcessIdentifier()).thenReturn("testProcessIdentifier2");
        Mockito.when(processThread2.getProcess()).thenReturn(process);
        Mockito.when(processThread3.getProcessIdentifier()).thenReturn("testProcessIdentifier3");
        Mockito.when(processThread3.getProcess()).thenReturn(process);
        Mockito.when(processThread4.getProcessIdentifier()).thenReturn("testProcessIdentifier4");
        Mockito.when(processThread4.getProcess()).thenReturn(process);
        Mockito.when(processThread5.getProcessIdentifier()).thenReturn("testProcessIdentifier5");
        Mockito.when(processThread5.getProcess()).thenReturn(process);
        new ProcessPoolOperations().addProcessToPool(processThread1);
        new ProcessPoolOperations().markProcessAsCompleted(processThread1);
        new ProcessPoolOperations().addProcessToPool(processThread2);
        new ProcessPoolOperations().markProcessAsCompleted(processThread2);
        new ProcessPoolOperations().addProcessToPool(processThread3);
        new ProcessPoolOperations().markProcessAsCompleted(processThread3);
        new ProcessPoolOperations().addProcessToPool(processThread4);
        new ProcessPoolOperations().markProcessAsCompleted(processThread4);
        new ProcessPoolOperations().addProcessToPool(processThread5);
        new ProcessPoolOperations().markProcessAsCompleted(processThread5);

        new ProcessPoolOperations().forceStopAllProcess();
        assertEquals(0, new ProcessPoolOperations().getCurrentProcessPoolSize());
    }

    /**
     * Should clear {@link ProcessPoolProvider#completedProcessIds}
     */
    @Test
    public void testForceStopAllProcessShouldClearCompletedIds() {
        ProcessThread processThread1 = Mockito.mock(ProcessThread.class);
        ProcessThread processThread2 = Mockito.mock(ProcessThread.class);
        ProcessThread processThread3 = Mockito.mock(ProcessThread.class);
        ProcessThread processThread4 = Mockito.mock(ProcessThread.class);
        ProcessThread processThread5 = Mockito.mock(ProcessThread.class);
        Mockito.when(processThread1.getProcessIdentifier()).thenReturn("testProcessIdentifier1");
        Mockito.when(processThread1.getProcess()).thenReturn(process);
        Mockito.when(processThread2.getProcessIdentifier()).thenReturn("testProcessIdentifier2");
        Mockito.when(processThread2.getProcess()).thenReturn(process);
        Mockito.when(processThread3.getProcessIdentifier()).thenReturn("testProcessIdentifier3");
        Mockito.when(processThread3.getProcess()).thenReturn(process);
        Mockito.when(processThread4.getProcessIdentifier()).thenReturn("testProcessIdentifier4");
        Mockito.when(processThread4.getProcess()).thenReturn(process);
        Mockito.when(processThread5.getProcessIdentifier()).thenReturn("testProcessIdentifier5");
        Mockito.when(processThread5.getProcess()).thenReturn(process);
        new ProcessPoolOperations().addProcessToPool(processThread1);
        new ProcessPoolOperations().markProcessAsCompleted(processThread1);
        new ProcessPoolOperations().addProcessToPool(processThread2);
        new ProcessPoolOperations().markProcessAsCompleted(processThread2);
        new ProcessPoolOperations().addProcessToPool(processThread3);
        new ProcessPoolOperations().markProcessAsCompleted(processThread3);
        new ProcessPoolOperations().addProcessToPool(processThread4);
        new ProcessPoolOperations().markProcessAsCompleted(processThread4);
        new ProcessPoolOperations().addProcessToPool(processThread5);
        new ProcessPoolOperations().markProcessAsCompleted(processThread5);

        new ProcessPoolOperations().forceStopAllProcess();
        assertEquals(false, ProcessPoolProvider.getProcessPoolProvider().getProcessCompletionStatus("testProcessIdentifier1"));
        assertEquals(false, ProcessPoolProvider.getProcessPoolProvider().getProcessCompletionStatus("testProcessIdentifier2"));
        assertEquals(false, ProcessPoolProvider.getProcessPoolProvider().getProcessCompletionStatus("testProcessIdentifier3"));
        assertEquals(false, ProcessPoolProvider.getProcessPoolProvider().getProcessCompletionStatus("testProcessIdentifier4"));
        assertEquals(false, ProcessPoolProvider.getProcessPoolProvider().getProcessCompletionStatus("testProcessIdentifier5"));
    }

    /**
     * Should return {@code true}
     */
    @Test
    public void testIsProcessRunningShouldReturnTrue() {
        new ProcessPoolOperations().addProcessToPool(processThread);
        new ProcessPoolOperations().markProcessAsCompleted(processThread);

        boolean isRunning = new ProcessPoolOperations().isProcessRunning(processThread.getProcessIdentifier());
        assertEquals(true, isRunning);
    }

    /**
     * Should return {@code false}
     */
    @Test
    public void testIsProcessRunningShouldReturnFalse() {
        new ProcessPoolOperations().addProcessToPool(processThread);
        new ProcessPoolOperations().markProcessAsCompleted(processThread);

        boolean isRunning = new ProcessPoolOperations().isProcessRunning("InvalidProcessId");
        assertEquals(false, isRunning);
    }

    /**
     * Should return {@code false}
     */
    @Test
    public void testIsProcessRunningShouldReturnFalseWhenNotgingInPool() {
        boolean isRunning = new ProcessPoolOperations().isProcessRunning("InvalidProcessId");
        assertEquals(false, isRunning);
    }

    /**
     * Should return {@code true}
     */
    @Test
    public void testIsProcessCompletedShouldReturnTrue() {
        new ProcessPoolOperations().addProcessToPool(processThread);
        new ProcessPoolOperations().markProcessAsCompleted(processThread);

        boolean isCompleted = new ProcessPoolOperations().isProcessCompleted(processThread.getProcessIdentifier());
        assertEquals(true, isCompleted);
    }

    /**
     * Should return {@code false}
     */
    @Test
    public void testIsProcessCompletedShouldReturnFalse() {
        new ProcessPoolOperations().addProcessToPool(processThread);
        new ProcessPoolOperations().markProcessAsCompleted(processThread);

        boolean isCompleted = new ProcessPoolOperations().isProcessCompleted("invalidProcessId");
        assertEquals(false, isCompleted);
    }

    /**
     * Should return {@code false}
     */
    @Test
    public void testIsProcessCompletedShouldReturnFalseWhenNotingInPool() {
        boolean isCompleted = new ProcessPoolOperations().isProcessCompleted("invalidProcessId");
        assertEquals(false, isCompleted);
    }

    /**
     * Should return [@code zero}
     */
    @Test
    public void testGetCurrentProcessPoolSizeForEmptyPool() {
         int size = new ProcessPoolOperations().getCurrentProcessPoolSize();
         assertEquals(0,size);
    }

    /**
     * Should return size of {@link ProcessPoolProvider#pool}
     */
    @Test
    public void testGetCurrentProcessPoolSize() {
        ProcessThread processThread1 = Mockito.mock(ProcessThread.class);
        ProcessThread processThread2 = Mockito.mock(ProcessThread.class);
        ProcessThread processThread3 = Mockito.mock(ProcessThread.class);
        ProcessThread processThread4 = Mockito.mock(ProcessThread.class);
        ProcessThread processThread5 = Mockito.mock(ProcessThread.class);
        Mockito.when(processThread1.getProcessIdentifier()).thenReturn("testProcessIdentifier1");
        Mockito.when(processThread1.getProcess()).thenReturn(process);
        Mockito.when(processThread2.getProcessIdentifier()).thenReturn("testProcessIdentifier2");
        Mockito.when(processThread2.getProcess()).thenReturn(process);
        Mockito.when(processThread3.getProcessIdentifier()).thenReturn("testProcessIdentifier3");
        Mockito.when(processThread3.getProcess()).thenReturn(process);
        Mockito.when(processThread4.getProcessIdentifier()).thenReturn("testProcessIdentifier4");
        Mockito.when(processThread4.getProcess()).thenReturn(process);
        Mockito.when(processThread5.getProcessIdentifier()).thenReturn("testProcessIdentifier5");
        Mockito.when(processThread5.getProcess()).thenReturn(process);
        new ProcessPoolOperations().addProcessToPool(processThread1);
        new ProcessPoolOperations().addProcessToPool(processThread2);
        new ProcessPoolOperations().addProcessToPool(processThread3);
        new ProcessPoolOperations().addProcessToPool(processThread4);
        new ProcessPoolOperations().addProcessToPool(processThread5);

        int size = new ProcessPoolOperations().getCurrentProcessPoolSize();
        assertEquals(5,size);
    }
}