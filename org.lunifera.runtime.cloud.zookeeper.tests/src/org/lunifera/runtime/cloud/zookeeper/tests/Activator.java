package org.lunifera.runtime.cloud.zookeeper.tests;

import java.util.ArrayList;
import java.util.List;

import org.knowhowlab.osgi.testing.utils.ServiceUtils;
import org.lunifera.runtime.cloud.zookeeper.IZookeeperServer;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.cm.ConfigurationAdmin;

public class Activator implements BundleActivator {

	public static BundleContext context;
	private static Activator instance;

	/**
	 * @return the instance
	 */
	public static Activator getInstance() {
		return instance;
	}

	@Override
	public void start(BundleContext context) throws Exception {
		Activator.context = context;
		instance = this;
		BundleHelper.ensureSetup();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		Activator.context = null;
		instance = null;
	}

	/**
	 * Returns the configuration admin.
	 * 
	 * @return
	 */
	public ConfigurationAdmin getConfigurationAdmin() {
		return ServiceUtils.getService(context, ConfigurationAdmin.class);
	}

	/**
	 * Returns a list of zookeeper servers.
	 * 
	 * @return
	 */
	public List<IZookeeperServer> getZookeeperServers() {
		List<IZookeeperServer> servers = new ArrayList<IZookeeperServer>();
		try {
			for (ServiceReference<IZookeeperServer> reference : context
					.getServiceReferences(IZookeeperServer.class, null)) {
				servers.add(context.getService(reference));
			}
		} catch (InvalidSyntaxException e) {
			throw new IllegalStateException(e);
		}
		return servers;
	}

}
