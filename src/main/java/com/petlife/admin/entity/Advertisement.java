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

import com.oracle.wls.shaded.org.apache.xpath.functions.FuncFalse;

@Entity
@Table(name = "ad")
public class Advertisement {
	@Id
	@Column(name = "ad_id", updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer advertisementId;

	@Column(name = "ad_title")
	private String advertisementTitle;

	@Column(name = "ad_img", columnDefinition = "longblob")
	private byte[] advertisementImg;

	@Column(name = "ad_status", insertable = false)
	private boolean adStatus;

	@Column(name = "ad_content", columnDefinition = "longtext")
	private String advertisementContent;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;

	public Advertisement() {
	}

	public Advertisement(String advertisementTitle, byte[] advertisementImg, String advertisementContent,
			Date startDate, Date endDate) {
		super();
		this.advertisementTitle = advertisementTitle;
		this.advertisementImg = advertisementImg;
		this.advertisementContent = advertisementContent;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Advertisement(Integer advertisementId, String advertisementTitle, byte[] advertisementImg, boolean adStatus,
			String advertisementContent, Date startDate, Date endDate) {
		super();
		this.advertisementId = advertisementId;
		this.advertisementTitle = advertisementTitle;
		this.advertisementImg = advertisementImg;
		this.adStatus = adStatus;
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

	public String getAdvertisementTitle() {
		return advertisementTitle;
	}

	public void setAdvertisementTitle(String advertisementTitle) {
		this.advertisementTitle = advertisementTitle;
	}

	public byte[] getAdvertisementImg() {
		return advertisementImg;
	}

	public void setAdvertisementImg(byte[] advertisementImg) {
		this.advertisementImg = advertisementImg;
	}

	public boolean isAdStatus() {
		return adStatus;
	}

	public void setAdStatus(boolean adStatus) {
		this.adStatus = adStatus;
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
		return "Advertisement [advertisementId=" + advertisementId + ", advertisementTitle=" + advertisementTitle
				+ ", advertisementImg=" + Arrays.toString(advertisementImg) + ", adStatus=" + adStatus
				+ ", advertisementContent=" + advertisementContent + ", startDate=" + startDate + ", endDate=" + endDate
				+ "]";
	}

}
