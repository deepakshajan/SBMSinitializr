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

package com.initializr.utils;

import com.initializr.backbone.SBMSUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Deepak Shajan
 */
@Component
@Scope(value = "prototype")
public class ReflectionUtils implements SBMSUtils {

    public Method getMethodFromClass(Class<?> clazz, String methodName, Class<?> parameterType) {

        try {
            Method[] declaredMethods = clazz.getDeclaredMethods();
            for(Method declaredMethod : declaredMethods)
                if(methodName.equals(declaredMethod.getName())) {
                    Class<?> parameterTypeActual = getParameterType(declaredMethod);
                    Object o = parameterTypeActual.getDeclaredConstructor().newInstance();
                    if (parameterType.isInstance(o))
                        return declaredMethod;
                }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Class<?> getParameterType(Method method) {

        Class<?> parameterType = null;
        Class<?>[] parameterTypes = method.getParameterTypes();
        parameterType = parameterTypes[0];
        return parameterType;
    }

    public Class<?> getReturnType(Method method) {

        Class<?> returnType = null;
        returnType = method.getReturnType();
        return returnType;
    }

    public Set<String> getFullRequestMappingsForMethod(Class<?> clazz, Method method, Class<RequestMapping> annotationClass) {

        LinkedHashSet<String> annotationvalues = new LinkedHashSet<String>();
        RequestMapping requestMappingAnnotationForClass = clazz.getDeclaredAnnotation(annotationClass);
        String[] valuesForClass = requestMappingAnnotationForClass.value();
        for(String valueForClass : valuesForClass) {
            RequestMapping requestMappingAnnotationForMethod = method.getDeclaredAnnotation(annotationClass);
            String[] valuesForMethod = requestMappingAnnotationForMethod.value();
            for(String valueForMethod : valuesForMethod) {
                String finalAnotationValue = valueForClass + valueForMethod;
                annotationvalues.add(finalAnotationValue);
            }

        }
        return annotationvalues;
    }
}
