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

/**
 * Class to hold the various utility methods related to the JUnits execution
 * @author Deepak Shajan
 */
class JUnitUtils {

    /**
     * A Singleton instance of the canvas configuration stored for local use in this class only.
     */
    private static ConfigurationEntity configuration = null;

    /**
     * Checks if the passed colour is supported in the application
     * @param colour - colour to be checked
     * @return true if supported, else returns false
     */
    protected static boolean isSupportedColour(String colour) {

        for(Colour supportedColour : Colour.values())
            if(supportedColour.toString().equalsIgnoreCase(colour))
                return true;
        return false;
    }

    /**
     * Checks if the passed value falls inside the positive value limit range of the application.
     * <p>Overloaded method accepting integer type</p>
     * @param value - value to be checked, type integer
     * @return true is in range, else returns false
     */
    protected static boolean isInsidePositiveApplicationValueRange(int value) {

        return value > 0 && value < configuration.getHighValue();
    }

    /**
     * Checks if the passed value falls inside the positive value limit range of the application.
     * <p>Overloaded method accepting float type</p>
     * @param value - value to be checked, type float
     * @return true is in range, else returns false
     */
    protected static boolean isInsidePositiveApplicationValueRange(float value) {

        return value > 0 && value < configuration.getHighValue();
    }

    /**
     * Checks if the passed value is inside the valid percentage limit, which in this application is 0-100.
     * @param probablity
     * @return
     */
    protected static boolean isInsideValidPercentageValueRange(int probablity) {
        return (probablity>=0&&probablity<=100);
    }

    /**
     * Sets the static field {@link JUnitUtils#configuration}
     * @param configuration
     */
    @Autowired
    public void setConfiguration(ConfigurationEntity configuration) {
        JUnitUtils.configuration = configuration;
    }
}
