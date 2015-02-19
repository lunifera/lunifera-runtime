package org.lunifera.runtime.systemextension;

import org.eclipse.osgi.internal.hookregistry.HookConfigurator;
import org.eclipse.osgi.internal.hookregistry.HookRegistry;

public class LuniferaHookConfigurator implements HookConfigurator {

	@Override
	public void addHooks(HookRegistry hookRegistry) {
		hookRegistry
				.addActivatorHookFactory(new LuniferaActivatorHookFactory());
	}

}
