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
 * Different versions of objects may be available. For instance a changed and an
 * unchanged version of a bean. This class is responsible to handle dirty state objects.
 */
public interface IDirtyState {

	/**
	 * Returns true, if a dirty version of the given object is available.
	 * 
	 * @param object
	 * @return
	 */
	boolean containsDirty(Object object);

	/**
	 * Returns the dirty version of the object if available.
	 * 
	 * @param object
	 * @return
	 */
	Object getDirty(Object object);
}
