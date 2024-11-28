package com.example.onlinebookstore.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public HibernateUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                System.out.println("Attempting to configure Hibernate...");
                String environment = System.getProperty("hibernate.env", "production"); // Default to production
                String configFile = environment.equals("test") ? "hibernate-test.cfg.xml" : "hibernate.cfg.xml";

                StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .configure(configFile) // Load the appropriate configuration file
                        .build();
                sessionFactory = new Configuration().buildSessionFactory(serviceRegistry);
                System.out.println("Hibernate SessionFactory created successfully.");
            } catch (Exception e) {
                System.err.println("Error creating SessionFactory: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
