package com.example.demo.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.demo.model.user.User;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "userEntityManagerFactory", transactionManagerRef = "userTransactionManager", basePackages = {
		"com.example.demo.repository.user" })
public class UserConfig {

	@Bean
	@ConfigurationProperties(prefix = "spring.users")
	public DataSourceProperties userDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	@ConfigurationProperties("spring.users.configuration")
	public DataSource userDataSource() {
		return userDataSourceProperties().initializeDataSourceBuilder().type(BasicDataSource.class).build();
	}

	@Bean(name = "userEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean userEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(userDataSource()).packages(User.class).build();
	}

	@Bean
	public PlatformTransactionManager userTransactionManager(
			final @Qualifier("userEntityManagerFactory") LocalContainerEntityManagerFactoryBean userEntityManagerFactory) {
		return new JpaTransactionManager(userEntityManagerFactory.getObject());
	}

}
