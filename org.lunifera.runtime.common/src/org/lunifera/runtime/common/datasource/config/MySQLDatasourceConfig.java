package org.lunifera.runtime.common.datasource.config;

import java.util.HashMap;

import org.lunifera.runtime.common.datasource.config.ClientDerbyDatasourceConfig.Keys;
import org.lunifera.runtime.common.util.OSGiUtil;

public class MySQLDatasourceConfig extends CommonDatasourceConfig {

	public MySQLDatasourceConfig() {
		super();
		DRIVER_NAME = "com.mysql.jdbc.Driver";
		properties.put(OSGI_DRIVER_NAME, DRIVER_NAME);
	}

	public enum Keys {
		ALLOW_LOAD_LOCAL_INFILE {
			public String toString() {
				return "allowLoadLocalInfile";
			}
		},

		ALLOW_MASTER_DOWN_CONNECTIONS {
			public String toString() {
				return "allowMasterDownConnections";
			}
		},

		ALLOW_MULTI_QUERIES {
			public String toString() {
				return "allowMultiQueries";
			}
		},

		ALLOW_NAN_AND_INF {
			public String toString() {
				return "allowNanAndInf";
			}
		},

		ALLOW_PUBLIC_KEY_RETRIEVAL {
			public String toString() {
				return "allowPublicKeyRetrieval";
			}
		},

		ALLOW_URL_IN_LOCAL_INFILE {
			public String toString() {
				return "allowUrlInLocalInfile";
			}
		},

		ALWAYS_SEND_SET_ISOLATION {
			public String toString() {
				return "alwaysSendSetIsolation";
			}
		},

		AUTHENTICATION_PLUGINS {
			public String toString() {
				return "authenticationPlugins";
			}
		},

		AUTO_CLOSE_P_STMT_STREAMS {
			public String toString() {
				return "autoClosePStmtStreams";
			}
		},

		AUTO_DESERIALIZE {
			public String toString() {
				return "autoDeserialize";
			}
		},

		AUTO_GENERATE_TESTCASE_SCRIPT {
			public String toString() {
				return "autoGenerateTestcaseScript";
			}
		},

		AUTO_RECONNECT {
			public String toString() {
				return "autoReconnect";
			}
		},

		AUTO_RECONNECT_FOR_POOLS {
			public String toString() {
				return "autoReconnectForPools";
			}
		},

		AUTO_SLOW_LOG {
			public String toString() {
				return "autoSlowLog";
			}
		},

		BLOB_SEND_CHUNK_SIZE {
			public String toString() {
				return "blobSendChunkSize";
			}
		},

		BLOBS_ARE_STRINGS {
			public String toString() {
				return "blobsAreStrings";
			}
		},

		CACHE_CALLABLE_STMTS {
			public String toString() {
				return "cacheCallableStmts";
			}
		},

		CACHE_DEFAULT_TIMEZONE {
			public String toString() {
				return "cacheDefaultTimezone";
			}
		},

		CACHE_PREP_STMTS {
			public String toString() {
				return "cachePrepStmts";
			}
		},

		CACHE_RESULT_SET_METADATA {
			public String toString() {
				return "cacheResultSetMetadata";
			}
		},

		CACHE_SERVER_CONFIGURATION {
			public String toString() {
				return "cacheServerConfiguration";
			}
		},

		CALLABLE_STMT_CACHE_SIZE {
			public String toString() {
				return "callableStmtCacheSize";
			}
		},

		CAPITALIZE_TYPE_NAMES {
			public String toString() {
				return "capitalizeTypeNames";
			}
		},

		CHARACTER_ENCODING {
			public String toString() {
				return "characterEncoding";
			}
		},

		CHARACTER_SET_RESULTS {
			public String toString() {
				return "characterSetResults";
			}
		},

		CLIENT_CERTIFICATE_KEY_STORE_PASSWORD {
			public String toString() {
				return "clientCertificateKeyStorePassword";
			}
		},

		CLIENT_CERTIFICATE_KEY_STORE_TYPE {
			public String toString() {
				return "clientCertificateKeyStoreType";
			}
		},

		CLIENT_CERTIFICATE_KEY_STORE_URL {
			public String toString() {
				return "clientCertificateKeyStoreUrl";
			}
		},

		CLIENT_INFO_PROVIDER {
			public String toString() {
				return "clientInfoProvider";
			}
		},

		CLOB_CHARACTER_ENCODING {
			public String toString() {
				return "clobCharacterEncoding";
			}
		},

		CLOBBER_STREAMING_RESULTS {
			public String toString() {
				return "clobberStreamingResults";
			}
		},

		COMPENSATE_ON_DUPLICATE_KEY_UPDATE_COUNTS {
			public String toString() {
				return "compensateOnDuplicateKeyUpdateCounts";
			}
		},

		CONNECT_TIMEOUT {
			public String toString() {
				return "connectTimeout";
			}
		},

		CONNECTION_ATTRIBUTES {
			public String toString() {
				return "connectionAttributes";
			}
		},

		CONNECTION_COLLATION {
			public String toString() {
				return "connectionCollation";
			}
		},

		CONNECTION_LIFECYCLE_INTERCEPTORS {
			public String toString() {
				return "connectionLifecycleInterceptors";
			}
		},

		CONTINUE_BATCH_ON_ERROR {
			public String toString() {
				return "continueBatchOnError";
			}
		},

		CREATE_DATABASE_IF_NOT_EXIST {
			public String toString() {
				return "createDatabaseIfNotExist";
			}
		},

		DEFAULT_AUTHENTICATION_PLUGIN {
			public String toString() {
				return "defaultAuthenticationPlugin";
			}
		},

		DEFAULT_FETCH_SIZE {
			public String toString() {
				return "defaultFetchSize";
			}
		},

		DETECT_CUSTOM_COLLATIONS {
			public String toString() {
				return "detectCustomCollations";
			}
		},

		DISABLED_AUTHENTICATION_PLUGINS {
			public String toString() {
				return "disabledAuthenticationPlugins";
			}
		},

		DISCONNECT_ON_EXPIRED_PASSWORDS {
			public String toString() {
				return "disconnectOnExpiredPasswords";
			}
		},

		DONT_CHECK_ON_DUPLICATE_KEY_UPDATE_IN_S_Q_L {
			public String toString() {
				return "dontCheckOnDuplicateKeyUpdateInSQL";
			}
		},

		DONT_TRACK_OPEN_RESOURCES {
			public String toString() {
				return "dontTrackOpenResources";
			}
		},

		DUMP_METADATA_ON_COLUMN_NOT_FOUND {
			public String toString() {
				return "dumpMetadataOnColumnNotFound";
			}
		},

		DUMP_QUERIES_ON_EXCEPTION {
			public String toString() {
				return "dumpQueriesOnException";
			}
		},

		DYNAMIC_CALENDARS {
			public String toString() {
				return "dynamicCalendars";
			}
		},

		ELIDE_SET_AUTO_COMMITS {
			public String toString() {
				return "elideSetAutoCommits";
			}
		},

		EMPTY_STRINGS_CONVERT_TO_ZERO {
			public String toString() {
				return "emptyStringsConvertToZero";
			}
		},

		EMULATE_LOCATORS {
			public String toString() {
				return "emulateLocators";
			}
		},

		EMULATE_UNSUPPORTED_PSTMTS {
			public String toString() {
				return "emulateUnsupportedPstmts";
			}
		},

		ENABLE_PACKET_DEBUG {
			public String toString() {
				return "enablePacketDebug";
			}
		},

		ENABLE_QUERY_TIMEOUTS {
			public String toString() {
				return "enableQueryTimeouts";
			}
		},

		EXCEPTION_INTERCEPTORS {
			public String toString() {
				return "exceptionInterceptors";
			}
		},

		EXPLAIN_SLOW_QUERIES {
			public String toString() {
				return "explainSlowQueries";
			}
		},

		FAIL_OVER_READ_ONLY {
			public String toString() {
				return "failOverReadOnly";
			}
		},

		FUNCTIONS_NEVER_RETURN_BLOBS {
			public String toString() {
				return "functionsNeverReturnBlobs";
			}
		},

		GATHER_PERF_METRICS {
			public String toString() {
				return "gatherPerfMetrics";
			}
		},

		GENERATE_SIMPLE_PARAMETER_METADATA {
			public String toString() {
				return "generateSimpleParameterMetadata";
			}
		},

		GET_PROCEDURES_RETURNS_FUNCTIONS {
			public String toString() {
				return "getProceduresReturnsFunctions";
			}
		},

		HOLD_RESULTS_OPEN_OVER_STATEMENT_CLOSE {
			public String toString() {
				return "holdResultsOpenOverStatementClose";
			}
		},

		IGNORE_NON_TX_TABLES {
			public String toString() {
				return "ignoreNonTxTables";
			}
		},

		INCLUDE_INNODB_STATUS_IN_DEADLOCK_EXCEPTIONS {
			public String toString() {
				return "includeInnodbStatusInDeadlockExceptions";
			}
		},

		INCLUDE_THREAD_DUMP_IN_DEADLOCK_EXCEPTIONS {
			public String toString() {
				return "includeThreadDumpInDeadlockExceptions";
			}
		},

		INCLUDE_THREAD_NAMES_AS_STATEMENT_COMMENT {
			public String toString() {
				return "includeThreadNamesAsStatementComment";
			}
		},

		INITIAL_TIMEOUT {
			public String toString() {
				return "initialTimeout";
			}
		},

		INTERACTIVE_CLIENT {
			public String toString() {
				return "interactiveClient";
			}
		},

		JDBC_COMPLIANT_TRUNCATION {
			public String toString() {
				return "jdbcCompliantTruncation";
			}
		},

		LARGE_ROW_SIZE_THRESHOLD {
			public String toString() {
				return "largeRowSizeThreshold";
			}
		},

		LOAD_BALANCE_AUTO_COMMIT_STATEMENT_REGEX {
			public String toString() {
				return "loadBalanceAutoCommitStatementRegex";
			}
		},

		LOAD_BALANCE_AUTO_COMMIT_STATEMENT_THRESHOLD {
			public String toString() {
				return "loadBalanceAutoCommitStatementThreshold";
			}
		},

		LOAD_BALANCE_BLACKLIST_TIMEOUT {
			public String toString() {
				return "loadBalanceBlacklistTimeout";
			}
		},

		LOAD_BALANCE_CONNECTION_GROUP {
			public String toString() {
				return "loadBalanceConnectionGroup";
			}
		},

