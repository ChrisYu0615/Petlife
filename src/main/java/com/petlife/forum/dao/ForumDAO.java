package com.petlife.forum.dao;

import java.util.List;
import com.petlife.forum.entity.Forum;

public interface ForumDAO {
	public Integer add(Forum forum); 
	
	public Integer update(Forum forum);
	
	public Integer delete(Integer forumId);
	
    public Forum findByPK(Integer forumId);
    
    List<Forum> getAll();
}
