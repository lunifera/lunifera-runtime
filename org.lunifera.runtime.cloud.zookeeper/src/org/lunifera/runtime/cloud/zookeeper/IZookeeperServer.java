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
package org.lunifera.runtime.cloud.zookeeper;

import org.lunifera.runtime.cloud.zookeeper.internal.ZookeeperException;

/**
 * An abstraction above the Zookeeper-Server. It allows to start, stop and
 * control the wrapped server.
 */
public interface IZookeeperServer {

	/**
	 * Returns the unique id of that zookeeper server.
	 * 
	 * @return
	 */
	public String getId();

	/**
	 * Returns the port of that zookeeper server.
	 * 
	 * @return the port
	 */
	int getPort();

	/**
	 * Returns the number of max client connections.
	 * 
	 * @return the maxClientConnections
	 */
	int getMaxClientConnections();

	/**
	 * Returns true, if the zookeeper server is started. False otherwise.
	 * 
	 * @return
	 */
	boolean isStarted();

	/**
	 * Starts the server if not started yet.
	 * 
	 * @throws ZookeeperException
	 */
	void start() throws ZookeeperException;

	/**
	 * Stops the server if not stopped yet.
	 * 
	 * @throws ZookeeperException
	 */
	void stop() throws ZookeeperException;

}
