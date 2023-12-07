package com.petlife.forum.dao;

import java.util.List;

import com.petlife.forum.entity.ArticleImg;


public interface ArticleImgDAO {

	public Integer add(ArticleImg articleImg);
	
	public Integer update(ArticleImg articleImg);
	
	public Integer delete(Integer articleImgId);
	
	public ArticleImg findByPK(Integer articleImgId);
	
	public List<ArticleImg> getAll();

	List<ArticleImg> getImgsByArticleId(Integer articleId);


     
	
    }
