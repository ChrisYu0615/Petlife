package com.petlife.forum.dao;

import java.util.List;
import org.hibernate.Session;
import com.petlife.forum.entity.Forum;
import util.HibernateUtil;

public class ForumDAOImpl implements ForumDAO {

    @Override
    public int add(Forum forum) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            Integer id = (Integer) session.save(forum);
            session.getTransaction().commit();
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return -1;
    }

    @Override
    public int update(Forum forum) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.update(forum);
            session.getTransaction().commit();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return -1;
    }

    @Override
    public int delete(Integer forumId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            Forum forum = session.get(Forum.class, forumId);
            if (forum != null) {
                session.delete(forum);
            }
            session.getTransaction().commit();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return -1;
    }

    @Override
    public Forum findByPK(Integer forumId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            Forum forum = session.get(Forum.class, forumId);
            session.getTransaction().commit();
            return forum;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

    @Override
    public List<Forum> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            List<Forum> list = session.createQuery("from Forum", Forum.class).list();
            session.getTransaction().commit();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }
}
