package com.petlife.forum.dao.impl;

import java.sql.Timestamp;
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
import org.hibernate.query.Query;

import com.petlife.forum.dao.ArticleDAO;
import com.petlife.forum.entity.Article;
import com.petlife.util.HibernateUtil;

public class ArticleDAOImpl implements ArticleDAO {
	private SessionFactory factory;

	public ArticleDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public Integer add(Article article) {
		Integer id = (Integer) getSession().save(article);
		return id;
	}

	@Override
	public Integer delete(Integer articleId) {
		Article article = getSession().get(Article.class, articleId);
		if (article != null) {
			getSession().delete(article);
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public Integer update(Article article) {
		try {
			getSession().update(article);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public Article findByPK(Integer articleId) {
		return getSession().get(Article.class, articleId);
	}

	@Override
	public List<Article> getAll() {
		return getSession().createQuery("from Article", Article.class).getResultList();
	}

	@Override
	public List<Article> getAll(Integer userId) {
		return getSession().createQuery("from Article where user.userId=:userId", Article.class)
				.setParameter("userId", userId).getResultList();
	}

//關鍵字搜尋
	@Override
	public List<Article> searchByKeyword(String keyword) {
		String hql = "from Article where article_name like :keyword or article_content like :keyword";
		Query<Article> query = getSession().createQuery(hql, Article.class);
		query.setParameter("keyword", "%" + keyword + "%");
		return query.getResultList();
	}

	// 複合查詢
	@Override
	public List<Article> getByCompositeQuery(Map<String, String> map) {
		if (map.size() == 0)
			return getAll();

		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<Article> criteriaQuery = builder.createQuery(Article.class);
		Root<Article> root = criteriaQuery.from(Article.class);

		// 收集查詢限制條件
		List<Predicate> predicates = new ArrayList<>();

		if (map.containsKey("article_startdate") && map.containsKey("article_enddate"))
			predicates.add(builder.between(root.get("updateTime"), Timestamp.valueOf(map.get("article_startdate")),
					Timestamp.valueOf(map.get("article_enddate"))));

		for (Map.Entry<String, String> row : map.entrySet()) {
			if ("article_name".equals(row.getKey())) {
				predicates.add(builder.like(root.get("articleName"), "%" + row.getValue() + "%"));
			}

			if ("article_category".equals(row.getKey())) {
				predicates.add(builder.equal(root.get("forum").get("forumId"), row.getValue()));
			}

			if ("article_startdate".equals(row.getKey())) {
				if (!map.containsKey("article_enddate"))
					predicates.add(
							builder.greaterThanOrEqualTo(root.get("updateTime"), Timestamp.valueOf(row.getValue())));
			}

			if ("article_enddate".equals(row.getKey())) {
				if (!map.containsKey("article_startdate"))
					predicates.add(
							builder.greaterThanOrEqualTo(root.get("updateTime"), Timestamp.valueOf(row.getValue())));
			}
		}

		criteriaQuery.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteriaQuery.orderBy(builder.asc(root.get("articleId")));
		TypedQuery<Article> query = getSession().createQuery(criteriaQuery);

		return query.getResultList();
	}
	@Override
	public void updateView(Integer articleId) {
		Article article = getSession().get(Article.class, articleId);
		article.setCtr(article.getCtr() + 1);
	}
	//以瀏覽數和論壇ID來判斷熱門文章
	public List<Article> findTopArticlesByCTR(int forumId, int limit) {
	    String hql = "FROM Article a WHERE a.forum.forumId = :forumId ORDER BY a.ctr DESC";
	    Query<Article> query = getSession().createQuery(hql, Article.class);
	    query.setParameter("forumId", forumId); // 使用論壇ID作為查詢參數
	    query.setMaxResults(limit); // 使用传入的limit参数
	    return query.getResultList();
	}


	 @Override
	 public List<Article> findArticlesByForumId(Integer forumId) {
		    List<Article> articles = new ArrayList<>();
		    // 实现数据库查询逻辑
		    String hql = "FROM Article a WHERE a.forum.forumId = :forumId";
		    Query<Article> query = getSession().createQuery(hql, Article.class); 
		    query.setParameter("forumId", forumId);
		    articles = query.getResultList();
		    return articles;
		}
	
	
	
}
