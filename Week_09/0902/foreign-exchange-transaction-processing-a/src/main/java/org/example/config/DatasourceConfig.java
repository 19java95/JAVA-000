package org.example.config;

import com.zaxxer.hikari.HikariDataSource;
import org.dromara.hmily.tac.p6spy.HmilyP6Datasource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author tangtian
 * @version 1.0
 * @className config
 * @description
 * @date 2020/12/18 8:04 AM
 **/
@Configuration
public class DatasourceConfig {
    private static final Logger LOG = LoggerFactory.getLogger(DatasourceConfig.class);

    private final DataSourceProperties dataSourceProperties;

    /**
     * Instantiates a new Hmily tac datasource config.
     *
     * @param dataSourceProperties the data source properties
     */
    public DatasourceConfig(DataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    /**
     * Data source data source.
     *
     * @return the data source
     */
    @Bean
    @Primary
    public DataSource dataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(dataSourceProperties.getUrl());
        hikariDataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        hikariDataSource.setUsername(dataSourceProperties.getUsername());
        hikariDataSource.setPassword(dataSourceProperties.getPassword());
        hikariDataSource.setMaximumPoolSize(20);
        hikariDataSource.setMinimumIdle(10);
        hikariDataSource.setConnectionTimeout(30000);
        hikariDataSource.setIdleTimeout(600000);
        hikariDataSource.setMaxLifetime(1800000);
        return new HmilyP6Datasource(hikariDataSource);
    }
}
