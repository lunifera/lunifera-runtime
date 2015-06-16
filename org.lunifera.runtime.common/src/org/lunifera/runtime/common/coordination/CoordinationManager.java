/**
 * Copyright (c) 2011 - 2015, Lunifera GmbH (Gross Enzersdorf), Loetz KG (Heidelberg)
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *         Florian Pirchner - Initial implementation
 */
package org.lunifera.runtime.common.coordination;

import java.util.Map;
import java.util.UUID;

import org.lunifera.runtime.common.state.SharedStateUnitOfWork;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.coordinator.Coordination;
import org.osgi.service.coordinator.Coordinator;
import org.osgi.util.tracker.ServiceTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Helper methods for OSGi coordination.
 */
public class CoordinationManager {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CoordinationManager.class);
	private ServiceTracker<Coordinator, Coordinator> tracker;

	public CoordinationManager() {

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
					FrameworkUtil.getBundle(CoordinationManager.class)
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
	 * coordination. Returns <code>null</code> if no {@link Coordinator} is
	 * available.
	 * <p>
	 * <b>Attention:</b> Do not forget to release the manager! This will release
	 * the CoordinatorService. Pending coordinations will be ended abnormally in
	 * case that no other manager will hold a reference to the
	 * CoordinationService.
	 * 
	 * @return
	 */
	public Coordination createCurrentCoordination(
			Map<Class<?>, Object> properties) {
		try {
			tracker = new ServiceTracker<Coordinator, Coordinator>(
					FrameworkUtil.getBundle(SharedStateUnitOfWork.class)
							.getBundleContext(), Coordinator.class, null);
			tracker.open();
			Coordinator coordinator = tracker.waitForService(10);
			if (coordinator != null) {
				Coordination peek = coordinator.begin(UUID.randomUUID()
						.toString(), 0);
				peek.getVariables().putAll(properties);
				return peek;
			} else {
				LOGGER.error("No coordination service available!");
			}
		} catch (InterruptedException e) {
		} finally {
		}

		return null;
	}

	/**
	 * Releases the {@link Coordinator} service.
	 */
	public void release() {
		if (tracker != null) {
			tracker.close();
			tracker = null;
		}
	}

	/**
	 * Returns the property with the given key from the current
	 * "implicit Coordination". Or <code>null</code> if property is not
	 * available.
	 * 
	 * @param key
	 * @return
	 */
	public static Object getPropertyFromCurrentCoordination(Class<?> key) {
		Coordination current = getCurrentCoordination();
		if (current != null) {
			return current.getVariables().get(key);
		}
		return null;
	}
}
