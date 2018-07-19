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

package com.initializr.process;

import com.initializr.config.Configuration;
import com.initializr.process.thread.SmartProcessThread;
import com.initializr.process.tree.ProcessTreeProvider;
import com.initializr.process.tree.api.DTree;
import com.initializr.service.request.DeployServiceClusterServiceRequest;
import com.initializr.service.request.StartProcessServiceRequest;
import com.initializr.service.request.adapter.DeployServiceClusterServiceRequestAdapter;
import com.initializr.utils.ThreadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Iterator;

/**
 * @author Deepak Shajan
 */
@Component
public class ProcessTreeDeployer {

    @Autowired
    private ProcessTreeProvider processTreeProvider;

    @Autowired
    private ThreadPoolTaskExecutor smartProcessTaskExecutor;

    @Autowired
    private SmartProcessThread smartProcessThread;

    @Autowired
    private DeployServiceClusterServiceRequestAdapter deployServiceClusterServiceRequestAdapter;

    @Autowired
    private ThreadUtils threadUtils;

    public void iterateOverTreeAndStartProcessInOrder(DeployServiceClusterServiceRequest request) throws IOException {

        DTree<String> dTree = processTreeProvider.getProcessTree(request.getClusterPath());
        Iterator<String> dTreeIterator = dTree.iterator();
        while(dTreeIterator.hasNext()) {
            String moduleName = dTreeIterator.next();
            StartProcessServiceRequest startProcessServiceRequest = deployServiceClusterServiceRequestAdapter.convertToStartProcessServiceRequestImpl(request, moduleName);
            SmartProcessThread cloneSmartProcessThread = smartProcessThread.clone();
            cloneSmartProcessThread.setRequestToThread(startProcessServiceRequest);
            threadUtils.startThread(smartProcessTaskExecutor, cloneSmartProcessThread);
            threadUtils.sleep(Configuration.getConfiguration().getProcessStartTimeGap());
        }
    }

}
