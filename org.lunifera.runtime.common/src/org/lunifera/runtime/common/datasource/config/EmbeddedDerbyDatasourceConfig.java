package org.lunifera.runtime.common.datasource.config;

import java.util.HashMap;

import org.lunifera.runtime.common.util.OSGiUtil;

public class EmbeddedDerbyDatasourceConfig extends CommonDatasourceConfig {

	public EmbeddedDerbyDatasourceConfig() {
		super();
		DRIVER_NAME = "org.apache.derby.jdbc.EmbeddedDriver";
		properties.put(OSGI_DRIVER_NAME, DRIVER_NAME);
	}

	public enum Keys {
		ATTRIBUTES_AS_PASSWORD {
			public String toString() {
				return "attributesAsPassword";
			}
		},
		CONNECTION_ATTRIBUTES {
			public String toString() {
				return "connectionAttributes";
			}
		},
		CREATE_DATABASE {
			public String toString() {
				return "createDatabase";
			}
		},
		DATABASE_NAME {
			public String toString() {
				return "databaseName";
			}
		},
		DATASOURCE_NAME {
			public String toString() {
				return "dataSourceName";
			}
		},
		DESCRIPTION {
			public String toString() {
				return "description";
			}
		},
		LOG_WRITER {
			public String toString() {
				return "logWriter";
			}
		},
		LOGIN_TIMEOUT {
			public String toString() {
				return "loginTimeout";
			}
		},
		PASSWORD {
			public String toString() {
				return "password";
			}
		},
		SHUTDOWN_DATABASE {
			public String toString() {
				return "shutdownDatabase";
			}
		},
		USER {
			public String toString() {
				return "user";
			}
		}
	}

	public void setAttributesAsPassword(String input) {
		setValue(Keys.ATTRIBUTES_AS_PASSWORD.toString(), input);
	}

	public String getAttributesAsPassword() {
		return getValue(Keys.ATTRIBUTES_AS_PASSWORD.toString());
	}

	public void setConnectionAttributes(String input) {
		setValue(Keys.CONNECTION_ATTRIBUTES.toString(), input);
	}

	public String getConnectionAttributes() {
		return getValue(Keys.CONNECTION_ATTRIBUTES.toString());
	}

	public void setCreateDatabase(String input) {
		setValue(Keys.CREATE_DATABASE.toString(), input);
	}

	public String getCreateDatabase() {
		return getValue(Keys.CREATE_DATABASE.toString());
	}

	public void setDataBaseName(String input) {
		setValue(Keys.DATABASE_NAME.toString(), input);
	}

	public String getDataBaseName() {
		return getValue(Keys.DATABASE_NAME.toString());
	}

	public void setDataSourceName(String input) {
		setValue(Keys.DATASOURCE_NAME.toString(), input);
	}

	public String getDataSourceName() {
		return getValue(Keys.DATASOURCE_NAME.toString());
	}

	public void setDescription(String input) {
		setValue(Keys.DESCRIPTION.toString(), input);
	}

	public String getDescription() {
		return getValue(Keys.DESCRIPTION.toString());
	}

	public void setLogWriter(String input) {
		setValue(Keys.LOG_WRITER.toString(), input);
	}

	public String getLogWriter() {
		return getValue(Keys.LOG_WRITER.toString());
	}

	public void setLoginTimeout(String input) {
		setValue(Keys.LOGIN_TIMEOUT.toString(), input);
	}

	public String getLoginTimeout() {
		return getValue(Keys.LOGIN_TIMEOUT.toString());
	}

	public void setPassword(String input) {
		setValue(Keys.PASSWORD.toString(), input);
	}

	public String getPassword() {
		return getValue(Keys.PASSWORD.toString());
	}

	public void setShutdownDatabase(String input) {
		setValue(Keys.SHUTDOWN_DATABASE.toString(), input);
	}

	public String getShutdownDatabase() {
		return getValue(Keys.SHUTDOWN_DATABASE.toString());
	}

	public void setUser(String input) {
		setValue(Keys.USER.toString(), input);
	}

	public String getUser() {
		return getValue(Keys.USER.toString());
	}

	public HashMap<String, String> filterProperties() {
		return filterProperties(OSGiUtil.getEnumValues(Keys.class));
	}
}
