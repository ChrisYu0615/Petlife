package com.petlife.article.service;

import java.util.List;

import com.petlife.article.dao.ArticleDAO;
import com.petlife.article.dao.ArticleDAOImpl;
import com.petlife.article.entity.Article;

public class ArticleService {
    private final ArticleDAO dao;

    public ArticleService() {
        dao = new ArticleDAOImpl();
    }
     
    public void addArticle(Article article) {
    	dao.add(article);
    }
    
    public void deleteArticle(Integer articleId) {
    	dao.delete(articleId);
    }
    
    public void updateArticle(Article article) {
    	dao.update(article);
    }
    
    public Article getOneArticle(Integer articleId) {
    	return dao.findByPK(articleId);
    }
    
    public List<Article> getAll(){
    	return dao.getAll();
    }
   }