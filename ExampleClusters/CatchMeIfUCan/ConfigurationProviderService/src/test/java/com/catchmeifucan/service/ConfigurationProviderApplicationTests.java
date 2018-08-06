/**
 * MIT License
 Copyright (c) 2017 deepakshajan
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

package com.catchmeifucan.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

/**
 * Unit test cases for class {@link ConfigurationProviderApplication}
 * @author Deepak Shajan
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigurationProviderApplicationTests {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ConfigurationEntity configuration;

    private static final String ERROR = "Error in bean registration";

    @Test
	public void testIfAllBeansAreLoadedIntoContext() {

	    Assert.isTrue(null != restTemplate, ERROR);
	    Assert.isInstanceOf(RestTemplate.class, restTemplate, ERROR);
	    Assert.isTrue(null != configuration, ERROR);
	    Assert.isInstanceOf(ConfigurationEntity.class, configuration, ERROR);
	}

	@Test
	public void testRestTemplate() {

        Assert.isInstanceOf(RestTemplate.class, new ConfigurationProviderApplication().restTemplate(), ERROR);
        Assert.isTrue(null != new ConfigurationProviderApplication().restTemplate(), ERROR);
    }

    @Test
    public void testConfiguration() {

	    Assert.isInstanceOf(ConfigurationEntity.class, new ConfigurationProviderApplication().configuration(), ERROR);
	    Assert.isTrue(null != new ConfigurationProviderApplication().configuration(), ERROR);
    }
}
