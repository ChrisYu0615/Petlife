package com.petlife.mall.dao;

import java.util.List;

import com.petlife.mall.entity.BuylistState;



public interface BuylistStateDAO {
	
	Integer add (BuylistState buyliststate);
	
	Integer delete (Integer buylistStateId);
	
	Integer update (BuylistState buyliststate);
	
	BuylistState findByPK(Integer buylistStateId);
	
	List<BuylistState> getAll();
	
//	List<Buyliststate> getByCompositeQuery(Map<String, String> map);

//	List<Buyliststate> getAll(int currentPage);
	
//	long getTotal();
}
