package com.petlife.pet.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.petlife.pet.entity.PetPhoto;
import com.petlife.util.HibernateUtil;
import com.petlife.util.Idao;




public class PetPhotoDAOImpl implements Idao<PetPhoto> {
private SessionFactory factory;
	
	public PetPhotoDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}
	
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(PetPhoto entity) {
		try {
			

			getSession().save(entity);

			System.out.println("no");

			return 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			getSession().getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public PetPhoto update(PetPhoto entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		System.out.println("PetPhotoDAOImpl : delete Entry");
		Session session = getSession();
		PetPhoto petPhoto = session.get(PetPhoto.class, id);
		if (petPhoto != null) session.delete(petPhoto);
//		PetPhoto petPhoto = new PetPhoto();
//		petPhoto.setPhotoId(id);
//		session.delete(petPhoto);
	}

	@Override
	public PetPhoto getById(Integer id) {
		try {
			PetPhoto petPhoto = getSession().createQuery("from PetPhoto where photoId =" + id, PetPhoto.class)
					.uniqueResult();
			return petPhoto;
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
			getSession().getTransaction().rollback();
		}
		return null;
	}

	public List<PetPhoto> getAll(Integer petId) {
		getSession().createQuery("from PetPhoto where pet.petId=:petId", PetPhoto.class).setParameter("petId", petId)
				.getResultList();
		return null;
	}

	@Override
	public List<PetPhoto> getByCompositeQuery(Map<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PetPhoto> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}