package com.petlife.forum.service.impl;

import java.util.List;

import com.petlife.forum.dao.GoodDAO;
import com.petlife.forum.dao.impl.GoodDAOImpl;
import com.petlife.forum.entity.Good;
import com.petlife.forum.service.GoodService;

public class GoodServiceImpl implements GoodService {
    private GoodDAO goodDao;

    public GoodServiceImpl() {
        this.goodDao = new GoodDAOImpl();
    }

    @Override
    public Good addGood(Good good) {
        Integer id = goodDao.add(good);
        if (id != null && id != -1) {
            return goodDao.findByPK(id);
        }
        return null;
    }

    @Override
    public Integer updateGood(Good good) {
        return goodDao.update(good);
    }

    @Override
    public Integer deleteGood(Integer goodId) {
        return goodDao.delete(goodId);
    }

    @Override
    public Good getGoodById(Integer goodId) {
        return goodDao.findByPK(goodId);
    }

    @Override
    public List<Good> getAllGoods() {
        return goodDao.getAll();
    }

    @Override
    public Integer getGoodAmountByArticleId(Integer articleId) {
        return goodDao.getGoodAmount(articleId);
    }

    // 注释掉的方法示例
    // @Override
    // public List<Article> getPopularArticles(Date startTime, Date endTime, int limit) {
    //     // 实现获取热门文章内容的逻辑
    //     // 需要在 DAO 层提供相应的方法
    //     return null;
    // }
}
