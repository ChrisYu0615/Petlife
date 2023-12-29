package com.petlife.shelter.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.petlife.admin.dao.AcctStateDAO;
import com.petlife.admin.dao.impl.AcctStateDAOImpl;
import com.petlife.admin.entity.AcctState;
import com.petlife.admin.service.AcctStateService;
import com.petlife.admin.service.impl.AcctStateServiceImpl;
import com.petlife.pet.entity.PetPhoto;
import com.petlife.seller.entity.Seller;
import com.petlife.shelter.entity.Shelter;
import com.petlife.shelter.service.ShelterService;
import com.petlife.shelter.service.impl.ShelterServiceImpl;
import com.petlife.util.MailService;
import com.petlife.util.RandomAuthenCode;
import com.petlife.util.RandomPassword;
import com.petlife.util.Sha1Util;

@WebServlet("/shelter/shelter.do")
@MultipartConfig
public class ShelterServlet extends HttpServlet {

	private ShelterService shelterService;

	@Override
	public void init() throws ServletException {
		shelterService = new ShelterServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String forwardPath = "";
		System.out.println(action);
		switch (action) {
		case "getAll":
			forwardPath = getAllShelters(req, res);
			break;
		case "getOne":
			getOneShelter(res, req);
			break;
		case "compositeQuery":
			forwardPath = getCompositeSheltersQuery(req, res);
			break;
		case "insert":
			forwardPath = getAddShelter(req, res);
			break;
		case "getOneToUpdate":
			forwardPath = getOneToUpdateShelter(req, res);
			break;
		case "update":
			forwardPath = getUpdateShelter(req, res);
			break;
		case "shelterRegister":
			shelterRegister(req, res);
			break;
		case "verify":
			authencation(req, res);
			break;
		case "getAuthenCode":
			getAuthenCode(req, res);
			break;
		case "forgetPwd":
			setNewPassword(req, res);
			break;
		case "modifyShelterAcctState":
			forwardPath = modifyShelterAcctState(req, res);
			break;
		case "verify_Shelter":
			forwardPath = verifyShelter(req, res);
			break;
		case "update_forward":
			forwardPath = update_forward(req, res);
			break;
		case "update_put":
			forwardPath = update_put(req, res);
			break;
		case "getShelterPhoto":
			forwardPath = getShelterPhoto(req, res);
			break;
		default:
			forwardPath = "/index.jsp";
		}

		res.setContentType("text/html; charset=UTF-8");
		if (forwardPath.length() != 0) {
			RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
			dispatcher.forward(req, res);
		}
	}
//12/24詩涵
private String getShelterPhoto(HttpServletRequest req, HttpServletResponse res)throws IOException {
	Integer id=(Integer.valueOf(req.getParameter("shelterId")));
	
	System.out.println(id);
	Shelter shelter = shelterService.getShelterByShelterId(id);


	if (shelter.getShelterPhoto() != null) {
		System.out.println("我有照片");
	    res.setContentType("image/gif");
	    
	  
	    byte[] imageData = shelter.getShelterPhoto();

	    try (ServletOutputStream out = res.getOutputStream()) {
	        
	        out.write(imageData);
	    } catch (IOException e) {
	        e.printStackTrace(); 
	    }
	}
	return "";
	}

//1215修改 詩涵
	private String getUpdateShelter(HttpServletRequest req, HttpServletResponse res) {
		Shelter shelter = (Shelter)req.getSession().getAttribute("shelter");
		req.setAttribute("shelter", shelter);
		return "/petjsp/shelter_update.jsp";
	}

