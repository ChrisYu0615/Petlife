package com.petlife.seller.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.petlife.seller.entity.Seller;
import com.petlife.util.HibernateUtil;

public class SellerDAOImpl implements SellerDAO{
	private SessionFactory factory;
	
	public SellerDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}
	
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public Integer add(Seller seller) {
		return (Integer)getSession().save(seller);
	}

	@Override
	public Integer delete(Integer sellerId) {
		Seller seller=  getSession().get(Seller.class, sellerId);
		if (seller != null) {
			getSession().delete(seller);
			return 1;
		}else {
		return -1;
		}
	}

	@Override
	public Integer update(Seller seller) {
		try {
			getSession().update(seller);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public Seller findByPK(Integer sellerId) {
		return getSession().get(Seller.class, sellerId);
	}

	@Override
	public List<Seller> getAll() {
		return getSession().createQuery("from Seller", Seller.class).getResultList();
	}
	
	
}
