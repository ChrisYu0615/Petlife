package com.petlife.admin.entity;

import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.petlife.user.entity.User;
import com.petlife.seller.entity.Seller;
import com.petlife.shelter.entity.Shelter;

@Entity
@Table(name = "acct_type")
public class AcctType {
	@Id
	@Column(name = "acct_type_id", updatable = false)
	@Expose
	private Integer acctTypeId;

	@Column(name = "acct_type")
	@Expose
	private String acctType;

	@OneToMany(mappedBy = "acctType", cascade = CascadeType.ALL)
	@OrderBy("user_id asc")
	private Set<User> users;

	@OneToMany(mappedBy = "acctType", cascade = CascadeType.ALL)
	@OrderBy("seller_id asc")
	private Set<Admin> admins;
	
	@OneToMany(mappedBy = "acctType", cascade = CascadeType.ALL)
	@OrderBy("admin_id asc")
	private Set<Seller> sellers;
	
	@OneToMany(mappedBy = "acctType", cascade = CascadeType.ALL)
	@OrderBy("shelterId asc")
	private Set<Shelter> shelters;

	public AcctType() {
	}



	public AcctType(Integer acctTypeId, String acctType, Set<User> users, Set<Admin> admins, Set<Shelter> shelters) {
		super();
		this.acctTypeId = acctTypeId;
		this.acctType = acctType;
		this.users = users;
		this.admins = admins;
		this.shelters = shelters;
	}



	public Integer getAcctTypeId() {
		return acctTypeId;
	}

	public void setAcctTypeId(Integer acctTypeId) {
		this.acctTypeId = acctTypeId;
	}

	public String getAcctType() {
		return acctType;
	}

	public void setAcctType(String acctType) {
		this.acctType = acctType;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Admin> getAdmins() {
		return admins;
	}

	public void setAdmins(Set<Admin> admins) {
		this.admins = admins;
	}

	
	
	public Set<Shelter> getShelters() {
		return shelters;
	}



	public void setShelters(Set<Shelter> shelters) {
		this.shelters = shelters;
	}



	@Override
	public int hashCode() {
		return Objects.hash(acctTypeId);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AcctType other = (AcctType) obj;
		return Objects.equals(acctTypeId, other.acctTypeId);
	}



	@Override
	public String toString() {
		return "AcctType [acctTypeId=" + acctTypeId + ", acctType=" + acctType + ", users=" + users + ", admins="
				+ admins + ", shelters=" + shelters + "]";
	}


	

}
