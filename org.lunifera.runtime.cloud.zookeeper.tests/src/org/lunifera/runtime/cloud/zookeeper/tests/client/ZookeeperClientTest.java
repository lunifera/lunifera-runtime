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

import java.io.File;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lunifera.runtime.cloud.zookeeper.internal.ZookeeperException;
import org.lunifera.runtime.cloud.zookeeper.internal.client.ZookeeperClient;
import org.lunifera.runtime.cloud.zookeeper.internal.server.ZookeeperServerService;

/**
 * Tests the ZookeeperService.
 */
@SuppressWarnings("restriction")
public class ZookeeperClientTest {

	private static ZookeeperServerService zkServer;
	private ZookeeperClient zk;

	@Before
	public void setup() throws ZookeeperException {
		File file = new File(System.getProperty("user.home")
				+ "/lunifera/zookeeper/test");
		zkServer = new ZookeeperServerService("1", file);
		zkServer.start();
		zk = new ZookeeperClient();
	}

	@After
	public void after() throws ZookeeperException {
		zk.stop();
		zkServer.stop();
	}

	@Test
	public void test_start() throws ZookeeperException {
		zk.start();
	}

	@Test
	public void test_connectionString() {
		zk.setConnectionString(null);
		try {
			zk.start();
			Assert.fail();
		} catch (ZookeeperException e) {
		}
	}

	@Test
	public void test_isStarted() throws ZookeeperException {
		Assert.assertFalse(zk.isStarted());
		zk.start();
		Assert.assertTrue(zk.isStarted());
		zk.stop();
		Assert.assertFalse(zk.isStarted());
	}

}
