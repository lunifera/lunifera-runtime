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

public interface IConfigurationService  {

	static final String CONFIGURATION_INCLUDE_PATTERN_FIELD_NAME = "configurationIncludePattern";

	static final String CONFIGURATION_EXCLUDE_PATTERN_FIELD_NAME = "configurationExcludePattern";

	static final String CONFIGURATION_BASE_DIR_FIELD_NAME = "configurationBasedir";

	public final static String	SERVICE_FACTORYPID		= "service.factoryPid";

	/**
	 * if not exists, it creates and initializes with the informed properties a configuration store for the informed
	 * PID. If the store for the PID already exists it will be released and
	 * initialized again.
	 * 
	 * @param pid
	 */
	void initializeConfigurationStore(String pid, Dictionary<String, Object> properties);

	/**
	 * if not exists, it creates and initializes with the informed properties a configuration store for the informed
	 * PID. If the store for the PID already exists it will be released and
	 * initialized again.
	 * 
	 * @param pid
	 */
	void initializeFactoryConfigurationStore(String factoryPid, String pid, Dictionary<String, Object> properties);

	Dictionary<String, Object> getProperties(String pid);

	String getProperty(String pid, String propertyName);

	void deleteProperties(String pid);

	void putProperty(String pid, String propertyName, Object value);

	void putProperties(String pid, Dictionary<String, Object> properties);

	Dictionary<String, Object> getFactoryProperties(String factoryPid, String pid);
	
	String getFactoryProperty(String factoryPid, String pid, String propertyName);
	
	void deleteFactoryProperties(String factoryPid, String pid);
	
	void putFactoryProperty(String factoryPid, String pid, String propertyName, Object value);
	
	void putFactoryProperties(String factoryPid, String pid, Dictionary<String, Object> properties);

	void displayFactoryConfiguration(String factoryPid);
}
