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
			
		return  1;
	}catch(Exception e) {
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public PetPhoto getById(Integer id) {
		try {
			System.out.println(id);
			PetPhoto petPhoto = getSession().createQuery("from PetPhoto where photoId =" + id, PetPhoto.class).uniqueResult();
			return petPhoto;
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
			getSession().getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<PetPhoto> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PetPhoto> getByCompositeQuery(Map<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}
	
}