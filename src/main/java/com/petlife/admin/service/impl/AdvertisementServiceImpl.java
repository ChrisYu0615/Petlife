package com.petlife.admin.service.impl;

import java.util.List;

import com.petlife.admin.dao.AdvertisementDAO;
import com.petlife.admin.dao.impl.AdvertisementDAOImpl;
import com.petlife.admin.entity.Advertisement;
import com.petlife.admin.service.AdvertisementService;

public class AdvertisementServiceImpl implements AdvertisementService {
	private AdvertisementDAO dao;

	public AdvertisementServiceImpl() {
		dao = new AdvertisementDAOImpl();
	}

	@Override
	public void addAdvertisement(Advertisement advertisement) {
		dao.add(advertisement);
	}

	@Override
	public Integer deleteAdvertisement(Integer advertisementId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateAdvertisement(Advertisement advertisement) {
		dao.update(advertisement);
	}

	@Override
	public Advertisement getAdvertisementById(Integer advertisementId) {
		return dao.findByPK(advertisementId);
	}

	@Override
	public List<Advertisement> getAll() {
		return dao.getAll();
	}

	@Override
	public List<Advertisement> getAllWithActived() {
		return dao.getAllActived();
	}

}
