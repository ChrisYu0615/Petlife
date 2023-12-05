package com.petlife.forum.dao.impl;



import java.util.List;

import org.hibernate.Session;

import com.petlife.forum.dao.ArticleDAO;
import com.petlife.forum.entity.Article;
import com.petlife.util.HibernateUtil;
public class ArticleDAOImpl implements ArticleDAO {

	@Override
	public int add(Article article) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Integer id = (Integer) session.save(article);
			session.getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}
		
	

	@Override
	public int update(Article article) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(article);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}	
//
	@Override
	public int delete(Integer articleId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Article article = session.get(Article.class, articleId);
			if (article != null) {
				session.delete(article);
			}
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
		
	}

	@Override
	public Article findByPK(Integer articleId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Article article = session.get(Article.class, articleId);
			session.getTransaction().commit();
			return article;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<Article> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<Article> list = session.createQuery("from Article", Article.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

}
