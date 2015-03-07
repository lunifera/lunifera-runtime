package org.lunifera.runtime.datasource.provider;

import java.util.Properties;

import org.apache.derby.jdbc.EmbeddedDriver;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
@SuppressWarnings(value = { "all" })
public class Activator implements BundleActivator {

	public static final String OSGI_JNDI_SERVICE_NAME = "osgi.jndi.service.name";
	public static final String OSGI_JNDI_URL_PREFIX = "osgi:service/";
	public static final String OSGI_UNIT_NAME = "osgi.unit.name";

	public static final String DS_DRIVER_NAME = EmbeddedDriver.class.getName();

	private static BundleContext ctx;
	private static Activator plugin;
	private Properties dataSourceProperties;

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		ctx = context;
		plugin = this;
		// // PostgreSQL Properties
		// // !!! on PostgreSQL, the server configuration property
		// "maxPreparedConnections"
		// // must be set to a value > 0
		// props.setProperty("user", "jppf");
		// props.setProperty("password", "jppf");
		// props.setProperty("databaseName", "jppf_samples");
		// props.setProperty("serverName", "localhost");
		// props.setProperty("portNumber", "5432");
		// // MySQL Properties
		// props.setProperty("user", "jppf");
		// props.setProperty("password", "jppf");
		// props.setProperty("serverName", "localhost");
		// props.setProperty("port", "3306");
		// props.setProperty("databaseName", "jppf_samples");
		// props.setProperty("pinGlobalTxToPhysicalConnection", "true");
		//
		// // H2 Properties
		// props.setProperty("user", "jppf");
		// props.setProperty("password", "jppf");
		// props.setProperty("URL",
		// "jdbc:h2:tcp://localhost:9092/./jppf_samples;SCHEMA=PUBLIC");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		ctx = null;
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

//	private synchronized DataSource createDataSource(String jndiDatasource)
//			throws SQLException, InvalidSyntaxException {
//		DataSourceFactory dsf = lookupDsf(DS_DRIVER_NAME);
//		DataSource ds = dsf.createDataSource(dataSourceProperties);
//		Hashtable<String, String> ht = new Hashtable<String, String>();
//		ht.put(OSGI_JNDI_SERVICE_NAME, jndiDatasource);
//		ctx.registerService(DataSource.class, ds, ht);
//		return ds;
//	}
//
//	private synchronized XADataSource createXADataSource(String jndiDatasource)
//			throws SQLException, InvalidSyntaxException {
//		DataSourceFactory dsf = lookupDsf(DS_DRIVER_NAME);
//		XADataSource ds = dsf.createXADataSource(dataSourceProperties);
//		Hashtable<String, String> ht = new Hashtable<String, String>();
//		ht.put(OSGI_JNDI_SERVICE_NAME, "XA" + jndiDatasource);
//		ctx.registerService(XADataSource.class, ds, ht);
//		return ds;
//	}
//
//	private DataSourceFactory lookupDsf(String clientDriverName)
//			throws InvalidSyntaxException {
//		String filter = "(&(" + DataSourceFactory.OSGI_JDBC_DRIVER_CLASS + "="
//				+ clientDriverName + "))";
//		return getDsf(filter);
//	}
//
//	private DataSourceFactory getDsf(String filter)
//			throws InvalidSyntaxException {
//		ServiceReference[] refs = null;
//		refs = ctx.getServiceReferences(DataSourceFactory.class.getName(),
//				filter);
//		return (refs == null) ? null : (DataSourceFactory) ctx
//				.getService(refs[0]);
//	}
}
