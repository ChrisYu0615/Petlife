package com.petlife.advertisement.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.petlife.advertisement.entity.Advertisement;
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
		Session session = getSession();
		try {
			session.beginTransaction();
			Integer id = (Integer) session.save(advertisement);
			session.getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
		return -1;

//		return (Integer) getSession().save(advertisement);
	}

	@Override
	public Integer delete(Integer advertisementId) {
		Session session = getSession();
		try {
			session.beginTransaction();
			Advertisement advertisement = getSession().get(Advertisement.class, advertisementId);
			if (advertisement != null) {
				getSession().delete(advertisement);
			}
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
		return -1;

//		Advertisement advertisement = getSession().get(Advertisement.class, advertisementId);
//		if (advertisement != null) {
//			getSession().delete(advertisement);
//			return 1;
//		}
//		return -1;
	}

	@Override
	public Integer update(Advertisement advertisement) {
		Session session = getSession();
		try {
			session.beginTransaction();
			session.update(advertisement);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
		return -1;

//		try {
//			getSession().update(advertisement);
//			return 1;
//		} catch (Exception e) {
//			return -1;
//		}
	}

	@Override
	public Advertisement findByPK(Integer advertisementId) {
		Session session = getSession();

		try {
			session.beginTransaction();
			Advertisement advertisement = session.get(Advertisement.class, advertisementId);
			session.getTransaction().commit();
			return advertisement;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
		return null;

//		return getSession().get(Advertisement.class, advertisementId);
	}

	@Override
	public List<Advertisement> getAll() {
		Session session = getSession();
		try {
			session.beginTransaction();
			List<Advertisement> advertisements = session.createQuery("from Advertisement", Advertisement.class)
					.getResultList();
			session.getTransaction().commit();
			return advertisements;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
		return null;

//		return getSession().createQuery("from Advertisement", Advertisement.class).getResultList();
	}

}
