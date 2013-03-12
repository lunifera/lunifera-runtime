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

public interface ZookeeperConstants {

	
	/**
	 * The PID for managed factory service access.
	 */
	public static final String OSGI__ZK_SERVER_PID = "lunifera.cloud.server.zookeeper";
	
	/**
	 * The PID for managed service access.
	 */
	public static final String PID = "lunifera.cloud.zookeeper";

	/**
	 * The connection string used to connect to the zookeeper server.
	 */
	public static final String CLIENT_CONNECTION_STRING = "lunifera.cloud.zookeeper.connectionstring";

	/**
	 * The unique id of the zookeeper server.
	 */
	public static final String SERVER_ID = "lunifera.cloud.zookeeper.server.id";

	/**
	 * The port used by the zookeeper server.
	 */
	public static final String SERVER_PORT = "lunifera.cloud.zookeeper.server.port";

	/**
	 * The max number of client connections.
	 */
	public static final String SERVER_MAX_CLIENT_CONNECTIONS = "lunifera.cloud.zookeeper.server.maxclientconn";

	/**
	 * The path to the client zookeeper preferences.
	 */
	public static final String PREFERENCE_CLIENT_NODE_PATH = "lunifera/cloud/zookeeper/client";

	/**
	 * The path to the server zookeeper preferences.
	 */
	public static final String PREFERENCE_SERVER_NODE_PATH = "lunifera/cloud/zookeeper/server";

	/**
	 * The preference key for connectionString.
	 */
	public static final String PREFERENCE_KEY__CLIENT_CONNECTION_STRING = "connectionString";

	/**
	 * The preference key for sessionTimeout.
	 */
	public static final String PREFERENCE_KEY__CLIENT_SESSION_TIMEOUT = "sessionTimeout";

	/**
	 * The preference key for connectionString.
	 */
	public static final String DEFAULT__CLIENT_CONNECTION_STRING = "localhost:2181";

	/**
	 * The default value for sessionTimeout.
	 */
	public static final int DEFAULT__CLIENT_SESSION_TIMEOUT = 5000;

	/**
	 * The default value for server port.
	 */
	public static final int DEFAULT__SERVER_PORT = 2181;

	/**
	 * The default value for max client connections.
	 */
	public static final int DEFAULT__SERVER_MAX_CLIENT_CONNECTIONS = 10;

}