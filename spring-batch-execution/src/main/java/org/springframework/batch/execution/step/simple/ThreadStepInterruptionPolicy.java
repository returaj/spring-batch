/*
 * Copyright 2006-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.batch.execution.step.simple;

import org.springframework.batch.core.executor.StepInterruptedException;
import org.springframework.batch.repeat.RepeatContext;

/**
 * Policy that checks the current thread to see if it has been interrupted.
 * 
 * @author Lucas Ward
 * @author Dave Syer
 * 
 */
public class ThreadStepInterruptionPolicy implements StepInterruptionPolicy {

	/**
	 * Returns if the current job lifecycle has been interrupted by checking if
	 * the current thread is interrupted.
	 */
	public void checkInterrupted(RepeatContext context) throws StepInterruptedException {

		if (isInterrupted(context)) {
			throw new StepInterruptedException("Job interrupted status detected.");
		}
		
	}

	/**
	 * @param context the current context
	 * @return true if the job has been interrupted
	 */
	private boolean isInterrupted(RepeatContext context) {
		return Thread.currentThread().isInterrupted() || context.isTerminateOnly();
	}

}
