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

import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.Assert;
import javax.servlet.http.HttpServletResponse;

/**
 * Unit test cases for class {@link ServiceImpl}
 * @author Deepak Shajan
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value=ServiceImpl.class)
public class ServiceImplTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetConfigurationsService() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/configuration").contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        final String ERROR = "Configuration provider service response is not as expected";

        Assert.isTrue(null != response, ERROR);
        Assert.isTrue(response.getStatus()== HttpServletResponse.SC_OK, ERROR);
        Assert.isTrue(MediaType.APPLICATION_JSON_UTF8_VALUE.equals(response.getContentType()), ERROR);

        ConfigurationEntity configuration = new Gson().fromJson(response.getContentAsString(), ConfigurationEntity.class);
        Assert.isTrue(null != configuration, ERROR);
    }

}