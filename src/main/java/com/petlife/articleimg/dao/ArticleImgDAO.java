package com.petlife.articleimg.dao;

import java.util.List;

import com.petlife.articleimg.entity.ArticleImg;


public interface ArticleImgDAO {

    int add(ArticleImg articleImg);
    int update(ArticleImg articleImg);
    int delete(Integer articleImgId);
    ArticleImg findByPK(Integer articleImgId);
    List<ArticleImg> getAll();
     
    }
