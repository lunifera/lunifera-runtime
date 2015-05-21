/*******************************************************************************
 * Copyright (c) 1998, 2014 Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0 
 * which accompanies this distribution. 
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at 
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/
package org.lunifera.runtime.eclipselink.core.fragment;

import javax.transaction.TransactionManager;

import org.eclipse.persistence.transaction.JTATransactionController;

/**
 * <p>
 * <b>Purpose</b>: TransactionController implementation for JBoss JTA
 * <p>
 * <b>Description</b>: Implements the required behavior for controlling JTA 1.0
 * transactions in JBoss. The JTA TransactionManager must be set on the
 * instance.
 *
 * @see org.eclipse.persistence.transaction.JTATransactionController
 */
public class LuniferaTransactionController extends JTATransactionController {
	public static final String JNDI_TRANSACTION_MANAGER = "osgi:service/javax.transaction.TransactionManager";

	/**
	 * INTERNAL: Obtain and return the JTA TransactionManager on this platform
	 */
	protected TransactionManager acquireTransactionManager() throws Exception {
		return (TransactionManager) jndiLookup(JNDI_TRANSACTION_MANAGER);
	}
}
