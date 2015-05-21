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
 *     06/30/2010-2.1.1 Michael O'Brien 
 *       - 316513: Enable JMX MBean functionality for JBoss, Glassfish and WebSphere in addition to WebLogic
 *       Move JMX MBean generic registration code up from specific platforms
 *       see <link>http://wiki.eclipse.org/EclipseLink/DesignDocs/316513</link>        
 ******************************************************************************/
package org.lunifera.runtime.eclipselink.core.fragment;

import org.eclipse.persistence.platform.server.ServerPlatformBase;
import org.eclipse.persistence.sessions.DatabaseSession;

/**
 * PUBLIC:
 *
 * This is the concrete subclass responsible for representing JBoss-specific
 * server behavior.
 *
 * This platform overrides:
 *
 * getExternalTransactionControllerClass(): to use an JBoss-specific controller
 * class
 *
 */
public class LuniferaPlatform extends ServerPlatformBase {

	/**
	 * INTERNAL: Default Constructor: All behavior for the default constructor
	 * is inherited
	 */
	public LuniferaPlatform(DatabaseSession newDatabaseSession) {
		super(newDatabaseSession);
	}

	@Override
	public boolean isRuntimeServicesEnabledDefault() {
		return false;
	}

	/**
	 * INTERNAL: getExternalTransactionControllerClass(): Answer the class of
	 * external transaction controller to use for JBoss. This is read-only.
	 *
	 * @return Class externalTransactionControllerClass
	 *
	 * @see org.eclipse.persistence.transaction.JTATransactionController
	 * @see org.eclipse.persistence.platform.server.ServerPlatformBase#isJTAEnabled()
	 * @see org.eclipse.persistence.platform.server.ServerPlatformBase#disableJTA()
	 * @see org.eclipse.persistence.platform.server.ServerPlatformBase#initializeExternalTransactionController()
	 */
	public Class<?> getExternalTransactionControllerClass() {
		if (externalTransactionControllerClass == null) {
			externalTransactionControllerClass = LuniferaTransactionController.class;
		}
		return externalTransactionControllerClass;
	}

}
