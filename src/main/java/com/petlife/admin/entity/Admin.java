package com.petlife.admin.entity;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "admin")
public class Admin {

	@Id
	@Column(name = "admin_id", updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Integer adminId;

	@Column(name = "admin_acct", unique = true, updatable = false)
	private String adminAcct;

	@Column(name = "admin_pwd")
	private String adminPwd;

	@Column(name = "admin_nickname")
	@Expose
	private String adminNickname;

	@Column(name = "admin_pwd_err_times", columnDefinition = "tinyint", insertable = false)
	private Integer adminPwdErrTimes;

	@ManyToOne
	@JoinColumn(name = "acct_state_id", referencedColumnName = "acct_state_id", insertable = false)
	private AcctState acctState;

	@ManyToOne
	@JoinColumn(name = "acct_type_id", referencedColumnName = "acct_type_id", insertable = false)
	private AcctType acctType;

	@Column(name = "admin_create_time", updatable = false, insertable = false)
	private Timestamp adminCreateTime;

	public Admin() {
	}

	public Admin(Integer adminId, String adminAcct, String adminPwd, String adminNickname, Integer adminPwdErrTimes,
			AcctState acctState, AcctType acctType, Timestamp adminCreateTime) {
		super();
		this.adminId = adminId;
		this.adminAcct = adminAcct;
		this.adminPwd = adminPwd;
		this.adminNickname = adminNickname;
		this.adminPwdErrTimes = adminPwdErrTimes;
		this.acctState = acctState;
		this.acctType = acctType;
		this.adminCreateTime = adminCreateTime;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getAdminAcct() {
		return adminAcct;
	}

	public void setAdminAcct(String adminAcct) {
		this.adminAcct = adminAcct;
	}

	public String getAdminPwd() {
		return adminPwd;
	}

	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}

	public String getAdminNickname() {
		return adminNickname;
	}

	public void setAdminNickname(String adminNickname) {
		this.adminNickname = adminNickname;
	}

	public Integer getAdminPwdErrTimes() {
		return adminPwdErrTimes;
	}

	public void setAdminPwdErrTimes(Integer adminPwdErrTimes) {
		this.adminPwdErrTimes = adminPwdErrTimes;
	}

	public AcctState getAcctState() {
		return acctState;
	}

	public void setAcctState(AcctState acctState) {
		this.acctState = acctState;
	}

	public AcctType getAcctType() {
		return acctType;
	}

	public void setAcctType(AcctType acctType) {
		this.acctType = acctType;
	}

	public Timestamp getAdminCreateTime() {
		return adminCreateTime;
	}

	public void setAdminCreateTime(Timestamp adminCreateTime) {
		this.adminCreateTime = adminCreateTime;
	}

	@Override
	public int hashCode() {
		return Objects.hash(adminId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Admin other = (Admin) obj;
		return Objects.equals(adminId, other.adminId);
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminAcct=" + adminAcct + ", adminPwd=" + adminPwd + ", adminNickname="
				+ adminNickname + ", adminPwdErrTimes=" + adminPwdErrTimes + ", acctState=" + acctState.getAcctStateType() + ", acctType="
				+ acctType.getAcctType() + ", adminCreateTime=" + adminCreateTime + "]";
	}
}
