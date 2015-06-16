/**
 * Copyright (c) 2011 - 2015, Lunifera GmbH (Gross Enzersdorf), Loetz KG (Heidelberg)
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *         Florian Pirchner - Initial implementation
 */
package org.lunifera.runtime.common.state;

import org.lunifera.runtime.common.hash.HashUtil;

/**
 * Different participants may share data.
 * <p>
 * For instance an {@link IDataState}. In a business application groups of views
 * may share the same data like beans. And other groups of views will share
 * their own data. A shared environment is an abstraction above this group.
 * Views may share the {@link ISharedStateContext} and will also share their
 * properties and data.
 * </p>
 */
public interface ISharedStateContext {

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
	void setProperty(Object key, Object value);

	/**
	 * Returns the value for the key or <code>null</code> if no value
	 * registered.
	 * 
	 * @param key
	 * @return
	 */
	Object getProperty(Object key);

	/**
	 * Returns all keys that are registered in the environment.
	 * 
	 * @return
	 */
	Object[] getPropertyKeys();

	/**
	 * Returns the dirty state that contains all dirty objects for the shared
	 * state.
	 * 
	 * @return
	 */
	IDataState getDirtyState();

	/**
	 * Returns the "used state" that contains all objects that are currently
	 * used for the shared state.
	 * 
	 * @return
	 */
	IDataState getGlobalDataState();

	/**
	 * Returns an immutable compound state that handles the global and dirty
	 * state.
	 * 
	 * @return
	 */
	IDataState getDirtyAwareGlobalState();

	/**
	 * Makes the given dto undirty.
	 * 
	 * @param key
	 * @param dto
	 */
	void makeUndirty(Object key, Object dto);

	/**
	 * Adds a new "not persistent" entry. The key will be calculated using the
	 * {@link HashUtil}.
	 * <p>
	 * The object will be put into the dirtyState but not the global state.
	 * 
	 * @param newEntry
	 */
	void addNewTransient(Object newEntry);

}
