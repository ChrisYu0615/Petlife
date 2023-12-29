package com.petlife.forum.service;

import java.util.List;
import java.util.Map;

import com.petlife.forum.entity.Article;

public interface ArticleService {
    
    // 增加文章
    Article addArticle(Article article);
    
    // 刪除文章
    Integer deleteArticle(Integer articleId); 
    
    // 更新文章
    Integer updateArticle(Article article);
    
    // 根據文章ID查詢
    Article getArticleByArticleId(Integer articleId);
    
    // 獲得所有文章
    List<Article> getAllArticle();
    
    // 根據userId拿到所有文章
    List<Article> getAllArticle(Integer userId);
    
    // 根據關鍵字搜尋文章
    List<Article> searchArticlesByKeyword(String keyword);

	List<Article> getArticlesByCompositeQuery(Map<String, String[]> map);

    //  點閱數
	
	public void updateView(Integer articleId);
    
	// 依照點閱數和論壇ID找查文章 
	
	public List<Article> getTopArticlesByCTR(int forumId, int limit);
	
	public List<Article> getArticlesByForumId(Integer forumId); 
}
