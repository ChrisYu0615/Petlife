package com.petlife.admin.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.oracle.wls.shaded.org.apache.regexp.recompile;
import com.petlife.admin.dao.AcctStateDAO;
import com.petlife.admin.entity.AcctState;
import com.petlife.util.HibernateUtil;

public class AcctStateDAOImpl implements AcctStateDAO {
	private SessionFactory factory;

	public AcctStateDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public Integer add(AcctState acctState) {
		return (Integer) getSession().save(acctState);
	}

	@Override
	public Integer delete(Integer acctStateId) {
		AcctState acctState = getSession().get(AcctState.class, acctStateId);
		if (acctState != null) {
			getSession().delete(acctState);
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public Integer update(AcctState acctState) {
		try {
			getSession().update(acctState);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public AcctState findByPK(Integer acctStateId) {
		return getSession().get(AcctState.class, acctStateId);
	}

	@Override
	public List<AcctState> getAll() {
		return getSession().createQuery("from AcctState ", AcctState.class).getResultList();
	}

}
