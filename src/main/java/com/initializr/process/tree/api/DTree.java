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

import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * @author Deepak Shajan
 * @param <ContentTypeT>
 */
public class DTree<ContentTypeT> {

    private DTreeNode<ContentTypeT> root;

    public DTree(ContentTypeT root) {
        this.root =  (new DTreeNode(root)).new DTreeNodeRoot(root);
    }

    private DTreeOptimizer getProcessTreeOptimiser() {
        return new DTreeOptimizer(this);
    }

    public boolean addChildToRoot(ContentTypeT childValue) {

        DTreeNode<ContentTypeT> childNode = new DTreeNode<>(childValue);
        DTreeNode optimisedChild = getProcessTreeOptimiser().getOptimisedNode(childNode);
        return root.addChild(optimisedChild);
    }

    public boolean addChildToParent(ContentTypeT parentValue, ContentTypeT childValue) {

        DTreeNode<ContentTypeT> childNode = new DTreeNode<>(childValue);
        DTreeNode<ContentTypeT> parentNode = new DTreeNode<>(parentValue);
        return addChildToParent(parentNode, childNode);
    }

    public Iterator<ContentTypeT> iterator() {

        Set<ContentTypeT> set = new LinkedHashSet<>();
        recursivlyAddChildrenToListForIterator(root, set);
        return set.iterator();
    }

    private boolean addChildToParent(DTreeNode parentNode, DTreeNode childNode) {

        DTreeNode optimisedParent = getProcessTreeOptimiser().getOptimisedNode(parentNode);
        DTreeNode optimisedChild = getProcessTreeOptimiser().getOptimisedNode(childNode);
        boolean added = optimisedParent.addChild(optimisedChild);
        return added;
    }

    private void recursivlyAddChildrenToListForIterator(@NotNull DTreeNode<ContentTypeT> dTreeNode, @NotNull Set<ContentTypeT> set) {

        if(null != dTreeNode) {
            for (DTreeNode<ContentTypeT> node : dTreeNode.getChildren()) {
                set.add(node.getValue());
                recursivlyAddChildrenToListForIterator(node, set);
            }
        }
    }

    public DTreeNode getRoot() {
        return root;
    }

}
