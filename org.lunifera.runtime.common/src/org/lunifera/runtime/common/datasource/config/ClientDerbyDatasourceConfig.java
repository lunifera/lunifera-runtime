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
package org.lunifera.runtime.common.datasource.config;

import java.util.HashMap;

import org.lunifera.runtime.common.util.OSGiUtil;

public class ClientDerbyDatasourceConfig extends CommonDatasourceConfig {

	public ClientDerbyDatasourceConfig() {
		super();
		DRIVER_NAME = "org.apache.derby.jdbc.ClientDriver";
		properties.put(OSGI_DRIVER_NAME, DRIVER_NAME);
	}

	public enum Keys {
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
		DATASOURCE_NAME {
			public String toString() {
				return "dataSourceName";
			}
		},
		DATABASE_NAME {
			public String toString() {
				return "databaseName";
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
		PORT_NUMBER {
			public String toString() {
				return "portNumber";
			}
		},
		RETRIEVE_MESSAGE_TEXT {
			public String toString() {
				return "retrieveMessageText";
			}
		},
		SECURITY_MECHANISM {
			public String toString() {
				return "securityMechanism";
			}
		},
		SERVER_NAME {
			public String toString() {
				return "serverName";
			}
		},
		SHUTDOWN_DATABASE {
			public String toString() {
				return "shutdownDatabase";
			}
		},
		SSL {
			public String toString() {
				return "ssl";
			}
		},
		TRACE_DIRECTORY {
			public String toString() {
				return "traceDirectory";
			}
		},
		TRACE_FILE {
			public String toString() {
				return "traceFile";
			}
		},
		TRACE_FILE_APPEND {
			public String toString() {
				return "traceFileAppend";
			}
		},
		TRACE_LEVEL {
			public String toString() {
				return "traceLevel";
			}
		},
		USER {
			public String toString() {
				return "user";
			}
		}
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

	public void setPortNumber(String input) {
		setValue(Keys.PORT_NUMBER.toString(), input);
	}

	public String getPortNumber() {
		return getValue(Keys.PORT_NUMBER.toString());
	}

	public void setRetrieveMessageText(String input) {
		setValue(Keys.RETRIEVE_MESSAGE_TEXT.toString(), input);
	}

	public String getRetrieveMessageText() {
		return getValue(Keys.RETRIEVE_MESSAGE_TEXT.toString());
	}

	public void setSecurityMechanism(String input) {
		setValue(Keys.SECURITY_MECHANISM.toString(), input);
	}

	public String getSecurityMechanism() {
		return getValue(Keys.SECURITY_MECHANISM.toString());
	}

	public void setServerName(String input) {
		setValue(Keys.SERVER_NAME.toString(), input);
	}

	public String getServerName() {
		return getValue(Keys.SERVER_NAME.toString());
	}

	public void setShutdownDatabase(String input) {
		setValue(Keys.SHUTDOWN_DATABASE.toString(), input);
	}

	public String getShutdownDatabase() {
		return getValue(Keys.SHUTDOWN_DATABASE.toString());
	}

	public void setSsl(String input) {
		setValue(Keys.SSL.toString(), input);
	}

	public String getSsl() {
		return getValue(Keys.SSL.toString());
	}

	public void setTraceDirectory(String input) {
		setValue(Keys.TRACE_DIRECTORY.toString(), input);
	}

	public String getTraceDirectory() {
		return getValue(Keys.TRACE_DIRECTORY.toString());
	}

	public void setTraceFile(String input) {
		setValue(Keys.TRACE_FILE.toString(), input);
	}

	public String getTraceFile() {
		return getValue(Keys.TRACE_FILE.toString());
	}

	public void setTraceFileAppend(String input) {
		setValue(Keys.TRACE_FILE_APPEND.toString(), input);
	}

	public String getTraceFileAppend() {
		return getValue(Keys.TRACE_FILE_APPEND.toString());
	}

	public void setTraceLevel(String input) {
		setValue(Keys.TRACE_LEVEL.toString(), input);
	}

	public String getTraceLevel() {
		return getValue(Keys.TRACE_LEVEL.toString());
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
