package com.petlife.shelter.entity;

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
import com.petlife.pet.entity.Pet;
import com.petlife.shelter.entity.Shelter;
import com.petlife.user.entity.User;

import java.sql.Timestamp;

@Entity
@Table(name = "reservation")
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "res_id", updatable = false)
	private Integer resId;
	
	@ManyToOne
	@JoinColumn(name = "shelter_id", referencedColumnName = "shelter_id")
	private Shelter shelter;
	

	@Column(name="shelter_booking_id", updatable = false)
	private Integer shelterBookingId;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
	@Expose
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "pet_id", referencedColumnName = "pet_id", insertable = false, updatable = false)
	@Expose
	private Pet pet;
	
	@ManyToOne
	@JoinColumn(name = "shelter_booking_id", referencedColumnName = "shelter_booking_id", insertable = false, updatable = false)
	@Expose
	private ShelterBooking shelterBooking;
	

	@Column(name = "user_id", updatable = false)
	private Integer userId;
	

	@Column(name = "pet_id", updatable = false)
	private Integer petId;
	
	@ManyToOne
	@JoinColumn(name = "res_type_id", referencedColumnName = "res_type_id", updatable = false, insertable = false)
	private ResType resType;
	
	@Column(name = "res_type_id", insertable = false, updatable = true)
	private Integer resTypeId;
	
	@Column(name = "res_keyin_date", insertable = false, updatable = false)
	private Timestamp resKeyinDate;
	
	

	public Reservation(Shelter shelter, Integer shelterBookingId, Integer userId, Integer petId) {
		super();
		this.shelter = shelter;
		this.shelterBookingId = shelterBookingId;
		this.userId = userId;
		this.petId = petId;
	}
	
	

	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Reservation(Integer resId, Shelter shelter, Integer shelterBookingId, Integer userId, Integer petId,
			ResType resType, Timestamp resKeyinDate) {
		super();
		this.resId = resId;
		this.shelter = shelter;
		this.shelterBookingId = shelterBookingId;
		this.userId = userId;
		this.petId = petId;
		this.resType = resType;
		this.resKeyinDate = resKeyinDate;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public Pet getPet() {
		return pet;
	}



	public void setPet(Pet pet) {
		this.pet = pet;
	}



	public ShelterBooking getShelterBooking() {
		return shelterBooking;
	}



	public void setShelterBooking(ShelterBooking shelterBooking) {
		this.shelterBooking = shelterBooking;
	}



	public Integer getResTypeId() {
		return resTypeId;
	}



	public void setResTypeId(Integer resTypeId) {
		this.resTypeId = resTypeId;
	}



	public Integer getResId() {
		return resId;
	}

	public void setResId(Integer resId) {
		this.resId = resId;
	}

	public Shelter getShelter() {
		return shelter;
	}

	public void setShelter(Shelter shelter) {
		this.shelter = shelter;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getPetId() {
		return petId;
	}

	public void setPetId(Integer petId) {
		this.petId = petId;
	}

	public ResType getResType() {
		return resType;
	}

	public void setResType(ResType resType) {
		this.resType = resType;
	}

	public Timestamp getResKeyinDate() {
		return resKeyinDate;
	}

	public void setResKeyinDate(Timestamp resKeyinDate) {
		this.resKeyinDate = resKeyinDate;
	}
	
	public Integer getShelterBookingId() {
		return shelterBookingId;
	}

	public void setShelterBookingId(Integer shelterBookingId) {
		this.shelterBookingId = shelterBookingId;
	}

	@Override
	public String toString() {
		return "Reservation [resId=" + resId + ", shelter=" + shelter + ", shelterBookingId=" + shelterBookingId
				+ ", userId=" + userId + ", petId=" + petId + ", resType=" + resType + ", resKeyinDate=" + resKeyinDate
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(resId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservation other = (Reservation) obj;
		return Objects.equals(resId, other.resId);
	}
	
	
	
}
