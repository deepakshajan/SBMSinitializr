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

package com.initializr.service.request;

import com.initializr.exception.InvalidServiceRequestException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

/**
 * Test class for {@link StartProcessServiceRequestImpl}
 * @author Deepak Shajan
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class StartProcessServiceRequestImplTest {

    StartProcessServiceRequestImpl request = new StartProcessServiceRequestImpl();

    @Before
    public void setUp() {
        this.request = new StartProcessServiceRequestImpl();
    }

    /**
     * Should return {@code mvn clean test spring-boot:run}
     */
    @Test
    public void testGetExecutableCommandForFullCommandForMaven() {
        request.setBuildType("maven");
        request.setRunClean(true);
        request.setRunTests(true);
        request.setRunBoot(true);

        String command = request.getExecutableCommand();
        assertEquals("mvn clean test spring-boot:run", command);
    }

    /**
     * Should throw {@link InvalidServiceRequestException}
     */
    @Test(expected = InvalidServiceRequestException.class)
    public void testGetExecutableCommandForBuildCommandOnlyForMaven() {
        request.setBuildType("maven");
        request.getExecutableCommand();
    }

    /**
     *Should return {@code mvn clean}
     */
    @Test
    public void testGetExecutableCommandForCleanCommandForMaven() {
        request.setBuildType("maven");
        request.setRunClean(true);

        String command = request.getExecutableCommand();
        assertEquals("mvn clean", command);
    }

    /**
     * Should return {@code mvn test}
     */
    @Test
    public void testGetExecutableCommandForTestCommandOnlyForMaven() {
        request.setBuildType("maven");
        request.setRunTests(true);

        String command = request.getExecutableCommand();
        assertEquals("mvn test", command);
    }

    /**
     * Should return mvn spring-boot:run
     */
    @Test
    public void testGetExecutableCommandForBootOnlyForMaven() {
        request.setBuildType("maven");
        request.setRunBoot(true);

        String command = request.getExecutableCommand();
        assertEquals("mvn spring-boot:run", command);
    }

    /**
     * Should return {@code mvn clean test}
     */
    @Test
    public void testGetExecutableCommandForCleanAndTestForMaven() {
        request.setBuildType("maven");
        request.setRunClean(true);
        request.setRunTests(true);

        String command = request.getExecutableCommand();
        assertEquals("mvn clean test", command);
    }

    /**
     * Should return {@code mvn clean spring-boot:run}
     */
    @Test
    public void testGetExecutableCommandForCleanAndBootForMaven() {
        request.setBuildType("maven");
        request.setRunClean(true);
        request.setRunBoot(true);

        String command = request.getExecutableCommand();
        assertEquals("mvn clean spring-boot:run", command);
    }

    /**
     * SHould return {@code mvn test spring-boot:run}
     */
    @Test
    public void testGetExecutableCommandForTestAndBootForMaven() {
        request.setBuildType("maven");
        request.setRunTests(true);
        request.setRunBoot(true);

        String command = request.getExecutableCommand();
        assertEquals("mvn test spring-boot:run", command);
    }


    /**
     * Should return {@code gradle clean test bootRun}
     */
    @Test
    public void testGetExecutableCommandForFullCommandForGradle() {
        request.setBuildType("gradle");
        request.setRunClean(true);
        request.setRunTests(true);
        request.setRunBoot(true);

        String command = request.getExecutableCommand();
        assertEquals("gradle clean test bootRun", command);
    }

    /**
     * Should throw {@link InvalidServiceRequestException}
     */
    @Test(expected = InvalidServiceRequestException.class)
    public void testGetExecutableCommandForBuildCommandOnlyForGradle() {
        request.setBuildType("gradle");
        request.getExecutableCommand();
    }

    /**
     * Should return {@code gradle clean}
     */
    @Test
    public void testGetExecutableCommandForCleanCommandForGradle() {
        request.setBuildType("gradle");
        request.setRunClean(true);

        String command = request.getExecutableCommand();
        assertEquals("gradle clean", command);
    }

    /**
     * Should return {@code gradle test}
     */
    @Test
    public void testGetExecutableCommandForTestCommandOnlyForGradle() {
        request.setBuildType("gradle");
        request.setRunTests(true);

        String command = request.getExecutableCommand();
        assertEquals("gradle test", command);
    }

    /**
     * Should return {@code gradle bootRun}
     */
    @Test
    public void testGetExecutableCommandForBootOnlyForGradle() {
        request.setBuildType("gradle");
        request.setRunBoot(true);

        String command = request.getExecutableCommand();
        assertEquals("gradle bootRun", command);
    }

    /**
     * Should return {@code gradle clean test}
     */
    @Test
    public void testGetExecutableCommandForCleanAndTestForGradle() {
        request.setBuildType("gradle");
        request.setRunClean(true);
        request.setRunTests(true);

        String command = request.getExecutableCommand();
        assertEquals("gradle clean test", command);
    }

    /**
     * Should return {@code gradle clean bootRun}
     */
    @Test
    public void testGetExecutableCommandForCleanAndBootForGradle() {
        request.setBuildType("gradle");
        request.setRunClean(true);
        request.setRunBoot(true);

        String command = request.getExecutableCommand();
        assertEquals("gradle clean bootRun", command);
    }

    /**
     * Should return {@code gradle test bootRun}
     */
    @Test
    public void testGetExecutableCommandForTestAndBootForGradle() {
        request.setBuildType("gradle");
        request.setRunTests(true);
        request.setRunBoot(true);

        String command = request.getExecutableCommand();
        assertEquals("gradle test bootRun", command);
    }


}