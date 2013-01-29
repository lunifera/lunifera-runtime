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

import java.security.Permission;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.equinox.log.ExtendedLogService;
import org.eclipse.equinox.log.LogPermission;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceRegistration;

/**
 * <code>ExtendedLogServiceFactory</code> creates {@link ExtendedLogService} implementation that wraps SLF4J.
 *
 * @author Cristiano Gavião
 */
public class ExtendedLogServiceFactory implements ServiceFactory<ExtendedLogService>, BundleListener {

	private final Map<Bundle, ExtendedLogService> logServices = new HashMap<Bundle, ExtendedLogService>();
	private final Permission logPermission = new LogPermission("*", LogPermission.LOG); //$NON-NLS-1$


	public ExtendedLogService getService(Bundle bundle, ServiceRegistration<ExtendedLogService> registration) {
		return getLogService(bundle);
	}

	public void ungetService(Bundle bundle, ServiceRegistration<ExtendedLogService> registration, ExtendedLogService service) {
		// do nothing
		// Notice that we do not remove the the LogService impl for the bundle because other bundles
		// still need to be able to get the cached loggers for a bundle.
	}

	public void bundleChanged(BundleEvent event) {
		if (event.getType() == BundleEvent.UNINSTALLED)
			removeLogService(event.getBundle());
	}

	synchronized void removeLogService(Bundle bundle) {
		logServices.remove(bundle);
	}

	synchronized ExtendedLogServiceImpl getLogService(Bundle bundle) {
		ExtendedLogServiceImpl logService = (ExtendedLogServiceImpl) logServices.get(bundle);
		if (logService == null) {
			logService = new ExtendedLogServiceImpl(this, bundle);
			if (bundle != null && bundle.getState() != Bundle.UNINSTALLED)
				logServices.put(bundle, logService);
		}
		return logService;
	}
	
	synchronized void shutdown() {
		logServices.clear();
	}

	
	void checkLogPermission() throws SecurityException {
		SecurityManager sm = System.getSecurityManager();
		if (sm != null)
			sm.checkPermission(logPermission);
	}	
}
