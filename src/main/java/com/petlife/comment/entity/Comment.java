package com.petlife.comment.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.oracle.wls.shaded.org.apache.bcel.generic.DALOAD;
import com.petlife.article.entity.Article;
import com.petlife.user.entity.User;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增鍵
    @Column(name = "comment_id", updatable = false, nullable = false, insertable = false) 
    private Integer commentId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id",insertable = false)
	@Expose
    private User user;

    @ManyToOne
    @JoinColumn(name = "article_id", referencedColumnName = "article_id", nullable = false, insertable = false)
    @Expose
    private Article article;

    @Column(name = "comment_text", columnDefinition = "LONGTEXT", nullable = false)
    private String commentText;

    @Column(name = "comment_datetime", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP", insertable = false)
    private Timestamp commentDatetime;

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(Integer commentId, User user, Article article, String commentText, Timestamp commentDatetime) {
		super();
		this.commentId = commentId;
		this.user = user;
		this.article = article;
		this.commentText = commentText;
		this.commentDatetime = commentDatetime;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public Timestamp getCommentDatetime() {
		return commentDatetime;
	}

	public void setCommentDatetime(Timestamp commentDatetime) {
		this.commentDatetime = commentDatetime;
	}

    
}
