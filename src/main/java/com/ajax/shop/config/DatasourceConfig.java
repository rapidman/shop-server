package com.ajax.shop.config;

import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
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
@EnableJpaAuditing
public class DatasourceConfig {
    private static final Logger log = LoggerFactory.getLogger(DatasourceConfig.class);
    @Resource
    private ConfigurableEnvironment environment;
    @Value("${postgresql.username}")
    private String dbUserName;
    @Value("${postgresql.password}")
    private String dbUserPass;

    @Bean
    @ConfigurationProperties(prefix = "postgresql.hikari")
    public DataSource dataSource() {
        log.info("url:{},dbUserName:{},dbUserPass:{}", environment.getProperty("postgresql.url"), dbUserName, dbUserPass);
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(environment.getProperty("postgresql.url"));
        dataSource.setUsername(dbUserName);
        dataSource.setPassword(dbUserPass);

        return dataSource;
    }

}
