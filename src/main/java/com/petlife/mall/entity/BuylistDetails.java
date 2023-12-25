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

@Entity
@Table(name = "buylist_details")
public class BuylistDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "buylist_details_id", updatable = false)
	@Expose
	private Integer buylistDetailsId;

	@ManyToOne // buylist_id
	@JoinColumn(name = "buylist_id", referencedColumnName = "buylist_id")
	private Buylist buylist;

	@ManyToOne // comm_id
	@JoinColumn(name = "comm_id", referencedColumnName = "comm_id")
	@Expose
	private Comm comm;

	@Column(name = "buylist_details_price",columnDefinition = "Decimal")
	@Expose
	private BigDecimal buylistDetailsPrice;

	@Column(name = "buylist_details_purchase_amount")
	@Expose
	private Integer buylistDetailsPurchaseAmount;

	public BuylistDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BuylistDetails(Integer buylistDetailsId, Buylist buylist, Comm comm, BigDecimal buylistDetailsPrice,
			Integer buylistDetailsPurchaseAmount) {
		super();
		this.buylistDetailsId = buylistDetailsId;
		this.buylist = buylist;
		this.comm = comm;
		this.buylistDetailsPrice = buylistDetailsPrice;
		this.buylistDetailsPurchaseAmount = buylistDetailsPurchaseAmount;
	}

	public Integer getBuylistDetailsId() {
		return buylistDetailsId;
	}

	public void setBuylistDetailsId(Integer buylistDetailsId) {
		this.buylistDetailsId = buylistDetailsId;
	}

	public Buylist getBuylist() {
		return buylist;
	}

	public void setBuylist(Buylist buylist) {
		this.buylist = buylist;
	}

	public Comm getComm() {
		return comm;
	}

	public void setComm(Comm comm) {
		this.comm = comm;
	}

	public BigDecimal getBuylistDetailsPrice() {
		return buylistDetailsPrice;
	}

	public void setBuylistDetailsPrice(BigDecimal buylistDetailsPrice) {
		this.buylistDetailsPrice = buylistDetailsPrice;
	}

	public Integer getBuylistDetailsPurchaseAmount() {
		return buylistDetailsPurchaseAmount;
	}

	public void setBuylistDetailsPurchaseAmount(Integer buylistDetailsPurchaseAmount) {
		this.buylistDetailsPurchaseAmount = buylistDetailsPurchaseAmount;
	}

	@Override
	public String toString() {
		return "BuylistDetails [buylistDetailsId=" + buylistDetailsId + ", buylist=" + buylist
				+ ", buylistDetailsPrice=" + buylistDetailsPrice + ", buylistDetailsPurchaseAmount="
				+ buylistDetailsPurchaseAmount ;
	}

//	@OneToMany(mappedBy = "acctState", cascade = CascadeType.ALL)
//	private Set<User> users;		//mappedBy對應user76行變數,Set<對應到的表格>變數

}
