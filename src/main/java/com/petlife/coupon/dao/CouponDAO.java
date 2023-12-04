package com.petlife.coupon.dao;

import java.util.List;

import com.petlife.coupon.entity.Coupon;

public interface CouponDAO {
	//增
	public Integer add(Coupon coupon);
	//刪
	public Integer delete(Integer couponId);
	//改
	public Integer update(Coupon coupon);
	//查(單個)
	public Coupon findByPK(Integer couponId);
	//查(多個)
	public List<Coupon> getAll();
}
