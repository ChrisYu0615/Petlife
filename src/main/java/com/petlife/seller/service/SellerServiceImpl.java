package com.petlife.seller.service;

import java.util.List;

import com.petlife.seller.dao.SellerDAO;
import com.petlife.seller.dao.SellerDAOImpl;
import com.petlife.seller.entity.Seller;

public class SellerServiceImpl implements SellerService {
	private SellerDAO dao;

	public SellerServiceImpl() {
		dao = new SellerDAOImpl();
	}

	@Override
	public Seller addSeller(Seller seller) {
		Integer id = dao.add(seller);
		seller = dao.findByPK(id);
		return seller;
	}

	@Override
	public Integer deleteSeller(Integer sellerId) {
		return dao.delete(sellerId);
	}

	@Override
	public Integer updateSeller(Seller seller) {
		return dao.update(seller);
	}

	@Override
	public Seller getSellerBySellerId(Integer sellerId) {
		return dao.findByPK(sellerId);
	}

	@Override
	public List<Seller> getAllSellers() {
		return dao.getAll();
	}

	// 查詢賣家賣場名稱是否重複
	@Override
	public boolean existSellerShopname(String shopname) {
		if (dao.findSellerBySellerShopname(shopname) != null) {
			return true;
		}
		return false;
	}

	// 查詢賣家帳號是否重複
	@Override
	public boolean existSellerAccount(String sellerAccount) {
		if (dao.findSellerBySellerAccount(sellerAccount) != null) {
			return true;
		}
		return false;
	}

}
