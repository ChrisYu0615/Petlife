package com.petlife.seller.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.petlife.seller.entity.Seller;
import com.petlife.seller.service.SellerService;
import com.petlife.seller.service.SellerServiceImpl;

@WebServlet("/seller/seller.do")
@MultipartConfig
public class SellerController extends HttpServlet {
	private SellerService sellerService;

	@Override
	public void init() throws ServletException {
		sellerService = new SellerServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");
		String forwardPath = "";
		switch (action) {
		case "sellerRegister":
			forwardPath = sellerRegister(req, resp);
			break;
		case "verify":
			forwardPath = authencation(req, resp);
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

	// 驗證帳號與暱稱是否可以使用
	private String authencation(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sellerShopname = req.getParameter("shopname");
		String sellerAccount = req.getParameter("selleraccount");

		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();

		if (sellerShopname != null && sellerShopname.length() != 0) {
			boolean checkSellerShopname = sellerService.existSellerShopname(sellerShopname);

			if (checkSellerShopname) {
				out.print("<font color='red'>暱稱重複!!</font>");
			} else {
				out.print("<font color='green'>暱稱可使用</font>");
			}
		}

		if (sellerAccount != null && sellerAccount.length() != 0) {
			String sellerAcctReg = "^[A-Za-z0-9-_\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
			if (!sellerAccount.matches(sellerAcctReg)) {
				out.print("<font color='red'>信箱格式不符!!</font>");
			} else {
				boolean checkSellerAccount = sellerService.existSellerAccount(sellerAccount);

				if (checkSellerAccount) {
					out.print("<font color='red'>帳號重複!!</font>");
				} else {
					out.print("<font color='green'>帳號可使用</font>");
				}
			}
		}
		return "";
	}

	// 註冊賣家程序
	private String sellerRegister(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		// 驗證使用者註冊的資料，使用Map裝入(有錯誤的話)
		Map<String, String> errorMsg = new HashMap<>();

		// 已經用ajax先檢查了(帳號)
		String sellerAccount = req.getParameter("selleraccount");

		// 驗證碼晚點寫
		String authenCode = req.getParameter("authencode");

		// 驗證密碼
		String sellerPwd = req.getParameter("password");
		String sellerPwdReg = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+])[A-Za-z\\d!@#$%^&*()_+]{1,20}$";
		if (!sellerPwd.matches(sellerPwdReg)) {
			errorMsg.put("sellerPwdErr", "密碼格式不正確，必須包含英文大小寫及特殊符號");
		}

		// 驗證姓名
		String sellerName = req.getParameter("sellername");
		String sellerNameReg = "^[\\u4e00-\\u9fa5]{1,20}$";
		if (!sellerName.matches(sellerNameReg)) {
			errorMsg.put("sellerNameErr", "姓名只能使用中文字，並限制20個字以內!!");
		}

		String sellerIdentification = req.getParameter("selleridenfication");
		String idReg = "^[A-Z]{1}[1-2]{1}[0-9]{8}$";
		if (!sellerIdentification.matches(idReg)) {
			errorMsg.put("sellerIdentificationErr", "身分證格式錯誤!!");
		}

		// 已經用ajax先檢查了(暱稱)
		String sellerShopname = req.getParameter("shopname");

		// 性別
		boolean sellerGender = Boolean.valueOf(req.getParameter("gender"));

		// 驗證日期
		Date sellerBirthday = null;
		try {
			sellerBirthday = java.sql.Date.valueOf(req.getParameter("birthdate"));
		} catch (IllegalArgumentException e) {
			errorMsg.put("sellerBirthdayErr", "日期格式錯誤!!");
		}

		// 驗證手機號碼
		String sellerPhone = req.getParameter("phone");
		String sellerPhoneNumReg = "^09\\d{8}$";
		if (!sellerPhone.matches(sellerPhoneNumReg)) {
			errorMsg.put("sellerPhoneNumErr", "手機號碼只能以09開頭，並且10個數字!!");
		}

		// 驗證地址
		String sellerAddress = req.getParameter("address");
		String addressReg = "^[\\u4e00-\\u9fa50-9\\s]+$";
		if (!sellerAddress.matches(addressReg)) {
			errorMsg.put("addressErr", "地址只能包含中文與數字!!");
		} else {
			sellerAddress = req.getParameter("country") + req.getParameter("district") + req.getParameter("address");
		}

		String bankCode = req.getParameter("bankcode");

		String bankAccount = req.getParameter("bankAccount");
		String bankAccountReg = "^\\d{10,16}$";
		if (!bankAccount.matches(bankAccountReg)) {
			errorMsg.put("bankAccountErr", "帳戶只能輸入數字(10-16碼之間)");
		}
		
		Part idcardFront = req.getPart("idcardFront");
		InputStream idcardFrontIn = idcardFront.getInputStream();
		byte[] idcardFrontBuf = new byte[idcardFrontIn.available()];
		idcardFrontIn.read(idcardFrontBuf);
		idcardFrontIn.close();

		Part idcardBack = req.getPart("idcardBack");
		InputStream idcardBackIn = idcardBack.getInputStream();
		byte[] idcardBackBuf = new byte[idcardBackIn.available()];
		idcardBackIn.read(idcardBackBuf);
		idcardBackIn.close();

		Part accountImg = req.getPart("accountImg");
		InputStream accountImgIn = accountImg.getInputStream();
		byte[] accountImgBuf = new byte[accountImgIn.available()];
		accountImgIn.read(accountImgBuf);
		accountImgIn.close();

		// 判斷有無錯誤資訊，有的話輸出以Json格式輸出到前端
		if (errorMsg.size() > 0) {
			resp.setContentType("application/json; charset=UTF-8");
			Gson gson = new Gson();
			String errorMsgJson = gson.toJson(errorMsg);

			PrintWriter out = resp.getWriter();
			out.print(errorMsgJson);
			return "";
		} else {
			// 如果沒有任何錯誤驗證資訊，開始執行service並儲存到資料庫中
			Seller seller = new Seller(sellerAccount, sellerPwd, sellerName, sellerIdentification, sellerShopname,
					sellerBirthday, sellerGender, sellerAddress, sellerPhone, bankCode, bankAccount, idcardFrontBuf,
					idcardBackBuf, accountImgBuf);
			seller = sellerService.addSeller(seller);
			req.getSession().setAttribute("seller", seller);
			// 這裡要重導還是轉發，目的地應該是首頁?
			return "";
		}
	}
}
