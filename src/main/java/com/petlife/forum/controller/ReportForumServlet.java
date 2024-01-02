package com.petlife.forum.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.petlife.admin.entity.AcctState;
import com.petlife.admin.entity.Admin;
import com.petlife.admin.service.AcctStateService;
import com.petlife.admin.service.AdminService;
import com.petlife.admin.service.impl.AcctStateServiceImpl;
import com.petlife.admin.service.impl.AdminServiceImpl;
import com.petlife.forum.entity.Article;
import com.petlife.forum.entity.ReportForum;
import com.petlife.forum.entity.ReportType;
import com.petlife.forum.service.ArticleService;
import com.petlife.forum.service.ReportForumService;
import com.petlife.forum.service.impl.ArticleServiceImpl;
import com.petlife.forum.service.impl.ReportForumServiceImpl;
import com.petlife.user.entity.User;
import com.petlife.user.service.UserService;
import com.petlife.user.service.impl.UserServiceImpl;
import com.petlife.util.MailService;

@WebServlet("/reportForum/reportForum.do")
@MultipartConfig
public class ReportForumServlet extends HttpServlet {
	private ReportForumService reportForumService;

	@Override
	public void init() throws ServletException {
		reportForumService = new ReportForumServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		String forwardPath = "";
		switch (action) {
		case "getAllReports":
			forwardPath = getAllReports(req, resp);
			break;
		case "getOne":
			getOneReport(req, resp);
			break;
		case "adminReply":
			forwardPath = adminReply(req, resp);
			break;
		case "addReport":
			forwardPath = addReport(req, resp);
			break;
		default:
			forwardPath = "";
			break;
		}

		if (!forwardPath.isEmpty()) {
			RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
			dispatcher.forward(req, resp);
		}
	}

	private String adminReply(HttpServletRequest req, HttpServletResponse resp) {
		Integer reportForumId = Integer.valueOf(req.getParameter("reportId"));
		Integer adminId = Integer.valueOf(req.getParameter("adminId"));
		String replyMsg = req.getParameter("adminReply");
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());

		ReportForum reportForum = reportForumService.getReportForumById(reportForumId);
		AdminService adminService = new AdminServiceImpl();
		Admin admin = adminService.getAdminByAdminId(adminId);

		Boolean articleState = Boolean.valueOf(req.getParameter("article_state"));
		Article article = reportForum.getArticle();
		User user = reportForum.getUser();  //檢舉方		
		User articleUser = article.getUser(); //被檢舉方

		// 修改文章狀態為true時，文章狀態是false(下架)，發文者的被檢舉次數+1，檢舉到達5次就停權
		if (articleState == true) {
			article.setState(false);
			Integer userReportCountInteger = articleUser.getUserReportCount();
			if (userReportCountInteger < 5) {
<<<<<<< HEAD
				articleUser.setUserReportCount(articleUser.getUserReportCount() + 1);
			} else{
=======
				user.setUserReportCount(user.getUserReportCount() + 1);
			} else {
>>>>>>> refs/heads/master
				AcctStateService acctStateService = new AcctStateServiceImpl();
				AcctState acctState = acctStateService.getByAcctStateId(1);
				articleUser.setAcctState(acctState);
			}
			// 二、將修改過的文章存到資料庫中
			ArticleService articleService = new ArticleServiceImpl();
			articleService.updateArticle(article);
			UserService userService = new UserServiceImpl();
			userService.updateUser(articleUser);

		}

		reportForum.setAdminReply(replyMsg);
		reportForum.setAdmin(admin);
		reportForum.setAdminReplyTime(timestamp);

		reportForumService.updateReportForum(reportForum);
		Thread thread = new Thread(() -> {
			MailService.replyReportMsg(article, user.getUserAcct(), replyMsg);
		});
		thread.start();

		return "/reportForum/reportForum.do?action=getAllReports&condition=unReply";
	}

	private void getOneReport(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Integer reportForumId = Integer.valueOf(req.getParameter("reportForumId"));
		String value = req.getParameter("value");

		ReportForum reportForum = reportForumService.getReportForumById(reportForumId);
		resp.setContentType("application/json; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		String reportForumJson;

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd hh:mm:ss")
				.create();
		reportForumJson = gson.toJson(reportForum);

		out.print(reportForumJson);
	}

	private String getAllReports(HttpServletRequest req, HttpServletResponse resp) {
		String condition = req.getParameter("condition");
		List<ReportForum> reportForumList = reportForumService.getAllReportForums(condition);
		req.setAttribute("getAllReports", reportForumList);
		return "/admin/forum_report_management.jsp";
	}

	private String addReport(HttpServletRequest req, HttpServletResponse resp) {
//		System.out.print(reportTypeId);
//		System.out.println(reportForumReason);
		// 錯誤處理
		List<String> errorMsgs = new ArrayList<>();
		req.setAttribute("errorMsgs", errorMsgs);

		/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
		// 檢舉原因不能為空
//			try {
//				if (reportForumReason!=null && !reportForumReason.trim().isEmpty()) {
//					//addReport(req,resp);
//				}else {
//					System.out.println("請輸入原因");		
//				}
//			}catch (Exception  e) {
//					System.out.println("檢舉失敗" + e.getMessage());
//				}

		// ============================================

//			Integer reportForumId = Integer.valueOf(req.getParameter("reportForumId"));

		Integer reportTypeId = Integer.valueOf(req.getParameter("reportTypeId"));
		Integer articleId = Integer.valueOf(req.getParameter("article"));
		String reportForumReason = req.getParameter("reportForumReason");

		ReportForum reportForum = new ReportForum();
		reportForum.setReportForumReason(reportForumReason);

		ReportType reportType = new ReportType();
		reportType.setReportTypeId(reportTypeId);
		reportForum.setReportType(reportType);

		User user = (User) req.getSession().getAttribute("user");
		reportForum.setUser(user);

		ArticleService articleService = new ArticleServiceImpl();
		Article article = articleService.getArticleByArticleId(articleId);
		reportForum.setArticle(article);

		/*************************** 2.開始新增資料 ***************************************/
//			try {
//				System.out.println("檢舉成功");
//			} catch (Exception e) {
//				System.out.println("檢舉失敗" + e.getMessage());
//				e.getMessage();
//			};

		reportForumService.addReportForum(reportForum);
		req.setAttribute("article", article);

		return "/article/spec-blog.jsp";

	}
}
