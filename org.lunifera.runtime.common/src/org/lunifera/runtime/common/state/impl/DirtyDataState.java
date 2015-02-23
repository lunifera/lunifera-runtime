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

import com.google.common.cache.CacheBuilder;

/**
 * Responsible to cache changed objects.
 */
public class DirtyDataState extends DataState {

	public DirtyDataState(SharedStateContext sharedStateContext) {
		super(sharedStateContext);
	}

	protected void ensureCache() {
		if (cache == null) {
			cache = CacheBuilder.newBuilder().initialCapacity(20).weakValues()
					.build();
		}
	}
}
