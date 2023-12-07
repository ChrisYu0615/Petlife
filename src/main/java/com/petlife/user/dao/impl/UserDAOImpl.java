package com.petlife.user.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.petlife.user.dao.UserDAO;
import com.petlife.user.entity.User;
import com.petlife.util.HibernateUtil;

public class UserDAOImpl implements UserDAO {

	@Override
	public Integer add(User user) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			Integer id = (Integer) session.save(user);

			session.getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
		return -1;
	}

	@Override
	public Integer delete(Integer userId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			User user = session.get(User.class, userId);
			if (user != null) {
				session.delete(user);
			}
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
		return -1;
	}

	@Override
	public Integer update(User user) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			session.update(user);

			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
		return -1;
	}

	@Override
	public User findByPK(Integer userId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			User user = session.get(User.class, userId);

			session.getTransaction().commit();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
		return null;
	}

	@Override
	public List<User> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			List<User> userList = session.createQuery("from User", User.class).getResultList();

			session.getTransaction().commit();
			return userList;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
		return null;
	}

	@Override
	public Long getTotal() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			Long count = session.createQuery("select count(*) from User", Long.class).getSingleResult();

			session.getTransaction().commit();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
		return 0L;
	}

	@Override
	public User findUserByUserNickname(String userNickname) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<User> user = session.createQuery("from User where userNickName=:userNickName", User.class)
					.setParameter("userNickName", userNickname).getResultList();
			session.getTransaction().commit();

			if (user.size() > 0) {
				return user.get(0);
			}
			return null;

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
		return null;
	}

	@Override
	public User findUserByUserAccount(String userAcct) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserByUserAccountAndPassword(String userAcct, String userPwd) {
		// TODO Auto-generated method stub
		return null;
	}

}
