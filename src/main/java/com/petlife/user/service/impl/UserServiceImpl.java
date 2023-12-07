package com.petlife.user.service.impl;

import java.util.List;

import com.petlife.user.dao.UserDAO;
import com.petlife.user.dao.impl.UserDAOImpl2;
import com.petlife.user.entity.User;
import com.petlife.user.service.UserServeice;

public class UserServiceImpl implements UserServeice {
	private UserDAO dao;

	public UserServiceImpl() {
		dao = new UserDAOImpl2();
	}

	@Override
	public User addUser(User user) {
		Integer id = dao.add(user);
		user = dao.findByPK(id);
//		return dao.add(user);
		return user;
	}

	// 測試Json寫法()用於取得註冊成功的會員資料
//	@Override
//	public User registUser(User user) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//
//		try {
//			session.beginTransaction();
//
//			Integer id = dao.add(user);
//			user = dao.findByPK(id);
//
//			session.getTransaction().commit();
//			return user;
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		} finally {
//			HibernateUtil.shutdown();
//		}
//		return null;
//	}

	@Override
	public Integer deleteUser(Integer userId) {
		return dao.delete(userId);
	}

	@Override
	public Integer updateUser(User user) {
		return dao.update(user);
	}

	@Override
	public User getUserByUserId(Integer userId) {
		return dao.findByPK(userId);
	}

	@Override
	public List<User> getAllUsers() {
		return dao.getAll();
	}

	@Override
	public int getPageTotal() {
		return 0;
	}

	@Override
	public boolean existUserNickname(String userNickName) {
		if (dao.findUserByUserNickname(userNickName) != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean exisUserAccount(String userAccount) {
		if (dao.findUserByUserAccount(userAccount) != null) {
			return true;
		}
		return false;
	}

}
