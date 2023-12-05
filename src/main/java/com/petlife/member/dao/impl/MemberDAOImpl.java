package com.petlife.member.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.petlife.member.dao.MemberDAO;
import com.petlife.member.entity.Member;
import com.petlife.util.HibernateUtil;

public class MemberDAOImpl implements MemberDAO {

	@Override
	public Integer add(Member user) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			Integer id = (Integer) session.save(user);

			session.getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
		return -1;
	}

	@Override
	public Integer delete(Integer userId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			Member user = session.get(Member.class, userId);
			if (user != null) {
				session.delete(user);
			}
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
		return -1;
	}

	@Override
	public Integer update(Member user) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			session.update(user);

			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
		return -1;
	}

	@Override
	public Member findByPK(Integer userId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			Member user = session.get(Member.class, userId);

			session.getTransaction().commit();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
		return null;
	}

	@Override
	public List<Member> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			List<Member> userList = session.createQuery("from User", Member.class).getResultList();

			session.getTransaction().commit();
			return userList;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
		return null;
	}

	@Override
	public Long getTotal() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			Long count = session.createQuery("select count(*) from User", Long.class).getSingleResult();

			session.getTransaction().commit();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
		return 0L;
	}

	@Override
	public Member findUserByUserNickname(String userNickname) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<Member> user = session.createQuery("from User where userNickName=:userNickName", Member.class)
					.setParameter("userNickName", userNickname).getResultList();
			session.getTransaction().commit();

			if (user.size() > 0) {
				return user.get(0);
			}
			return null;

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
		return null;
	}

	@Override
	public Member findUserByUserAccount(String userAccount) {
		// TODO Auto-generated method stub
		return null;
	}

}
