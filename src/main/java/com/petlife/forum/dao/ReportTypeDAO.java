package com.petlife.forum.dao;

import java.util.List;

import com.petlife.forum.entity.ReportType;

public interface ReportTypeDAO {
    int add(ReportType reportType);
    int update(ReportType reportType);
    int delete(Integer reportTypeId);
    ReportType findByPK(Integer reportTypeId);
    List<ReportType> getAll();
}
