package org.lunifera.datasource;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;
import javax.sql.XADataSource;

import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.jdbc.DataSourceFactory;

@Component
public class DatasourceComponent {

	private ComponentContext context;
	private DataSourceFactory dsFactory;
	private DataSource ds;
	private XADataSource xaDs;
	private ServiceRegistration<DataSource> dsRegister;
	private ServiceRegistration<XADataSource> xaDsRegister;
	private Properties props;

	@Activate
	protected void activate(ComponentContext context2) {
		this.context = context2;
		props = new Properties();
		props.setProperty(DataSourceFactory.JDBC_DATABASE_NAME, "");
		props.setProperty(DataSourceFactory.JDBC_SERVER_NAME, "");
		props.setProperty(DataSourceFactory.JDBC_PORT_NUMBER, "");
		props.setProperty(DataSourceFactory.JDBC_USER, "");
		props.setProperty(DataSourceFactory.JDBC_PASSWORD, "");
		
		try {
			ds = dsFactory.createDataSource(props);

			dsRegister = context.getBundleContext().registerService(
					DataSource.class, ds, null);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			xaDs = dsFactory.createXADataSource(props);

			xaDsRegister = context.getBundleContext().registerService(
					XADataSource.class, xaDs, null);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Deactivate
	protected void deactivate() {
		this.context = null;

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

	@Reference(cardinality = ReferenceCardinality.MANDATORY, target = "(osgi.jdbc.driver.class=org.apache.derby.jdbc.EmbeddedDriver)", unbind = "unbindDatasourceFactory", policy = ReferencePolicy.STATIC)
	protected void bindDatasourceFactory(DataSourceFactory dsFactory) {
		this.dsFactory = dsFactory;
	}

	protected void unbindDatasourceFactory(DataSourceFactory dsFactory) {
		this.dsFactory = null;
	}

}
