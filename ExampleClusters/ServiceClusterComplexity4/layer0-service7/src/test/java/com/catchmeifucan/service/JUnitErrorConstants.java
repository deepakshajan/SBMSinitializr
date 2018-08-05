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

/**
 * Class holds the String constants for various test case failure cases
 * @author Deepak Shajan
 */
public final class JUnitErrorConstants {

    /**
     * The value of the configuration attribute was null.
     */
    static final String NULL = "Null value is not expected for this attribute";

    /**
     * The data type of the configuration is not as expected.
     * <p>For example, for a configuration that expects and integer value, you cannot provide
     * a float value</p>
     */
    static final String TYPE = "The type of the attribute is not as expected";

    /**
     * The value of the configuration attribute is not as expected.
     * <p>For example, if the value expected is a percentage, then the value must be within 0 and 100</p>
     */
    static final String VALUE = "The value of the attribute is invalid";

    /**
     * The value of the configuration attribute is dependant on some other configuration.
     * <p>For example, If the game piece has colour red, then the opponent cannot have colour red.</p>
     */
    static final String DEPENDANCY = "The attribute value is dependent on some other value";
}
