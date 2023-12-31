package com.petlife.mall.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.petlife.mall.dao.BuylistDAO;
import com.petlife.mall.entity.Buylist;
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
	public List<Buylist> getAll(String memberId) {
		switch (memberId.charAt(0)) {
		case '1':
			// 1開頭(一般會員)
			Integer userId = Integer.valueOf(memberId);
			return getSession().createQuery("from Buylist where user.userId=:userId", Buylist.class)
					.setParameter("userId", userId).getResultList();
		case '2':
			// 2開頭(賣家會員)
			Integer sellerId = Integer.valueOf(memberId);
			return getSession().createQuery("from Buylist where seller.sellerId=:sellerId", Buylist.class)
					.setParameter("sellerId", sellerId).getResultList();
		}

		return getSession().createQuery("from Buylist", Buylist.class).getResultList();
	}

	@Override
	public List<Buylist> getBuylistsByState(Integer buylistState, Integer sellerId) {
		try {
			Session session = getSession();
			List<Buylist> buylists = session
					.createQuery("from Buylist where buylistState.buylistStateId = :state and seller.sellerId = :sellerId", Buylist.class)
					.setParameter("state", buylistState).setParameter("sellerId", sellerId).getResultList();
			return buylists;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Buylist> getAll() {
		return getSession().createQuery("from Buylist ", Buylist.class).getResultList();
	}

	@Override
	public Double getUserRating(Integer userId) {
		return getSession().createQuery(
				"select AVG(memberRatingStars) from Buylist where user.userId=:userId and memberRatingStars is not null",
				Double.class).setParameter("userId", userId).getSingleResult();
	}

}
