package com.petlife.forum.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.petlife.forum.dao.GoodDAO;
import com.petlife.forum.entity.Article;
import com.petlife.forum.entity.Good;
import com.petlife.util.HibernateUtil;

public class GoodDAOImpl implements GoodDAO {
    private SessionFactory factory;

    public GoodDAOImpl() {
        factory = HibernateUtil.getSessionFactory();
    }

    private Session getSession() {
        return factory.getCurrentSession();
    }

    @Override
    public Integer add(Good good) {
        Integer id = (Integer) getSession().save(good);
        return id;
    }

    @Override
    public Integer update(Good good) {
        try {
            getSession().update(good);
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public Integer delete(Integer goodId) {
        Good good = getSession().get(Good.class, goodId);
        if (good != null) {
            getSession().delete(good);
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public Good findByPK(Integer goodId) {
        return getSession().get(Good.class, goodId);
    }

    @Override
    public List<Good> getAll() {
        return getSession().createQuery("from Good", Good.class).getResultList();
    }

    @Override
    public Integer getGoodAmount(Integer articleId) {
        String hql = "select count(*) from Good where articleId = :articleId";
        Query<Long> query = getSession().createQuery(hql, Long.class);
        query.setParameter("articleId", articleId);
        return query.uniqueResult().intValue();
    }

//    @Override
//    public List<Article> getPopularArticles(Date startTime, Date endTime, int limit) {
//        String hql = "select article from Good where createTime between :startTime and :endTime group by articleId order by count(*) desc";
//        Query<Article> query = getSession().createQuery(hql, Article.class);
//        query.setParameter("startTime", startTime);
//        query.setParameter("endTime", endTime);
//        query.setMaxResults(limit);
//        return query.getResultList();
//    }


    // 其他方法的实现...
}
