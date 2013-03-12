/**
 * Copyright (c) 2012 Committers of lunifera.org.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Florian Pirchner - initial API and implementation
 */
package org.lunifera.runtime.cloud.zookeeper.tests.server;

import static junit.framework.Assert.assertEquals;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.lunifera.runtime.cloud.zookeeper.IZookeeperServer;
import org.lunifera.runtime.cloud.zookeeper.ZookeeperConstants;
import org.lunifera.runtime.cloud.zookeeper.tests.Activator;
import org.lunifera.runtime.cloud.zookeeper.tests.BundleHelper;
import org.osgi.framework.BundleException;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.cm.ConfigurationException;

public class ZookeeperServerFactoryTest {

	private ConfigurationAdmin cm;
	private Activator activator;

	/**
	 * Setup tests.
	 * 
	 * @throws ConfigurationException
	 * @throws BundleException
	 */
	@Before
	public void setup() throws ConfigurationException, BundleException {
		BundleHelper.ensureSetup();
		cm = Activator.getInstance().getConfigurationAdmin();
		activator = Activator.getInstance();
	}

	/**
	 * Test that a http application starts.
	 * 
	 * @throws IOException
	 */
	@Test
	public void test_startServers() throws IOException {
		waitCM();
		waitCM();

		assertEquals(0, activator.getZookeeperServers().size());

		// create new instance
		Configuration config = cm.createFactoryConfiguration(
				ZookeeperConstants.OSGI__ZK_SERVER_PID, null);
		config.update(preparePort2129());
		waitCM();
		assertEquals(1, activator.getZookeeperServers().size());

		// update instance
		config.update(preparePort2129());
		waitCM();
		assertEquals(1, activator.getZookeeperServers().size());

		// create new instance
		Configuration config2 = cm.createFactoryConfiguration(
				ZookeeperConstants.OSGI__ZK_SERVER_PID, null);
		config2.update(preparePort2130());
		waitCM();
		assertEquals(2, activator.getZookeeperServers().size());

		// remove instance 1
		config.delete();
		waitCM();
		assertEquals(1, activator.getZookeeperServers().size());

		// remove instance 2
		config2.delete();
		waitCM();
		assertEquals(0, activator.getZookeeperServers().size());
	}

	/**
	 * Tests the use of properties in the services.
	 * 
	 * @throws IOException
	 * @throws InvalidSyntaxException
	 */
	@Test
	public void test_properties() throws IOException, InvalidSyntaxException {

		// wait 600ms for CM
		waitCM();
		waitCM();
		waitCM();

		assertEquals(0, activator.getZookeeperServers().size());

		// HttpApplications
		assertEquals(
				0,
				Activator.context.getServiceReferences(IZookeeperServer.class,
						"(lunifera.cloud.zookeeper.server.port=2129)").size());
		assertEquals(
				0,
				Activator.context.getServiceReferences(IZookeeperServer.class,
						"(lunifera.cloud.zookeeper.server.port=2130)").size());

		// create new instance
		Configuration config = cm.createFactoryConfiguration(
				ZookeeperConstants.OSGI__ZK_SERVER_PID, null);
		Dictionary<String, Object> props = new Hashtable<String, Object>();
		props.put(ZookeeperConstants.SERVER_PORT, "2129");
		config.update(props);
		waitCM();

		// HttpApplication
		assertEquals(
				1,
				Activator.context.getServiceReferences(IZookeeperServer.class,
						"(lunifera.cloud.zookeeper.server.port=2129)").size());

		// create new instance
		Configuration config2 = cm.createFactoryConfiguration(
				ZookeeperConstants.OSGI__ZK_SERVER_PID, null);
		Dictionary<String, Object> props2 = new Hashtable<String, Object>();
		props2.put(ZookeeperConstants.SERVER_PORT, "2130");
		config2.update(props2);
		waitCM();

		// HttpApplication
		assertEquals(
				1,
				Activator.context.getServiceReferences(IZookeeperServer.class,
						"(lunifera.cloud.zookeeper.server.port=2130)").size());

		config.delete();
		config2.delete();
		waitCM();

		// HttpApplication
		assertEquals(
				0,
				Activator.context.getServiceReferences(IZookeeperServer.class,
						"(lunifera.cloud.zookeeper.server.port=2129)").size());
		assertEquals(
				0,
				Activator.context.getServiceReferences(IZookeeperServer.class,
						"(lunifera.cloud.zookeeper.server.port=2130)").size());
	}

