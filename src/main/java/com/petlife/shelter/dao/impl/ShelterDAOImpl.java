package com.petlife.shelter.dao.impl;

import static com.petlife.util.Constants.PAGE_MAX_RESULT;

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

import com.petlife.admin.dao.AcctStateDAO;
import com.petlife.admin.dao.impl.AcctStateDAOImpl;
import com.petlife.admin.entity.AcctState;
import com.petlife.shelter.dao.ShelterDAO;
import com.petlife.shelter.entity.Shelter;
import com.petlife.util.HibernateUtil;

public class ShelterDAOImpl implements ShelterDAO {
	// SessionFactory 為 thread-safe，可宣告為屬性讓請求執行緒們共用
	private SessionFactory factory;

	public ShelterDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}

	// Session 為 not thread-safe，所以此方法在各個增刪改查方法裡呼叫
	// 以避免請求執行緒共用了同個 Session
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(Shelter entity) {
		// 回傳給 service 剛新增成功的自增主鍵值
		return (Integer) getSession().save(entity);
	}

	@Override
	public int update(Shelter entity) {
		getSession().flush();
		try {
			getSession().update(entity);
			System.out.println("修改DAO");
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int delete(Integer id) {
		Shelter shelter = getSession().get(Shelter.class, id);
		if (shelter != null) {
			getSession().delete(shelter);
			// 回傳給 service，1代表刪除成功
			return 1;
		} else {
			// 回傳給 service，-1代表刪除失敗
			return -1;
		}
	}
//1215更新 詩涵
	@Override
	public Shelter getById(Integer shelterId) {
		System.out.println("ShelterDAOImpl: getById Entry");
		System.out.println(shelterId);
		Shelter Shelter = getSession().createQuery("from Shelter where shelterId =" + shelterId, Shelter.class)
				.uniqueResult();
		return Shelter;
	}

	@Override
	public Shelter getByName(String shelterName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Shelter getByAddress(String shelterAddress) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Shelter getByAccount(String shelterAcct) {
		List<Shelter> shelters = getSession().createQuery("from Shelter where shelterAcct=:shelterAcct", Shelter.class)
				.setParameter("shelterAcct", shelterAcct).getResultList();

		if (shelters.size() > 0) {
			return shelters.get(0);
		}
		return null;
	}

	@Override
	public List<Shelter> getAll(String... conditions) {
		if (conditions != null && conditions.length > 0) {
			AcctStateDAO acctStateDAO = new AcctStateDAOImpl();
			AcctState acctState = acctStateDAO.findByPK(4);
			if ("verified".equals(conditions[0])) {
				return getSession().createQuery("from Shelter where acctState!=:acctState", Shelter.class)
						.setParameter("acctState", acctState).getResultList();
			} else if ("unverified".equals(conditions[0])) {
				return getSession().createQuery("from Shelter where acctState=:acctState", Shelter.class)
						.setParameter("acctState", acctState).getResultList();
			}
		}
		return getSession().createQuery("from Shelter", Shelter.class).getResultList();
	}

	@Override
	public List<Shelter> getByCompositeQuery(Map<String, String> map) {
		if (map.size() == 0)
			return getAll();

		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<Shelter> criteria = builder.createQuery(Shelter.class);
		Root<Shelter> root = criteria.from(Shelter.class);

		List<Predicate> predicates = new ArrayList<>();

		for (Map.Entry<String, String> row : map.entrySet()) {
			if ("shelterName".equals(row.getKey())) {
				predicates.add(builder.like(root.get("shelterName"), "%" + row.getValue() + "%"));
			}

			if ("shelterAddress".equals(row.getKey())) {
				predicates.add(builder.like(root.get("shelterAddress"), "%" + row.getValue() + "%"));
			}
		}

		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get("shelterId")));
		TypedQuery<Shelter> query = getSession().createQuery(criteria);

		return query.getResultList();
	}

	@Override
	public List<Shelter> getAll(int currentPage) {
		int first = (currentPage - 1) * PAGE_MAX_RESULT;
		return getSession().createQuery("from Shelter", Shelter.class).setFirstResult(first)
				.setMaxResults(PAGE_MAX_RESULT).list();
	}

	@Override
	public long getTotal() {
		return getSession().createQuery("select count(*) from Shelter", Long.class).uniqueResult();
	}

	@Override
	public Shelter getByAccountAndPassword(String shelterAcct, String shelterPwd) {
		List<Shelter> shelters = getSession()
				.createQuery("from Shelter where shelterAcct=:shelterAcct and shelterPwd=:shelterPwd", Shelter.class)
				.setParameter("shelterAcct", shelterAcct).setParameter("shelterPwd", shelterPwd).getResultList();

		if (shelters.size() > 0) {
			return shelters.get(0);
		}
		return null;
	}
}
