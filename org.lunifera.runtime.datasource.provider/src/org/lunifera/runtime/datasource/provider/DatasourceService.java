package org.lunifera.runtime.datasource.provider;

import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.sql.DataSource;
import javax.sql.XADataSource;

import org.lunifera.runtime.common.datasource.IDataSourceService;
import org.lunifera.runtime.common.datasource.config.CommonDatasourceConfig;
import org.lunifera.runtime.common.util.OSGiUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.util.tracker.ServiceTracker;

@SuppressWarnings("unused")
@Component
public class DatasourceService implements IDataSourceService {
	private static final String ORG_LUNIFERA_EXAMPLES_DATASOURCE = "org.lunifera.runtime.datasource.provider";
	private ServiceTracker<ConfigurationAdmin, ConfigurationAdmin> cfgAdminTracker;
	private ConfigurationAdmin configAdmin;
	private ComponentContext context;

	private Collection<ServiceReference<XADataSource>> xADataSources;
	private Collection<ServiceReference<DataSource>> dataSources;

	@Activate
	protected void activate(ComponentContext context) {
		this.context = context;

		try {
			cfgAdminTracker = new ServiceTracker<ConfigurationAdmin, ConfigurationAdmin>(
					context.getBundleContext(), ConfigurationAdmin.class, null);
			cfgAdminTracker.open();
			configAdmin = cfgAdminTracker.waitForService(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<DataSourceInfo> getDataSourcInfos(String filter) {
		try {
			dataSources = context.getBundleContext().getServiceReferences(
					DataSource.class, filter);
			xADataSources = context.getBundleContext().getServiceReferences(
					XADataSource.class, filter);
		} catch (InvalidSyntaxException e) {
		}

		ArrayList<DataSourceInfo> dsInfos = new ArrayList<DataSourceInfo>();
		dataSources
				.stream()
				.map(serviceRef -> context.getBundleContext().getService(
						serviceRef)).collect(Collectors.toList()).stream()
				.forEach(dataSource -> {
					DatabaseMetaData c;
					try {
						c = dataSource.getConnection().getMetaData();
						DataSourceInfo dI = new DataSourceInfo();
						dI.getProperties().put("Url", c.getURL().toString());
						dsInfos.add(dI);
					} catch (Exception e) {
						e.printStackTrace();
					}
				});

		return dsInfos;
	}

	@Override
	public List<DataSource> getDataSources(String filter) {
		try {
			dataSources = context.getBundleContext().getServiceReferences(
					DataSource.class, filter);
			xADataSources = context.getBundleContext().getServiceReferences(
					XADataSource.class, filter);
		} catch (InvalidSyntaxException e) {
		}

		ArrayList<DataSource> dataSourcelist = new ArrayList<DataSource>();
		dataSourcelist = (ArrayList<DataSource>) dataSources
				.stream()
				.map(serviceRef -> context.getBundleContext().getService(
						serviceRef)).collect(Collectors.toList());

		return dataSourcelist;
	}

	// @Override
	// public void createDataSource(DataSourceConfig config) {
	// try {
	// // try {
	// // if (configAdmin.listConfigurations(null) != null) {
	// // List<Configuration> configurations = Arrays
	// // .asList(configAdmin.listConfigurations(null));
	// // for (Configuration c : configurations) {
	// // c.delete();
	// // }
	// // }
	// // } catch (NullPointerException e) {
	// // e.printStackTrace();
	// // }
	// String pid = configAdmin.createFactoryConfiguration(
	// ORG_LUNIFERA_EXAMPLES_DATASOURCE, null).getPid();
	// configAdmin.getConfiguration(pid, null).update(
	// config.getProperties());
	// } catch (IOException e) {
	// e.printStackTrace();
	// // } catch (InvalidSyntaxException e) {
	// // e.printStackTrace();
	// }
	// }

	@Override
	public void createDataSource(CommonDatasourceConfig config) {
		try {
			String pid = configAdmin.createFactoryConfiguration(
					ORG_LUNIFERA_EXAMPLES_DATASOURCE, null).getPid();
			configAdmin.getConfiguration(pid, null)
					.update(OSGiUtil.convertHashMapToDictionary(config
							.getProperties()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
