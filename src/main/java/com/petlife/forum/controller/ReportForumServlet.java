package com.petlife.forum.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
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
import com.petlife.admin.entity.Admin;
import com.petlife.admin.service.AdminService;
import com.petlife.admin.service.impl.AdminServiceImpl;
import com.petlife.forum.entity.ReportForum;
import com.petlife.forum.service.ReportForumService;
import com.petlife.forum.service.impl.ReportForumServiceImpl;

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
		default:
			forwardPath = "";
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

		reportForum.setAdminReply(replyMsg);
		reportForum.setAdmin(admin);
		reportForum.setAdminReplyTime(timestamp);

		reportForumService.updateReportForum(reportForum);

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
}
