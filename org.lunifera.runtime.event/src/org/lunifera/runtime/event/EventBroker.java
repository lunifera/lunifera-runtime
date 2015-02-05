/*******************************************************************************
 * Copyright (c) 2009, 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Florian Pirchner - adjustments to lunifera code
 *******************************************************************************/
package org.lunifera.runtime.event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.lunifera.runtime.common.event.IEventBroker;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

@Component
public class EventBroker implements IEventBroker {

	// private static final Logger LOGGER = LoggerFactory
	// .getLogger(EventBroker.class);

	// TBD synchronization
	private Map<EventHandler, Collection<ServiceRegistration<?>>> registrations = new HashMap<EventHandler, Collection<ServiceRegistration<?>>>();

	private EventAdmin eventAdmin;

	private ComponentContext context;

	public EventBroker() {
		// placeholder
	}

	@Activate
	protected void activate(ComponentContext context) {
		this.context = context;
	}

	@Deactivate
	protected void deactivate(ComponentContext context) {
		Collection<Collection<ServiceRegistration<?>>> values = new ArrayList<Collection<ServiceRegistration<?>>>(
				registrations.values());
		registrations.clear();
		for (Collection<ServiceRegistration<?>> handled : values) {
			for (ServiceRegistration<?> registration : handled) {
				registration.unregister();
			}
		}

		this.context = null;
	}

	@Reference(cardinality = ReferenceCardinality.MANDATORY, unbind = "unbindEventAdmin")
	protected void bindEventAdmin(EventAdmin eventAdmin) {
		this.eventAdmin = eventAdmin;
	}

	protected void unbindEventAdmin(EventAdmin eventAdmin) {
		this.eventAdmin = null;
	}

	@Override
	public boolean send(String topic, Object data) {
		Event event = constructEvent(topic, data);
		eventAdmin.sendEvent(event);
		return true;
	}

	@Override
	public boolean post(String topic, Object data) {
		Event event = constructEvent(topic, data);
		eventAdmin.postEvent(event);
		return true;
	}

	@SuppressWarnings("unchecked")
	private Event constructEvent(String topic, Object data) {
		Event event;
		if (data instanceof Dictionary<?, ?>) {
			event = new Event(topic, (Dictionary<String, ?>) data);
		} else if (data instanceof Map<?, ?>) {
			event = new Event(topic, (Map<String, ?>) data);
		} else {
			Dictionary<String, Object> d = new Hashtable<String, Object>(2);
			d.put(EventConstants.EVENT_TOPIC, topic);
			if (data != null)
				d.put(IEventBroker.DATA, data);
			event = new Event(topic, d);
		}
		return event;
	}

	@Override
	public boolean subscribe(String topic, EventHandler eventHandler) {
		return subscribe(topic, null, eventHandler, false);
	}

	@Override
	public boolean subscribe(String topic, String filter,
			EventHandler eventHandler, boolean headless) {
		BundleContext bundleContext = context.getBundleContext();
		String[] topics = new String[] { topic };
		Dictionary<String, Object> d = new Hashtable<String, Object>();
		d.put(EventConstants.EVENT_TOPIC, topics);
		if (filter != null) {
			d.put(EventConstants.EVENT_FILTER, filter);
		}
		ServiceRegistration<?> registration = bundleContext.registerService(
				EventHandler.class.getName(), eventHandler, d);
		Collection<ServiceRegistration<?>> handled = registrations
				.get(eventHandler);
		if (handled == null) {
			registrations.put(eventHandler,
					handled = new ArrayList<ServiceRegistration<?>>());
		}
		handled.add(registration);
		return true;
	}

	@Override
	public boolean unsubscribe(EventHandler eventHandler) {
		Collection<ServiceRegistration<?>> handled = registrations
				.remove(eventHandler);
		if (handled == null || handled.isEmpty())
			return false;
		for (ServiceRegistration<?> r : handled) {
			r.unregister();
		}
		return true;
	}
}
