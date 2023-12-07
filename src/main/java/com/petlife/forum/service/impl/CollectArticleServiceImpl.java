package com.petlife.forum.service.impl;

import java.util.List;

import com.petlife.forum.dao.CollectArticleDAO;
import com.petlife.forum.dao.impl.CollectArticleDAOImpl;
import com.petlife.forum.entity.CollectArticle;
import com.petlife.forum.service.CollectArticleService;

public class CollectArticleServiceImpl implements CollectArticleService {
    private CollectArticleDAO dao;

    public CollectArticleServiceImpl() {
        dao = new CollectArticleDAOImpl();
    }

    @Override
    public CollectArticle addCollectArticle(CollectArticle collectArticle) {
        Integer id = dao.add(collectArticle);
        if(id != null && id != -1) {
            return dao.findByPK(id);
        }
        return null;
    }

    @Override
    public Integer deleteCollectArticle(Integer collectArticleId) {
        return dao.delete(collectArticleId);
    }

    @Override
    public Integer updateCollectArticle(CollectArticle collectArticle) {
        return dao.update(collectArticle);
    }

    @Override
    public CollectArticle getCollectArticleById(Integer collectArticleId) {
        return dao.findByPK(collectArticleId);
    }

    @Override
    public List<CollectArticle> getAllCollectArticles() {
        return dao.getAll();
    }

    // 可能的其他方法
    // ...
}
