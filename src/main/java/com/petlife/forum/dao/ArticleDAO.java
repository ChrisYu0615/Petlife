package com.petlife.forum.dao;

import java.util.List;
import java.util.Map;

import com.petlife.forum.entity.Article;
public interface ArticleDAO {
	public Integer add(Article article);
	
	public Integer update(Article article);
	
	public Integer delete(Integer articleId);
	
	public Article findByPK(Integer articleId);
	
	List<Article> getAll();
	
//	public Long getTotal();
   //搜尋文章功能
	public List<Article> searchByKeyword(String keyword);

	public List<Article> getByCompositeQuery(Map<String, String> map);
}

