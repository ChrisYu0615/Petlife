package com.petlife.mall.dao;

import java.util.List;

import com.petlife.mall.entity.Buylist;
import com.petlife.mall.entity.Comm;

public interface CommDAO {
	// 增
	public Integer add(Comm comm);
	
	// 刪
	public Integer delete(Integer commId);
	
	// 改
	public Integer update(Comm comm);
	
	// 查
	public Comm findByPk(Integer commId);
	
//	public Comm findByUserId(Integer userId);
//	
//	public Comm findByCatId(Integer comm_cat_id);
//	
//	public Comm findByPriceRange(Integer max_price, Integer min_price);
	
	// 查全部
	public List<Comm> getAll();
	
	public List<Comm> getAll(String memberId);
	//根據訂單狀態ID查詢
	List<Comm> getCommsByState(Integer commState, Integer sellerId);
	
	//瀏覽數
	 public void updateView(Integer commId) ;
		

//	public List<Comm> getCommImg(Integer commId);
}
