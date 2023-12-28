package com.petlife.mall.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
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
import com.petlife.mall.dao.BuylistDetailsDAO;
import com.petlife.mall.dao.impl.BuylistDetailsDAOImpl;
import com.petlife.mall.entity.Buylist;
import com.petlife.mall.entity.BuylistDetails;
import com.petlife.mall.entity.Comm;
import com.petlife.mall.service.BuylistDetailsService;
import com.petlife.mall.service.impl.BuylistDetailsServiceImpl;

@WebServlet("/buylistdetails/buylistdetails.do")
@MultipartConfig
public class BuylistDetailsServlet extends HttpServlet {
	// 一個 servlet 實體對應一個 service 實體
	private BuylistDetailsService buylistDetailsService;

	@Override
	public void init() throws ServletException {
		buylistDetailsService = new BuylistDetailsServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=UTF-8");

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String forwardPath = "";

		switch (action) {
		case "getOne_For_Display":
			// 來自selact_page.jsp
			forwardPath = getOneDisplay(req, res);
			break;
		case "getOne_For_Update":
			// 來自listAllBuylistDetails.jsp
			forwardPath = getOneUpdate(req, res);
			break;
		case "update":
			// 來自update_buylistDetails_input.jsp
			forwardPath = update(req, res);
			break;
		case "insert":
			// 來自listAllBuylistDetails.jsp
			forwardPath = insert(req, res);
			break;
		case "delete":
			// 來自listAllBuylistDetails.jsp
			forwardPath = delete(req, res);
			break; // 新加的break
		case "getBuylistDetailsById":
			getBuylistById(req, res);
			break;
		default:
			forwardPath = "/select_page.jsp";
		}

		if (!forwardPath.isEmpty()) {
			RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
			dispatcher.forward(req, res);
		}
	}

	// 1,查詢
	private String getOneDisplay(HttpServletRequest req, HttpServletResponse res) {
		// 錯誤處理
		List<String> errorMsgs = new ArrayList<>();
		req.setAttribute("errorMsgs", errorMsgs);

///*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
		String str = req.getParameter("buylistDetailsId");

		if (str == null || (str.trim()).length() == 0) {
			errorMsgs.add("請輸入優惠券編號");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			return "/buylistdetails/select_page.jsp";// 程式中斷
		}

		Integer buylistDetailsId = null;
		try {
			buylistDetailsId = Integer.valueOf(str);
		} catch (Exception e) {
			errorMsgs.add("優惠券編號格式不正確");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			return "/buylistdetails/select_page.jsp";// 程式中斷
		}

///*************************** 2.開始查詢資料 *****************************************/
//		BuylistDetailsService coupoService = new BuylistDetailsService();
		buylistDetailsService = new BuylistDetailsServiceImpl();
		BuylistDetails buylistDetails = buylistDetailsService.getBuylistDetailsByBuylistDetailsId(buylistDetailsId);

		if (buylistDetails == null) {
			errorMsgs.add("查無資料");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			return "/buylistdetails/select_page.jsp";// 程式中斷
		}

///*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
		req.setAttribute("buylistDetails", buylistDetails); // 資料庫取出的manage物件,存入req
		return "/buylistdetails/listOneBuylistDetails.jsp"; // 20231128
	}
	// ===================================================

	// 2,修改
	private String getOneUpdate(HttpServletRequest req, HttpServletResponse res) {
//		System.out.println(req.getParameter("buylistDetailsId"));
		Integer buylistDetailsId = Integer.valueOf(req.getParameter("buylistDetailsId")); // 20231206
//		System.out.println("+++" + buylistDetailsId);

		BuylistDetails buylistDetails = buylistDetailsService.getBuylistDetailsByBuylistDetailsId(buylistDetailsId);
//		System.out.println(buylistDetails);

		req.setAttribute("buylistDetails", buylistDetails);
		return "/buylistdetails/update_buylistDetails_input.jsp";
	}

