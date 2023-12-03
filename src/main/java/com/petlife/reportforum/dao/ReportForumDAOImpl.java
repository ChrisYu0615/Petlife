package com.petlife.reportforum.dao;

import java.util.List;
import org.hibernate.Session;
import com.petlife.reportforum.entity.ReportForum;
import util.HibernateUtil;

public class ReportForumDAOImpl implements ReportForumDAO {

    @Override
    public int add(ReportForum reportForum) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            Integer id = (Integer) session.save(reportForum);
            session.getTransaction().commit();
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return -1;
    }

    @Override
    public int update(ReportForum reportForum) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.update(reportForum);
            session.getTransaction().commit();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return -1;
    }

    @Override
    public int delete(Integer reportForumId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            ReportForum reportForum = session.get(ReportForum.class, reportForumId);
            if (reportForum != null) {
                session.delete(reportForum);
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
    public ReportForum findByPK(Integer reportForumId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            ReportForum reportForum = session.get(ReportForum.class, reportForumId);
            session.getTransaction().commit();
            return reportForum;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

    @Override
    public List<ReportForum> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            List<ReportForum> list = session.createQuery("from ReportForum", ReportForum.class).list();
            session.getTransaction().commit();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }
}
