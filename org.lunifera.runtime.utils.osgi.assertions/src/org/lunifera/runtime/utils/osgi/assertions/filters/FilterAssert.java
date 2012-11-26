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

package org.lunifera.runtime.utils.osgi.assertions.filters;

import org.junit.Assert;
import org.lunifera.runtime.utils.osgi.assertions.OSGiAssert;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;


/**
 * A set of OSGi Filter specific assertion methods useful for writing tests.
 * <p/>
 * Before use it should be initialized
 * {@link OSGiAssert#init(org.osgi.framework.BundleContext)}
 *
 * @author dmytro.pishchukhin
 * @version 1.0
 * @see java.lang.AssertionError
 * @see OSGiAssert
 */
public class FilterAssert extends OSGiAssert {
    /**
     * Utility class. Only static methods are available.
     */
    private FilterAssert() {
    }

    /**
     * Asserts that given filter is correct. If it not as expected
     * {@link AssertionError} without a message is thrown
     *
     * @param filter filter
     */
    public static void assertFilterCorrect(String filter) {
        assertFilterCorrect(null, filter);
    }

    /**
     * Asserts that given filter is correct. If it not as expected
     * {@link AssertionError} is thrown with the given message
     *
     * @param message message
     * @param filter  filter
     */
    public static void assertFilterCorrect(String message, String filter) {
        Assert.assertNotNull("Filter is null", filter);
        try {
            FrameworkUtil.createFilter(filter);
        } catch (InvalidSyntaxException e) {
            Assert.fail(message);
        }
    }

    /**
     * Asserts that given filter is incorrect. If it not as expected
     * {@link AssertionError} without a message is thrown
     *
     * @param filter filter
     */
    public static void assertFilterIncorrect(String filter) {
        assertFilterIncorrect(null, filter);
    }

    /**
     * Asserts that given filter is incorrect. If it not as expected
     * {@link AssertionError} is thrown with the given message
     *
     * @param message message
     * @param filter  filter
     */
    public static void assertFilterIncorrect(String message, String filter) {
        Assert.assertNotNull("Filter is null", filter);
        try {
            FrameworkUtil.createFilter(filter);
            Assert.fail(message);
        } catch (InvalidSyntaxException e) {
            // do nothing
        }
    }
}
