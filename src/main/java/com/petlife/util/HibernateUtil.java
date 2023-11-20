package com.petlife.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	private static StandardServiceRegistry registry;
	private static final SessionFactory sessionFactory = createSessionFactory();

	// 私有化建構式
	private HibernateUtil() {
	}

	// 公開方法用來返回SessionFactory實例
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	private static SessionFactory createSessionFactory() {
		try {
			// 載入系統組態檔
			registry = new StandardServiceRegistryBuilder().configure().build();

			// 創建SessionFactory
			SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
			// 返回SessionFactory實例
			return sessionFactory;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
	}

	public static void shutdown() {
		if (registry != null) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}
}
