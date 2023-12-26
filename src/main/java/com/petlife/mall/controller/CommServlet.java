package com.petlife.mall.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petlife.mall.dao.CommDAO;
import com.petlife.mall.dao.impl.CommDAOImpl;
import com.petlife.mall.entity.Buylist;
import com.petlife.mall.entity.Comm;
import com.petlife.mall.entity.CommCat;
import com.petlife.mall.service.CommService;
import com.petlife.mall.service.impl.CommServiceImpl;
import com.petlife.seller.entity.Seller;
import com.petlife.user.entity.User;

@WebServlet("/comm/comm.do")
@MultipartConfig
public class CommServlet extends HttpServlet {
	// 一個 servlet 實體對應一個 service 實體
	private CommService commService;

	@Override
	public void init() throws ServletException {
		commService = new CommServiceImpl();
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
			// 來自select_page.jsp
			forwardPath = getOneDisplay(req, res);
			break;
		case "getOne_For_Update":
			// 來自listAllComm.jsp
			forwardPath = getOneUpdate(req, res);
			break;
		case "update":
			// 來自update_comm_input.jsp
			forwardPath = update(req, res);
			break;
		case "insert":
			// 來自listAllComm.jsp
			forwardPath = insert(req, res);
			break;
		case "delete":
			// 來自listAllComm.jsp
			forwardPath = delete(req, res);
			break;
		case "findByPk":
			// 新增處理 findByPk 的 case
			findByPk(req, res);
			break;
		case "getCommByMemberId":
			forwardPath = getCommByMemberId(req, res);
			break;
//		case "getCommImg":
//			getCommImg(req, res);
//			break;
		default:
			forwardPath = "/comm/listAllComm.jsp";
		}

		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
	}

