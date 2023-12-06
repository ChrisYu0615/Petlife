package com.petlife.member.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.petlife.member.dao.MemberDAO;
import com.petlife.member.entity.Member;
import com.petlife.util.HibernateUtil;

public class MemberDAOImpl2 implements MemberDAO {
	private SessionFactory factory;

	public MemberDAOImpl2() {
		factory = HibernateUtil.getSessionFactory();
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public Integer add(Member user) {
		Integer id = (Integer) getSession().save(user);
		return id;
	}

	@Override
	public Integer delete(Integer userId) {
		Member user = getSession().get(Member.class, userId);
		if (user != null) {
			getSession().delete(user);
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public Integer update(Member user) {
		try {
			getSession().update(user);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public Member findByPK(Integer userId) {
		getSession().clear();
		return getSession().get(Member.class, userId);
	}

	@Override
	public List<Member> getAll() {
		return getSession().createQuery("from User", Member.class).getResultList();
	}

	@Override
	public Long getTotal() {
		return getSession().createQuery("select count(*) from User", Long.class).getSingleResult();
	}

	@Override
	public Member findUserByUserNickname(String userNickname) {
		List<Member> users = getSession().createQuery("from User where userNickName=:userNickName", Member.class)
				.setParameter("userNickName", userNickname).getResultList();

		if (users.size() > 0) {
			return users.get(0);
		}
		return null;
	}

	@Override
	public Member findUserByUserAccount(String userAccount) {
		List<Member> users = getSession().createQuery("from User where userAcct=:userAcct", Member.class)
				.setParameter("userAcct", userAccount).getResultList();
		
		if (users.size() > 0) {
			return users.get(0);
		}
		return null;
	}
}
