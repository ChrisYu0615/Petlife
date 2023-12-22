package com.petlife.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.petlife.admin.service.AdminService;
import com.petlife.admin.service.impl.AdminServiceImpl;
import com.petlife.seller.entity.Seller;
import com.petlife.seller.service.SellerService;
import com.petlife.seller.service.impl.SellerServiceImpl;
import com.petlife.shelter.entity.Shelter;
import com.petlife.shelter.service.ShelterService;
import com.petlife.shelter.service.impl.ShelterServiceImpl;
import com.petlife.util.MailService;
import com.petlife.util.RandomAuthenCode;

@WebServlet("/admin/admin.do")
@MultipartConfig
public class AdminServlet extends HttpServlet {
	private AdminService adminService;

	@Override
	public void init() throws ServletException {
		adminService = new AdminServiceImpl();
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
		case "adminRegister":
			adminRegister(req, resp);
			break;
		case "verify":
			authencation(req, resp);
			break;
		case "getAuthenCode":
			getAuthenCode(req, resp);
			break;
		case "forgetPwd":
			setNewPassword(req, resp);
			break;
		case "getAllMembers":
			forwardPath = getAllMembers(req, resp);
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

	private String getAllMembers(HttpServletRequest req, HttpServletResponse resp) {
		String conditions = req.getParameter("condition");
		ShelterService shelterService = new ShelterServiceImpl();
		List<Shelter> shelterList = shelterService.getAllShelters(conditions);
		SellerService sellerService = new SellerServiceImpl();
		List<Seller> sellerList = sellerService.getAllSellers(conditions);

		req.setAttribute("getAllShelters", shelterList);
		req.setAttribute("getAllSellers", sellerList);
		return "/admin/unverify_member_management.jsp";
	}

	private void getAuthenCode(HttpServletRequest req, HttpServletResponse resp) {
		String adminAcct = req.getParameter("adminaccount");
		String value = req.getParameter("value");
		String memberType = "newPwd".equals(value) ? "AdminNewPwd" : "Admin";

		// 取得儲存在redis當中的驗證碼(效期只有10分鐘)
		String authenCode = RandomAuthenCode.setAuthenCode(memberType, adminAcct);

		// 寄信給該註冊帳號
		MailService.sendAuthenCode(adminAcct, authenCode);

	}

	private void setNewPassword(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String adminAcct = req.getParameter("account");
		System.out.println(adminAcct);
		String authenCode = req.getParameter("authencode");
		System.out.println(authenCode);

		Map<String, String> errorMsg = new HashMap<>();
		resp.setContentType("application/json; charset=UTF-8");
		Gson gson = new Gson();
		PrintWriter out = resp.getWriter();
		if (!adminService.exisAdminAccount(adminAcct)) {
			errorMsg.put("accountErr", "帳號不存在!!");
			String errorMsgJson = gson.toJson(errorMsg);
			System.out.println(errorMsgJson);
			out.print(errorMsgJson);
			return;
		}

		String authenCodeFromJedis = RandomAuthenCode.getAuthenCode("AdminNewPwd", adminAcct);
		if (authenCodeFromJedis == null) {
			errorMsg.put("authenCodeErr", "請先取得驗證碼!!");
		} else {
			if (!authenCode.equals(authenCodeFromJedis)) {
				errorMsg.put("authenCodeErr", "驗證碼輸入錯誤");
			}
		}

		if (errorMsg.size() > 0) {
			String errorMsgJson = gson.toJson(errorMsg);
			System.out.println(errorMsgJson);
			out.print(errorMsgJson);
		} else {
			String result = adminService.getNewPwd(adminAcct);
			Map<String, String> successMsg = new HashMap<>();
			successMsg.put("success", result);

			out.print(gson.toJson(successMsg));
		}
	}

	private void authencation(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub

	}

	private void adminRegister(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub

	}

}
