package com.petlife.admin.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "coupon")
public class Coupon {
	@Id
	@Column(name = "coupon_id", updatable = false)
	private Integer couponId;

	@Column(name = "coupon_name", columnDefinition = "char")
	private String couponName;
	@Column(name = "coupon_content", columnDefinition = "char")
	private String couponContent;
	@Column(name = "conditions_of_use")
	private Integer conditionsOfUse;
	@Column(name = "start_date")
	private Timestamp startDate;
	@Column(name = "end_date")
	private Timestamp endDate;
	@Column(name = "discount_amount")
	private Integer discountAmount;

	public Coupon() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Coupon(Integer couponId, String couponName, String couponContent, Integer conditionsOfUse,
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

	@Override
	public String toString() {
		return "Coupon [couponId=" + couponId + ", couponName=" + couponName + ", couponContent=" + couponContent
				+ ", conditionsOfUse=" + conditionsOfUse + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", discountAmount=" + discountAmount + "]";
	}

}
