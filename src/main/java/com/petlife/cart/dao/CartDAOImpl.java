package com.petlife.cart.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;


import com.petlife.cart.entity.Cart;
import com.petlife.util.HibernateUtil;

public class CartDAOImpl implements CartDAO{
	private SessionFactory factory;
	
	public CartDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}
	@Override
	public void add(Cart cart) {
		getSession().save(cart);
	}

	@Override
	public void delete(Integer cartId) {
		Cart cart = getSession().get(Cart.class, cartId);
		if(cart != null) {
			getSession().delete(cart);
		}
	}

	@Override
	public void update(Cart cart) {
		try {
			getSession().update(cart);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}

	@Override
	public Cart findByUserId(Integer userId) {
		Session session = null;
		Cart cart = null;
		
		try {
			session = getSession();
			String hqlString = "FROM Cart WHERE userId = :userId";
			Query query = session.createQuery(hqlString);
			query.setParameter("userId", userId);
			cart = (Cart) query.uniqueResult(); 
		} catch (Exception e) {
			if(session != null) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		return cart;
	}
}
