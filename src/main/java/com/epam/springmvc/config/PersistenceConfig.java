package com.epam.springmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.epam.springmvc.dao")
public class PersistenceConfig {

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDataSourceMysql());
    }

    /**
     * For H2 Database
     */
    @Bean
    public DataSource getDataSourceH2() {
        return new EmbeddedDatabaseBuilder()
                .generateUniqueName(false)
                .setName("phones")
                .setType(EmbeddedDatabaseType.H2)
                .addScript("db/sql/create-db.sql")
                .addScript("db/sql/insert-data.sql")
                .setScriptEncoding("UTF-8")
                .ignoreFailedDrops(true)
                .build();
    }

    /**
     * For Msql Database
     */
    @Bean
    public DataSource getDataSourceMysql() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/phones?userSll=false?createDatabaseIfNotExist=true");
        dataSource.setUsername("root");
        dataSource.setPassword("nettuno27");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        return dataSource;
    }
}
