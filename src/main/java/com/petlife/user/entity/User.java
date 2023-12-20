package com.petlife.user.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
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
import com.petlife.admin.entity.AcctState;
import com.petlife.admin.entity.AcctType;
import com.petlife.shelter.entity.Reservation;

@Entity
@Table(name = "user")
public class User {
	@Id
	@Column(name = "user_id", updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Integer userId;

	@Column(name = "user_acct", unique = true, updatable = false)
	@Expose
	private String userAcct;

	@Column(name = "user_pwd")
	@Expose
	private String userPwd;

	@Column(name = "user_name")
	@Expose
	private String userName;

	@Column(name = "user_nickname")
	@Expose
	private String userNickName;

	@Column(name = "user_pwd_err_times", columnDefinition = "tinyint", insertable = false)
	@Expose
	private Integer userPwdErrTimes;

	@Column(name = "birthday")
	@Expose
	private Date birthday;

	@Column(name = "address")
	@Expose
	private String address;

	@Column(name = "phone_num")
	@Expose
	private String phoneNum;

	@Column(name = "gender")
	@Expose
	private Boolean gender;

	@Column(name = "headshot", columnDefinition = "longblob")
	@Expose
	private byte[] headshot;
	
	@Column(name = "user_report_count")
	@Expose
	private Integer userReportCount;

	// 多方(ManyToOne預設)：fetch預設FetchType.EAGER -> 取得該物件時，也會立即讓關聯的物件也跟著取得
	@ManyToOne
	@JoinColumn(name = "acct_state_id", referencedColumnName = "acct_state_id", insertable = false)
	@Expose
	private AcctState acctState;

	@ManyToOne
	@JoinColumn(name = "acct_type_id", referencedColumnName = "acct_type_id", insertable = false)
	@Expose
	private AcctType acctType;

	@Column(name = "user_create_time", updatable = false, insertable = false)
	@Expose
	private Timestamp userCreateTime;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Reservation> reservations;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@OrderBy("credit_card_id asc")
	private Set<CreditCard> creditCards;
	
	
	public User() {
	}

	public User(String userAcct, String userPwd, String userName, String userNickName, Date birthday, String address,
			String phoneNum, Boolean gender) {
		super();
		this.userAcct = userAcct;
		this.userPwd = userPwd;
		this.userName = userName;
		this.userNickName = userNickName;
		this.birthday = birthday;
		this.address = address;
		this.phoneNum = phoneNum;
		this.gender = gender;
	}

	public User(Integer userId, String userAcct, String userPwd, String userName, String userNickName,
			Integer userPwdErrTimes, Date birthday, String address, String phoneNum, Boolean gender, byte[] headshot,
			Integer userReportCount, AcctState acctState, AcctType acctType, Timestamp userCreateTime,
			Set<Reservation> reservations, Set<CreditCard> creditCards) {
		super();
		this.userId = userId;
		this.userAcct = userAcct;
		this.userPwd = userPwd;
		this.userName = userName;
		this.userNickName = userNickName;
		this.userPwdErrTimes = userPwdErrTimes;
		this.birthday = birthday;
		this.address = address;
		this.phoneNum = phoneNum;
		this.gender = gender;
		this.headshot = headshot;
		this.userReportCount = userReportCount;
		this.acctState = acctState;
		this.acctType = acctType;
		this.userCreateTime = userCreateTime;
		this.reservations = reservations;
		this.creditCards = creditCards;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserAcct() {
		return userAcct;
	}

	public void setUserAcct(String userAcct) {
		this.userAcct = userAcct;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNickName() {
		return userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}

	public Integer getUserPwdErrTimes() {
		return userPwdErrTimes;
	}

	public void setUserPwdErrTimes(Integer userPwdErrTimes) {
		this.userPwdErrTimes = userPwdErrTimes;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public byte[] getHeadshot() {
		return headshot;
	}

	public void setHeadshot(byte[] headshot) {
		this.headshot = headshot;
	}
	
	public Integer getUserReportCount() {
		return userReportCount;
	}

	public void setUserReportCount(Integer userReportCount) {
		this.userReportCount = userReportCount;
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

	public Timestamp getUserCreateTime() {
		return userCreateTime;
	}

	public void setUserCreateTime(Timestamp userCreateTime) {
		this.userCreateTime = userCreateTime;
	}
	

	public Set<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}
	
	public Set<CreditCard> getCreditCards() {
		return creditCards;
	}

	public void setCreditCards(Set<CreditCard> creditCards) {
		this.creditCards = creditCards;
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(userId, other.userId);
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userAcct=" + userAcct + ", userPwd=" + userPwd + ", userName=" + userName
				+ ", userNickName=" + userNickName + ", userPwdErrTimes=" + userPwdErrTimes + ", birthday=" + birthday
				+ ", address=" + address + ", phoneNum=" + phoneNum + ", gender=" + gender + ", headshot="
				+ Arrays.toString(headshot) + ", userReportCount=" + userReportCount + ", acctState=" + acctState.getAcctStateType()
				+ ", acctType=" + acctType.getAcctType() + ", userCreateTime=" + userCreateTime + ", reservations=" + reservations
				+ ", creditCards=" + creditCards + "]";
	}

}
