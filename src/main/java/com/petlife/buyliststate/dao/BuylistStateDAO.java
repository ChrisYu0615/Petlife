package com.petlife.buyliststate.dao;

import java.util.List;

import com.petlife.buyliststate.entity.BuylistState;



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
