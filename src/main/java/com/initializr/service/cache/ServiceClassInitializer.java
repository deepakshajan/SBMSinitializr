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

import com.initializr.backbone.SBMSServiceRequest;
import com.initializr.backbone.SBMSServiceResponse;
import com.initializr.service.provider.RestControllerClassProvider;
import com.initializr.utils.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

/**
 * @author Deepak Shajan
 */
@Component
public class ServiceClassInitializer {

    @Autowired
    private ServiceCache serviceCache;

    @Autowired
    private RestControllerClassProvider restControllerClassProvider;

    @Autowired
    private ReflectionUtils reflectionUtils;

    @PostConstruct
    public void initaliseServiceCache() {

        try {
            Set<Class> restControllerClassSet = restControllerClassProvider.getRestControllerClassSet();
            for (Class restControllerClass : restControllerClassSet) {
                Method executeMethod = getExecuteMethod(restControllerClass);
                Set<String> requestMappingValues = getRequestMappingValues(restControllerClass, executeMethod, RequestMapping.class);
                Class<? extends SBMSServiceRequest> serviceRequestType = getServiceRequestType(executeMethod);
                Class<? extends SBMSServiceResponse> serviceResponseType = getServiceResponseType(executeMethod);
                fillServiceCache(restControllerClass, executeMethod, requestMappingValues, serviceRequestType, serviceResponseType);
            }

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private void fillServiceCache(Class restControllerClass, Method executeMethod, Set<String> requestMappingValues, Class<? extends SBMSServiceRequest> serviceRequestType, Class<? extends SBMSServiceResponse> serviceResponseType) {

        Map<String, ServiceCacheData> cacheInstance = serviceCache.getInstance();
        ServiceCacheData serviceCacheData = createServiceCacheData(restControllerClass, executeMethod, serviceRequestType, serviceResponseType);
        for(String requestMappingValue : requestMappingValues)
            if(cacheInstance.get(requestMappingValue) == null)
                cacheInstance.put(requestMappingValue, serviceCacheData);
    }

    private Method getExecuteMethod(Class restControllerClass) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        Method executeMethod = reflectionUtils.getMethodFromClass(restControllerClass, "execute", SBMSServiceRequest.class);
        return executeMethod;
    }

    private Class<? extends SBMSServiceRequest> getServiceRequestType(Method executeMethod) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Class<?> parameterType = reflectionUtils.getParameterType(executeMethod);
        return (Class<? extends SBMSServiceRequest>) parameterType;
    }

    private Set<String> getRequestMappingValues(Class restControllerClass, Method executeMethod, Class<RequestMapping> requestMappingClass) {

        Set<String> fullRequestMappingsForMethod = reflectionUtils.getFullRequestMappingsForMethod(restControllerClass, executeMethod, requestMappingClass);
        return fullRequestMappingsForMethod;
    }

    private Class<? extends SBMSServiceResponse> getServiceResponseType(Method executeMethod) {

        Class<?> serviceResponseType = reflectionUtils.getReturnType(executeMethod);
        return (Class<? extends SBMSServiceResponse>) serviceResponseType;
    }

    private ServiceCacheData createServiceCacheData(Class restControllerClass, Method executeMethod, Class<? extends SBMSServiceRequest> serviceRequestType, Class<? extends SBMSServiceResponse> serviceResponseType) {

        ServiceCacheData serviceCacheData = new ServiceCacheData();
        serviceCacheData.setServiceClass(restControllerClass);
        serviceCacheData.setServiceMethod(executeMethod);
        serviceCacheData.setServiceRequest(serviceRequestType);
        serviceCacheData.setServiceResponseType(serviceResponseType);
        return serviceCacheData;
    }

}
