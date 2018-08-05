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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;


/**
 * Contains the service methods that are exposed as RESTFull webservice
 * @author Deepak Shajan
 */
@RestController
@RequestMapping("/")
public class ServiceImpl {

    @Autowired
    private ConfigurationEntity configuration;

    /**
     * Loads all the configurations from the files, maps them to the {@link ResponseEntity}.
     * <p> The response object is ultimately returned as a JSON string. User the URL <code>{contextPath}/config</code> to hit the service,
     * where <code>{contextPath}</code> denotes the application context path<code>(eg - https://localhost:8080)</code>
     * @return set of configurations
     */
    @RequestMapping(value = "/configuration", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConfigurationEntity> getConfigurations() {
        return new ResponseEntity<>(configuration, HttpStatus.OK);
    }
}