		LOAD_BALANCE_ENABLE_J_M_X {
			public String toString() {
				return "loadBalanceEnableJMX";
			}
		},

		LOAD_BALANCE_EXCEPTION_CHECKER {
			public String toString() {
				return "loadBalanceExceptionChecker";
			}
		},

		LOAD_BALANCE_PING_TIMEOUT {
			public String toString() {
				return "loadBalancePingTimeout";
			}
		},

		LOAD_BALANCE_S_Q_L_EXCEPTION_SUBCLASS_FAILOVER {
			public String toString() {
				return "loadBalanceSQLExceptionSubclassFailover";
			}
		},

		LOAD_BALANCE_S_Q_L_STATE_FAILOVER {
			public String toString() {
				return "loadBalanceSQLStateFailover";
			}
		},

		LOAD_BALANCE_STRATEGY {
			public String toString() {
				return "loadBalanceStrategy";
			}
		},

		LOAD_BALANCE_VALIDATE_CONNECTION_ON_SWAP_SERVER {
			public String toString() {
				return "loadBalanceValidateConnectionOnSwapServer";
			}
		},

		LOCAL_SOCKET_ADDRESS {
			public String toString() {
				return "localSocketAddress";
			}
		},

		LOCATOR_FETCH_BUFFER_SIZE {
			public String toString() {
				return "locatorFetchBufferSize";
			}
		},

		LOG_SLOW_QUERIES {
			public String toString() {
				return "logSlowQueries";
			}
		},

		LOG_XA_COMMANDS {
			public String toString() {
				return "logXaCommands";
			}
		},

		LOGGER {
			public String toString() {
				return "logger";
			}
		},

		MAINTAIN_TIME_STATS {
			public String toString() {
				return "maintainTimeStats";
			}
		},

		MAX_ALLOWED_PACKET {
			public String toString() {
				return "maxAllowedPacket";
			}
		},

		MAX_QUERY_SIZE_TO_LOG {
			public String toString() {
				return "maxQuerySizeToLog";
			}
		},

		MAX_RECONNECTS {
			public String toString() {
				return "maxReconnects";
			}
		},

		MAX_ROWS {
			public String toString() {
				return "maxRows";
			}
		},

		METADATA_CACHE_SIZE {
			public String toString() {
				return "metadataCacheSize";
			}
		},

		NET_TIMEOUT_FOR_STREAMING_RESULTS {
			public String toString() {
				return "netTimeoutForStreamingResults";
			}
		},

		NO_ACCESS_TO_PROCEDURE_BODIES {
			public String toString() {
				return "noAccessToProcedureBodies";
			}
		},

		NO_DATETIME_STRING_SYNC {
			public String toString() {
				return "noDatetimeStringSync";
			}
		},

		NO_TIMEZONE_CONVERSION_FOR_DATE_TYPE {
			public String toString() {
				return "noTimezoneConversionForDateType";
			}
		},

		NO_TIMEZONE_CONVERSION_FOR_TIME_TYPE {
			public String toString() {
				return "noTimezoneConversionForTimeType";
			}
		},

		NULL_CATALOG_MEANS_CURRENT {
			public String toString() {
				return "nullCatalogMeansCurrent";
			}
		},

		NULL_NAME_PATTERN_MATCHES_ALL {
			public String toString() {
				return "nullNamePatternMatchesAll";
			}
		},

		OVERRIDE_SUPPORTS_INTEGRITY_ENHANCEMENT_FACILITY {
			public String toString() {
				return "overrideSupportsIntegrityEnhancementFacility";
			}
		},

		PACKET_DEBUG_BUFFER_SIZE {
			public String toString() {
				return "packetDebugBufferSize";
			}
		},

		PAD_CHARS_WITH_SPACE {
			public String toString() {
				return "padCharsWithSpace";
			}
		},

		PARANOID {
			public String toString() {
				return "paranoid";
			}
		},

		PARSE_INFO_CACHE_FACTORY {
			public String toString() {
				return "parseInfoCacheFactory";
			}
		},

		PASSWORD {
			public String toString() {
				return "password";
			}
		},

		PASSWORD_CHARACTER_ENCODING {
			public String toString() {
				return "passwordCharacterEncoding";
			}
		},

		PEDANTIC {
			public String toString() {
				return "pedantic";
			}
		},

		PIN_GLOBAL_TX_TO_PHYSICAL_CONNECTION {
			public String toString() {
				return "pinGlobalTxToPhysicalConnection";
			}
		},

		POPULATE_INSERT_ROW_WITH_DEFAULT_VALUES {
			public String toString() {
				return "populateInsertRowWithDefaultValues";
			}
		},

		PREP_STMT_CACHE_SIZE {
			public String toString() {
				return "prepStmtCacheSize";
			}
		},

		PREP_STMT_CACHE_SQL_LIMIT {
			public String toString() {
				return "prepStmtCacheSqlLimit";
			}
		},

		PROCESS_ESCAPE_CODES_FOR_PREP_STMTS {
			public String toString() {
				return "processEscapeCodesForPrepStmts";
			}
		},

		PROFILE_S_Q_L {
			public String toString() {
				return "profileSQL";
			}
		},

		PROFILE_SQL {
			public String toString() {
				return "profileSql";
			}
		},

		PROFILER_EVENT_HANDLER {
			public String toString() {
				return "profilerEventHandler";
			}
		},

		PROPERTIES_TRANSFORM {
			public String toString() {
				return "propertiesTransform";
			}
		},

		QUERIES_BEFORE_RETRY_MASTER {
			public String toString() {
				return "queriesBeforeRetryMaster";
			}
		},

		QUERY_TIMEOUT_KILLS_CONNECTION {
			public String toString() {
				return "queryTimeoutKillsConnection";
			}
		},

		READ_ONLY_PROPAGATES_TO_SERVER {
			public String toString() {
				return "readOnlyPropagatesToServer";
			}
		},

		RECONNECT_AT_TX_END {
			public String toString() {
				return "reconnectAtTxEnd";
			}
		},

		RELAX_AUTO_COMMIT {
			public String toString() {
				return "relaxAutoCommit";
			}
		},

		REPLICATION_ENABLE_J_M_X {
			public String toString() {
				return "replicationEnableJMX";
			}
		},

		REPORT_METRICS_INTERVAL_MILLIS {
			public String toString() {
				return "reportMetricsIntervalMillis";
			}
		},

		REQUIRE_S_S_L {
			public String toString() {
				return "requireSSL";
			}
		},

		RESOURCE_ID {
			public String toString() {
				return "resourceId";
			}
		},

		RESULT_SET_SIZE_THRESHOLD {
			public String toString() {
				return "resultSetSizeThreshold";
			}
		},

		RETAIN_STATEMENT_AFTER_RESULT_SET_CLOSE {
			public String toString() {
				return "retainStatementAfterResultSetClose";
			}
		},

		RETRIES_ALL_DOWN {
			public String toString() {
				return "retriesAllDown";
			}
		},

		REWRITE_BATCHED_STATEMENTS {
			public String toString() {
				return "rewriteBatchedStatements";
			}
		},

		ROLLBACK_ON_POOLED_CLOSE {
			public String toString() {
				return "rollbackOnPooledClose";
			}
		},

		ROUND_ROBIN_LOAD_BALANCE {
			public String toString() {
				return "roundRobinLoadBalance";
			}
		},

		RUNNING_C_T_S13 {
			public String toString() {
				return "runningCTS13";
			}
		},

		SECONDS_BEFORE_RETRY_MASTER {
			public String toString() {
				return "secondsBeforeRetryMaster";
			}
		},

		SELF_DESTRUCT_ON_PING_MAX_OPERATIONS {
			public String toString() {
				return "selfDestructOnPingMaxOperations";
			}
		},

		SELF_DESTRUCT_ON_PING_SECONDS_LIFETIME {
			public String toString() {
				return "selfDestructOnPingSecondsLifetime";
			}
		},

		SERVER_CONFIG_CACHE_FACTORY {
			public String toString() {
				return "serverConfigCacheFactory";
			}
		},

		SERVER_R_S_A_PUBLIC_KEY_FILE {
			public String toString() {
				return "serverRSAPublicKeyFile";
			}
		},

		SERVER_TIMEZONE {
			public String toString() {
				return "serverTimezone";
			}
		},

		SESSION_VARIABLES {
			public String toString() {
				return "sessionVariables";
			}
		},

		SLOW_QUERY_THRESHOLD_MILLIS {
			public String toString() {
				return "slowQueryThresholdMillis";
			}
		},

		SLOW_QUERY_THRESHOLD_NANOS {
			public String toString() {
				return "slowQueryThresholdNanos";
			}
		},

		SOCKET_FACTORY {
			public String toString() {
				return "socketFactory";
			}
		},

		SOCKET_TIMEOUT {
			public String toString() {
				return "socketTimeout";
			}
		},

		SOCKS_PROXY_HOST {
			public String toString() {
				return "socksProxyHost";
			}
		},

		SOCKS_PROXY_PORT {
			public String toString() {
				return "socksProxyPort";
			}
		},

		STATEMENT_INTERCEPTORS {
			public String toString() {
				return "statementInterceptors";
			}
		},

		STRICT_FLOATING_POINT {
			public String toString() {
				return "strictFloatingPoint";
			}
		},

		STRICT_UPDATES {
			public String toString() {
				return "strictUpdates";
			}
		},

		TCP_KEEP_ALIVE {
			public String toString() {
				return "tcpKeepAlive";
			}
		},

		TCP_NO_DELAY {
			public String toString() {
				return "tcpNoDelay";
			}
		},

		TCP_RCV_BUF {
			public String toString() {
				return "tcpRcvBuf";
			}
		},

		TCP_SND_BUF {
			public String toString() {
				return "tcpSndBuf";
			}
		},

		TCP_TRAFFIC_CLASS {
			public String toString() {
				return "tcpTrafficClass";
			}
		},

		TINY_INT1IS_BIT {
			public String toString() {
				return "tinyInt1isBit";
			}
		},

		TRACE_PROTOCOL {
			public String toString() {
				return "traceProtocol";
			}
		},

		TRANSFORMED_BIT_IS_BOOLEAN {
			public String toString() {
				return "transformedBitIsBoolean";
			}
		},

		TREAT_UTIL_DATE_AS_TIMESTAMP {
			public String toString() {
				return "treatUtilDateAsTimestamp";
			}
		},

