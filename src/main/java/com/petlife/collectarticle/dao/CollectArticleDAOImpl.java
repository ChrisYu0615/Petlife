package com.petlife.collectarticle.dao;

import java.util.List;
import org.hibernate.Session;
import com.petlife.collectarticle.entity.CollectArticle;
import util.HibernateUtil;

public class CollectArticleDAOImpl implements CollectArticleDAO {

    @Override
    public int add(CollectArticle collectArticle) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            Integer id = (Integer) session.save(collectArticle);
            session.getTransaction().commit();
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return -1;
        }
    }

    @Override
    public int update(CollectArticle collectArticle) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.update(collectArticle);
            session.getTransaction().commit();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return -1;
        }
    }

    @Override
    public int delete(Integer collectArticleId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            CollectArticle collectArticle = session.get(CollectArticle.class, collectArticleId);
            if (collectArticle != null) {
                session.delete(collectArticle);
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
    public CollectArticle findByPK(Integer collectArticleId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            CollectArticle collectArticle = session.get(CollectArticle.class, collectArticleId);
            session.getTransaction().commit();
            return collectArticle;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public List<CollectArticle> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            List<CollectArticle> list = session.createQuery("from CollectArticle", CollectArticle.class).list();
            session.getTransaction().commit();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        }
    }
}

