package com.petlife.mall.service;

import java.util.List;

import com.petlife.mall.entity.BuylistDetails;

public interface BuylistDetailsService {
	// 增
	BuylistDetails addBuylistDetails(BuylistDetails buylistDetails);

	// 刪
	Integer deleteBuylistDetails(Integer buylistDetailsId);

	// 改
	Integer updateBuylistDetails(BuylistDetails buylistDetails);

	// 查(單個)
	BuylistDetails getBuylistDetailsByBuylistDetailsId(Integer buylistDetailsId);

	// 查(多個)
	List<BuylistDetails> getAllBuylistDetailss(Integer buylistId);
	
}
