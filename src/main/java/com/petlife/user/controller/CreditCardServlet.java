package com.petlife.user.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petlife.mall.entity.Buylist;
import com.petlife.mall.entity.Comm;
import com.petlife.user.dao.CreditCardDAO;
import com.petlife.user.dao.impl.CreditCardDAOImpl;
import com.petlife.user.entity.CreditCard;
import com.petlife.user.entity.User;
import com.petlife.user.service.CreditCardService;
import com.petlife.user.service.impl.CreditCardServiceImpl;
import com.petlife.user.service.impl.UserServiceImpl;

@WebServlet("/creditcard/creditcard.do")
public class CreditCardServlet extends HttpServlet {
	// 一個 servlet 實體對應一個 service 實體
	private CreditCardService creditCardService;

	@Override
	public void init() throws ServletException {
		creditCardService = new CreditCardServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String forwardPath = "";

		switch (action) {
		case "getOne_For_Display":
			// 來自selact_page.jsp
			forwardPath = getOneDisplay(req, res);
			break;
		case "getOne_For_Update":
			// 來自listAllCreditCard.jsp
			forwardPath = getOneUpdate(req, res);
			break;
		case "update":
			// 來自update_creditCard_input.jsp
			forwardPath = update(req, res);
			break;
		case "insert":
			// 來自listAllCreditCard.jsp
			forwardPath = insert(req, res);
			break;
		case "delete":
			// 來自listAllCreditCard.jsp
			forwardPath = delete(req, res);
			break; // 新加的break
		default:
			forwardPath = "/select_page.jsp";
		}

		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
	}

	// 1,查詢
	private String getOneDisplay(HttpServletRequest req, HttpServletResponse res) {
		// 錯誤處理
		List<String> errorMsgs = new ArrayList<>();
		req.setAttribute("errorMsgs", errorMsgs);

///*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
		String str = req.getParameter("creditCardId");

		if (str == null || (str.trim()).length() == 0) {
			errorMsgs.add("請輸入優惠券編號");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			return "/buylistdetails/select_page.jsp";// 程式中斷
		}

		Integer creditCardId = null;
		try {
			creditCardId = Integer.valueOf(str);
		} catch (Exception e) {
			errorMsgs.add("優惠券編號格式不正確");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			return "/buylistdetails/select_page.jsp";// 程式中斷
		}

///*************************** 2.開始查詢資料 *****************************************/
//		CreditCardService coupoService = new CreditCardService();
		creditCardService = new CreditCardServiceImpl();
		CreditCard creditCard = creditCardService.getCreditCardByCreditCardId(creditCardId);

		if (creditCard == null) {
			errorMsgs.add("查無資料");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			return "/buylistdetails/select_page.jsp";// 程式中斷
		}

///*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
		req.setAttribute("creditCard", creditCard); // 資料庫取出的manage物件,存入req
		return "/buylistdetails/listOneCreditCard.jsp"; // 20231128
	}
	// ===================================================

	// 2,修改
	private String getOneUpdate(HttpServletRequest req, HttpServletResponse res) {
//		System.out.println(req.getParameter("creditCardId"));
		Integer creditCardId = Integer.valueOf(req.getParameter("creditCardId")); // 20231206
		System.out.println("+++" + creditCardId);

		CreditCard creditCard = creditCardService.getCreditCardByCreditCardId(creditCardId);
		System.out.println(creditCard);

		req.setAttribute("creditCard", creditCard);
		return "/buylistdetails/update_creditCard_input.jsp";
	}

