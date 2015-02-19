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
 * Different participants may share data.
 * <p>
 * For instance an {@link IDirtyState}. In a business application groups of
 * views may share the same data like beans. And other groups of views will
 * share their own data. A shared environment is an abstraction above this
 * group. Views may share the {@link ISharedEnvironment} and will also share
 * their properties and data.
 * </p>
 */
public interface ISharedEnvironment {

	/**
	 * Returns the unique id of the environment.
	 * 
	 * @return
	 */
	String getId();

	/**
	 * Set and key value pair to be shared in the environment.
	 * 
	 * @param key
	 * @param value
	 */
	void setData(Object key, Object value);

	/**
	 * Returns the value for the key or <code>null</code> if no value
	 * registered.
	 * 
	 * @param key
	 * @return
	 */
	Object getData(Object key);

	/**
	 * Returns all keys that are registered in the environment.
	 * 
	 * @return
	 */
	Object[] getDataKeys();

}
