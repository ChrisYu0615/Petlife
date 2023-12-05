package com.petlife.collectarticle.dao;

import java.util.List;
import com.petlife.collectarticle.entity.CollectArticle;

public interface CollectArticleDAO {
    int add(CollectArticle collectArticle);
    int update(CollectArticle collectArticle);
    int delete(Integer collectArticleId);
    CollectArticle findByPK(Integer collectArticleId);
    List<CollectArticle> getAll();
}
