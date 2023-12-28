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

	List<Article> getAll(Integer userId);

//	public Long getTotal();
	// 搜尋文章功能
	public List<Article> searchByKeyword(String keyword);

	public List<Article> getByCompositeQuery(Map<String, String> map);
	
	//瀏覽數
	public void updateView(Integer articleId);
	
	//以瀏覽數和論壇ID來判斷熱門文章
	List <Article> findTopArticlesByCTR(int forumId, int limit);
	
	//以論壇種類來找文章
	
	List <Article>  findArticlesByForumId(Integer forumId);
	
}
