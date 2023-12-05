package com.petlife.comment.dao;

import java.util.List;
import org.hibernate.Session;
import com.petlife.comment.entity.Comment;
import util.HibernateUtil;

public class CommentDAOImpl implements CommentDAO {

    @Override
    public int add(Comment comment) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            Integer id = (Integer) session.save(comment);
            session.getTransaction().commit();
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return -1;
        }
    }

    @Override
    public int update(Comment comment) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.update(comment);
            session.getTransaction().commit();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return -1;
        }
    }

    @Override
    public int delete(Integer commentId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            Comment comment = session.get(Comment.class, commentId);
            if (comment != null) {
                session.delete(comment);
            }
            session.getTransaction().commit();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return -1;
        }
    }

    @Override
    public Comment findByPK(Integer commentId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            Comment comment = session.get(Comment.class, commentId);
            session.getTransaction().commit();
            return comment;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public List<Comment> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            List<Comment> list = session.createQuery("from Comment", Comment.class).list();
            session.getTransaction().commit();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        }
    }
}
