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


package com.initializr.process.tree.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Deepak Shajan
 * @param <ContentTypeT>
 */
public class DTreeNode<ContentTypeT> {

    private ContentTypeT value = null;

    private List<DTreeNode<ContentTypeT>> children = new ArrayList<>();

    public DTreeNode(ContentTypeT value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null ? obj.equals(value) : false;
    }

    public boolean addChild(DTreeNode child) {
        return children.add(child);
    }

    public ContentTypeT getValue() {
        return value;
    }

    public void setValue(ContentTypeT value) {
        this.value = value;
    }

    public List<DTreeNode<ContentTypeT>> getChildren() {
        return children != null ? children : Collections.emptyList();
    }

    class DTreeNodeRoot<ContentTypeT> extends DTreeNode<ContentTypeT> {

        public DTreeNodeRoot(ContentTypeT value) {
            super(value);
        }
    }

}
