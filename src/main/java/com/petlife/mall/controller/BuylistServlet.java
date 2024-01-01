package com.petlife.mall.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
import com.petlife.admin.entity.Coupon;
import com.petlife.mall.dao.BuylistDAO;
import com.petlife.mall.dao.impl.BuylistDAOImpl;
import com.petlife.mall.entity.Buylist;
import com.petlife.mall.entity.BuylistDetails;
import com.petlife.mall.entity.BuylistState;
import com.petlife.mall.service.BuylistService;
import com.petlife.mall.service.BuylistStateService;
import com.petlife.mall.service.impl.BuylistServiceImpl;
import com.petlife.mall.service.impl.BuylistStateServiceImpl;
import com.petlife.mall.service.impl.BuylistDetailsServiceImpl;
import com.petlife.mall.service.BuylistDetailsService;
import com.petlife.seller.entity.Seller;
import com.petlife.user.entity.User;
import com.petlife.util.MailService;

@WebServlet("/buylist/buylist.do")
@MultipartConfig
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
		res.setContentType("text/html; charset=UTF-8");
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
			System.out
					.println("==================================" + forwardPath + "=================================");
			break;
		case "delete":
			// 來自listAllBuylist.jsp
			forwardPath = delete(req, res);
			break; 
		case "getBuyListByMemberId":
			forwardPath = getBuyListByMemberId(req, res);
			break;
		case "getOneBuylistById":
			getOneBuylistById(req, res);
			break;
		case "cancelBuylist":
			forwardPath = cancelBuylist(req, res);
			break;
		case "memberRateBuylist":
			forwardPath = memberRateBuylist(req, res);
			break;
		case "sellerRateBuylist":
			forwardPath = sellerRateBuylist(req, res);
			break;
		case "showBuylistDetails":    //2023/12/27
		    // 來自 listAllBuylist.jsp 或其他頁面，用於顯示 buylistDetails
		    forwardPath = showBuylistDetails(req, res);
		    break;
		default:
			forwardPath = "/buylist/listAllBuylist.jsp"; // 2023/12/18
			break;
		}

		System.out.println(forwardPath);
		if (!forwardPath.isEmpty()) {
			RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
			dispatcher.forward(req, res);
		}
	}
//================顯示buylistDetails2023/12/27=========================
	private String showBuylistDetails(HttpServletRequest req, HttpServletResponse res) {
	    Integer buylistId = Integer.valueOf(req.getParameter("buylistId"));
	    
	    // 使用 BuylistDetailsServiceImpl 取得相應的 buylistDetails
	    BuylistDetailsService buylistDetailsService = new BuylistDetailsServiceImpl();
	    List<BuylistDetails> buylistDetailsList = buylistDetailsService.getAllBuylistDetailss(buylistId);
	    
	    req.setAttribute("buylistDetailsList", buylistDetailsList);
	    
	    return "/buylist/listOneBuylistDetails.jsp"; // 設定顯示 buylistDetails 的 JSP 頁面
	}
//================/顯示buylistDetails2023/12/27=========================
	private void getOneBuylistById(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Integer buylistId = Integer.valueOf(req.getParameter("buylistId"));
		Buylist buylist = buylistService.getBuylistByBuylistId(buylistId);
		res.setContentType("application/json; charset=UTF-8");
		PrintWriter out = res.getWriter();
		Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
		String buylistJson = gson.toJson(buylist);
		out.print(buylistJson);
	}

	private String memberRateBuylist(HttpServletRequest req, HttpServletResponse res) {
		Integer buylistId = Integer.valueOf(req.getParameter("retedBuylistId").trim());
		Integer memberId = Integer.valueOf(req.getParameter("ratedMemberId").trim());
		Double memberRatingStar = Double.valueOf(req.getParameter("ratedStar").trim());
		String rateReason = req.getParameter("rateReason").trim();

		Buylist buylist = buylistService.getBuylistByBuylistId(buylistId);
		buylist.setMemberRatingStars(memberRatingStar);
		buylist.setMemberEvaluateNarrative(rateReason);
		buylist.setMemberEvaluateTime(Timestamp.valueOf(LocalDateTime.now()));

		return "/buylist/buylist.do?action=getBuyListByMemberId&memberId=" + memberId;
	}
//賣家評價==============================================================
	private String sellerRateBuylist(HttpServletRequest req, HttpServletResponse res) {
		Integer buylistId = Integer.valueOf(req.getParameter("buylistId").trim());
		
		Double sellerRatingStars = Double.parseDouble(req.getParameter("sellerRatingStars"));
		String sellerEvaluateNarrative = req.getParameter("sellerEvaluateNarrative");
		
		Buylist buylist = buylistService.getBuylistByBuylistId(buylistId);
		buylist.setSellerRatingStars(sellerRatingStars);
		buylist.setSellerEvaluateNarrative(sellerEvaluateNarrative);
		buylist.setSellerEvaluateTime(Timestamp.valueOf(LocalDateTime.now()));
		
		return "/buylist/listAllBuylist.jsp";
	}
