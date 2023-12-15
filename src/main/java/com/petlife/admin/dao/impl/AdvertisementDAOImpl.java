package com.petlife.admin.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.petlife.admin.dao.AdvertisementDAO;
import com.petlife.admin.entity.Advertisement;
import com.petlife.util.HibernateUtil;

public class AdvertisementDAOImpl implements AdvertisementDAO {
	private SessionFactory factory;

	public AdvertisementDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public Integer add(Advertisement advertisement) {
		return (Integer) getSession().save(advertisement);
	}

	@Override
	public Integer delete(Integer advertisementId) {
		Advertisement advertisement = getSession().get(Advertisement.class, advertisementId);
		if (advertisement != null) {
			getSession().delete(advertisement);
			return 1;
		}
		return -1;
	}

	@Override
	public Integer update(Advertisement advertisement) {
		try {
			getSession().update(advertisement);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public Advertisement findByPK(Integer advertisementId) {
		return getSession().get(Advertisement.class, advertisementId);
	}

	@Override
	public List<Advertisement> getAll() {
		return getSession().createQuery("from Advertisement", Advertisement.class).getResultList();
	}

}
