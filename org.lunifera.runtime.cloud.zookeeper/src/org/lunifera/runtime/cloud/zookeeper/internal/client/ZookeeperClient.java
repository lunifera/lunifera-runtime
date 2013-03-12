package org.lunifera.runtime.cloud.zookeeper.internal.client;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.lunifera.runtime.cloud.zookeeper.IZookeeperClient;
import org.lunifera.runtime.cloud.zookeeper.internal.ZookeeperException;

public class ZookeeperClient implements IZookeeperClient, Watcher {

	private ZooKeeper zookeeper;

	private volatile boolean started;
	private CountDownLatch latch;
	private Lock lock = new ReentrantLock();

	private String connectionString = DEFAULT__CLIENT_CONNECTION_STRING;
	private int sessionTimeout = DEFAULT__CLIENT_SESSION_TIMEOUT;

	/**
	 * Returns the zookeeper instance.
	 * 
	 * @return
	 */
	public ZooKeeper getZooKeeper() {
		return zookeeper;
	}

	/**
	 * Sets the connection string that is used to connect to the server.
	 * 
	 * @param connectionString
	 */
	public void setConnectionString(String connectionString) {
		this.connectionString = connectionString;
	}

	/**
	 * Sets the session timeout of the zookeeper client.
	 * 
	 * @param sessionTimeout
	 */
	public void setSessionTimeout(int sessionTimeout) {
		this.sessionTimeout = sessionTimeout;
	}

	/**
	 * Is called to start the zookeeper service.
	 * 
	 * @throws ZookeeperException
	 */
	public void start() throws ZookeeperException {
		lock.lock();
		latch = new CountDownLatch(1);
		try {
			zookeeper = new ZooKeeper(connectionString, sessionTimeout, this);

			latch.await(30, TimeUnit.SECONDS);
		} catch (Exception e) {
			throw new ZookeeperException(e);
		} finally {
			started = true;
			lock.unlock();
		}
	}

	/**
	 * Is called to stop the zookeeper exception.
	 * 
	 * @throws ZookeeperException
	 */
	public void stop() throws ZookeeperException {
		started = false;

		lock.lock();
		try {
			try {
				if (zookeeper != null) {
					zookeeper.close();
					zookeeper = null;
				}
			} catch (InterruptedException e) {
				throw new ZookeeperException(e);
			}
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void process(WatchedEvent event) {
		latch.countDown();
	}

	@Override
	public boolean isStarted() {
		return started;
	}

}
