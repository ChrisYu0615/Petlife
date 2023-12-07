package com.petlife.forum.service;

import java.util.Date;
import java.util.List;

import com.petlife.forum.entity.Good;

public interface GoodService {
    // 添加讚
    Good addGood(Good good);

    // 更新讚
    Integer updateGood(Good good);

    // 刪除讚
    Integer deleteGood(Integer goodId);

    // 根據主键查找讚
    Good getGoodById(Integer goodId);

    // 獲取所有讚
    List<Good> getAllGoods();

    // 獲取特定文章的總讚數
    Integer getGoodAmountByArticleId(Integer articleId);

    // 獲取熱門文章內容
    // List<Article> getPopularArticles(Date startTime, Date endTime, int limit);
}
