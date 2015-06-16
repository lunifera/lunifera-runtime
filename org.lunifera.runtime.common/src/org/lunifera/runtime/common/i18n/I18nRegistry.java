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
package org.lunifera.runtime.common.i18n;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.map.LRUMap;
import org.eclipse.core.runtime.FileLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A registry that may be used to collect and access I18n translations based on
 * URLs. The target of URLs need to be property files with the name pattern
 * {file}_{language}-{country} (myI18n_de-DE). If no language and country are
 * specified, the default locale is used.
 */
public class I18nRegistry {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(I18nRegistry.class);

	private final LRUMap fastAccess = new LRUMap(1000);

	private final Map<Locale, List<ResourceDescription>> cache = new HashMap<Locale, List<ResourceDescription>>();

	public I18nRegistry() {

	}

	/**
	 * Tries to find the translation for the given locale and key.
	 * 
	 * @param locale
	 * @param key
	 * @return
	 */
	public String findTranslation(Locale locale, String key) {
		String result = tryFastAccess(locale, key);
		if (result != null) {
			return result;
		}

		AccessPath accessPath = computeBestMatchAccessPath(locale, key);
		Translation translation = accessPath.getTranslation();
		if (translation == null) {
			return "";
		}

		return putFastAccess(translation.getLocale(), translation.getI18nKey(),
				translation.getI18nValue());
	}