		TRUST_CERTIFICATE_KEY_STORE_PASSWORD {
			public String toString() {
				return "trustCertificateKeyStorePassword";
			}
		},

		TRUST_CERTIFICATE_KEY_STORE_TYPE {
			public String toString() {
				return "trustCertificateKeyStoreType";
			}
		},

		TRUST_CERTIFICATE_KEY_STORE_URL {
			public String toString() {
				return "trustCertificateKeyStoreUrl";
			}
		},

		ULTRA_DEV_HACK {
			public String toString() {
				return "ultraDevHack";
			}
		},

		USE_AFFECTED_ROWS {
			public String toString() {
				return "useAffectedRows";
			}
		},

		USE_BLOB_TO_STORE_U_T_F8_OUTSIDE_B_M_P {
			public String toString() {
				return "useBlobToStoreUTF8OutsideBMP";
			}
		},

		USE_COLUMN_NAMES_IN_FIND_COLUMN {
			public String toString() {
				return "useColumnNamesInFindColumn";
			}
		},

		USE_COMPRESSION {
			public String toString() {
				return "useCompression";
			}
		},

		USE_CONFIGS {
			public String toString() {
				return "useConfigs";
			}
		},

		USE_CURSOR_FETCH {
			public String toString() {
				return "useCursorFetch";
			}
		},

		USE_DIRECT_ROW_UNPACK {
			public String toString() {
				return "useDirectRowUnpack";
			}
		},

		USE_DYNAMIC_CHARSET_INFO {
			public String toString() {
				return "useDynamicCharsetInfo";
			}
		},

		USE_FAST_DATE_PARSING {
			public String toString() {
				return "useFastDateParsing";
			}
		},

		USE_FAST_INT_PARSING {
			public String toString() {
				return "useFastIntParsing";
			}
		},

		USE_GMT_MILLIS_FOR_DATETIMES {
			public String toString() {
				return "useGmtMillisForDatetimes";
			}
		},

		USE_HOSTS_IN_PRIVILEGES {
			public String toString() {
				return "useHostsInPrivileges";
			}
		},

		USE_INFORMATION_SCHEMA {
			public String toString() {
				return "useInformationSchema";
			}
		},

		USE_J_D_B_C_COMPLIANT_TIMEZONE_SHIFT {
			public String toString() {
				return "useJDBCCompliantTimezoneShift";
			}
		},

		USE_JVM_CHARSET_CONVERTERS {
			public String toString() {
				return "useJvmCharsetConverters";
			}
		},

		USE_LEGACY_DATETIME_CODE {
			public String toString() {
				return "useLegacyDatetimeCode";
			}
		},

		USE_LOCAL_SESSION_STATE {
			public String toString() {
				return "useLocalSessionState";
			}
		},

		USE_LOCAL_TRANSACTION_STATE {
			public String toString() {
				return "useLocalTransactionState";
			}
		},

		USE_NANOS_FOR_ELAPSED_TIME {
			public String toString() {
				return "useNanosForElapsedTime";
			}
		},

		USE_OLD_ALIAS_METADATA_BEHAVIOR {
			public String toString() {
				return "useOldAliasMetadataBehavior";
			}
		},

		USE_OLD_U_T_F8_BEHAVIOR {
			public String toString() {
				return "useOldUTF8Behavior";
			}
		},

		USE_ONLY_SERVER_ERROR_MESSAGES {
			public String toString() {
				return "useOnlyServerErrorMessages";
			}
		},

		USE_READ_AHEAD_INPUT {
			public String toString() {
				return "useReadAheadInput";
			}
		},

		USE_S_S_L {
			public String toString() {
				return "useSSL";
			}
		},

		USE_S_S_P_S_COMPATIBLE_TIMEZONE_SHIFT {
			public String toString() {
				return "useSSPSCompatibleTimezoneShift";
			}
		},

		USE_SERVER_PREP_STMTS {
			public String toString() {
				return "useServerPrepStmts";
			}
		},

		USE_SQL_STATE_CODES {
			public String toString() {
				return "useSqlStateCodes";
			}
		},

		USE_STREAM_LENGTHS_IN_PREP_STMTS {
			public String toString() {
				return "useStreamLengthsInPrepStmts";
			}
		},

		USE_TIMEZONE {
			public String toString() {
				return "useTimezone";
			}
		},

		USE_UNBUFFERED_INPUT {
			public String toString() {
				return "useUnbufferedInput";
			}
		},

		USE_UNICODE {
			public String toString() {
				return "useUnicode";
			}
		},

		USE_USAGE_ADVISOR {
			public String toString() {
				return "useUsageAdvisor";
			}
		},

		USER {
			public String toString() {
				return "user";
			}
		},

		UTF8_OUTSIDE_BMP_EXCLUDED_COLUMN_NAME_PATTERN {
			public String toString() {
				return "utf8OutsideBmpExcludedColumnNamePattern";
			}
		},

		UTF8_OUTSIDE_BMP_INCLUDED_COLUMN_NAME_PATTERN {
			public String toString() {
				return "utf8OutsideBmpIncludedColumnNamePattern";
			}
		},

		VERIFY_SERVER_CERTIFICATE {
			public String toString() {
				return "verifyServerCertificate";
			}
		},

		ZERO_DATE_TIME_BEHAVIOR {
			public String toString() {
				return "zeroDateTimeBehavior";
			}
		}
	}

	public void setAllowLoadLocalInfile(String input) {
		setValue(Keys.ALLOW_LOAD_LOCAL_INFILE.toString(), input);
	}

	public String getAllowLoadLocalInfile() {
		return getValue(Keys.ALLOW_LOAD_LOCAL_INFILE.toString());
	};

	public void setAllowMasterDownConnections(String input) {
		setValue(Keys.ALLOW_MASTER_DOWN_CONNECTIONS.toString(), input);
	}

	public String getAllowMasterDownConnections() {
		return getValue(Keys.ALLOW_MASTER_DOWN_CONNECTIONS.toString());
	};

	public void setAllowMultiQueries(String input) {
		setValue(Keys.ALLOW_MULTI_QUERIES.toString(), input);
	}

	public String getAllowMultiQueries() {
		return getValue(Keys.ALLOW_MULTI_QUERIES.toString());
	};

	public void setAllowNanAndInf(String input) {
		setValue(Keys.ALLOW_NAN_AND_INF.toString(), input);
	}

	public String getAllowNanAndInf() {
		return getValue(Keys.ALLOW_NAN_AND_INF.toString());
	};

	public void setAllowPublicKeyRetrieval(String input) {
		setValue(Keys.ALLOW_PUBLIC_KEY_RETRIEVAL.toString(), input);
	}

	public String getAllowPublicKeyRetrieval() {
		return getValue(Keys.ALLOW_PUBLIC_KEY_RETRIEVAL.toString());
	};

	public void setAllowUrlInLocalInfile(String input) {
		setValue(Keys.ALLOW_URL_IN_LOCAL_INFILE.toString(), input);
	}

	public String getAllowUrlInLocalInfile() {
		return getValue(Keys.ALLOW_URL_IN_LOCAL_INFILE.toString());
	};

	public void setAlwaysSendSetIsolation(String input) {
		setValue(Keys.ALWAYS_SEND_SET_ISOLATION.toString(), input);
	}

	public String getAlwaysSendSetIsolation() {
		return getValue(Keys.ALWAYS_SEND_SET_ISOLATION.toString());
	};

	public void setAuthenticationPlugins(String input) {
		setValue(Keys.AUTHENTICATION_PLUGINS.toString(), input);
	}

	public String getAuthenticationPlugins() {
		return getValue(Keys.AUTHENTICATION_PLUGINS.toString());
	};

	public void setAutoClosePStmtStreams(String input) {
		setValue(Keys.AUTO_CLOSE_P_STMT_STREAMS.toString(), input);
	}

	public String getAutoClosePStmtStreams() {
		return getValue(Keys.AUTO_CLOSE_P_STMT_STREAMS.toString());
	};

	public void setAutoDeserialize(String input) {
		setValue(Keys.AUTO_DESERIALIZE.toString(), input);
	}

	public String getAutoDeserialize() {
		return getValue(Keys.AUTO_DESERIALIZE.toString());
	};

	public void setAutoGenerateTestcaseScript(String input) {
		setValue(Keys.AUTO_GENERATE_TESTCASE_SCRIPT.toString(), input);
	}

	public String getAutoGenerateTestcaseScript() {
		return getValue(Keys.AUTO_GENERATE_TESTCASE_SCRIPT.toString());
	};

	public void setAutoReconnect(String input) {
		setValue(Keys.AUTO_RECONNECT.toString(), input);
	}

	public String getAutoReconnect() {
		return getValue(Keys.AUTO_RECONNECT.toString());
	};

	public void setAutoReconnectForPools(String input) {
		setValue(Keys.AUTO_RECONNECT_FOR_POOLS.toString(), input);
	}

	public String getAutoReconnectForPools() {
		return getValue(Keys.AUTO_RECONNECT_FOR_POOLS.toString());
	};

	public void setAutoSlowLog(String input) {
		setValue(Keys.AUTO_SLOW_LOG.toString(), input);
	}

	public String getAutoSlowLog() {
		return getValue(Keys.AUTO_SLOW_LOG.toString());
	};

	public void setBlobSendChunkSize(String input) {
		setValue(Keys.BLOB_SEND_CHUNK_SIZE.toString(), input);
	}

	public String getBlobSendChunkSize() {
		return getValue(Keys.BLOB_SEND_CHUNK_SIZE.toString());
	};

	public void setBlobsAreStrings(String input) {
		setValue(Keys.BLOBS_ARE_STRINGS.toString(), input);
	}

	public String getBlobsAreStrings() {
		return getValue(Keys.BLOBS_ARE_STRINGS.toString());
	};

	public void setCacheCallableStmts(String input) {
		setValue(Keys.CACHE_CALLABLE_STMTS.toString(), input);
	}

	public String getCacheCallableStmts() {
		return getValue(Keys.CACHE_CALLABLE_STMTS.toString());
	};

	public void setCacheDefaultTimezone(String input) {
		setValue(Keys.CACHE_DEFAULT_TIMEZONE.toString(), input);
	}

	public String getCacheDefaultTimezone() {
		return getValue(Keys.CACHE_DEFAULT_TIMEZONE.toString());
	};

	public void setCachePrepStmts(String input) {
		setValue(Keys.CACHE_PREP_STMTS.toString(), input);
	}

