package com.petlife.reporttype.dao;

import java.util.List;
import com.petlife.reporttype.entity.ReportType;

public interface ReportTypeDAO {
    int add(ReportType reportType);
    int update(ReportType reportType);
    int delete(Integer reportTypeId);
    ReportType findByPK(Integer reportTypeId);
    List<ReportType> getAll();
}
