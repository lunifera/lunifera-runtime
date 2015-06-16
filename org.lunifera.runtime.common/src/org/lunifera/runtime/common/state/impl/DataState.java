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
package org.lunifera.runtime.common.state.impl;

import java.util.Collection;

import org.lunifera.runtime.common.dispose.AbstractDisposable;
import org.lunifera.runtime.common.state.IDataState;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * Responsible to cache data by a cache implementation.
 */
public class DataState extends AbstractDisposable implements IDataState {

	protected Cache<Object, Object> cache;
	protected final SharedStateContext sharedStateContext;

	public DataState(SharedStateContext sharedStateContext) {
		this.sharedStateContext = sharedStateContext;
	}

	@Override
	public boolean contains(Object key) {
		checkDisposed();

		if (cache == null) {
			return false;
		}
		return cache.getIfPresent(key) != null;
	}

	@Override
	public Object get(Object key) {
		checkDisposed();

		if (cache == null) {
			return null;
		}

		return cache.getIfPresent(key);
	}

	@Override
	public void register(Object key, Object object) {
		checkDisposed();
		
		if (contains(key)) {
			// already contained
			return;
		}

		ensureCache();
		cache.put(key, object);
	}

	protected void ensureCache() {
		if (cache == null) {
			cache = CacheBuilder.newBuilder().initialCapacity(250).weakValues()
					.build();
		}
	}

	@Override
	public void registerAll(Collection<Object> objects, Hashing hasher) {
		checkDisposed();
		for (Object object : objects) {
			register(hasher.getHashCode(object), object);
		}
	}

	@Override
	public void invalidate(Object key) {
		checkDisposed();
		if (cache != null) {
			cache.invalidate(key);
		}
	}

	@Override
	protected void internalDispose() {
		if (cache != null) {
			cache.invalidateAll();
			cache = null;
		}
	}

	@Override
	public long size() {
		if (cache == null) {
			return 0;
		}
		cache.cleanUp();
		return cache.size();
	}

	@Override
	public boolean empty() {
		return size() <= 0;
	}
}
