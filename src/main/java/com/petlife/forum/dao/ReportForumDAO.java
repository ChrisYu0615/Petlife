package com.petlife.forum.dao;
import java.util.List;

import com.petlife.forum.entity.ReportForum;
public interface ReportForumDAO {
	public Integer add(ReportForum reportForum);
	
	public Integer update(ReportForum reportForum);
	
	public Integer delete(Integer repotForumId);
	
	public ReportForum findByPK(Integer repotForumId);
	
	List<ReportForum> getAll();
}
