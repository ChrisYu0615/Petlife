package com.petlife.admin.service.impl;

import java.util.List;

import com.petlife.admin.dao.CouponDAO;
import com.petlife.admin.dao.impl.CouponDAOImpl;
import com.petlife.admin.entity.Coupon;
import com.petlife.admin.service.CouponService;

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
