package com.petlife.good.entity;



import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.petlife.article.entity.Article;
import com.petlife.user.entity.User;

@Entity
@Table(name = "good")
public class Good {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "good_id", updatable = false, nullable = false)
    private Integer goodId;

    @ManyToOne
    @JoinColumn(name = "article_id", referencedColumnName = "article_id", nullable = false, insertable = false)
    @Expose
    private Article article;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id",insertable = false)
	@Expose
    private User user;

    @Column(name = "good_date", nullable = false, insertable = false)
    private Timestamp goodDate;

	public Good() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Good(Integer goodId, Article article, User user, Timestamp goodDate) {
		super();
		this.goodId = goodId;
		this.article = article;
		this.user = user;
		this.goodDate = goodDate;
	}

	public Integer getGoodId() {
		return goodId;
	}

	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
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

	public Timestamp getGoodDate() {
		return goodDate;
	}

	public void setGoodDate(Timestamp goodDate) {
		this.goodDate = goodDate;
	}

    
   
}
