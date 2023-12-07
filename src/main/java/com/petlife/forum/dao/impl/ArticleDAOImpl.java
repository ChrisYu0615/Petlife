package com.petlife.forum.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.petlife.forum.dao.ArticleDAO;
import com.petlife.forum.entity.Article;
import com.petlife.util.HibernateUtil;

public class ArticleDAOImpl implements ArticleDAO {
    private SessionFactory factory;

    public ArticleDAOImpl() {
        factory = HibernateUtil.getSessionFactory();
    }

    private Session getSession() {
        return factory.getCurrentSession();
    }

    @Override
    public Integer add(Article article) {
        Integer id = (Integer) getSession().save(article);
        return id;
    }

    @Override
    public Integer delete(Integer articleId) {
        Article article = getSession().get(Article.class, articleId);
        if (article != null) {
            getSession().delete(article);
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public Integer update(Article article) {
        try {
            getSession().update(article);
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public Article findByPK(Integer articleId) {
        return getSession().get(Article.class, articleId);
    }

    @Override
    public List<Article> getAll() {
        return getSession().createQuery("from Article", Article.class).getResultList();
    }
//關鍵字搜尋
    @Override
    public List<Article> searchByKeyword(String keyword) {
        String hql = "from Article where article_name like :keyword or article_content like :keyword";
        Query<Article> query = getSession().createQuery(hql, Article.class);
        query.setParameter("keyword", "%" + keyword + "%");
        return query.getResultList();
    }

    // 其他可能的方法
    // ...
}
