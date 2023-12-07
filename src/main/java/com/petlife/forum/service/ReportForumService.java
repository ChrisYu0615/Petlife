package com.petlife.forum.service;

import java.util.List;

import com.petlife.forum.entity.ReportForum;

public interface ReportForumService {
    // 新增檢舉
    ReportForum addReportForum(ReportForum reportForum);

    // 更新檢舉
    Integer updateReportForum(ReportForum reportForum);

    // 刪檢舉
    Integer deleteReportForum(Integer reportForumId);

    // 根據主鍵找檢舉
    ReportForum getReportForumById(Integer reportForumId);

    // 獲取所有檢舉
    List<ReportForum> getAllReportForums();
}
