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

package org.lunifera.runtime.utils.osgi.assertions;

import org.junit.Assert;
import org.lunifera.runtime.utils.osgi.assertions.bundles.BundleUtils;
import org.osgi.framework.BundleContext;


/**
 * Abstract OSGi Asset class with BundleContext Handling
 *
 * @author dpishchukhin
 * @version 1.0
 * @see java.lang.AssertionError
 * @see org.osgi.framework.BundleContext
 */
public abstract class OSGiAssert {
    /**
     * BundleContext value
     */
    private static BundleContext bc;
    
    /**
     * Initialize all OSGi assertions with BundleContext value
     *
     * @param bc BundleContext value
     * @throws IllegalStateException utility class is already initialized
     */
    public static void init(BundleContext bc) {
        init(bc, false);
    }

    /**
     * Initialize all OSGi assertions with BundleContext value
     *
     * @param bc BundleContext value
     * @throws IllegalStateException utility class is already initialized
     */
    public static void init(BundleContext bc, boolean showLog) {
    	OSGiAssert.bc = bc;
    	BundleUtils.setShowLog(showLog);
    }

    /**
     * Asserts BundleContext before return.
     *
     * @return BundleContext
     */
    protected static BundleContext getBundleContext() {
        Assert.assertNotNull("BundleContext is null", bc);
        return bc;
    }
}
