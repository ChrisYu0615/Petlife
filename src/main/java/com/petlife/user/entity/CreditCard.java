package com.petlife.user.entity;


import java.sql.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "credit_card")
public class CreditCard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "credit_card_id", updatable = false)
	private Integer creditCardId;

	@ManyToOne 
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;

	@Column(name = "credit_card_number")
	private String creditCardNumber;

	@Column(name = "credit_card_holder_name")
	private String creditCardHolderName;

	@Column(name = "credit_card_expiration_date")
	private Date creditCardExpirationDate;


	public CreditCard() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CreditCard(Integer creditCardId, User user, String creditCardNumber, String creditCardHolderName,
			Date creditCardExpirationDate) {
		super();
		this.creditCardId = creditCardId;
		this.user = user;
		this.creditCardNumber = creditCardNumber;
		this.creditCardHolderName = creditCardHolderName;
		this.creditCardExpirationDate = creditCardExpirationDate;
	}


	public Integer getCreditCardId() {
		return creditCardId;
	}


	public void setCreditCardId(Integer creditCardId) {
		this.creditCardId = creditCardId;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getCreditCardNumber() {
		return creditCardNumber;
	}


	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}


	public String getCreditCardHolderName() {
		return creditCardHolderName;
	}


	public void setCreditCardHolderName(String creditCardHolderName) {
		this.creditCardHolderName = creditCardHolderName;
	}


	public Date getCreditCardExpirationDate() {
		return creditCardExpirationDate;
	}


	public void setCreditCardExpirationDate(Date creditCardExpirationDate) {
		this.creditCardExpirationDate = creditCardExpirationDate;
	}

	

	@Override
	public int hashCode() {
		return Objects.hash(creditCardId);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreditCard other = (CreditCard) obj;
		return Objects.equals(creditCardId, other.creditCardId);
	}


	@Override
	public String toString() {
		return "CreditCard [creditCardId=" + creditCardId + ", user=" + user + ", creditCardNumber=" + creditCardNumber
				+ ", creditCardHolderName=" + creditCardHolderName + ", creditCardExpirationDate="
				+ creditCardExpirationDate + "]";
	}



}
