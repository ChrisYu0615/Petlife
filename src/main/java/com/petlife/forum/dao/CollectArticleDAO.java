package com.petlife.forum.dao;

import java.util.List;

import com.petlife.forum.entity.CollectArticle;

public interface CollectArticleDAO {
    int add(CollectArticle collectArticle);
    int update(CollectArticle collectArticle);
    int delete(Integer collectArticleId);
    CollectArticle findByPK(Integer collectArticleId);
    List<CollectArticle> getAll();
}