	public String getCachePrepStmts() {
		return getValue(Keys.CACHE_PREP_STMTS.toString());
	};

	public void setCacheResultSetMetadata(String input) {
		setValue(Keys.CACHE_RESULT_SET_METADATA.toString(), input);
	}

	public String getCacheResultSetMetadata() {
		return getValue(Keys.CACHE_RESULT_SET_METADATA.toString());
	};

	public void setCacheServerConfiguration(String input) {
		setValue(Keys.CACHE_SERVER_CONFIGURATION.toString(), input);
	}

	public String getCacheServerConfiguration() {
		return getValue(Keys.CACHE_SERVER_CONFIGURATION.toString());
	};

	public void setCallableStmtCacheSize(String input) {
		setValue(Keys.CALLABLE_STMT_CACHE_SIZE.toString(), input);
	}

	public String getCallableStmtCacheSize() {
		return getValue(Keys.CALLABLE_STMT_CACHE_SIZE.toString());
	};

	public void setCapitalizeTypeNames(String input) {
		setValue(Keys.CAPITALIZE_TYPE_NAMES.toString(), input);
	}

	public String getCapitalizeTypeNames() {
		return getValue(Keys.CAPITALIZE_TYPE_NAMES.toString());
	};

	public void setCharacterEncoding(String input) {
		setValue(Keys.CHARACTER_ENCODING.toString(), input);
	}

	public String getCharacterEncoding() {
		return getValue(Keys.CHARACTER_ENCODING.toString());
	};

	public void setCharacterSetResults(String input) {
		setValue(Keys.CHARACTER_SET_RESULTS.toString(), input);
	}

	public String getCharacterSetResults() {
		return getValue(Keys.CHARACTER_SET_RESULTS.toString());
	};

	public void setClientCertificateKeyStorePassword(String input) {
		setValue(Keys.CLIENT_CERTIFICATE_KEY_STORE_PASSWORD.toString(), input);
	}

	public String getClientCertificateKeyStorePassword() {
		return getValue(Keys.CLIENT_CERTIFICATE_KEY_STORE_PASSWORD.toString());
	};

	public void setClientCertificateKeyStoreType(String input) {
		setValue(Keys.CLIENT_CERTIFICATE_KEY_STORE_TYPE.toString(), input);
	}

	public String getClientCertificateKeyStoreType() {
		return getValue(Keys.CLIENT_CERTIFICATE_KEY_STORE_TYPE.toString());
	};

	public void setClientCertificateKeyStoreUrl(String input) {
		setValue(Keys.CLIENT_CERTIFICATE_KEY_STORE_URL.toString(), input);
	}

	public String getClientCertificateKeyStoreUrl() {
		return getValue(Keys.CLIENT_CERTIFICATE_KEY_STORE_URL.toString());
	};

	public void setClientInfoProvider(String input) {
		setValue(Keys.CLIENT_INFO_PROVIDER.toString(), input);
	}

	public String getClientInfoProvider() {
		return getValue(Keys.CLIENT_INFO_PROVIDER.toString());
	};

	public void setClobCharacterEncoding(String input) {
		setValue(Keys.CLOB_CHARACTER_ENCODING.toString(), input);
	}

	public String getClobCharacterEncoding() {
		return getValue(Keys.CLOB_CHARACTER_ENCODING.toString());
	};

	public void setClobberStreamingResults(String input) {
		setValue(Keys.CLOBBER_STREAMING_RESULTS.toString(), input);
	}

	public String getClobberStreamingResults() {
		return getValue(Keys.CLOBBER_STREAMING_RESULTS.toString());
	};

	public void setCompensateOnDuplicateKeyUpdateCounts(String input) {
		setValue(Keys.COMPENSATE_ON_DUPLICATE_KEY_UPDATE_COUNTS.toString(),
				input);
	}

	public String getCompensateOnDuplicateKeyUpdateCounts() {
		return getValue(Keys.COMPENSATE_ON_DUPLICATE_KEY_UPDATE_COUNTS
				.toString());
	};

	public void setConnectTimeout(String input) {
		setValue(Keys.CONNECT_TIMEOUT.toString(), input);
	}

	public String getConnectTimeout() {
		return getValue(Keys.CONNECT_TIMEOUT.toString());
	};

	public void setConnectionAttributes(String input) {
		setValue(Keys.CONNECTION_ATTRIBUTES.toString(), input);
	}

	public String getConnectionAttributes() {
		return getValue(Keys.CONNECTION_ATTRIBUTES.toString());
	};

	public void setConnectionCollation(String input) {
		setValue(Keys.CONNECTION_COLLATION.toString(), input);
	}

	public String getConnectionCollation() {
		return getValue(Keys.CONNECTION_COLLATION.toString());
	};

	public void setConnectionLifecycleInterceptors(String input) {
		setValue(Keys.CONNECTION_LIFECYCLE_INTERCEPTORS.toString(), input);
	}

	public String getConnectionLifecycleInterceptors() {
		return getValue(Keys.CONNECTION_LIFECYCLE_INTERCEPTORS.toString());
	};

	public void setContinueBatchOnError(String input) {
		setValue(Keys.CONTINUE_BATCH_ON_ERROR.toString(), input);
	}

	public String getContinueBatchOnError() {
		return getValue(Keys.CONTINUE_BATCH_ON_ERROR.toString());
	};

	public void setCreateDatabaseIfNotExist(String input) {
		setValue(Keys.CREATE_DATABASE_IF_NOT_EXIST.toString(), input);
	}

	public String getCreateDatabaseIfNotExist() {
		return getValue(Keys.CREATE_DATABASE_IF_NOT_EXIST.toString());
	};

	public void setDefaultAuthenticationPlugin(String input) {
		setValue(Keys.DEFAULT_AUTHENTICATION_PLUGIN.toString(), input);
	}

	public String getDefaultAuthenticationPlugin() {
		return getValue(Keys.DEFAULT_AUTHENTICATION_PLUGIN.toString());
	};

	public void setDefaultFetchSize(String input) {
		setValue(Keys.DEFAULT_FETCH_SIZE.toString(), input);
	}

	public String getDefaultFetchSize() {
		return getValue(Keys.DEFAULT_FETCH_SIZE.toString());
	};

	public void setDetectCustomCollations(String input) {
		setValue(Keys.DETECT_CUSTOM_COLLATIONS.toString(), input);
	}

	public String getDetectCustomCollations() {
		return getValue(Keys.DETECT_CUSTOM_COLLATIONS.toString());
	};

	public void setDisabledAuthenticationPlugins(String input) {
		setValue(Keys.DISABLED_AUTHENTICATION_PLUGINS.toString(), input);
	}

	public String getDisabledAuthenticationPlugins() {
		return getValue(Keys.DISABLED_AUTHENTICATION_PLUGINS.toString());
	};

	public void setDisconnectOnExpiredPasswords(String input) {
		setValue(Keys.DISCONNECT_ON_EXPIRED_PASSWORDS.toString(), input);
	}

	public String getDisconnectOnExpiredPasswords() {
		return getValue(Keys.DISCONNECT_ON_EXPIRED_PASSWORDS.toString());
	};

	public void setDontCheckOnDuplicateKeyUpdateInSQL(String input) {
		setValue(Keys.DONT_CHECK_ON_DUPLICATE_KEY_UPDATE_IN_S_Q_L.toString(),
				input);
	}

	public String getDontCheckOnDuplicateKeyUpdateInSQL() {
		return getValue(Keys.DONT_CHECK_ON_DUPLICATE_KEY_UPDATE_IN_S_Q_L
				.toString());
	};

	public void setDontTrackOpenResources(String input) {
		setValue(Keys.DONT_TRACK_OPEN_RESOURCES.toString(), input);
	}

	public String getDontTrackOpenResources() {
		return getValue(Keys.DONT_TRACK_OPEN_RESOURCES.toString());
	};

	public void setDumpMetadataOnColumnNotFound(String input) {
		setValue(Keys.DUMP_METADATA_ON_COLUMN_NOT_FOUND.toString(), input);
	}

	public String getDumpMetadataOnColumnNotFound() {
		return getValue(Keys.DUMP_METADATA_ON_COLUMN_NOT_FOUND.toString());
	};

	public void setDumpQueriesOnException(String input) {
		setValue(Keys.DUMP_QUERIES_ON_EXCEPTION.toString(), input);
	}

	public String getDumpQueriesOnException() {
		return getValue(Keys.DUMP_QUERIES_ON_EXCEPTION.toString());
	};

	public void setDynamicCalendars(String input) {
		setValue(Keys.DYNAMIC_CALENDARS.toString(), input);
	}

	public String getDynamicCalendars() {
		return getValue(Keys.DYNAMIC_CALENDARS.toString());
	};

	public void setElideSetAutoCommits(String input) {
		setValue(Keys.ELIDE_SET_AUTO_COMMITS.toString(), input);
	}

	public String getElideSetAutoCommits() {
		return getValue(Keys.ELIDE_SET_AUTO_COMMITS.toString());
	};

	public void setEmptyStringsConvertToZero(String input) {
		setValue(Keys.EMPTY_STRINGS_CONVERT_TO_ZERO.toString(), input);
	}

	public String getEmptyStringsConvertToZero() {
		return getValue(Keys.EMPTY_STRINGS_CONVERT_TO_ZERO.toString());
	};

	public void setEmulateLocators(String input) {
		setValue(Keys.EMULATE_LOCATORS.toString(), input);
	}

	public String getEmulateLocators() {
		return getValue(Keys.EMULATE_LOCATORS.toString());
	};

	public void setEmulateUnsupportedPstmts(String input) {
		setValue(Keys.EMULATE_UNSUPPORTED_PSTMTS.toString(), input);
	}

	public String getEmulateUnsupportedPstmts() {
		return getValue(Keys.EMULATE_UNSUPPORTED_PSTMTS.toString());
	};

	public void setEnablePacketDebug(String input) {
		setValue(Keys.ENABLE_PACKET_DEBUG.toString(), input);
	}

	public String getEnablePacketDebug() {
		return getValue(Keys.ENABLE_PACKET_DEBUG.toString());
	};

	public void setEnableQueryTimeouts(String input) {
		setValue(Keys.ENABLE_QUERY_TIMEOUTS.toString(), input);
	}

	public String getEnableQueryTimeouts() {
		return getValue(Keys.ENABLE_QUERY_TIMEOUTS.toString());
	};

	public void setExceptionInterceptors(String input) {
		setValue(Keys.EXCEPTION_INTERCEPTORS.toString(), input);
	}

