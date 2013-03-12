package org.lunifera.runtime.cloud.zookeeper.internal;

@SuppressWarnings("serial")
public class ZookeeperException extends Exception {

	public ZookeeperException() {
		super();
	}

	public ZookeeperException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ZookeeperException(String message, Throwable cause) {
		super(message, cause);
	}

	public ZookeeperException(String message) {
		super(message);
	}

	public ZookeeperException(Throwable cause) {
		super(cause);
	}

}
