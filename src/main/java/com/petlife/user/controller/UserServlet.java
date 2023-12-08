package com.petlife.user.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.petlife.user.entity.User;
import com.petlife.user.service.UserServeice;
import com.petlife.user.service.impl.UserServiceImpl;
import com.petlife.util.MailService;
import com.petlife.util.RandomAuthenCode;

import redis.clients.jedis.Jedis;

@WebServlet("/user/user.do")
@MultipartConfig
public class UserServlet extends HttpServlet {
	private UserServeice userServeice;

	@Override
	public void init() throws ServletException {
		userServeice = new UserServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");
		String forwardPath = "";
		switch (action) {
		case "userRegister":
			forwardPath = userRegist(req, resp);
			break;
		case "getAuthenCode":
			getAuthenCode(req, resp);
			break;
		case "userLogin":
			forwardPath = userLogin(req, resp);
			break;
		case "update":
			forwardPath = updateUser(req, resp);
			break;
		case "getOneByPK":
			forwardPath = getUserByPK(req, resp);
			break;
		case "getAll":
			forwardPath = getAllUsers(req, resp);
			break;
		case "verify":
			authencation(req, resp);
		default:
			forwardPath = "";
			break;
		}
		// dispatcher路徑是從專案開始，forwardPath要加/
		if (!("".equals(forwardPath))) {
			RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
			dispatcher.forward(req, resp);
		}

	}

	// 驗證帳號與暱稱是否可以使用
	private void authencation(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String userNickname = req.getParameter("nickname");
		String userAcct = req.getParameter("useraccount");

		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();

		if (userNickname != null && userNickname.length() != 0) {
			boolean checkUserNickname = userServeice.existUserNickname(userNickname);

			if (checkUserNickname) {
				out.print("<font color='red'>暱稱重複!!</font>");
			} else {
				out.print("<font color='green'>暱稱可使用</font>");
			}
		}

		if (userAcct != null && userAcct.length() != 0) {
			String userAcctReg = "^[A-Za-z0-9-_\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
			if (!userAcct.matches(userAcctReg)) {
				out.print("<font color='red'>信箱格式不符!!</font>");
			} else {
				boolean checkUserAccount = userServeice.exisUserAccount(userAcct);

				if (checkUserAccount) {
					out.print("<font color='red'>帳號重複!!</font>");
				} else {
					out.print("<font color='green'>帳號可使用</font>");
				}
			}
		}
	}

	// 註冊會員程序
	private String userRegist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		// 用來裝前端傳入的json資料(會員註冊填的資料)
		Map<String, String> registerUserData = null;
		// 取得前端傳入的json資料，使用BufferReader
		try {
			BufferedReader reader = req.getReader();
			StringBuilder jsonStr = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				jsonStr.append(line);
			}

