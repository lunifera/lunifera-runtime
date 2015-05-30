package org.lunifera.runtime.security.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

@Component(immediate=true)
public class SecurityManagerComponent {

	private IniSecurityManagerFactory factory;
	private DefaultSecurityManager securityManager;
	private ComponentContext context;
	private ServiceRegistration<SecurityManager> reg;

	protected void activate(ComponentContext context) {
		this.context = context;
		factory = new IniSecurityManagerFactory("classpath:shiro.ini");

		securityManager = (DefaultSecurityManager) factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		reg = this.context.getBundleContext().registerService(
				SecurityManager.class, securityManager, null);
	}

	@Reference(cardinality = ReferenceCardinality.MULTIPLE, unbind = "removeRealm")
	protected void addRealm(Realm realm) {
		List<Realm> realms = new ArrayList<Realm>(securityManager.getRealms());
		realms.add(realm);
		securityManager.setRealms(realms);
	}

	protected void removeRealm(Realm realm) {
		// not supported by shiro
	}

	protected void deactivate() {
		reg.unregister();
		reg = null;

		factory = null;
		securityManager = null;
	}
}
