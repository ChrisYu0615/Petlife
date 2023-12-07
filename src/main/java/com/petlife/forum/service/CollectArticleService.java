package com.petlife.forum.service;

import java.util.List;

import com.petlife.forum.entity.CollectArticle;

public interface CollectArticleService {
    
    // 增加收藏文章
    CollectArticle addCollectArticle(CollectArticle collectArticle);

    // 刪除收藏文章
    Integer deleteCollectArticle(Integer collectArticleId);

    // 更新收藏文章
    Integer updateCollectArticle(CollectArticle collectArticle);

    // 根據ID查詢收藏文章
    CollectArticle getCollectArticleById(Integer collectArticleId);

    // 獲得所有收藏文章
    List<CollectArticle> getAllCollectArticles();

    // 可能的其他方法
    // ...
}
