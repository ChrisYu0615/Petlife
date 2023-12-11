package com.petlife.admin.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.petlife.admin.dao.AdminDAO;
import com.petlife.admin.entity.Admin;
import com.petlife.user.entity.User;
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
		return (Integer) getSession().save(admin);
	}

	@Override
	public Integer delete(Integer adminId) {
		Admin admin = getSession().get(Admin.class, adminId);
		if (admin != null) {
			getSession().delete(admin);
			return 1;
		}
		return -1;
	}

	@Override
	public Integer update(Admin admin) {
		getSession().flush();
		try {
			getSession().update(admin);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public Admin findByPK(Integer adminId) {
		getSession().clear();
		return getSession().get(Admin.class, adminId);
	}

	@Override
	public List<Admin> getAll() {
		return getSession().createQuery("from Admin", Admin.class).getResultList();
	}

	@Override
	public Admin findByAdminAccount(String adminAcct) {
		List<Admin> admin = getSession().createQuery("from Admin where adminAcct=:adminAcct", Admin.class)
				.setParameter("adminAcct", adminAcct).getResultList();

		if (admin.size() > 0) {
			return admin.get(0);
		}
		return null;
	}

	@Override
	public Admin findByAdminAccountAndPassword(String adminAcct, String adminPwd) {
		List<Admin> admin = getSession()
				.createQuery("from Admin where adminAcct=:adminAcct and adminPwd=:adminPwd", Admin.class)
				.setParameter("adminAcct", adminAcct).setParameter("adminPwd", adminPwd).getResultList();
		
		if (admin.size() > 0) {
			return admin.get(0);
		}
		return null;
	}

}
