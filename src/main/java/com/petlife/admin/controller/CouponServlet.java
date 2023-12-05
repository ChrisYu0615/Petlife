package com.petlife.admin.controller;
//package com.petlife.coupon.servlet;
//
//import java.io.IOException;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.petlife.coupon.dao.CouponDAO;
//import com.petlife.coupon.dao.CouponDAOImpl;
//import com.petlife.coupon.entity.Coupon;
//import com.petlife.coupon.service.CouponService;
//import com.petlife.coupon.service.CouponServiceImpl;
//
//
//@WebServlet("/coupon/coupon.do")
//public class CouponServlet extends HttpServlet {
//	// 一個 servlet 實體對應一個 service 實體
//	private CouponService couponService;
//
//	@Override
//	public void init() throws ServletException {
//		couponService = new CouponServiceImpl();
//	}
//
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		doPost(req, res);
//	}
//
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//
//		req.setCharacterEncoding("UTF-8");
//		String action = req.getParameter("action");
//		String forwardPath = "";
//
//		switch (action) {
//		case "getOne_For_Display":
//			// 來自selact_page.jsp
//			forwardPath = getOneDisplay(req, res);
//			break;
//		case "getOne_For_Update":
//			// 來自listAllCoupon.jsp
//			forwardPath = getOneUpdate(req, res);
//			break;
//		case "update":
//			// 來自update_coupon_input.jsp
//			forwardPath = update(req, res);
//			break;
//		case "insert":
//			// 來自listAllCoupon.jsp
//			forwardPath = insert(req, res);
//			break;
//		case "delete":
//			// 來自listAllCoupon.jsp
//			forwardPath = delete(req, res);
//			break; // 新加的break
//		default:
//			forwardPath = "/select_page.jsp"; // !!!???
//		}
//
//		res.setContentType("text/html; charset=UTF-8");
//		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
//		dispatcher.forward(req, res);
//	}
//
//	// 1,查詢
//	private String getOneDisplay(HttpServletRequest req, HttpServletResponse res) {
//		// 錯誤處理
//		List<String> errorMsgs = new ArrayList<>();
//		req.setAttribute("errorMsgs", errorMsgs);
//
/////*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//		String str = req.getParameter("coupon_id"); // 注意需與MySQL一樣的table名
//
//		if (str == null || (str.trim()).length() == 0) {
//			errorMsgs.add("請輸入優惠券編號");
//		}
//		// Send the use back to the form, if there were errors
//		if (!errorMsgs.isEmpty()) {
//			return "/coupon/select_page.jsp";// 程式中斷
//		}
//
//		Integer couponId = null;
//		try {
//			couponId = Integer.valueOf(str);
//		} catch (Exception e) {
//			errorMsgs.add("優惠券編號格式不正確");
//		}
//		// Send the use back to the form, if there were errors
//		if (!errorMsgs.isEmpty()) {
//			return "/coupon/select_page.jsp";// 程式中斷
//		}
//
/////*************************** 2.開始查詢資料 *****************************************/
////		CouponService coupoService = new CouponService();
//		couponService = new CouponServiceImpl();
//		Coupon coupon = couponService.getCouponByCouponId(couponId);
//
//		if (coupon == null) {
//			errorMsgs.add("查無資料");
//		}
//		// Send the use back to the form, if there were errors
//		if (!errorMsgs.isEmpty()) {
//			return "/coupon/select_page.jsp";// 程式中斷
//		}
//
/////*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//		req.setAttribute("coupon", coupon); // 資料庫取出的manage物件,存入req
//		return "/coupon/listOneCoupon.jsp";	//20231128
//	}
//	// ===================================================
//
//	// 2,修改
//	private String getOneUpdate(HttpServletRequest req, HttpServletResponse res) {
////		System.out.println(req.getParameter("couponId"));
//		Integer couponId = Integer.valueOf(req.getParameter("coupon_id"));
//		System.out.println("+++" + couponId);
//
//		Coupon coupon = couponService.getCouponByCouponId(couponId);
//		System.out.println(coupon);
//
//		req.setAttribute("coupon", coupon);
//		return "/coupon/update_coupon_input.jsp";
//	}
//
//	private String update(HttpServletRequest req, HttpServletResponse res) {
//		// 錯誤處理
//		List<String> errorMsgs = new ArrayList<>();
//		req.setAttribute("errorMsgs", errorMsgs);
//
/////*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//		Integer couponId = Integer.parseInt(req.getParameter("coupon_id")); // 20231127
//		couponId= (couponId != null) ? couponId.intValue() : 0;  					//這行要刪
//		System.out.println("你有印出來嗎"+getInitParameter("coupon_id"));
//
//		String couponName = req.getParameter("coupon_name"); // 20231127
//		String couponNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//		if (couponName == null || couponName.trim().length() == 0) {
//			errorMsgs.add("管理員姓名: 請勿空白");
//		} else if (!couponName.trim().matches(couponNameReg)) { // 以下練習正則(規)表示式(regular-expression)
//			errorMsgs.add("管理員姓名: 只能是中文, 且長度必需大於2個字");
//		}
//
//		String couponContent = req.getParameter("couponContent");
////		System.out.println(req.getParameter("couponId"));
//		Integer conditionsOfUse = Integer.parseInt(req.getParameter("conditionsOfUse")); // 前端對應到他的name標籤
//
//		Timestamp startDate;
//		try {
//			startDate = java.sql.Timestamp.valueOf(req.getParameter("startDate").trim());
//		} catch (IllegalArgumentException e) {
//			startDate = new java.sql.Timestamp(System.currentTimeMillis());
//			errorMsgs.add("請輸入開始時間!");
//		}
//
//		Timestamp endDate;
//		try {
//			endDate = java.sql.Timestamp.valueOf(req.getParameter("endDate").trim());
//		} catch (IllegalArgumentException e) {
//			endDate = new java.sql.Timestamp(System.currentTimeMillis());
//			errorMsgs.add("請輸入管理員最後上線時間!");
//		}
//
//		Integer discountAmount = Integer.valueOf(req.getParameter("discountAmount"));
//
//		Coupon coupon = new Coupon();
//		coupon.setCouponId(couponId);
//		coupon.setCouponName(couponName);
//		coupon.setCouponContent(couponContent);
//		coupon.setConditionsOfUse(conditionsOfUse);
//		coupon.setStartDate(startDate);
//		coupon.setEndDate(endDate);
//		coupon.setDiscountAmount(discountAmount);
//
//		// Send the use back to the form, if there were errors
//		if (!errorMsgs.isEmpty()) {
//			req.setAttribute("coupon", coupon); // 含有輸入格式錯誤的manage物件,也存入req
//			return "/coupon/update_coupon_input.jsp"; // 程式中斷
//		}
//
/////*************************** 2.開始修改資料 *****************************************/
//		couponService.updateCoupon(coupon);
//		req.setAttribute("coupon", couponService.getCouponByCouponId(couponId));
//
/////*************************** 3.修改完成,準備轉交(Send the Success view) *************/
//		req.setAttribute("coupon", coupon); // 資料庫update成功後,正確的的manage物件,存入req
//		return "/coupon/listOneCoupon.jsp";
//	}
//
//	// ===================================================
//
//	// 3,新增
//	private String insert(HttpServletRequest req, HttpServletResponse res) {
//		// 錯誤處理
//		
//		List<String> errorMsgs = new ArrayList<>();
//		req.setAttribute("errorMsgs", errorMsgs);
////		/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
//		String couponId = req.getParameter("couponId");
//		String couponName = req.getParameter("couponName");
//		String couponNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$"; // 英文數字中文
//		if (couponName == null || couponName.trim().length() == 0) {
//			errorMsgs.add("名稱格式不對");
//		} else if (!couponName.trim().matches(couponNameReg)) { // 以下練習正則(規)表示式(regular-expression)
//			errorMsgs.add("優惠券名稱: 長度必需大於2個字");
//		}
//
//		String couponContent = req.getParameter("couponContent");
//
//		Integer conditionsOfUse = Integer.valueOf(req.getParameter("conditionsOfUse"));
//
//		Timestamp startDate = null;
//		try {
//			startDate = java.sql.Timestamp.valueOf(req.getParameter("startDate").trim());
//		} catch (IllegalArgumentException e) {
//			startDate = new java.sql.Timestamp(System.currentTimeMillis());
//			errorMsgs.add("請輸入開始時間!");
//		}
//
//		java.sql.Timestamp endDate = null;
//		try {
//			endDate = java.sql.Timestamp.valueOf(req.getParameter("endDate").trim());
//		} catch (IllegalArgumentException e) {
//			endDate = new java.sql.Timestamp(System.currentTimeMillis());
//			errorMsgs.add("請輸入結束時間!");
//		}
//
//		Integer discountAmount = Integer.valueOf(req.getParameter("discountAmount"));
//
//		Coupon coupon = new Coupon();
//		coupon.setCouponName(couponName);
//		coupon.setCouponContent(couponContent);
//		coupon.setConditionsOfUse(conditionsOfUse);
//		coupon.setStartDate(startDate);
//		coupon.setEndDate(endDate);
//		coupon.setDiscountAmount(discountAmount);
//
//		if (coupon.getCouponId() != null && coupon.getCouponId() > 0) {
//		    System.out.println("新增成功");
//		} else {
//		    System.out.println("新增失敗");
//		}
//		
//		// Send the use back to the form, if there were errors
//		if (!errorMsgs.isEmpty()) {
//			req.setAttribute("coupon", coupon); // 含有輸入格式錯誤的empVO物件,也存入req
//			return "/coupon/addCoupon.jsp";
//		}
//
////		/*************************** 2.開始新增資料 ***************************************/
//		System.out.println("1");
//		couponService.addCoupon(coupon);
////		CouponDAO couponDAO = new CouponDAOImpl();
////		couponDAO.update(coupon);
//		if (coupon.getCouponId() != null && coupon.getCouponId() > 0) {
//		    System.out.println("新增成功2");
//		} else {
//		    System.out.println("新增失敗2");
//		}
//
////		/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//		return "/coupon/listOneCoupon.jsp";
//	}
//
//	// ===================================================
//
//	// 4,刪除
//	private String delete(HttpServletRequest req, HttpServletResponse res) {
////		  List<String> errorMsgs = new List<String>();
//		// Store this set in the request scope, in case we need to
//		// send the ErrorPage view.
////          req.setAttribute("errorMsgs", errorMsgs);
//
////          /**1.接收請求參數**/
//		Integer couponId = Integer.valueOf(req.getParameter("couponId"));
//
////          /**2.開始刪除資料**/
//		CouponDAO couponDAO = new CouponDAOImpl();
//		couponDAO.delete(couponId);
//
////          /**3.刪除完成,準備轉交(Send the Success view)**/
//		return "/coupon/listAllCoupon.jsp";
////          String url = "/coupon/listAllCoupon.jsp";
////          RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
////          successView.forward(req, res);
//	}
//
//}
