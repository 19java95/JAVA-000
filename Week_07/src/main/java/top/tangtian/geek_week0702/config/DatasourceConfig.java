package top.tangtian.geek_week0702.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * @author tangtian
 * @version 1.0
 * @className DatasourceConfig
 * @description
 * @date 2020/11/30 8:15 AM
 **/
@Configuration
@PropertySource("classpath:jdbc.properties")
public class DatasourceConfig {
    private static final Logger LOG = LoggerFactory.getLogger(DatasourceConfig.class);
    @Bean("master")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean("slave")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDataSource(){
        return DataSourceBuilder.create().build();
    }
}
