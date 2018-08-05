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

package com.initializr.process.pool;

import com.initializr.exception.ProcessNotFoundInPoolException;
import com.initializr.process.operations.ProcessPoolOperations;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link ProcessPoolProvider}
 * @author Deepak Shajan
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProcessPoolProviderTest {

    @Autowired
    ProcessPoolOperations processPoolOperations;

    @Mock
    ProcessThread processThread;

    @Mock
    Process process;

    @Before
    public void setUp() {
        when(processThread.getProcessIdentifier()).thenReturn("testProcessId");
        when(processThread.getProcess()).thenReturn(process);
    }

    @After
    public void teatDown() {
        processPoolOperations.forceStopAllProcess();
    }

    /**
     * Should add process to pool
     */
    @Test
    public void testAddProcessToPool() {
        int initialPoolSize = processPoolOperations.getCurrentProcessPoolSize();
        ProcessPoolProvider.getProcessPoolProvider().addProcessToPool(processThread);
        int finalPoolSize = processPoolOperations.getCurrentProcessPoolSize();
        assertEquals(initialPoolSize+1, finalPoolSize);
    }

    /**
     * Should throw {@link ProcessNotFoundInPoolException} as the process we try to mark as complete is not there in the {@link ProcessPoolProvider#pool}
     */
    @Test(expected = ProcessNotFoundInPoolException.class)
    public void testMarkProcessAsCompletedShouldThrowException() {
        ProcessPoolProvider.getProcessPoolProvider().markProcessAsCompleted(processThread);
    }

    /**
     * Should mark the porcess as completed.
     */
    @Test
    public void testMarkProcessAsCompletedShouldMarkCompletion() {
        boolean isStarted = ProcessPoolProvider.getProcessPoolProvider().isProcessStarted(processThread.getProcessIdentifier());
        assertEquals(false, isStarted);
        processPoolOperations.addProcessToPool(processThread);
        processPoolOperations.markProcessAsCompleted(processThread);

        isStarted = ProcessPoolProvider.getProcessPoolProvider().isProcessStarted(processThread.getProcessIdentifier());
        assertEquals(true, isStarted);
    }

    /**
     * Should throw {@link ProcessNotFoundInPoolException} as the process we try to mark as complete is not there in the {@link ProcessPoolProvider#pool}
     */
    @Test(expected = ProcessNotFoundInPoolException.class)
    public void testMarkProcessAsCompleted1ShouldThrowException() {
        ProcessPoolProvider.getProcessPoolProvider().markProcessAsCompleted(processThread.getProcessIdentifier());
    }

    /**
     * Should mark the porcess as completed.
     */
    @Test
    public void testMarkProcessAsCompleted1ShouldMarkCompletion() {
        boolean isStarted = ProcessPoolProvider.getProcessPoolProvider().isProcessStarted(processThread.getProcessIdentifier());
        assertEquals(false, isStarted);
        processPoolOperations.addProcessToPool(processThread);

        ProcessPoolProvider.getProcessPoolProvider().markProcessAsCompleted(processThread.getProcessIdentifier());
        isStarted = ProcessPoolProvider.getProcessPoolProvider().isProcessStarted(processThread.getProcessIdentifier());
        assertEquals(true, isStarted);
    }

    /**
     * Should throw {@link ProcessNotFoundInPoolException} as the process we try to mark as failed is not there in the {@link ProcessPoolProvider#pool}
     */
    @Test(expected = ProcessNotFoundInPoolException.class)
    public void testMarkProcessAsFailedShouldThrowException() {
        ProcessPoolProvider.getProcessPoolProvider().markProcessAsFailed(processThread);
    }

    /**
     * Should mark the process as failed.
     */
    @Test
    public void testMarkProcessAsFailedShouldMarkCompletion() {
        boolean isFailed = ProcessPoolProvider.getProcessPoolProvider().isProcessFailed(processThread.getProcessIdentifier());
        assertEquals(false, isFailed);
        processPoolOperations.addProcessToPool(processThread);
        processPoolOperations.markProcessAsFailed(processThread);

        isFailed = ProcessPoolProvider.getProcessPoolProvider().isProcessFailed(processThread.getProcessIdentifier());
        assertEquals(true, isFailed);
    }

    /**
     * Should throw {@link ProcessNotFoundInPoolException} as the process we try to mark as failed is not there in the {@link ProcessPoolProvider#pool}
     */
    @Test(expected = ProcessNotFoundInPoolException.class)
    public void testMarkProcessAsFailed1ShouldThrowException() {
        ProcessPoolProvider.getProcessPoolProvider().markProcessAsFailed(processThread.getProcessIdentifier());
    }

    /**
     * Should mark the porcess as completed.
     */
    @Test
    public void testMarkProcessAsFailed1ShouldMarkCompletion() {
        boolean isFailed = ProcessPoolProvider.getProcessPoolProvider().isProcessFailed(processThread.getProcessIdentifier());
        assertEquals(false, isFailed);
        processPoolOperations.addProcessToPool(processThread);

        ProcessPoolProvider.getProcessPoolProvider().markProcessAsFailed(processThread.getProcessIdentifier());
        isFailed = ProcessPoolProvider.getProcessPoolProvider().isProcessFailed(processThread.getProcessIdentifier());
        assertEquals(true, isFailed);
    }

    /**
     * Should return {@code null}
     */
    @Test
    public void testGetProcessFromPoolWhenPoolEmpty() {
        Process processFromPool = ProcessPoolProvider.getProcessPoolProvider().getProcessFromPool(processThread.getProcessIdentifier());
        assertEquals(null, processFromPool);
    }

    /**
     * Should return the process
     */
    @Test
    public void testGetProcessFromPoolWhenProcessPresent() {
        processPoolOperations.addProcessToPool(processThread);
        processPoolOperations.markProcessAsCompleted(processThread);

        Process processFromPool = ProcessPoolProvider.getProcessPoolProvider().getProcessFromPool(processThread.getProcessIdentifier());
        assertNotNull(processFromPool);
    }

    /**
     * Should return {@code null}
     */
    @Test
    public void testGetProcessFromPoolWhenProcessAbsent() {
        ProcessThread processThread1 = Mockito.mock(ProcessThread.class);
        when(processThread1.getProcessIdentifier()).thenReturn("dummyProcessId");
        when(processThread1.getProcess()).thenReturn(process);
        processPoolOperations.addProcessToPool(processThread1);

        Process processFromPool = ProcessPoolProvider.getProcessPoolProvider().getProcessFromPool(processThread.getProcessIdentifier());
        assertEquals(null, processFromPool);
    }

    /**
     * Should return {@code false}
     */
    @Test
    public void testGetProcessCompletionStatusWhenListEmpty() {
        boolean processCompletionStatus = ProcessPoolProvider.getProcessPoolProvider().isProcessStarted(processThread.getProcessIdentifier());
        assertEquals(false, processCompletionStatus);
    }

    /**
     * Should return {@code true}
     */
    @Test
    public void testGetProcessCompletionStatusWhenListHasProcessId() {
        processPoolOperations.addProcessToPool(processThread);
        processPoolOperations.markProcessAsCompleted(processThread);

        boolean processCompletionStatus = ProcessPoolProvider.getProcessPoolProvider().isProcessStarted(processThread.getProcessIdentifier());
        assertEquals(true, processCompletionStatus);
    }

    /**
     * Should return {@code false}
     */
    @Test
    public void testGetProcessCompletionStatusWhenListNotHasProcessId() {
        ProcessThread processThread1 = mock(ProcessThread.class);
        when(processThread1.getProcessIdentifier()).thenReturn("dummyProcessId");
        when(processThread1.getProcess()).thenReturn(process);
        processPoolOperations.addProcessToPool(processThread1);
        processPoolOperations.markProcessAsCompleted(processThread1);

        boolean processCompletionStatus = ProcessPoolProvider.getProcessPoolProvider().isProcessStarted(processThread.getProcessIdentifier());
        assertEquals(false, processCompletionStatus);
    }

    /**
     * Should return {@code false}
     */
    @Test
    public void testRemoveProcessFromPoolWhenPoolEmpty() {
        boolean result = ProcessPoolProvider.getProcessPoolProvider().removeProcessFromPool(processThread.getProcessIdentifier());
        assertEquals(false, result);
    }

    /**
     * Should remove process from {@link ProcessPoolProvider#pool} and return {@code true}.
     */
    @Test
    public void testRemoveProcessFromPoolWhenProcessNotCompleted() {
        processPoolOperations.addProcessToPool(processThread);
        int initialPoolSize = processPoolOperations.getCurrentProcessPoolSize();

        boolean result = ProcessPoolProvider.getProcessPoolProvider().removeProcessFromPool(processThread.getProcessIdentifier());
        int finalPoolSize = processPoolOperations.getCurrentProcessPoolSize();
        assertEquals(initialPoolSize-1,finalPoolSize);
        assertEquals(true, result);
    }

    /**
     * Should remove the process from {@link ProcessPoolProvider#completedProcessIds} and return true.
     */
    @Test
    public void testRemoveProcessFromPoolWhenProcessCompleted() {
        processPoolOperations.addProcessToPool(processThread);
        processPoolOperations.markProcessAsCompleted(processThread);

        boolean result = ProcessPoolProvider.getProcessPoolProvider().removeProcessFromPool(processThread.getProcessIdentifier());
        boolean isStarted = processPoolOperations.isProcessStarted(processThread.getProcessIdentifier());
        assertEquals(false,isStarted);
        assertEquals(true, result);
    }

    /**
     * Should remove process from {@link ProcessPoolProvider#pool} and return {@code true}.
     */
    @Test
    public void testRemoveProcessFromPoolWhenProcessNotFailed() {
        processPoolOperations.addProcessToPool(processThread);
        int initialPoolSize = processPoolOperations.getCurrentProcessPoolSize();

        boolean result = ProcessPoolProvider.getProcessPoolProvider().removeProcessFromPool(processThread.getProcessIdentifier());
        int finalPoolSize = processPoolOperations.getCurrentProcessPoolSize();
        assertEquals(initialPoolSize-1,finalPoolSize);
        assertEquals(true, result);
    }

    /**
     * Should remove the process from {@link ProcessPoolProvider#completedProcessIds} and return true.
     */
    @Test
    public void testRemoveProcessFromPoolWhenProcessIsFailed() {
        processPoolOperations.addProcessToPool(processThread);
        processPoolOperations.markProcessAsFailed(processThread);

        boolean result = ProcessPoolProvider.getProcessPoolProvider().removeProcessFromPool(processThread.getProcessIdentifier());
        boolean isFailed = processPoolOperations.isProcessFailed(processThread.getProcessIdentifier());
        assertEquals(false,isFailed);
        assertEquals(true, result);
    }

    /**
     * Should return {@code false}
     */
    @Test
    public void testRemoveProcessFromPoolWhenProcessNotFound() {
        ProcessThread processThread1 = mock(ProcessThread.class);
        when(processThread1.getProcessIdentifier()).thenReturn("dummyProcessId");
        when(processThread1.getProcess()).thenReturn(process);
        processPoolOperations.addProcessToPool(processThread1);
        processPoolOperations.markProcessAsCompleted(processThread1);
        int initialPoolSize = processPoolOperations.getCurrentProcessPoolSize();

        boolean result = ProcessPoolProvider.getProcessPoolProvider().removeProcessFromPool(processThread.getProcessIdentifier());
        int finalPoolSize = processPoolOperations.getCurrentProcessPoolSize();
        assertEquals(false, result);
        assertEquals(initialPoolSize, finalPoolSize);
    }

}