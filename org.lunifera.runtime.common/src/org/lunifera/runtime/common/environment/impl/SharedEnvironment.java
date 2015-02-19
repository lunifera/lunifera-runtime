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
package org.lunifera.runtime.common.environment.impl;

import java.util.HashMap;
import java.util.Map;

import org.lunifera.runtime.common.dispose.AbstractDisposable;
import org.lunifera.runtime.common.environment.ISharedEnvironment;

public class SharedEnvironment extends AbstractDisposable implements
		ISharedEnvironment {

	private Map<Object, Object> data;

	private final String id;

	public SharedEnvironment(String id) {
		this.id = id;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	protected void internalDispose() {
		if (data != null) {
			data.clear();
			data = null;
		}
	}

	@Override
	public void setData(Object key, Object value) {
		checkDisposed();
		if (data == null) {
			data = new HashMap<Object, Object>();
		}
		data.put(key, value);
	}

	@Override
	public Object getData(Object key) {
		checkDisposed();
		if (data == null) {
			return null;
		}
		return data.get(key);
	}

	@Override
	public Object[] getDataKeys() {
		checkDisposed();
		if (data == null) {
			return new Object[0];
		}
		return data.keySet().toArray();
	}
}
