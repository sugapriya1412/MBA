package org.vtop.CourseRegistration;

import java.util.Properties;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement
@EnableAspectJAutoProxy
public abstract class AbstractJPAConfiguration {
	
    protected Properties additionalProperties() {
    	
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialectt");
        return properties;
    }
}
