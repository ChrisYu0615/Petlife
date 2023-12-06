package com.petlife.forum.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.petlife.forum.dao.CommentDAO;
import com.petlife.forum.entity.Comment;
import com.petlife.util.HibernateUtil;

public class CommentDAOImpl implements CommentDAO {
    private SessionFactory factory;

    public CommentDAOImpl() {
        factory = HibernateUtil.getSessionFactory();
    }

    private Session getSession() {
        return factory.getCurrentSession();
    }

    @Override
    public Integer add(Comment comment) {
        Integer id = (Integer) getSession().save(comment);
        return id;
    }

    @Override
    public Integer delete(Integer commentId) {
        Comment comment = getSession().get(Comment.class, commentId);
        if (comment != null) {
            getSession().delete(comment);
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public Integer update(Comment comment) {
        try {
            getSession().update(comment);
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public Comment findByPK(Integer commentId) {
        return getSession().get(Comment.class, commentId);
    }

    @Override
    public List<Comment> getAll() {
        return getSession().createQuery("from Comment", Comment.class).getResultList();
    }
}
