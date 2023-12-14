package com.petlife.seller.service;

import java.util.List;
import java.util.Map;

import com.petlife.seller.entity.Seller;

public interface SellerService {

	// 增
	Seller addSeller(Seller seller);

	// 刪
	Integer deleteSeller(Integer sellerId);

	// 改
	Integer updateSeller(Seller seller);

	// 查(單個)
	Seller getSellerBySellerId(Integer sellerId);

	// 查詢賣家賣場名稱是否重複
	boolean existSellerShopname(String shopname);

	// 查詢賣家帳號是否重複
	boolean existSellerAccount(String sellerAccount);

	// 查(多個)
	List<Seller> getAllSellers(String... conditions);

	Map<String, Integer> sellerLogin(String sellerAcct, String sellerPwd);

	String getNewPwd(String sellerAcct);
}
