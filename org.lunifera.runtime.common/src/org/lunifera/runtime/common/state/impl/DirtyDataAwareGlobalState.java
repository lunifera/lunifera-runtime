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

import org.lunifera.runtime.common.state.IDataState;

/**
 * Combines the global data state and the dirty data state.
 */
public class DirtyDataAwareGlobalState implements IDataState {

	private final IDataState dataState;
	private final IDataState dirtyState;

	public DirtyDataAwareGlobalState(IDataState dataState, IDataState dirtyState) {
		this.dataState = dataState;
		this.dirtyState = dirtyState;
	}

	@Override
	public boolean contains(Object key) {
		return dirtyState.contains(key) || dataState.contains(key);
	}

	@Override
	public Object get(Object key) {
		Object result = dirtyState.get(key);
		if (result == null) {
			result = dataState.get(key);
		}

		return result;
	}

	@Override
	public void register(Object key, Object object) {
		throw new UnsupportedOperationException("Not a valid call");
	}

	@Override
	public void registerAll(Collection<Object> objects, Hashing hasher) {
		throw new UnsupportedOperationException("Not a valid call");
	}

	@Override
	public void invalidate(Object key) {
		throw new UnsupportedOperationException("Not a valid call");
	}

	@Override
	public long size() {
		throw new UnsupportedOperationException("Not a valid call");
	}

	@Override
	public boolean empty() {
		throw new UnsupportedOperationException("Not a valid call");
	}

}