	/**
	 * Tests the update of properties.
	 * 
	 * @throws IOException
	 * @throws InvalidSyntaxException
	 */
	@Test
	public void test_properties_update() throws IOException,
			InvalidSyntaxException {
		waitCM();

		assertEquals(0, activator.getZookeeperServers().size());

		// create new instance
		Configuration config = cm.createFactoryConfiguration(
				ZookeeperConstants.OSGI__ZK_SERVER_PID, null);
		Dictionary<String, Object> props = new Hashtable<String, Object>();
		props.put(ZookeeperConstants.SERVER_PORT, "2129");
		config.update(props);
		waitCM();

		// HttpApplication
		assertEquals(
				1,
				Activator.context.getServiceReferences(IZookeeperServer.class,
						"(lunifera.cloud.zookeeper.server.port=2129)").size());
		assertEquals(
				0,
				Activator.context.getServiceReferences(IZookeeperServer.class,
						"(lunifera.cloud.zookeeper.server.port=2130)").size());

		props = new Hashtable<String, Object>();
		props.put(ZookeeperConstants.SERVER_PORT, "2130");
		config.update(props);
		waitCM();

		// HttpApplication
		assertEquals(
				0,
				Activator.context.getServiceReferences(IZookeeperServer.class,
						"(lunifera.cloud.zookeeper.server.port=2129)").size());
		assertEquals(
				1,
				Activator.context.getServiceReferences(IZookeeperServer.class,
						"(lunifera.cloud.zookeeper.server.port=2130)").size());
		config.delete();

	}

	/**
	 * Tests that different web applications have different ids.
	 * 
	 * @throws IOException
	 * @throws InvalidSyntaxException
	 */
	@Test
	public void test_Id() throws IOException, InvalidSyntaxException {

		// create new instance
		Configuration config = cm.createFactoryConfiguration(
				ZookeeperConstants.OSGI__ZK_SERVER_PID, null);
		config.update(preparePort2129());
		// create new instance
		Configuration config2 = cm.createFactoryConfiguration(
				ZookeeperConstants.OSGI__ZK_SERVER_PID, null);
		Dictionary<String, Object> props = new Hashtable<String, Object>();
		props.put(ZookeeperConstants.SERVER_PORT, "2130");
		config2.update(props);
		waitCM();

		IZookeeperServer server1 = Activator.context.getService(Activator.context
				.getServiceReferences(IZookeeperServer.class,
						"(lunifera.cloud.zookeeper.server.port=2129)").iterator().next());
		IZookeeperServer server2 = Activator.context.getService(Activator.context
				.getServiceReferences(IZookeeperServer.class,
						"(lunifera.cloud.zookeeper.server.port=2130)").iterator().next());
		Assert.assertFalse(server1.getId().equals(server2.getId()));

		config.delete();
		config2.delete();
	}

	private void waitCM() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
		}
	}

	/**
	 * Prepares default properties that should be used.
	 * 
	 * @return
	 */
	public Dictionary<String, Object> preparePort2129() {
		Dictionary<String, Object> props = new Hashtable<String, Object>();
		props.put(ZookeeperConstants.SERVER_PORT, Integer.toString(2129));
		props.put(ZookeeperConstants.SERVER_MAX_CLIENT_CONNECTIONS,
				Integer.toString(15));
		return props;
	}
	
	/**
	 * Prepares default properties that should be used.
	 * 
	 * @return
	 */
	public Dictionary<String, Object> preparePort2130() {
		Dictionary<String, Object> props = new Hashtable<String, Object>();
		props.put(ZookeeperConstants.SERVER_PORT, Integer.toString(2130));
		props.put(ZookeeperConstants.SERVER_MAX_CLIENT_CONNECTIONS,
				Integer.toString(15));
		return props;
	}
}
