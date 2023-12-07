package com.petlife.forum.service.impl;

import java.util.List;

import com.petlife.forum.dao.ReportTypeDAO;
import com.petlife.forum.dao.impl.ReportTypeDAOImpl;
import com.petlife.forum.entity.ReportType;
import com.petlife.forum.service.ReportTypeService;

public class ReportTypeServiceImpl implements ReportTypeService {
    private ReportTypeDAO reportTypeDao;

    public ReportTypeServiceImpl() {
        this.reportTypeDao = new ReportTypeDAOImpl();
    }

    @Override
    public ReportType addReportType(ReportType reportType) {
        Integer id = reportTypeDao.add(reportType);
        if (id != null && id != -1) {
            return reportTypeDao.findByPK(id);
        }
        return null;
    }

    @Override
    public Integer updateReportType(ReportType reportType) {
        return reportTypeDao.update(reportType);
    }

    @Override
    public Integer deleteReportType(Integer reportTypeId) {
        return reportTypeDao.delete(reportTypeId);
    }

    @Override
    public ReportType getReportTypeById(Integer reportTypeId) {
        return reportTypeDao.findByPK(reportTypeId);
    }

    @Override
    public List<ReportType> getAllReportTypes() {
        return reportTypeDao.getAll();
    }

    // 可能的其他方法
    // ...
}
