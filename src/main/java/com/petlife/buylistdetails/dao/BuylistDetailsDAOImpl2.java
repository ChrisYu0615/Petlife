package com.petlife.buylistdetails.dao;

import java.util.List;

import org.hibernate.Session;

import com.petlife.buylistdetails.entity.BuylistDetails;
import com.petlife.util.HibernateUtil;

public class BuylistDetailsDAOImpl2 implements BuylistDetailsDAO {
	@Override
	public Integer add(BuylistDetails buylistDetails) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Integer id = (Integer) session.save(buylistDetails);
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
	public Integer delete(Integer buylistDetailsId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			BuylistDetails buylistDetails = session.get(BuylistDetails.class, buylistDetailsId);
			if (buylistDetails != null) {
				session.delete(buylistDetails);
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
	public Integer update(BuylistDetails buylistDetails) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			session.update(buylistDetails);

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
	public BuylistDetails findByPK(Integer buylistDetailsId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			BuylistDetails buylistDetails = session.get(BuylistDetails.class, buylistDetailsId);

			session.getTransaction().commit();
			return buylistDetails;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
		return null;
	}

	@Override
	public List<BuylistDetails> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			List<BuylistDetails> buylistDetailsList = session.createQuery("from BuylistDetails", BuylistDetails.class).getResultList();

			session.getTransaction().commit();
			return buylistDetailsList;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
		return null;
	}
}
