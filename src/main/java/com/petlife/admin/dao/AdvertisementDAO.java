package com.petlife.admin.dao;

import java.util.List;

import com.petlife.admin.entity.Advertisement;

public interface AdvertisementDAO {
	// 增
	public Integer add(Advertisement advertisement);

	// 刪
	public Integer delete(Integer advertisementId);

	// 改
	public Integer update(Advertisement advertisement);

	// 查(單個)
	public Advertisement findByPK(Integer advertisementId);

	// 查(多個)
	public List<Advertisement> getAll();
}
