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

import java.util.Collection;

/**
 * Different versions of objects may be available. For instance a changed and an
 * unchanged version of a bean. This class is responsible to handle beans and
 * offer them in a common context. It caches registered beans in a GC-aware
 * manner.
 */
public interface IDataState {

	/**
	 * Returns the size of the cached objects.
	 * 
	 * @return
	 */
	long size();

	/**
	 * Returns true if the cache is empty.
	 * 
	 * @return
	 */
	boolean empty();

	/**
	 * Returns true, if a dirty version of the given object is available.
	 * 
	 * @param key
	 * @return
	 */
	boolean contains(Object key);

	/**
	 * Returns the dirty version of the object if available.
	 * 
	 * @param key
	 * @return
	 */
	Object get(Object key);

	/**
	 * Registers the given object by the passed key.
	 * 
	 * @param key
	 * @param object
	 * @return
	 */
	void register(Object key, Object object);

	/**
	 * Registers all objects provided by the collection. The hasher is used, to
	 * calculate proper keys for the map.
	 * 
	 * @param objects
	 * @param hasher
	 */
	void registerAll(Collection<Object> objects, Hashing hasher);

	/**
	 * Invalidates the object with the given key.
	 * 
	 * @param key
	 */
	void invalidate(Object key);

	/**
	 * Returns the hash code or key for the given object.
	 */
	public interface Hashing {

		/**
		 * Returns the hashcode for the given object.
		 * 
		 * @param object
		 * @return
		 */
		Object getHashCode(Object object);

	}

}
