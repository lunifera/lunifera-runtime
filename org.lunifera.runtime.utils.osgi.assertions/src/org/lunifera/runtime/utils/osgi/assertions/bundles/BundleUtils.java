/*******************************************************************************
 * Copyright (c) 2012 Committers of lunifera.org - Lunifera.org.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Cristiano Gavião - initial API and implementation
 *******************************************************************************/

package org.lunifera.runtime.utils.osgi.assertions.bundles;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Version;

/**
 * OSGi Bundles utilities class
 * 
 * @author dpishchukhin
 * @version 1.0
 * @see org.osgi.framework.Bundle
 * @see org.osgi.service.packageadmin.PackageAdmin
 * @see org.osgi.framework.BundleContext
 */
public class BundleUtils {
	
    private static boolean showLog;


	/**
	 * Utility class. Only static methods are available.
	 */
	private BundleUtils() {
	}

	/**
	 * Find bundle by ID
	 * 
	 * @param bc
	 *            BundleContext
	 * @param bundleId
	 *            bundle id
	 * @return Bundle instance or <code>null</code>
	 * 
	 * @throws NullPointerException
	 *             If <code>bc</code> is <code>null</code>
	 */
	public static Bundle findBundle(BundleContext bc, long bundleId) {
		return bc.getBundle(bundleId);
	}

	/**
	 * Find bundle by SymbolicName
	 * 
	 * @param bc
	 *            BundleContext
	 * @param symbolicName
	 *            symbolicName
	 * @return Bundle instance or <code>null</code>
	 * 
	 * @throws NullPointerException
	 *             If <code>bc</code> or <code>symbolicName</code> are
	 *             <code>null</code>
	 */
	public static Bundle findBundle(BundleContext bc, String symbolicName) {
		return findBundle(bc, symbolicName, null);
	}

	/**
	 * Find bundle by SymbolicName and Version
	 * 
	 * @param bc
	 *            BundleContext
	 * @param symbolicName
	 *            symbolicName
	 * @param version
	 *            version
	 * @return Bundle instance or <code>null</code>
	 * 
	 * @throws NullPointerException
	 *             If <code>bc</code> or <code>symbolicName</code> are
	 *             <code>null</code>
	 */
	public static Bundle findBundle(BundleContext bc, String symbolicName,
			Version version) {
		Bundle[] bundles = bc.getBundles();
		for (Bundle b : bundles) {
			if (isShowLog())
				System.out.println("Bundle is " + b.getBundleId() + ": "
						+ b.getSymbolicName());
			if (version != null) {
				if (b.getSymbolicName().equals(symbolicName)
						&& b.getVersion().equals(version)) {
					return b;
				}
			} else {
				if (b.getSymbolicName().equals(symbolicName)) {
					return b;
				}
			}
		}
		return null;
	}

	public static boolean isShowLog() {
		return showLog;
	}

	public static void setShowLog(boolean showLog) {
		BundleUtils.showLog = showLog;
	}
}
