package com.petlife.mall.entity;

import java.math.BigDecimal;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.petlife.seller.entity.Seller;

@Entity
@Table(name = "comm")
public class Comm {
	@Id
	@Column(name = "comm_id", updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Integer commId;

//	Seller 等隊友弄好才能使用
//	@ManyToOne
//	@JoinColumn(name = "seller_id")
//	@Expose
//	private Seller seller;

	@Column(name = "seller_id", updatable = true)
	@Expose
	private Integer sellerId;

	@Column(name = "comm_name", updatable = true)
	@Expose
	private String commName;

	@Column(name = "comm_desc", updatable = true, columnDefinition = "longtext")
	@Expose
	private String commDesc;

	@Column(name = "comm_state", updatable = true, columnDefinition = "tinyint")
	@Expose
	private Integer commState;

	@Column(name = "list_datetime", updatable = true)
	@Expose
	private Timestamp listDatetime;

	@Lob // 告知為大型Object
	@Column(name = "comm_img", updatable = true)
	@Expose
	private byte[] commImg;

//	等做完商品分類表再回來
//	@ManyToOne
//	@JoinColumn(name = "comm_cat_id")
//	private CommCat commCatId;

	@Column(name = "comm_cat_id", updatable = true)
	@Expose
	private Integer commCatId;

	@Column(name = "comm_stock", updatable = true)
	@Expose
	private Integer commStock;

	@Column(name = "comm_price", updatable = true,columnDefinition = "Decimal")
	@Expose
	private BigDecimal commPrice;

	@Column(name = "comm_onsale_price", updatable = true,columnDefinition = "Decimal")
	@Expose
	private BigDecimal commOnsalePrice;

	@Column(name = "comm_view_count", updatable = true)
	@Expose
	private Integer commViewCount;

	public Comm() {
	}

	public Comm(Integer commId, Integer sellerId, String commName, String commDesc, Integer commState,
			Timestamp listDatetime, byte[] commImg, Integer commCatId, Integer commStock, BigDecimal commPrice,
			BigDecimal commOnsalePrice, Integer commViewCount) {
		super();
		this.commId = commId;
		this.sellerId = sellerId;
		this.commName = commName;
		this.commDesc = commDesc;
		this.commState = commState;
		this.listDatetime = listDatetime;
		this.commImg = commImg;
		this.commCatId = commCatId;
		this.commStock = commStock;
		this.commPrice = commPrice;
		this.commOnsalePrice = commOnsalePrice;
	}

	public Integer getCommId() {
		return commId;
	}
	
	public void setCommId(Integer commId) {
		this.commId = commId;
	}
	
	public Integer getsellerId() {
		return sellerId;
	}

	public void setSeller(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public String getCommName() {
		return commName;
	}

	public void setCommName(String commName) {
		this.commName = commName;
	}

	public String getCommDesc() {
		return commDesc;
	}

	public void setCommDesc(String commDesc) {
		this.commDesc = commDesc;
	}

	public Integer getCommState() {
		return commState;
	}

	public void setCommState(Integer commState) {
		this.commState = commState;
	}

	public Timestamp getListDatetime() {
		return listDatetime;
	}

	public void setListDatetime(Timestamp listDatetime) {
		this.listDatetime = listDatetime;
	}

	public byte[] getCommImg() {
		return commImg;
	}

	public void setCommImg(byte[] commImg) {
		this.commImg = commImg;
	}

	public Integer getCommCatId() {
		return commCatId;
	}

	public void setCommCatId(Integer commCatId) {
		this.commCatId = commCatId;
	}

	public Integer getCommStock() {
		return commStock;
	}

	public void setCommStock(Integer commStock) {
		this.commStock = commStock;
	}

	public BigDecimal getCommPrice() {
		return commPrice;
	}

	public void setCommPrice(BigDecimal commPrice) {
		this.commPrice = commPrice;
	}

	public BigDecimal getCommOnsalePrice() {
		return commOnsalePrice;
	}

	public void setCommOnsalePrice(BigDecimal commOnsalePrice) {
		this.commOnsalePrice = commOnsalePrice;
	}

	public Integer getCommViewCount() {
		return commViewCount;
	}

	public void setCommViewCount(Integer commViewCount) {
		this.commViewCount = commViewCount;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null) {
			return false;
		}
		if(getClass() != obj.getClass()) {
			return false;
		}
		Comm other = (Comm) obj;
		return Objects.equals(commId, other.commId);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(commId);
	}
	
	@Override
	public String toString() {
	    return "Comm{" +
	           "commId=" + commId +
	           ", seller=" + sellerId+
	           ", commName='" + commName + '\'' +
	           ", commDesc='" + commDesc + '\'' +
	           ", commState=" + commState +
	           ", listDatetime=" + listDatetime +
	           ", commImg=" + (commImg != null ? "Length: " + commImg.length : "null") +
	           ", commCatId=" + commCatId +
	           ", commStock=" + commStock +
	           ", commPrice=" + commPrice +
	           ", commOnsalePrice=" + commOnsalePrice +
	           ", commViewCount=" + commViewCount +
	           '}';
	}
}