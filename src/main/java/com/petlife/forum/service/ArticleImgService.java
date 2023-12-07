package com.petlife.forum.service;

import java.util.List;

import com.petlife.forum.entity.ArticleImg;

public interface ArticleImgService {
    
    // 增加圖片
    ArticleImg addArticleImg(ArticleImg articleImg);

    // 刪除圖片
    Integer deleteArticleImg(Integer articleImgId);

    // 更新圖片
    Integer updateArticleImg(ArticleImg articleImg);

    // 根據圖片ID查詢
    ArticleImg getArticleImgByImgId(Integer articleImgId);

    // 獲得所有圖片
    List<ArticleImg> getAllArticleImgs();

     //根據文章ID獲得所有相關圖片
    List<ArticleImg> getArticleImgsByArticleId(Integer articleId);

    
}
