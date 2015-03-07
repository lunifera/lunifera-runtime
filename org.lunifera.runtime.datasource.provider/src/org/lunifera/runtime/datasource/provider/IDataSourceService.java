package org.lunifera.runtime.datasource.provider;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import javax.sql.DataSource;

public interface IDataSourceService {

	List<DataSourceInfo> getDataSourcInfos(String filter);

	List<DataSource> getDataSources(String filter);

	void createDataSource(DataSourceConfig config);

	public static class DataSourceInfo {
		private HashMap<String, String> properties;

		public DataSourceInfo() {
			properties = new HashMap<String, String>();
		}

		public HashMap<String, String> getProperties() {
			return properties;
		}

		public void setProperties(HashMap<String, String> properties) {
			this.properties = properties;
		}
	}

	public static class DataSourceConfig {

		private Dictionary<String, Object> props;

		public DataSourceConfig() {
			props = new Hashtable<String, Object>();
		}

		public void addProperty(String key, String value) {
			props.put(key.toString(), value);
		}

		// public void addProperty(DsProperties key, String value) {
		// props.put(key.toString(), value);
		// }

		public Dictionary<String, Object> getProperties() {
			return props;
		}

		public enum DsProperties {
			EMBEDDED_DERBY_ATTRIBUTES_AS_PASSWORD {
				public String toString() {
					return "attributesAsPassword";
				}
			},
			EMBEDDED_DERBY_CONNECTION_ATTRIBUTES {
				public String toString() {
					return "connectionAttributes";
				}
			},
			EMBEDDED_DERBY_CREATE_DATABASE {
				public String toString() {
					return "createDatabase";
				}
			},
			EMBEDDED_DERBY_DATASOURCE_NAME {
				public String toString() {
					return "dataSourceName";
				}
			},
			EMBEDDED_DERBY_DATABASE_NAME {
				public String toString() {
					return "databaseName";
				}
			},
			EMBEDDED_DERBY_DESCRIPTION {
				public String toString() {
					return "description";
				}
			},
			EMBEDDED_DERBY_LOG_WRITER {
				public String toString() {
					return "logWriter";
				}
			},
			EMBEDDED_DERBY_LOGIN_TIMEOUT {
				public String toString() {
					return "loginTimeout";
				}
			},
			EMBEDDED_DERBY_PASSWORD {
				public String toString() {
					return "password";
				}
			},
			EMBEDDED_DERBY_SHUTDOWN_DATABASE {
				public String toString() {
					return "shutdownDatabase";
				}
			},
			EMBEDDED_DERBY_USER {
				public String toString() {
					return "user";
				}
			},
			CLIENT_DERBY_CONNECTION_ATTRIBUTES {
				public String toString() {
					return "connectionAttributes";
				}
			},
			CLIENT_DERBY_CREATE_DATABASE {
				public String toString() {
					return "createDatabase";
				}
			},
			CLIENT_DERBY_DATASOURCE_NAME {
				public String toString() {
					return "dataSourceName";
				}
			},
			CLIENT_DERBY_DATABASE_NAME {
				public String toString() {
					return "databaseName";
				}
			},
			CLIENT_DERBY_DESCRIPTION {
				public String toString() {
					return "description";
				}
			},
			CLIENT_DERBY_LOG_WRITER {
				public String toString() {
					return "logWriter";
				}
			},
			CLIENT_DERBY_LOGIN_TIMEOUT {
				public String toString() {
					return "loginTimeout";
				}
			},
			CLIENT_DERBY_PASSWORD {
				public String toString() {
					return "password";
				}
			},
			CLIENT_DERBY_PORT_NUMBER {
				public String toString() {
					return "portNumber";
				}
			},
			CLIENT_DERBY_RETRIEVE_MESSAGE_TEXT {
				public String toString() {
					return "retrieveMessageText";
				}
			},
			CLIENT_DERBY_SECURITY_MECHANISM {
				public String toString() {
					return "securityMechanism";
				}
			},
			CLIENT_DERBY_SERVER_NAME {
				public String toString() {
					return "serverName";
				}
			},
			CLIENT_DERBY_SHUTDOWN_DATABASE {
				public String toString() {
					return "shutdownDatabase";
				}
			},
			CLIENT_DERBY_SSL {
				public String toString() {
					return "ssl";
				}
			},
			CLIENT_DERBY_TRACE_DIRECTORY {
				public String toString() {
					return "traceDirectory";
				}
			},
			CLIENT_DERBY_TRACE_FILE {
				public String toString() {
					return "traceFile";
				}
			},
			CLIENT_DERBY_TRACE_FILE_APPEND {
				public String toString() {
					return "traceFileAppend";
				}
			},
			CLIENT_DERBY_TRACE_LEVEL {
				public String toString() {
					return "traceLevel";
				}
			},
			CLIENT_DERBY_USER {
				public String toString() {
					return "user";
				}
			}
		}
	}

}