	/**
	 * Try to find the translation in the fast access map.
	 * 
	 * @param locale
	 * @param key
	 * @return
	 */
	private String tryFastAccess(Locale locale, String key) {
		List<Locale> computedLocales = computeLocales(locale);
		for (Locale temp : computedLocales) {
			String fastAccessKey = createFastAccessKey(temp, key);
			String result = (String) fastAccess.get(fastAccessKey);
			if (result != null) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Try to find the translation in the fast access map.
	 * 
	 * @param locale
	 * @param key
	 * @return
	 */
	private String putFastAccess(Locale locale, String key, String translation) {
		fastAccess.put(createFastAccessKey(locale, key), translation);
		return translation;
	}

	/**
	 * Creates the key for the fast access map access.
	 * 
	 * @param temp
	 * @param key
	 * @return
	 */
	private String createFastAccessKey(Locale temp, String key) {
		return temp.toLanguageTag() + ":" + key;
	}

	/**
	 * Computes the access url.
	 * <p>
	 * Following order will be used:
	 * <ol>
	 * <li>Use given locale</li>
	 * <li>Create more specific locale and repeat until default locale is
	 * reached.</li>
	 * </ul> </li>
	 * </ol>
	 * 
	 * @param locale
	 * @param packageName
	 * @param key
	 * @return
	 */
	private AccessPath computeAccessPath(Locale locale, String key) {

		Matcher valueMatcher = null;
		if (key != null && !key.equals("")) {
			key = Pattern.quote(key);
			Pattern valuePattern = Pattern.compile(key.toString());
			valueMatcher = valuePattern.matcher("");
			valueMatcher.reset();
		}

		AccessPath url = new AccessPath();
		int prio = 0;

		List<Locale> computedLocales = computeLocales(locale);

		for (Locale computedLocale : computedLocales) {
			Accessor accessor = new Accessor(computedLocale, ++prio, key,
					valueMatcher);
			url.addAccessor(accessor);
		}

		return url;
	}

	/**
	 * Computes the access url to find the best matching element. See
	 * {@link #computeAccessPath(IProject, Locale, String, String)}.
	 *
	 * @param locale
	 * @param packageName
	 * @param key
	 * @return
	 */
	private AccessPath computeBestMatchAccessPath(Locale locale, String key) {
		return computeAccessPath(locale, key);
	}

	/**
	 * Computes all locales that should be added to AccessPath
	 * 
	 * @param locale
	 * @return
	 */
	private List<Locale> computeLocales(Locale locale) {
		List<Locale> locales = new LinkedList<Locale>();

		// Add first locale
		locales.add(locale);

		Locale temp = locale;
		while (true) {
			String tag = temp.toLanguageTag();
			String[] segments = tag.split("-");
			if (segments.length > 1) {
				StringBuilder builder = new StringBuilder();
				for (int i = 0; i < segments.length - 1; i++) {
					if (builder.length() != 0) {
						builder.append("-");
					}
					builder.append(segments[i]);
				}
				Locale moreGeneral = Locale.forLanguageTag(builder.toString());
				locales.add(moreGeneral);
				temp = moreGeneral;
			} else {
				break;
			}
		}

		locales.add(new Locale(""));

		return locales;
	}

	private boolean isValid(String result) {
		return result != null && !result.equals("");
	}

	/**
	 * Add a new resource to the cache.
	 * 
	 * @param description
	 */
	public void addResource(URL url) {

		Properties properties = new Properties();
		try {
			properties.load(url.openStream());
		} catch (IOException e) {
			LOGGER.error("{}", e);
			return;
		}

		Locale locale = getLocale(url);
		if (locale == null) {
			LOGGER.error("Could not determine locale for " + url.toString());
			return;
		}

		ResourceDescription description = new ResourceDescription(locale, url,
				properties);
		addResource(description);
	}

	private Locale getLocale(URL url) {
		File file = null;
		try {
			file = new File(FileLocator.toFileURL(url).toURI());
		} catch (URISyntaxException e) {
			LOGGER.error("{}", e);
			return null;
		} catch (IOException e) {
			LOGGER.error("{}", e);
			return null;
		}

		String[] tokens = file.getName().replace(".properties", "").split("_");
		StringBuilder builder = new StringBuilder();
		if (tokens.length >= 2) {
			for (int i = 1; i < tokens.length; i++) {
				if (builder.length() > 0) {
					builder.append("-");
				}
				builder.append(tokens[i]);
			}
		}
		Locale locale = Locale.forLanguageTag(builder.toString());
		return locale;
	}

	/**
	 * Add a new resource to the cache.
	 * 
	 * @param description
	 */
	public void addResource(ResourceDescription description) {
		synchronized (cache) {
			if (!cache.containsKey(description.getLocale())) {
				cache.put(description.getLocale(),
						new ArrayList<ResourceDescription>());
			}

			List<ResourceDescription> descs = cache
					.get(description.getLocale());
			if (!descs.contains(description)) {
				descs.add(description);
			}
		}

		// clear the fast access cache
		fastAccess.clear();
	}

	private List<ResourceDescription> getResourceDescriptions(Locale locale) {
		if (cache.containsKey(locale)) {
			return new ArrayList<I18nRegistry.ResourceDescription>(
					cache.get(locale));
		} else {
			return Collections.emptyList();
		}
	}

	/**
	 * Removes the given translations.
	 * 
	 * @param locale
	 * @param url
	 */
	public void removeResource(Locale locale, URL url) {
		if (!cache.containsKey(locale)) {
			return;
		}

		// clear the fast access cache
		fastAccess.clear();

		// remove the resource from the builder
		synchronized (cache) {
			List<ResourceDescription> descs = cache.get(locale);
			for (Iterator<ResourceDescription> iterator = descs.iterator(); iterator
					.hasNext();) {
				ResourceDescription desc = iterator.next();
				if (desc.getURL().equals(url)) {
					iterator.remove();
				}
			}
		}
	}

	/**
	 * Removes the translations with the given URL.
	 * 
	 * @param url
	 */
	public void removeResource(URL url) {
		Locale locale = getLocale(url);
		if (locale == null) {
			LOGGER.error("Could not determine locale for " + url.toString());
			return;
		}
		removeResource(locale, url);

	}

	/**
	 * Defines how the registry should be searched. For instance the ordering of
	 * locales...
	 */
	private static class AccessPath {
		private List<Accessor> accessors = new LinkedList<I18nRegistry.Accessor>();

		public AccessPath() {
		}

		public void addAccessor(Accessor accessor) {
			accessors.add(accessor);
		}

		/**
		 * Returns the translation. The key matches the entire key for an i18n
		 * record.
		 * 
		 * @return
		 */
		public Translation getTranslation() {
			for (Accessor accessor : accessors) {
				Translation result = accessor.getTranslation();
				if (result != null) {
					return result;
				}
			}

			return null;
		}
	}

	/**
	 * This class will access the registry.
	 */
	private class Accessor {

		private final Locale locale;
		private final Matcher matcher;
		@SuppressWarnings("unused")
		private final String key;
		private final int prio;

		public Accessor(Locale locale, int prio, String key, Matcher matcher) {
			super();
			this.locale = locale;
			this.key = key;
			this.matcher = matcher;
			this.prio = prio;
		}

		/**
		 * Returns the first translation found. The matcher must match the given
		 * key.
		 * 
		 * @return
		 */
		public Translation getTranslation() {
			List<ResourceDescription> descs = getResourceDescriptions(locale);
			for (ResourceDescription desc : descs) {
				for (Map.Entry<Object, Object> entry : desc.getProperties()
						.entrySet()) {
					if (matcher.reset(((String) entry.getKey())).matches()) {
						if (isValid((String) entry.getValue())) {
							return new Translation((String) entry.getKey(),
									(String) entry.getValue(), desc, prio);
						}
					}
				}
			}
			return null;
		}
	}

	/**
	 * A resource is a file containing all translations for a file.
	 */
	public static class ResourceDescription {
		private final Locale locale;
		private final URL url;
		private final Properties properties;

		public ResourceDescription(Locale locale, URL url, Properties properties) {
			this.locale = locale;
			this.url = url;
			this.properties = properties;
		}

		public Locale getLocale() {
			return locale;
		}

		public URL getURL() {
			return url;
		}

		public Properties getProperties() {
			return properties;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((url == null) ? 0 : url.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ResourceDescription other = (ResourceDescription) obj;
			if (url == null) {
				if (other.url != null)
					return false;
			} else if (!url.equals(other.url))
				return false;
			return true;
		}
	}

	public static class Translation {

		private final String i18nKey;
		private final String i18nValue;
		private final ResourceDescription resourceDescription;
		@SuppressWarnings("unused")
		private final int priority;

		public Translation(String i18nKey, String i18nValue,
				ResourceDescription resourceDescription, int priority) {
			super();
			this.i18nKey = i18nKey;
			this.i18nValue = i18nValue;
			this.resourceDescription = resourceDescription;
			this.priority = priority;
		}

		/**
		 * Returns the i18nKey.
		 * 
		 * @return
		 */
		public String getI18nKey() {
			return i18nKey;
		}

		/**
		 * Returns the i18nValue.
		 * 
		 * @return
		 */
		public String getI18nValue() {
			return i18nValue;
		}

		/**
		 * Returns the locale where key and value had been found.
		 * 
		 * @return
		 */
		public Locale getLocale() {
			return resourceDescription.getLocale();
		}

		/**
		 * Returns the resource description, where the i18n entry was contained.
		 * 
		 * @return
		 */
		public ResourceDescription getResourceDescription() {
			return resourceDescription;
		}

	}
}
