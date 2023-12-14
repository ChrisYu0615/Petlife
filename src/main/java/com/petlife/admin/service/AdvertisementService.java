package com.petlife.admin.service;

import java.util.List;

import com.petlife.admin.entity.Advertisement;

public interface AdvertisementService {
	// 增
	void addAdvertisement(Advertisement advertisement);

	// 刪
	Integer deleteAdvertisement(Integer advertisementId);

	// 改
	void updateAdvertisement(Advertisement advertisement);

	// 查(單個)
	Advertisement getAdvertisementById(Integer advertisementId);

	// 改(全部)
	List<Advertisement> getAll();
}
