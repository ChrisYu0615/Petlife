package com.petlife.acctstate.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.petlife.acctstate.entity.AcctState;
import com.petlife.user.entity.User;
import com.petlife.util.HibernateUtil;

public class AcctStateDAOImpl implements AcctStateDAO {

	@Override
	public Integer add(AcctState acctState) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Integer id = (Integer) session.save(acctState);
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
	public Integer delete(Integer acctStateId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			AcctState acctState = session.get(AcctState.class, acctStateId);
			if (acctState != null) {
				session.delete(acctState);
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
	public Integer update(AcctState acctState) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			session.update(acctState);

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
	public AcctState findByPK(Integer acctStateId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			AcctState acctState = session.get(AcctState.class, acctStateId);

			session.getTransaction().commit();
			return acctState;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
		return null;
	}

	@Override
	public List<AcctState> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			List<AcctState> acctStateList = session.createQuery("from AcctState", AcctState.class).getResultList();
			
			session.getTransaction().commit();
			return acctStateList;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
		return null;
	}

}
