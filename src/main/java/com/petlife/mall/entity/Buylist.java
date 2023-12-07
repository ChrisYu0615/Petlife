package com.petlife.mall.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.petlife.admin.entity.Coupon;
import com.petlife.member.entity.Member;
import com.petlife.seller.entity.Seller;

@Entity
@Table(name = "buylist")
public class Buylist {
	@Id
	@Column(name = "buylist_id", updatable = false)
	private Integer buylistId;

	@ManyToOne   //user_id
    @JoinColumn(name = "user_id" ,referencedColumnName = "user_id")
    private Member user;
	
	@ManyToOne   //seller_id
    @JoinColumn(name = "seller_id" ,referencedColumnName = "seller_id")
    private Seller seller;
	
	@ManyToOne   //buylist_state_id
    @JoinColumn(name = "buylist_state_id" ,referencedColumnName = "buylist_state_id")
    private BuylistState buylistState;
	
	@ManyToOne   //coupon_id
    @JoinColumn(name = "coupon_id" ,referencedColumnName = "coupon_id")
    private Coupon coupon;
	
	@Column(name = "seller_rating_stars")
	private double sellerRatingStars;
	
	@Column(name = "seller_evaluate_narrative",columnDefinition = "LONGTEXT")
	private String sellerEvaluateNarrative;
	
	@Column(name = "seller_evaluate_time")
	private Timestamp sellerEvaluateTime;
	
	@Column(name = "buylist_amount")
	private int buylistAmount;
	
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

	public Member getUser() {
		return user;
	}

	public void setUser(Member user) {
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

	public double getSellerRatingStars() {
		return sellerRatingStars;
	}

	public void setSellerRatingStars(double sellerRatingStars) {
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

	public int getBuylistAmount() {
		return buylistAmount;
	}

	public void setBuylistAmount(int buylistAmount) {
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
