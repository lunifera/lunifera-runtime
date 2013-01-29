/*******************************************************************************
 * Copyright (c) 2012, 2013 Committers of lunifera.org - Lunifera.org.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Cristiano Gavião - initial API and implementation
 *******************************************************************************/
package org.lunifera.runtime.component.logging.over.slf4j;

import org.eclipse.equinox.log.Logger;
import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceReference;
import org.osgi.service.log.LogService;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class LoggerWrapper implements Logger {

	/**
	 * Bundlenames in LogEntries can be surrounded by a Prefix and Postfix per
	 * ex. ...the log... - [B:MyBundleName]
	 */
	public static final String BUNDLE_PREFIX = " [B:"; //$NON-NLS-1$

	/**
	 * Bundlenames in LogEntries can be surrounded by a Prefix and Postfix per
	 * ex. ...the log... - [B:MyBundleName]
	 */
	public static final String BUNDLE_POSTFIX = "] "; //$NON-NLS-1$

	/**
	 * Service-references in LogEntries can be surrounded by a Prefix and
	 * Postfix per ex. ...the log... - [S:MyBundleName]
	 */
	public static final String SERVICE_PREFIX = " [S:"; //$NON-NLS-1$

	/**
	 * Service-references in LogEntries can be surrounded by a Prefix and
	 * Postfix per ex. ...the log... - [S:MyBundleName]
	 */
	public static final String SERVICE_POSTFIX = "] "; //$NON-NLS-1$

	private final org.slf4j.Logger delegate;

	public LoggerWrapper(ExtendedLogServiceImpl extendedLogServiceImpl,
			String name) {
		delegate = LoggerFactory.getLogger(name);
	}

	@Override
	public void log(int level, String message) {
		callSlf4jLog(level, message, null);
	}

	@Override
	public void log(int level, String message, Throwable exception) {
		String message2 = "";
		callSlf4jLog(level, message2, null, exception);
	}

	@Override
	public void log(ServiceReference<?> sr, int level, String message) {
		String message2 = "";
		callSlf4jLog(level, message2, null, sr);
	}

	@Override
	public void log(ServiceReference<?> sr, int level, String message,
			Throwable exception) {
		String message2 = "";
		callSlf4jLog(level, message2, null, exception);
	}

	@Override
	public void log(Object context, int level, String message) {
		Marker marker = null;
		if (context != null && context instanceof Bundle){
			marker = MarkerFactory.getMarker(((Bundle)context).getSymbolicName());
		}
		callSlf4jLog(level, message, marker, context);
	}

	@Override
	public void log(Object context, int level, String message,
			Throwable exception) {
		String message2 = "";
		callSlf4jLog(level, message2, null, exception);
	}

	private void callSlf4jLog(int level, String message, Marker marker,
			Throwable e) {
		switch (level) {
		case LogService.LOG_DEBUG:
			delegate.debug(marker, message, e);
			break;
		case LogService.LOG_ERROR:
			delegate.error(marker, message, e);
			break;
		case LogService.LOG_WARNING:
			delegate.warn(marker, message, e);
			break;
		default:
			delegate.info(marker, message, e);
			break;
		}
	}

	private void callSlf4jLog(int level, String message, Marker marker,
			Object... objects) {

		switch (level) {
		case LogService.LOG_DEBUG:
			delegate.debug(marker, message, objects);
			break;
		case LogService.LOG_ERROR:
			delegate.error(marker, message, objects);
			break;
		case LogService.LOG_WARNING:
			delegate.warn(marker, message, objects);
			break;
		default:
			delegate.info(marker, message, objects);
			break;
		}
	}

	@Override
	public boolean isLoggable(int level) {
		switch (level) {
		case LogService.LOG_DEBUG:
			return delegate.isDebugEnabled();
		case LogService.LOG_ERROR:
			return delegate.isErrorEnabled();
		case LogService.LOG_WARNING:
			return delegate.isWarnEnabled();
		default:
			return delegate.isInfoEnabled();
		}
	}

	@Override
	public String getName() {
		return delegate.getName();
	}

}
