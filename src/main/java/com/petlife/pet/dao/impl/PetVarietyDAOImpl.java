package com.petlife.pet.dao.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;



import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.petlife.pet.entity.PetVariety;
import com.petlife.util.HibernateUtil;
import com.petlife.util.Idao;







public class PetVarietyDAOImpl implements Idao<PetVariety> {
	
	private SessionFactory factory;
	
	public PetVarietyDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}
	
	private Session getSession() {
		return factory.getCurrentSession();
	}
	
	public int insert(PetVariety petVariety){
		try {
			return  (Integer) getSession().save(petVariety);
		}catch(Exception e) {
			getSession().getTransaction().rollback();
		}
		return -1;
	}
	
	public PetVariety update(PetVariety petVariety) {
		try {
			
			getSession().update(petVariety);
			return	petVariety;
		}catch(Exception e) {
			getSession().getTransaction().rollback();
		}
			return petVariety;
	}
	
	public void delete(Integer id) {
		
	}
	
	public PetVariety getById(Integer id) {
		
		try {
			
			PetVariety petVariety = getSession().createQuery("from PetVariety where id =" + id, PetVariety.class).uniqueResult();
			return  petVariety;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
		return null;
	}
		
		
		

	public List<PetVariety> getAll(){
		return getSession().createQuery("from PetVariety", PetVariety.class).list();
	}
	
	
	public List<PetVariety> getByCompositeQuery(Map<String, String> map){
		
		try {
			if (map.size() == 0)
				return getAll();

			CriteriaBuilder builder = getSession().getCriteriaBuilder();
			CriteriaQuery<PetVariety> criteria = builder.createQuery(PetVariety.class);
			Root<PetVariety> root = criteria.from(PetVariety.class);

			List<Predicate> predicates = new ArrayList<>();
			for (Map.Entry<String, String> row : map.entrySet()) {

				if ("type".equals(row.getKey())) {
					predicates.add(builder.equal(root.get("type"), row.getValue()));
				}

				if ("variety".equals(row.getKey())) {
					predicates.add(builder.like(root.get("variety"), "%" + row.getValue() + "%"));
				}

			}
			criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
			criteria.orderBy(builder.asc(root.get("id")));
			TypedQuery<PetVariety> query = getSession().createQuery(criteria);

			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
}
