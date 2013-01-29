/*******************************************************************************
 * Copyright (c) 2012, 2013 Committers of lunifera.org - Lunifera.org.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Cristiano Gavião - initial API and implementation
 *******************************************************************************/
package org.lunifera.runtime.component.logging.over.slf4j;

import java.util.HashMap;

import org.eclipse.equinox.log.ExtendedLogService;
import org.eclipse.equinox.log.Logger;
import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceReference;
import org.osgi.service.log.LogService;


/**
 * A SFL4J based implementation of {@link ExtendedLogService} and {@link LogService}.
 * 
 * @author Cristiano Gavião
 *
 */
public class ExtendedLogServiceImpl implements ExtendedLogService, LogService {
	
	private final ExtendedLogServiceFactory factory;
	private volatile Bundle bundle;
	private final HashMap<String, Logger> loggerCache = new HashMap<String, Logger>();

	public ExtendedLogServiceImpl(ExtendedLogServiceFactory factory, Bundle bundle) {
		this.factory = factory;
		this.bundle = bundle;
	}	


	public void log(int level, String message) {
		getLogger(bundle, null).log(level, message);
	}

	public void log(int level, String message, Throwable exception) {
		getLogger(bundle, null).log(level, message, exception);
	}

	@SuppressWarnings("rawtypes")
	public void log(ServiceReference sr, int level, String message) {
		getLogger(bundle, null).log(sr, level, message);
	}

	@SuppressWarnings("rawtypes")
	public void log(ServiceReference sr, int level, String message, Throwable exception) {
		getLogger(bundle, null).log(sr, level, message, exception);
	}

	public void log(Object context, int level, String message) {
		getLogger(bundle, null).log(context, level, message);
		log(context, level, message, null);
	}

	public void log(Object context, int level, String message, Throwable exception) {
		getLogger(bundle, null).log(context, level, message, exception);
	}

	public synchronized Logger getLogger(String name) {
		if (name == null)
		{
			name = bundle.getSymbolicName();
		}
		Logger logger = loggerCache.get(name);
		if (logger == null) {
			
			logger = new LoggerWrapper(this, name);
			loggerCache.put(name, logger);
		}
		return logger;
	}

	public Logger getLogger(Bundle logBundle, String name) {
		if (logBundle == null || logBundle == bundle)
			return getLogger(name);
		// only check permission if getting another bundles log
		factory.checkLogPermission();
		ExtendedLogService bundleLogService = factory.getLogService(logBundle);
		return bundleLogService.getLogger(name);
	}

	public String getName() {
		return getLogger(null).getName();
	}

	public boolean isLoggable(int level) {
		return getLogger(null).isLoggable(level);
	}

	void setBundle(Bundle bundle) {
		this.bundle = bundle;
	}
}
