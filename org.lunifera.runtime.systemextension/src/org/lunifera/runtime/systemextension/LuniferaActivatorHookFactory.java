package org.lunifera.runtime.systemextension;

import org.eclipse.osgi.internal.hookregistry.ActivatorHookFactory;
import org.osgi.framework.BundleActivator;

public class LuniferaActivatorHookFactory implements ActivatorHookFactory {

	@Override
	public BundleActivator createActivator() {
		return new Activator();
	}

}
