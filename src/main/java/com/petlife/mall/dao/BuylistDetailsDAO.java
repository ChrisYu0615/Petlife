package com.petlife.mall.dao;

import java.util.List;

import com.petlife.mall.entity.Buylist;
import com.petlife.mall.entity.BuylistDetails;

public interface BuylistDetailsDAO {
	//增
	public Integer add(BuylistDetails buylistDetails);
	//刪
	public Integer delete(Integer buylistDetails);
	//改
	public Integer update(BuylistDetails buylistDetails);
	//查(單個)
	public BuylistDetails findByPK(Integer buylistDetailsId);
	//查(多個)
	public List<BuylistDetails> getAll();
}
