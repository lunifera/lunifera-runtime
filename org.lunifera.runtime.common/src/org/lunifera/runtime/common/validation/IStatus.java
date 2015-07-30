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

/**
 * The status defines the current state of a process. It may be used for
 * different usecases. For instance for validations, to determine veto before
 * calling a process, to visualize thrown exceptions,... It is a general
 * implementation.
 */
public interface IStatus {

	/**
	 * A constant for ok status.
	 */
	public static final IStatus OK = Status.createOKStatus();

	/**
	 * A constant for cancel status.
	 */
	public static final IStatus CANCEL = Status.createCancelStatus();
	
	/**
	 * A constant for error status.
	 */
	public static final IStatus ERROR = Status.createErrorStatus();

	/**
	 * Returns the id of the status. It is prepared by the pattern
	 * <code>{bundleSymbolicName}.{code}. So every bundle may specify its own codes.
	 * 
	 * @return
	 */
	String getId();

	/**
	 * Returns the status code. The code specifies the unique id of a status
	 * message in a bundle.
	 * 
	 * @return
	 */
	String getCode();

	/**
	 * Returns symbolic name of the bundle that has created the status.
	 * 
	 * @return
	 */
	String getBundleSymblicName();

	/**
	 * Returns the severity of the status.
	 * 
	 * @return
	 */
	Severity getSeverity();

	/**
	 * Returns the message of the status. May be <code>null</code>.
	 * 
	 * @return
	 */
	String getMessage();

	/**
	 * Returns the exception of the status. May be <code>null</code>.
	 * 
	 * @return
	 */
	Exception getException();

	/**
	 * Returns true, if the status is {@link Severity#OK} or
	 * {@link Severity#INFO}.
	 * 
	 * @return
	 */
	boolean isOK();

	/**
	 * Returns true, if the status is {@link Severity#INFO}.
	 * 
	 * @return
	 */
	boolean isInfo();

	/**
	 * Returns true, if the status is {@link Severity#WARNING}.
	 * 
	 * @return
	 */
	boolean isWarning();

	/**
	 * Returns true, if the status is {@link Severity#ERROR}.
	 * 
	 * @return
	 */
	boolean isError();

	/**
	 * Returns true, if the status is {@link Severity#CANCEL}. May be used to
	 * detect a "veto" for a process.
	 * 
	 * @return
	 */
	boolean isCancel();

	/**
	 * The severity of the status.
	 */
	enum Severity {
		OK, INFO, WARNING, ERROR, CANCEL;
	}
}
