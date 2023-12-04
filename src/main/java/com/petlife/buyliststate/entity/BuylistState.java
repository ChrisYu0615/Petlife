package com.petlife.buyliststate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "buylist_state")
public class BuylistState {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "buylist_state_id", updatable = false)
	private Integer buylistStateId;
	
	@Column(name = "buylist_state_name")
	private String buylistStateName;

//	@OneToMany(mappedBy = )

	public Integer getBuylistStateId() {
		return buylistStateId;
	}



	public void setBuylistStateId(Integer buylistStateId) {
		this.buylistStateId = buylistStateId;
	}



	public String getBuylistStateName() {
		return buylistStateName;
	}



	public void setBuylistStateName(String buylistStateName) {
		this.buylistStateName = buylistStateName;
	}


	public BuylistState() {
		super();
	}
	public BuylistState(Integer buylistStateId, String buylistStateName) {
		super();
		this.buylistStateId = buylistStateId;
		this.buylistStateName = buylistStateName;
	}



	@Override
	public String toString() {
		return "Buyliststate [buylist_state_id =" + buylistStateId + ", buylist_state_name=" +buylistStateName+ "]";
	}

}
