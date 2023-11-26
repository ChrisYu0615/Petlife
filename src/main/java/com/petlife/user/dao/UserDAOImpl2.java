package com.petlife.user.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.petlife.user.entity.User;
import com.petlife.util.HibernateUtil;

public class UserDAOImpl2 implements UserDAO {
	private SessionFactory factory;

	public UserDAOImpl2() {
		factory = HibernateUtil.getSessionFactory();
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public Integer add(User user) {
		Integer id = (Integer) getSession().save(user);
		return id;
	}

	@Override
	public Integer delete(Integer userId) {
		User user = getSession().get(User.class, userId);
		if (user != null) {
			getSession().delete(user);
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public Integer update(User user) {
		try {
			getSession().update(user);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public User findByPK(Integer userId) {
		getSession().clear();
		return getSession().get(User.class, userId);
	}

	@Override
	public List<User> getAll() {
		return getSession().createQuery("from User", User.class).getResultList();
	}

	@Override
	public Long getTotal() {
		return getSession().createQuery("select count(*) from User", Long.class).getSingleResult();
	}

	@Override
	public User findUserByUserNickname(String userNickname) {
		List<User> users = getSession().createQuery("from User where userNickName=:userNickName", User.class)
				.setParameter("userNickName", userNickname).getResultList();

		if (users.size() > 0) {
			return users.get(0);
		}
		return null;
	}

	@Override
	public User findUserByUserAccount(String userAccount) {
		List<User> users = getSession().createQuery("from User where userAcct=:userAcct", User.class)
				.setParameter("userAcct", userAccount).getResultList();
		
		if (users.size() > 0) {
			return users.get(0);
		}
		return null;
	}
}
