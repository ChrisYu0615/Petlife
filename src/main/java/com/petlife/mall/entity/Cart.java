package com.petlife.mall.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.petlife.user.entity.User;

@Entity
@Table(name = "cart")
public class Cart {
	
	@Id
	@Column(name = "cart_id", updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Integer cartId;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "comm_id")
	private Comm comm;
	
	// SQL中是user_id & comm_id 的欄位這邊都用object User & Comm 表示
	
	@Column(name = "purchasing_amount", updatable = true)
	@Expose
	private Integer purchasingAmount;

	public Cart() {
	}

	public Cart(Integer cartId, User user, Comm comm, Integer purchasingAmout) {
		super();
		this.cartId = cartId;
		this.user = user;
		this.comm = comm;
		this.purchasingAmount = purchasingAmout;
	}
	
	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Comm getComm() {
		return comm;
	}

	public void setComm(Comm comm) {
		this.comm = comm;
	}

	public Integer getPurchasingAmount() {
		return purchasingAmount;
	}

	public void setPurchasingAmount(Integer purchasingAmount) {
		this.purchasingAmount = purchasingAmount;
	}	

	@Override
	public int hashCode() {
		return Objects.hash(cartId, comm, purchasingAmount, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		return Objects.equals(cartId, other.cartId) && Objects.equals(comm, other.comm)
				&& Objects.equals(purchasingAmount, other.purchasingAmount) && Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", user=" + user + ", comm=" + comm + ", purchasingAmout=" + purchasingAmount
				+ "]";
	}
}
