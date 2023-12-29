package com.petlife.admin.service;

import java.util.List;

import com.petlife.admin.entity.Coupon;

public interface CouponService {

	// 增
	Coupon addCoupon(Coupon coupon);

	// 刪
	Integer deleteCoupon(Integer couponId);

	// 改
	Integer updateCoupon(Coupon coupon);

	// 查(單個)
	Coupon getCouponByCouponId(Integer couponId);

	// 查(多個)
	List<Coupon> getAllCoupons();
	
	// 使用者驗證coupon
	Coupon getCouponByCouponName(String couponNameString);
}
