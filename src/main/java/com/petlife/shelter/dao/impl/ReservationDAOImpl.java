package com.petlife.shelter.dao.impl;

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

import com.petlife.shelter.dao.ReservationDAO;
import com.petlife.shelter.entity.Reservation;
import com.petlife.shelter.entity.ShelterBooking;
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
	public Reservation update(Reservation entity) {
		getSession().flush();
		try {
			System.out.println("ReservationDAOImpl  update: Entry");
			getSession().update(entity);

			return entity;
		} catch (Exception e) {
			return entity;
		}
	}

	@Override
	public int delete(Integer id) {
		Reservation reservation = getSession().get(Reservation.class, id);
		if (reservation != null) {
			getSession().delete(reservation);
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public Reservation getById(Integer id) {
		getSession().clear();
		try {
			System.out.println("ReservationDAOImpl : Entry");
			System.out.println("ReservationDAOImpl: id = " + id);
			Reservation reservation = getSession().createQuery("from Reservation where resId =" + id, Reservation.class)
					.uniqueResult();
			return reservation;
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
			getSession().getTransaction().rollback();
		}
		return null;
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
	public List<Reservation> getAll(Integer userId) {
		return getSession().createQuery("from Reservation where user.userId=:userId", Reservation.class)
				.setParameter("userId", userId).getResultList();
	}

	@Override
	public List<Reservation> getResByCompositeQuery(Map<String, String> map) {
		System.out.println("ReservationDAOImpl : getResByCompositeQuery Entry");
		if (map.size() == 0)
			return getAll();

		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<Reservation> criteria = builder.createQuery(Reservation.class);
		Root<Reservation> root = criteria.from(Reservation.class);
		Join<Reservation, ShelterBooking> shelterBookingJoin = root.join("shelterBooking", JoinType.INNER);

		// Predicate是JPA套件代表查詢條件

		List<Predicate> predicates = new ArrayList<>();

		for (Map.Entry<String, String> row : map.entrySet()) {
//			如果要新增其他條件要在這裡寫
			
			if ("shelterId".equals(row.getKey())) {
				predicates.add(builder.equal(root.get("shelter").get("shelterId"), row.getValue()));
			}

			if ("resTypeId".equals(row.getKey())) {
				predicates.add(builder.equal(root.get("resTypeId"), row.getValue()));
			}

			if ("search_start".equals(row.getKey())) {
				// 拿到日期 String 日期转换为 java.util.Date
				Date searchStartDate = Date.valueOf(row.getValue());
				predicates.add(
						builder.greaterThanOrEqualTo(shelterBookingJoin.get("shelterBookingDate"), searchStartDate));
			}
			if ("search_end".equals(row.getKey())) {
				// 拿到日期 String 日期转换为 java.util.Date
				Date searchStartDate = Date.valueOf(row.getValue());
				predicates
						.add(builder.lessThanOrEqualTo(shelterBookingJoin.get("shelterBookingDate"), searchStartDate));
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
