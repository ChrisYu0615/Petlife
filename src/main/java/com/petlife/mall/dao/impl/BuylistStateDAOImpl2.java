package com.petlife.mall.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.petlife.mall.dao.BuylistStateDAO;
import com.petlife.mall.entity.BuylistState;
import com.petlife.util.HibernateUtil;

public class BuylistStateDAOImpl2 implements BuylistStateDAO {
	@Override
	public Integer add(BuylistState buylistState) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Integer id = (Integer) session.save(buylistState);
			session.getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
		return -1;
	}

	@Override
	public Integer delete(Integer buylistStateId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			BuylistState buylistState = session.get(BuylistState.class, buylistStateId);
			if (buylistState != null) {
				session.delete(buylistState);
			}
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
		return -1;
	}

	@Override
	public Integer update(BuylistState buylistState) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			session.update(buylistState);

			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
		return -1;
	}

	@Override
	public BuylistState findByPK(Integer buylistStateId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			BuylistState buylistState = session.get(BuylistState.class, buylistStateId);

			session.getTransaction().commit();
			return buylistState;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
		return null;
	}

	@Override
	public List<BuylistState> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			List<BuylistState> buylistStateList = session.createQuery("from BuylistState", BuylistState.class).getResultList();

			session.getTransaction().commit();
			return buylistStateList;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
		return null;
	}
}
