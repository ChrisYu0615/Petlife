package com.petlife.reportforum.entity;

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
import com.oracle.wls.shaded.org.apache.xpath.functions.FuncFalse;
import com.petlife.admin.entity.Admin;
import com.petlife.article.entity.Article;
import com.petlife.comment.entity.Comment;
import com.petlife.reporttype.entity.ReportType;
import com.petlife.user.entity.User;

@Entity
@Table(name = "report_forum")
public class ReportForum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_forum_id", updatable = false, insertable = false)
    private Integer reportForumId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false, insertable = false)
	@Expose
    private User user;

    @ManyToOne
    @JoinColumn(name = "article_id", referencedColumnName = "article_id", nullable = false, insertable = false)
    @Expose
    private Article article;

    @ManyToOne
    @JoinColumn(name = "comment_id", referencedColumnName = "comment_id", nullable = false, insertable = false)
    @Expose
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "report_type_id",referencedColumnName = "report_type_id", nullable = false, insertable = false)
    @Expose
    private ReportType reportType;

    @Column(name = "report_forum_time", nullable = false, insertable = false)
    private Timestamp reportForumTime;

    @Column(name = "report_forum_reason", columnDefinition = "longtext")
    private String reportForumReason;

    @ManyToOne	
    @JoinColumn(name = "admin_id", referencedColumnName = "admin_id", nullable = false)
    @Expose
    private Admin admin;

    @Column(name = "admin_reply", columnDefinition = "longtext")
    private String adminReply;

    @Column(name = "admin_reply_time")
    private Timestamp adminReplyTime;

	public ReportForum() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReportForum(Integer reportForumId, User user, Article article, Comment comment, ReportType reportType,
			Timestamp reportForumTime, String reportForumReason, Admin admin, String adminReply,
			Timestamp adminReplyTime) {
		super();
		this.reportForumId = reportForumId;
		this.user = user;
		this.article = article;
		this.comment = comment;
		this.reportType = reportType;
		this.reportForumTime = reportForumTime;
		this.reportForumReason = reportForumReason;
		this.admin = admin;
		this.adminReply = adminReply;
		this.adminReplyTime = adminReplyTime;
	}

	public Integer getReportForumId() {
		return reportForumId;
	}

	public void setReportForumId(Integer reportForumId) {
		this.reportForumId = reportForumId;
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

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public ReportType getReportType() {
		return reportType;
	}

	public void setReportType(ReportType reportType) {
		this.reportType = reportType;
	}

	public Timestamp getReportForumTime() {
		return reportForumTime;
	}

	public void setReportForumTime(Timestamp reportForumTime) {
		this.reportForumTime = reportForumTime;
	}

	public String getReportForumReason() {
		return reportForumReason;
	}

	public void setReportForumReason(String reportForumReason) {
		this.reportForumReason = reportForumReason;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public String getAdminReply() {
		return adminReply;
	}

	public void setAdminReply(String adminReply) {
		this.adminReply = adminReply;
	}

	public Timestamp getAdminReplyTime() {
		return adminReplyTime;
	}

	public void setAdminReplyTime(Timestamp adminReplyTime) {
		this.adminReplyTime = adminReplyTime;
	}

   
}
