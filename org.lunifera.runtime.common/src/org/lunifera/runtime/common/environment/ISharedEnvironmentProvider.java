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

/**
 * The {@link ISharedEnvironmentProvider} is responsible to create shared
 * environments, to dispose them and to provide active instances of them.
 */
public interface ISharedEnvironmentProvider {

	/**
	 * Returns the {@link ISharedEnvironment} for the given id. If no
	 * environment is available, a new instance will be created and registered
	 * as an OSGi service.
	 * 
	 * @param id
	 * @return
	 */
	ISharedEnvironment getEnvironment(String id);

	/**
	 * Disposes the given shared environment. It must not be used afterwards.
	 * 
	 * @param environment
	 */
	void disposeEnvironment(ISharedEnvironment environment);

}
