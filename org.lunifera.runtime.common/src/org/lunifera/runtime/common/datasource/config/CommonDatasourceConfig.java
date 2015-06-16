/**
 * Copyright (c) 2011 - 2015, Bernhard Edler (Wien)
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *         Bernhard Edler - Initial implementation
 */
package org.lunifera.runtime.common.datasource.config;

import java.util.Arrays;
import java.util.HashMap;

import org.lunifera.runtime.common.util.OSGiUtil;

public abstract class CommonDatasourceConfig {
	public static String DRIVER_NAME = "";
	private static String EMPTY_STRING = "";
	protected static String OSGI_DRIVER_NAME = "osgi.jdbc.driver.name";

	protected HashMap<String, String> properties;

	public CommonDatasourceConfig() {
		properties = new HashMap<String, String>();
	}

	public HashMap<String, String> getProperties() {
		return properties;
	}

	public void setProperties(HashMap<String, String> properties) {
		this.properties = properties;
	}

	protected void setValue(String key, String value) {
		if (properties.containsKey(key)) {
			properties.remove(key);
			properties.put(key, value);
		} else {
			properties.put(key, value);
		}
	}

	protected String getValue(String key) {
		return properties.containsKey(key) ? properties.get(key) : EMPTY_STRING;
	}

	protected HashMap<String, String> filterProperties(String[] keySet) {
		for (String key : properties.keySet()) {
			if (!Arrays.asList(keySet).contains(key)) {
				properties.remove(key);
			}
		}
		return properties;
	};

	public static String[] getPropertyKeysForDriver(String driverName) {
		if (EmbeddedDerbyDatasourceConfig.DRIVER_NAME.equals(driverName)) {
			return OSGiUtil
					.getEnumValues(EmbeddedDerbyDatasourceConfig.Keys.class);
		} else if (ClientDerbyDatasourceConfig.DRIVER_NAME.equals(driverName)) {
			return OSGiUtil
					.getEnumValues(ClientDerbyDatasourceConfig.Keys.class);
		} else if (MySQLDatasourceConfig.DRIVER_NAME.equals(driverName)) {
			return OSGiUtil
					.getEnumValues(MySQLDatasourceConfig.Keys.class);
		} else {
			return null;
		}
	}
}
