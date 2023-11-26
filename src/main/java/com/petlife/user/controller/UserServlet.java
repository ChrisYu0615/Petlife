package com.petlife.user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.petlife.user.entity.User;
import com.petlife.user.service.UserServeice;
import com.petlife.user.service.UserServiceImpl;

@WebServlet("/user/user.do")
public class UserServlet extends HttpServlet {
	private UserServeice userServeice;

	@Override
	public void init() throws ServletException {
		userServeice = new UserServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
//		resp.setContentType("text/html;charset=UTF-8");
//		resp.setContentType("application/json;charset=UTF-8");

		String action = req.getParameter("action");
		String forwardPath = "";
		switch (action) {
		case "userRegist":
			forwardPath = userRegist(req, resp);
			break;
		case "delete":
			forwardPath = deleteUser(req, resp);
			break;
		case "update":
			forwardPath = updateUser(req, resp);
			break;
		case "getOneByPK":
			forwardPath = getUserByPK(req, resp);
			break;
		case "getAll":
			forwardPath = getAllUsers(req, resp);
			break;
		case "verify":
			forwardPath = authencation(req, resp);
		default:
			forwardPath = "";
			break;
		}
		// dispatcher路徑是從專案開始，forwardPath要加/
		if (!forwardPath.isEmpty()) {
			RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
			dispatcher.forward(req, resp);
		}

	}

	private String authencation(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String userNickname = req.getParameter("nickname");
		String userAccount = req.getParameter("useraccount");

		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		if (userNickname != null) {
			boolean checkUserNickname = userServeice.existUserNickname(userNickname);

			if (userNickname.length() == 0) {
				out.print("");
			} else if (checkUserNickname) {
				out.print("<font color='red'>暱稱重複!!</font>");
			} else {
				out.print("<font color='green'>暱稱可使用</font>");
			}
		}

		if (userAccount != null) {
			boolean checkUserAccount = userServeice.exisUserAccount(userAccount);

			if (userAccount.length() == 0) {
				out.print("");
			} else if (checkUserAccount) {
				out.print("<font color='red'>帳號重複!!</font>");
			} else {
				out.print("<font color='green'>帳號可使用</font>");
			}
		}

		return "";
	}

	/*
	 * 新增一個會員的檢查
	 */
	private String userRegist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		resp.setContentType("application/json;charset=UTF-8");
		String userAcct = req.getParameter("useraccount");
		String authenCode = req.getParameter("authencode");
		String userPwd = req.getParameter("password");
		String userName = req.getParameter("username");
		String userNickname = req.getParameter("nickname");
		Date userBirthday = java.sql.Date.valueOf(req.getParameter("birthdate"));
		String userPhoneNum = req.getParameter("phone");
		String userAddress = req.getParameter("country") + req.getParameter("district") + req.getParameter("address");
		boolean userGender = Boolean.valueOf(req.getParameter("gender"));
//		String headshot = req.getParameter("headshot");

		User user = new User(userAcct, userPwd, userName, userNickname, userBirthday, userAddress, userPhoneNum,
				userGender);

		if ("check".equalsIgnoreCase(authenCode)) {
			// 這裡是名稱重複的情況
			if (userServeice.existUserNickname(userNickname)) {
				System.out.println("暱稱重複!!");
				return "/member_regist.html";
			} else {
				// 這裡是驗證碼正確而且暱稱不重複的情況
				// 使用jsp返回
				user = userServeice.addUser(user);
				req.setAttribute("user", user);
				return "/listOneEmp.jsp";
				// 返回資料庫查詢的物件
//			user = userServeice.registUser(user);
				// 返回json物件
//			if (user != null) {
//				System.out.println(user);
//				System.out.println("註冊成功!!，以下是會員Json");

//				Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
//						.setDateFormat("yyyy-MM-dd HH:mm:ss")
//						.setPrettyPrinting()
//						.create();
//				String userJson = gson.toJson(user);
//				System.out.println(userJson);
//				PrintWriter out = resp.getWriter();
//				out.write(userJson);
//			} else {
//				System.out.println("註冊失敗!!");
//			}
//			return "";
				// 請求重定向
//				try {
//					System.out.println("註冊會員成功!!");
//					resp.sendRedirect(req.getContextPath() + "/listOneEmp.jsp");
//					return "";
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				return "";
			}
		} else {
			// 這裡是驗證碼輸入錯誤的情況
			System.out.println("驗證碼輸入錯誤!!");
			return "/member_regist.html";
		}
	}

	private String deleteUser(HttpServletRequest req, HttpServletResponse resp) {
		userServeice.deleteUser(null);
		return "";
	}

	private String updateUser(HttpServletRequest req, HttpServletResponse resp) {
		userServeice.updateUser(null);
		return "";
	}

	private String getUserByPK(HttpServletRequest req, HttpServletResponse resp) {
		userServeice.getUserByUserId(null);
		return "";
	}

	private String getAllUsers(HttpServletRequest req, HttpServletResponse resp) {
		userServeice.getAllUsers();
		return "";
	}
}
