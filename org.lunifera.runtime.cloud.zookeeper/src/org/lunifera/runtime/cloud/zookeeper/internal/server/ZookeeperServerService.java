package org.lunifera.runtime.cloud.zookeeper.internal.server;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.zookeeper.server.PurgeTxnLog;
import org.apache.zookeeper.server.ServerCnxnFactory;
import org.apache.zookeeper.server.ZooKeeperServer;
import org.apache.zookeeper.server.persistence.FileTxnSnapLog;
import org.lunifera.runtime.cloud.zookeeper.IZookeeperServer;
import org.lunifera.runtime.cloud.zookeeper.internal.ZookeeperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZookeeperServerService implements IZookeeperServer {

	private static final Logger logger = LoggerFactory
			.getLogger(ZookeeperServerService.class);

	private final String id;
	private final File configLocation;

	private ZooKeeperServer server;
	private ServerCnxnFactory factory;

	private int port = 2181;
	private int maxClientConnections = 10;

	private Lock lock = new ReentrantLock();

	private boolean started;

	public ZookeeperServerService(String id, File configLocation) {
		this.id = id;
		this.configLocation = configLocation;
	}
	
	public String getId() {
		return id;
	}

	public int getPort() {
		return port;
	}

	/**
	 * Sets the port of this zookeeper server.
	 * 
	 * @param port
	 *            the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	public int getMaxClientConnections() {
		return maxClientConnections;
	}

	/**
	 * Sets the number of max client connections.
	 * 
	 * @param maxClientConnections
	 *            the maxClientConnections to set
	 */
	public void setMaxClientConnections(int maxClientConnections) {
		this.maxClientConnections = maxClientConnections;
	}

	/**
	 * Returns the zookeeper server.
	 * 
	 * @return the server
	 */
	public ZooKeeperServer getZooKeeperServer() {
		return server;
	}

	@Override
	public boolean isStarted() {
		return started;
	}

	@Override
	public void start() throws ZookeeperException {
		if (started) {
			return;
		}

		try {
			lock.lock();
			startServer();

			started = true;
		} catch (IllegalAccessException e) {
			logger.error("{}", e);
			throw new ZookeeperException(e);
		} catch (InvocationTargetException e) {
			logger.error("{}", e);
			throw new ZookeeperException(e);
		} catch (NoSuchMethodException e) {
			logger.error("{}", e);
			throw new ZookeeperException(e);
		} catch (ClassNotFoundException e) {
			logger.error("{}", e);
			throw new ZookeeperException(e);
		} catch (IOException e) {
			logger.error("{}", e);
			throw new ZookeeperException(e);
		} catch (InterruptedException e) {
			logger.error("{}", e);
			throw new ZookeeperException(e);
		} finally {
			lock.unlock();
		}

	}

	@Override
	public void stop() throws ZookeeperException {
		if (!started) {
			return;
		}

		try {
			lock.lock();
			started = false;

			factory.shutdown();
			factory = null;
			server.shutdown();
			server = null;
		} finally {
			lock.unlock();
		}

	}

	/**
	 * Starts the zookeeper server.
	 * 
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws ClassNotFoundException
	 * @throws InterruptedException
	 */
	protected void startServer() throws IOException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException,
			ClassNotFoundException, InterruptedException {
		System.setProperty("zookeeper.jmx.log4j.disable",
				Boolean.TRUE.toString());

		// get directories
		File snapDir = configLocation;
		File dataDir = new File(String.format("%s/logs",
				configLocation.getAbsolutePath()));

		// clean old logs
		PurgeTxnLog.purge(dataDir, snapDir, 3);

		server = new ZooKeeperServer();
		server.setTxnLogFactory(new FileTxnSnapLog(dataDir, snapDir));

		// rely on defaults for the following values
		server.setTickTime(ZooKeeperServer.DEFAULT_TICK_TIME);
		server.setMinSessionTimeout(2 * ZooKeeperServer.DEFAULT_TICK_TIME);
		server.setMaxSessionTimeout(10 * ZooKeeperServer.DEFAULT_TICK_TIME);

		factory = org.apache.zookeeper.server.ServerCnxnFactory.createFactory(
				new InetSocketAddress(port), maxClientConnections);
		factory.startup(server);
	}

}
