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

@Entity
@Table(name = "article_img")
public class ArticleImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_img_id", updatable = false, nullable = false)
    private Integer articleImgId;

    @ManyToOne
    @JoinColumn(name = "article_id", referencedColumnName = "article_id", nullable = false )
    @Expose
    private Article article;

    @Column(name = "article_img", columnDefinition = "longblob")
    private byte[] articleImg;

	public ArticleImg() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArticleImg(Integer articleImgId, Article article, byte[] articleImg) {
		super();
		this.articleImgId = articleImgId;
		this.article = article;
		this.articleImg = articleImg;
	}

	public Integer getArticleImgId() {
		return articleImgId;
	}

	public void setArticleImgId(Integer articleImgId) {
		this.articleImgId = articleImgId;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public byte[] getArticleImg() {
		return articleImg;
	}

	public void setArticleImg(byte[] articleImg) {
		this.articleImg = articleImg;
	}

   
}
