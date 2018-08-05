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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.Assert.assertEquals;


/**
 * Test class for {@link ProcessPoolOperations}
 * @author Deepak Shajan
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProcessPoolOperationsTest {

    @Autowired
    ProcessPoolOperations processPoolOperations;

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
        processPoolOperations.addProcessToPool(processThread);
        processPool = ProcessPoolProvider.getProcessPoolProvider().getPool();
        assertEquals(initialPoolSize+1,processPool.size());
    }

    /**
     * Should update the {@link ProcessPoolProvider#completedProcessIds} list
     */
    @Test
    public void testMarkProcessAsFailedWhenProcessIsInPool() {
        ProcessPoolProvider.getProcessPoolProvider().addProcessToPool(processThread);

        boolean initialFailStatus = ProcessPoolProvider.getProcessPoolProvider().isProcessFailed(processThread.getProcessIdentifier());
        assertEquals(false, initialFailStatus);
        processPoolOperations.markProcessAsFailed(processThread);
        boolean failStatus = ProcessPoolProvider.getProcessPoolProvider().isProcessFailed(processThread.getProcessIdentifier());
        assertEquals(true, failStatus);
    }

    /**
     * Should throw exception({@link com.initializr.exception.ProcessNotFoundInPoolException})
     */
    @Test(expected = ProcessNotFoundInPoolException.class)
    public void testMarkProcessAsFailedWhenProcessIsNotInPool() {
        processPoolOperations.markProcessAsFailed(processThread);
    }

    /**
     * Should update the {@link ProcessPoolProvider#completedProcessIds} list
     */
    @Test
    public void testMarkProcessAsFailed1WhenProcessIsInPool() {
        ProcessPoolProvider.getProcessPoolProvider().addProcessToPool(processThread);

        boolean initialFailStatus = ProcessPoolProvider.getProcessPoolProvider().isProcessFailed(processThread.getProcessIdentifier());
        assertEquals(false, initialFailStatus);
        processPoolOperations.markProcessAsFailed(processThread.getProcessIdentifier());
        boolean failStatus = ProcessPoolProvider.getProcessPoolProvider().isProcessFailed(processThread.getProcessIdentifier());
        assertEquals(true, failStatus);
    }

    /**
     * Should throw exception({@link com.initializr.exception.ProcessNotFoundInPoolException})
     */
    @Test(expected = ProcessNotFoundInPoolException.class)
    public void testMarkProcessAsFailed1WhenProcessIsNotInPool() {
        processPoolOperations.markProcessAsFailed(processThread.getProcessIdentifier());
    }

    /**
     * Should update the {@link ProcessPoolProvider#completedProcessIds} list
     */
    @Test
    public void testMarkProcessAsCompletedWhenProcessIsInPool() {
        ProcessPoolProvider.getProcessPoolProvider().addProcessToPool(processThread);

        boolean initialCompletionStatus = ProcessPoolProvider.getProcessPoolProvider().isProcessStarted(processThread.getProcessIdentifier());
        assertEquals(false, initialCompletionStatus);
        processPoolOperations.markProcessAsCompleted(processThread);
        boolean completionStatus = ProcessPoolProvider.getProcessPoolProvider().isProcessStarted(processThread.getProcessIdentifier());
        assertEquals(true, completionStatus);
    }

    /**
     * Should throw exception({@link com.initializr.exception.ProcessNotFoundInPoolException})
     */
    @Test(expected = ProcessNotFoundInPoolException.class)
    public void testMarkProcessAsCompletedWhenProcessIsNotInPool() {
        processPoolOperations.markProcessAsCompleted(processThread);
    }

    /**
     * Should update the {@link ProcessPoolProvider#completedProcessIds} list
     */
    @Test
    public void testMarkProcessAsCompleted1WhenProcessIsInPool() {
        ProcessPoolProvider.getProcessPoolProvider().addProcessToPool(processThread);

        boolean initialCompletionStatus = ProcessPoolProvider.getProcessPoolProvider().isProcessStarted(processThread.getProcessIdentifier());
        assertEquals(false, initialCompletionStatus);
        processPoolOperations.markProcessAsCompleted(processThread.getProcessIdentifier());
        boolean completionStatus = ProcessPoolProvider.getProcessPoolProvider().isProcessStarted(processThread.getProcessIdentifier());
        assertEquals(true, completionStatus);
    }

    /**
     * Should throw exception({@link com.initializr.exception.ProcessNotFoundInPoolException})
     */
    @Test(expected = ProcessNotFoundInPoolException.class)
    public void testMarkProcessAsCompleted1WhenProcessIsNotInPool() {
        processPoolOperations.markProcessAsCompleted(processThread.getProcessIdentifier());
    }

    /**
     * Should remove the process from {@link ProcessPoolProvider#pool}
     * @throws Exception
     */
    @Test
    public void testRemoveProcessFromPoolShouldRemoveFromPool() {
        processPoolOperations.addProcessToPool(processThread);
        processPoolOperations.markProcessAsCompleted(processThread);

        int initialSize = processPoolOperations.getCurrentProcessPoolSize();
        processPoolOperations.removeProcessFromPool(processThread);
        int finalSize = processPoolOperations.getCurrentProcessPoolSize();
        assertEquals(initialSize-1, finalSize);
    }

    /**
     * Should remove the process from {@link ProcessPoolProvider#completedProcessIds}
     */
    @Test
    public void testRemoveProcessFromPoolShouldRemoveFromCompletedIds() {
        processPoolOperations.addProcessToPool(processThread);
        processPoolOperations.markProcessAsCompleted(processThread);

        boolean initialCompletionStatus = ProcessPoolProvider.getProcessPoolProvider().isProcessStarted(processThread.getProcessIdentifier());
        assertEquals(true, initialCompletionStatus);
        processPoolOperations.removeProcessFromPool(processThread);
        boolean finalCompletionStatus = ProcessPoolProvider.getProcessPoolProvider().isProcessStarted(processThread.getProcessIdentifier());
        assertEquals(false, finalCompletionStatus);
    }

    /**
     * Should destroy the underlying system process.
     */
    @Test
    public void testForceStopProcessShouldDestroyProcess() {
        processPoolOperations.addProcessToPool(processThread);
        processPoolOperations.markProcessAsCompleted(processThread);

        processPoolOperations.forceStopProcess(processThread);
        Mockito.verify(process, Mockito.atLeast(1)).destroyForcibly();
    }

    /**
     * Should remove the process from {@link ProcessPoolProvider#pool}
     */
    @Test
    public void testForceStopProcessShouldRemoveFromPool() {
        processPoolOperations.addProcessToPool(processThread);
        processPoolOperations.markProcessAsCompleted(processThread);

        int initialSize = processPoolOperations.getCurrentProcessPoolSize();
        processPoolOperations.forceStopProcess(processThread);
        int finalSize = processPoolOperations.getCurrentProcessPoolSize();
        assertEquals(initialSize-1, finalSize);
    }

    /**
     * Should remove the process from {@link ProcessPoolProvider#completedProcessIds}
     */
    @Test
    public void testForceStopProcessShouldRemoveFromCompletedIds() {
        processPoolOperations.addProcessToPool(processThread);
        processPoolOperations.markProcessAsCompleted(processThread);

        boolean initialCompletionStatus = ProcessPoolProvider.getProcessPoolProvider().isProcessStarted(processThread.getProcessIdentifier());
        assertEquals(true, initialCompletionStatus);
        processPoolOperations.forceStopProcess(processThread);
        boolean finalCompletionStatus = ProcessPoolProvider.getProcessPoolProvider().isProcessStarted(processThread.getProcessIdentifier());
        assertEquals(false, finalCompletionStatus);
    }

    @Test(expected = ProcessNotFoundInPoolException.class)
    public void testForceStopProcessShouldThrowException() {
        processPoolOperations.forceStopProcess(processThread);
    }


    /**
     * Should destroy the underlying system process.
     */
    @Test
    public void testForceStopProcess1ShouldDestroyProcess() {
        processPoolOperations.addProcessToPool(processThread);
        processPoolOperations.markProcessAsCompleted(processThread);

        processPoolOperations.forceStopProcess(processThread);
        Mockito.verify(process, Mockito.atLeast(1)).destroyForcibly();
    }

    /**
     * Should remove the process from {@link ProcessPoolProvider#pool}
     */
    @Test
    public void testForceStopProcess1ShouldRemoveFromPool() {
        processPoolOperations.addProcessToPool(processThread);
        processPoolOperations.markProcessAsCompleted(processThread);

        int initialSize = processPoolOperations.getCurrentProcessPoolSize();
        processPoolOperations.forceStopProcess(processThread);
        int finalSize = processPoolOperations.getCurrentProcessPoolSize();
        assertEquals(initialSize-1, finalSize);
    }

    /**
     * Should remove the process from {@link ProcessPoolProvider#completedProcessIds}
     */
    @Test
    public void testForceStopProcess1ShouldRemoveFromCompletedIds() {
        processPoolOperations.addProcessToPool(processThread);
        processPoolOperations.markProcessAsCompleted(processThread);

        boolean initialCompletionStatus = ProcessPoolProvider.getProcessPoolProvider().isProcessStarted(processThread.getProcessIdentifier());
        assertEquals(true, initialCompletionStatus);
        processPoolOperations.forceStopProcess(processThread);
        boolean finalCompletionStatus = ProcessPoolProvider.getProcessPoolProvider().isProcessStarted(processThread.getProcessIdentifier());
        assertEquals(false, finalCompletionStatus);
    }

    /**
     * Should throw {@link ProcessNotFoundInPoolException}
     */
    @Test(expected = ProcessNotFoundInPoolException.class)
    public void testForceStopProcess1ShouldThrowException() {
        processPoolOperations.forceStopProcess(processThread);
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
        processPoolOperations.addProcessToPool(processThread1);
        processPoolOperations.markProcessAsCompleted(processThread1);
        processPoolOperations.addProcessToPool(processThread2);
        processPoolOperations.markProcessAsCompleted(processThread2);
        processPoolOperations.addProcessToPool(processThread3);
        processPoolOperations.markProcessAsCompleted(processThread3);
        processPoolOperations.addProcessToPool(processThread4);
        processPoolOperations.markProcessAsCompleted(processThread4);
        processPoolOperations.addProcessToPool(processThread5);
        processPoolOperations.markProcessAsCompleted(processThread5);

        processPoolOperations.forceStopAllProcess();
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
        processPoolOperations.addProcessToPool(processThread1);
        processPoolOperations.markProcessAsCompleted(processThread1);
        processPoolOperations.addProcessToPool(processThread2);
        processPoolOperations.markProcessAsCompleted(processThread2);
        processPoolOperations.addProcessToPool(processThread3);
        processPoolOperations.markProcessAsCompleted(processThread3);
        processPoolOperations.addProcessToPool(processThread4);
        processPoolOperations.markProcessAsCompleted(processThread4);
        processPoolOperations.addProcessToPool(processThread5);
        processPoolOperations.markProcessAsCompleted(processThread5);

        processPoolOperations.forceStopAllProcess();
        assertEquals(0, processPoolOperations.getCurrentProcessPoolSize());
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
        processPoolOperations.addProcessToPool(processThread1);
        processPoolOperations.markProcessAsCompleted(processThread1);
        processPoolOperations.addProcessToPool(processThread2);
        processPoolOperations.markProcessAsCompleted(processThread2);
        processPoolOperations.addProcessToPool(processThread3);
        processPoolOperations.markProcessAsCompleted(processThread3);
        processPoolOperations.addProcessToPool(processThread4);
        processPoolOperations.markProcessAsCompleted(processThread4);
        processPoolOperations.addProcessToPool(processThread5);
        processPoolOperations.markProcessAsCompleted(processThread5);

        processPoolOperations.forceStopAllProcess();
        assertEquals(false, ProcessPoolProvider.getProcessPoolProvider().isProcessStarted("testProcessIdentifier1"));
        assertEquals(false, ProcessPoolProvider.getProcessPoolProvider().isProcessStarted("testProcessIdentifier2"));
        assertEquals(false, ProcessPoolProvider.getProcessPoolProvider().isProcessStarted("testProcessIdentifier3"));
        assertEquals(false, ProcessPoolProvider.getProcessPoolProvider().isProcessStarted("testProcessIdentifier4"));
        assertEquals(false, ProcessPoolProvider.getProcessPoolProvider().isProcessStarted("testProcessIdentifier5"));
    }

    /**
     * Should return {@code true}
     */
    @Test
    public void testIsProcessRunningShouldReturnTrue() {
        processPoolOperations.addProcessToPool(processThread);
        processPoolOperations.markProcessAsCompleted(processThread);

        boolean isRunning = processPoolOperations.isProcessRunning(processThread.getProcessIdentifier());
        assertEquals(true, isRunning);
    }

    /**
     * Should return {@code false}
     */
    @Test
    public void testIsProcessRunningShouldReturnFalse() {
        processPoolOperations.addProcessToPool(processThread);
        processPoolOperations.markProcessAsCompleted(processThread);

        boolean isRunning = processPoolOperations.isProcessRunning("InvalidProcessId");
        assertEquals(false, isRunning);
    }

    /**
     * Should return {@code false}
     */
    @Test
    public void testIsProcessRunningShouldReturnFalseWhenNotgingInPool() {
        boolean isRunning = processPoolOperations.isProcessRunning("InvalidProcessId");
        assertEquals(false, isRunning);
    }

    /**
     * Should return {@code true}
     */
    @Test
    public void testIsProcessFailedShouldReturnTrue() {
        processPoolOperations.addProcessToPool(processThread);
        processPoolOperations.markProcessAsFailed(processThread);

        boolean isFailed = processPoolOperations.isProcessFailed(processThread.getProcessIdentifier());
        assertEquals(true, isFailed);
    }

    /**
     * Should return {@code false}
     */
    @Test
    public void testIsProcessFailedShouldReturnFalse() {
        processPoolOperations.addProcessToPool(processThread);
        processPoolOperations.markProcessAsFailed(processThread);

        boolean isFailed = processPoolOperations.isProcessFailed("invalidProcessId");
        assertEquals(false, isFailed);
    }

    /**
     * Should return {@code false}
     */
    @Test
    public void testIsProcessFailedShouldReturnFalseWhenNotingInPool() {
        boolean isFailed = processPoolOperations.isProcessFailed("invalidProcessId");
        assertEquals(false, isFailed);
    }

    /**
     * Should return {@code true}
     */
    @Test
    public void testIsProcessCompletedShouldReturnTrue() {
        processPoolOperations.addProcessToPool(processThread);
        processPoolOperations.markProcessAsCompleted(processThread);

        boolean isStarted = processPoolOperations.isProcessStarted(processThread.getProcessIdentifier());
        assertEquals(true, isStarted);
    }

    /**
     * Should return {@code false}
     */
    @Test
    public void testIsProcessCompletedShouldReturnFalse() {
        processPoolOperations.addProcessToPool(processThread);
        processPoolOperations.markProcessAsCompleted(processThread);

        boolean isStarted = processPoolOperations.isProcessStarted("invalidProcessId");
        assertEquals(false, isStarted);
    }

    /**
     * Should return {@code false}
     */
    @Test
    public void testIsProcessCompletedShouldReturnFalseWhenNotingInPool() {
        boolean isStarted = processPoolOperations.isProcessStarted("invalidProcessId");
        assertEquals(false, isStarted);
    }

    /**
     * Should return [@code zero}
     */
    @Test
    public void testGetCurrentProcessPoolSizeForEmptyPool() {
         int size = processPoolOperations.getCurrentProcessPoolSize();
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
        processPoolOperations.addProcessToPool(processThread1);
        processPoolOperations.addProcessToPool(processThread2);
        processPoolOperations.addProcessToPool(processThread3);
        processPoolOperations.addProcessToPool(processThread4);
        processPoolOperations.addProcessToPool(processThread5);

        int size = processPoolOperations.getCurrentProcessPoolSize();
        assertEquals(5,size);
    }
}