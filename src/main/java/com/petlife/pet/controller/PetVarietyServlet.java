package com.petlife.pet.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.petlife.pet.entity.PetVariety;
import com.petlife.pet.service.PetVarietyService;
import com.petlife.pet.serviceimpl.PetVarietyServiceImpl;



@WebServlet("/project/pet")

public class PetVarietyServlet extends HttpServlet {
	// 一個 servlet 實體對應一個 service 實體
	private PetVarietyService petVarietyService;

	@Override
	public void init() throws ServletException {
		petVarietyService = new PetVarietyServiceImpl();
	}

//   =======================dopost============================= 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost Entry");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String forwardPath = "";
		System.out.println(action);
		switch (action) {
		case "insert":
			forwardPath = insert(req, res);
			break;
		case "update":
			forwardPath = update(req, res);
			break;

		case "update_put":
			forwardPath = update_put(req, res);
			break;
		case "getCompositePetsQuery":
			forwardPath = getCompositeEmpsQuery(req, res);
			break;
		default:
			forwardPath = "/index.jsp";
		}
		
		if (!forwardPath.isEmpty()) {
			res.setContentType("text/html; charset=UTF-8");
			RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
			dispatcher.forward(req, res);
		}
		
	}

// =======================insert=====================================
	private String insert(HttpServletRequest req, HttpServletResponse res) {
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		PetVariety petVariety = new PetVariety();
		try {
			String type = req.getParameter("Type");
			String variety = req.getParameter("Variety");
			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (variety == null || variety.trim().length() == 0) {
				throw new CalException("品種: 請勿空白");
			} else if (!variety.trim().matches(enameReg)) { // 以下練習正則(規)表示式(regular-expression)
				throw new CalException("品種名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			petVariety.setType(type);
			petVariety.setVariety(variety);
			petVarietyService.addPetVariety(petVariety);

		} catch (Exception e) {
			errorMsgs.add(e.getMessage());
			e.printStackTrace();
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("PetVariety", petVariety); // 含有輸入格式錯誤的empVO物件,也存入req
				return "/emp/petVariety_insert.jsp";
			}

		}
		return "/emp/petVariety_insert.jsp";
	}

//=======================update_put完成==========================
	private String update_put(HttpServletRequest req, HttpServletResponse res) {
		Integer Id = Integer.valueOf(req.getParameter("Id"));
		String type = req.getParameter("Type");
		String variety = req.getParameter("Variety");
		PetVariety petVariety = new PetVariety();
		petVariety.setId(Id);
		petVariety.setType(type);
		petVariety.setVariety(variety);
		petVarietyService.update_putPetVariety(petVariety);
		req.setAttribute("petVariety", petVariety);
		return "/emp/petVariety_update_put.jsp";
	}

//======================準備update=============================
	private String update(HttpServletRequest req, HttpServletResponse res) {

		Integer Id = Integer.valueOf(req.getParameter("id"));
		PetVariety petVariety = petVarietyService.getOnePetVariety(Id);
		req.setAttribute("petVariety", petVariety);

		return "/emp/petVariety_update.jsp";
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String id = req.getParameter("Id");
		petVarietyService.deletePetVariety(Integer.parseInt(id));
	}
//=======================doget =================================

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
		}

//=============================查全部===================================

	private String getAllPetVariety(HttpServletRequest req, HttpServletResponse res) {
		List<PetVariety> petVarietyList = petVarietyService.getAll();
		req.setAttribute("petVarietyList", petVarietyList);
		return "/emp/listAllEmps.jsp";
	}

//============================複合查詢============================
	private String getCompositeEmpsQuery(HttpServletRequest req, HttpServletResponse res) throws IOException {
		try {
			System.out.println("getPetListAsync Entry");
			String type = req.getParameter("type");
			System.out.println(type);

			Map<String, String[]> map = new HashMap<String, String[]>();
			map.put("type", new String[] { type });

			if (map != null) {
				List<PetVariety> petList = petVarietyService.getByCompositeQuery(map);
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				res.setContentType("application/json;charset=UTF-8");
				res.getWriter().println(gson.toJson(petList));
			}
		} catch (Exception e) {
			res.resetBuffer();
			res.setContentType("application/json;charset=UTF-8");
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

			res.getWriter().write(e.getMessage());
		}

		return "";
//		Map<String, String[]> map = req.getParameterMap();
//
//		if (map != null) {
//			List<PetVariety> petVarietyList = petVarietyService.getByCompositeQuery(map);
//			req.setAttribute("petVarietyList", petVarietyList);
//		} else {
//			return "/index.jsp";
//		}
//		return "/emp/listCompositeQueryEmps.jsp";
	}

}