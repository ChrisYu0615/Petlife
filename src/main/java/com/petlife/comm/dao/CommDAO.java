package com.petlife.comm.dao;

import java.util.List;

import com.petlife.comm.entity.Comm;

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
//	public Comm findByPriceRange(BigDecimal max_price, BigDecimal min_price);
	
	// 查全部
	public List<Comm> getAll();
}
