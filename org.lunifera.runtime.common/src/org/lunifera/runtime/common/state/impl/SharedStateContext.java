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
package org.lunifera.runtime.common.state.impl;

import java.util.HashMap;
import java.util.Map;

import org.lunifera.runtime.common.dispose.AbstractDisposable;
import org.lunifera.runtime.common.dispose.IReferenceCountable;
import org.lunifera.runtime.common.state.IDataState;
import org.lunifera.runtime.common.state.ISharedStateContext;

public class SharedStateContext extends AbstractDisposable implements
		ISharedStateContext, IReferenceCountable {

	private Map<Object, Object> data;

	private final String id;

	private DirtyDataState dirtyState;

	private DataState globalState;

	private int usageCount;

	public SharedStateContext(String id) {
		this.id = id;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setProperty(Object key, Object value) {
		checkDisposed();

		if (data == null) {
			data = new HashMap<Object, Object>();
		}
		data.put(key, value);
	}

	@Override
	public Object getProperty(Object key) {
		checkDisposed();

		if (data == null) {
			return null;
		}
		return data.get(key);
	}

	@Override
	public Object[] getPropertyKeys() {
		checkDisposed();

		if (data == null) {
			return new Object[0];
		}
		return data.keySet().toArray();
	}

	@Override
	public IDataState getDirtyState() {
		checkDisposed();

		if (dirtyState == null) {
			dirtyState = new DirtyDataState(this);
		}
		return dirtyState;
	}

	@Override
	public IDataState getGlobalDataState() {
		checkDisposed();

		if (globalState == null) {
			globalState = new GlobalDataState(this);
		}
		return globalState;
	}

	@Override
	public IDataState getDirtyAwareGlobalState() {
		return new DirtyDataAwareGlobalState(getGlobalDataState(),
				getDirtyState());
	}

	@Override
	protected void internalDispose() {
		if (data != null) {
			data.clear();
			data = null;
		}

		if (dirtyState != null) {
			dirtyState.dispose();
			dirtyState = null;
		}

		if (globalState != null) {
			globalState.dispose();
			globalState = null;
		}
	}

	@Override
	public int getUsageCount() {
		return usageCount;
	}

	@Override
	public void increaseUsageCount() {
		usageCount++;
	}

	@Override
	public void decreaseUsageCount() {
		usageCount--;
	}

	public void invalidate(String key, Object object) {
		if (dirtyState != null) {
			dirtyState.invalidate(key);
		}
		if (globalState != null) {
			globalState.invalidate(key);
		}
	}

	public void handleDirty(String key, Object object) {
		if (dirtyState != null) {
			dirtyState.register(key, object);
		}
	}
}
