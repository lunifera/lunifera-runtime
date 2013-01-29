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

import java.util.Dictionary;
import java.util.Hashtable;

import org.eclipse.equinox.log.ExtendedLogService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleListener;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceFactory;
import org.osgi.service.log.LogService;


public class Activator implements BundleActivator {

    public void start(BundleContext bundleContext) throws Exception {

		Dictionary<String, Object> props = new Hashtable<String, Object>();
		props.put(Constants.SERVICE_DESCRIPTION,
				"Lunifera OSGi Log Service over SLF4J");
		props.put(Constants.SERVICE_RANKING, Integer.MAX_VALUE);
		props.put(Constants.SERVICE_VENDOR, "Lunifera.org");

		ServiceFactory<ExtendedLogService> factory = new ExtendedLogServiceFactory();
		
		bundleContext.addBundleListener((BundleListener) factory);
		
		bundleContext.registerService(
				new String[] { ExtendedLogService.class.getName(),
						LogService.class.getName() }, factory, props);

    }

    /**
     *
     * Implements <code>BundleActivator.stop()</code>.
     *
     * @param bundleContext the framework context for the bundle
     * @throws Exception
     */
    public void stop(BundleContext bundleContext) throws Exception {

        // Note: It is not required that we remove the service here, since
        // the framework will do it automatically.
    }
}
