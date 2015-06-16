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
package org.lunifera.runtime.common.metric;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeLogger {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(TimeLogger.class);

	private final Class<?> clazz;
	private final long start;

	public static TimeLogger start(Class<?> clazz) {
		return new TimeLogger(clazz);
	}

	private TimeLogger(Class<?> clazz) {
		this.clazz = clazz;
		start = new Date().getTime();
	}

	/**
	 * 
	 * @param message
	 */
	public void stop(String message) {
		long stop = new Date().getTime();
		LOGGER.info(clazz.getName() + ": " + message + " " + (stop - start)
				+ "ms");
	}

}