	public String getExceptionInterceptors() {
		return getValue(Keys.EXCEPTION_INTERCEPTORS.toString());
	};

	public void setExplainSlowQueries(String input) {
		setValue(Keys.EXPLAIN_SLOW_QUERIES.toString(), input);
	}

	public String getExplainSlowQueries() {
		return getValue(Keys.EXPLAIN_SLOW_QUERIES.toString());
	};

	public void setFailOverReadOnly(String input) {
		setValue(Keys.FAIL_OVER_READ_ONLY.toString(), input);
	}

	public String getFailOverReadOnly() {
		return getValue(Keys.FAIL_OVER_READ_ONLY.toString());
	};

	public void setFunctionsNeverReturnBlobs(String input) {
		setValue(Keys.FUNCTIONS_NEVER_RETURN_BLOBS.toString(), input);
	}

	public String getFunctionsNeverReturnBlobs() {
		return getValue(Keys.FUNCTIONS_NEVER_RETURN_BLOBS.toString());
	};

	public void setGatherPerfMetrics(String input) {
		setValue(Keys.GATHER_PERF_METRICS.toString(), input);
	}

	public String getGatherPerfMetrics() {
		return getValue(Keys.GATHER_PERF_METRICS.toString());
	};

	public void setGenerateSimpleParameterMetadata(String input) {
		setValue(Keys.GENERATE_SIMPLE_PARAMETER_METADATA.toString(), input);
	}

	public String getGenerateSimpleParameterMetadata() {
		return getValue(Keys.GENERATE_SIMPLE_PARAMETER_METADATA.toString());
	};

	public void setGetProceduresReturnsFunctions(String input) {
		setValue(Keys.GET_PROCEDURES_RETURNS_FUNCTIONS.toString(), input);
	}

	public String getGetProceduresReturnsFunctions() {
		return getValue(Keys.GET_PROCEDURES_RETURNS_FUNCTIONS.toString());
	};

	public void setHoldResultsOpenOverStatementClose(String input) {
		setValue(Keys.HOLD_RESULTS_OPEN_OVER_STATEMENT_CLOSE.toString(), input);
	}

	public String getHoldResultsOpenOverStatementClose() {
		return getValue(Keys.HOLD_RESULTS_OPEN_OVER_STATEMENT_CLOSE.toString());
	};

	public void setIgnoreNonTxTables(String input) {
		setValue(Keys.IGNORE_NON_TX_TABLES.toString(), input);
	}

	public String getIgnoreNonTxTables() {
		return getValue(Keys.IGNORE_NON_TX_TABLES.toString());
	};

	public void setIncludeInnodbStatusInDeadlockExceptions(String input) {
		setValue(Keys.INCLUDE_INNODB_STATUS_IN_DEADLOCK_EXCEPTIONS.toString(),
				input);
	}

	public String getIncludeInnodbStatusInDeadlockExceptions() {
		return getValue(Keys.INCLUDE_INNODB_STATUS_IN_DEADLOCK_EXCEPTIONS
				.toString());
	};

	public void setIncludeThreadDumpInDeadlockExceptions(String input) {
		setValue(Keys.INCLUDE_THREAD_DUMP_IN_DEADLOCK_EXCEPTIONS.toString(),
				input);
	}

	public String getIncludeThreadDumpInDeadlockExceptions() {
		return getValue(Keys.INCLUDE_THREAD_DUMP_IN_DEADLOCK_EXCEPTIONS
				.toString());
	};

	public void setIncludeThreadNamesAsStatementComment(String input) {
		setValue(Keys.INCLUDE_THREAD_NAMES_AS_STATEMENT_COMMENT.toString(),
				input);
	}

	public String getIncludeThreadNamesAsStatementComment() {
		return getValue(Keys.INCLUDE_THREAD_NAMES_AS_STATEMENT_COMMENT
				.toString());
	};

	public void setInitialTimeout(String input) {
		setValue(Keys.INITIAL_TIMEOUT.toString(), input);
	}

	public String getInitialTimeout() {
		return getValue(Keys.INITIAL_TIMEOUT.toString());
	};

	public void setInteractiveClient(String input) {
		setValue(Keys.INTERACTIVE_CLIENT.toString(), input);
	}

	public String getInteractiveClient() {
		return getValue(Keys.INTERACTIVE_CLIENT.toString());
	};

	public void setJdbcCompliantTruncation(String input) {
		setValue(Keys.JDBC_COMPLIANT_TRUNCATION.toString(), input);
	}

	public String getJdbcCompliantTruncation() {
		return getValue(Keys.JDBC_COMPLIANT_TRUNCATION.toString());
	};

	public void setLargeRowSizeThreshold(String input) {
		setValue(Keys.LARGE_ROW_SIZE_THRESHOLD.toString(), input);
	}

	public String getLargeRowSizeThreshold() {
		return getValue(Keys.LARGE_ROW_SIZE_THRESHOLD.toString());
	};

	public void setLoadBalanceAutoCommitStatementRegex(String input) {
		setValue(Keys.LOAD_BALANCE_AUTO_COMMIT_STATEMENT_REGEX.toString(),
				input);
	}

	public String getLoadBalanceAutoCommitStatementRegex() {
		return getValue(Keys.LOAD_BALANCE_AUTO_COMMIT_STATEMENT_REGEX
				.toString());
	};

	public void setLoadBalanceAutoCommitStatementThreshold(String input) {
		setValue(Keys.LOAD_BALANCE_AUTO_COMMIT_STATEMENT_THRESHOLD.toString(),
				input);
	}

	public String getLoadBalanceAutoCommitStatementThreshold() {
		return getValue(Keys.LOAD_BALANCE_AUTO_COMMIT_STATEMENT_THRESHOLD
				.toString());
	};

	public void setLoadBalanceBlacklistTimeout(String input) {
		setValue(Keys.LOAD_BALANCE_BLACKLIST_TIMEOUT.toString(), input);
	}

	public String getLoadBalanceBlacklistTimeout() {
		return getValue(Keys.LOAD_BALANCE_BLACKLIST_TIMEOUT.toString());
	};

	public void setLoadBalanceConnectionGroup(String input) {
		setValue(Keys.LOAD_BALANCE_CONNECTION_GROUP.toString(), input);
	}

	public String getLoadBalanceConnectionGroup() {
		return getValue(Keys.LOAD_BALANCE_CONNECTION_GROUP.toString());
	};

	public void setLoadBalanceEnableJMX(String input) {
		setValue(Keys.LOAD_BALANCE_ENABLE_J_M_X.toString(), input);
	}

	public String getLoadBalanceEnableJMX() {
		return getValue(Keys.LOAD_BALANCE_ENABLE_J_M_X.toString());
	};

	public void setLoadBalanceExceptionChecker(String input) {
		setValue(Keys.LOAD_BALANCE_EXCEPTION_CHECKER.toString(), input);
	}

	public String getLoadBalanceExceptionChecker() {
		return getValue(Keys.LOAD_BALANCE_EXCEPTION_CHECKER.toString());
	};

	public void setLoadBalancePingTimeout(String input) {
		setValue(Keys.LOAD_BALANCE_PING_TIMEOUT.toString(), input);
	}

	public String getLoadBalancePingTimeout() {
		return getValue(Keys.LOAD_BALANCE_PING_TIMEOUT.toString());
	};

	public void setLoadBalanceSQLExceptionSubclassFailover(String input) {
		setValue(
				Keys.LOAD_BALANCE_S_Q_L_EXCEPTION_SUBCLASS_FAILOVER.toString(),
				input);
	}

	public String getLoadBalanceSQLExceptionSubclassFailover() {
		return getValue(Keys.LOAD_BALANCE_S_Q_L_EXCEPTION_SUBCLASS_FAILOVER
				.toString());
	};

	public void setLoadBalanceSQLStateFailover(String input) {
		setValue(Keys.LOAD_BALANCE_S_Q_L_STATE_FAILOVER.toString(), input);
	}

	public String getLoadBalanceSQLStateFailover() {
		return getValue(Keys.LOAD_BALANCE_S_Q_L_STATE_FAILOVER.toString());
	};

	public void setLoadBalanceStrategy(String input) {
		setValue(Keys.LOAD_BALANCE_STRATEGY.toString(), input);
	}

	public String getLoadBalanceStrategy() {
		return getValue(Keys.LOAD_BALANCE_STRATEGY.toString());
	};

	public void setLoadBalanceValidateConnectionOnSwapServer(String input) {
		setValue(
				Keys.LOAD_BALANCE_VALIDATE_CONNECTION_ON_SWAP_SERVER.toString(),
				input);
	}

	public String getLoadBalanceValidateConnectionOnSwapServer() {
		return getValue(Keys.LOAD_BALANCE_VALIDATE_CONNECTION_ON_SWAP_SERVER
				.toString());
	};

	public void setLocalSocketAddress(String input) {
		setValue(Keys.LOCAL_SOCKET_ADDRESS.toString(), input);
	}

	public String getLocalSocketAddress() {
		return getValue(Keys.LOCAL_SOCKET_ADDRESS.toString());
	};

	public void setLocatorFetchBufferSize(String input) {
		setValue(Keys.LOCATOR_FETCH_BUFFER_SIZE.toString(), input);
	}

	public String getLocatorFetchBufferSize() {
		return getValue(Keys.LOCATOR_FETCH_BUFFER_SIZE.toString());
	};

	public void setLogSlowQueries(String input) {
		setValue(Keys.LOG_SLOW_QUERIES.toString(), input);
	}

	public String getLogSlowQueries() {
		return getValue(Keys.LOG_SLOW_QUERIES.toString());
	};

	public void setLogXaCommands(String input) {
		setValue(Keys.LOG_XA_COMMANDS.toString(), input);
	}

	public String getLogXaCommands() {
		return getValue(Keys.LOG_XA_COMMANDS.toString());
	};

	public void setLogger(String input) {
		setValue(Keys.LOGGER.toString(), input);
	}

	public String getLogger() {
		return getValue(Keys.LOGGER.toString());
	};

	public void setMaintainTimeStats(String input) {
		setValue(Keys.MAINTAIN_TIME_STATS.toString(), input);
	}

	public String getMaintainTimeStats() {
		return getValue(Keys.MAINTAIN_TIME_STATS.toString());
	};

	public void setMaxAllowedPacket(String input) {
		setValue(Keys.MAX_ALLOWED_PACKET.toString(), input);
	}

