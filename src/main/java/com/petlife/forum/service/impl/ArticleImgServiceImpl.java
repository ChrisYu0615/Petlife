package com.petlife.forum.service.impl;

import java.util.List;

import com.petlife.forum.dao.ArticleImgDAO;
import com.petlife.forum.dao.impl.ArticleImgDAOImpl;
import com.petlife.forum.entity.ArticleImg;
import com.petlife.forum.service.ArticleImgService;

public class ArticleImgServiceImpl implements ArticleImgService {
    private ArticleImgDAO dao;

    public ArticleImgServiceImpl() {
        dao = new ArticleImgDAOImpl();
    }

    @Override
    public ArticleImg addArticleImg(ArticleImg articleImg) {
        Integer id = dao.add(articleImg);
        if(id != null && id != -1) {
            articleImg = dao.findByPK(id);
            return articleImg;
        }
        return null;
    }

    @Override
    public Integer deleteArticleImg(Integer articleImgId) {
        return dao.delete(articleImgId);
    }

    @Override
    public Integer updateArticleImg(ArticleImg articleImg) {
        return dao.update(articleImg);
    }

    @Override
    public ArticleImg getArticleImgById(Integer articleId) {
        return dao.findByPK(articleId);
    }

    @Override
    public List<ArticleImg> getAllArticleImgs() {
        return dao.getAll();
    }

    @Override
    public List<ArticleImg> getArticleImgsByArticleId(Integer articleId) {
        return dao.getImgsByArticleId(articleId);
    }
}