			Gson gson = new Gson();
			Type type = new TypeToken<Map<String, String>>() {
			}.getType();
			registerUserData = gson.fromJson(jsonStr.toString(), type);
		} catch (Exception e) {
			// 異常處理
			e.printStackTrace();
		}

		// 驗證使用者註冊的資料，使用Map裝入
		Map<String, String> errorMsg = new HashMap<>();

		// 已經用ajax先檢查了(帳號)
		String userAcct = registerUserData.get("useraccount");

		// 驗證驗證碼
		String authenCode = registerUserData.get("authencode");
		String authenCodeFromJedis = RandomAuthenCode.getAuthenCode("Member", userAcct);
		if (authenCodeFromJedis == null) {
			errorMsg.put("userAuthenCodeErr", "請先取得驗證碼!!");
		} else {
			if (!authenCode.equals(authenCodeFromJedis)) {
				errorMsg.put("userAuthenCodeErr", "驗證碼輸入錯誤");
			}
		}

		// 驗證密碼
		String userPwd = registerUserData.get("password");
		String userPwdReg = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+])[A-Za-z\\d!@#$%^&*()_+]{1,20}$";
		if (!userPwd.matches(userPwdReg)) {
			errorMsg.put("userPwdErr", "密碼格式不正確，必須包含英文大小寫及特殊符號");
		}

		// 驗證姓名
		String userName = registerUserData.get("username");
		String userNameReg = "^[\\u4e00-\\u9fa5]{1,20}$";
		if (!userName.matches(userNameReg)) {
			errorMsg.put("userNameErr", "姓名只能使用中文字，並限制20個字以內!!");
		}

		// 已經用ajax先檢查了(暱稱)
		String userNickname = registerUserData.get("nickname");

		Date userBirthday = null;
		try {
			userBirthday = java.sql.Date.valueOf(registerUserData.get("birthdate"));
		} catch (IllegalArgumentException e) {
			errorMsg.put("userBirthdayErr", "日期格式錯誤!!");
		}

		// 驗證手機號碼
		String userPhoneNum = registerUserData.get("phone");
		String userPhoneNumReg = "^09\\d{8}$";
		if (!userPhoneNum.matches(userPhoneNumReg)) {
			errorMsg.put("userPhoneNumErr", "手機號碼只能以09開頭，並且10個數字!!");
		}

		// 驗證地址
		String userAddress = registerUserData.get("address");
		String addressReg = "^[\\u4e00-\\u9fa50-9\\s]+$";
		if (!userAddress.matches(addressReg)) {
			errorMsg.put("addressErr", "地址只能包含中文與數字!!");
		} else {
			userAddress = registerUserData.get("country") + registerUserData.get("district")
					+ registerUserData.get("address");
		}

		// 性別
		boolean userGender = Boolean.valueOf(registerUserData.get("gender"));

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
			User user = new User(userAcct, userPwd, userName, userNickname, userBirthday, userAddress, userPhoneNum,
					userGender);
			user = userServeice.addUser(user);

			// 寄信表示註冊成功
			MailService.memberRegisterSuccess(user.getUserAcct());

			// 把user資訊放到Session中
			req.getSession().setAttribute("user", user);
			// 這裡要重導還是轉發，目的地應該是首頁?
			return "";
		}
	}

	// 取得隨機驗證碼並寄信給該用戶
	private void getAuthenCode(HttpServletRequest req, HttpServletResponse resp) {
		String userAcct = req.getParameter("useraccount");

		// 取得儲存在redis當中的驗證碼(效期只有10分鐘)
		String authenCode = RandomAuthenCode.setAuthenCode("Member", userAcct);

		// 寄信給該註冊帳號
		MailService.sendAuthenCode(userAcct, authenCode);
	}

	// 登入判斷
	private String userLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		req.setCharacterEncoding("UTF-8");

		String userAcct = req.getParameter("account");
		String userPwd = req.getParameter("password");

		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		// 檢查要登入的帳號是否存在
		if (userServeice.exisUserAccount(userAcct)) {
			// 存在的話檢查帳密是否存在，並回傳帳號目前狀態
			Integer checkLogin = userServeice.userLogin(userAcct, userPwd);
			// 登入成功，檢查帳號的狀態
			switch (checkLogin) {
			case 1:
				out.print(1);
				break;
			case 2:
				out.print(2);
				break;
			case 3:
				out.print(3);
				break;
			default:
				out.print(0);
				User user = userServeice.getUserByUserId(checkLogin);
				System.out.println(user);
				// 登入成功要轉發或重導?
				req.setAttribute("user", user);
				return "";
			}
			return "";
		} else {
			// 登入失敗：帳號不存在
			out.print(-1);
			return "";
		}
	}

	private String updateUser(HttpServletRequest req, HttpServletResponse resp) {
		userServeice.updateUser(null);
		return "";
	}

	private String getUserByPK(HttpServletRequest req, HttpServletResponse resp) {
		userServeice.getUserByUserId(null);
		return "";
	}

	private String getAllUsers(HttpServletRequest req, HttpServletResponse resp) {
		userServeice.getAllUsers();
		return "";
	}
}
