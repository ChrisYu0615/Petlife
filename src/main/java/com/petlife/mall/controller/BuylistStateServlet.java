package com.petlife.mall.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petlife.mall.dao.BuylistStateDAO;
import com.petlife.mall.dao.impl.BuylistStateDAOImpl;
import com.petlife.mall.entity.BuylistState;
import com.petlife.mall.service.BuylistStateService;
import com.petlife.mall.service.impl.BuylistStateServiceImpl;



@WebServlet("/buyliststate/buyliststate.do")
public class BuylistStateServlet extends HttpServlet {
	// 一個 servlet 實體對應一個 service 實體
	private BuylistStateService buylistStateService;

	@Override
	public void init() throws ServletException {
		buylistStateService = new BuylistStateServiceImpl();
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
			// 來自listAllBuylistState.jsp
			forwardPath = getOneUpdate(req, res);
			break;
		case "update":
			// 來自update_buylistState_input.jsp
			forwardPath = update(req, res);
			break;
		case "insert":
			// 來自listAllBuylistState.jsp
			forwardPath = insert(req, res);
			break;
		case "delete":
			// 來自listAllBuylistState.jsp
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
		String str = req.getParameter("buylistStateId");

		if (str == null || (str.trim()).length() == 0) {
			errorMsgs.add("請輸入訂單狀態編號");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			return "/buyliststate/select_page.jsp";// 程式中斷
		}

		Integer buylistStateId = null;
		try {
			buylistStateId = Integer.valueOf(str);
		} catch (Exception e) {
			errorMsgs.add("訂單狀態編號格式不正確");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			return "/buyliststate/select_page.jsp";// 程式中斷
		}

///*************************** 2.開始查詢資料 *****************************************/
//		BuylistStateService coupoService = new BuylistStateService();
		buylistStateService = new BuylistStateServiceImpl();
		BuylistState buylistState = buylistStateService.getBuylistStateByBuylistStateId(buylistStateId);

		if (buylistState == null) {
			errorMsgs.add("查無資料");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			return "/buyliststate/select_page.jsp";// 程式中斷
		}

///*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
		req.setAttribute("buylistState", buylistState); // 資料庫取出的manage物件,存入req
		return "/buyliststate/listOneBuylistState.jsp"; // 20231128
	}
	// ===================================================

	// 2,修改
	private String getOneUpdate(HttpServletRequest req, HttpServletResponse res) {
//		System.out.println(req.getParameter("buylistStateId"));
		Integer buylistStateId = Integer.valueOf(req.getParameter("buylistStateId")); // 20231206
		System.out.println("+++" + buylistStateId);

		BuylistState buylistState = buylistStateService.getBuylistStateByBuylistStateId(buylistStateId);
		System.out.println(buylistState);

		req.setAttribute("buylistState", buylistState);
		return "/buyliststate/update_buylistState_input.jsp";
	}

	private String update(HttpServletRequest req, HttpServletResponse res) {
		// 錯誤處理
		List<String> errorMsgs = new ArrayList<>();
		req.setAttribute("errorMsgs", errorMsgs);

///*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
		Integer buylistStateId = Integer.parseInt(req.getParameter("buylistStateId")); // 20231127
//		buylistStateId = (buylistStateId != null) ? buylistStateId.intValue() : 0; // 這行要刪
		System.out.println(req.getParameter("buylistStateId"));

		String buylistStateName = req.getParameter("buylistStateName"); // 20231127
		String buylistStateNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
		if (buylistStateName == null || buylistStateName.trim().length() == 0) {
			errorMsgs.add("訂單狀態名稱: 請勿空白");
		} else if (!buylistStateName.trim().matches(buylistStateNameReg)) { // 以下練習正則(規)表示式(regular-expression)
			errorMsgs.add("訂單狀態名稱: 長度必需大於2個字");
		}
		

		BuylistState buylistState = new BuylistState();
		buylistState.setBuylistStateId(buylistStateId);
		buylistState.setBuylistStateName(buylistStateName);

		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			req.setAttribute("buylistState", buylistState); // 含有輸入格式錯誤的manage物件,也存入req
			return "/buyliststate/update_buylistState_input.jsp"; // 程式中斷
		}

///*************************** 2.開始修改資料 *****************************************/
		buylistStateService.updateBuylistState(buylistState);
		req.setAttribute("buylistState", buylistStateService.getBuylistStateByBuylistStateId(buylistStateId));

///*************************** 3.修改完成,準備轉交(Send the Success view) *************/
		req.setAttribute("buylistState", buylistState); // 資料庫update成功後,正確的的manage物件,存入req
		return "/buyliststate/listOneBuylistState.jsp";
	}

	// ===================================================

	// 3,新增
	private String insert(HttpServletRequest req, HttpServletResponse res) {
		// 錯誤處理

		List<String> errorMsgs = new ArrayList<>();
		req.setAttribute("errorMsgs", errorMsgs);
//		/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
		Integer buylistStateId = Integer.valueOf(req.getParameter("buylistStateId"));
		String buylistStateName = req.getParameter("buylistStateName");
		System.out.println(req.getParameter("buylistStateName"));
		String buylistStateNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$"; // 英文數字中文
		if (buylistStateName == null || buylistStateName.trim().length() == 0) {
			errorMsgs.add("名稱格式不對");
		} else if (!buylistStateName.trim().matches(buylistStateNameReg)) { // 以下練習正則(規)表示式(regular-expression)
			errorMsgs.add("訂單狀態名稱: 長度必需大於2個字");
		}

	
		BuylistState buylistState = new BuylistState();
		buylistState.setBuylistStateId(buylistStateId);
		buylistState.setBuylistStateName(buylistStateName);

//		/*************************** 2.開始新增資料 ***************************************/
		
		try {
			System.out.println("1");
			buylistStateService.addBuylistState(buylistState);
			}catch (Exception e) {
				System.out.println("2");
				e.getMessage();
			};

//		/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
		return "/buyliststate/select_page.jsp";
	}

	// ===================================================

	// 4,刪除
	private String delete(HttpServletRequest req, HttpServletResponse res) {

//          /**1.接收請求參數**/
		Integer buylistStateId = Integer.valueOf(req.getParameter("buylistStateId"));

//          /**2.開始刪除資料**/
		BuylistStateDAO buylistStateDAO = new BuylistStateDAOImpl();
		buylistStateDAO.delete(buylistStateId);

//          /**3.刪除完成,準備轉交(Send the Success view)**/
		return "/buyliststate/listAllBuylistState.jsp";
	}

}
