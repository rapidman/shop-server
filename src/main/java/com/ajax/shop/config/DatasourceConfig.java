package com.ajax.shop.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>
 */
@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = {"com.ajax.shop.entity"})
@EnableJpaRepositories(basePackages = {"com.ajax.shop.repository"})
public class DatasourceConfig {
    @Resource
    private ConfigurableEnvironment environment;
    @Value("${postgresql.username}")
    private String dbUserName;
    @Value("${postgresql.password}")
    private String dbUserPass;

    @Bean
    @ConfigurationProperties(prefix = "postgresql.hikari")
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(environment.getProperty("postgresql.url"));
        dataSource.setUsername(dbUserName);
        dataSource.setPassword(dbUserPass);

        return dataSource;
    }

}
