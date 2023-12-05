package com.petlife.seller.dao;

import java.util.List;

import com.petlife.seller.entity.Seller;



public interface SellerDAO {
	
	Integer add (Seller seller);
	
	Integer delete (Integer sellerId);
	
	Integer update (Seller seller);
	
	Seller findByPK(Integer sellerId);
	
	List<Seller> getAll();
	
//	List<Buyliststate> getByCompositeQuery(Map<String, String> map);

//	List<Buyliststate> getAll(int currentPage);
	
//	long getTotal();
}
