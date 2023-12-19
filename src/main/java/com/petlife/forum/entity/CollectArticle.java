package com.petlife.forum.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.petlife.user.entity.User;

@Entity
@Table(name = "collect_article")
public class CollectArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "collect_article_id", updatable = false, nullable = false, insertable = false)
    private Integer collectArticleId;

    @ManyToOne
    @JoinColumn(name = "article_id", referencedColumnName = "article_id", nullable = false)
    @Expose
    private Article article;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
	@Expose
    private User user;

	public CollectArticle() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CollectArticle(Integer collectArticleId, Article article, User user) {
		super();
		this.collectArticleId = collectArticleId;
		this.article = article;
		this.user = user;
	}

	public Integer getCollectArticleId() {
		return collectArticleId;
	}

	public void setCollectArticleId(Integer collectArticleId) {
		this.collectArticleId = collectArticleId;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

   
}
