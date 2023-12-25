package com.petlife.shelter.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.petlife.admin.entity.AcctState;
import com.petlife.admin.entity.AcctType;
import com.petlife.pet.entity.Pet;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "shelter")
public class Shelter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shelter_id", updatable = false)
	@Expose
	private Integer shelterId;
	
	@Column(name = "shelter_acct", unique = true)
	@Expose
	private String shelterAcct;
	
	@Column(name = "shelter_pwd")
	@Expose
	private String shelterPwd;
	
	@Column(name = "shelter_name")
	@Expose
	private String shelterName;

	public Set<Pet> getPets() {
		return pets;
	}

	public void setPets(Set<Pet> pets) {
		this.pets = pets;
	}

	@Column(name = "shelter_create_time", insertable = false)
	@Expose
	private Timestamp shelterCreateTime;
	
	@Column(name = "shelter_pwd_err_times",columnDefinition="tinyint", insertable = false)
	@Expose
	private Integer shelterPwdErrTimes;
	
	@Column(name = "shelter_phone_num")
	@Expose
	private String shelterPhoneNum;
	
	@Column(name = "shelter_address")
	@Expose
	private String shelterAddress;
	
	@Column(name = "shelter_photo",columnDefinition="longblob")
	@Expose
	private byte[] shelterPhoto;
	
	@Column(name = "shelter_introduction",columnDefinition="longtext")
	@Expose
	private String shelterIntroduction;
	
	@ManyToOne
	@JoinColumn(name = "acct_type_id", referencedColumnName = "acct_type_id", insertable = false)
	@Expose
	private AcctType acctType;
	
	@Column(name = "shelter_lng")
	@Expose
	private Double shelterLng;
	
	@Column(name = "shelter_lat")
	@Expose
	private Double shelterLat;
	
	@ManyToOne
	@JoinColumn(name = "acct_state_id", referencedColumnName = "acct_state_id", insertable = false)
	@Expose
	private AcctState acctState;
	
	@OneToMany(mappedBy = "shelter", cascade = CascadeType.ALL)
	private Set<Pet> pets;
	
	@OneToMany(mappedBy = "shelter", cascade = CascadeType.ALL)
	private Set<Reservation> reservations;
	
	
	
	public Shelter(String shelterAcct, String shelterPwd, String shelterName, String shelterPhoneNum, 
			String shelterAddress, Double shelterLng, Double shelterLat) {
		super();
		this.shelterAcct = shelterAcct;
		this.shelterPwd = shelterPwd;
		this.shelterName = shelterName;
		this.shelterPhoneNum = shelterPhoneNum;
		this.shelterAddress = shelterAddress;
		this.shelterLng = shelterLng;
		this.shelterLat = shelterLat;
	}
	
	public Shelter(String shelterAcct, String shelterPwd, String shelterName, String shelterPhoneNum,
			String shelterAddress, String shelterIntroduction, Double shelterLng, Double shelterLat) {
		super();
		this.shelterAcct = shelterAcct;
		this.shelterPwd = shelterPwd;
		this.shelterName = shelterName;
		this.shelterPhoneNum = shelterPhoneNum;
		this.shelterAddress = shelterAddress;
		this.shelterIntroduction = shelterIntroduction;
		this.shelterLng = shelterLng;
		this.shelterLat = shelterLat;
	}

	public Shelter(Integer shelterId, String shelterAcct, String shelterPwd, String shelterName,
			Timestamp shelterCreateTime, Integer shelterPwdErrTimes, String shelterPhoneNum, String shelterAddress,
			byte[] shelterPhoto, String shelterIntroduction, AcctType acctType, Double shelterLng, Double shelterLat,
			AcctState acctState) {
		super();
		this.shelterId = shelterId;
		this.shelterAcct = shelterAcct;
		this.shelterPwd = shelterPwd;
		this.shelterName = shelterName;
		this.shelterCreateTime = shelterCreateTime;
		this.shelterPwdErrTimes = shelterPwdErrTimes;
		this.shelterPhoneNum = shelterPhoneNum;
		this.shelterAddress = shelterAddress;
		this.shelterPhoto = shelterPhoto;
		this.shelterIntroduction = shelterIntroduction;
		this.acctType = acctType;
		this.shelterLng = shelterLng;
		this.shelterLat = shelterLat;
		this.acctState = acctState;
	}



	public Shelter() {
		
	}



	public Integer getShelterId() {
		return shelterId;
	}



	public void setShelterId(Integer shelterId) {
		this.shelterId = shelterId;
	}



	public String getShelterAcct() {
		return shelterAcct;
	}



	public void setShelterAcct(String shelterAcct) {
		this.shelterAcct = shelterAcct;
	}



	public String getShelterPwd() {
		return shelterPwd;
	}



	public void setShelterPwd(String shelterPwd) {
		this.shelterPwd = shelterPwd;
	}



	public String getShelterName() {
		return shelterName;
	}



	public void setShelterName(String shelterName) {
		this.shelterName = shelterName;
	}



	public Timestamp getShelterCreateTime() {
		return shelterCreateTime;
	}



	public void setShelterCreateTime(Timestamp shelterCreateTime) {
		this.shelterCreateTime = shelterCreateTime;
	}



	public Integer getShelterPwdErrTimes() {
		return shelterPwdErrTimes;
	}



	public void setShelterPwdErrTimes(Integer shelterPwdErrTimes) {
		this.shelterPwdErrTimes = shelterPwdErrTimes;
	}



	public String getShelterPhoneNum() {
		return shelterPhoneNum;
	}



	public void setShelterPhoneNum(String shelterPhoneNum) {
		this.shelterPhoneNum = shelterPhoneNum;
	}



	public String getShelterAddress() {
		return shelterAddress;
	}



	public void setShelterAddress(String shelterAddress) {
		this.shelterAddress = shelterAddress;
	}



	public byte[] getShelterPhoto() {
		return shelterPhoto;
	}



	public void setShelterPhoto(byte[] shelterPhoto) {
		this.shelterPhoto = shelterPhoto;
	}



	public String getShelterIntroduction() {
		return shelterIntroduction;
	}



	public void setShelterIntroduction(String shelterIntroduction) {
		this.shelterIntroduction = shelterIntroduction;
	}



	public AcctType getAcctType() {
		return acctType;
	}



	public void setAcctType(AcctType acctType) {
		this.acctType = acctType;
	}



	public Double getShelterLng() {
		return shelterLng;
	}



	public void setShelterLng(Double shelterLng) {
		this.shelterLng = shelterLng;
	}



	public Double getShelterLat() {
		return shelterLat;
	}



	public void setShelterLat(Double shelterLat) {
		this.shelterLat = shelterLat;
	}



	public AcctState getAcctState() {
		return acctState;
	}



	public void setAcctState(AcctState acctState) {
		this.acctState = acctState;
	}



	// 搜尋時不讓收容所資料重複，要加上hashcode跟equals
	@Override
	public int hashCode() {
		return Objects.hash(shelterId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Shelter other = (Shelter) obj;
		return Objects.equals(shelterId, other.shelterId);
	}

	

}
