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

package com.initializr;

import com.initializr.config.Configuration;
import com.initializr.constants.TaskConstant;
import com.initializr.startup.ApplicationStartUpOperationsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


/**
 * @author Deepak Shajan
 */
@SpringBootApplication
public class SbmSinitializrApplication {

	@Autowired
	private ApplicationStartUpOperationsBean applicationStartUpOperationsBean;

	public static void main(String[] args) {
		SpringApplication.run(SbmSinitializrApplication.class, args);
	}

	/**
	 * Executed when application is starting up. Registers a shutdown hook to clean up all all started process.
	 */
	@EventListener(ApplicationReadyEvent.class)
	public void init() {
		applicationStartUpOperationsBean.registerShutDownHook();
	}

	@Bean(name = "processTaskExecutor")
	public ThreadPoolTaskExecutor processTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(Configuration.getConfiguration().getCoreThreadCount());
		executor.setMaxPoolSize(Configuration.getConfiguration().getMaxThreadCount());
		executor.setQueueCapacity(Configuration.getConfiguration().getThreadQueueCapacity());
		executor.setWaitForTasksToCompleteOnShutdown(false);
		executor.setThreadNamePrefix(TaskConstant.PROCESS_TASK_EXECUTER_NAME);
		return executor;
	}

	@Bean(name = "monitorTaskExecutor")
	public ThreadPoolTaskExecutor monitorTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(Configuration.getConfiguration().getCoreThreadCount());
		executor.setMaxPoolSize(Configuration.getConfiguration().getMaxThreadCount());
		executor.setQueueCapacity(Configuration.getConfiguration().getThreadQueueCapacity());
		executor.setWaitForTasksToCompleteOnShutdown(false);
		executor.setThreadNamePrefix(TaskConstant.MONITOR_TASK_EXECUTER_NAME);
		return executor;
	}

	@Bean(name = "smartProcessTaskExecutor")
	public ThreadPoolTaskExecutor smartProcessTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(Configuration.getConfiguration().getCoreThreadCount());
		executor.setMaxPoolSize(Configuration.getConfiguration().getMaxThreadCount());
		executor.setQueueCapacity(Configuration.getConfiguration().getThreadQueueCapacity());
		executor.setWaitForTasksToCompleteOnShutdown(false);
		executor.setThreadNamePrefix(TaskConstant.SMART_PROCESS_TASK_EXECUTER_NAME);
		return executor;
	}


}