//===================圖片===========================
	private void findByPk(HttpServletRequest req, HttpServletResponse res) {
		try {
			Integer commId = Integer.valueOf(req.getParameter("commId"));
			Comm comm = commService.findByPk(commId);

			if (comm == null) {
				// 如果商品為空，可以返回一張默認的圖片，或者返回404錯誤
				res.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}

			byte[] commImg = comm.getCommImg();

			res.setContentType("image/png");
			if (commImg != null && commImg.length > 0) {
				try (ServletOutputStream out = res.getOutputStream()) {
					out.write(commImg);
				}
			} else {
				// 如果商品圖片為空，可以返回一張默認的圖片，或者返回404錯誤
				res.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace(); // 考慮使用日誌系統進行輸出
		}
	}

	// ===================/圖片===========================
	
	//=================================================
	private String getCommByMemberId(HttpServletRequest req, HttpServletResponse res) {
		String memberId = req.getParameter("memberId");
		System.out.println("===========================" + memberId + "============================");
		String forwardPath = "";
		List<Comm> commList = new ArrayList<>();
		switch (memberId.charAt(0)) {
		case '1':
			commList = commService.getAll(memberId);
			forwardPath = "/member_center/order_management.jsp";
			break;
		case '2':
			commList = commService.getAll(memberId);
			forwardPath = "/buylist/listAllComm.jsp";
			break;
		}
		//================================================================
		String sellerId = req.getParameter("sellerId");
		System.out.println("===========================" + sellerId + "============================");
		
		
		switch (sellerId.charAt(0)) {
		case '1':
			commList = commService.getAll(sellerId);
			forwardPath = "/comm/listAllComm.jsp";
			break;
		case '2':
			commList = commService.getAll(sellerId);
			forwardPath = "/comm/listAllComm.jsp";
			break;
		}
		//================================================================
		req.setAttribute("getAll", commList);
		return forwardPath;
	}
	//=================================================
	// 1,查詢
	private String getOneDisplay(HttpServletRequest req, HttpServletResponse res) {
		// 錯誤處理
		List<String> errorMsgs = new ArrayList<>();
		req.setAttribute("errorMsgs", errorMsgs);

///*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
		String str = req.getParameter("commId");

		if (str == null || (str.trim()).length() == 0) {
			errorMsgs.add("請輸入商品編號");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			return "/comm/listAllComm.jsp";// 程式中斷
		}

		Integer commId = null;
		try {
			commId = Integer.valueOf(str);
		} catch (Exception e) {
			errorMsgs.add("商品編號格式不正確");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			return "/comm/listAllComm.jsp";// 程式中斷
		}

///*************************** 2.開始查詢資料 *****************************************/
//		CommService coupoService = new CommService();
		commService = new CommServiceImpl();
		Comm comm = commService.findByPk(commId);

		if (comm == null) {
			errorMsgs.add("查無資料");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			return "/comm/listAllComm.jsp";// 程式中斷
		}
		commService.updateView(commId);

///*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
		req.setAttribute("comm", comm); // 資料庫取出的manage物件,存入req
		return "/comm/listOneComm.jsp"; // 20231128
	}
	// ===================================================

	// 2,修改
	private String getOneUpdate(HttpServletRequest req, HttpServletResponse res) {
//		System.out.println(req.getParameter("commId"));
		Integer commId = Integer.valueOf(req.getParameter("commId"));
		System.out.println("+++" + commId);

		Comm comm = commService.findByPk(commId);
		System.out.println(comm);

		req.setAttribute("comm", comm);
		return "/comm/update_comm_input.jsp";
	}

	private String update(HttpServletRequest req, HttpServletResponse res) {
		// 錯誤處理
		List<String> errorMsgs = new ArrayList<>();
		req.setAttribute("errorMsgs", errorMsgs);

///*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
		Integer commId = Integer.parseInt(req.getParameter("commId"));
//		Integer sellerId = Integer.parseInt(req.getParameter("seller"));
		String commName = req.getParameter("commName");
		String commDesc = req.getParameter("commDesc");
		Integer commState = Integer.parseInt(req.getParameter("commState"));

//		Timestamp listDatetime = java.sql.Timestamp.valueOf(req.getParameter("listDatetime").trim());
//		Timestamp sellerEvaluateTime;
//		try {
//			listDatetime = java.sql.Timestamp.valueOf(req.getParameter("listDatetime").trim());
//		} catch (IllegalArgumentException e) {
//			listDatetime = new java.sql.Timestamp(System.currentTimeMillis());
//			errorMsgs.add("請輸入時間!");
//		}
//==========================================================
//		byte[] commImg = null;
//		try {
//			InputStream in = req.getPart("commImg").getInputStream();
//			if (in.available() != 0) {
//				commImg = new byte[in.available()];
//				in.read(commImg);
//				in.close();
//			} else {
//				errorMsgs.add("商品圖片: 请上傳照片");
//			}
//		} catch (IOException | ServletException e) {
//			errorMsgs.add("圖片上傳失敗: " + e.getMessage());
//		}
////========================================================
//		// 取得圖片
////		Byte[] mProfilePic = null;
//		InputStream in = req.getPart("commImg").getInputStream(); //從javax.servlet.http.Part物件取得上傳檔案的InputStream
//		byte[] commImg = null;
//		if(in.available()!=0){
//			commImg = new byte[in.available()];
//			in.read(commImg);
//			in.close();
//		}  else {
//			CommService commService = new CommServiceImpl();
//			commImg = commService.findByPk(commId).getCommImg();
//		}
//========================================================
		byte[] commImg = null;
		try {
			InputStream in = req.getPart("commImg").getInputStream(); 

		    if (in.available() != 0) {
		        commImg = new byte[in.available()];
		        in.read(commImg);
		        in.close();
		    }
		} catch (IOException | ServletException e) {
		    errorMsgs.add("圖片上傳失敗: " + e.getMessage());
		}

		// 如果 commImg 為 null，代表上傳失敗，或者使用預設的商品圖片
		if (commImg == null) {
		    CommService commService = new CommServiceImpl();
		    commImg = commService.findByPk(commId).getCommImg();
		}

//========================================================
		Integer commCatId = Integer.parseInt(req.getParameter("commCat"));
		Integer commStock = Integer.parseInt(req.getParameter("commStock"));

		BigDecimal commPrice;
		try {
			// 假設 req 是 HttpServletRequest 物件
			String commPriceStr = req.getParameter("commPrice");

			// 將字串轉換為 BigDecimal
			commPrice = new BigDecimal(commPriceStr);

			// 設定默認值，保留兩位小數
			commPrice = commPrice.setScale(2, RoundingMode.HALF_UP);

			// 在這裡您可以使用 commPrice 進行後續的操作
		} catch (NumberFormatException e) {
			// 處理轉換失敗的情況，例如記錄錯誤或提供默認值
			e.printStackTrace();
			// 例如，提供默認值
			commPrice = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
		}

		BigDecimal commOnsalePrice;
		try {
			// 假設 req 是 HttpServletRequest 物件
			String commOnsalePriceStr = req.getParameter("commOnsalePrice");

			if (commOnsalePriceStr != null) {
				// 將字串轉換為 BigDecimal
				commOnsalePrice = new BigDecimal(commOnsalePriceStr);

				// 設定默認值，保留兩位小數
				commOnsalePrice = commOnsalePrice.setScale(2, RoundingMode.HALF_UP);

				// 在這裡您可以使用 commPrice 進行後續的操作
			} else {
				// 提供默認值
				commOnsalePrice = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
			}
		} catch (NumberFormatException e) {
			// 處理轉換失敗的情況，例如記錄錯誤或提供默認值
			e.printStackTrace();
			// 例如，提供默認值
			commOnsalePrice = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
		}

//		Integer commViewCount = Integer.parseInt(req.getParameter("commViewCount"));

//============================================

//		Comm comm = new Comm();
		Comm comm = commService.findByPk(commId);
		System.out.println("==================================="+comm+"=========================");
//		comm.setCommId(commId);

//		Seller seller = new Seller();
//		seller.setSellerId(sellerId);
//		comm.setSeller(seller);

		CommCat commCat = new CommCat();
		commCat.setCommCatId(commCatId);
		comm.setCommCat(commCat);

		comm.setCommName(commName);
		comm.setCommDesc(commDesc);
		comm.setCommState(commState);
//		comm.setListDatetime(listDatetime);

		if (commImg != null && commImg.length > 0) {
//			user.setHeadshot(headshot);
			comm.setCommImg(commImg);
		}
		comm.setCommCat(commCat);
		comm.setCommStock(commStock);
		comm.setCommPrice(commPrice);
		comm.setCommOnsalePrice(commOnsalePrice);
//		comm.setCommViewCount(commViewCount);

//==========================================		
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			req.setAttribute("comm", comm); // 含有輸入格式錯誤的manage物件,也存入req
			return "/comm/update_comm_input.jsp"; // 程式中斷
		}

///*************************** 2.開始修改資料 *****************************************/
		System.out.println(commService.update(comm));

///*************************** 3.修改完成,準備轉交(Send the Success view) *************/
		req.setAttribute("comm", comm); // 資料庫update成功後,正確的的manage物件,存入req
		return "/comm/listOneComm.jsp";
	}

	// ===================================================

	// 3,新增
	private String insert(HttpServletRequest req, HttpServletResponse res) {
		// 錯誤處理

		List<String> errorMsgs = new ArrayList<>();
		req.setAttribute("errorMsgs", errorMsgs);
//		/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

//		Integer commId = Integer.parseInt(req.getParameter("commId"));
		Integer sellerId = Integer.parseInt(req.getParameter("seller"));
		String commName = req.getParameter("commName");
		String commDesc = req.getParameter("commDesc");
		Integer commState = Integer.parseInt(req.getParameter("commState"));

		Timestamp listDatetime = java.sql.Timestamp.valueOf(req.getParameter("listDatetime").trim());
//		Timestamp sellerEvaluateTime;
//		try {
//			listDatetime = java.sql.Timestamp.valueOf(req.getParameter("listDatetime").trim());
//		} catch (IllegalArgumentException e) {
//			listDatetime = new java.sql.Timestamp(System.currentTimeMillis());
//			errorMsgs.add("請輸入時間!");
//		}

		byte[] commImg = null;
		try {
			InputStream in = req.getPart("commImg").getInputStream();
			if (in.available() != 0) {
				commImg = new byte[in.available()];
				in.read(commImg);
				in.close();
			} else {
				errorMsgs.add("商品圖片: 请上傳照片");
			}
		} catch (IOException | ServletException e) {
			errorMsgs.add("圖片上傳失敗: " + e.getMessage());
		}

		Integer commCatId = Integer.parseInt(req.getParameter("commCat"));
		Integer commStock = Integer.parseInt(req.getParameter("commStock"));

		BigDecimal commPrice;
		try {
			// 假設 req 是 HttpServletRequest 物件
			String commPriceStr = req.getParameter("commPrice");

			// 將字串轉換為 BigDecimal
			commPrice = new BigDecimal(commPriceStr);

			// 設定默認值，保留兩位小數
			commPrice = commPrice.setScale(2, RoundingMode.HALF_UP);

			// 在這裡您可以使用 commPrice 進行後續的操作
		} catch (NumberFormatException e) {
			// 處理轉換失敗的情況，例如記錄錯誤或提供默認值
			e.printStackTrace();
			// 例如，提供默認值
			commPrice = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
		}

		BigDecimal commOnsalePrice;
		try {
			// 假設 req 是 HttpServletRequest 物件
			String commOnsalePriceStr = req.getParameter("commOnsalePrice");

			if (commOnsalePriceStr != null) {
				// 將字串轉換為 BigDecimal
				commOnsalePrice = new BigDecimal(commOnsalePriceStr);

				// 設定默認值，保留兩位小數
				commOnsalePrice = commOnsalePrice.setScale(2, RoundingMode.HALF_UP);

				// 在這裡您可以使用 commPrice 進行後續的操作
			} else {
				// 提供默認值
				commOnsalePrice = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
			}
		} catch (NumberFormatException e) {
			// 處理轉換失敗的情況，例如記錄錯誤或提供默認值
			e.printStackTrace();
			// 例如，提供默認值
			commOnsalePrice = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
		}

		Integer commViewCount = Integer.parseInt(req.getParameter("commViewCount"));

		// ============================================

		Comm comm = new Comm();
//		comm.setCommId(commId);

		Seller seller = new Seller();
		seller.setSellerId(sellerId);
		comm.setSeller(seller);

		CommCat commCat = new CommCat();
		commCat.setCommCatId(commCatId);
		comm.setCommCat(commCat);

		comm.setCommName(commName);
		comm.setCommDesc(commDesc);
		comm.setCommState(commState);
		comm.setListDatetime(listDatetime);
		comm.setCommImg(commImg);
//		comm.setCommCat(commCat);
		comm.setCommStock(commStock);
		comm.setCommPrice(commPrice);
		comm.setCommOnsalePrice(commOnsalePrice);
		comm.setCommViewCount(commViewCount);

		// ==========================================

//		/*************************** 2.開始新增資料 ***************************************/

		try {
			System.out.println("新增成功");
			commService.add(comm);
		} catch (Exception e) {
			System.out.println("新增失敗" + e.getMessage());
			e.getMessage();
		}
		;

//		if (comm.getCommId() != null && comm.getCommId() > 0) {
//			System.out.println("新增成功2");
//		} else {
//			System.out.println("新增失敗2");
//		}

//		/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
		return "/comm/listAllComm.jsp";
	}

	// ===================================================

	// 4,刪除
	private String delete(HttpServletRequest req, HttpServletResponse res) {
//		  List<String> errorMsgs = new List<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
//          req.setAttribute("errorMsgs", errorMsgs);

//          /**1.接收請求參數**/
		Integer commId = Integer.valueOf(req.getParameter("commId"));

//          /**2.開始刪除資料**/
		CommDAO commDAO = new CommDAOImpl();
		commDAO.delete(commId);
//          /**3.刪除完成,準備轉交(Send the Success view)**/
		return "/comm/listAllComm.jsp";
//          String url = "/comm/listAllComm.jsp";
//          RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//          successView.forward(req, res);
	}

}