	// 1215新增 詩涵
	private String update_forward(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("ShelterServlet: update_forward Entry");
		Shelter shelter2= (Shelter)(req.getSession().getAttribute("shelter"));
		Integer shelterId = shelter2.getShelterId();
		Shelter shelter = shelterService.getShelterByShelterId(shelterId);
		req.setAttribute("shelter", shelter);
		System.out.println("ShelterServlet: update_forward End");
		return "/petjsp/shelter_update_put.jsp";
	}

//1215新增 詩涵
	private String update_put(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException {
		System.out.println("ShelterServlet: update_put Entry");
		Shelter shelter2= (Shelter)(req.getSession().getAttribute("shelter"));
		Integer shelterId = shelter2.getShelterId();
		Shelter shelter = shelterService.getShelterByShelterId(shelterId);
		
		String shelterName = req.getParameter("shelterName").trim();
		String shelterAcct = req.getParameter("shelterAcct").trim();
		String password = req.getParameter("password").trim();
		System.out.println(password);

		if(!password.equals("")) {
			password = Sha1Util.encodePwd(password);
		}
		
		String shelterPhoneNum = req.getParameter("shelterPhoneNum").trim();
		String shelterAddress = req.getParameter("shelterAddress").trim();
		String shelterIntroduction = req.getParameter("shelterIntroduction").trim();
		
		for (Part part : req.getParts()) {
			if (!part.getName().equals("shelterblob"))
				continue;
			InputStream in = part.getInputStream();
			byte[] shelterPhoto = null;
			if (in.available() != 0) {
				shelterPhoto = new byte[in.available()];
				in.read(shelterPhoto);
				in.close();
				shelter.setShelterPhoto(shelterPhoto);
				
			}
		}
		
		shelter.setShelterId(shelterId);
		shelter.setShelterName(shelterName);
		shelter.setShelterAcct(shelterAcct);
		if(!password.equals("")) {
			shelter.setShelterPwd(password);
		}
		
		shelter.setShelterPhoneNum(shelterPhoneNum);
		shelter.setShelterAddress(shelterAddress);
		shelter.setShelterIntroduction(shelterIntroduction);

		shelter = shelterService.updateShelter(shelter);
		req.setAttribute("shelter", shelter);
		return "/petjsp/shelter_update.jsp";
	}

	private String verifyShelter(HttpServletRequest req, HttpServletResponse res) {
		Integer ShelterId = Integer.valueOf(req.getParameter("memberId").trim());
		String selectValue = req.getParameter("shelterReviewResult").trim();
		String reason = req.getParameter("reason").trim();
		Shelter shelter = shelterService.getShelterByShelterId(ShelterId);
		Thread thread;
		switch (selectValue) {
		case "1":
			AcctStateDAO acctStateDAO = new AcctStateDAOImpl();
			AcctState acctState = acctStateDAO.findByPK(0);
			shelter.setAcctState(acctState);
			thread = new Thread(() -> {
				MailService.verifySuccess(shelter.getShelterAcct());
			});
			thread.start();
			shelterService.updateShelter(shelter);
			break;
		case "2":
			shelterService.deleteShelter(ShelterId);
			thread = new Thread(() -> {
				MailService.verifyfailed(shelter.getShelterAcct(), reason);
			});
			thread.start();
			break;
		}

		return "/admin/admin.do?action=getAllMembers&condition=unverified";
	}

	private void getOneShelter(HttpServletResponse res, HttpServletRequest req) throws IOException {
		Integer shelterId = Integer.valueOf(req.getParameter("memberId"));
		Shelter shelter = shelterService.getShelterByShelterId(shelterId);

		Gson gson = new GsonBuilder().setPrettyPrinting().setDateFormat("yyyy-MM-dd")
				.excludeFieldsWithoutExposeAnnotation().create();
		String shelterJson = gson.toJson(shelter);
		res.setContentType("application/json; charset=UTF-8");
		PrintWriter out = res.getWriter();
		out.print(shelterJson);
	}


	
	private String modifyShelterAcctState(HttpServletRequest req, HttpServletResponse res) {
		Integer shelterId = Integer.parseInt(req.getParameter("memberId"));
		String modify = req.getParameter("modify");
		Shelter shelter = shelterService.getShelterByShelterId(shelterId);
		AcctState acctState = null;
		AcctStateService acctStateService = new AcctStateServiceImpl();

		if (modify != null && "suspendShelter".equals(modify)) {
			acctState = acctStateService.getByAcctStateId(1);
		} else if (modify != null && "recoverShelter".equals(modify)) {
			acctState = acctStateService.getByAcctStateId(0);
		}
		
		shelter.setAcctState(acctState);
		shelterService.updateShelter(shelter);
		return "/shelter/shelter.do?action=getAll&condition=verified";
	}

	private String getCompositeSheltersQuery(HttpServletRequest req, HttpServletResponse res) {
		System.out.println(req.getParameter("shelterName"));
		Map<String, String[]> map = req.getParameterMap();

		if (map != null) {
			List<Shelter> shelterList = shelterService.getSheltersByCompositeQuery(map);
			System.out.println(shelterList);
			req.setAttribute("shelterList", shelterList);
		} else {
			return "/index.jsp";
		}
		return "/shelter/listCompositeQueryShelters.jsp";
	}

	private String getAllShelters(HttpServletRequest req, HttpServletResponse res) {
		String condition = req.getParameter("condition");

		if (condition != null && condition.length() > 0) {
			String forwardPath = "";
			switch (condition) {
			case "verified":
				forwardPath = "/admin/shelter_management.jsp";
				break;
			case "unverified":
				forwardPath = "/admin/uncheck_member_management.jsp";
				break;
			}
			List<Shelter> shelterList = new ArrayList<>();
			shelterList = shelterService.getAllShelters(condition);
			req.setAttribute("getAllShelters", shelterList);
			return forwardPath;
		}

		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);

		List<Shelter> shelterList = shelterService.getAllShelters(currentPage);

		if (req.getSession().getAttribute("shelterPageQty") == null) {
			int shelterPageQty = shelterService.getPageTotal();
			req.getSession().setAttribute("shelterPageQty", shelterPageQty);
		}

		req.setAttribute("shelterList", shelterList);
		req.setAttribute("currentPage", currentPage);

		return "/shelter/listAllShelters.jsp";
	}

