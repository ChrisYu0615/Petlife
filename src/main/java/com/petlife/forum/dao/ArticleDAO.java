package com.petlife.forum.dao;

import java.util.List;

import com.petlife.forum.entity.Article;
public interface ArticleDAO {
	int add(Article article);
	int update(Article article);
	int delete(Integer articleId);
	Article findByPK(Integer articleId);
	List<Article> getAll();
}

