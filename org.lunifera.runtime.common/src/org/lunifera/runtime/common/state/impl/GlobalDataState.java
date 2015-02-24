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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang.reflect.MethodUtils;
import org.lunifera.runtime.common.annotations.DtoUtils;
import org.lunifera.runtime.common.hash.HashUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Responsible to cache changed objects.
 */
public class GlobalDataState extends DataState implements
		PropertyChangeListener {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(GlobalDataState.class);

	public GlobalDataState(SharedStateContext sharedStateContext) {
		super(sharedStateContext);
	}

	@Override
	public void register(Object key, Object object) {
		checkDisposed();

		if (contains(key)) {
			// already contained
			return;
		}

		super.register(key, object);

		if (object != null) {
			try {
				MethodUtils.invokeMethod(object, "addPropertyChangeListener",
						this);
			} catch (SecurityException e) {
				LOGGER.warn("Observer for dirtyState handling could not be added for "
						+ object.getClass().getName());
			} catch (IllegalAccessException e) {
				LOGGER.warn("Observer for dirtyState handling could not be added for "
						+ object.getClass().getName());
			} catch (IllegalArgumentException e) {
				LOGGER.warn("Observer for dirtyState handling could not be added for "
						+ object.getClass().getName());
			} catch (InvocationTargetException e) {
				LOGGER.warn("Observer for dirtyState handling could not be added for "
						+ object.getClass().getName());
			} catch (NoSuchMethodException e) {
				LOGGER.warn("Observer for dirtyState handling could not be added for "
						+ object.getClass().getName());
			}
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		Object object = event.getSource();
		Class<?> objClass = object.getClass();
		try {
			if (DtoUtils.isDisposeField(objClass, event.getPropertyName())) {
				handleDispose(event);
				return;
			}
		} catch (SecurityException ignoreIt) {
			LOGGER.error("{}", ignoreIt);
		}

		handleDirty(event);
	}

	/**
	 * Puts the object into the dirty state.
	 * 
	 * @param event
	 */
	protected void handleDirty(PropertyChangeEvent event) {
		Object object = event.getSource();
		Class<?> objClass = object.getClass();

		sharedStateContext.handleDirty(
				HashUtil.createObjectWithIdHash(objClass, object), object);
	}

	/**
	 * Invalidates the object in the cache.
	 * 
	 * @param event
	 */
	protected void handleDispose(PropertyChangeEvent event) {
		Object object = event.getSource();
		Class<?> objClass = object.getClass();

		sharedStateContext.invalidate(
				HashUtil.createObjectWithIdHash(objClass, object), object);
	}
}
