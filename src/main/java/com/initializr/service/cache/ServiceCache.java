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

import com.initializr.backbone.SBMSCache;
import com.initializr.backbone.SBMSServiceRequest;
import com.initializr.service.provider.RestControllerClassProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Deepak Shajan
 */
@Component
public class ServiceCache implements SBMSCache {

    private Map<String, ServiceCacheData> instance = new LinkedHashMap<>();

    Map<String, ServiceCacheData> getInstance() {
        return instance;
    }

    public Set<Class<?>> getAllServiceClasses() {

        Set<Class<?>> serviceClasses = new LinkedHashSet();
        instance.forEach((key, value) -> serviceClasses.add(value.getServiceClass()));
        return serviceClasses;
    }

    public Set<Method> getAllServiceMethods() {

        Set<Method> serviceMethods = new LinkedHashSet<>();
        instance.forEach((key, value) -> serviceMethods.add(value.getServiceMethod()));
        return serviceMethods;
    }

    public Set<String> getAllServiceEndPoints() {

        Set<String> serviceEndPoints = new LinkedHashSet<>();
        instance.forEach((key, value) -> serviceEndPoints.add(key));
        return serviceEndPoints;
    }

    public Method getServiceMethodForEndPoint(String endPoint) {

        Method serviceMethod = instance.get(endPoint).getServiceMethod();
        return serviceMethod;
    }

    public Class<? extends SBMSServiceRequest> getServiceRequestTypeForEndPoint(String endPoint) {

        Class<? extends SBMSServiceRequest> serviceRequestType = instance.get(endPoint).getServiceRequest();
        return serviceRequestType;
    }

}
