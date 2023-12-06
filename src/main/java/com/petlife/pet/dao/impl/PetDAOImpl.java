package com.petlife.pet.dao.impl;

import java.math.BigDecimal;
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

import com.petlife.pet.entity.Pet;
import com.petlife.util.HibernateUtil;
import com.petlife.util.Idao;



public class PetDAOImpl implements Idao<Pet> {

	private SessionFactory factory;

	public PetDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(Pet pet) {
		try {
			getSession().save(pet);
			System.out.println("no");

			return 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			getSession().getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public Pet update(Pet entity) {
		try {
			getSession().update(entity);
			System.out.println("ok");
			return entity;
		} catch (Exception e) {
			System.out.println("3");
			getSession().getTransaction().rollback();
		}
		return entity;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Pet getById(Integer id) {
		try {
			System.out.println(id);
			Pet pet = getSession().createQuery("from Pet where id =" + id, Pet.class).uniqueResult();
			return pet;
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
			getSession().getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<Pet> getAll() {
		System.out.println("getAll Entry");
		return getSession().createQuery("from Pet", Pet.class).list();
	}

	@Override
	public List<Pet> getByCompositeQuery(Map<String, String> map) {
		try {
			System.out.println("getByCompositeQuery Entry");
			if (map.size() == 0) {
				return getAll();
			}
			CriteriaBuilder builder = getSession().getCriteriaBuilder();
			CriteriaQuery<Pet> criteria = builder.createQuery(Pet.class);
			Root<Pet> root = criteria.from(Pet.class);

			List<Predicate> predicates = new ArrayList<>();
//			if (map.containsKey("start_comin_date") && map.containsKey("end_comin_date"))
//				predicates.add(builder.between(root.get("comeInDate"), Date.valueOf(map.get("start_comin_date")), Date.valueOf(map.get("end_comin_date"))));
//
//			if (map.containsKey("start_adopt_date") && map.containsKey("end_adopt_date"))
//				predicates.add(builder.between(root.get("adoptDate"), new BigDecimal(map.get("start_adopt_date")), new BigDecimal(map.get("end_adopt_date"))));
			
			for (Map.Entry<String, String> row : map.entrySet()) {
				System.out.println(row.getKey()+ " : " + row.getValue());
				// 種類
				if ("type".equals(row.getKey())) {
					predicates.add(builder.equal(root.get("type"), row.getValue()));
				}
				// 性別
				if ("petGender".equals(row.getKey())) {
					predicates.add(builder.equal(root.get("petGender"), row.getValue()));
				}
				// 品種
				if ("petVarietyId".equals(row.getKey())) {
					predicates.add(builder.equal(root.get("petVarietyId"), row.getValue()));
				}
				// 收容編號
				if ("petNum".equals(row.getKey())) {
					predicates.add(builder.equal(root.get("petNum"), row.getValue()));
				}

				// insert 驗證用的收容所id
				if ("shelterId".equals(row.getKey())) {
					predicates.add(builder.equal(root.get("shelterId"), row.getValue()));
				}
				
				if ("come_in_date_start".equals(row.getKey())) {
					predicates.add(builder.greaterThanOrEqualTo(root.get("comeInDate"), Date.valueOf(row.getValue())));
				}
				if ("come_in_date_end".equals(row.getKey())) {
					predicates.add(builder.lessThanOrEqualTo(root.get("comeInDate"), Date.valueOf(row.getValue())));
				}
				if ("adopt_date_start".equals(row.getKey())) {
					predicates.add(builder.greaterThanOrEqualTo(root.get("adoptDate"), Date.valueOf(row.getValue())));
				}
				if ("adopt_date_end".equals(row.getKey())) {
					predicates.add(builder.lessThanOrEqualTo(root.get("adoptDate"), Date.valueOf(row.getValue())));
				}
					

			}
			criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
			criteria.orderBy(builder.asc(root.get("id")));
			TypedQuery<Pet> query = getSession().createQuery(criteria);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}

	}
}
