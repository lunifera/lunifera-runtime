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
package org.lunifera.runtime.cloud.zookeeper.internal.server;

import java.io.File;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.lunifera.runtime.cloud.zookeeper.IZookeeperServer;
import org.lunifera.runtime.cloud.zookeeper.ZookeeperConstants;
import org.lunifera.runtime.cloud.zookeeper.internal.ZookeeperException;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedServiceFactory;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.prefs.Preferences;
import org.osgi.service.prefs.PreferencesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ManagedServiceFactories that creates new instances of ZookeeperServerService.
 */
public class ZookeeperServerFactory implements ManagedServiceFactory {

	private static final Logger logger = LoggerFactory
			.getLogger(ZookeeperServerFactory.class);

	private Map<String, ZookeeperServerService> servers = Collections
			.synchronizedMap(new HashMap<String, ZookeeperServerService>());
	private Map<String, ServiceRegistration<IZookeeperServer>> registrations = Collections
			.synchronizedMap(new HashMap<String, ServiceRegistration<IZookeeperServer>>());

	private int lastServiceId = 0;
	private ComponentContext context;
	private PreferencesService preferenceService;

	/**
	 * Called by OSGi-DS.
	 * 
	 * @param context
	 * @param properties
	 */
	protected void activate(ComponentContext context,
			Map<String, Object> properties) {
		logger.debug("{} started", getName());
		this.context = context;
	}

	/**
	 * Called by OSGi-DS.
	 * 
	 * @param context
	 * @param properties
	 */
	protected void deactivate(ComponentContext context,
			Map<String, Object> properties) {

		this.context = null;

		for (String pid : servers.keySet().toArray(new String[servers.size()])) {
			deleted(pid);
		}

		logger.debug("{} stopped", getName());
	}

	@Override
	public String getName() {
		return "ZookeeperServerService Factory";
	}

	@Override
	public void updated(String pid, Dictionary<String, ?> properties)
			throws ConfigurationException {

		ZookeeperServerService serverService = (ZookeeperServerService) servers
				.get(pid);
		if (serverService == null) {
			File configFile = context.getBundleContext().getDataFile(
					"lunifera_zookeeper");
			serverService = new ZookeeperServerService(
					Integer.toString(++lastServiceId), configFile);
		} else {
			try {
				serverService.stop();
			} catch (ZookeeperException e) {
				logger.error("{}", e);
				throw new ConfigurationException(null, "", e);
			}
		}

		Preferences preferences = preferenceService.getSystemPreferences();
		Preferences serverPrefs = preferences
				.node(ZookeeperConstants.PREFERENCE_SERVER_NODE_PATH);

		int port = ZookeeperConstants.DEFAULT__SERVER_PORT;
		if (properties.get(ZookeeperConstants.SERVER_PORT) != null) {
			port = Integer.valueOf((String) properties
					.get(ZookeeperConstants.SERVER_PORT));
		} else if (serverPrefs != null) {
			port = serverPrefs.getInt(ZookeeperConstants.SERVER_PORT,
					ZookeeperConstants.DEFAULT__SERVER_PORT);
		}

		int maxClientConnections = ZookeeperConstants.DEFAULT__SERVER_MAX_CLIENT_CONNECTIONS;
		if (properties.get(ZookeeperConstants.SERVER_MAX_CLIENT_CONNECTIONS) != null) {
			maxClientConnections = Integer.valueOf((String) properties
					.get(ZookeeperConstants.SERVER_MAX_CLIENT_CONNECTIONS));
		} else if (serverPrefs != null) {
			maxClientConnections = serverPrefs.getInt(
					ZookeeperConstants.SERVER_MAX_CLIENT_CONNECTIONS,
					ZookeeperConstants.DEFAULT__SERVER_MAX_CLIENT_CONNECTIONS);
		}

		serverService.setPort(port);
		serverService.setMaxClientConnections(maxClientConnections);

		Dictionary<String, Object> copyProps = copy(properties);
		copyProps.put(ZookeeperConstants.SERVER_ID, serverService.getId());
		copyProps.put(ZookeeperConstants.SERVER_PORT, Integer.toString(port));

		// Register application as service and add to cache
		//
		if (!servers.containsKey(pid)) {
			servers.put(pid, serverService);

			// register zookeeper server as a service
			ServiceRegistration<IZookeeperServer> registration = context
					.getBundleContext().registerService(IZookeeperServer.class,
							serverService, copyProps);
			registrations.put(pid, registration);
		} else {
			ServiceRegistration<IZookeeperServer> registration = registrations
					.get(pid);
			registration.setProperties(copyProps);
		}

		// start the application again
		//
		try {
			serverService.start();
		} catch (ZookeeperException e) {
			logger.error("{}", e);
			throw new ConfigurationException(null, "", e);
		}
		logger.debug("New ZookeeperServerService {} started on port {}",
				serverService.getId(), serverService.getPort());
	}

	/**
	 * Maps the params to a map.
	 * 
	 * @param input
	 * @return
	 */
	private Dictionary<String, Object> copy(final Dictionary<?, ?> input) {
		if (input == null) {
			return null;
		}

		final Hashtable<String, Object> result = new Hashtable<String, Object>(
				input.size());
		final Enumeration<?> keys = input.keys();
		while (keys.hasMoreElements()) {
			final Object key = keys.nextElement();
			try {
				result.put((String) key, (String) input.get(key));
			} catch (final ClassCastException e) {
				throw new IllegalArgumentException("Only strings are allowed",
						e);
			}
		}
		return result;
	}

	@Override
	public void deleted(String pid) {
		ZookeeperServerService server = servers.remove(pid);
		if (server != null) {
			try {
				server.stop();
			} catch (ZookeeperException e) {
				logger.error("{}", e);
			}
			logger.debug("ZookeeperServerService {} stopped on port {}",
					server.getId(), server.getPort());
		}

		// unregister server service as a service
		ServiceRegistration<IZookeeperServer> registration = registrations
				.remove(pid);
		if (registration != null) {
			registration.unregister();
			logger.debug("ZookeeperServerService {} removed as a service!",
					server.getId());
		}
	}

	/**
	 * Binds the OSGi preferences.
	 * 
	 * @param preferences
	 */
	protected void bindPreferences(PreferencesService preferences) {
		this.preferenceService = preferences;
	}

	/**
	 * Unbinds the OSGi preferences.
	 * 
	 * @param preferences
	 */
	protected void unbindPreferences(PreferencesService preferences) {
		this.preferenceService = null;
	}
}
