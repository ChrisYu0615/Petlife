package com.petlife.forum.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.petlife.forum.dao.ReportForumDAO;
import com.petlife.forum.entity.ReportForum;
import com.petlife.util.HibernateUtil;

public class ReportForumDAOImpl implements ReportForumDAO {
    private SessionFactory factory;

    public ReportForumDAOImpl() {
        factory = HibernateUtil.getSessionFactory();
    }

    private Session getSession() {
        return factory.getCurrentSession();
    }

    @Override
    public Integer add(ReportForum reportForum) {
        try {
            return (Integer) getSession().save(reportForum);
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public Integer update(ReportForum reportForum) {
        try {
            getSession().update(reportForum);
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public Integer delete(Integer reportForumId) {
        ReportForum reportForum = getSession().get(ReportForum.class, reportForumId);
        if (reportForum != null) {
            getSession().delete(reportForum);
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public ReportForum findByPK(Integer reportForumId) {
        return getSession().get(ReportForum.class, reportForumId);
    }

    @Override
    public List<ReportForum> getAll() {
        return getSession().createQuery("from ReportForum", ReportForum.class).getResultList();
    }
}
