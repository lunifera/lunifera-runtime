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
package org.lunifera.runtime.cloud.zookeeper.tests.client;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;

import junit.framework.Assert;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lunifera.runtime.cloud.zookeeper.ZookeeperConstants;
import org.lunifera.runtime.cloud.zookeeper.internal.ZookeeperException;
import org.lunifera.runtime.cloud.zookeeper.internal.client.ZookeeperClient;
import org.lunifera.runtime.cloud.zookeeper.tests.Activator;
import org.lunifera.runtime.cloud.zookeeper.tests.BundleHelper;
import org.osgi.framework.BundleException;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.cm.ConfigurationException;

@SuppressWarnings("restriction")
public class ZookeeperClientServiceTest {

	private ConfigurationAdmin cm;
	private Activator activator;
	private Configuration serverConfig;

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
	
	@After
	public void teardown() throws IOException {
		if(serverConfig != null){
			serverConfig.delete();
			serverConfig = null;
		}
	}

	@Test
	public void testClient() throws ZookeeperException, KeeperException, InterruptedException, IOException{
		startServer();
		
		ZookeeperClient client = new ZookeeperClient();
		client.setConnectionString("localhost:2129");
		client.start();
		
		ZookeeperClient client2 = new ZookeeperClient();
		client2.setConnectionString("localhost:2129");
		client2.start();
		
		ZooKeeper zk1 = client.getZooKeeper();
		ZooKeeper zk2 = client2.getZooKeeper();
		
		Assert.assertNull(zk2.exists("/client1/test", false));
		zk1.create("/client1/test", "Testdata".getBytes(), null, CreateMode.EPHEMERAL);
		Assert.assertNotNull(zk2.exists("/client1/test", false));
	}
	
	/**
	 * Starts the server.
	 * 
	 * @throws IOException
	 */
	public void startServer() throws IOException {
		// create new instance
		Dictionary<String, Object> props = new Hashtable<String, Object>();
		props.put(ZookeeperConstants.SERVER_PORT, Integer.toString(2129));
		props.put(ZookeeperConstants.SERVER_MAX_CLIENT_CONNECTIONS,
				Integer.toString(15));

		serverConfig = cm.createFactoryConfiguration(
				ZookeeperConstants.OSGI__ZK_SERVER_PID, null);
		serverConfig.update(props);
		waitCM();
	}

	private void waitCM() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
		}
	}
}
