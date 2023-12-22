package com.petlife.seller.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Base64;
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
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.petlife.admin.dao.AcctStateDAO;
import com.petlife.admin.dao.impl.AcctStateDAOImpl2;
import com.petlife.admin.entity.AcctState;
import com.petlife.seller.entity.Seller;
import com.petlife.seller.service.SellerService;
import com.petlife.seller.service.impl.SellerServiceImpl;
import com.petlife.user.entity.User;
import com.petlife.util.MailService;
import com.petlife.util.RandomAuthenCode;

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
			sellerRegister(req, resp);
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
		case "getAll":
			forwardPath = getAllSellers(req, resp);
			break;
		case "getOne":
			getOneSeller(req, resp);
			break;
		case "suspend_Seller":
			forwardPath = suspendSeller(req, resp);
			break;
		case "recover_Seller":
			forwardPath = recoverSeller(req, resp);
			break;
		case "verify_Seller":
			forwardPath = verifySeller(req, resp);
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

	private String verifySeller(HttpServletRequest req, HttpServletResponse resp) {
		Integer SellerId = Integer.valueOf(req.getParameter("memberId"));
		String selectValue = req.getParameter("sellerReviewResult");
		String reason = req.getParameter("reason");
		Seller seller = sellerService.getSellerBySellerId(SellerId);
		Thread thread;
		switch (selectValue) {
		case "1":
			AcctStateDAO acctStateDAO = new AcctStateDAOImpl2();
			AcctState acctState = acctStateDAO.findByPK(0);
			seller.setAcctState(acctState);
			sellerService.updateSeller(seller);
			thread = new Thread(() -> {
				MailService.verifySuccess(seller.getSellerAcct());
			});
			thread.start();
			break;
		case "2":
			sellerService.deleteSeller(SellerId);
			thread = new Thread(() -> {
				MailService.verifyfailed(seller.getSellerAcct(), reason);
			});
			break;
		}

		return "/admin/admin.do?action=getAllMembers&condition=unverified";
	}

	private void getOneSeller(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Integer sellerId = Integer.valueOf(req.getParameter("memberId"));
		Seller seller = sellerService.getSellerBySellerId(sellerId);
		// 裝賣家的驗證照片(轉成Base64方便Json傳送)
		List<String> sellerImgs = new ArrayList<>();
		sellerImgs.add(Base64.getEncoder().encodeToString(seller.getIdFront()));
		sellerImgs.add(Base64.getEncoder().encodeToString(seller.getIdBack()));
		sellerImgs.add(Base64.getEncoder().encodeToString(seller.getBankAcctImg()));

		Map<String, Object> sellerData = new HashMap<>();
		sellerData.put("seller", seller);
		sellerData.put("imgs", sellerImgs);

		Gson gson = new GsonBuilder().setPrettyPrinting().setDateFormat("yyyy-MM-dd")
				.excludeFieldsWithoutExposeAnnotation().create();
		String sellerDataJson = gson.toJson(sellerData);
		resp.setContentType("application/json; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.print(sellerDataJson);
	}

	private String recoverSeller(HttpServletRequest req, HttpServletResponse resp) {
		Integer sellerId = Integer.parseInt(req.getParameter("memberId"));
		Seller seller = sellerService.getSellerBySellerId(sellerId);
		seller.setAcctState(new AcctState(0, "可使用"));
		sellerService.updateSeller(seller);
		return "/seller/seller.do?action=getAll&condition=verified";
	}

	private String suspendSeller(HttpServletRequest req, HttpServletResponse resp) {
		Integer sellerId = Integer.parseInt(req.getParameter("memberId"));
		Seller seller = sellerService.getSellerBySellerId(sellerId);
		seller.setAcctState(new AcctState(1, "停權"));
		sellerService.updateSeller(seller);
		return "/seller/seller.do?action=getAll&condition=verified";
	}

	// 驗證帳號與暱稱是否可以使用
	private void authencation(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sellerAcct = req.getParameter("selleraccount");

		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();

		if (sellerAcct != null && sellerAcct.length() != 0) {
			String sellerAcctReg = "^[A-Za-z0-9-_\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
			if (!sellerAcct.matches(sellerAcctReg)) {
				out.print("<font color='red'>信箱格式不符!!</font>");
			} else {
				boolean checkSellerAccount = sellerService.existSellerAccount(sellerAcct);

				if (checkSellerAccount) {
					out.print("<font color='red'>帳號重複!!</font>");
				} else {
					out.print("<font color='green'>帳號可使用</font>");
				}
			}
		}
	}

	// 註冊賣家程序
	private void sellerRegister(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		// 驗證使用者註冊的資料，使用Map裝入(有錯誤的話)
		Map<String, String> errorMsg = new HashMap<>();

		// 已經用ajax先檢查了(帳號)
		String sellerAcct = req.getParameter("selleraccount");

		// 驗證驗證碼
		String authenCode = req.getParameter("authencode");
		String authenCodeFromJedis = RandomAuthenCode.getAuthenCode("Seller", sellerAcct);
		if (authenCodeFromJedis == null) {
			errorMsg.put("sellerAuthenCodeErr", "請先取得驗證碼!!");
		} else {
			if (!authenCode.equals(authenCodeFromJedis)) {
				errorMsg.put("sellerAuthenCodeErr", "驗證碼輸入錯誤");
			}
		}

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

		// 驗證銀行帳號
		String bankAccount = req.getParameter("bankAccount");
		String bankAccountReg = "^\\d{10,16}$";
		if (!bankAccount.matches(bankAccountReg)) {
			errorMsg.put("bankAccountErr", "帳戶只能輸入數字(10-16碼之間)");
		}

		Part idcardFront = req.getPart("idcardFront");
		byte[] idcardFrontBuf = getImgBytes(idcardFront);

		Part idcardBack = req.getPart("idcardBack");
		byte[] idcardBackBuf = getImgBytes(idcardBack);

		Part accountImg = req.getPart("accountImg");
		byte[] accountImgBuf = getImgBytes(accountImg);

		resp.setContentType("application/json; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		// 判斷有無錯誤資訊，有的話輸出以Json格式輸出到前端
		if (errorMsg.size() > 0) {
			Gson gson = new Gson();
			String errorMsgJson = gson.toJson(errorMsg);

			out.print(errorMsgJson);
		} else {
			// 如果沒有任何錯誤驗證資訊，開始執行service並儲存到資料庫中
			Seller seller = new Seller(sellerAcct, sellerPwd, sellerName, sellerIdentification, sellerShopname,
					sellerBirthday, sellerGender, sellerAddress, sellerPhone, bankCode, bankAccount, idcardFrontBuf,
					idcardBackBuf, accountImgBuf);
			seller = sellerService.addSeller(seller);

			// 把seller資訊放到Session中
			req.getSession().setAttribute("seller", seller);
			// 這裡要重導還是轉發，目的地應該是首頁?
			Gson gson = new Gson();
			String redirectPath = gson.toJson(req.getContextPath() + "/index.html");
			out.print(redirectPath);

			// 寄信表示註冊成功
			Thread thread = new Thread(()->{
				MailService.registerSuccess(sellerAcct);
			});
			thread.start();
		}
	}

	// 取得隨機驗證碼並寄信給該用戶
	private void getAuthenCode(HttpServletRequest req, HttpServletResponse resp) {
		String sellerAcct = req.getParameter("selleraccount");
		String value = req.getParameter("value");
		String memberType = "newPwd".equals(value) ? "SellerNewPwd" : "Seller";

		// 取得儲存在redis當中的驗證碼(效期只有10分鐘)
		String authenCode = RandomAuthenCode.setAuthenCode(memberType, sellerAcct);

		// 寄信給該註冊帳號
		MailService.sendAuthenCode(sellerAcct, authenCode);
	}

	private void setNewPassword(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String sellerAcct = req.getParameter("account");
		String authenCode = req.getParameter("authencode");

		Map<String, String> errorMsg = new HashMap<>();
		resp.setContentType("application/json; charset=UTF-8");
		Gson gson = new Gson();
		PrintWriter out = resp.getWriter();
		if (!sellerService.existSellerAccount(sellerAcct)) {
			errorMsg.put("accountErr", "帳號不存在!!");
			String errorMsgJson = gson.toJson(errorMsg);
			System.out.println(errorMsgJson);
			out.print(errorMsgJson);
			return;
		}

		String authenCodeFromJedis = RandomAuthenCode.getAuthenCode("SellerNewPwd", sellerAcct);
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
			String result = sellerService.getNewPwd(sellerAcct);
			Map<String, String> successMsg = new HashMap<>();
			successMsg.put("success", result);

			out.print(gson.toJson(successMsg));
		}
	}

	private String getAllSellers(HttpServletRequest req, HttpServletResponse resp) {
		List<Seller> sellerList = new ArrayList<>();
		String condition = req.getParameter("condition");
		if (condition != null && condition.length() > 0) {
			sellerList = sellerService.getAllSellers(condition);
		} else {
			sellerList = sellerService.getAllSellers();
		}
		req.setAttribute("getAllSellers", sellerList);
		return "/admin/seller_management.jsp";
	}

	private byte[] getImgBytes(Part part) throws IOException {
		InputStream in = part.getInputStream();
		byte[] buf = new byte[in.available()];
		in.read(buf);
		in.close();
		return buf;
	}
}
