package com.example.register.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories (
        basePackages ={"com.example.register.repositories"}, transactionManagerRef = "transactionManager",
entityManagerFactoryRef = "managerFactory")
public class PrimaryDataSource {
    @Value("${app.db.url}")
    private String databaseUrl;

    @Value("${app.db.username}")
    private String databaseUsername;

    @Value("${app.db.password}")
    private String databasePassword;

    @Value("${app.db.driver}")
    private String databaseDriver;

    @Value("${app.db.change-type:none}")
    private String changeType;

    @Bean
    public DataSource dataSource(){
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(databaseUrl);
        hikariConfig.setUsername(databaseUsername);
        hikariConfig.setPassword(databasePassword);
        hikariConfig.setDriverClassName(databaseDriver);
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean managerFactory(){
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(dataSource());
        bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        bean.setPackagesToScan("com.example.register.model");
        Map<String,String> propertiesMap = new HashMap<>();
        propertiesMap.put("hibernate.hbm2ddl.auto",changeType);
        bean.setJpaPropertyMap(propertiesMap);
        return bean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        return new JpaTransactionManager(managerFactory().getObject());
    }


}
