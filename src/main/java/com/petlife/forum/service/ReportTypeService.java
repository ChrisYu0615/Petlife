package com.petlife.forum.service;

import java.util.List;

import com.petlife.forum.entity.ReportType;

public interface ReportTypeService {
    // 新增举报类型
    ReportType addReportType(ReportType reportType);

    // 更新举报类型
    Integer updateReportType(ReportType reportType);

    // 删除举报类型
    Integer deleteReportType(Integer reportTypeId);

    // 根据主键查找举报类型
    ReportType getReportTypeById(Integer reportTypeId);

    // 获取所有举报类型
    List<ReportType> getAllReportTypes();
}
