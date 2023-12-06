package com.petlife.forum.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.petlife.forum.dao.ReportTypeDAO;
import com.petlife.forum.entity.ReportType;
import com.petlife.util.HibernateUtil;

public class ReportTypeDAOImpl implements ReportTypeDAO {
    private SessionFactory factory;

    public ReportTypeDAOImpl() {
        factory = HibernateUtil.getSessionFactory();
    }

    private Session getSession() {
        return factory.getCurrentSession();
    }

    @Override
    public Integer add(ReportType reportType) {
        try {
            return (Integer) getSession().save(reportType);
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public Integer update(ReportType reportType) {
        try {
            getSession().update(reportType);
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public Integer delete(Integer reportTypeId) {
        ReportType reportType = getSession().get(ReportType.class, reportTypeId);
        if (reportType != null) {
            getSession().delete(reportType);
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public ReportType findByPK(Integer reportTypeId) {
        return getSession().get(ReportType.class, reportTypeId);
    }

    @Override
    public List<ReportType> getAll() {
        return getSession().createQuery("from ReportType", ReportType.class).getResultList();
    }
}
