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

package com.initializr.socket.adapter;

import com.google.gson.Gson;
import com.initializr.backbone.SBMSAdapter;
import com.initializr.backbone.SBMSServiceRequest;
import com.initializr.backbone.SBMSWebSocketRequest;
import com.initializr.service.cache.ServiceCache;
import com.initializr.service.invoke.RestServiceInvoker;
import com.initializr.service.provider.RestControllerClassProvider;
import com.initializr.utils.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author Deepak Shajan
 */
@Component
@Scope(value = "prototype")
public class WebSocketRequestAdapter implements SBMSAdapter {

    @Autowired
    private RestControllerClassProvider restControllerClassProvider;

    @Autowired
    private ReflectionUtils reflectionUtils;

    @Autowired
    private RestServiceInvoker restServiceInvoker;

    @Autowired
    private ServiceCache serviceCache;

    public SBMSServiceRequest convertToServiceRequest(SBMSWebSocketRequest webSocketRequest) {

        SBMSServiceRequest serviceRequest = null;
        String endPoint = webSocketRequest.getEndPoint();
        Class<? extends SBMSServiceRequest> serviceRequestClass = serviceCache.getServiceRequestTypeForEndPoint(endPoint);
        String webSocketRequestJson = new Gson().toJson(webSocketRequest);
        serviceRequest = new Gson().fromJson(webSocketRequestJson, serviceRequestClass);
        return  serviceRequest;
    }
}
