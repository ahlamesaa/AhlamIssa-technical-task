package com.luminous.integration.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class HibernateUtil {

	private static final SessionFactory sessionFactory;
	static {
		try {
			StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
					.configure("com/luminous/integration/properties/hibernate.cfg.xml").build();
			Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
			sessionFactory = metaData.getSessionFactoryBuilder().build();
		} catch (Exception th) {

			throw new ExceptionInInitializerError(th);
		}
	}

	private HibernateUtil() {
	}

	public static Session getSession() {

		return sessionFactory.openSession();
	}

	public static SessionFactory getSessionFactory() {

		return sessionFactory;
	}

	public static void shutdown() {
		if (sessionFactory != null) {
			sessionFactory.close();

		}
	}
}