//賣家評價/==============================================================
	private String cancelBuylist(HttpServletRequest req, HttpServletResponse res) {
		Integer buylistId = Integer.valueOf(req.getParameter("buylistId").trim());
		Integer memberId = Integer.valueOf(req.getParameter("memberId").trim());
		String cancelReasonSelect = req.getParameter("cancelReasonResult").trim();
		String cancelReason;
		String sellerAcct = req.getParameter("sellerAcct").trim();

		switch (cancelReasonSelect) {
		case "1":
			cancelReason = "商品購買錯誤，想要重新選擇。";
			break;
		case "2":
			cancelReason = "已經不需要這些商品了，想要取消整筆訂單。";
			break;
		case "3":
			cancelReason = "有看到其他更便宜的商品，決定換購，想要取消整筆訂單。";
			break;
		default:
			cancelReason = req.getParameter("cancelReason").trim();
		}

		Buylist buylist = buylistService.getBuylistByBuylistId(buylistId);
		BuylistStateService buylistStateService = new BuylistStateServiceImpl();
		BuylistState buylistState = buylistStateService.getBuylistStateByBuylistStateId(4);

		buylist.setBuylistState(buylistState);
		buylistService.updateBuylist(buylist);

		Thread thread = new Thread(() -> {
			MailService.cancelBuylist(buylistId, sellerAcct, cancelReason);
		});
		thread.start();
		return "/buylist/buylist.do?action=getBuyListByMemberId&memberId=" + memberId;
	}

	private String getBuyListByMemberId(HttpServletRequest req, HttpServletResponse res) {
		String memberId = req.getParameter("memberId");
		System.out.println("===========================" + memberId + "============================");
		String forwardPath = "";
		List<Buylist> buylistList = new ArrayList<>();
		switch (memberId.charAt(0)) {
		case '1':
			buylistList = buylistService.getAllBuylists(memberId);
			forwardPath = "/member_center/order_management.jsp";
			break;
		case '2':
			buylistList = buylistService.getAllBuylists(memberId);
			forwardPath = "/buylist/listAllBuylist.jsp";
			break;
		}
		//================================================================
//		String sellerId = req.getParameter("sellerId");
//		System.out.println("===========================" + sellerId + "============================");
//		
//		
//		switch (sellerId.charAt(0)) {
//		case '1':
//			buylistList = buylistService.getAllBuylists(sellerId);
//			forwardPath = "/buylist/listAllBuylist.jsp";
//			break;
//		case '2':
//			buylistList = buylistService.getAllBuylists(sellerId);
//			forwardPath = "/buylist/listAllBuylist.jsp";
//			break;
//		}
		//================================================================
		req.setAttribute("getAllBuylist", buylistList);
		return forwardPath;
	}

	// 1,查詢
	private String getOneDisplay(HttpServletRequest req, HttpServletResponse res) {
		// 錯誤處理
		List<String> errorMsgs = new ArrayList<>();
		req.setAttribute("errorMsgs", errorMsgs);

///*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
		String str = req.getParameter("buylistId");
		// sellerId

		if (str == null || (str.trim()).length() == 0) {
			errorMsgs.add("請輸入訂單編號");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			return "/buylist/listAllBuylist.jsp";// 程式中斷
		}

		Integer buylistId = null;
		try {
			buylistId = Integer.valueOf(str);
		} catch (Exception e) {
			errorMsgs.add("訂單編號格式不正確");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			return "/buylist/listAllBuylist.jsp";// 程式中斷
		}

///*************************** 2.開始查詢資料 *****************************************/
//		BuylistService coupoService = new BuylistService();
		buylistService = new BuylistServiceImpl();
		Buylist buylist = buylistService.getBuylistByBuylistId(buylistId);
		// geyBySellerId(Integer sellerId)
		// List<Buylist> (from Buylist where
		// sellerId=:sellerId).setParameter("sellerId,sellerId")

		if (buylist == null) {
			errorMsgs.add("查無資料");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			return "/buylist/listAllBuylist.jsp";// 程式中斷
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
//		System.out.println("+++" + buylistId);

		Buylist buylist = buylistService.getBuylistByBuylistId(buylistId);
//		System.out.println(buylist);

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
//		Integer couponId = Integer.parseInt(req.getParameter("coupon"));
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

//		Coupon coupon = new Coupon();
//		coupon.setCouponId(couponId);
//		buylist.setCoupon(coupon);

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

		Integer couponId;
		try {
			couponId = Integer.parseInt(req.getParameter("coupon"));
		} catch (Exception e) {
			e.printStackTrace();
			couponId = null;
		}

		Double sellerRatingStars = Double.parseDouble(req.getParameter("sellerRatingStars"));
		String sellerEvaluateNarrative = req.getParameter("sellerEvaluateNarrative");
		// ----
//		System.out.println("sellerEvaluateTime String: " + req.getParameter("sellerEvaluateTime"));
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
//		System.out.println("buylistDate String: " + req.getParameter("buylistDate"));
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

		if(couponId==null) {
			buylist.setCoupon(null);
		}else {
			Coupon coupon = new Coupon();
			coupon.setCouponId(couponId);
			buylist.setCoupon(coupon);
		}

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
		return "/buylist/listAllBuylist.jsp";
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
