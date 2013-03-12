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
package org.lunifera.runtime.cloud.zookeeper.tests.server;

import java.io.File;

import junit.framework.Assert;

import org.apache.zookeeper.server.ZooKeeperServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lunifera.runtime.cloud.zookeeper.internal.ZookeeperException;
import org.lunifera.runtime.cloud.zookeeper.internal.server.ZookeeperServerService;

/**
 * Tests the ZookeeperServerService.
 */
@SuppressWarnings("restriction")
public class ZookeeperServerServiceTest {

	private ZookeeperServerService zkServer;

	@Before
	public void setup() {
		File file = new File(System.getProperty("user.home")
				+ "/lunifera/zookeeper/test");
		zkServer = new ZookeeperServerService("1", file);
	}

	@After
	public void after() throws ZookeeperException {
		zkServer.stop();
	}

	@Test
	public void test_start() throws ZookeeperException {
		zkServer.start();
	}

	@Test
	public void test_isStarted() throws ZookeeperException {
		Assert.assertFalse(zkServer.isStarted());
		zkServer.start();
		Assert.assertTrue(zkServer.isStarted());
		zkServer.stop();
		Assert.assertFalse(zkServer.isStarted());
		zkServer.start();
		Assert.assertTrue(zkServer.isStarted());
	}

	@Test
	public void test_port() throws ZookeeperException {
		zkServer.setPort(2132);
		zkServer.start();
	}

	@Test
	public void test_zookeeperRunning() throws ZookeeperException {
		zkServer.start();
		ZooKeeperServer i1 = zkServer.getZooKeeperServer();
		Assert.assertTrue(i1.isRunning());
		zkServer.stop();
		Assert.assertFalse(i1.isRunning());
		zkServer.start();
		Assert.assertTrue(zkServer.getZooKeeperServer().isRunning());
	}

	@Test
	public void test_zookeeperInstances() throws ZookeeperException {
		Object i1 = zkServer.getZooKeeperServer();
		Assert.assertNull(i1);
		zkServer.start();
		Object i2 = zkServer.getZooKeeperServer();
		Assert.assertNotNull(i2);
		zkServer.stop();
		Object i3 = zkServer.getZooKeeperServer();
		Assert.assertNull(i3);
		zkServer.start();
		Object i4 = zkServer.getZooKeeperServer();
		Assert.assertNotSame(i2, i4);
	}

}
