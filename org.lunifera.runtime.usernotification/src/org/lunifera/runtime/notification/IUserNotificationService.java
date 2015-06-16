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
package org.lunifera.runtime.notification;

import java.util.Dictionary;
import java.util.Map;

import org.osgi.service.event.EventHandler;

/**
 * Is responsible for sending and retrieving notifications.
 * <p>
 * IMPORTANT: The notification service ensures, that the OSGi event admin is
 * used and not the e4EventBroker.
 */
public interface IUserNotificationService {

	/**
	 * The name of the default event attribute used to pass data.
	 */
	public String DATA = "org.lunifera.data"; //$NON-NLS-1$

	/**
	 * Publish event synchronously (the method does not return until the event
	 * is processed).
	 * <p>
	 * If data is a {@link Map} or a {@link Dictionary}, it is passed as is.
	 * Otherwise, a new Map is constructed and its {@link #DATA} attribute is
	 * populated with this value.
	 * </p>
	 * 
	 * @param topic
	 *            topic of the event to be published
	 * @param data
	 *            data to be published with the event
	 * @return <code>true</code> if this operation was performed successfully;
	 *         <code>false</code> otherwise
	 */
	public boolean send(String userId, Object data);

	/**
	 * Publish event asynchronously (this method returns immediately).
	 * <p>
	 * If data is a {@link Map} or a {@link Dictionary}, it is passed as is.
	 * Otherwise, a new Map is constructed and its {@link #DATA} attribute is
	 * populated with this value.
	 * </p>
	 * 
	 * @param topic
	 *            topic of the event to be published
	 * @param data
	 *            data to be published with the event
	 * @return <code>true</code> if this operation was performed successfully;
	 *         <code>false</code> otherwise
	 */
	public boolean post(String userId, Object data);

	/**
	 * Subscribe for events on the given topic.
	 * <p>
	 * The handler will be called on the UI thread.
	 * </p>
	 * 
	 * @param topic
	 *            topic of interest
	 * @param eventHandler
	 *            object to call when an event of interest arrives
	 * @return <code>true</code> if this operation was performed successfully;
	 *         <code>false</code> otherwise
	 */
	public boolean subscribe(String userId, EventHandler eventHandler);

	/**
	 * Subscribe for events on the given topic.
	 * <p>
	 * The handler will be called on the UI thread if "headless" is set to
	 * <code>true</code>.
	 * </p>
	 * 
	 * @param topic
	 *            topic of interest
	 * @param filter
	 *            the LDAP event filter
	 * @param eventHandler
	 *            object to call when an event of interest arrives
	 * @param headless
	 *            <code>true</code> if handing of the events does not require
	 *            UI; <code>false</code> otherwise
	 * @return <code>true</code> if this operation was performed successfully;
	 *         <code>false</code> otherwise
	 */
	public boolean subscribe(String userId, String filter,
			EventHandler eventHandler, boolean headless);

	/**
	 * Unsubscribe handler previously registered using
	 * {@link #subscribe(String, EventHandler)}.
	 * 
	 * @param eventHandler
	 *            previously registered event handler
	 * @return <code>true</code> if this operation was performed successfully;
	 *         <code>false</code> otherwise
	 */
	public boolean unsubscribe(EventHandler eventHandler);

}
