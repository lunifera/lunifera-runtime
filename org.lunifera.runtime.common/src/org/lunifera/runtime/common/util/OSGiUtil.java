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
package org.lunifera.runtime.common.util;

import java.util.Arrays;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Properties;

@SuppressWarnings("all")
public class OSGiUtil {

	public static Dictionary<String, Object> convertHashMapToDictionary(
			HashMap<?, ?> input) {
		Dictionary<String, Object> dict = new Hashtable<String, Object>();
		for (Object key : input.keySet()) {
			dict.put(key.toString(), input.get(key));
		}
		return dict;
	}

	public static Properties convertHashMapToProperties(HashMap<?, ?> input) {
		Properties props = new Properties();
		for (Object key : input.keySet()) {
			props.put(key, input.get(key));
		}
		return props;
	}

	public static HashMap<String, String> convertDictionaryToHashMap(
			Dictionary<?, ?> input) {
		HashMap<String, String> map = new HashMap<String, String>();
		Enumeration<String> iter = (Enumeration<String>) input.keys();
		while (iter.hasMoreElements()) {
			String key = (String) iter.nextElement();
			map.put(key, input.get(key).toString());
		}
		return map;
	}

	public static Properties convertDictionaryToProperties(
			Dictionary<?, ?> input) {
		Properties props = new Properties();
		Enumeration<String> iter = (Enumeration<String>) input.keys();
		while (iter.hasMoreElements()) {
			String key = (String) iter.nextElement();
			props.setProperty(key, input.get(key).toString());
		}
		return props;
	}

	public static String[] getEnumValues(Class<? extends Enum<?>> e) {	
		return Arrays.toString(e.getEnumConstants()).replaceAll("^.|.$", "")
				.split(", ");
	}

	public static HashMap<String, String> filterProperties(
			HashMap<String, String> properties, String[] keySet) {
		for (String key : properties.keySet()) {
			if (!Arrays.asList(keySet).contains(key)) {
				properties.remove(key);
			}
		}
		return properties;
	};

	public static Properties filterProperties(Properties properties,
			String[] keySet) {
		Enumeration<Object> iter = (Enumeration<Object>) properties.keys();
		while (iter.hasMoreElements()) {
			String key = (String) iter.nextElement();
			if (!Arrays.asList(keySet).contains(key)) {
				properties.remove(key);
			}
		}
		return properties;
	};
}
