package org.lunifera.runtime.common.coordination;

import java.util.Map;
import java.util.UUID;

import org.lunifera.runtime.common.environment.SharedEnvironmentUnitOfWork;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.coordinator.Coordination;
import org.osgi.service.coordinator.Coordinator;
import org.osgi.util.tracker.ServiceTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Helper methods for OSGi coordination.
 */
public class CoordinationUtil {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CoordinationUtil.class);

	private CoordinationUtil() {

	}

	/**
	 * Returns the current coordination or <code>null</code> if no current
	 * coordination is available.
	 * 
	 * @return
	 */
	public static Coordination getCurrentCoordination() {
		ServiceTracker<Coordinator, Coordinator> tracker = null;
		try {
			tracker = new ServiceTracker<Coordinator, Coordinator>(
					FrameworkUtil.getBundle(CoordinationUtil.class)
							.getBundleContext(), Coordinator.class, null);
			tracker.open();
			Coordinator coordinator = tracker.waitForService(10);
			if (coordinator != null) {
				return coordinator.peek();
			} else {
				LOGGER.error("No coordination service available!");
			}
		} catch (InterruptedException e) {
		} finally {
			if (tracker != null) {
				tracker.close();
			}
		}
		return null;
	}

	/**
	 * Returns a new implicit coordination and makes it the current
	 * coordination. Returns <code>null</code> if no {@link Coordinator} is available.
	 * 
	 * @return
	 */
	public static Coordination createCurrentCoordination(
			Map<Class<?>, Object> properties) {
		ServiceTracker<Coordinator, Coordinator> tracker = null;
		try {
			tracker = new ServiceTracker<Coordinator, Coordinator>(
					FrameworkUtil.getBundle(SharedEnvironmentUnitOfWork.class)
							.getBundleContext(), Coordinator.class, null);
			tracker.open();
			Coordinator coordinator = tracker.waitForService(500);
			if (coordinator != null) {
				Coordination peek = coordinator.begin(UUID.randomUUID()
						.toString(), 100);
				peek.getVariables().putAll(properties);
				return peek;
			} else {
				LOGGER.error("No coordination service available!");
			}
		} catch (InterruptedException e) {
		} finally {
			if (tracker != null) {
				tracker.close();
			}
		}

		return null;
	}
}
