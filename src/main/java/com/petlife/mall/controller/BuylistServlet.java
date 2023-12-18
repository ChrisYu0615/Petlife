package com.petlife.mall.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petlife.admin.entity.Coupon;
import com.petlife.mall.dao.BuylistDAO;
import com.petlife.mall.dao.impl.BuylistDAOImpl;
import com.petlife.mall.entity.Buylist;
import com.petlife.mall.entity.BuylistState;
import com.petlife.mall.service.BuylistService;
import com.petlife.mall.service.impl.BuylistServiceImpl;
import com.petlife.seller.entity.Seller;
import com.petlife.user.entity.User;

@WebServlet("/buylist/buylist.do")
public class BuylistServlet extends HttpServlet {
	// 一個 servlet 實體對應一個 service 實體
	private BuylistService buylistService;

	@Override
	public void init() throws ServletException {
		buylistService = new BuylistServiceImpl();
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
			// 來自listAllBuylist.jsp
			forwardPath = getOneUpdate(req, res);
			break;
		case "update":
			// 來自update_buylist_input.jsp
			forwardPath = update(req, res);
			break;
		case "insert":
			// 來自listAllBuylist.jsp
			forwardPath = insert(req, res);
			break;
		case "delete":
			// 來自listAllBuylist.jsp
			forwardPath = delete(req, res);
			break; // 新加的break
		default:
			forwardPath = "/buylist/select_page.jsp"; //2023/12/18
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
		String str = req.getParameter("buylistId");

		if (str == null || (str.trim()).length() == 0) {
			errorMsgs.add("請輸入訂單編號");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			return "/buylist/select_page.jsp";// 程式中斷
		}

		Integer buylistId = null;
		try {
			buylistId = Integer.valueOf(str);
		} catch (Exception e) {
			errorMsgs.add("訂單編號格式不正確");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			return "/buylist/select_page.jsp";// 程式中斷
		}

///*************************** 2.開始查詢資料 *****************************************/
//		BuylistService coupoService = new BuylistService();
		buylistService = new BuylistServiceImpl();
		Buylist buylist = buylistService.getBuylistByBuylistId(buylistId);

		if (buylist == null) {
			errorMsgs.add("查無資料");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			return "/buylist/select_page.jsp";// 程式中斷
		}

///*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
		req.setAttribute("buylist", buylist); // 資料庫取出的manage物件,存入req
		return "/buylist/listOneBuylist.jsp"; // 20231128
	}
	// ===================================================

	// 2,修改
	private String getOneUpdate(HttpServletRequest req, HttpServletResponse res) {
//		System.out.println(req.getParameter("buylistId"));
		Integer buylistId = Integer.valueOf(req.getParameter("buylistId")); // 20231206
		System.out.println("+++" + buylistId);

		Buylist buylist = buylistService.getBuylistByBuylistId(buylistId);
		System.out.println(buylist);

		req.setAttribute("buylist", buylist);
		return "/buylist/update_buylist_input.jsp";
	}