	private String update(HttpServletRequest req, HttpServletResponse res) {
		// 錯誤處理
		List<String> errorMsgs = new ArrayList<>();
		req.setAttribute("errorMsgs", errorMsgs);

///*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
		Integer buylistDetailsId = Integer.parseInt(req.getParameter("buylistDetailsId"));

		Integer buylistId = Integer.parseInt(req.getParameter("buylistId"));
		Integer commId = Integer.parseInt(req.getParameter("comm"));
//		Integer comm = Integer.parseInt(req.getParameter("comm"));

		BigDecimal buylistDetailsPrice;
		try {
			// 假設 req 是 HttpServletRequest 物件
			String buylistDetailsPriceStr = req.getParameter("buylistDetailsPrice");

			// 將字串轉換為 BigDecimal
			buylistDetailsPrice = new BigDecimal(buylistDetailsPriceStr);

			// 設定默認值，保留兩位小數
			buylistDetailsPrice = buylistDetailsPrice.setScale(2, RoundingMode.HALF_UP);

			// 在這裡您可以使用 buylistDetailsPrice 進行後續的操作
		} catch (NumberFormatException e) {
			// 處理轉換失敗的情況，例如記錄錯誤或提供默認值
			e.printStackTrace();
			// 例如，提供默認值
			buylistDetailsPrice = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
		}

		Integer buylistDetailsPurchaseAmount = Integer.parseInt(req.getParameter("buylistDetailsPurchaseAmount"));
		Double memberRatingStars = Double.parseDouble(req.getParameter("memberRatingStars"));
		String buyerEvaluateNarrative = req.getParameter("buyerEvaluateNarrative");
		Timestamp buyerEvaluateTime;
		try {
			buyerEvaluateTime = java.sql.Timestamp.valueOf(req.getParameter("buyerEvaluateTime").trim());
		} catch (IllegalArgumentException e) {
			buyerEvaluateTime = new java.sql.Timestamp(System.currentTimeMillis());
			errorMsgs.add("請輸入買家評價時間!");
		}
		String returnReasons = req.getParameter("returnReasons");

//		String buylistDetailsName = req.getParameter("buylistDetailsName"); // 20231127
//		System.out.println(req.getParameter("buylistDetailsName"));
//		String buylistDetailsNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//		if (buylistDetailsName == null || buylistDetailsName.trim().length() == 0) {
//			errorMsgs.add("優惠碼名稱: 請勿空白");
//		} else if (!buylistDetailsName.trim().matches(buylistDetailsNameReg)) { // 以下練習正則(規)表示式(regular-expression)
//			errorMsgs.add("優惠碼名稱: 長度必需大於2個字");
//		}

//============================================

		BuylistDetails buylistDetails = new BuylistDetails();
		buylistDetails.setBuylistDetailsId(buylistDetailsId);

//		buylistDetails.setBuylist(buylist);
		Buylist buylist = new Buylist();
		buylist.setBuylistId(buylistId);
		buylistDetails.setBuylist(buylist);

		Comm comm = new Comm();
		comm.setCommId(commId);
		buylistDetails.setComm(comm);

		buylistDetails.setBuylistDetailsPrice(buylistDetailsPrice);
		buylistDetails.setBuylistDetailsPurchaseAmount(buylistDetailsPurchaseAmount);


//==========================================		
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			req.setAttribute("buylistDetails", buylistDetails); // 含有輸入格式錯誤的manage物件,也存入req
			return "/buylistdetails/update_buylistDetails_input.jsp"; // 程式中斷
		}

///*************************** 2.開始修改資料 *****************************************/
		buylistDetailsService.updateBuylistDetails(buylistDetails);
		req.setAttribute("buylistDetails", buylistDetailsService.getBuylistDetailsByBuylistDetailsId(buylistDetailsId));

///*************************** 3.修改完成,準備轉交(Send the Success view) *************/
		req.setAttribute("buylistDetails", buylistDetails); // 資料庫update成功後,正確的的manage物件,存入req
		return "/buylistdetails/listOneBuylistDetails.jsp";
	}

	// ===================================================

	// 3,新增
	private String insert(HttpServletRequest req, HttpServletResponse res) {
		// 錯誤處理

		List<String> errorMsgs = new ArrayList<>();
		req.setAttribute("errorMsgs", errorMsgs);
//		/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

		String buylistDetailsName = req.getParameter("buylistDetailsName");
//		System.out.println(req.getParameter("buylistDetailsName"));
		String buylistDetailsNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$"; // 英文數字中文
//		if (buylistDetailsName == null || buylistDetailsName.trim().length() == 0) {
//			errorMsgs.add("名稱格式不對");
//		} else if (!buylistDetailsName.trim().matches(buylistDetailsNameReg)) { // 以下練習正則(規)表示式(regular-expression)
//			errorMsgs.add("名稱: 長度必需大於2個字");
//		}
		Integer buylistId = Integer.parseInt(req.getParameter("buylist"));
		Integer commId = Integer.parseInt(req.getParameter("comm"));

		BigDecimal buylistDetailsPrice;
		try {
			// 假設 req 是 HttpServletRequest 物件
			String buylistDetailsPriceStr = req.getParameter("buylistDetailsPrice");

			// 將字串轉換為 BigDecimal
			buylistDetailsPrice = new BigDecimal(buylistDetailsPriceStr);

			// 設定默認值，保留兩位小數
			buylistDetailsPrice = buylistDetailsPrice.setScale(2, RoundingMode.HALF_UP);

			// 在這裡您可以使用 buylistDetailsPrice 進行後續的操作
		} catch (NumberFormatException e) {
			// 處理轉換失敗的情況，例如記錄錯誤或提供默認值
			e.printStackTrace();
			// 例如，提供默認值
			buylistDetailsPrice = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
		}

		Integer buylistDetailsPurchaseAmount = Integer.parseInt(req.getParameter("buylistDetailsPurchaseAmount"));
		Double memberRatingStars = Double.parseDouble(req.getParameter("memberRatingStars"));
		String buyerEvaluateNarrative = req.getParameter("buyerEvaluateNarrative");

		Timestamp buyerEvaluateTime;
		try {
			buyerEvaluateTime = java.sql.Timestamp.valueOf(req.getParameter("buyerEvaluateTime").trim());
		} catch (IllegalArgumentException e) {
			buyerEvaluateTime = new java.sql.Timestamp(System.currentTimeMillis());
			errorMsgs.add("請輸入買家評價時間!");
		}
		String returnReasons = req.getParameter("returnReasons");

		// ============================================

		BuylistDetails buylistDetails = new BuylistDetails();

//				buylistDetails.setBuylist(buylistId);
		Buylist buylist = new Buylist();
		buylist.setBuylistId(buylistId);
		buylistDetails.setBuylist(buylist);
//				
		Comm comm = new Comm();
		comm.setCommId(commId);
		buylistDetails.setComm(comm);

		buylistDetails.setBuylistDetailsPrice(buylistDetailsPrice);
		buylistDetails.setBuylistDetailsPurchaseAmount(buylistDetailsPurchaseAmount);

		// ==========================================

//		/*************************** 2.開始新增資料 ***************************************/

		try {
			System.out.println("1");
			buylistDetailsService.addBuylistDetails(buylistDetails);
		} catch (Exception e) {
			System.out.println("2");
			e.getMessage();
		}
		;

//		if (buylistDetails.getBuylistDetailsId() != null && buylistDetails.getBuylistDetailsId() > 0) {
//			System.out.println("新增成功2");
//		} else {
//			System.out.println("新增失敗2");
//		}

//		/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
		return "/buylistdetails/select_page.jsp";
	}

	// ===================================================

	// 4,刪除
	private String delete(HttpServletRequest req, HttpServletResponse res) {
//		  List<String> errorMsgs = new List<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
//          req.setAttribute("errorMsgs", errorMsgs);

//          /**1.接收請求參數**/
		Integer buylistDetailsId = Integer.valueOf(req.getParameter("buylistDetailsId"));

//          /**2.開始刪除資料**/
		BuylistDetailsDAO buylistDetailsDAO = new BuylistDetailsDAOImpl();
		buylistDetailsDAO.delete(buylistDetailsId);
//          /**3.刪除完成,準備轉交(Send the Success view)**/
		return "/buylistdetails/listAllBuylistDetails.jsp";
//          String url = "/buylistDetails/listAllBuylistDetails.jsp";
//          RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//          successView.forward(req, res);
	}

	private void getBuylistById(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("application/json; charset=UTF-8");
		PrintWriter out = res.getWriter();

		Integer buylistId = Integer.valueOf(req.getParameter("buylistId"));
		List<BuylistDetails> buylistDetailsList = buylistDetailsService.getAllBuylistDetailss(buylistId);
		Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
		String buylistJson = gson.toJson(buylistDetailsList);
		out.print(buylistJson);
	}

}
