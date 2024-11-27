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
                StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .configure() // Load configuration from hibernate.cfg.xml
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
