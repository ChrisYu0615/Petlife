package com.petlife.shelter.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "res_type")
public class ResType {
	
	@Id
	@Column(name = "res_type_id", updatable = false)
	private Integer resTypeId;
	
	@Column(name = "res_con")
	private String resCon;
	
	@OneToMany(mappedBy = "resType",  cascade = CascadeType.ALL)
	@OrderBy("resId asc")
	private Set<Reservation> reservations;

	public Integer getResTypeId() {
		return resTypeId;
	}

	public void setResTypeId(Integer resTypeId) {
		this.resTypeId = resTypeId;
	}

	public String getResCon() {
		return resCon;
	}

	public void setResCon(String resCon) {
		this.resCon = resCon;
	}

	public Set<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}

	@Override
	public String toString() {
		return "ResType [resTypeId=" + resTypeId + ", resCon=" + resCon + ", reservations=" + reservations + "]";
	}
	
	
		
	
}
