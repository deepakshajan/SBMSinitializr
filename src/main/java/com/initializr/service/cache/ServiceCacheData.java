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

package com.initializr.service.cache;

import com.initializr.backbone.SBMSService;
import com.initializr.backbone.SBMSServiceRequest;
import com.initializr.backbone.SBMSServiceResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author Deepak Shajan
 */
@Component
@Scope(value = "prototype")
public class ServiceCacheData {

    private Class<? extends SBMSService> serviceClass;

    private Method serviceMethod;

    private Class<? extends SBMSServiceRequest> serviceRequest;

    private Class<? extends SBMSServiceResponse> serviceResponseType;

    public Class<? extends SBMSService> getServiceClass() {
        return serviceClass;
    }

    public void setServiceClass(Class<? extends SBMSService> serviceClass) {
        this.serviceClass = serviceClass;
    }

    public Method getServiceMethod() {
        return serviceMethod;
    }

    public void setServiceMethod(Method serviceMethod) {
        this.serviceMethod = serviceMethod;
    }

    public Class<? extends SBMSServiceRequest> getServiceRequest() {
        return serviceRequest;
    }

    public void setServiceRequest(Class<? extends SBMSServiceRequest> serviceRequest) {
        this.serviceRequest = serviceRequest;
    }

    public Class<? extends SBMSServiceResponse> getServiceResponseType() {
        return serviceResponseType;
    }

    public void setServiceResponseType(Class<? extends SBMSServiceResponse> serviceResponseType) {
        this.serviceResponseType = serviceResponseType;
    }
}
