/**
 * Copyright (c) 2012 Committers of lunifera.org.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Florian Pirchner - initial API and implementation
 */
package org.lunifera.runtime.common.datasource;

import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.lunifera.runtime.common.datasource.config.CommonDatasourceConfig;

public interface IDataSourceService {

	List<DataSourceInfo> getDataSourcInfos(String filter);

	List<DataSource> getDataSources(String filter);

	void createDataSource(CommonDatasourceConfig config);

	public static class DataSourceInfo {
		private HashMap<String, String> properties;

		private String pid;

		private String url;

		private String databaseName;

		public DataSourceInfo() {
			properties = new HashMap<String, String>();
		}

		public HashMap<String, String> getProperties() {
			return properties;
		}

		public void setProperties(HashMap<String, String> properties) {
			this.properties = properties;
		}

		/**
		 * @return the pid
		 */
		public String getPid() {
			return pid;
		}

		/**
		 * @return the url
		 */
		public String getUrl() {
			return url;
		}

		/**
		 * @return the databaseName
		 */
		public String getDatabaseName() {
			return databaseName;
		}

	}
}
