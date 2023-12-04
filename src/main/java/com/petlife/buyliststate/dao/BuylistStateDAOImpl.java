package com.petlife.buyliststate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.petlife.buyliststate.entity.BuylistState;
import com.petlife.util.HibernateUtil;

public class BuylistStateDAOImpl implements BuylistStateDAO {
	private SessionFactory factory;

	public BuylistStateDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public Integer add(BuylistState buyliststate) {
		return (Integer) getSession().save(buyliststate);
	}

	@Override
	public Integer delete(Integer buylistStateId) {
		BuylistState buylistState = getSession().get(BuylistState.class, buylistStateId);
		if (buylistState != null) {
			getSession().delete(buylistState);
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public Integer update(BuylistState buylistState) {
		try {
			getSession().update(buylistState);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public BuylistState findByPK(Integer buylistStateId) {
		return getSession().get(BuylistState.class, buylistStateId);
	}

	@Override
	public List<BuylistState> getAll() {
		return getSession().createQuery("from BuylistState ", BuylistState.class).getResultList();
	}

}
