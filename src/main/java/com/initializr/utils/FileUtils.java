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

import com.initializr.constants.ProcessTreeConstants;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author Deepak Shajan
 */
@Component
public class FileUtils {

    public File getFile(String path) {

        File file = new File(path);
        if(file.exists())
            return file;
        return  null;
    }

    public List<File> getFilesAsList(String path) {
        File parentFile = getFile(path);
        return Arrays.asList( parentFile.listFiles());
    }

    public Optional<File> getDepsFile(File file) {

        for(File fileIter : file.listFiles())
            if(ProcessTreeConstants.DEPS_FILE.equals(fileIter.getName()))
                return Optional.ofNullable(fileIter);
        return Optional.empty();
    }

    public List<String> parseFile(File file) throws IOException {

        String line;
        List<String> dependencies = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        while((line=br.readLine())!=null)
            if(!"".equals(line= line.trim()))
                dependencies.add(line);
        return dependencies;
    }
}
