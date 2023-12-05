package com.petlife.forum.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.petlife.forum.dao.ArticleImgDAO;
import com.petlife.forum.entity.ArticleImg;
import com.petlife.util.HibernateUtil;

public class ArticleImgDAOImpl implements ArticleImgDAO {

    @Override
    public int add(ArticleImg articleImg) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            Integer id = (Integer) session.save(articleImg);
            session.getTransaction().commit();
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return -1;
        }
    }

    @Override
    public int update(ArticleImg articleImg) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.update(articleImg);
            session.getTransaction().commit();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return -1;
        }
    }

    @Override
    public int delete(Integer articleImgId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            ArticleImg articleImg = session.get(ArticleImg.class, articleImgId);
            if (articleImg != null) {
                session.delete(articleImg);
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
    public ArticleImg findByPK(Integer articleImgId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            ArticleImg articleImg = session.get(ArticleImg.class, articleImgId);
            session.getTransaction().commit();
            return articleImg;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public List<ArticleImg> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            List<ArticleImg> list = session.createQuery("from ArticleImg", ArticleImg.class).list();
            session.getTransaction().commit();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        }
    }
}
