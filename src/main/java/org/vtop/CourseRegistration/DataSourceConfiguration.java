package org.vtop.CourseRegistration;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfiguration {

	@Bean
	public HikariDataSource masterDataSource(DataSourceProperties masterDataSourceProperties) throws SQLException {

		HikariConfig dataSrcConfig = new HikariConfig();

		dataSrcConfig.setUsername("reguser");
		dataSrcConfig.setPassword("H!ghC0nf!d9nc9");
		dataSrcConfig.setJdbcUrl("jdbc:postgresql://10.10.3.236:5432/vtop");
		dataSrcConfig.setMinimumIdle(1);
		dataSrcConfig.setMaximumPoolSize(5);
		dataSrcConfig.setConnectionTimeout(30000);
		dataSrcConfig.setIdleTimeout(300000);
		dataSrcConfig.setDriverClassName("org.postgresql.Driver");
		dataSrcConfig.setConnectionTestQuery("SELECT 1");

		HikariDataSource dataSource=new HikariDataSource(dataSrcConfig);

		return dataSource;
	}

	@Bean
	public HikariDataSource slaveDataSource(DataSourceProperties slaveDataSourceProperties) throws SQLException {
		
		HikariConfig dataSrcConfig = new HikariConfig();

		dataSrcConfig.setUsername("reguser");
		dataSrcConfig.setPassword("H!ghC0nf!d9nc9");
		dataSrcConfig.setJdbcUrl("jdbc:postgresql://10.10.3.236:5432/vtop");
		dataSrcConfig.setMinimumIdle(1);
		dataSrcConfig.setMaximumPoolSize(5);
		dataSrcConfig.setConnectionTimeout(30000);
		dataSrcConfig.setIdleTimeout(300000);
		dataSrcConfig.setDriverClassName("org.postgresql.Driver");
		dataSrcConfig.setConnectionTestQuery("SELECT 1");

		HikariDataSource dataSource=new HikariDataSource(dataSrcConfig);

		return dataSource;
	}

	@Bean
	@Primary
	public TransactionRoutingDataSource routingDataSource(DataSource masterDataSource,  DataSource slaveDataSource) throws SQLException {
		
		TransactionRoutingDataSource routingDataSource = new TransactionRoutingDataSource();

		Map<Object, Object> dataSourceMap = new HashMap<>();
		dataSourceMap.put(DataSourceType.READ_WRITE, masterDataSource);
		dataSourceMap.put(DataSourceType.READ_ONLY, slaveDataSource);

		routingDataSource.setTargetDataSources(dataSourceMap);
		routingDataSource.setDefaultTargetDataSource(masterDataSource);

		return routingDataSource;
	}

	@Bean
	public BeanPostProcessor dialectProcessor() {

		return new BeanPostProcessor() {
			@Override
			public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
				if (bean instanceof HibernateJpaVendorAdapter) {
					((HibernateJpaVendorAdapter) bean).getJpaDialect().setPrepareConnection(false);
				}
				return bean;
			}
		};
	}
}
