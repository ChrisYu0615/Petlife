package com.petlife.couponjdbc;

import java.sql.Timestamp;

public class CouponVO {
	private Integer couponId; 
	private String couponName;
	private String couponContent;
	private Integer conditionsOfUse;
	private Timestamp startDate;
	private Timestamp endDate;
	private Integer discountAmount;
	
	public  CouponVO() {
		// TODO Auto-generated constructor stub
	}

	public CouponVO(Integer couponId, String couponName, String couponContent, Integer conditionsOfUse,
			Timestamp startDate, Timestamp endDate, Integer discountAmount) {
		super();
		this.couponId = couponId;
		this.couponName = couponName;
		this.couponContent = couponContent;
		this.conditionsOfUse = conditionsOfUse;
		this.startDate = startDate;
		this.endDate = endDate;
		this.discountAmount = discountAmount;
	}

	public Integer getCouponId() {
		return couponId;
	}

	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public String getCouponContent() {
		return couponContent;
	}

	public void setCouponContent(String couponContent) {
		this.couponContent = couponContent;
	}

	public Integer getConditionsOfUse() {
		return conditionsOfUse;
	}

	public void setConditionsOfUse(Integer conditionsOfUse) {
		this.conditionsOfUse = conditionsOfUse;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public Integer getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Integer discountAmount) {
		this.discountAmount = discountAmount;
	}


	
	
	
	
	
}
