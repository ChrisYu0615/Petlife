package com.petlife.admin.entity;

import java.sql.Timestamp;

public class Member {
	private Integer memId;
	private String memAcct;
	private String memAcctType;
	private String memName;
	private String memPhoneNum;
	private String memAcctState;
	private Timestamp memCreateTime;

	public Member() {
	}

	public Member(Integer memId, String memAcct, String memAcctType, String memName, String memPhoneNum,
			String memAcctState, Timestamp memCreateTime) {
		this.memId = memId;
		this.memAcct = memAcct;
		this.memAcctType = memAcctType;
		this.memName = memName;
		this.memPhoneNum = memPhoneNum;
		this.memAcctState = memAcctState;
		this.memCreateTime = memCreateTime;
	}

	public Integer getMemId() {
		return memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}

	public String getMemAcct() {
		return memAcct;
	}

	public void setMemAcct(String memAcct) {
		this.memAcct = memAcct;
	}

	public String getMemAcctType() {
		return memAcctType;
	}

	public void setMemAcctType(String memAcctType) {
		this.memAcctType = memAcctType;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemPhoneNum() {
		return memPhoneNum;
	}

	public void setMemPhoneNum(String memPhoneNum) {
		this.memPhoneNum = memPhoneNum;
	}

	public String getMemAcctState() {
		return memAcctState;
	}

	public void setMemAcctState(String memAcctState) {
		this.memAcctState = memAcctState;
	}

	public Timestamp getMemCreateTime() {
		return memCreateTime;
	}

	public void setMemCreateTime(Timestamp memCreateTime) {
		this.memCreateTime = memCreateTime;
	}

}
