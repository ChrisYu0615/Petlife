package com.petlife.admin.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.petlife.admin.entity.Admin;
import com.petlife.util.HibernateUtil;

public class AdminDAOImpl implements AdminDAO {
	private SessionFactory factory;

	public AdminDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public Integer add(Admin admin) {
//		Session session = getSession();
//		try {
//			session.beginTransaction();
//			Integer id = (Integer) session.save(admin);
//			session.getTransaction().commit();
//			return id;
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		} finally {
//			HibernateUtil.shutdown();
//		}
//		return -1;
		
		return (Integer) getSession().save(admin);
	}

	@Override
	public Integer delete(Integer adminId) {
//		Session session = getSession();
//		try {
//			session.beginTransaction();
//			Admin admin = getSession().get(Admin.class, adminId);
//			if (admin != null) {
//				getSession().delete(admin);
//			}
//			session.getTransaction().commit();
//			return 1;
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		} finally {
//			HibernateUtil.shutdown();
//		}
//		return -1;

		Admin admin = getSession().get(Admin.class, adminId);
		if (admin != null) {
			getSession().delete(admin);
			return 1;
		}
		return -1;
	}

	@Override
	public Integer update(Admin admin) {
//		Session session = getSession();
//		try {
//			session.beginTransaction();
//			session.update(admin);
//			session.getTransaction().commit();
//			return 1;
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		} finally {
//			HibernateUtil.shutdown();
//		}
//		return -1;

		try {
			getSession().update(admin);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public Admin findByPK(Integer adminId) {
//		Session session = getSession();
//
//		try {
//			session.beginTransaction();
//			Admin admin = session.get(Admin.class, adminId);
//			session.getTransaction().commit();
//			return admin;
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		} finally {
//			HibernateUtil.shutdown();
//		}
//		return null;
		return getSession().get(Admin.class, adminId);
	}

	@Override
	public List<Admin> getAll() {
//		Session session = getSession();
//		try {
//			session.beginTransaction();
//			List<Admin> admins = session.createQuery("from Admin", Admin.class).getResultList();
//			session.getTransaction().commit();
//			return admins;
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		} finally {
//			HibernateUtil.shutdown();
//		}
//		return null;

		return getSession().createQuery("from Admin", Admin.class).getResultList();
	}

}
