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

import static org.knowhowlab.osgi.testing.assertions.BundleAssert.assertBundleAvailable;
import static org.knowhowlab.osgi.testing.assertions.ServiceAssert.assertServiceAvailable;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.knowhowlab.osgi.testing.assertions.BundleAssert;
import org.knowhowlab.osgi.testing.assertions.ServiceAssert;
import org.knowhowlab.osgi.testing.utils.ServiceUtils;
import org.lunifera.runtime.component.configuration.manager.service.IConfigurationService;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.cm.ManagedServiceFactory;

public class ConfigurationManagerTest {

	private static BundleContext bc;

	@BeforeClass
	public static void init() {
		bc = FrameworkUtil.getBundle(ConfigurationManagerTest.class)
				.getBundleContext();
		ServiceAssert.setDefaultBundleContext(bc);
		BundleAssert.setDefaultBundleContext(bc);
	}

	public void ensureNeedBundlesWasInstalled() {

		assertBundleAvailable("Assertions bundle is not available",
				"org.knowhowlab.osgi.testing.assertions");

		assertBundleAvailable("DS bundle is not available",
				"org.eclipse.equinox.ds");

		assertBundleAvailable("Services bundle is not available",
				"org.eclipse.osgi.services");

		assertBundleAvailable("Log bundle is not available",
				"org.eclipse.equinox.log");

		assertBundleAvailable("CM bundle is not available",
				"org.eclipse.equinox.cm");

		assertBundleAvailable("CM Wrapper bundle is not available",
				"org.lunifera.runtime.component.configuration.manager");

		assertBundleAvailable("CM fragment was not installed",
				"org.lunifera.runtime.component.configuration.manager.test.conf");
	}

	@Before
	public void ensureServicesWasRegistered() {

		ensureNeedBundlesWasInstalled();

		// assert CM service is available
		assertServiceAvailable("CM was not available",
				"org.osgi.service.cm.ConfigurationAdmin");

		// assert Log service is available
		assertServiceAvailable("LogService was not available",
				"org.osgi.service.log.LogService");

		// assert CM wrapper is available
		assertServiceAvailable(
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

		String actual = cms.getFactoryProperty(
				"org.eclipse.equinox.http.jetty.config", "", "http.port");
		Assert.assertEquals("Property was not found in CM store.", "8080",
				actual);
	}

	@Test
	public void ensureFactoryPidIsUsedByOSGiCM() throws IOException {
		String factoryPid = "org.lunifera.runtime.component.configuration.manager.tests.helper";

		// reset all configurations
		for (Configuration configuration : findFactoryConfigurations(factoryPid)) {
			configuration.delete();
		}

		// register the managed service factory
		Hashtable<String, String> props = new Hashtable<String, String>();
		props.put(Constants.SERVICE_PID, factoryPid);
		bc.registerService(ManagedServiceFactory.class,
				new HelperManagedServiceFactory(), props);

		IConfigurationService cms = ServiceUtils.getService(bc,
				IConfigurationService.class);

		cms.initializeFactoryConfigurationStore(factoryPid, "1001", null);

		Configuration[] configs = findFactoryConfigurations(factoryPid);
		Assert.assertEquals(1, configs.length);
		Assert.assertEquals("1001",
				configs[0].getProperties().get(Constants.SERVICE_PID));
	}

	/**
	 * Looks for all factory configurations for the given factoryPid.
	 * 
	 * @param factoryPid
	 * @return
	 * @throws IOException
	 */
	protected Configuration[] findFactoryConfigurations(String factoryPid) {
		Configuration[] configs = null;
		// As ConfigurationAdmin.getConfiguration creates the configuration if
		// it is not yet there, we check its existence first
		try {
			String filter = "(service.factoryPid=" + factoryPid + ")";

			ConfigurationAdmin admin = ServiceUtils.getService(bc,
					ConfigurationAdmin.class);

			configs = admin.listConfigurations(filter);
		} catch (InvalidSyntaxException e) {
			Assert.fail(e.toString());
		} catch (IOException e) {
			Assert.fail(e.toString());
		}

		return configs != null ? configs : new Configuration[0];
	}
}
