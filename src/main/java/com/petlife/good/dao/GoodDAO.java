package com.petlife.good.dao;



import java.util.List;
import com.petlife.good.entity.Good;

public interface GoodDAO {
    int add(Good good);
    int update(Good good);
    int delete(Integer goodId);
    Good findByPK(Integer goodId);
    List<Good> getAll();
}

