/*******************************************************************************
 * Copyright (c) 2012 Committers of lunifera.org - Lunifera.org.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Florian Pirchner - initial API and implementation
 *******************************************************************************/
package org.lunifera.runtime.component.configuration.manager.tests;

import java.util.Dictionary;

import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedServiceFactory;

/**
 * Simple helper service.
 */
public class HelperManagedServiceFactory implements ManagedServiceFactory {

	@Override
	public String getName() {
		return HelperManagedServiceFactory.class.getName();
	}

	@Override
	public void updated(String pid, Dictionary<String, ?> properties)
			throws ConfigurationException {

	}

	@Override
	public void deleted(String pid) {

	}

}
