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

import java.util.Collection;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.lunifera.runtime.common.dispose.IDisposable;
import org.lunifera.runtime.common.dispose.IReferenceCountable;
import org.lunifera.runtime.common.state.ISharedStateContext;
import org.lunifera.runtime.common.state.ISharedStateContextProvider;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(property = { "service.ranking=100" }, immediate = true)
public class SharedStateContextProvider implements ISharedStateContextProvider {

	private static final String OSGI_PROP__ENV_ID = "sharedState.id";

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SharedStateContextProvider.class);

	private ComponentContext componentContext;
	private Map<String, ServiceRegistration<ISharedStateContext>> registrations = new HashMap<String, ServiceRegistration<ISharedStateContext>>();

	@Override
	public ISharedStateContext getContext(String id,
			Map<String, Object> properties) {
		ISharedStateContext context = find(id);
		if (context == null) {
			context = createContext(id, properties);
		}

		((IReferenceCountable) context).increaseUsageCount();

		return context;
	}

	protected ISharedStateContext createContext(String id,
			Map<String, Object> inProperties) {
		SharedStateContext context = new SharedStateContext(id);

		// register as service
		Dictionary<String, Object> properties = new Hashtable<String, Object>();
		properties.put(OSGI_PROP__ENV_ID, id);
		// add input properties
		if (inProperties != null) {
			for (Map.Entry<String, Object> entry : inProperties.entrySet()) {
				properties.put(entry.getKey(), entry.getValue());
			}
		}
		ServiceRegistration<ISharedStateContext> reg = componentContext
				.getBundleContext().registerService(ISharedStateContext.class,
						context, properties);
		registrations.put(id, reg);
		return context;
	}

	@Override
	public void unget(ISharedStateContext context) {
		IReferenceCountable countable = (IReferenceCountable) context;
		countable.decreaseUsageCount();

		if (countable.getUsageCount() <= 0) {
			ServiceRegistration<ISharedStateContext> reg = registrations
					.remove(context.getId());
			if (reg != null) {
				ISharedStateContext service = componentContext
						.getBundleContext().getService(reg.getReference());
				if (service instanceof IDisposable) {
					((IDisposable) service).dispose();
				}
				reg.unregister();
			}
		}
	}

	@Activate
	protected void activate(ComponentContext context) {
		this.componentContext = context;
	}

	@Deactivate
	protected void deactivate(ComponentContext context) {

		for (ServiceRegistration<ISharedStateContext> reg : registrations
				.values()) {
			ISharedStateContext service = context.getBundleContext()
					.getService(reg.getReference());
			if (service instanceof IDisposable) {
				((IDisposable) service).dispose();
			}
			reg.unregister();
		}

		this.componentContext = null;
	}

	protected ISharedStateContext find(String id) {
		try {
			BundleContext bc = componentContext.getBundleContext();
			Collection<ServiceReference<ISharedStateContext>> references = bc
					.getServiceReferences(ISharedStateContext.class,
							createFilter(id));
			if (!references.isEmpty()) {
				ISharedStateContext sharedStateContext = bc
						.getService(references.iterator().next());
				return sharedStateContext;
			}
		} catch (InvalidSyntaxException e) {
			LOGGER.error("{}", e);
		}

		return null;
	}

	protected String createFilter(String id) throws InvalidSyntaxException {
		return String.format("(%s=%s)", OSGI_PROP__ENV_ID, id);
	}

}
