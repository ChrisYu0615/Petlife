package com.petlife.forum.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.petlife.forum.dao.CollectArticleDAO;
import com.petlife.forum.entity.CollectArticle;
import com.petlife.util.HibernateUtil;

public class CollectArticleDAOImpl implements CollectArticleDAO {
    private SessionFactory factory;

    public CollectArticleDAOImpl() {
        factory = HibernateUtil.getSessionFactory();
    }

    private Session getSession() {
        return factory.getCurrentSession();
    }

    @Override
    public Integer add(CollectArticle collectArticle) {
        Integer id = (Integer) getSession().save(collectArticle);
        return id;
    }

    @Override
    public Integer delete(Integer collectArticleId) {
        CollectArticle collectArticle = getSession().get(CollectArticle.class, collectArticleId);
        if (collectArticle != null) {
            getSession().delete(collectArticle);
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public Integer update(CollectArticle collectArticle) {
        try {
            getSession().update(collectArticle);
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public CollectArticle findByPK(Integer collectArticleId) {
        return getSession().get(CollectArticle.class, collectArticleId);
    }

    @Override
    public List<CollectArticle> getAll() {
        return getSession().createQuery("from CollectArticle", CollectArticle.class).getResultList();
    }

    @Override
    public Long getTotal() {
        return (Long) getSession().createQuery("select count(*) from CollectArticle").getSingleResult();
    }
    // 其他可能的方法
    // ...
}
