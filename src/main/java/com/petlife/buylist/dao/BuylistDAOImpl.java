package com.petlife.buylist.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.petlife.buylist.entity.Buylist;
import com.petlife.util.HibernateUtil;

public class BuylistDAOImpl implements BuylistDAO {
	private SessionFactory factory;

	public BuylistDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public Integer add(Buylist buylist) {
		return (Integer) getSession().save(buylist);
	}

	@Override
	public Integer delete(Integer buylistId) {
		Buylist buylist = getSession().get(Buylist.class, buylistId);
		if (buylist != null) {
			getSession().delete(buylist);
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public Integer update(Buylist buylist) {
		try {
			getSession().update(buylist);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public Buylist findByPK(Integer buylistId) {
		return getSession().get(Buylist.class, buylistId);
	}

	@Override
	public List<Buylist> getAll() {
		return getSession().createQuery("from Buylist", Buylist.class).getResultList();
	}

}