	private String update(HttpServletRequest req, HttpServletResponse res) {
		// 錯誤處理
		List<String> errorMsgs = new ArrayList<>();
		req.setAttribute("errorMsgs", errorMsgs);

///*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
		Integer creditCardId = Integer.parseInt(req.getParameter("creditCardId"));

		Integer userId = Integer.parseInt(req.getParameter("user"));
		String creditCardNumber = req.getParameter("creditCardNumber");
		String creditCardHolderName = req.getParameter("creditCardHolderName");
		// 錯誤處理用前端
		Date creditCardExpirationDate = java.sql.Date.valueOf(req.getParameter("creditCardExpirationDate").trim());
//		Date creditCardExpirationDate;
//		try {
//			creditCardExpirationDate = java.sql.Date.valueOf(req.getParameter("creditCardExpirationDate").trim());
//		} catch (IllegalArgumentException e) {
//			creditCardExpirationDate = new java.sql.Date(System.currentTimeMillis());
//			errorMsgs.add("請輸入信用卡到期日!");
//		}

//============================================

		CreditCard creditCard = new CreditCard();
		creditCard.setCreditCardId(creditCardId);
		User user = new User();
		user.setUserId(userId);
		creditCard.setUser(user);
		creditCard.setCreditCardNumber(creditCardNumber);
		creditCard.setCreditCardExpirationDate(creditCardExpirationDate);
		creditCard.setCreditCardHolderName(creditCardHolderName);

//==========================================		
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			req.setAttribute("creditCard", creditCard); // 含有輸入格式錯誤的manage物件,也存入req
			return "/buylistdetails/update_creditCard_input.jsp"; // 程式中斷
		}

///*************************** 2.開始修改資料 *****************************************/
		creditCardService.updateCreditCard(creditCard);
		req.setAttribute("creditCard", creditCardService.getCreditCardByCreditCardId(creditCardId));

///*************************** 3.修改完成,準備轉交(Send the Success view) *************/
		req.setAttribute("creditCard", creditCard); // 資料庫update成功後,正確的的manage物件,存入req
		return "/buylistdetails/listOneCreditCard.jsp";
	}

	// ===================================================

	// 3,新增
	private String insert(HttpServletRequest req, HttpServletResponse res) {
    //		/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
		// 錯誤處理用前端
		Integer userId = Integer.parseInt(req.getParameter("userId"));
		String creditCardNumber = req.getParameter("creditCardNum");
		String creditCardHolderName = req.getParameter("creditCardName");
		Date creditCardExpirationDate = java.sql.Date.valueOf(req.getParameter("creditCardExpirationDate").trim()+ "-01");
		// ============================================
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		CreditCard creditCard = new CreditCard();
		User user = new User();
		user.setUserId(userId);
		creditCard.setUser(user);
		creditCard.setCreditCardNumber(creditCardNumber);
		creditCard.setCreditCardExpirationDate(creditCardExpirationDate);
		creditCard.setCreditCardHolderName(creditCardHolderName);

		// ==========================================

//		/*************************** 2.開始新增資料 ***************************************/

		try {
			System.out.println("1");
			creditCardService.addCreditCard(creditCard);
		} catch (Exception e) {
			System.out.println("2");
			e.getMessage();
		}
		
		user = userServiceImpl.getUserByUserId(userId);
		req.getSession().setAttribute("account", user);

//		if (creditCard.getCreditCardId() != null && creditCard.getCreditCardId() > 0) {
//			System.out.println("新增成功2");
//		} else {
//			System.out.println("新增失敗2");
//		}

//		/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
		return "/member_center/user_profile.jsp";
	}

	// ===================================================

	// 4,刪除
	private String delete(HttpServletRequest req, HttpServletResponse res) {
//		  List<String> errorMsgs = new List<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
//          req.setAttribute("errorMsgs", errorMsgs);

//          /**1.接收請求參數**/
		Integer creditCardId = Integer.valueOf(req.getParameter("creditCardId"));

//          /**2.開始刪除資料**/
		CreditCardDAO creditCardDAO = new CreditCardDAOImpl();
		creditCardDAO.delete(creditCardId);
//          /**3.刪除完成,準備轉交(Send the Success view)**/
		return "/buylistdetails/listAllCreditCard.jsp";
//          String url = "/creditCard/listAllCreditCard.jsp";
//          RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//          successView.forward(req, res);
	}

}
