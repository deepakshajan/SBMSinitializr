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
import java.util.List;


/**
 * @author Deepak Shajan
 * @param <ContentTypeT>
 */
class DTreeOptimizer<ContentTypeT> {

    DTree<ContentTypeT> dTree;

    List<DTreeNode<ContentTypeT>> checkedNodes = null;

    public DTreeOptimizer(DTree<ContentTypeT> dTree) {
        this.dTree = dTree;
    }

    public DTreeNode getOptimisedNode(DTreeNode nodeToBeFound) {

        DTreeNode<ContentTypeT> root = dTree.getRoot();
        checkedNodes = new ArrayList<>();
        return recursivelyMatchNodes(root.getChildren(), nodeToBeFound);
    }

    private DTreeNode recursivelyMatchNodes(List<DTreeNode<ContentTypeT>> childNodes, DTreeNode<ContentTypeT> nodeToBeFound) {

        DTreeNode<ContentTypeT> returnValue = nodeToBeFound;
        for(DTreeNode childNode : childNodes) {

            if(checkedNodes.contains(childNode))
                continue;

            boolean matchFound = new DTreeUtils().matchNode(childNode, nodeToBeFound);
            if(matchFound) {
                returnValue = childNode;
                break;
            }
            else {
                returnValue = recursivelyMatchNodes(childNode.getChildren(), nodeToBeFound);
                if(nodeToBeFound.hashCode() != returnValue.hashCode())
                    break;
            }

        }
        return returnValue;
    }

}
