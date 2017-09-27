package com.siran;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by guangrongyang on 17/4/6.
 */

@Configuration

@EnableTransactionManagement

// Load to Environment.
//@PropertySources({@PropertySource("classpath:datasource-mysql.properties")})
@PropertySource("classpath:datasource-mysql.properties")
public class ApplicationContextConfig {
    // The Environment class serves as the property holder
    // and stores all the properties loaded by the @PropertySource
    @Autowired
    private Environment env;

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        // 使用 hikariCP
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(env.getProperty("ds.database-driver"));
        config.setJdbcUrl(env.getProperty("ds.url"));
        config.setUsername(env.getProperty("ds.username"));
        config.setPassword(env.getProperty("ds.password"));
//        config.addDataSourceProperty("cachePrepStmts", true);
//        config.addDataSourceProperty("prepStmtCacheSize", 500);
//        config.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
        //池中最小空闲链接数量 config.setMinimumIdle(minimum);
        //池中最大链接数量 config.setMaximumPoolSize(Maximum);
        HikariDataSource dataSource = new HikariDataSource(config);
        // See: datasouce-cfg.properties

        System.out.println("## getDataSource: " + dataSource);

        return dataSource;
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager getTransactionManager() {
        DataSourceTransactionManager txManager = new DataSourceTransactionManager();

        DataSource dataSource = this.getDataSource();
        txManager.setDataSource(dataSource);

        return txManager;
    }

    @Bean(name = "jdbcOperations")
    @Deprecated
    public JdbcOperations getJdbcOperations(){

        JdbcOperations jdbcOperations = new JdbcTemplate(this.getDataSource(),true);
        System.out.println("## jdbcOperations: " + jdbcOperations);

        return jdbcOperations;
    }

}
