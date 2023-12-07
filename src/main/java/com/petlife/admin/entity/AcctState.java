package com.petlife.admin.entity;

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

@Entity
@Table(name = "acct_state")
public class AcctState {
	@Id
	@Column(name = "acct_state_id", updatable = false)
	@Expose
	private Integer acctStateId;

	@Column(name = "acct_state_type")
	@Expose
	private String acctStateType;

	@OneToMany(mappedBy = "acctState", cascade = CascadeType.ALL)
	@OrderBy("user_id asc")
	private Set<User> users;

	@OneToMany(mappedBy = "acctState", cascade = CascadeType.ALL)
	@OrderBy("admin_id asc")
	private Set<Admin> admins;

	public AcctState() {
	}

	public AcctState(Integer acctStateId, String acctStateType) {
		super();
		this.acctStateId = acctStateId;
		this.acctStateType = acctStateType;
	}

	public AcctState(Integer acctStateId, String acctStateType, Set<User> users, Set<Admin> admins) {
		super();
		this.acctStateId = acctStateId;
		this.acctStateType = acctStateType;
		this.users = users;
		this.admins = admins;
	}

	public Integer getAcctStateId() {
		return acctStateId;
	}

	public void setAcctStateId(Integer acctStateId) {
		this.acctStateId = acctStateId;
	}

	public String getAcctStateType() {
		return acctStateType;
	}

	public void setAcctStateType(String acctStateType) {
		this.acctStateType = acctStateType;
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
		return "AcctState [acctStateId=" + acctStateId + ", acctStateType=" + acctStateType + ", users=" + users
				+ ", admins=" + admins + "]";
	}

}
