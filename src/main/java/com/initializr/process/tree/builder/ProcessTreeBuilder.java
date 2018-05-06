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

package com.initializr.process.tree.builder;

import com.initializr.constants.ProcessTreeConstant;
import com.initializr.process.tree.api.DTree;
import com.initializr.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * @author Deepak Shajan
 */
public final class ProcessTreeBuilder {

    DTree<String> processTree;

    File clusterPathFile;

    public Optional<DTree> buildTreeFromFileSystem(String clusterPath) throws IOException {

        List<File> files = new FileUtils().getFilesAsList(clusterPath);
        initialiseProcessTreeBuilder(clusterPath);
        for (File file : files) {
            processTree.addChildToRoot(file.getName());
            addChildNodesToTree(file);
        }
        return Optional.ofNullable(processTree);
    }

    private void initialiseProcessTreeBuilder(String clusterPath) {
        processTree = new DTree<>(ProcessTreeConstant.ROOT);
        clusterPathFile = new FileUtils().getFile(clusterPath);
    }

    private void addChildNodesToTree(File file) throws IOException {

        File depsFile = new FileUtils().getDepsFile(file).orElseGet(()->null);
        if(depsFile != null) {
            List<String> dependancies = new FileUtils().parseDepsFile(depsFile);
            for (String dependancy : dependancies) {
                processTree.addChildToParent(file.getName(), dependancy);
            }

        }
    }

}
