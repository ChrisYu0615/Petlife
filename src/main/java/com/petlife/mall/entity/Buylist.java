package com.petlife.mall.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.petlife.admin.entity.Coupon;
import com.petlife.seller.entity.Seller;
import com.petlife.user.entity.User;

@Entity
@Table(name = "buylist")
public class Buylist {
	@Id
	@Column(name = "buylist_id", updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Integer buylistId;

	@ManyToOne   //user_id
    @JoinColumn(name = "user_id" ,referencedColumnName = "user_id")
    private User user;
	
	@ManyToOne   //seller_id
    @JoinColumn(name = "seller_id" ,referencedColumnName = "seller_id")
	@Expose
    private Seller seller;
	
	@ManyToOne   //buylist_state_id
    @JoinColumn(name = "buylist_state_id" ,referencedColumnName = "buylist_state_id")
    private BuylistState buylistState;
	
	@ManyToOne   //coupon_id
    @JoinColumn(name = "coupon_id" ,referencedColumnName = "coupon_id",nullable = true)
    private Coupon coupon;
	
	@Column(name = "seller_rating_stars")
	private Double sellerRatingStars;
	
	@Column(name = "seller_evaluate_narrative",columnDefinition = "LONGTEXT")
	private String sellerEvaluateNarrative;
	
	@Column(name = "seller_evaluate_time")
	private Timestamp sellerEvaluateTime;
	
	@Column(name = "buylist_amount", columnDefinition = "Decimal")
	@Expose
	private BigDecimal buylistAmount;
	
	@Column(name = "buylist_date")
	private Timestamp buylistDate;

//	@OneToMany(mappedBy = "acctState", cascade = CascadeType.ALL)
//	private Set<User> users;		//mappedBy對應user76行變數,Set<對應到的表格>變數

//	public BuyList() {
//	};
	public Buylist() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Integer getBuylistId() {
		return buylistId;
	}

	public void setBuylistId(Integer buylistId) {
		this.buylistId = buylistId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public BuylistState getBuylistState() {
		return buylistState;
	}

	public void setBuylistState(BuylistState buylistState) {
		this.buylistState = buylistState;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public Double getSellerRatingStars() {
		return sellerRatingStars;
	}

	public void setSellerRatingStars(Double sellerRatingStars) {
		this.sellerRatingStars = sellerRatingStars;
	}

	public String getSellerEvaluateNarrative() {
		return sellerEvaluateNarrative;
	}

	public void setSellerEvaluateNarrative(String sellerEvaluateNarrative) {
		this.sellerEvaluateNarrative = sellerEvaluateNarrative;
	}

	public Timestamp getSellerEvaluateTime() {
		return sellerEvaluateTime;
	}

	public void setSellerEvaluateTime(Timestamp sellerEvaluateTime) {
		this.sellerEvaluateTime = sellerEvaluateTime;
	}

	public BigDecimal getBuylistAmount() {
		return buylistAmount;
	}

	public void setBuylistAmount(BigDecimal buylistAmount) {
		this.buylistAmount = buylistAmount;
	}

	public Timestamp getBuylistDate() {
		return buylistDate;
	}

	public void setBuylistDate(Timestamp buylistDate) {
		this.buylistDate = buylistDate;
	}

	@Override
	public String toString() {
		return "Buylist [buylistId=" + buylistId + ", userId=" + user + ", seller_id=" + seller + ", buylist_state_id=" + buylistState +
				", coupon_id=" + coupon +", seller_rating_stars=" + sellerRatingStars +", seller_evaluate_narrative=" + sellerEvaluateNarrative +
				", seller_evaluate_time=" + sellerEvaluateTime +", buylist_amount=" + buylistAmount +", buylist_date=" + buylistDate +"]";
	}
}
