package com.petlife.article.dao;

import java.util.List;

import com.petlife.article.entity.Article;

public class TestArticle {

	public static void main(String[] args) throws Exception {
		ArticleDAO dao = new ArticleDAOImpl();

		// 新增
		Article article = new Article();
//		article.setUserId(100000003);
//		article.setForumArtId(3);
		article.setArticleName("測試測試拉拉拉拉");
		article.setArticleContent("家人甚麼時候要去泰國");
		article.setCtr(200);
		article.setState(true);
//		article.setUpdateTime(java.sql.Timestamp.valueOf("2021-11-20 13:21:00"));
		dao.add(article);
//		
		// 修改
//		Article article1 = new Article();
//		article1.setArticleId(13);
//		article1.setUserId(100000004);
//		article1.setForumArtId(4);
//		article1.setArticleName("哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈");
//		article1.setArticleContent("哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈");
//		article1.setCtr(100);
//		article1.setState(true);;
//		dao.add(article1);
//	
        //刪除
//		dao.delete(13);
	
////		 查詢單筆
//			Article art2 = dao.findByPK(2);
//			System.out.print(art2.getArticleId() + ",");
//			System.out.print(art2.getUserId() + ",");
//			System.out.print(art2.getForumArtId() + ",");
//			System.out.print(art2.getArticleName() + ",");
//			System.out.print(art2.getArticleContent() + ",");
//			System.out.print(art2.getUpdateTime() + ",");
//			System.out.print(art2.getCtr() + ",");
//			System.out.println(art2.getState());
//			System.out.println("---------------------");
//			System.out.println("成功");
	
			// 查詢多筆
			List<Article> list = dao.getAll();
			for (Article art3 : list) {
				System.out.print(art3.getArticleId() + ",");
				System.out.print(art3.getUser() + ",");
				System.out.print(art3.getForum() + ",");
				System.out.print(art3.getArticleContent() + ",");
				System.out.print(art3.getUpdateTime() + ",");
				System.out.print(art3.getCtr() + ",");
				System.out.print(art3.getState());
				System.out.println();
			}
	
	
	}
}