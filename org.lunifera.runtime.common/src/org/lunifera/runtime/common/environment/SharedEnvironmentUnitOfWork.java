/**
 * Copyright (c) 2012 Committers of lunifera.org.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Florian Pirchner - initial API and implementation
 */
package org.lunifera.runtime.common.environment;

import java.util.HashMap;
import java.util.Map;

import org.lunifera.runtime.common.coordination.CoordinationUtil;
import org.osgi.service.coordinator.Coordination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class SharedEnvironmentUnitOfWork {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SharedEnvironmentUnitOfWork.class);

	public SharedEnvironmentUnitOfWork() {

	}

	/**
	 * Executes the unit of work creating an implicit {@link Coordination} and
	 * passing the env as variables.
	 * 
	 * @param env
	 */
	public void execute(final ISharedEnvironment env) {
		Coordination peek = null;
		try {
			Map<Class<?>, Object> props = new HashMap<Class<?>, Object>();
			peek = CoordinationUtil.createCurrentCoordination(props);
			if (peek != null) {
				doExecute();
			} else {
				LOGGER.error("No coordination service available!");
			}
		} finally {
			if (peek != null) {
				peek.end();
			}
		}
	}
	
	/**
	 * The unit of work to do.
	 */
	protected abstract void doExecute();

}
