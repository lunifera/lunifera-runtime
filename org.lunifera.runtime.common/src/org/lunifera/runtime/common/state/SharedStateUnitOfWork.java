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
package org.lunifera.runtime.common.state;

import java.util.HashMap;
import java.util.Map;

import org.lunifera.runtime.common.coordination.CoordinationManager;
import org.osgi.service.coordinator.Coordination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class SharedStateUnitOfWork<R> {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SharedStateUnitOfWork.class);

	public SharedStateUnitOfWork() {

	}

	/**
	 * Executes the unit of work creating an implicit {@link Coordination} and
	 * passing the env as variables.
	 * 
	 * @param context
	 * @return the data returned by {@link #doExecute()}
	 */
	public R execute(final ISharedStateContext context) {
		if (context != null) {
			Coordination peek = null;
			CoordinationManager coordinationManager = new CoordinationManager();
			try {
				Map<Class<?>, Object> props = new HashMap<Class<?>, Object>();
				props.put(ISharedStateContext.class, context);
				peek = coordinationManager.createCurrentCoordination(props);
				if (peek != null) {
					return doExecute();
				} else {
					LOGGER.error("No coordination service available!");
				}
			} finally {
				if (peek != null) {
					peek.end();
				}
				// release the coordination manager
				coordinationManager.release();
			}
		} else {
			return doExecute();
		}
		return null;
	}

	/**
	 * The unit of work to do.
	 * 
	 * @return any kind of data. May also be <code>null</code>.
	 */
	protected abstract R doExecute();

}
