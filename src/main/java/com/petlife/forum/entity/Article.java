package com.petlife.forum.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.petlife.user.entity.User;

@Entity
@Table(name = "article")

@NamedQuery(name = "getAllArticle", query = "from Article where articleId > :articleId order by articleId desc")
public class Article  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //自增鍵
	@Column(name = "article_id", updatable = false,nullable= false) // updatable = false --> updata SQL  不包括此欄位
	@Expose
	private Integer articleId;
	
	@ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
	@Expose
    private User user;
	
//	@Column(name="forum_art_id")
//	private Integer forumId;
	
	@ManyToOne
	@JoinColumn(name = "forum_art_id", referencedColumnName = "forum_id", nullable= false)
	@Expose
	private Forum forum;	
	
	@Column(name = "article_name")
	@Expose
	private String articleName;	
	
	@Column(name = "article_content",columnDefinition = "longtext", nullable = false)
	private String articleContent;	
	
	@Column(name = "update_time", insertable = false)
	private Timestamp updateTime;
		
	@Column(name = "ctr")
	private Integer ctr;
		
	@Column(name = "state")
	private Boolean state;
	
	@OneToMany(mappedBy = "article",cascade = CascadeType.ALL)
	@OrderBy("article_id asc")
	List<ArticleImg> articleImgs = new ArrayList<>();

	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Article(Integer articleId, User user, Forum forum, String articleName, String articleContent,
			Timestamp updateTime, Integer ctr, Boolean state) {
		super();
		this.articleId = articleId;
		this.user = user;
		this.forum = forum;
		this.articleName = articleName;
		this.articleContent = articleContent;
		this.updateTime = updateTime;
		this.ctr = ctr;
		this.state = state;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Forum getForum() {
		return forum;
	}

	public void setForum(Forum forum) {
		this.forum = forum;
	}

	public String getArticleName() {
		return articleName;
	}

	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}

	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getCtr() {
		return ctr;
	}

	public void setCtr(Integer ctr) {
		this.ctr = ctr;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public List<ArticleImg> getArticleImgs() {
		return articleImgs;
	}

	public void setArticleImgs(List<ArticleImg> articleImgs) {
		this.articleImgs = articleImgs;
	}
	
	


	
	
}