	public String getMaxAllowedPacket() {
		return getValue(Keys.MAX_ALLOWED_PACKET.toString());
	};

	public void setMaxQuerySizeToLog(String input) {
		setValue(Keys.MAX_QUERY_SIZE_TO_LOG.toString(), input);
	}

	public String getMaxQuerySizeToLog() {
		return getValue(Keys.MAX_QUERY_SIZE_TO_LOG.toString());
	};

	public void setMaxReconnects(String input) {
		setValue(Keys.MAX_RECONNECTS.toString(), input);
	}

	public String getMaxReconnects() {
		return getValue(Keys.MAX_RECONNECTS.toString());
	};

	public void setMaxRows(String input) {
		setValue(Keys.MAX_ROWS.toString(), input);
	}

	public String getMaxRows() {
		return getValue(Keys.MAX_ROWS.toString());
	};

	public void setMetadataCacheSize(String input) {
		setValue(Keys.METADATA_CACHE_SIZE.toString(), input);
	}

	public String getMetadataCacheSize() {
		return getValue(Keys.METADATA_CACHE_SIZE.toString());
	};

	public void setNetTimeoutForStreamingResults(String input) {
		setValue(Keys.NET_TIMEOUT_FOR_STREAMING_RESULTS.toString(), input);
	}

	public String getNetTimeoutForStreamingResults() {
		return getValue(Keys.NET_TIMEOUT_FOR_STREAMING_RESULTS.toString());
	};

	public void setNoAccessToProcedureBodies(String input) {
		setValue(Keys.NO_ACCESS_TO_PROCEDURE_BODIES.toString(), input);
	}

	public String getNoAccessToProcedureBodies() {
		return getValue(Keys.NO_ACCESS_TO_PROCEDURE_BODIES.toString());
	};

	public void setNoDatetimeStringSync(String input) {
		setValue(Keys.NO_DATETIME_STRING_SYNC.toString(), input);
	}

	public String getNoDatetimeStringSync() {
		return getValue(Keys.NO_DATETIME_STRING_SYNC.toString());
	};

	public void setNoTimezoneConversionForDateType(String input) {
		setValue(Keys.NO_TIMEZONE_CONVERSION_FOR_DATE_TYPE.toString(), input);
	}

	public String getNoTimezoneConversionForDateType() {
		return getValue(Keys.NO_TIMEZONE_CONVERSION_FOR_DATE_TYPE.toString());
	};

	public void setNoTimezoneConversionForTimeType(String input) {
		setValue(Keys.NO_TIMEZONE_CONVERSION_FOR_TIME_TYPE.toString(), input);
	}

	public String getNoTimezoneConversionForTimeType() {
		return getValue(Keys.NO_TIMEZONE_CONVERSION_FOR_TIME_TYPE.toString());
	};

	public void setNullCatalogMeansCurrent(String input) {
		setValue(Keys.NULL_CATALOG_MEANS_CURRENT.toString(), input);
	}

	public String getNullCatalogMeansCurrent() {
		return getValue(Keys.NULL_CATALOG_MEANS_CURRENT.toString());
	};

	public void setNullNamePatternMatchesAll(String input) {
		setValue(Keys.NULL_NAME_PATTERN_MATCHES_ALL.toString(), input);
	}

	public String getNullNamePatternMatchesAll() {
		return getValue(Keys.NULL_NAME_PATTERN_MATCHES_ALL.toString());
	};

	public void setOverrideSupportsIntegrityEnhancementFacility(String input) {
		setValue(
				Keys.OVERRIDE_SUPPORTS_INTEGRITY_ENHANCEMENT_FACILITY
						.toString(),
				input);
	}

	public String getOverrideSupportsIntegrityEnhancementFacility() {
		return getValue(Keys.OVERRIDE_SUPPORTS_INTEGRITY_ENHANCEMENT_FACILITY
				.toString());
	};

	public void setPacketDebugBufferSize(String input) {
		setValue(Keys.PACKET_DEBUG_BUFFER_SIZE.toString(), input);
	}

	public String getPacketDebugBufferSize() {
		return getValue(Keys.PACKET_DEBUG_BUFFER_SIZE.toString());
	};

	public void setPadCharsWithSpace(String input) {
		setValue(Keys.PAD_CHARS_WITH_SPACE.toString(), input);
	}

	public String getPadCharsWithSpace() {
		return getValue(Keys.PAD_CHARS_WITH_SPACE.toString());
	};

	public void setParanoid(String input) {
		setValue(Keys.PARANOID.toString(), input);
	}

	public String getParanoid() {
		return getValue(Keys.PARANOID.toString());
	};

	public void setParseInfoCacheFactory(String input) {
		setValue(Keys.PARSE_INFO_CACHE_FACTORY.toString(), input);
	}

	public String getParseInfoCacheFactory() {
		return getValue(Keys.PARSE_INFO_CACHE_FACTORY.toString());
	};

	public void setPassword(String input) {
		setValue(Keys.PASSWORD.toString(), input);
	}

	public String getPassword() {
		return getValue(Keys.PASSWORD.toString());
	};

	public void setPasswordCharacterEncoding(String input) {
		setValue(Keys.PASSWORD_CHARACTER_ENCODING.toString(), input);
	}

	public String getPasswordCharacterEncoding() {
		return getValue(Keys.PASSWORD_CHARACTER_ENCODING.toString());
	};

	public void setPedantic(String input) {
		setValue(Keys.PEDANTIC.toString(), input);
	}

	public String getPedantic() {
		return getValue(Keys.PEDANTIC.toString());
	};

	public void setPinGlobalTxToPhysicalConnection(String input) {
		setValue(Keys.PIN_GLOBAL_TX_TO_PHYSICAL_CONNECTION.toString(), input);
	}

	public String getPinGlobalTxToPhysicalConnection() {
		return getValue(Keys.PIN_GLOBAL_TX_TO_PHYSICAL_CONNECTION.toString());
	};

	public void setPopulateInsertRowWithDefaultValues(String input) {
		setValue(Keys.POPULATE_INSERT_ROW_WITH_DEFAULT_VALUES.toString(), input);
	}

	public String getPopulateInsertRowWithDefaultValues() {
		return getValue(Keys.POPULATE_INSERT_ROW_WITH_DEFAULT_VALUES.toString());
	};

	public void setPrepStmtCacheSize(String input) {
		setValue(Keys.PREP_STMT_CACHE_SIZE.toString(), input);
	}

	public String getPrepStmtCacheSize() {
		return getValue(Keys.PREP_STMT_CACHE_SIZE.toString());
	};

	public void setPrepStmtCacheSqlLimit(String input) {
		setValue(Keys.PREP_STMT_CACHE_SQL_LIMIT.toString(), input);
	}

	public String getPrepStmtCacheSqlLimit() {
		return getValue(Keys.PREP_STMT_CACHE_SQL_LIMIT.toString());
	};

	public void setProcessEscapeCodesForPrepStmts(String input) {
		setValue(Keys.PROCESS_ESCAPE_CODES_FOR_PREP_STMTS.toString(), input);
	}

	public String getProcessEscapeCodesForPrepStmts() {
		return getValue(Keys.PROCESS_ESCAPE_CODES_FOR_PREP_STMTS.toString());
	};

	public void setProfileSQL(String input) {
		setValue(Keys.PROFILE_S_Q_L.toString(), input);
	}

	public String getProfileSQL() {
		return getValue(Keys.PROFILE_S_Q_L.toString());
	};

	public void setProfileSql(String input) {
		setValue(Keys.PROFILE_SQL.toString(), input);
	}

	public String getProfileSql() {
		return getValue(Keys.PROFILE_SQL.toString());
	};

	public void setProfilerEventHandler(String input) {
		setValue(Keys.PROFILER_EVENT_HANDLER.toString(), input);
	}

	public String getProfilerEventHandler() {
		return getValue(Keys.PROFILER_EVENT_HANDLER.toString());
	};

	public void setPropertiesTransform(String input) {
		setValue(Keys.PROPERTIES_TRANSFORM.toString(), input);
	}

	public String getPropertiesTransform() {
		return getValue(Keys.PROPERTIES_TRANSFORM.toString());
	};

	public void setQueriesBeforeRetryMaster(String input) {
		setValue(Keys.QUERIES_BEFORE_RETRY_MASTER.toString(), input);
	}

	public String getQueriesBeforeRetryMaster() {
		return getValue(Keys.QUERIES_BEFORE_RETRY_MASTER.toString());
	};

	public void setQueryTimeoutKillsConnection(String input) {
		setValue(Keys.QUERY_TIMEOUT_KILLS_CONNECTION.toString(), input);
	}

	public String getQueryTimeoutKillsConnection() {
		return getValue(Keys.QUERY_TIMEOUT_KILLS_CONNECTION.toString());
	};

	public void setReadOnlyPropagatesToServer(String input) {
		setValue(Keys.READ_ONLY_PROPAGATES_TO_SERVER.toString(), input);
	}

	public String getReadOnlyPropagatesToServer() {
		return getValue(Keys.READ_ONLY_PROPAGATES_TO_SERVER.toString());
	};

	public void setReconnectAtTxEnd(String input) {
		setValue(Keys.RECONNECT_AT_TX_END.toString(), input);
	}

	public String getReconnectAtTxEnd() {
		return getValue(Keys.RECONNECT_AT_TX_END.toString());
	};

	public void setRelaxAutoCommit(String input) {
		setValue(Keys.RELAX_AUTO_COMMIT.toString(), input);
	}

	public String getRelaxAutoCommit() {
		return getValue(Keys.RELAX_AUTO_COMMIT.toString());
	};

	public void setReplicationEnableJMX(String input) {
		setValue(Keys.REPLICATION_ENABLE_J_M_X.toString(), input);
	}

	public String getReplicationEnableJMX() {
		return getValue(Keys.REPLICATION_ENABLE_J_M_X.toString());
	};

	public void setReportMetricsIntervalMillis(String input) {
		setValue(Keys.REPORT_METRICS_INTERVAL_MILLIS.toString(), input);
	}

	public String getReportMetricsIntervalMillis() {
		return getValue(Keys.REPORT_METRICS_INTERVAL_MILLIS.toString());
	};

	public void setRequireSSL(String input) {
		setValue(Keys.REQUIRE_S_S_L.toString(), input);
	}

	public String getRequireSSL() {
		return getValue(Keys.REQUIRE_S_S_L.toString());
	};

	public void setResourceId(String input) {
		setValue(Keys.RESOURCE_ID.toString(), input);
	}

