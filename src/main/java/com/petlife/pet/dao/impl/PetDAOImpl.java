package com.petlife.pet.dao.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.petlife.pet.entity.Pet;
import com.petlife.shelter.entity.Shelter;
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
			Session session = getSession();
			session.save(pet);
			return pet.getId();
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
			System.out.println("PetDAOImpl: Entry");
			System.out.println("PetDAOImpl: id = " + id);
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
			System.out.println("PetDAOImpl: getByCompositeQuery Entry");
			if (map.size() == 0) {
				return getAll();
			}
			CriteriaBuilder builder = getSession().getCriteriaBuilder();
			CriteriaQuery<Pet> criteria = builder.createQuery(Pet.class);
			Root<Pet> root = criteria.from(Pet.class);			

			List<Predicate> predicates = new ArrayList<>();
	
			for (Map.Entry<String, String> row : map.entrySet()) {
				System.out.println("PetDAOImpl: " + row.getKey()+ " : " + row.getValue());
				// 種類
                if ("type".equals(row.getKey())) {
                     predicates.add(builder.equal(root.get("variety").get("type"), row.getValue()));
                }
				// 性別
				if ("petGender".equals(row.getKey())) {
					predicates.add(builder.equal(root.get("petGender"), row.getValue()));
				}
				// 品種
				if ("petVarietyId".equals(row.getKey())) {
					predicates.add(builder.equal(root.get("petVariety"), row.getValue()));
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
				if("pet_variety".equals(row.getKey())) {
					predicates.add(builder.equal(root.get("petVariety"), row.getValue()));
				}
				if("shelter_name".equals(row.getKey())) {
					System.out.println("get shelter");
					 predicates.add(builder.equal(root.get("shelter").get("shelterName"), row.getValue()));
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
