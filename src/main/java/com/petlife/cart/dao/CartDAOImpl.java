package com.petlife.cart.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.petlife.cart.entity.Cart;
import com.petlife.user.entity.User;
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
	public Integer add(Cart cart) {
		Session s = getSession();
		Transaction tx = s.beginTransaction();
		
		try {
			Integer id = (Integer) s.save(cart);
			
			tx.commit();
			
			return id;
		} catch(Exception e) {
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
	public void delete(Integer cartId) {
		Session s = getSession();
		Transaction tx = s.beginTransaction();
		
		try {
	        Cart cart = s.get(Cart.class, cartId);
	        if (cart != null) {
	            s.delete(cart);
	            tx.commit();
	            System.out.println("Delete cartId = " + cartId);
	        } else {
	            System.out.println("Cart not found for cartId = " + cartId);
	        }
		} catch (Exception e) {
	        if (tx != null) tx.rollback();
	        e.printStackTrace();
	        System.out.println("Failed to delete cartId = " + cartId);
		} finally {
			s.close();
		}
	}

	@Override
	public void update(Cart cart) {
		Session s = getSession();
		Transaction tx = s.beginTransaction();
		try {
			s.update(cart);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			tx.rollback();
		} finally {
			s.close();
		}
	}

	@Override
	public Cart findByUserId(User user) {
	    Session session = null;
	    Cart cart = null;
	    Transaction tx = null;

	    try {
	        session = getSession();
	        tx = session.beginTransaction();
	        CriteriaBuilder builder = session.getCriteriaBuilder();
	        CriteriaQuery<Cart> criteria = builder.createQuery(Cart.class);
	        Root<Cart> root = criteria.from(Cart.class);
	        Join<Cart, User> userJoin = root.join("user");
	        criteria.select(root).where(builder.equal(root.get("userId"), 100000002));
	        cart = session.createQuery(criteria).uniqueResult();

	        tx.commit();
	    } catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	       
	            session.close();
	        
	    }

	    return cart;
	}
}
;