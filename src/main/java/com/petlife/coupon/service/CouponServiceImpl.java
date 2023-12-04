package com.petlife.coupon.service;

import java.util.List;

import com.petlife.coupon.dao.CouponDAO;
import com.petlife.coupon.dao.CouponDAOImpl;
import com.petlife.coupon.entity.Coupon;

public class CouponServiceImpl implements CouponService {
	private CouponDAO dao;

	public CouponServiceImpl() {
		dao = new CouponDAOImpl();
	}

	@Override
	public Coupon addCoupon(Coupon coupon) {
		Integer id = dao.add(coupon);
		coupon = dao.findByPK(id);
		return coupon;
	}

	@Override
	public Integer deleteCoupon(Integer couponId) {
		return dao.delete(couponId);
	}

	@Override
	public Integer updateCoupon(Coupon coupon) {
		return dao.update(coupon);
	}

	@Override
	public Coupon getCouponByCouponId(Integer couponId) {
		return dao.findByPK(couponId);
	}

	@Override
	public List<Coupon> getAllCoupons() {
		return dao.getAll();
	}
}
