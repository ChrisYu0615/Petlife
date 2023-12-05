package com.petlife.comm.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional.TxType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.mysql.cj.Query;
import com.petlife.comm.entity.Comm;
import com.petlife.util.HibernateUtil;

public class CommDAOImpl implements CommDAO{
	private SessionFactory factory;
	
	public CommDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}
	
	private Session getSession() {
		return factory.getCurrentSession();
	}
	
	@Override
	public Integer add(Comm comm) {
		Session s = getSession();
		Transaction tx = s.beginTransaction();
		try {
			Integer id = (Integer) s.save(comm);
			
			tx.commit();
			
			return id;
		} catch (Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			return null;
		} finally {
			if(s != null) {
				s.close();
			}
		}
	}

	@Override
	public Integer delete(Integer commId) {
		Session s = getSession();
		Transaction tx = s.beginTransaction();
		
		Comm comm = s.get(Comm.class, commId);
		try {
			s.delete(comm);
			tx.commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			return -1;
		} finally {
			s.close();
		}
	}

	@Override
	public Integer update(Comm comm) {
		Session s = getSession();
		Transaction tx = s.beginTransaction();
		try {
			s.update(comm);
			tx.commit();
			return 1;
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
			tx.rollback();
			return -1;
		} finally {
			s.close();
		}
	}

	@Override
	public Comm findByPk(Integer commId) {
		Session s = getSession();
		Transaction tx = s.beginTransaction();
		try {
			Comm comm = s.get(Comm.class, commId);
			tx.commit();
			return comm;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			return null;
		} finally {
//			s.close();
//			HibernateUtil.shutdown();
		}
	}

//	@Override
//	public Comm findByUserId(Integer userId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Comm findByCatId(Integer comm_cat_id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Comm findByPriceRange(Integer max_price, Integer min_price) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	
	@Override
	public List<Comm> getAll() {
		Session s = getSession();
		Transaction tx = null;
		
		try {
			tx = s.beginTransaction();
			// 看不懂就去餵GPT
			CriteriaBuilder builder = s.getCriteriaBuilder();
			CriteriaQuery<Comm> criteria = builder.createQuery(Comm.class);
			criteria.from(Comm.class);
			List<Comm> resultList = s.createQuery(criteria).getResultList();
			
			tx.commit();
			return resultList;
		} catch (Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if(s != null) {
				s.close();
			}
		}
		return null;
	}
}
