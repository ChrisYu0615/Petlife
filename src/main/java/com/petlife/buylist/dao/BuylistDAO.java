package com.petlife.buylist.dao;

import java.util.List;

import com.petlife.buylist.entity.Buylist;

public interface BuylistDAO {
	//增
	public Integer add(Buylist buylist);
	//刪
	public Integer delete(Integer buylistId);
	//改
	public Integer update(Buylist buylist);
	//查(單個)
	public Buylist findByPK(Integer buylistId);
	//查(多個)
	public List<Buylist> getAll();
}
