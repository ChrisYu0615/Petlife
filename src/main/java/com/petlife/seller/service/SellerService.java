package com.petlife.seller.service;

import java.util.List;

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

	// 查(多個)
	List<Seller> getAllSellers();
}
