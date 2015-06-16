/**
 * Copyright (c) 2011 - 2015, Bernhard Edler (Wien)
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *         Bernhard Edler - Initial implementation
 */
package org.lunifera.runtime.datasource.provider;

import java.sql.SQLException;
import java.util.Dictionary;
import java.util.Properties;

import javax.sql.DataSource;
import javax.sql.XADataSource;

import org.lunifera.runtime.common.datasource.config.CommonDatasourceConfig;
import org.lunifera.runtime.common.util.OSGiUtil;
import org.osgi.framework.Filter;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.jdbc.DataSourceFactory;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

@Component(name = "org.lunifera.runtime.datasource.provider", configurationPolicy = ConfigurationPolicy.REQUIRE)
public class DatasourceComponent implements
		ServiceTrackerCustomizer<DataSourceFactory, DataSourceFactory>,
		ManagedService {

	private static final String DSFACTORY_FILTER = "(&(objectClass=org.osgi.service.jdbc.DataSourceFactory)(osgi.jdbc.driver.class=%s))";
	private ComponentContext context;
	private DataSource ds;
	private XADataSource xaDs;
	private ServiceRegistration<DataSource> dsRegister;
	private ServiceRegistration<XADataSource> xaDsRegister;
	private ServiceTracker<DataSourceFactory, DataSourceFactory> dsFactoryTracker;
	private DataSourceFactory dataSourceFactory;

	@Activate
	protected void activate(ComponentContext context) {
		this.context = context;

	}

	private Filter createFilter() throws InvalidSyntaxException {
		Filter f = context.getBundleContext().createFilter(
				String.format(
						DSFACTORY_FILTER,
						context.getProperties().get(
								DataSourceFactory.OSGI_JDBC_DRIVER_NAME)));
		return f;
	}

	@Modified
	protected void update(ComponentContext context) {
		killCurrent();
		if (dataSourceFactory != null) {
			createNewDatasource(context.getProperties());
		}
	}

	private void killCurrent() {
		if (dsRegister != null) {
			ds = null;
			dsRegister.unregister();
			dsRegister = null;
		}

		if (xaDsRegister != null) {
			xaDs = null;
			xaDsRegister.unregister();
			xaDsRegister = null;
		}
	}

	private void createNewDatasource(Dictionary<?, ?> input) {
		try {
			String driverName = (String) input
					.get(DataSourceFactory.OSGI_JDBC_DRIVER_NAME);

			Properties properties = OSGiUtil
					.filterProperties(OSGiUtil
							.convertDictionaryToProperties(input),
							CommonDatasourceConfig
									.getPropertyKeysForDriver(driverName));

			ds = dataSourceFactory.createDataSource(properties);
			xaDs = dataSourceFactory.createXADataSource(properties);

			// Hashtable<String, Object> props = new Hashtable<String,
			// Object>();
			// props.put(JNDIConstants.JNDI_SERVICENAME, "");
			// TODO - please put props to registry
			// 1) datasourceName from DatasourceFactory#OSGI_JDBC_DRIVER_CLASS,
			// #OSGI_JDBC_DRIVER_NAME, #OSGI_JDBC_DRIVER_VERSION,
			// #JDBC_DATABASE_NAME, #JDBC_DATASOURCE_NAME
			//
			// And please register the #JDBC_DATASOURCE_NAME with
			// JNDIConstants.JNDI_SERVICENAME too.
			dsRegister = context.getBundleContext().registerService(
					DataSource.class, ds, null);
			xaDsRegister = context.getBundleContext().registerService(
					XADataSource.class, xaDs, null);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	@Deactivate
	protected void deactivate(ComponentContext context) {
		this.context = null;

		killCurrent();

		if (dsFactoryTracker != null) {
			dsFactoryTracker.close();
			dsFactoryTracker = null;
		}

	}

	@Override
	public DataSourceFactory addingService(
			ServiceReference<DataSourceFactory> reference) {
		if (dataSourceFactory == null) {
			dataSourceFactory = context.getBundleContext()
					.getService(reference);
			createNewDatasource(context.getProperties());
			return dataSourceFactory;
		} else {
			return null;
		}
	}

	@Override
	public void modifiedService(ServiceReference<DataSourceFactory> reference,
			DataSourceFactory service) {

	}

	@Override
	public void removedService(ServiceReference<DataSourceFactory> reference,
			DataSourceFactory service) {
		if (service == dataSourceFactory) {
			killCurrent();
			dataSourceFactory = null;
		}
	}

	@Override
	public void updated(Dictionary<String, ?> properties)
			throws ConfigurationException {
		try {
			dsFactoryTracker = new ServiceTracker<DataSourceFactory, DataSourceFactory>(
					context.getBundleContext(), createFilter(), this);
			dsFactoryTracker.open();
			dataSourceFactory = dsFactoryTracker.waitForService(1000);
		} catch (InvalidSyntaxException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (properties != null) {
			killCurrent();
			createNewDatasource(properties);
		}
	}

}
