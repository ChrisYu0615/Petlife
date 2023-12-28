package com.petlife.forum.service.impl;

import java.util.List;

import com.petlife.forum.dao.CommentDAO;
import com.petlife.forum.dao.impl.CommentDAOImpl;
import com.petlife.forum.entity.Comment;
import com.petlife.forum.service.CommentService;

public class CommentServiceImpl implements CommentService {
    private CommentDAO dao;

    public CommentServiceImpl() {
        dao = new CommentDAOImpl();
    }

    @Override
    public Comment addComment(Comment comment) {
        Integer id = dao.add(comment);
        if(id != null && id != -1) {
            return dao.findByPK(id);
        }
        return null;
    }

    @Override
    public Integer deleteComment(Integer commentId) {
        return dao.delete(commentId);
    }

    @Override
    public Integer updateComment(Comment comment) {
        return dao.update(comment);
    }

    @Override
    public Comment getCommentById(Integer commentId) {
        return dao.findByPK(commentId);
    }

    @Override
    public List<Comment> getAllComments(Integer articleId) {
        return dao.getAll(articleId);
    }

    // 可能的其他方法
    // ...
}
