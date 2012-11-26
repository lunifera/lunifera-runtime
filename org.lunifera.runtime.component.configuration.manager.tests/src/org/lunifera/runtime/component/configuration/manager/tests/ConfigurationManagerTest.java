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
package org.lunifera.runtime.component.configuration.manager.tests;

import java.util.Dictionary;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

import org.lunifera.runtime.component.configuration.manager.service.IConfigurationService;
import org.lunifera.runtime.utils.osgi.assertions.bundles.BundleAssert;
import org.lunifera.runtime.utils.osgi.assertions.services.ServiceAssert;
import org.lunifera.runtime.utils.osgi.assertions.services.ServiceUtils;

public class ConfigurationManagerTest {

	private static BundleContext bc;

	@BeforeClass
	public static void init() {
		bc = FrameworkUtil.getBundle(ConfigurationManagerTest.class)
				.getBundleContext();
		ServiceAssert.init(bc);
		BundleAssert.init(bc);
	}

	public void ensureNeedBundlesWasInstalled() {

		BundleAssert.assertBundleAvailable(
				"Assertions bundle is not available",
				"org.lunifera.runtime.utils.osgi.assertions");

		BundleAssert.assertBundleAvailable("DS bundle is not available",
				"org.eclipse.equinox.ds");

		BundleAssert.assertBundleAvailable("Services bundle is not available",
				"org.eclipse.osgi.services");

		BundleAssert.assertBundleAvailable("Log bundle is not available",
				"org.eclipse.equinox.log");

		BundleAssert.assertBundleAvailable("CM bundle is not available",
				"org.eclipse.equinox.cm");

		BundleAssert.assertBundleAvailable(
				"CM Wrapper bundle is not available",
				"org.lunifera.runtime.component.configuration.manager");

		BundleAssert
				.assertBundleAvailable("CM fragment was not installed",
						"org.lunifera.runtime.component.configuration.manager.test.conf");
	}

	@Before
	public void ensureServicesWasRegistered() {

		ensureNeedBundlesWasInstalled();

		// assert CM service is available
		ServiceAssert.assertServiceAvailable("CM was not available",
				"org.osgi.service.cm.ConfigurationAdmin");

		// assert Log service is available
		ServiceAssert.assertServiceAvailable("LogService was not available",
				"org.osgi.service.log.LogService");

		// assert CM wrapper is available
		ServiceAssert
				.assertServiceAvailable(
						"CM Wrapper was not available",
						"org.lunifera.runtime.component.configuration.manager.service.IConfigurationService");
	}

	@Test
	public void ensurePidWasRegistered() {

		IConfigurationService cms = ServiceUtils.getService(bc,
				IConfigurationService.class);
		Dictionary<String, Object> props = cms
				.getProperties("org.eclipse.equinox.http.jetty.config.test");

		Assert.assertNotNull("PID was not registered properly.", props);
		Assert.assertEquals("PID was not registered properly.", "8088",
				props.get("http.port"));
	}

	@Test
	public void ensureFactoryPidWithDefaultPidWasRegistered() {

		IConfigurationService cms = ServiceUtils.getService(bc,
				IConfigurationService.class);
		Dictionary<String, Object> props = cms.getFactoryProperties(
				"org.eclipse.equinox.http.jetty.config", "");

		Assert.assertNotNull("Factory properties is null", props);
		
		Assert.assertTrue(props.size() == 3);

		String actual = cms
				.getFactoryProperty("org.eclipse.equinox.http.jetty.config",
						"", "http.port");
		Assert.assertEquals("Property was not found in CM store.", "8080",
				actual);
	}
}
