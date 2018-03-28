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

import java.io.BufferedReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link SuccessConditionChainLink}
 * @author Deepak Shajan
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SuccessConditionChainLinkTest {

    @Mock
    EvaluatorRequest evaluatorRequest;

    @Mock
    StartProcessServiceRequest request;

    @Mock
    BufferedReader bufferedReader;

    /**
     * Should return {@code true}
     */
    @Test
    public void testAppliesReturnsTrue() throws IOException {
        when(evaluatorRequest.getLogLine()).thenReturn("2018-03-25 14:38:50.105  INFO 16196" +
                " --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : " +
                "Tomcat started on port(s): 5002 (http)");
        when(evaluatorRequest.getBufferedReader()).thenReturn(bufferedReader);
        when(bufferedReader.readLine()).thenReturn("2018-03-25 14:38:50.120  INFO 16196 --- " +
                "[           main] c.c.s.ConfigurationProviderApplication   : Started ConfigurationProviderApplication in " +
                "3.794 seconds (JVM running for 8.766)");

        boolean result = new SuccessConditionChainLink().applies(evaluatorRequest);
        assertEquals(true, result);
    }

    /**
     * Should return {@code false}
     */
    @Test
    public void testAppliesReturnsFalse() throws IOException {
        when(evaluatorRequest.getLogLine()).thenReturn("2018-03-25 14:38:50.105  INFO 16196" +
                " --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : " +
                "Tomcat not started on port(s): 5002 (http)");
        when(evaluatorRequest.getBufferedReader()).thenReturn(bufferedReader);
        when(bufferedReader.readLine()).thenReturn("2018-03-25 14:38:50.120  INFO 16196 --- " +
                "[           main] c.c.s.ConfigurationProviderApplication   : Did not Start ConfigurationProviderApplication in " +
                "3.794 seconds (JVM running for 8.766)");

        boolean result = new SuccessConditionChainLink().applies(evaluatorRequest);
        assertEquals(false, result);
    }

    /**
     * Should return {@code false}
     */
    @Test(expected = IOException.class)
    public void testAppliesThrowsException() throws IOException {
        when(evaluatorRequest.getLogLine()).thenReturn("2018-03-25 14:38:50.105  INFO 16196" +
                " --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : " +
                "Tomcat started on port(s): 5002 (http)");
        when(evaluatorRequest.getBufferedReader()).thenReturn(bufferedReader);
        when(bufferedReader.readLine()).thenThrow(IOException.class);

        new SuccessConditionChainLink().applies(evaluatorRequest);
    }

    /**
     * Should return {@code true}
     */
    @Test
    public void testLogic() {
        when(evaluatorRequest.getRequest()).thenReturn(request);
        when(request.getModuleName()).thenReturn("TestProcess");

        assertEquals(true, new SuccessConditionChainLink().logic(evaluatorRequest));
    }
}