package com.petlife.accttype.dao;

import java.util.List;

import org.hibernate.Session;

import com.petlife.accttype.entity.AcctType;
import com.petlife.util.HibernateUtil;

public class AcctTypeDAOImpl implements AcctTypeDAO {

	@Override
	public Integer add(AcctType acctType) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Integer id = (Integer) session.save(acctType);
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
	public Integer delete(Integer acctTypeId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			AcctType acctType = session.get(AcctType.class, acctTypeId);
			if (acctType != null) {
				session.delete(acctType);
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
	public Integer update(AcctType acctType) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			session.update(acctType);

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
	public AcctType findByPK(Integer acctTypeId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			AcctType acctType = session.get(AcctType.class, acctTypeId);

			session.getTransaction().commit();
			return acctType;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
		return null;
	}

	@Override
	public List<AcctType> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			List<AcctType> acctTypeList = session.createQuery("from AcctType", AcctType.class).getResultList();

			session.getTransaction().commit();
			return acctTypeList;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
		return null;
	}

}
