package com.petlife.forum.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.petlife.forum.dao.ArticleImgDAO;
import com.petlife.forum.entity.ArticleImg;
import com.petlife.util.HibernateUtil;

public class ArticleImgDAOImpl implements ArticleImgDAO {
    private SessionFactory factory;

    public ArticleImgDAOImpl() {
        factory = HibernateUtil.getSessionFactory();
    }

    private Session getSession() {
        return factory.getCurrentSession();
    }

    @Override
    public Integer add(ArticleImg articleImg) {
        Integer id = (Integer) getSession().save(articleImg);
        return id;
    }

    @Override
    public Integer delete(Integer articleImgId) {
        ArticleImg articleImg = getSession().get(ArticleImg.class, articleImgId);
        if (articleImg != null) {
            getSession().delete(articleImg);
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public Integer update(ArticleImg articleImg) {
        try {
            getSession().update(articleImg);
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public ArticleImg findByPK(Integer articleImgId) {
        return getSession().get(ArticleImg.class, articleImgId);
    }

    @Override
    public List<ArticleImg> getAll() {
        return getSession().createQuery("from ArticleImg", ArticleImg.class).getResultList();
    }

    @Override
    public List<ArticleImg> getImgsByArticleId(Integer articleId) {
        String hql = "from ArticleImg where article_id = :articleId";
        Query<ArticleImg> query = getSession().createQuery(hql, ArticleImg.class);
        query.setParameter("articleId", articleId);
        return query.getResultList();
    }

    // 其他可能的方法
    // ...
}
