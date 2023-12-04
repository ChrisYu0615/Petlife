package com.petlife.comment.dao;

import java.util.List;

import com.petlife.comment.entity.Comment;


public interface CommentDAO {

    int add(Comment comment);
    int update(Comment comment); 
    int delete(Integer commentId); 
    Comment findByPK(Integer commentId);

    public List<Comment> getAll();
}
