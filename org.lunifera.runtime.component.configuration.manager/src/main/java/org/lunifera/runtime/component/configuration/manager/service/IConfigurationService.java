/*******************************************************************************
 * Copyright (c) 2012 Committers of lunifera.org - Lunifera.org.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Cristiano Gavião - initial API and implementation
 *******************************************************************************/
package org.lunifera.runtime.component.configuration.manager.service;

import java.util.Dictionary;

public interface IConfigurationService {

	static final String CONFIGURATION_INCLUDE_PATTERN_FIELD_NAME = "configurationIncludePattern";

	static final String CONFIGURATION_EXCLUDE_PATTERN_FIELD_NAME = "configurationExcludePattern";

	static final String CONFIGURATION_BASE_DIR_FIELD_NAME = "configurationBasedir";

	public final static String SERVICE_FACTORYPID = "service.factoryPid";

	/**
	 * Can be used in the manifest.mf to mark a bundle as a config bundle. The
	 * string value following the header will be interpreted as the folder that
	 * contains the config files.
	 * <p>
	 * <code>Lunifera-Config: configs</code> points to the configs folder under
	 * root.
	 */
	public static final String MANIFESTHEADER__CONFIG = "Lunifera-Config";

	/**
	 * This property key will be used to add the passed externalPid in
	 * {@link #initializeFactoryConfigurationStore(String, String, Dictionary)
	 * initializeFactoryConfigurationStore} to the properties of the created
	 * configuration. It can be used to filter the service by its name.
	 */
	public final static String EXTERNAL_PID = "lunifera.externalPid";

	/**
	 * if not exists, it creates and initializes with the informed properties a
	 * configuration store for the informed PID. If the store for the PID
	 * already exists it will be released and initialized again.
	 * 
	 * @param pid
	 */
	void initializeConfigurationStore(String pid,
			Dictionary<String, Object> properties);

	/**
	 * If not exists, it creates and initializes with the informed properties a
	 * configuration store. If a configuration was already created for the given
	 * externalPid, it will updated.
	 * <p>
	 * Attention:<br>
	 * The passed externalPid is not the pid of the created configuration. The
	 * use of an externalPid is required since the configuration admin
	 * automatically creates a new pid for each configuration. The externalPid
	 * will be added to the properties of the prepared configuration with key
	 * {@link #EXTERNAL_PID}. And the generated pid will be returned.
	 * 
	 * @param factoryPid
	 *            The pid of the factory
	 * @param externalPid
	 *            The externalPid. (Not the pid!)
	 * @param properties
	 *            The passed properties
	 * @return pid the pid of the created configuration
	 */
	String initializeFactoryConfigurationStore(String factoryPid,
			String externalPid, Dictionary<String, Object> properties);

	Dictionary<String, Object> getProperties(String pid);

	String getProperty(String pid, String propertyName);

	void deleteProperties(String pid);

	void putProperty(String pid, String propertyName, Object value);

	void putProperties(String pid, Dictionary<String, Object> properties);

	Dictionary<String, Object> getFactoryProperties(String factoryPid,
			String pid);

	String getFactoryProperty(String factoryPid, String pid, String propertyName);

	void deleteFactoryProperties(String factoryPid, String pid);

	void putFactoryProperty(String factoryPid, String pid, String propertyName,
			Object value);

	void putFactoryProperties(String factoryPid, String pid,
			Dictionary<String, Object> properties);

	void displayFactoryConfiguration(String factoryPid);
}
