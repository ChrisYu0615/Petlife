package com.petlife.forum.dao;

import java.util.List;

import com.petlife.forum.entity.Comment;


public interface CommentDAO {

    int add(Comment comment);
    int update(Comment comment); 
    int delete(Integer commentId); 
    Comment findByPK(Integer commentId);

    public List<Comment> getAll();
}
