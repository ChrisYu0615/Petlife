package com.petlife.seller.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
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
import com.petlife.admin.entity.AcctState;
import com.petlife.admin.entity.AcctType;

@Entity
@Table(name = "seller")
public class Seller {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seller_id")
	@Expose
	private int sellerId;

	@Column(name = "seller_acct")
	@Expose
	private String sellerAcct;

	@Column(name = "seller_pwd")
	@Expose
	private String sellerPwd;

	@Column(name = "seller_name")
	@Expose
	private String sellerName;

	@Column(name = "seller_nickname")
	@Expose
	private String sellerNickname;

	@Column(name = "id", columnDefinition = "char")
	@Expose
	private String id;

	@Column(name = "birthday")
	@Expose
	private Date birthday;

	@Column(name = "gender", updatable = false)
	@Expose
	private Boolean gender;

	@Column(name = "seller_address")
	@Expose
	private String sellerAddress;

	@Column(name = "phone_num", columnDefinition = "char")
	@Expose
	private String phoneNum;

	@Column(name = "head_shot", columnDefinition = "LONGBLOB")
	@Expose
	private byte[] headShot;

	@ManyToOne
	@JoinColumn(name = "acct_state_id", referencedColumnName = "acct_state_id", insertable = false)
	@Expose
	private AcctState acctState;

	@Column(name = "accumula_start")
	@Expose
	private Double accumulaStart;

	@Column(name = "accumula_people")
	@Expose
	private Integer accumulaPeople;

	@ManyToOne
	@JoinColumn(name = "acct_type_id", updatable = false, columnDefinition = "TINYINT", insertable = false)
	@Expose
	private AcctType acctType;

	@Column(name = "swift_code")
	@Expose
	private String swiftCode;

	@Column(name = "bank_acct")
	@Expose
	private String bankAcct;

	@Column(name = "id_front", columnDefinition = "LONGBLOB")
	@Expose
	private byte[] idFront;

	@Column(name = "id_back", columnDefinition = "LONGBLOB")
	@Expose
	private byte[] idBack;

	@Column(name = "bank_acct_img", columnDefinition = "LONGBLOB")
	@Expose
	private byte[] bankAcctImg;

	@Column(name = "seller_create_time", insertable = false)
	@Expose
	private Timestamp sellerCreateTime;

	@Column(name = "seller_pwd_err_times", columnDefinition = "TINYINT", insertable = false)
	@Expose
	private Integer sellerPwdErrTimes;

	public Seller() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Seller(String sellerAcct, String sellerPwd, String sellerName, String id, String sellerNickname,
			Date birthday, Boolean gender, String sellerAddress, String phoneNum, String swiftCode, String bankAcct,
			byte[] idFront, byte[] idBack, byte[] bankAcctImg) {
		this.sellerAcct = sellerAcct;
		this.sellerPwd = sellerPwd;
		this.sellerName = sellerName;
		this.id = id;
		this.sellerNickname = sellerNickname;
		this.birthday = birthday;
		this.gender = gender;
		this.sellerAddress = sellerAddress;
		this.phoneNum = phoneNum;
		this.swiftCode = swiftCode;
		this.bankAcct = bankAcct;
		this.idFront = idFront;
		this.idBack = idBack;
		this.bankAcctImg = bankAcctImg;
	}

	public Seller(int sellerId, String sellerAcct, String sellerPwd, String sellerName, String sellerNickname,
			String id, Date birthday, Boolean gender, String sellerAddress, String phoneNum, byte[] headShot,
			AcctState acctState, double accumulaStart, int accumulaPeople, AcctType acctType, String swiftCode,
			String bankAcct, byte[] idFront, byte[] idBack, byte[] bankAcctImg, Timestamp sellerCreateTime,
			Integer sellerPwdErrTimes) {
		super();
		this.sellerId = sellerId;
		this.sellerAcct = sellerAcct;
		this.sellerPwd = sellerPwd;
		this.sellerName = sellerName;
		this.sellerNickname = sellerNickname;
		this.id = id;
		this.birthday = birthday;
		this.gender = gender;
		this.sellerAddress = sellerAddress;
		this.phoneNum = phoneNum;
		this.headShot = headShot;
		this.acctState = acctState;
		this.accumulaStart = accumulaStart;
		this.accumulaPeople = accumulaPeople;
		this.acctType = acctType;
		this.swiftCode = swiftCode;
		this.bankAcct = bankAcct;
		this.idFront = idFront;
		this.idBack = idBack;
		this.bankAcctImg = bankAcctImg;
		this.sellerCreateTime = sellerCreateTime;
		this.sellerPwdErrTimes = sellerPwdErrTimes;
	}

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	public String getSellerAcct() {
		return sellerAcct;
	}

	public void setSellerAcct(String sellerAcct) {
		this.sellerAcct = sellerAcct;
	}

	public String getSellerPwd() {
		return sellerPwd;
	}

	public void setSellerPwd(String sellerPwd) {
		this.sellerPwd = sellerPwd;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getSellerNickname() {
		return sellerNickname;
	}

	public void setSellerNickname(String sellerNickname) {
		this.sellerNickname = sellerNickname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public String getSellerAddress() {
		return sellerAddress;
	}

	public void setSellerAddress(String sellerAddress) {
		this.sellerAddress = sellerAddress;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public byte[] getHeadShot() {
		return headShot;
	}

	public void setHeadShot(byte[] headShot) {
		this.headShot = headShot;
	}

	public AcctState getAcctState() {
		return acctState;
	}

	public void setAcctState(AcctState acctState) {
		this.acctState = acctState;
	}

	public double getAccumulaStart() {
		return accumulaStart;
	}

	public void setAccumulaStart(double accumulaStart) {
		this.accumulaStart = accumulaStart;
	}

	public int getAccumulaPeople() {
		return accumulaPeople;
	}

	public void setAccumulaPeople(int accumulaPeople) {
		this.accumulaPeople = accumulaPeople;
	}

	public AcctType getAcctType() {
		return acctType;
	}

	public void setAcctType(AcctType acctType) {
		this.acctType = acctType;
	}

	public String getSwiftCode() {
		return swiftCode;
	}

	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}

	public String getBankAcct() {
		return bankAcct;
	}

	public void setBankAcct(String bankAcct) {
		this.bankAcct = bankAcct;
	}

	public byte[] getIdFront() {
		return idFront;
	}

	public void setIdFront(byte[] idFront) {
		this.idFront = idFront;
	}

	public byte[] getIdBack() {
		return idBack;
	}

	public void setIdBack(byte[] idBack) {
		this.idBack = idBack;
	}

	public byte[] getBankAcctImg() {
		return bankAcctImg;
	}

	public void setBankAcctImg(byte[] bankAcctImg) {
		this.bankAcctImg = bankAcctImg;
	}

	public Timestamp getSellerCreateTime() {
		return sellerCreateTime;
	}

	public void setSellerCreateTime(Timestamp sellerCreateTime) {
		this.sellerCreateTime = sellerCreateTime;
	}

	public Integer getSellerPwdErrTimes() {
		return sellerPwdErrTimes;
	}

	public void setSellerPwdErrTimes(Integer sellerPwdErrTimes) {
		this.sellerPwdErrTimes = sellerPwdErrTimes;
	}

//    @OneToMany(mappedBy = "buylist",cascade = CascadeType.ALL)
//    	private Set<Buylist> buylists;
//    //要記得改回來
	
	@Override
	public int hashCode() {
		return Objects.hash(sellerId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seller other = (Seller) obj;
		return sellerId == other.sellerId;
	}
}
