package com.petlife.forum.dao;

import java.util.List;

import com.petlife.forum.entity.CollectArticle;

public interface CollectArticleDAO {
    public Integer add(CollectArticle collectArticle);
    
    public Integer update(CollectArticle collectArticle);
    
    public Integer delete(Integer collectArticleId);
    
    CollectArticle findByPK(Integer collectArticleId);
    
    List<CollectArticle> getAll();
    
    Long getTotal();  // 新增 getTotal 方法
}
