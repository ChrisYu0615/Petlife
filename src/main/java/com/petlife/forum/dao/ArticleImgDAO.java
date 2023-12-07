package com.petlife.forum.dao;

import java.util.List;

import com.petlife.forum.entity.ArticleImg;


public interface ArticleImgDAO {

    int add(ArticleImg articleImg);
    int update(ArticleImg articleImg);
    int delete(Integer articleImgId);
    ArticleImg findByPK(Integer articleImgId);
    List<ArticleImg> getAll();
     
    }
