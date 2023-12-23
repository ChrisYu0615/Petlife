package com.petlife.mall.dao.impl;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.petlife.mall.dao.CartDAO;
import com.petlife.mall.entity.Cart;
import com.petlife.user.entity.User;
import com.petlife.util.HibernateUtil;

public class CartDAOImpl implements CartDAO {
	private SessionFactory factory;

	public CartDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public Integer add(Cart cart) {
		return (Integer) getSession().save(cart);
	}

	@Override
	public Integer delete(Integer cartId) {
		Cart cart = getSession().get(Cart.class, cartId);
		if (cart != null) {
			getSession().delete(cart);
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public Integer update(Cart cart) {
		try {
			getSession().update(cart);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public Cart findByPK(Integer cartId) {
		return getSession().get(Cart.class, cartId);
	}

	@Override
	public List<Cart> getAll() {
		return getSession().createQuery("from Cart", Cart.class).getResultList();
	}
	
	@Override
	public List<Cart> getCartsByUser(User user) {
	    try {
	        Session session = getSession();
	        List<Cart> carts = session.createQuery("from Cart where user.userId = :userId", Cart.class)
	                .setParameter("userId", user.getUserId())
	                .getResultList();
	        return carts; 
	    } catch (Exception e) {
	        e.printStackTrace();
	        return Collections.emptyList();
	    }
	}
}
