package com.petlife.forum.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.petlife.forum.dao.ArticleDAO;
import com.petlife.forum.dao.impl.ArticleDAOImpl;
import com.petlife.forum.entity.Article;
import com.petlife.forum.service.ArticleService;

public class ArticleServiceImpl implements ArticleService {
	private ArticleDAO dao;

	public ArticleServiceImpl() {
		dao = new ArticleDAOImpl();
	}

	@Override
	public Article addArticle(Article article) {
		Integer id = dao.add(article);
		if (id != null && id != -1) {
			article = dao.findByPK(id);
		}
		return article;
	}

	@Override
	public Integer deleteArticle(Integer articleId) {
		return dao.delete(articleId);
	}

	@Override
	public Integer updateArticle(Article article) {
		return dao.update(article);
	}

	@Override
	public Article getArticleByArticleId(Integer articleId) {
		return dao.findByPK(articleId);
	}

	@Override
	public List<Article> getAllArticle() {
		return dao.getAll();
	}

	@Override
	public List<Article> searchArticlesByKeyword(String keyword) {
		// 假設 ArticleDAO 有一個 searchByKeyword 方法
		return dao.searchByKeyword(keyword);
	}

	@Override
	public List<Article> getArticlesByCompositeQuery(Map<String, String[]> map) {
		Map<String, String> query = new HashMap<>();
		// 用傳入的map.entrySet()取得Set<Map.Entry<String,
		// String[]>>物件，Map.Entry即代表一組key-value
		Set<Map.Entry<String, String[]>> entry = map.entrySet();

		for (Map.Entry<String, String[]> row : entry) {
			String key = row.getKey();
			// 因為請求參數裡包含了action，做個去除動作
			if ("action".equals(key)) {
				continue;
			}

			// 若是value為空即代表沒有查詢條件，做個去除動作
			String value = row.getValue()[0]; // getValue拿到一個String陣列, 接著[0]取得第一個元素檢查
			if (value == null || value.isEmpty()) {
				continue;
			}

			if ("選擇文章分類".equals(value)) {
				continue;
			}

			if ("article_startdate".equals(key) || "article_enddate".equals(key)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
				try {
					Date parsedDate = sdf.parse(value);
					java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());

					Timestamp timestamp = new Timestamp(sqlDate.getTime());
					String timestampString = timestamp.toLocalDateTime().format(formatter);
					value = timestampString;
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			query.put(key, value);
		}
		System.out.println(query);

		return dao.getByCompositeQuery(query);
	}

	@Override
	public List<Article> getAllArticle(Integer userId) {
		return dao.getAll(userId);
	}

	// 可能的其他方法
	// ...
}
