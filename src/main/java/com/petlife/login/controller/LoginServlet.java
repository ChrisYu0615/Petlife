package com.petlife.login.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.petlife.admin.entity.Admin;
import com.petlife.admin.service.AdminService;
import com.petlife.admin.service.impl.AdminServiceImpl;
import com.petlife.seller.entity.Seller;
import com.petlife.seller.service.SellerService;
import com.petlife.seller.service.impl.SellerServiceImpl;
import com.petlife.shelter.entity.Shelter;
import com.petlife.shelter.service.ShelterService;
import com.petlife.shelter.service.impl.ShelterServiceImpl;
import com.petlife.user.entity.User;
import com.petlife.user.service.UserServeice;
import com.petlife.user.service.impl.UserServiceImpl;
import com.petlife.util.Sha1Util;

@WebServlet("/login/login.do")
@MultipartConfig
public class LoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");

		switch (action) {
		case "userLogin":
			userLogin(req, resp);
			break;
		case "sellerLogin":
			sellerLogin(req, resp);
			break;
		case "shelterLogin":
			shelterLogin(req, resp);
			break;
		case "adminLogin":
			adminLogin(req, resp);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + action);
		}
	}

	// 一般會員登入
	private void userLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String userAcct = req.getParameter("account").trim();
		String userPwd = req.getParameter("password").trim();
		userPwd = Sha1Util.encodePwd(userPwd);
		UserServeice userServeice = new UserServiceImpl();

		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		// 檢查要登入的帳號是否存在
		if (userServeice.exisUserAccount(userAcct)) {
			// 存在的話檢查帳密是否存在，並回傳帳號目前狀態
			Map<String, Integer> loginStatus = userServeice.userLogin(userAcct, userPwd);
			Integer acctState = loginStatus.get("acctState");
			// 登入成功，檢查帳號的狀態
			switch (acctState) {
			case 0:
				// 帳號為可使用狀態
				Integer userId = loginStatus.get("userId");
				User user = userServeice.getUserByUserId(userId);
				HttpSession session = req.getSession();
				session.setAttribute("user", user);
				try {
					String location = (String) session.getAttribute("location");
					// 重導回登入前頁面
					if (location != null) {
						session.removeAttribute("location");
						out.print(location);
						return;
					} else {
						out.print(req.getContextPath() + "/index.html");
						return;
					}
				} catch (Exception ignored) {
					// 重導回首頁
					out.print(req.getContextPath() + "/index.html");
				}
				break;
			case 1:
				// 帳號為停權狀態
				out.print("停權");
				break;
			case 2:
				// 密碼錯誤並已達5次
				out.print("密碼錯誤已達5次");
				break;
			case 5:
				// 密碼錯誤但尚未達5次
				out.print("密碼錯誤未達5次");
				break;
			}
		} else {
			// 登入失敗：帳號不存在
			out.print("帳號不存在");
		}
	}

	private void sellerLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String sellerAcct = req.getParameter("account").trim();
		String sellerPwd = req.getParameter("password").trim();
		sellerPwd = Sha1Util.encodePwd(sellerPwd);
		SellerService sellerService = new SellerServiceImpl();

		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		// 檢查要登入的帳號是否存在
		if (sellerService.existSellerAccount(sellerAcct)) {
			// 存在的話檢查帳密是否存在，並回傳帳號目前狀態
			Map<String, Integer> loginStatus = sellerService.sellerLogin(sellerAcct, sellerPwd);
			Integer acctState = loginStatus.get("acctState");
			// 登入成功，檢查帳號的狀態
			switch (acctState) {
			case 0:
				// 帳號為可使用狀態
				Integer sellerId = loginStatus.get("sellerId");
				Seller seller = sellerService.getSellerBySellerId(sellerId);
				HttpSession session = req.getSession();
				session.setAttribute("seller", seller);
				try {
					String location = (String) session.getAttribute("location");
					// 重導回登入前頁面
					if (location != null) {
						session.removeAttribute("location");
						out.print(location);
						return;
					} else {
						out.print(req.getContextPath() + "/index.html");
						return;
					}
				} catch (Exception ignored) {
					// 重導回首頁
					out.print(req.getContextPath() + "/index.html");
				}
				break;
			case 1:
				// 帳號為停權狀態
				out.print("停權");
				break;
			case 2:
				// 密碼錯誤並已達5次
				out.print("密碼錯誤已達5次");
				break;
			case 3:
				// 帳號為待補件狀態
				out.print("待補件");
				break;
			case 4:
				// 帳號為待審核狀態
				out.print("待審核");
				break;
			case 5:
				// 密碼錯誤未達5次
				out.print("密碼錯誤未達5次");
				break;
			}
		} else {
			// 登入失敗：帳號不存在
			out.print("帳號不存在");
		}

	}

	private void shelterLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String shelterAcct = req.getParameter("account").trim();
		String shelterPwd = req.getParameter("password").trim();
		shelterPwd = Sha1Util.encodePwd(shelterPwd);
		ShelterService shelterService = new ShelterServiceImpl();

		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		// 檢查要登入的帳號是否存在
		if (shelterService.existShelterAccount(shelterAcct)) {
			// 存在的話檢查帳密是否存在，並回傳帳號目前狀態
			Map<String, Integer> loginStatus = shelterService.shelterLogin(shelterAcct, shelterPwd);
			Integer acctState = loginStatus.get("acctState");
			// 登入成功，檢查帳號的狀態
			switch (acctState) {
			case 0:
				// 帳號為可使用狀態
				Integer shelterId = loginStatus.get("shelterId");
				Shelter shelter = shelterService.getShelterByShelterId(shelterId);
				HttpSession session = req.getSession();
				session.setAttribute("shelter", shelter);
				try {
					String location = (String) session.getAttribute("location");
					// 重導回登入前頁面
					if (location != null) {
						session.removeAttribute("location");
						out.print(location);
						return;
					} else {
						out.print(req.getContextPath() + "/index.html");
						return;
					}
				} catch (Exception ignored) {
					// 重導回首頁
					out.print(req.getContextPath() + "/index.html");
				}
				break;
			case 1:
				// 帳號為停權狀態
				out.print("停權");
				break;
			case 2:
				// 密碼錯誤並已達5次
				out.print("密碼錯誤已達5次");
				break;
			case 3:
				// 帳號為待補件狀態
				out.print("待補件");
				break;
			case 4:
				// 帳號為待審核狀態
				out.print("待審核");
				break;
			case 5:
				// 密碼錯誤未達5次
				out.print("密碼錯誤未達5次");
				break;
			}
		} else {
			// 登入失敗：帳號不存在
			out.print("帳號不存在");
		}

	}

	private void adminLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String adminAcct = req.getParameter("account").trim();
		String adminPwd = req.getParameter("password").trim();
		adminPwd = Sha1Util.encodePwd(adminPwd);
		AdminService adminService = new AdminServiceImpl();

		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		// 檢查要登入的帳號是否存在
		if (adminService.exisAdminAccount(adminAcct)) {
			Map<String, Integer> loginStatus = adminService.adminLogin(adminAcct, adminPwd);
			Integer acctState = loginStatus.get("acctState");
			switch (acctState) {
			case 0:
				// 帳號為可使用狀態
				Integer adminId = loginStatus.get("adminId");
				Admin admin = adminService.getAdminByAdminId(adminId);
				HttpSession session = req.getSession();
				session.setAttribute("admin", admin);
				try {
					String location = (String) session.getAttribute("location");
					if (location != null) {
						session.removeAttribute("location");
						out.print(location);
						return;
					} else {
						out.print(req.getContextPath() + "/user/user.do?action=getAll");
						return;
					}
				} catch (Exception ignore) {
					out.print(req.getContextPath() + "/user/user.do?action=getAll");
				}
				break;
			case 1:
				// 帳號為停權狀態
				out.print("停權");
				break;
			case 2:
				// 密碼錯誤並已達5次
				out.print("密碼錯誤已達5次");
				break;
			case 5:
				// 密碼錯誤但尚未達5次
				out.print("密碼錯誤未達5次");
				break;
			}
		} else {
			// 登入失敗：帳號不存在
			out.print("帳號不存在");
		}

	}
}
