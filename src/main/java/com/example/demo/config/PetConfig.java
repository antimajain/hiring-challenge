package com.example.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.demo.model.pet.Pet;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "petEntityManagerFactory", transactionManagerRef = "petTransactionManager", basePackages = {
		"com.example.demo.repository.pet" })
@Primary
public class PetConfig {

	@Bean
	@Primary
	@ConfigurationProperties("spring.pet")
	public DataSourceProperties petDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	@Primary
	@ConfigurationProperties("spring.pet.configuration")
	public DataSource petDataSource() {
		return petDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}

	@Primary
	@Bean(name = "petEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean petEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(petDataSource()).packages(Pet.class).build();
	}

	@Primary
	@Bean
	public PlatformTransactionManager petTransactionManager(
			final @Qualifier("petEntityManagerFactory") LocalContainerEntityManagerFactoryBean petEntityManagerFactory) {
		return new JpaTransactionManager(petEntityManagerFactory.getObject());
	}

}
