package com.petlife.forum.service.impl;

import java.util.List;

import com.petlife.forum.dao.ReportForumDAO;
import com.petlife.forum.dao.impl.ReportForumDAOImpl;
import com.petlife.forum.entity.ReportForum;
import com.petlife.forum.service.ReportForumService;

public class ReportForumServiceImpl implements ReportForumService {
    private ReportForumDAO reportForumDao;

    public ReportForumServiceImpl() {
        this.reportForumDao = new ReportForumDAOImpl();
    }

    @Override
    public ReportForum addReportForum(ReportForum reportForum) {
        Integer id = reportForumDao.add(reportForum);
        if (id != null && id != -1) {
            return reportForumDao.findByPK(id);
        }
        return null;
    }

    @Override
    public Integer updateReportForum(ReportForum reportForum) {
        return reportForumDao.update(reportForum);
    }

    @Override
    public Integer deleteReportForum(Integer reportForumId) {
        return reportForumDao.delete(reportForumId);
    }

    @Override
    public ReportForum getReportForumById(Integer reportForumId) {
        return reportForumDao.findByPK(reportForumId);
    }

    @Override
    public List<ReportForum> getAllReportForums(String ... conditions) {
        return reportForumDao.getAll(conditions);
    }

    // 可能的其他方法
    // ...
}
