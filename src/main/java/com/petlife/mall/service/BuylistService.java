package com.petlife.mall.service;

import java.util.List;

import com.petlife.mall.entity.Buylist;

public interface BuylistService {

	// 增
	Buylist addBuylist(Buylist buylist);

	// 刪
	Integer deleteBuylist(Integer buylistId);

	// 改
	Integer updateBuylist(Buylist buylist);

	// 查(單個)
	Buylist getBuylistByBuylistId(Integer buylistId);

	// 查(多個)
	List<Buylist> getAllBuylists();
}
