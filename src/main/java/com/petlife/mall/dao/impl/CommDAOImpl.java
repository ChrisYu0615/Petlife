package com.petlife.mall.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.petlife.mall.dao.CommDAO;
import com.petlife.mall.entity.Comm;
import com.petlife.util.HibernateUtil;

public class CommDAOImpl implements CommDAO{
	private SessionFactory factory;
	
	public CommDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}
	
	private Session getSession() {
		return factory.getCurrentSession();
	}
	
	@Override
	public Integer add(Comm comm) {
		Integer id = (Integer) getSession().save(comm);
		return id;
	}

	@Override
	public Integer delete(Integer commId) {
		Comm comm = getSession().get(Comm.class, commId);
		if(comm != null) {
			getSession().delete(comm);
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public Integer update(Comm comm) {
		try {
			getSession().update(comm);
			return 1;
		} catch(Exception e) {
			return -1;
		}
	}

	@Override
	public Comm findByPk(Integer commId) {
		getSession().clear();
		return getSession().get(Comm.class, commId);
	}

//	@Override
//	public Comm findByUserId(Integer userId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Comm findByCatId(Integer comm_cat_id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Comm findByPriceRange(Integer max_price, Integer min_price) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public List<Comm> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
