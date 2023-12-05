package com.petlife.mall.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.petlife.mall.dao.BuylistDetailsDAO;
import com.petlife.mall.entity.BuylistDetails;
import com.petlife.util.HibernateUtil;

public class BuylistDetailsDAOImpl implements BuylistDetailsDAO {
	private SessionFactory factory;

	public BuylistDetailsDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public Integer add(BuylistDetails buylistDetails) {
		return (Integer) getSession().save(buylistDetails);
	}

	@Override
	public Integer delete(Integer buylistDetailsId) {
		BuylistDetails buylistDetails = getSession().get(BuylistDetails.class, buylistDetailsId);
		if (buylistDetails != null) {
			getSession().delete(buylistDetails);
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public Integer update(BuylistDetails buylistDetails) {
		try {
			getSession().update(buylistDetails);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public BuylistDetails findByPK(Integer buylistDetailsId) {
		return getSession().get(BuylistDetails.class, buylistDetailsId);
	}

	@Override
	public List<BuylistDetails> getAll() {
		return getSession().createQuery("from BuylistDetails", BuylistDetails.class).getResultList();
	}

}
