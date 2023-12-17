package com.petlife.shelter.entity;

import java.sql.Date;
import java.time.LocalTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "shelter_booking")
public class ShelterBooking {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shelter_booking_id", updatable = false)
	@Expose
	private Integer id;
	
//	@ManyToOne
//	@JoinColumn(name = "shelter_id", referencedColumnName = "shelter_id",insertable = false)
//	private Shelter shelterId
	@Column(name = "shelter_id")
	@Expose
	private Integer shelterId;
	
	@Column(name = "shelter_booking_date")
	@Expose
	private Date shelterBookingDate;
	
	@Column(name = "shelter_booking_time",columnDefinition = "TIME")
	@Expose
	private LocalTime   shelterBookingTime  ;


	@Column(name = "shelter_booking_num",columnDefinition = "TINYINT")
	@Expose
	private Integer shelterBookingNum;
	
	@Column(name = "shelter_booking_max",columnDefinition = "TINYINT")
	@Expose
	private Integer shelterBookingMax;
	//===============預約人
	@OneToMany(mappedBy = "shelterBooking", cascade = CascadeType.ALL)
	private Set<Reservation> reservations;
	//===============預約人
	public ShelterBooking(Integer id, Integer shelterId, Date shelterBookingDate, LocalTime   shelterBookingTime,
			Integer shelterBookingNum, Integer shelterBookingMax) {
		super();
		this.id = id;
		this.shelterId = shelterId;
		this.shelterBookingDate = shelterBookingDate;
		this.shelterBookingTime = shelterBookingTime;
		this.shelterBookingNum = shelterBookingNum;
		this.shelterBookingMax = shelterBookingMax;
	}
	

	public ShelterBooking() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getShelterId() {
		return shelterId;
	}

	public void setShelterId(Integer shelterId) {
		this.shelterId = shelterId;
	}

	public Date getShelterBookingDate() {
		return shelterBookingDate;
	}

	public void setShelterBookingDate(Date shelterBookingDate) {
		this.shelterBookingDate = shelterBookingDate;
	}

	public LocalTime   getShelterBookingTime() {
	
		return shelterBookingTime;
	}
	
	 
	 
	public void setShelterBookingTime(LocalTime   shelterBookingTime) {
	
		this.shelterBookingTime = shelterBookingTime ;
	}

	public Integer getShelterBookingNum() {
		return shelterBookingNum;
	}

	public void setShelterBookingNum(Integer shelterBookingNum) {
		
		this.shelterBookingNum= shelterBookingNum;
		
	}

	public Integer getShelterBookingMax() {
		return shelterBookingMax;
	}

	public void setShelterBookingMax(Integer shelterBookingMax) {
		this.shelterBookingMax = shelterBookingMax;
	}
	


}
