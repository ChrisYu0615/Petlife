package com.petlife.shelter.dao.impl;

import java.sql.Date;
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

import com.petlife.shelter.entity.ShelterBooking;
import com.petlife.util.HibernateUtil;
import com.petlife.util.Idao;



public class ShelterBookingDAOImpl  implements Idao<ShelterBooking>{
	
	private SessionFactory factory;

	public ShelterBookingDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(ShelterBooking entity) {
		try {
			System.out.println("ShelterBookingDAOImpl : insert Entry");
			Session session = getSession();
			session.save(entity);
			return entity.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			getSession().getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public ShelterBooking update(ShelterBooking entity) {
		try {
			System.out.println("ShelterBookingDAOImpl : update Entry");
			getSession().update(entity);
			return entity;
		}catch(Exception e) {
			e.getStackTrace();
			getSession().getTransaction().rollback();
		}
		return entity;
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ShelterBooking getById(Integer id) {
		try {
			System.out.println("ShelterBookingDAOImpl: Entry");
			System.out.println("ShelterBookingDAOImpl: id = " + id);
			ShelterBooking shelterBooking = getSession().createQuery("from ShelterBooking where id =" + id, ShelterBooking.class).uniqueResult();
			return shelterBooking;
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
			getSession().getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<ShelterBooking> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShelterBooking> getByCompositeQuery(Map<String, String> map) {
		try {
			System.out.println("ShelterBookingDAOImpl: getByCompositeQuery Entry");
			if (map.size() == 0) {
				return getAll();
			}
			CriteriaBuilder builder = getSession().getCriteriaBuilder();
			CriteriaQuery<ShelterBooking> criteria = builder.createQuery(ShelterBooking.class);
			Root<ShelterBooking> root = criteria.from(ShelterBooking.class);
			

			List<Predicate> predicates = new ArrayList<>();

			
			for (Map.Entry<String, String> row : map.entrySet()) {
				System.out.println("ShelterBookingDAOImpl: " + row.getKey()+ " : " + row.getValue());
				
				if ("checkbookingstart".equals(row.getKey())) {
					predicates.add(builder.lessThanOrEqualTo(root.get("shelterBookingDate"), Date.valueOf(row.getValue())));
				}
				if ("checkbookingend".equals(row.getKey())) {
					predicates.add(builder.greaterThanOrEqualTo(root.get("shelterBookingDate"), Date.valueOf(row.getValue())));
				}
					

			}
			criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
			criteria.orderBy(builder.asc(root.get("id")));
			TypedQuery<ShelterBooking> query = getSession().createQuery(criteria);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}

	}
}
