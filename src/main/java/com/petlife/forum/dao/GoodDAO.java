package com.petlife.forum.dao;

import java.util.Date;
import java.util.List;

import com.petlife.forum.entity.Article;
import com.petlife.forum.entity.Good;
 // 假設 Content 是您的文章或帖子的實體類

public interface GoodDAO {
    // 添加讚
    public Integer add(Good good);
    
    // 更新讚
    public Integer update(Good good);
    
    // 刪除讚
    public Integer delete(Integer goodId);
    
    // 根據主鍵查找讚
    public Good findByPK(Integer goodId);
    
    // 獲取所有讚
    List<Good> getAll();

    // 獲取特定文章的總讚數
    /**
    * 獲取特定文章的總讚數。
    * 
    * @param articleId 文章的ID。
    * @return 該文章的總讚數。
    */
    public Integer getGoodAmount(Integer articleId);

    // 獲取熱門內容
    /**
    * 根據讚的數量獲取一定時間範圍內的熱門內容。
    * 
    * @param startTime 開始時間。
    * @param endTime 結束時間。
    * @param limit 返回的最大內容數量。
    * @return 一個列表，包含在指定時間範圍內獲得最多讚的內容。
    */
//    List<Article> getPopularContents(Date startTime, Date endTime, int limit);

   
}
