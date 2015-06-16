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
package org.lunifera.runtime.security.impl;

import javax.sql.DataSource;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

@Component(immediate=true)
public class JdbcRealmComponent {

	private ComponentContext context;
	private ServiceRegistration<Realm> reg;
	private DataSource ds;

	protected void activate(ComponentContext context) {
		this.context = context;

		JdbcRealm realm = new JdbcRealm();
		realm.setDataSource(ds);

		reg = this.context.getBundleContext().registerService(Realm.class,
				realm, null);
	}

	@Reference(cardinality = ReferenceCardinality.MANDATORY, target = "(security.datasource=jdbc)", unbind = "unsetDatasource")
	protected void setDatasource(DataSource ds) {
		this.ds = ds;
	}

	protected void unsetDatasource(DataSource ds) {
		this.ds = null;
	}

	protected void deactivate() {
		reg.unregister();
		reg = null;
	}
}
