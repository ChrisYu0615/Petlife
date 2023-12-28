package com.petlife.admin.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.petlife.admin.dao.AcctTypeDAO;
import com.petlife.admin.entity.AcctType;
import com.petlife.util.HibernateUtil;

public class AcctTypeDAOImpl implements AcctTypeDAO {
	private SessionFactory factory;

	public AcctTypeDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public Integer add(AcctType acctType) {
		return (Integer) getSession().save(acctType);
	}

	@Override
	public Integer delete(Integer acctTypeId) {
		AcctType acctType = getSession().get(AcctType.class, acctTypeId);
		if (acctType != null) {
			getSession().delete(acctType);
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public Integer update(AcctType acctType) {
		try {
			getSession().update(acctType);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public AcctType findByPK(Integer acctTypeId) {
		return getSession().get(AcctType.class, acctTypeId);
	}

	@Override
	public List<AcctType> getAll() {
		return getSession().createQuery("from AcctType", AcctType.class).getResultList();
	}

}
