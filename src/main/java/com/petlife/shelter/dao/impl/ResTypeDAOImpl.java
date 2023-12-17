package com.petlife.shelter.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.petlife.shelter.dao.ResTypeDAO;
import com.petlife.shelter.entity.ResType;
import com.petlife.util.HibernateUtil;



public class ResTypeDAOImpl implements ResTypeDAO {
	
	private SessionFactory factory;
	
	public ResTypeDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}
	
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(ResType entity) {
		return (Integer) getSession().save(entity);
	}

	@Override
	public int update(ResType entity) {
		try {
			getSession().update(entity);
			return 1;
		}catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int delete(Integer id) {
		ResType resType = getSession().get(ResType.class, id);
		if(resType != null) {
			getSession().delete(resType);
			return 1;
		}else {
			return -1;
		}
	}

	@Override
	public ResType getById(Integer id) {
		return getSession().get(ResType.class, id);
	}

	@Override
	public List<ResType> getAll() {
		return getSession().createQuery("from ResType", ResType.class).list();
	}
	


}