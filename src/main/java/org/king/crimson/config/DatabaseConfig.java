package org.king.crimson.config;

import com.zaxxer.hikari.HikariDataSource;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

    @Autowired
    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean factoryBean(HikariDataSource ds) {

        LocalSessionFactoryBean fb = new LocalSessionFactoryBean();
        fb.setDataSource(ds);
        fb.setPackagesToScan("org.king.crimson.model");

        Properties prop = new Properties();
        prop.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        prop.setProperty("hibernate.show_sql", "true");
        prop.setProperty("hibernate.connection.release_mode", "after_transaction");
        prop.setProperty("hibernate.connection.useUnicode", "true");
        prop.setProperty("hibernate.connection.charSet", "UTF8");

        fb.setHibernateProperties(prop);

        return fb;
    }

}
