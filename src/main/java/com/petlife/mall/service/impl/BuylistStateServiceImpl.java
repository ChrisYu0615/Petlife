package com.petlife.mall.service.impl;

import java.util.List;

import com.petlife.mall.dao.BuylistStateDAO;
import com.petlife.mall.dao.impl.BuylistStateDAOImpl;
import com.petlife.mall.entity.BuylistState;
import com.petlife.mall.service.BuylistStateService;



public class BuylistStateServiceImpl implements BuylistStateService {
	private BuylistStateDAO dao;

	public BuylistStateServiceImpl() {
		dao = new BuylistStateDAOImpl();
	}

	@Override
	public BuylistState addBuylistState(BuylistState buylistState) {
		Integer id = dao.add(buylistState);
		buylistState = dao.findByPK(id);
		return buylistState;
	}

	@Override
	public Integer deleteBuylistState(Integer buylistStateId) {
		return dao.delete(buylistStateId);
	}

	@Override
	public Integer updateBuylistState(BuylistState buylistState) {
		return dao.update(buylistState);
	}

	@Override
	public BuylistState getBuylistStateByBuylistStateId(Integer buylistStateId) {
		return dao.findByPK(buylistStateId);
	}

	@Override
	public List<BuylistState> getAllBuylistStates() {
		return dao.getAll();
	}
}
