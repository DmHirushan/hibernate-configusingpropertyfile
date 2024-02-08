package config;

import entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SessionFactoryConfig {
    private static SessionFactoryConfig sessionFactoryConfig;
    private SessionFactoryConfig(){}
    public static SessionFactoryConfig getInstance(){
        return sessionFactoryConfig!=null ? sessionFactoryConfig : (sessionFactoryConfig = new SessionFactoryConfig());
    }
    public Session getSession(){
        //StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
        try {
            Properties properties = new Properties();
            InputStream inputStream = SessionFactoryConfig.class.getClassLoader().getResourceAsStream("hibernate.properties");
            properties.load(inputStream);
            Configuration configuration = new org.hibernate.cfg.Configuration();
            configuration.setProperties(properties);

            //Metadata metadata = new MetadataSources((ServiceRegistry) configuration).addAnnotatedClass(Customer.class).getMetadataBuilder().build();

            configuration.addAnnotatedClass(Customer.class);
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            return sessionFactory.openSession();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
