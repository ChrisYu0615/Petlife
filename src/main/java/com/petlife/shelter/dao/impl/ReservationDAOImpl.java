package com.petlife.shelter.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.petlife.shelter.dao.ReservationDAO;
import com.petlife.shelter.entity.Reservation;
import com.petlife.shelter.entity.Shelter;
import com.petlife.util.HibernateUtil;

public class ReservationDAOImpl implements ReservationDAO {
	
	private SessionFactory factory;
	
	public ReservationDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}
	
	private Session getSession() {
		return factory.getCurrentSession();
	}
	
	@Override
	public int insert(Reservation entity) {
		// 回傳給 service 剛新增成功的自增主鍵值
		return (Integer) getSession().save(entity);
	}

	@Override
	public int update(Reservation entity) {
		try {
			getSession().update(entity);
			System.out.println("修改DAO");
			return 1;
		}catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int delete(Integer id) {
		Reservation reservation = getSession().get(Reservation.class, id);
		if(reservation != null) {
			getSession().delete(reservation);
			return 1;
		}else {
			return -1;
		}
	}

	@Override
	public Reservation getById(Integer id) {
		return getSession().get(Reservation.class, id);
	}

	@Override
	public Reservation getByResType(Integer resType) {
		return getSession().get(Reservation.class, resType);
	}

	@Override
	public Reservation getByShelterBookingId(Integer shelterBookingId) {
		return getSession().get(Reservation.class, shelterBookingId);
	}

	@Override
	public Reservation getByPetId(Integer petId) {
		return getSession().get(Reservation.class, petId);
	}

	@Override
	public List<Reservation> getAll() {
		return getSession().createQuery("from Reservation", Reservation.class).list();
	}

	@Override
	public List<Reservation> getResByCompositeQuery(Map<String, String> map) {
		if (map.size() == 0)
			return getAll();
		
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<Reservation> criteria = builder.createQuery(Reservation.class);
		Root<Reservation> root = criteria.from(Reservation.class);
		//	Predicate是JPA套件代表查詢條件
		System.out.println("Res複合查詢DAO");
		List<Predicate> predicates = new ArrayList<>();
		
		for (Map.Entry<String, String> row : map.entrySet()) {
//			如果要新增其他條件要在這裡寫
//			if ("shelterBookingId".equals(row.getKey())) {
//				predicates.add(builder.like(root.get("shelterBookingId"), "%" + row.getValue() + "%"));
//			}

			if ("resType".equals(row.getKey())) {
				predicates.add(builder.equal(root.get("resType"),row.getValue()));
			}
		}
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get("resId")));
		TypedQuery<Reservation> query = getSession().createQuery(criteria);
		
		return query.getResultList();
	}
	
	

	@Override
	public long getTotal() {
		return getSession().createQuery("select count(*) from Reservation", Long.class).uniqueResult();
	}

}
