package com.petlife.mall.service.impl;

import java.util.List;

import com.petlife.mall.dao.BuylistDAO;
import com.petlife.mall.dao.impl.BuylistDAOImpl;
import com.petlife.mall.entity.Buylist;
import com.petlife.mall.service.BuylistService;

public class BuylistServiceImpl implements BuylistService {
	private BuylistDAO dao;

	public BuylistServiceImpl() {
		dao = new BuylistDAOImpl();
	}

	@Override
	public Buylist addBuylist(Buylist buylist) {
		Integer id = dao.add(buylist);
		buylist = dao.findByPK(id);
		return buylist;
	}

	@Override
	public Integer deleteBuylist(Integer buylistId) {
		return dao.delete(buylistId);
	}

	@Override
	public Integer updateBuylist(Buylist buylist) {
		return dao.update(buylist);
	}

	@Override
	public Buylist getBuylistByBuylistId(Integer buylistId) {
		return dao.findByPK(buylistId);
	}

	@Override
	public List<Buylist> getAllBuylists() {
		return dao.getAll();
	}
}