	public String getResourceId() {
		return getValue(Keys.RESOURCE_ID.toString());
	};

	public void setResultSetSizeThreshold(String input) {
		setValue(Keys.RESULT_SET_SIZE_THRESHOLD.toString(), input);
	}

	public String getResultSetSizeThreshold() {
		return getValue(Keys.RESULT_SET_SIZE_THRESHOLD.toString());
	};

	public void setRetainStatementAfterResultSetClose(String input) {
		setValue(Keys.RETAIN_STATEMENT_AFTER_RESULT_SET_CLOSE.toString(), input);
	}

	public String getRetainStatementAfterResultSetClose() {
		return getValue(Keys.RETAIN_STATEMENT_AFTER_RESULT_SET_CLOSE.toString());
	};

	public void setRetriesAllDown(String input) {
		setValue(Keys.RETRIES_ALL_DOWN.toString(), input);
	}

	public String getRetriesAllDown() {
		return getValue(Keys.RETRIES_ALL_DOWN.toString());
	};

	public void setRewriteBatchedStatements(String input) {
		setValue(Keys.REWRITE_BATCHED_STATEMENTS.toString(), input);
	}

	public String getRewriteBatchedStatements() {
		return getValue(Keys.REWRITE_BATCHED_STATEMENTS.toString());
	};

	public void setRollbackOnPooledClose(String input) {
		setValue(Keys.ROLLBACK_ON_POOLED_CLOSE.toString(), input);
	}

	public String getRollbackOnPooledClose() {
		return getValue(Keys.ROLLBACK_ON_POOLED_CLOSE.toString());
	};

	public void setRoundRobinLoadBalance(String input) {
		setValue(Keys.ROUND_ROBIN_LOAD_BALANCE.toString(), input);
	}

	public String getRoundRobinLoadBalance() {
		return getValue(Keys.ROUND_ROBIN_LOAD_BALANCE.toString());
	};

	public void setRunningCTS13(String input) {
		setValue(Keys.RUNNING_C_T_S13.toString(), input);
	}

	public String getRunningCTS13() {
		return getValue(Keys.RUNNING_C_T_S13.toString());
	};

	public void setSecondsBeforeRetryMaster(String input) {
		setValue(Keys.SECONDS_BEFORE_RETRY_MASTER.toString(), input);
	}

	public String getSecondsBeforeRetryMaster() {
		return getValue(Keys.SECONDS_BEFORE_RETRY_MASTER.toString());
	};

	public void setSelfDestructOnPingMaxOperations(String input) {
		setValue(Keys.SELF_DESTRUCT_ON_PING_MAX_OPERATIONS.toString(), input);
	}

	public String getSelfDestructOnPingMaxOperations() {
		return getValue(Keys.SELF_DESTRUCT_ON_PING_MAX_OPERATIONS.toString());
	};

	public void setSelfDestructOnPingSecondsLifetime(String input) {
		setValue(Keys.SELF_DESTRUCT_ON_PING_SECONDS_LIFETIME.toString(), input);
	}

	public String getSelfDestructOnPingSecondsLifetime() {
		return getValue(Keys.SELF_DESTRUCT_ON_PING_SECONDS_LIFETIME.toString());
	};

	public void setServerConfigCacheFactory(String input) {
		setValue(Keys.SERVER_CONFIG_CACHE_FACTORY.toString(), input);
	}

	public String getServerConfigCacheFactory() {
		return getValue(Keys.SERVER_CONFIG_CACHE_FACTORY.toString());
	};

	public void setServerRSAPublicKeyFile(String input) {
		setValue(Keys.SERVER_R_S_A_PUBLIC_KEY_FILE.toString(), input);
	}

	public String getServerRSAPublicKeyFile() {
		return getValue(Keys.SERVER_R_S_A_PUBLIC_KEY_FILE.toString());
	};

	public void setServerTimezone(String input) {
		setValue(Keys.SERVER_TIMEZONE.toString(), input);
	}

	public String getServerTimezone() {
		return getValue(Keys.SERVER_TIMEZONE.toString());
	};

	public void setSessionVariables(String input) {
		setValue(Keys.SESSION_VARIABLES.toString(), input);
	}

	public String getSessionVariables() {
		return getValue(Keys.SESSION_VARIABLES.toString());
	};

	public void setSlowQueryThresholdMillis(String input) {
		setValue(Keys.SLOW_QUERY_THRESHOLD_MILLIS.toString(), input);
	}

	public String getSlowQueryThresholdMillis() {
		return getValue(Keys.SLOW_QUERY_THRESHOLD_MILLIS.toString());
	};

	public void setSlowQueryThresholdNanos(String input) {
		setValue(Keys.SLOW_QUERY_THRESHOLD_NANOS.toString(), input);
	}

	public String getSlowQueryThresholdNanos() {
		return getValue(Keys.SLOW_QUERY_THRESHOLD_NANOS.toString());
	};

	public void setSocketFactory(String input) {
		setValue(Keys.SOCKET_FACTORY.toString(), input);
	}

	public String getSocketFactory() {
		return getValue(Keys.SOCKET_FACTORY.toString());
	};

	public void setSocketTimeout(String input) {
		setValue(Keys.SOCKET_TIMEOUT.toString(), input);
	}

	public String getSocketTimeout() {
		return getValue(Keys.SOCKET_TIMEOUT.toString());
	};

	public void setSocksProxyHost(String input) {
		setValue(Keys.SOCKS_PROXY_HOST.toString(), input);
	}

	public String getSocksProxyHost() {
		return getValue(Keys.SOCKS_PROXY_HOST.toString());
	};

	public void setSocksProxyPort(String input) {
		setValue(Keys.SOCKS_PROXY_PORT.toString(), input);
	}

	public String getSocksProxyPort() {
		return getValue(Keys.SOCKS_PROXY_PORT.toString());
	};

	public void setStatementInterceptors(String input) {
		setValue(Keys.STATEMENT_INTERCEPTORS.toString(), input);
	}

	public String getStatementInterceptors() {
		return getValue(Keys.STATEMENT_INTERCEPTORS.toString());
	};

	public void setStrictFloatingPoint(String input) {
		setValue(Keys.STRICT_FLOATING_POINT.toString(), input);
	}

	public String getStrictFloatingPoint() {
		return getValue(Keys.STRICT_FLOATING_POINT.toString());
	};

	public void setStrictUpdates(String input) {
		setValue(Keys.STRICT_UPDATES.toString(), input);
	}

	public String getStrictUpdates() {
		return getValue(Keys.STRICT_UPDATES.toString());
	};

	public void setTcpKeepAlive(String input) {
		setValue(Keys.TCP_KEEP_ALIVE.toString(), input);
	}

	public String getTcpKeepAlive() {
		return getValue(Keys.TCP_KEEP_ALIVE.toString());
	};

	public void setTcpNoDelay(String input) {
		setValue(Keys.TCP_NO_DELAY.toString(), input);
	}

	public String getTcpNoDelay() {
		return getValue(Keys.TCP_NO_DELAY.toString());
	};

	public void setTcpRcvBuf(String input) {
		setValue(Keys.TCP_RCV_BUF.toString(), input);
	}

	public String getTcpRcvBuf() {
		return getValue(Keys.TCP_RCV_BUF.toString());
	};

	public void setTcpSndBuf(String input) {
		setValue(Keys.TCP_SND_BUF.toString(), input);
	}

	public String getTcpSndBuf() {
		return getValue(Keys.TCP_SND_BUF.toString());
	};

	public void setTcpTrafficClass(String input) {
		setValue(Keys.TCP_TRAFFIC_CLASS.toString(), input);
	}

	public String getTcpTrafficClass() {
		return getValue(Keys.TCP_TRAFFIC_CLASS.toString());
	};

	public void setTinyInt1isBit(String input) {
		setValue(Keys.TINY_INT1IS_BIT.toString(), input);
	}

	public String getTinyInt1isBit() {
		return getValue(Keys.TINY_INT1IS_BIT.toString());
	};

	public void setTraceProtocol(String input) {
		setValue(Keys.TRACE_PROTOCOL.toString(), input);
	}

	public String getTraceProtocol() {
		return getValue(Keys.TRACE_PROTOCOL.toString());
	};

	public void setTransformedBitIsBoolean(String input) {
		setValue(Keys.TRANSFORMED_BIT_IS_BOOLEAN.toString(), input);
	}

	public String getTransformedBitIsBoolean() {
		return getValue(Keys.TRANSFORMED_BIT_IS_BOOLEAN.toString());
	};

	public void setTreatUtilDateAsTimestamp(String input) {
		setValue(Keys.TREAT_UTIL_DATE_AS_TIMESTAMP.toString(), input);
	}

	public String getTreatUtilDateAsTimestamp() {
		return getValue(Keys.TREAT_UTIL_DATE_AS_TIMESTAMP.toString());
	};

	public void setTrustCertificateKeyStorePassword(String input) {
		setValue(Keys.TRUST_CERTIFICATE_KEY_STORE_PASSWORD.toString(), input);
	}

	public String getTrustCertificateKeyStorePassword() {
		return getValue(Keys.TRUST_CERTIFICATE_KEY_STORE_PASSWORD.toString());
	};

	public void setTrustCertificateKeyStoreType(String input) {
		setValue(Keys.TRUST_CERTIFICATE_KEY_STORE_TYPE.toString(), input);
	}

	public String getTrustCertificateKeyStoreType() {
		return getValue(Keys.TRUST_CERTIFICATE_KEY_STORE_TYPE.toString());
	};

	public void setTrustCertificateKeyStoreUrl(String input) {
		setValue(Keys.TRUST_CERTIFICATE_KEY_STORE_URL.toString(), input);
	}

	public String getTrustCertificateKeyStoreUrl() {
		return getValue(Keys.TRUST_CERTIFICATE_KEY_STORE_URL.toString());
	};

	public void setUltraDevHack(String input) {
		setValue(Keys.ULTRA_DEV_HACK.toString(), input);
	}

	public String getUltraDevHack() {
		return getValue(Keys.ULTRA_DEV_HACK.toString());
	};

	public void setUseAffectedRows(String input) {
		setValue(Keys.USE_AFFECTED_ROWS.toString(), input);
	}

	public String getUseAffectedRows() {
		return getValue(Keys.USE_AFFECTED_ROWS.toString());
	};

