package org.lunifera.runtime.security.impl;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.ldap.JndiLdapContextFactory;
import org.apache.shiro.realm.ldap.JndiLdapRealm;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.ComponentContext;

/**
 * To use this realm, provide your own declarative OSGi service and pass in the
 * properties requested.
 */
public class LdapRealmComponent {

	private static final String LDAP_AUTHENTICATION_MECHANISM = "lunifera.security.ldap.authenticationMechanism";
	private static final String LDAP_URL = "lunifera.security.ldap.url";
	private ComponentContext context;
	private ServiceRegistration<Realm> reg;

	protected void activate(ComponentContext context) {
		this.context = context;

		JndiLdapRealm realm = new JndiLdapRealm();
		realm.setUserDnTemplate((String) context.getProperties().get(
				"lunifera.security.ldap.userDnTemplate"));

		JndiLdapContextFactory factory = new JndiLdapContextFactory();
		factory.setUrl((String) context.getProperties().get(LDAP_URL));
		factory.setAuthenticationMechanism((String) context.getProperties()
				.get(LDAP_AUTHENTICATION_MECHANISM));
		realm.setContextFactory(factory);

		reg = this.context.getBundleContext().registerService(Realm.class,
				realm, null);
	}

	protected void deactivate() {
		reg.unregister();
		reg = null;
	}
}
