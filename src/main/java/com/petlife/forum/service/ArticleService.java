package com.petlife.forum.service;

import java.util.List;

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
    
    // 根據關鍵字搜尋文章
    List<Article> searchArticlesByKeyword(String keyword);

    // 可能的其他方法
    // ...
}
