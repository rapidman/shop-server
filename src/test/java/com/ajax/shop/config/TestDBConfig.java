package com.ajax.shop.config;

import de.flapdoodle.embed.process.runtime.Network;
import org.postgresql.ds.PGPoolingDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import ru.yandex.qatools.embed.postgresql.PostgresExecutable;
import ru.yandex.qatools.embed.postgresql.PostgresProcess;
import ru.yandex.qatools.embed.postgresql.PostgresStarter;
import ru.yandex.qatools.embed.postgresql.config.AbstractPostgresConfig;
import ru.yandex.qatools.embed.postgresql.config.PostgresConfig;
import ru.yandex.qatools.embed.postgresql.distribution.Version;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created by timur on 07.12.17.
 */
@Configuration
public class TestDBConfig {

    final PostgresConfig config = getPostgresConfig();

    private PostgresConfig getPostgresConfig() {
        try {
            return new PostgresConfig(
                    Version.V9_6_6,
                    new AbstractPostgresConfig.Net("localhost", Network.getFreeServerPort()),
                    new AbstractPostgresConfig.Storage("test"),
                    new AbstractPostgresConfig.Timeout(),
                    new AbstractPostgresConfig.Credentials("user", "pass")
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean(destroyMethod = "stop", name = "postgresProcess")
    public PostgresProcess postgresProcess() throws IOException {
        PostgresStarter<PostgresExecutable, PostgresProcess> runtime = PostgresStarter.getDefaultInstance();
        PostgresExecutable exec = runtime.prepare(config);
        PostgresProcess process = exec.start();
        return process;
    }

    @Bean(destroyMethod = "close")
    @DependsOn("postgresProcess")
    public DataSource dataSource() {
        PGPoolingDataSource ds = new PGPoolingDataSource();
        ds.setUser(config.credentials().username());
        ds.setPassword(config.credentials().password());
        ds.setPortNumber(config.net().port());
        ds.setServerName(config.net().host());
        ds.setDatabaseName(config.storage().dbName());
        return ds;
    }
}
