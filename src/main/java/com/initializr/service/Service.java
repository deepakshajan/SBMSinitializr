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

package com.initializr.service;

/**
 * Interface common for all rest services in the project.
 *
 * <p>Operates on a request to produce a response. Both request and response are generalized.</p>
 *
 * @author Deepak Shajan
 * @param <RequestTypeT> : The type of the request which the service consumes.
 * @param <ResponseTypeT> : The type of the response that the service produce.
 */
interface Service<RequestTypeT, ResponseTypeT> {

    /**
     * Endpoint method that is responsible to receive the request.
     * @param request : The request object
     * @return response is returned if no exceptions were thrown.
     * @throws Exception : Any exception being thrown from within any service is intended to be thrown to the caller.
     */
    ResponseTypeT execute(RequestTypeT request) throws Exception;
}
