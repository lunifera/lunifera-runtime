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

package org.lunifera.runtime.common.validation;

import org.osgi.framework.FrameworkUtil;

public class Status implements IStatus {

	private String code;
	private String bundleSymbolicName;
	private Severity severity;
	private String message;
	private Exception exception;

	public Status() {

	}

	/**
	 * Creates an OK status.
	 * 
	 * @return
	 */
	public static IStatus createOKStatus() {
		Status status = new Status();
		status.severity = Severity.OK;
		return status;
	}

	/**
	 * Creates an CANCEL status.
	 * 
	 * @return
	 */
	public static IStatus createCancelStatus() {
		Status status = new Status();
		status.severity = Severity.CANCEL;
		return status;
	}

	/**
	 * Creates an ERROR status.
	 * 
	 * @return
	 */
	public static IStatus createErrorStatus() {
		Status status = new Status();
		status.severity = Severity.ERROR;
		return status;
	}

	/**
	 * Creates an ERROR status.
	 * 
	 * @param e
	 *            - the exception
	 * 
	 * @return
	 */
	public static IStatus createErrorStatus(Exception e) {
		Status status = new Status();
		status.severity = Severity.ERROR;
		status.exception = e;
		return status;
	}

	/**
	 * Creates a status for the given parameters.
	 * 
	 * @param code
	 *            - the unique message code (is unique in a bundle)
	 * @param clazz
	 *            - the class that is contained in the bundle, that specifies
	 *            the code
	 * @param severity
	 *            - the serverity of the status
	 * @param message
	 *            - the message of the status
	 * @return
	 */
	public static IStatus createStatus(String code, Class<?> clazz,
			Severity severity, String message) {
		Status status = new Status();
		status.code = code;
		status.bundleSymbolicName = getBundleSymbolicName(clazz);
		status.severity = severity;
		status.message = message;
		return status;
	}

	/**
	 * 
	 * @param code
	 *            - the unique message code (is unique in a bundle)
	 * @param clazz
	 *            - the class that is contained in the bundle, that specifies
	 *            the code
	 * @param severity
	 *            - the serverity of the status
	 * @param message
	 *            - the message of the status
	 * @param exception
	 *            - the thrown exception
	 * @return
	 */
	public static IStatus createStatus(String code, Class<?> clazz,
			Severity severity, String message, Exception exception) {
		Status status = new Status();
		status.code = code;
		status.bundleSymbolicName = getBundleSymbolicName(clazz);
		status.severity = severity;
		status.message = message;
		status.exception = exception;
		return status;
	}

	/**
	 * @param code
	 *            - the unique message code (is unique in a bundle)
	 * @param clazz
	 *            - the class that is contained in the bundle, that specifies
	 *            the code
	 * @param severity
	 *            - the serverity of the status
	 * @param exception
	 *            - the thrown exception
	 * @return
	 */
	public static IStatus createStatus(String code, Class<?> clazz,
			Severity severity, Exception exception) {
		Status status = new Status();
		status.code = code;
		status.bundleSymbolicName = getBundleSymbolicName(clazz);
		status.severity = severity;
		status.exception = exception;
		return status;
	}

	/**
	 * Returns the bundle symbolic name for the given class.
	 * 
	 * @param clazz
	 * @return
	 */
	private static String getBundleSymbolicName(Class<?> clazz) {
		if (clazz == null || FrameworkUtil.getBundle(clazz) == null) {
			return "no bundle available";
		}
		return FrameworkUtil.getBundle(clazz).getSymbolicName();
	}

	@Override
	public String getId() {
		return String.format("%s.%s", getBundleSymblicName(), getCode());
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getBundleSymblicName() {
		return bundleSymbolicName;
	}

	@Override
	public Severity getSeverity() {
		return severity;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public Exception getException() {
		return exception;
	}

	@Override
	public boolean isOK() {
		return severity == Severity.OK || severity == Severity.INFO;
	}

	@Override
	public boolean isInfo() {
		return severity == Severity.INFO;
	}

	@Override
	public boolean isWarning() {
		return severity == Severity.WARNING;
	}

	@Override
	public boolean isError() {
		return severity == Severity.ERROR;
	}

	@Override
	public boolean isCancel() {
		return severity == Severity.CANCEL;
	}

}
