package com.applications.Multi_Tenant.tenantmangement.config.database;

import com.applications.Multi_Tenant.tenantmangement.TenantContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.cfg.MultiTenancySettings;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class MultiTenantConnectionProviderImpl
		implements MultiTenantConnectionProvider<String>, HibernatePropertiesCustomizer {
  	private final transient DataSource dataSource;

	@Override
	public Connection getAnyConnection() throws SQLException {
		return getConnection(TenantContext.getTenantId());
	}

	@Override
	public void releaseAnyConnection(Connection connection) throws SQLException {
		connection.close();
	}

	@Override
	public Connection getConnection(String tenantIdentifier) throws SQLException {
		log.info("Getting Connection With Tenant:: {}", tenantIdentifier);
		Connection connection = dataSource.getConnection();
		connection.setSchema(tenantIdentifier);
		return connection;
	}

	@Override
	public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
		log.info("Release Connection With Tenant:: {}", tenantIdentifier);
    	connection.setSchema("public");
		connection.close();
  	}

	@Override
	public boolean supportsAggressiveRelease() {
		return true;
	}

	@Override
	public boolean isUnwrappableAs(Class<?> unwrapType) {
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> unwrapType) {
		throw new UnsupportedOperationException("Unimplemented method 'unwrap'.");
	}

	@Override
	public void customize(Map<String, Object> hibernateProperties) {
		hibernateProperties.put(MultiTenancySettings.MULTI_TENANT_CONNECTION_PROVIDER, this);
	}

}