package com.petlife.comm.service;

import java.util.List;

import com.petlife.comm.entity.Comm;

public interface CommService {
	
	// 增加商品
	Comm addComm(Comm comm);
	
	// 刪除商品
	Integer deleteComm(Integer commId);
	
	// 更改商品
	Comm updateComm(Comm comm);
	
	// 查找商品(單個)
	Comm getByCommId(Integer commId);
	
	// 查找商品(多個)
	List<Comm> getAllComm();
	
	// 以下是進階的查找商品方式
	// 1. 藉由分類來找
	// 2. 藉由價格來找
	// 3. 藉由賣家來找
	
}
