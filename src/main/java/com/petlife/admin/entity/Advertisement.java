package com.petlife.admin.entity;

import java.sql.Date;
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

@Entity
@Table(name = "ad")
public class Advertisement {
	@Id
	@Column(name = "ad_id", updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer advertisementId;

	@ManyToOne
	@JoinColumn(name = "admin_id", referencedColumnName = "admin_id")
	private Admin admin;

	@Column(name = "ad_img", columnDefinition = "longblob")
	private byte[] advertisementImg;

	@Column(name = "ad_content", columnDefinition = "longtext")
	private String advertisementContent;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;

	public Advertisement() {
	}

	public Advertisement(Integer advertisementId, Admin admin, byte[] advertisementImg, String advertisementContent,
			Date startDate, Date endDate) {
		super();
		this.advertisementId = advertisementId;
		this.admin = admin;
		this.advertisementImg = advertisementImg;
		this.advertisementContent = advertisementContent;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Integer getAdvertisementId() {
		return advertisementId;
	}

	public void setAdvertisementId(Integer advertisementId) {
		this.advertisementId = advertisementId;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public byte[] getAdvertisementImg() {
		return advertisementImg;
	}

	public void setAdvertisementImg(byte[] advertisementImg) {
		this.advertisementImg = advertisementImg;
	}

	public String getAdvertisementContent() {
		return advertisementContent;
	}

	public void setAdvertisementContent(String advertisementContent) {
		this.advertisementContent = advertisementContent;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(advertisementId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Advertisement other = (Advertisement) obj;
		return Objects.equals(advertisementId, other.advertisementId);
	}

	@Override
	public String toString() {
		return "Advertisement [advertisementId=" + advertisementId + ", admin=" + admin.getAdminNickname()
				+ ", advertisementImg=" + Arrays.toString(advertisementImg) + ", advertisementContent="
				+ advertisementContent + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}

}
