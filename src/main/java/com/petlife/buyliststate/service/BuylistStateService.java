package com.petlife.buyliststate.service;

import java.util.List;

import com.petlife.buyliststate.entity.BuylistState;

public interface BuylistStateService {
	// 增
	BuylistState addBuylistState(BuylistState buylistState);

	// 刪
	Integer deleteBuylistState(Integer buylistStateId);

	// 改
	Integer updateBuylistState(BuylistState buylistState);

	// 查(單個)
	BuylistState getBuylistStateByBuylistStateId(Integer buylistStateId);

	// 查(多個)
	List<BuylistState> getAllBuylistStates();
}
