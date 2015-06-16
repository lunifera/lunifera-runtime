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
package org.lunifera.runtime.systemextension;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.framework.hooks.resolver.ResolverHookFactory;

public class Activator implements BundleActivator {

	private ServiceRegistration<ResolverHookFactory> reg;

	@Override
	public void start(BundleContext context) throws Exception {
		reg = context.registerService(ResolverHookFactory.class,
				new ResolverHook(), null);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		if (reg != null) {
			reg.unregister();
			reg = null;
		}
	}

}
