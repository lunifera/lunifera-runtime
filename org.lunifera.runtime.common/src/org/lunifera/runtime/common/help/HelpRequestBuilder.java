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
package org.lunifera.runtime.common.help;

import java.util.HashMap;
import java.util.Map;

public class HelpRequestBuilder {

	private Map<String, Object> properties = new HashMap<String, Object>();

	private HelpRequestBuilder() {

	}

	public static final HelpRequestBuilder newRequest() {
		return new HelpRequestBuilder();
	}

	/**
	 * The type of the help.
	 * 
	 * @param requestType
	 * @return
	 */
	public HelpRequestBuilder type(String requestType) {
		properties.put(IHelpService.TYPE__TASK, requestType);
		return this;
	}

	/**
	 * An id which identifies the help for the requestType.
	 * 
	 * @param requestType
	 * @return
	 */
	public HelpRequestBuilder id(Object id) {
		properties.put(IHelpService.PROP__ID, id);
		return this;
	}

	/**
	 * Any kind of data.
	 * 
	 * @param data
	 * @return
	 */
	public HelpRequestBuilder data(Object data) {
		properties.put(IHelpService.PROP__DATA, data);
		return this;
	}

	/**
	 * Pass any property.
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public HelpRequestBuilder property(String key, Object value) {
		properties.put(key, value);
		return this;
	}

	/**
	 * Returns the resulting request parameters.
	 * 
	 * @return
	 */
	public Map<String, Object> toRequest() {
		return new HashMap<String, Object>(properties);
	}

}
