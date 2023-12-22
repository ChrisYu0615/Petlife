package com.petlife.mall.service.impl;

import java.util.List;

import com.petlife.mall.dao.BuylistDetailsDAO;
import com.petlife.mall.dao.impl.BuylistDetailsDAOImpl;
import com.petlife.mall.entity.BuylistDetails;
import com.petlife.mall.service.BuylistDetailsService;

public class BuylistDetailsServiceImpl implements BuylistDetailsService {
	private BuylistDetailsDAO dao;

	public BuylistDetailsServiceImpl() {
		dao = new BuylistDetailsDAOImpl();
	}

	@Override
	public BuylistDetails addBuylistDetails(BuylistDetails buylistDetails) {
		Integer id = dao.add(buylistDetails);
		buylistDetails = dao.findByPK(id);
		return buylistDetails;
	}

	@Override
	public Integer deleteBuylistDetails(Integer buylistDetailsId) {
		return dao.delete(buylistDetailsId);
	}

	@Override
	public Integer updateBuylistDetails(BuylistDetails buylistDetails) {
		return dao.update(buylistDetails);
	}

	@Override
	public BuylistDetails getBuylistDetailsByBuylistDetailsId(Integer buylistDetailsId) {
		return dao.findByPK(buylistDetailsId);
	}
	
	@Override
	public List<BuylistDetails> getAllBuylistDetailss() {
		return dao.getAll();
	}

	@Override
	public List<BuylistDetails> getAllBuylistDetailss(Integer buylistId) {
		return dao.getAll(buylistId);
	}
}
