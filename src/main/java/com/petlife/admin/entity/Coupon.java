package com.petlife.admin.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "coupon")
public class Coupon {
	@Id
	@Column(name = "coupon_id", updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer couponId;

	@Column(name = "coupon_name")
	private String couponName;
	@Column(name = "coupon_content")
	private String couponContent;
	@Column(name = "conditions_of_use")
	private Integer conditionsOfUse;
	@Column(name = "coupon_state")
	private Boolean couponState;
	@Column(name = "start_date")
	private Date startDate;
	@Column(name = "end_date")
	private Date endDate;
	@Column(name = "discount_amount")
	private Integer discountAmount;

	public Coupon() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Coupon(String couponName, String couponContent, Integer conditionsOfUse, Date startDate, Date endDate,
			Integer discountAmount) {
		super();
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


	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Integer discountAmount) {
		this.discountAmount = discountAmount;
	}

	public Boolean getCouponState() {
		return couponState;
	}

	public void setCouponState(Boolean couponState) {
		this.couponState = couponState;
	}

	@Override
	public String toString() {
		return "Coupon [couponId=" + couponId + ", couponName=" + couponName + ", couponContent=" + couponContent
				+ ", conditionsOfUse=" + conditionsOfUse + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", discountAmount=" + discountAmount + "]";
	}

}
