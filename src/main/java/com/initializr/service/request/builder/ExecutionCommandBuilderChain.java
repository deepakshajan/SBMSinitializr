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

package com.initializr.service.request.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Holds the chain instance in the form of an iterator.
 *
 * <p>The {@link ExecutionCommandBuilderChain#iterator()} variable holds an iterator instance of all the chain links. This implementation is done to make iteration over the chain easy.
 * Because of this implementation we can simply create a new instance of this class to be used in a for each loop.</p>
 * <p>For Example :
 * <code>
 *     for(ExecutionCommandBuilderChainLink chainList : new ExecutionCommandBuilderChain()) {
 *         //logic goes here
 *     }
 * </code></p>
 * @author Deepak Shajan
 */
public final class ExecutionCommandBuilderChain extends ArrayList<ExecutionCommandBuilderChainLink> {

    /**
     * Create a {@link java.util.List} consisting of all the chain links in order and store its iterator as instance variable.
     */
    private final Iterator<ExecutionCommandBuilderChainLink> iterator = Arrays.asList(new ExecutionCommandBuilderChainLink[]{
            new BuildTypeChainLink(),
            new RunCleanChainLink(),
            new RunTestChainLink(),
            new RunBootChainLink()}).iterator();


    /**
     * @return iterator instance of all the chain links.
     */
    @Override
    public Iterator<ExecutionCommandBuilderChainLink> iterator() {
        return iterator;
    }
}
