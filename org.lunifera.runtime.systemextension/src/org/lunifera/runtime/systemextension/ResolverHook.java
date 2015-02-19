package org.lunifera.runtime.systemextension;

import java.util.Collection;
import java.util.Iterator;

import org.osgi.framework.hooks.resolver.ResolverHookFactory;
import org.osgi.framework.wiring.BundleCapability;
import org.osgi.framework.wiring.BundleRequirement;
import org.osgi.framework.wiring.BundleRevision;

public class ResolverHook implements ResolverHookFactory {

	@Override
	public org.osgi.framework.hooks.resolver.ResolverHook begin(
			final Collection<BundleRevision> triggers) {
		return new org.osgi.framework.hooks.resolver.ResolverHook() {

			@Override
			public void filterSingletonCollisions(BundleCapability singleton,
					Collection<BundleCapability> collisionCandidates) {
			}

			@Override
			public void filterResolvable(Collection<BundleRevision> candidates) {

			}

			@Override
			public void filterMatches(BundleRequirement requirement,
					Collection<BundleCapability> candidates) {
				if (requirement.getNamespace().equals("osgi.wiring.bundle"))
					if (candidates.size() > 1 && containsGuava(candidates)) {
						filterHighestGuava(candidates);
					}
			}

			@Override
			public void end() {

			}
		};
	}

	/**
	 * Returns true, if the candiates contain guava.
	 * 
	 * @param candidates
	 * @return
	 */
	protected boolean containsGuava(Collection<BundleCapability> candidates) {
		for (BundleCapability cap : candidates) {
			if (cap.getResource().getSymbolicName().equals("com.google.guava")) {
				return true;

			}
		}
		return false;
	}

	/**
	 * Handle guava bundle. Only use the highest supported version.
	 * 
	 * @param candidates
	 */
	private void filterHighestGuava(Collection<BundleCapability> candidates) {
		BundleCapability highest = getHighestGuava(candidates);
		if (highest != null) {
			for (Iterator<BundleCapability> iterator = candidates.iterator(); iterator
					.hasNext();) {
				BundleCapability cap = iterator.next();
				if (cap != highest) {
					iterator.remove();
				}
			}
		}
	}

	/**
	 * Filters the highest available guava from candidates.
	 * 
	 * @param candidates
	 * @return
	 */
	private BundleCapability getHighestGuava(
			Collection<BundleCapability> candidates) {
		BundleCapability highest = null;
		for (BundleCapability cap : candidates) {
			if (cap.getResource().getSymbolicName().equals("com.google.guava")) {
				if (highest != null) {
					if (highest.getResource().getVersion()
							.compareTo(cap.getResource().getVersion()) < 1) {
						highest = cap;
					}
				} else {
					highest = cap;
				}

			}
		}
		return highest;
	}

}
