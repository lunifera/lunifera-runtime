package org.lunifera.runtime.cloud.zookeeper.tests;

import static org.knowhowlab.osgi.testing.assertions.BundleAssert.assertBundleAvailable;
import static org.knowhowlab.osgi.testing.assertions.ServiceAssert.assertServiceAvailable;

import org.knowhowlab.osgi.testing.assertions.BundleAssert;
import org.knowhowlab.osgi.testing.assertions.ServiceAssert;
import org.knowhowlab.osgi.testing.utils.BundleUtils;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;

/**
 * Helps checking required bundles
 */
public class BundleHelper {

	public static void ensureSetup() throws BundleException {
		
		BundleAssert.setDefaultBundleContext(Activator.context);
		ServiceAssert.setDefaultBundleContext(Activator.context);
		
		ensureNeededBundlesAvailable();
		ensureNeededServicesAvailable();
	}

	public static void ensureNeededBundlesAvailable() throws BundleException {

		// check bundles available
		assertBundleAvailable("org.lunifera.runtime.cloud.zookeeper");
		assertBundleAvailable("org.eclipse.equinox.ds");
		assertBundleAvailable("org.eclipse.equinox.util");
		assertBundleAvailable("org.eclipse.equinox.cm");

		Bundle zk = BundleUtils.findBundle(Activator.context,
				"org.lunifera.runtime.cloud.zookeeper");
		if (zk.getState() != Bundle.STARTING && zk.getState() != Bundle.ACTIVE) {
			zk.start();
		}

		// start ds
		Bundle ds = BundleUtils.findBundle(Activator.context,
				"org.eclipse.equinox.ds");
		if (ds == null) {
			throw new IllegalStateException(
					"Bundle org.eclipse.equinox.ds is missing!");
		}
		if (ds.getState() != Bundle.STARTING && ds.getState() != Bundle.ACTIVE) {
			ds.start();
		}

		// start cm
		Bundle cm = BundleUtils.findBundle(Activator.context,
				"org.eclipse.equinox.cm");
		if (cm == null) {
			throw new IllegalStateException(
					"Bundle org.eclipse.equinox.cm is missing!");
		}
		if (cm.getState() != Bundle.STARTING && cm.getState() != Bundle.ACTIVE) {
			cm.start();
		}
	}

	public static void ensureNeededServicesAvailable() throws BundleException {
		assertServiceAvailable("org.osgi.service.cm.ConfigurationAdmin");
	}

}
