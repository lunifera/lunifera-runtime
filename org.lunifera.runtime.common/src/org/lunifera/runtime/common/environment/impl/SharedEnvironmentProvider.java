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

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.lunifera.runtime.common.dispose.IDisposable;
import org.lunifera.runtime.common.environment.ISharedEnvironment;
import org.lunifera.runtime.common.environment.ISharedEnvironmentProvider;
import org.osgi.framework.Filter;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.util.tracker.ServiceTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(property = { "service.ranking=100" }, immediate = true)
public class SharedEnvironmentProvider implements ISharedEnvironmentProvider {

	private static final String OSGI_PROP__ENV_ID = "env.id";

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SharedEnvironmentProvider.class);

	private ComponentContext context;
	private Map<String, ServiceRegistration<ISharedEnvironment>> registrations = new HashMap<String, ServiceRegistration<ISharedEnvironment>>();

	@Override
	public ISharedEnvironment getEnvironment(String id) {
		ISharedEnvironment env = find(id);
		if (env == null) {
			env = createEnvironment(id);
		}

		return null;
	}

	protected ISharedEnvironment createEnvironment(String id) {
		SharedEnvironment env = new SharedEnvironment(id);

		// register as service
		Dictionary<String, String> properties = new Hashtable<String, String>();
		properties.put(OSGI_PROP__ENV_ID, id);
		ServiceRegistration<ISharedEnvironment> reg = context
				.getBundleContext().registerService(ISharedEnvironment.class,
						env, properties);
		registrations.put(id, reg);
		return env;
	}

	@Override
	public void disposeEnvironment(ISharedEnvironment environment) {
		ServiceRegistration<ISharedEnvironment> reg = registrations
				.remove(environment.getId());
		if (reg != null) {
			ISharedEnvironment service = context.getBundleContext().getService(
					reg.getReference());
			if (service instanceof IDisposable) {
				((IDisposable) service).dispose();
			}
			reg.unregister();
		}
	}

	@Activate
	protected void activate(ComponentContext context) {
		this.context = context;
	}

	@Deactivate
	protected void deactivate(ComponentContext context) {

		for (ServiceRegistration<ISharedEnvironment> reg : registrations
				.values()) {
			ISharedEnvironment service = context.getBundleContext().getService(
					reg.getReference());
			if (service instanceof IDisposable) {
				((IDisposable) service).dispose();
			}
			reg.unregister();
		}

		this.context = null;
	}

	protected ISharedEnvironment find(String id) {
		ServiceTracker<ISharedEnvironment, ISharedEnvironment> tracker = null;
		try {
			tracker = new ServiceTracker<ISharedEnvironment, ISharedEnvironment>(
					context.getBundleContext(), createFilter(id), null);
			tracker.open();
			return tracker.waitForService(500);
		} catch (InvalidSyntaxException e) {
			LOGGER.error("{}", e);
		} catch (InterruptedException e) {
		} finally {
			if (tracker != null) {
				tracker.close();
			}
		}

		return null;
	}

	protected Filter createFilter(String id) throws InvalidSyntaxException {
		String filter = String
				.format("(&(objectClass=org.lunifera.runtime.common.environment.ISharedEnvironment)(%s=%s))",
						OSGI_PROP__ENV_ID, id);
		return context.getBundleContext().createFilter(filter);
	}

}
