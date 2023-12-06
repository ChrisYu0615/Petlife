package com.petlife.forum.dao;

import java.util.List;

import com.petlife.forum.entity.ReportType;

public interface ReportTypeDAO {
	public Integer add(ReportType reportType);
	
	public Integer update(ReportType reportType);
	
	public Integer delete(Integer reportTypeId);
	
    public ReportType findByPK(Integer reportTypeId);
    
    List<ReportType> getAll();
}
