package com.petlife.accttype.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.petlife.admin.entity.Admin;
import com.petlife.user.entity.User;

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
	@OrderBy("admin_id asc")
	private Set<Admin> admins;

	public AcctType() {
	}

	public AcctType(Integer acctTypeId, String acctType, Set<User> users, Set<Admin> admins) {
		super();
		this.acctTypeId = acctTypeId;
		this.acctType = acctType;
		this.users = users;
		this.admins = admins;
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

	@Override
	public String toString() {
		return "AcctType [acctTypeId=" + acctTypeId + ", acctType=" + acctType + ", users=" + users + ", admins="
				+ admins + "]";
	}
}
