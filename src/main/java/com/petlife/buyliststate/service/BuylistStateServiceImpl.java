package com.petlife.buyliststate.service;

import java.util.List;

import com.petlife.buyliststate.dao.BuylistStateDAO;
import com.petlife.buyliststate.dao.BuylistStateDAOImpl;
import com.petlife.buyliststate.entity.BuylistState;



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
