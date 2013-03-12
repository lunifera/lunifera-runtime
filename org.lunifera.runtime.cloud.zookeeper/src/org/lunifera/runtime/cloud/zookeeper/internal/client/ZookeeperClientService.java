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
package org.lunifera.runtime.cloud.zookeeper.internal.client;

import java.util.Dictionary;
import java.util.Map;

import org.lunifera.runtime.cloud.zookeeper.IZookeeperClient;
import org.lunifera.runtime.cloud.zookeeper.ZookeeperConstants;
import org.lunifera.runtime.cloud.zookeeper.internal.ZookeeperException;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;
import org.osgi.service.prefs.PreferencesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Manages the ZookeeperService and offers access for configuration admin.
 */
public class ZookeeperClientService implements ManagedService {

	private static final Logger logger = LoggerFactory
			.getLogger(ZookeeperClientService.class);

	private ZookeeperClient service;
	private ComponentContext context;

	private ServiceRegistration<IZookeeperClient> registration;

	private PreferencesService preferenceService;

	/**
	 * Called by OSGi-DS.
	 * 
	 * @param context
	 * @param properties
	 */
	protected void activate(ComponentContext context,
			Map<String, Object> properties) {
		logger.debug("ZookeeperManager started");
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
		logger.debug("ZookeeperManager stopped");
	}

	@Override
	public void updated(Dictionary<String, ?> properties)
			throws ConfigurationException {
		try {
			if (service != null) {
				stopService();

				String connectionString = ZookeeperConstants.DEFAULT__CLIENT_CONNECTION_STRING;
				int sessionTimeout = ZookeeperConstants.DEFAULT__CLIENT_SESSION_TIMEOUT;
				String pString = (String) properties
						.get(ZookeeperConstants.CLIENT_CONNECTION_STRING);
				if (pString != null && !pString.equals("")) {
					connectionString = pString;
				} else if (preferenceService != null) {
					Preferences preferences = preferenceService
							.getSystemPreferences();
					try {
						if (preferences
								.nodeExists(ZookeeperConstants.PREFERENCE_CLIENT_NODE_PATH)) {
							Preferences node = preferences
									.node(ZookeeperConstants.PREFERENCE_CLIENT_NODE_PATH);
							if (node != null) {
								connectionString = node
										.get(ZookeeperConstants.PREFERENCE_KEY__CLIENT_CONNECTION_STRING,
												ZookeeperConstants.DEFAULT__CLIENT_CONNECTION_STRING);
								sessionTimeout = node
										.getInt(ZookeeperConstants.PREFERENCE_KEY__CLIENT_SESSION_TIMEOUT,
												ZookeeperConstants.DEFAULT__CLIENT_SESSION_TIMEOUT);
							}
						}
					} catch (BackingStoreException e) {
						logger.warn("{}", e);
					}
				}

				service.setConnectionString(connectionString);
				service.setSessionTimeout(sessionTimeout);

				startService();
			}
		} catch (ZookeeperException e) {
			throw new ConfigurationException(null, "", e);
		}
	}

	/**
	 * Starts the zookeeper service again.
	 * 
	 * @throws ZookeeperException
	 */
	protected void startService() throws ZookeeperException {
		if (service == null) {
			service = new ZookeeperClient();
		}
		service.start();

		registration = context.getBundleContext().registerService(
				IZookeeperClient.class, service, null);
	}

	/**
	 * Stops the zookeeper service.
	 * 
	 * @throws ZookeeperException
	 */
	protected void stopService() throws ZookeeperException {
		if (service == null) {
			return;
		}

		if (registration != null) {
			registration.unregister();
			registration = null;
		}

		service.stop();
		service = null;
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