	public void setUseBlobToStoreUTF8OutsideBMP(String input) {
		setValue(Keys.USE_BLOB_TO_STORE_U_T_F8_OUTSIDE_B_M_P.toString(), input);
	}

	public String getUseBlobToStoreUTF8OutsideBMP() {
		return getValue(Keys.USE_BLOB_TO_STORE_U_T_F8_OUTSIDE_B_M_P.toString());
	};

	public void setUseColumnNamesInFindColumn(String input) {
		setValue(Keys.USE_COLUMN_NAMES_IN_FIND_COLUMN.toString(), input);
	}

	public String getUseColumnNamesInFindColumn() {
		return getValue(Keys.USE_COLUMN_NAMES_IN_FIND_COLUMN.toString());
	};

	public void setUseCompression(String input) {
		setValue(Keys.USE_COMPRESSION.toString(), input);
	}

	public String getUseCompression() {
		return getValue(Keys.USE_COMPRESSION.toString());
	};

	public void setUseConfigs(String input) {
		setValue(Keys.USE_CONFIGS.toString(), input);
	}

	public String getUseConfigs() {
		return getValue(Keys.USE_CONFIGS.toString());
	};

	public void setUseCursorFetch(String input) {
		setValue(Keys.USE_CURSOR_FETCH.toString(), input);
	}

	public String getUseCursorFetch() {
		return getValue(Keys.USE_CURSOR_FETCH.toString());
	};

	public void setUseDirectRowUnpack(String input) {
		setValue(Keys.USE_DIRECT_ROW_UNPACK.toString(), input);
	}

	public String getUseDirectRowUnpack() {
		return getValue(Keys.USE_DIRECT_ROW_UNPACK.toString());
	};

	public void setUseDynamicCharsetInfo(String input) {
		setValue(Keys.USE_DYNAMIC_CHARSET_INFO.toString(), input);
	}

	public String getUseDynamicCharsetInfo() {
		return getValue(Keys.USE_DYNAMIC_CHARSET_INFO.toString());
	};

	public void setUseFastDateParsing(String input) {
		setValue(Keys.USE_FAST_DATE_PARSING.toString(), input);
	}

	public String getUseFastDateParsing() {
		return getValue(Keys.USE_FAST_DATE_PARSING.toString());
	};

	public void setUseFastIntParsing(String input) {
		setValue(Keys.USE_FAST_INT_PARSING.toString(), input);
	}

	public String getUseFastIntParsing() {
		return getValue(Keys.USE_FAST_INT_PARSING.toString());
	};

	public void setUseGmtMillisForDatetimes(String input) {
		setValue(Keys.USE_GMT_MILLIS_FOR_DATETIMES.toString(), input);
	}

	public String getUseGmtMillisForDatetimes() {
		return getValue(Keys.USE_GMT_MILLIS_FOR_DATETIMES.toString());
	};

	public void setUseHostsInPrivileges(String input) {
		setValue(Keys.USE_HOSTS_IN_PRIVILEGES.toString(), input);
	}

	public String getUseHostsInPrivileges() {
		return getValue(Keys.USE_HOSTS_IN_PRIVILEGES.toString());
	};

	public void setUseInformationSchema(String input) {
		setValue(Keys.USE_INFORMATION_SCHEMA.toString(), input);
	}

	public String getUseInformationSchema() {
		return getValue(Keys.USE_INFORMATION_SCHEMA.toString());
	};

	public void setUseJDBCCompliantTimezoneShift(String input) {
		setValue(Keys.USE_J_D_B_C_COMPLIANT_TIMEZONE_SHIFT.toString(), input);
	}

	public String getUseJDBCCompliantTimezoneShift() {
		return getValue(Keys.USE_J_D_B_C_COMPLIANT_TIMEZONE_SHIFT.toString());
	};

	public void setUseJvmCharsetConverters(String input) {
		setValue(Keys.USE_JVM_CHARSET_CONVERTERS.toString(), input);
	}

	public String getUseJvmCharsetConverters() {
		return getValue(Keys.USE_JVM_CHARSET_CONVERTERS.toString());
	};

	public void setUseLegacyDatetimeCode(String input) {
		setValue(Keys.USE_LEGACY_DATETIME_CODE.toString(), input);
	}

	public String getUseLegacyDatetimeCode() {
		return getValue(Keys.USE_LEGACY_DATETIME_CODE.toString());
	};

	public void setUseLocalSessionState(String input) {
		setValue(Keys.USE_LOCAL_SESSION_STATE.toString(), input);
	}

	public String getUseLocalSessionState() {
		return getValue(Keys.USE_LOCAL_SESSION_STATE.toString());
	};

	public void setUseLocalTransactionState(String input) {
		setValue(Keys.USE_LOCAL_TRANSACTION_STATE.toString(), input);
	}

	public String getUseLocalTransactionState() {
		return getValue(Keys.USE_LOCAL_TRANSACTION_STATE.toString());
	};

	public void setUseNanosForElapsedTime(String input) {
		setValue(Keys.USE_NANOS_FOR_ELAPSED_TIME.toString(), input);
	}

	public String getUseNanosForElapsedTime() {
		return getValue(Keys.USE_NANOS_FOR_ELAPSED_TIME.toString());
	};

	public void setUseOldAliasMetadataBehavior(String input) {
		setValue(Keys.USE_OLD_ALIAS_METADATA_BEHAVIOR.toString(), input);
	}

	public String getUseOldAliasMetadataBehavior() {
		return getValue(Keys.USE_OLD_ALIAS_METADATA_BEHAVIOR.toString());
	};

	public void setUseOldUTF8Behavior(String input) {
		setValue(Keys.USE_OLD_U_T_F8_BEHAVIOR.toString(), input);
	}

	public String getUseOldUTF8Behavior() {
		return getValue(Keys.USE_OLD_U_T_F8_BEHAVIOR.toString());
	};

	public void setUseOnlyServerErrorMessages(String input) {
		setValue(Keys.USE_ONLY_SERVER_ERROR_MESSAGES.toString(), input);
	}

	public String getUseOnlyServerErrorMessages() {
		return getValue(Keys.USE_ONLY_SERVER_ERROR_MESSAGES.toString());
	};

	public void setUseReadAheadInput(String input) {
		setValue(Keys.USE_READ_AHEAD_INPUT.toString(), input);
	}

	public String getUseReadAheadInput() {
		return getValue(Keys.USE_READ_AHEAD_INPUT.toString());
	};

	public void setUseSSL(String input) {
		setValue(Keys.USE_S_S_L.toString(), input);
	}

	public String getUseSSL() {
		return getValue(Keys.USE_S_S_L.toString());
	};

	public void setUseSSPSCompatibleTimezoneShift(String input) {
		setValue(Keys.USE_S_S_P_S_COMPATIBLE_TIMEZONE_SHIFT.toString(), input);
	}

	public String getUseSSPSCompatibleTimezoneShift() {
		return getValue(Keys.USE_S_S_P_S_COMPATIBLE_TIMEZONE_SHIFT.toString());
	};

	public void setUseServerPrepStmts(String input) {
		setValue(Keys.USE_SERVER_PREP_STMTS.toString(), input);
	}

	public String getUseServerPrepStmts() {
		return getValue(Keys.USE_SERVER_PREP_STMTS.toString());
	};

	public void setUseSqlStateCodes(String input) {
		setValue(Keys.USE_SQL_STATE_CODES.toString(), input);
	}

	public String getUseSqlStateCodes() {
		return getValue(Keys.USE_SQL_STATE_CODES.toString());
	};

	public void setUseStreamLengthsInPrepStmts(String input) {
		setValue(Keys.USE_STREAM_LENGTHS_IN_PREP_STMTS.toString(), input);
	}

	public String getUseStreamLengthsInPrepStmts() {
		return getValue(Keys.USE_STREAM_LENGTHS_IN_PREP_STMTS.toString());
	};

	public void setUseTimezone(String input) {
		setValue(Keys.USE_TIMEZONE.toString(), input);
	}

	public String getUseTimezone() {
		return getValue(Keys.USE_TIMEZONE.toString());
	};

	public void setUseUnbufferedInput(String input) {
		setValue(Keys.USE_UNBUFFERED_INPUT.toString(), input);
	}

	public String getUseUnbufferedInput() {
		return getValue(Keys.USE_UNBUFFERED_INPUT.toString());
	};

	public void setUseUnicode(String input) {
		setValue(Keys.USE_UNICODE.toString(), input);
	}

	public String getUseUnicode() {
		return getValue(Keys.USE_UNICODE.toString());
	};

	public void setUseUsageAdvisor(String input) {
		setValue(Keys.USE_USAGE_ADVISOR.toString(), input);
	}

	public String getUseUsageAdvisor() {
		return getValue(Keys.USE_USAGE_ADVISOR.toString());
	};

	public void setUser(String input) {
		setValue(Keys.USER.toString(), input);
	}

	public String getUser() {
		return getValue(Keys.USER.toString());
	};

	public void setUtf8OutsideBmpExcludedColumnNamePattern(String input) {
		setValue(Keys.UTF8_OUTSIDE_BMP_EXCLUDED_COLUMN_NAME_PATTERN.toString(),
				input);
	}

	public String getUtf8OutsideBmpExcludedColumnNamePattern() {
		return getValue(Keys.UTF8_OUTSIDE_BMP_EXCLUDED_COLUMN_NAME_PATTERN
				.toString());
	};

	public void setUtf8OutsideBmpIncludedColumnNamePattern(String input) {
		setValue(Keys.UTF8_OUTSIDE_BMP_INCLUDED_COLUMN_NAME_PATTERN.toString(),
				input);
	}

	public String getUtf8OutsideBmpIncludedColumnNamePattern() {
		return getValue(Keys.UTF8_OUTSIDE_BMP_INCLUDED_COLUMN_NAME_PATTERN
				.toString());
	};

	public void setVerifyServerCertificate(String input) {
		setValue(Keys.VERIFY_SERVER_CERTIFICATE.toString(), input);
	}

	public String getVerifyServerCertificate() {
		return getValue(Keys.VERIFY_SERVER_CERTIFICATE.toString());
	};

	public void setZeroDateTimeBehavior(String input) {
		setValue(Keys.ZERO_DATE_TIME_BEHAVIOR.toString(), input);
	}

	public String getZeroDateTimeBehavior() {
		return getValue(Keys.ZERO_DATE_TIME_BEHAVIOR.toString());
	};

	public HashMap<String, String> filterProperties() {
		return filterProperties(OSGiUtil.getEnumValues(Keys.class));
	}
}
