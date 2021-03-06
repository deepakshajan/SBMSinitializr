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

package com.initializr.backbone;

import com.initializr.exception.InvalidServiceRequestException;

import java.io.Serializable;

/**
 * Interface to be extended by all requests incoming to any service in the application.
 * @author Deepak Shajan
 */
public interface SBMSServiceRequest extends Serializable, Cloneable{


    /**
     * Default method throws {@link InvalidServiceRequestException} if the {@link SBMSServiceRequest#filterInvalidRequest()} returns true.
     */
    default void filter(){
        if(filterInvalidRequest())
            throw new InvalidServiceRequestException();
    }

    /**
     * Implementations should provide the logic for all invalid requests that the rest services must decline.
     *
     * <p>This method enables the {@link SBMSServiceRequest} implementations to filter out any unwanted invocations.</p>
     */
    boolean filterInvalidRequest();
}