	private String getAddShelter(HttpServletRequest req, HttpServletResponse res) {

		String shelterAcct = req.getParameter("shelterAcct");
		String shelterPwd = req.getParameter("shelterPwd");
		String shelterName = req.getParameter("shelterName");
		String shelterPhoneNum = req.getParameter("shelterPhoneNum");
		String shelterAddress = req.getParameter("shelterAddress");
		String shelterIntroduction = req.getParameter("shelterIntroduction");
		Double shelterLng = Double.valueOf(req.getParameter("shelterLng"));
		Double shelterLat = Double.valueOf(req.getParameter("shelterLat"));

		Shelter shelter = new Shelter(shelterAcct, shelterPwd, shelterName, shelterPhoneNum, shelterAddress,
				shelterIntroduction, shelterLng, shelterLat);
//		System.out.println("進1");

		shelter = shelterService.addShelter(shelter);
		req.setAttribute("shelter", shelter);
		return "/shelter/shelter.do?action=getAll";

	}

	private String getOneToUpdateShelter(HttpServletRequest req, HttpServletResponse res) {
		Integer shelterId = Integer.valueOf(req.getParameter("shelterId"));
		System.out.println(shelterId);
		Shelter shelter = shelterService.getShelterByShelterId(shelterId);

		req.setAttribute("shelter", shelter);
		return "/shelter/getOneToUpdateShelter.jsp";

	}

	// 驗證帳號是否可以使用
	private void authencation(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String shelterAcct = req.getParameter("shelteraccount");
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();

		if (shelterAcct != null && shelterAcct.length() != 0) {
			String shelterAcctReg = "^[A-Za-z0-9-_\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
			if (!shelterAcct.matches(shelterAcctReg)) {
				out.print("<font color='red'>信箱格式不符!!</font>");
			} else {
				boolean checkShelterAccount = shelterService.existShelterAccount(shelterAcct);
				if (checkShelterAccount) {
					out.print("<font color='red'>帳號重複!!</font>");
				} else {
					out.print("<font color='green'>帳號可使用</font>");
				}
			}
		}
	}

	// 取得隨機驗證碼並寄信給該用戶
	private void getAuthenCode(HttpServletRequest req, HttpServletResponse resp) {
		String shelterAcct = req.getParameter("shelteraccount");
		String value = req.getParameter("value");
		String memberType = "newPwd".equals(value) ? "ShelterNewPwd" : "Shelter";

		// 取得儲存在redis當中的驗證碼(效期只有10分鐘)
		String authenCode = RandomAuthenCode.setAuthenCode(memberType, shelterAcct);

		// 寄信給該註冊帳號
		MailService.sendAuthenCode(shelterAcct, authenCode);
	}

