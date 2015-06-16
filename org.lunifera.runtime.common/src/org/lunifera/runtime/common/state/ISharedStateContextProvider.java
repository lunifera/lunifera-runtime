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

import java.util.Map;

/**
 * The {@link ISharedStateContextProvider} is responsible to create shared state
 * context, to dispose them and to provide active instances of them.
 * <p>
 * The provider is counting references to the shared state context objects
 * returned. A call to {@link #getContext(String, Map)} will increase the
 * counter of references. A call to {@link #unget(ISharedStateContext)}
 * will decrease the counter. If the counter for a {@link ISharedStateContext}
 * decreased to 0, it will become disposed.
 */
public interface ISharedStateContextProvider {

	/**
	 * Returns the {@link ISharedStateContext} for the given id. If no shared
	 * state context is available, a new instance will be created and registered
	 * as an OSGi service.
	 * <p>
	 * The internal usage counter will be increased. Do not miss to
	 * {@link #unget(ISharedStateContext)} the context if it is not
	 * needed anymore.
	 * 
	 * @param id
	 * @param properties
	 *            - will be added to the OSGi service
	 * @return
	 */
	ISharedStateContext getContext(String id, Map<String, Object> properties);

	/**
	 * If an {@link ISharedStateContext} is not used anymore, that method must
	 * be called.
	 * <p>
	 * The internal usage counter will be decreased. If usage counter reaches
	 * zero, the context will be disposed.
	 * 
	 * @param context
	 */
	void unget(ISharedStateContext context);

}
