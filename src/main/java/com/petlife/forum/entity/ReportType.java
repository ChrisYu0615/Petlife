package com.petlife.forum.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "report_type")
public class ReportType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增鍵
    @Column(name = "report_type_id", updatable = false, insertable = false)
    @Expose
    private Integer reportTypeId;

    @Column(name = "report_type_name")
    @Expose
    private String reportTypeName;

    public ReportType() {
        super();
    }

    public ReportType(Integer reportTypeId, String reportTypeName) {
        this.reportTypeId = reportTypeId;
        this.reportTypeName = reportTypeName;
    }

    public Integer getReportTypeId() {
        return reportTypeId;
    }

    public void setReportTypeId(Integer reportTypeId) {
        this.reportTypeId = reportTypeId;
    }

    public String getReportTypeName() {
        return reportTypeName;
    }

    public void setReportTypeName(String reportTypeName) {
        this.reportTypeName = reportTypeName;
    }

	@Override
	public int hashCode() {
		return Objects.hash(reportTypeId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReportType other = (ReportType) obj;
		return Objects.equals(reportTypeId, other.reportTypeId);
	}

	@Override
	public String toString() {
		return "ReportType [reportTypeId=" + reportTypeId + ", reportTypeName=" + reportTypeName + "]";
	}

    // 可以根據需要添加 toString()、hashCode() 和 equals() 方法
    
}
