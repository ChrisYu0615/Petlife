package com.petlife.forum.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.petlife.forum.dao.ForumDAO;
import com.petlife.forum.entity.Forum;
import com.petlife.util.HibernateUtil;

public class ForumDAOImpl implements ForumDAO {
    private SessionFactory factory;

    public ForumDAOImpl() {
        factory = HibernateUtil.getSessionFactory();
    }

    private Session getSession() {
        return factory.getCurrentSession();
    }

    @Override
    public Integer add(Forum forum) {
        Integer id = (Integer) getSession().save(forum);
        return id;
    }

    @Override
    public Integer delete(Integer forumId) {
        Forum forum = getSession().get(Forum.class, forumId);
        if (forum != null) {
            getSession().delete(forum);
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public Integer update(Forum forum) {
        try {
            getSession().update(forum);
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public Forum findByPK(Integer forumId) {
        return getSession().get(Forum.class, forumId);
    }

    @Override
    public List<Forum> getAll() {
        return getSession().createQuery("from Forum", Forum.class).getResultList();
    }
    
    public Forum findForumBySortName(String sortName) {
        Query<Forum> query = getSession().createQuery("from Forum where sortName = :sortName", Forum.class);
        query.setParameter("sortName", sortName);
        List<Forum> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
}
