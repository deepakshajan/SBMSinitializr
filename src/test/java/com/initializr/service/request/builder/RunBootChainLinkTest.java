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

package com.initializr.service.request.builder;

import com.initializr.exception.UnSupportedBuildTypeException;
import com.initializr.service.request.StartProcessServiceRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link RunBootChainLink}
 * @author Deepak Shajan
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class RunBootChainLinkTest {

    @Mock
    StartProcessServiceRequest request;

    /**
     * Should return {@code true}
     */
    @Test
    public void testAppliesReturnsTrue() {
        when(request.isRunBoot()).thenReturn(true);
        boolean result = new RunBootChainLink<>().applies(request);
        assertEquals(true, result);
    }

    /**
     * Should return {@code false}
     */
    @Test
    public void testAppliesReturnsFalse() {
        when(request.isRunBoot()).thenReturn(false);
        boolean result = new RunBootChainLink<>().applies(request);
        assertEquals(false, result);
    }

    /**
     * Should return the build command for maven
     */
    @Test
    public void testLogicWorksForMaven() {
        when(request.getBuildType()).thenReturn("maven");
        String command = new RunBootChainLink<>().logic(request);
        assertEquals("spring-boot:run", command);
    }

    /**
     * Should return the build command for gradle
     */
    @Test
    public void testLogicWorksForGradle() {
        when(request.getBuildType()).thenReturn("gradle");
        String command = new RunBootChainLink<>().logic(request);
        assertEquals("bootRun", command);
    }

    /**
     * Should throw {@link com.initializr.exception.UnSupportedBuildTypeException}
     */
    @Test(expected = UnSupportedBuildTypeException.class)
    public void testLogicThrowsExceptionForOtherBuildType() {
        when(request.getBuildType()).thenReturn("OtherBuildType");
        new RunBootChainLink<>().logic(request);
    }
}