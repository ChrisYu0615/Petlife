package com.petlife.forum.dao;

import java.util.List;

import com.petlife.forum.entity.Comment;


public interface CommentDAO {

	public Integer add(Comment comment);
	
	public Integer update(Comment comment); 
	
	public Integer delete(Integer commentId); 
	
    public Comment findByPK(Integer commentId);

    public List<Comment> getAll();
}
