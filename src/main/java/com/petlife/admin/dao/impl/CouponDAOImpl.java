package com.petlife.admin.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.petlife.admin.dao.CouponDAO;
import com.petlife.admin.entity.Coupon;
import com.petlife.util.HibernateUtil;

public class CouponDAOImpl implements CouponDAO {
	private SessionFactory factory;

	public CouponDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public Integer add(Coupon coupon) {
		return (Integer) getSession().save(coupon);
	}

	@Override
	public Integer delete(Integer couponId) {
		Coupon coupon = getSession().get(Coupon.class, couponId);
		if (coupon != null) {
			getSession().delete(coupon);
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public Integer update(Coupon coupon) {
		try {
			getSession().update(coupon);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public Coupon findByPK(Integer couponId) {
		return getSession().get(Coupon.class, couponId);
	}

	@Override
	public List<Coupon> getAll() {
		return getSession().createQuery("from Coupon ", Coupon.class).getResultList();
	}

	@Override
	public Coupon getCouponByCouponName(String couponNameString) {
	    String hql = "FROM Coupon WHERE couponName = :couponName";
	    Query<Coupon> query = getSession().createQuery(hql, Coupon.class);
	    query.setParameter("couponName", couponNameString);
	    return query.uniqueResult();
	}
}
