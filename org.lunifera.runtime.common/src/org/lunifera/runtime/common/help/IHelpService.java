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

import java.util.Map;

public interface IHelpService {

	/**
	 * Is used in the properties to define the helpType.
	 */
	public static final String PROP__HELP_TYPE = "helpType";

	/**
	 * Is used in the properties to pass any kind of data.
	 */
	public static final String PROP__DATA = "data";

	/**
	 * Is used in the properties to define the id of the given data. The
	 * implementation is responsible to provide a mechanism to convert the given
	 * id to the underlying logic.
	 */
	public static final String PROP__ID = "id";

	/**
	 * The type of the help is a task help.
	 */
	public static final String TYPE__TASK = "task";

	/**
	 * Returns the help content in html format.
	 * 
	 * @param properties
	 * @return
	 */
	String getHtml(Map<String, Object> request);

}
