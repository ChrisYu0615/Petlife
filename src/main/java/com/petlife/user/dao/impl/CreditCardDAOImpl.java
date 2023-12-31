package com.petlife.user.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.petlife.user.dao.CreditCardDAO;
import com.petlife.user.entity.CreditCard;
import com.petlife.util.HibernateUtil;

public class CreditCardDAOImpl implements CreditCardDAO {
	private SessionFactory factory;

	public CreditCardDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public Integer add(CreditCard creditCard) {
		return (Integer) getSession().save(creditCard);
	}

	@Override
	public Integer delete(Integer creditCardId) {
		CreditCard creditCard = getSession().get(CreditCard.class, creditCardId);
		if (creditCard != null) {
			getSession().delete(creditCard);
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public Integer update(CreditCard creditCard) {
		try {
			getSession().update(creditCard);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public CreditCard findByPK(Integer creditCardId) {
		return getSession().get(CreditCard.class, creditCardId);
	}
	
	@Override
	public CreditCard findByUserId(Integer userId) {
	    try {
	        return getSession()
	                .createQuery("FROM CreditCard WHERE user.userId = :userId", CreditCard.class)
	                .setParameter("userId", userId)
	                .getSingleResult();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}


	@Override
	public List<CreditCard> getAll() {
		return getSession().createQuery("from CreditCard", CreditCard.class).getResultList();
	}

}
