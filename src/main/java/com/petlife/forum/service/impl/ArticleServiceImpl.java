package com.petlife.forum.service.impl;

import java.util.List;

import com.petlife.forum.dao.ArticleDAO;
import com.petlife.forum.dao.impl.ArticleDAOImpl;
import com.petlife.forum.entity.Article;
import com.petlife.forum.service.ArticleService;

public class ArticleServiceImpl implements ArticleService {
    private ArticleDAO dao;

    public ArticleServiceImpl() {
        dao = new ArticleDAOImpl();
    }

    @Override
    public Article addArticle(Article article) {
        Integer id = dao.add(article);
        if (id != null && id != -1) {
            article = dao.findByPK(id);
        }
        return article;
    }

    @Override
    public Integer deleteArticle(Integer articleId) {
        return dao.delete(articleId);
    }

    @Override
    public Integer updateArticle(Article article) {
        return dao.update(article);
    }

    @Override
    public Article getArticleByArticleId(Integer articleId) {
        return dao.findByPK(articleId);
    }

    @Override
    public List<Article> getAllArticle() {
        return dao.getAll();
    }

    @Override
    public List<Article> searchArticlesByKeyword(String keyword) {
        // 假設 ArticleDAO 有一個 searchByKeyword 方法
        return dao.searchByKeyword(keyword);
    }

    // 可能的其他方法
    // ...
}
