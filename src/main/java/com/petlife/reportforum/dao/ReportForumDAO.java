package com.petlife.reportforum.dao;
import java.util.List;
import com.petlife.reportforum.entity.ReportForum;
public interface ReportForumDAO {
	int add(ReportForum reportForum);
	int update(ReportForum reportForum);
	int delete(Integer repotForumId);
	ReportForum findByPK(Integer repotForumId);
	List<ReportForum> getAll();
}