	private void shelterRegister(HttpServletRequest req, HttpServletResponse res) throws UnsupportedEncodingException {
		// 驗證使用者註冊的資料，使用Map裝入(有錯誤的話)
		Map<String, String> errorMsg = new HashMap<>();

		// 已經用ajax先檢查了(帳號)
		String shelterAcct = req.getParameter("shelteraccount");

		// 驗證驗證碼
		String authenCode = req.getParameter("authencode");
		String authenCodeFromJedis = RandomAuthenCode.getAuthenCode("Shelter", shelterAcct);
		if (authenCodeFromJedis == null) {
			errorMsg.put("shelterAuthenCodeErr", "請先取得驗證碼!!");
		} else {
			if (!authenCode.equalsIgnoreCase(authenCodeFromJedis)) {
				errorMsg.put("shelterAuthenCodeErr", "驗證碼輸入錯誤");
			}
		}

		// 驗證密碼
		String shelterPwd = req.getParameter("password");
		String shelterPwdReg = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+])[A-Za-z\\d!@#$%^&*()_+]{1,20}$";
		if (!shelterPwd.matches(shelterPwdReg)) {
			errorMsg.put("shelterPwdErr", "密碼格式不正確，必須包含英文大小寫及特殊符號");
		}
		shelterPwd = Sha1Util.encodePwd(shelterPwd);

		// 驗證收容所名稱
		String shelterName = req.getParameter("shelterName");
		String shelterNameReg = "^[\\u4e00-\\u9fa5]{1,50}$";
		if (!shelterName.matches(shelterNameReg)) {
			errorMsg.put("shelterNameErr", "名稱只能使用中文字，並限制50個字以內!!");
		}

		// 驗證電話號碼
		String shelterPhoneNum = req.getParameter("phone");
		String shelterPhoneNumReg = "^0[2-9][0-9]{7,8}$";
		if (!shelterPhoneNum.matches(shelterPhoneNumReg)) {
			errorMsg.put("shelterPhoneNumErr", "電話號碼格式錯誤!!");
		}

		// 驗證地址
		String shelterAddress = req.getParameter("address");
		String addressReg = "^[\\u4e00-\\u9fa50-9\\s]+$";
		if (!shelterAddress.matches(addressReg)) {
			errorMsg.put("addressErr", "地址只能包含中文與數字!!");
		} else {
			shelterAddress = req.getParameter("country") + req.getParameter("district") + shelterAddress;
		}

		res.setContentType("application/json; charset=UTF-8");
		PrintWriter out;
		// 如果有錯誤資訊就回傳前端
		if (errorMsg.size() > 0) {
			Gson gson = new Gson();
			String errorMsgJson = gson.toJson(errorMsg);
			try {
				out = res.getWriter();
				out.print(errorMsgJson);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			// 取得地址的經緯度資訊
			Map<String, Double> location = addressToLatitudeAndLongitude(shelterAddress);
			Double shelterLng = location.get("lng");
			Double shelterLat = location.get("lat");

			Shelter shelter = new Shelter(shelterAcct, shelterPwd, shelterName, shelterPhoneNum, shelterAddress,
					shelterLng, shelterLat);
			shelter = shelterService.addShelter(shelter);

			// 把shelter資訊放到Session中
			req.getSession().setAttribute("shelter", shelter);

			// 這裡要重導還是轉發，目的地應該是首頁?
			Gson gson = new Gson();
			String redirectPath = gson.toJson(req.getContextPath() + "/petjsp/shelter_update.jsp");
			try {
				out = res.getWriter();
				out.print(redirectPath);
			} catch (IOException e) {
				e.printStackTrace();
			}

			// 寄信表示註冊成功
			Thread thread = new Thread(() -> {
				MailService.registerSuccess(shelterAcct);
			});
			thread.start();
		}
	}

	// 地址轉經緯度
	private Map<String, Double> addressToLatitudeAndLongitude(String address) {
		Properties properties = new Properties();
		String apiKey;
		String apiUrl;
		try {
			InputStream is = getClass().getClassLoader().getResourceAsStream("geocoding.properties");
			properties.load(is);
			apiKey = (String) properties.getProperty("apikey");
			apiUrl = "https://maps.googleapis.com/maps/api/geocode/json?address=" + address + "&key=" + apiKey;
		} catch (IOException e) {
			System.out.println("找不到該設定檔!!");
			return null;
		}

		Map<String, Double> location = new HashMap<>();
		try {
			// Open a connection to the URL
			URL url = new URL(apiUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			// Get the response
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder response = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			reader.close();

			// Parse the JSON response using Gson
			JsonElement jsonElement = JsonParser.parseString(response.toString());
			JsonObject jsonObject = jsonElement.getAsJsonObject();
			JsonArray results = jsonObject.getAsJsonArray("results");

			if (results.size() > 0) {
				JsonObject locations = results.get(0).getAsJsonObject().getAsJsonObject("geometry")
						.getAsJsonObject("location");
				double latitude = locations.getAsJsonPrimitive("lat").getAsDouble();
				double longitude = locations.getAsJsonPrimitive("lng").getAsDouble();

				location.put("lat", latitude);
				location.put("lng", longitude);
			} else {
				System.out.println("No results found.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return location;
	}

	private void setNewPassword(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String shelterAcct = req.getParameter("account").trim();
		String authenCode = req.getParameter("authencode").trim();

		Map<String, String> errorMsg = new HashMap<>();
		res.setContentType("application/json; charset=UTF-8");
		Gson gson = new Gson();
		PrintWriter out = res.getWriter();
		if (!shelterService.existShelterAccount(shelterAcct)) {
			errorMsg.put("accountErr", "帳號不存在!!");
			String errorMsgJson = gson.toJson(errorMsg);
			out.print(errorMsgJson);
			return;
		}

		String authenCodeFromJedis = RandomAuthenCode.getAuthenCode("ShelterNewPwd", shelterAcct);
		if (authenCodeFromJedis == null) {
			errorMsg.put("authenCodeErr", "請先取得驗證碼!!");
		} else {
			if (!authenCode.equalsIgnoreCase(authenCodeFromJedis)) {
				errorMsg.put("authenCodeErr", "驗證碼輸入錯誤");
			}
		}

		if (errorMsg.size() > 0) {
			String errorMsgJson = gson.toJson(errorMsg);
			out.print(errorMsgJson);
		} else {
			String result = shelterService.getNewPwd(shelterAcct);
			Map<String, String> successMsg = new HashMap<>();
			successMsg.put("success", result);

			out.print(gson.toJson(successMsg));
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

}