	private String update(HttpServletRequest req, HttpServletResponse res) {
		// 錯誤處理
		List<String> errorMsgs = new ArrayList<>();
		req.setAttribute("errorMsgs", errorMsgs);

///*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
		Integer buylistId = Integer.parseInt(req.getParameter("buylistId"));
		Integer userId = Integer.parseInt(req.getParameter("user"));
		Integer sellerId = Integer.parseInt(req.getParameter("seller"));
		Integer buylistStateId = Integer.parseInt(req.getParameter("buylistState"));
		Integer couponId = Integer.parseInt(req.getParameter("coupon"));
		Double sellerRatingStars = Double.parseDouble(req.getParameter("sellerRatingStars"));
		String sellerEvaluateNarrative = req.getParameter("sellerEvaluateNarrative");
		Timestamp sellerEvaluateTime = java.sql.Timestamp.valueOf(req.getParameter("sellerEvaluateTime").trim());
//		Timestamp sellerEvaluateTime;
//		try {
//			sellerEvaluateTime = java.sql.Timestamp.valueOf(req.getParameter("sellerEvaluateTime").trim());
//		} catch (IllegalArgumentException e) {
//			sellerEvaluateTime = new java.sql.Timestamp(System.currentTimeMillis());
//			errorMsgs.add("請輸入買家評價時間!");
//		}
		
		BigDecimal buylistAmount;
		try {
			// 假設 req 是 HttpServletRequest 物件
			String buylistAmountStr = req.getParameter("buylistAmount");
			
			// 將字串轉換為 BigDecimal
			buylistAmount = new BigDecimal(buylistAmountStr);
			
			// 設定默認值，保留兩位小數
			buylistAmount = buylistAmount.setScale(2, RoundingMode.HALF_UP);
			
			// 在這裡您可以使用 buylistPrice 進行後續的操作
		} catch (NumberFormatException e) {
			// 處理轉換失敗的情況，例如記錄錯誤或提供默認值
			e.printStackTrace();
			// 例如，提供默認值
			buylistAmount = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
		}
		
		
		Timestamp buylistDate = java.sql.Timestamp.valueOf(req.getParameter("buylistDate").trim());

//		Timestamp buylistDate;
//		try {
//			buylistDate = java.sql.Timestamp.valueOf(req.getParameter("buylistDate").trim());
//		} catch (IllegalArgumentException e) {
//			buylistDate = new java.sql.Timestamp(System.currentTimeMillis());
//			errorMsgs.add("請輸入買家評價時間!");
//		}
		


//============================================

		Buylist buylist = new Buylist();
		buylist.setBuylistId(buylistId);

		User user = new User();
		user.setUserId(userId);
		buylist.setUser(user);

		Seller seller = new Seller();
		seller.setSellerId(sellerId);
		buylist.setSeller(seller);

		BuylistState buylistState = new BuylistState();
		buylistState.setBuylistStateId(buylistStateId);
		buylist.setBuylistState(buylistState);
		
		Coupon coupon = new Coupon();
		coupon.setCouponId(couponId);
		buylist.setCoupon(coupon);
		
		buylist.setSellerRatingStars(sellerRatingStars);
		buylist.setSellerEvaluateNarrative(sellerEvaluateNarrative);
		buylist.setSellerEvaluateTime(sellerEvaluateTime);

		buylist.setBuylistAmount(buylistAmount);
		buylist.setBuylistDate(buylistDate);

//==========================================		
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			req.setAttribute("buylist", buylist); // 含有輸入格式錯誤的manage物件,也存入req
			return "/buylist/update_buylist_input.jsp"; // 程式中斷
		}

///*************************** 2.開始修改資料 *****************************************/
		buylistService.updateBuylist(buylist);
		req.setAttribute("buylist", buylistService.getBuylistByBuylistId(buylistId));

///*************************** 3.修改完成,準備轉交(Send the Success view) *************/
		req.setAttribute("buylist", buylist); // 資料庫update成功後,正確的的manage物件,存入req
		return "/buylist/listOneBuylist.jsp";
	}

	// ===================================================

	// 3,新增
	private String insert(HttpServletRequest req, HttpServletResponse res) {
		// 錯誤處理

		List<String> errorMsgs = new ArrayList<>();
		req.setAttribute("errorMsgs", errorMsgs);
//		/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

//		Integer buylistId = Integer.parseInt(req.getParameter("buylistId"));
		Integer userId = Integer.parseInt(req.getParameter("user"));
		Integer sellerId = Integer.parseInt(req.getParameter("seller"));
		Integer buylistStateId = Integer.parseInt(req.getParameter("buylistState"));
		Integer couponId = Integer.parseInt(req.getParameter("coupon"));
		Double sellerRatingStars = Double.parseDouble(req.getParameter("sellerRatingStars"));
		String sellerEvaluateNarrative = req.getParameter("sellerEvaluateNarrative");
		//----
		System.out.println("sellerEvaluateTime String: " + req.getParameter("sellerEvaluateTime"));
		Timestamp sellerEvaluateTime = java.sql.Timestamp.valueOf(req.getParameter("sellerEvaluateTime").trim());
		
//		Timestamp sellerEvaluateTime;
//		try {
//			sellerEvaluateTime = java.sql.Timestamp.valueOf(req.getParameter("sellerEvaluateTime").trim());
//		} catch (IllegalArgumentException e) {
//			sellerEvaluateTime = new java.sql.Timestamp(System.currentTimeMillis());
//			errorMsgs.add("請輸入賣家評價時間!");
//		}
		
		BigDecimal buylistAmount;
		try {
			// 假設 req 是 HttpServletRequest 物件
			String buylistAmountStr = req.getParameter("buylistAmount");
			
			// 將字串轉換為 BigDecimal
			buylistAmount = new BigDecimal(buylistAmountStr);
			
			// 設定默認值，保留兩位小數
			buylistAmount = buylistAmount.setScale(2, RoundingMode.HALF_UP);
			
			// 在這裡您可以使用 buylistPrice 進行後續的操作
		} catch (NumberFormatException e) {
			// 處理轉換失敗的情況，例如記錄錯誤或提供默認值
			e.printStackTrace();
			// 例如，提供默認值
			buylistAmount = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
		}
		System.out.println("buylistDate String: " + req.getParameter("buylistDate"));
		Timestamp buylistDate = java.sql.Timestamp.valueOf(req.getParameter("buylistDate").trim());
//		Timestamp buylistDate = java.sql.Timestamp.valueOf(req.getParameter("buylistDate"));
//		Timestamp buylistDate;
//		try {
//			buylistDate = java.sql.Timestamp.valueOf(req.getParameter("buylistDate").trim());
//		} catch (IllegalArgumentException e) {
//			buylistDate = new java.sql.Timestamp(System.currentTimeMillis());
//			errorMsgs.add("請輸入訂單成立時間!");
//		}

		// ============================================

		Buylist buylist = new Buylist();
//		buylist.setBuylistId(buylistId);

		User user = new User();
		user.setUserId(userId);
		buylist.setUser(user);

		Seller seller = new Seller();
		seller.setSellerId(sellerId);
		buylist.setSeller(seller);

		BuylistState buylistState = new BuylistState();
		buylistState.setBuylistStateId(buylistStateId);
		buylist.setBuylistState(buylistState);
		
		Coupon coupon = new Coupon();
		coupon.setCouponId(couponId);
		buylist.setCoupon(coupon);
		
		buylist.setSellerRatingStars(sellerRatingStars);
		buylist.setSellerEvaluateNarrative(sellerEvaluateNarrative);
		buylist.setSellerEvaluateTime(sellerEvaluateTime);

		buylist.setBuylistAmount(buylistAmount);
		buylist.setBuylistDate(buylistDate);

		// ==========================================

//		/*************************** 2.開始新增資料 ***************************************/

		try {
			System.out.println("新增成功");
			buylistService.addBuylist(buylist);
		} catch (Exception e) {
			System.out.println("新增失敗" + e.getMessage());
			e.getMessage();
		}
		;

//		if (buylist.getBuylistId() != null && buylist.getBuylistId() > 0) {
//			System.out.println("新增成功2");
//		} else {
//			System.out.println("新增失敗2");
//		}

//		/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
		return "/buylist/select_page.jsp";
	}

	// ===================================================

	// 4,刪除
	private String delete(HttpServletRequest req, HttpServletResponse res) {
//		  List<String> errorMsgs = new List<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
//          req.setAttribute("errorMsgs", errorMsgs);

//          /**1.接收請求參數**/
		Integer buylistId = Integer.valueOf(req.getParameter("buylistId"));

//          /**2.開始刪除資料**/
		BuylistDAO buylistDAO = new BuylistDAOImpl();
		buylistDAO.delete(buylistId);
//          /**3.刪除完成,準備轉交(Send the Success view)**/
		return "/buylist/listAllBuylist.jsp";
//          String url = "/buylist/listAllBuylist.jsp";
//          RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//          successView.forward(req, res);
	}

}
