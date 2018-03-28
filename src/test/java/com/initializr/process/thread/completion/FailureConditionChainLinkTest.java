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

package com.initializr.process.thread.completion;

import com.initializr.service.request.StartProcessServiceRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link FailureConditionChainLink}
 * @author Deepak Shajan
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class FailureConditionChainLinkTest {

    @Mock
    EvaluatorRequest evaluatorRequest;

    @Mock
    StartProcessServiceRequest request;

    /**
     * Should return {@code true}
     */
    @Test
    public void testAppliesReturnTrue() {
        when(evaluatorRequest.getLogLine()).thenReturn("[INFO] BUILD FAILURE");

        boolean result = new FailureConditionChainLink().applies(evaluatorRequest);
        assertEquals(true, result);
    }

    /**
     * Should return {@code false}
     */
    @Test
    public void testAppliesReturnFalse() {
        when(evaluatorRequest.getLogLine()).thenReturn("[INFO] BUILD SUCCESS");

        boolean result = new FailureConditionChainLink().applies(evaluatorRequest);
        assertEquals(false, result);
    }

    /**
     * Should return {@code false}
     */
    @Test
    public void testLogic() {
        when(evaluatorRequest.getRequest()).thenReturn(request);
        when(request.getModuleName()).thenReturn("TestProcess");
        assertEquals(false, new FailureConditionChainLink().logic(evaluatorRequest));
    }
}