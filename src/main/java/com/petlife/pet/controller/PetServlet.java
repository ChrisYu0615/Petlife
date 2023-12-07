package com.petlife.pet.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.petlife.pet.entity.Pet;
import com.petlife.pet.entity.PetPhoto;
import com.petlife.pet.service.PetPhotoService;
import com.petlife.pet.service.PetService;
import com.petlife.pet.serviceimpl.PetPhotoServiceImpl;
import com.petlife.pet.serviceimpl.PetServiceImpl;



@WebServlet("/project/pet.do")
@MultipartConfig(fileSizeThreshold = 0 * 1024 * 1024, maxFileSize = 1 * 1024 * 1024, maxRequestSize = 10 * 1024 * 1024)
public class PetServlet extends HttpServlet {

	public PetServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	private PetService petService;
	private PetPhotoService petPhotoService;

	@Override
	public void init() throws ServletException {
		petService = new PetServiceImpl();
		petPhotoService = new PetPhotoServiceImpl();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String jsonRequest = "";
		if (req.getContentType() != null && req.getContentType().startsWith("application/json")) {
			// Request to JSON
			jsonRequest = readJsonRequest(req);
        }
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(jsonRequest);

		String action = req.getParameter("action");
		String forwardPath = "";
		System.out.println(action);
		switch (action) {
		case "insert":
			forwardPath = insert(req, res);
			break;
		case "getPetListAsync":
			forwardPath = getPetListAsync(req, res, jsonNode);
			break;

		case "getCompositePetsQuery":
			forwardPath = getCompositePetsQuery(req, res);
//			forwardPath = "/index.jsp";
			break;
		case "getAllPet":
			forwardPath = getAllPet(req, res, jsonNode);
			break;
		case "update":
			forwardPath = update(req, res); 
			break;
		case "update_put":
			forwardPath = update_put(req, res);
			break;
		default:
			forwardPath = "/index.jsp";
		}
		if (!forwardPath.isEmpty()) {
			RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
			dispatcher.forward(req, res);
		}
	}
	
	private String update_put(HttpServletRequest req, HttpServletResponse res) {
		Integer Id = Integer.valueOf(req.getParameter("id").trim());
		Integer shelterId =300000002;
		String petGender = req.getParameter("petGender").trim();
		Integer petVarietyId = Integer.valueOf(req.getParameter("petVarietyId").trim());
		String petLigation = req.getParameter("petLigation").trim();
		String petColor = req.getParameter("petColor").trim();
		Boolean adopt = Boolean.valueOf(req.getParameter("adopt"));
		String pet_content = req.getParameter("pet_content").trim();
		java.sql.Date comeInDate = null;
		comeInDate = java.sql.Date.valueOf(req.getParameter("comeInDate"));
		String petCage = req.getParameter("petCage").trim();
		String petNum = req.getParameter("petNum").trim();
		
		Boolean adopted = Boolean.valueOf(req.getParameter("adopted"));
		String userId = null;
		java.sql.Date adoptDate = null;
		if (adopted == true) {
			userId = req.getParameter("userId").trim();
			System.out.println(req.getParameter("adopt_date"));
			adoptDate = java.sql.Date.valueOf(req.getParameter("adopt_date"));
		}
		
		Pet pet =new Pet(Id,shelterId,petGender,petVarietyId,petLigation,petColor,adopt,pet_content,comeInDate,petCage,petNum,adopted,userId,adoptDate);
		
		
		try {
			
			System.out.println("1");
			petService.updatePet(pet);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/test/pet_search.html";
		
	}

	private String update(HttpServletRequest req, HttpServletResponse res) {
//		System.out.println(req.getParameter("rowId"));
		Integer Id = Integer.valueOf(req.getParameter("rowId"));
		Pet pet = petService.getOnePet(Id);
		req.setAttribute("pet", pet);

		return "/emp/petUpdate.jsp";
	}

	// insert 資料庫
	private String insert(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		System.out.println("insert Entry");
		Pet pet = new Pet();
		Integer shelterId = Integer.valueOf(req.getParameter("shelterId").trim());
		String petGender = req.getParameter("petGender").trim();
		Integer petVarietyId = Integer.valueOf(req.getParameter("petVarietyId").trim());
		String petLigation = req.getParameter("petLigation").trim();
		String petColor = req.getParameter("petColor").trim();
		Boolean adopt = Boolean.valueOf(req.getParameter("adopt"));
		String pet_content = req.getParameter("pet_content").trim();
		java.sql.Date comeInDate = null;
		comeInDate = java.sql.Date.valueOf(req.getParameter("comeInDate"));
		String petCage = req.getParameter("petCage").trim();
		String petNum = req.getParameter("petNum").trim();
		Boolean adopted = Boolean.valueOf(req.getParameter("adopted"));
		// 當收容所選擇沒有被領養時我會打null 進mysql
		String userId = null;
		java.sql.Date adopt_date = null;
		if (adopted == true) {
			userId = req.getParameter("userId").trim();
			adopt_date = java.sql.Date.valueOf(req.getParameter("adopt_date"));
		}


		pet = new Pet(shelterId, petGender, petVarietyId, petLigation, petColor, adopt, pet_content, comeInDate,
				petCage, petNum, adopted, userId, adopt_date);
		// 準備集合抓取照片
		List<PetPhoto> photoList = new ArrayList<PetPhoto>();
		for (Part part : req.getParts()) {
			if (!part.getName().equals("petphoto"))
				continue;
			InputStream in = part.getInputStream();
			byte[] petPhoto_byte = null;
			if (in.available() != 0) {
				petPhoto_byte = new byte[in.available()];
				in.read(petPhoto_byte);
				in.close();
				// 將抓取的照片存進petphoto->table
				PetPhoto petPhoto = new PetPhoto();
				petPhoto.setPet(pet);
				petPhoto.setPetPhoto(petPhoto_byte);
				photoList.add(petPhoto);
			}
		}

		try {
			pet = petService.addPet(pet);
			for (PetPhoto petPhoto : photoList) {
				petPhotoService.addPetPhoto(petPhoto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/petjsp/pet_insert.jsp";

	}
	
	private String readJsonRequest(HttpServletRequest request) throws IOException {
		System.out.println("readJsonRequest Entry");
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = request.getReader();

		String line;
		while ((line = bufferedReader.readLine()) != null) {
			stringBuilder.append(line);
		}
		
		System.out.println(stringBuilder.toString());
		return stringBuilder.toString();
	}
	

//將insert 前資料做檢查是否重複(petfrom.html)
	private String getPetListAsync(HttpServletRequest req, HttpServletResponse res, JsonNode node) throws IOException {
		try {
	        String shelterId = node.get("shelterId").asText();
	        String petNum = node.get("petNum").asText();
			Map<String, String[]> map = new HashMap<String, String[]>();
			map.put("petNum", new String[] { petNum });
			map.put("shelterId", new String[] {shelterId});

			if (map != null) {
				List<Pet> petList = petService.getByCompositeQuery(map);
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

	}
	//複合查詢 (pet_search.html)
	private String getCompositePetsQuery(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("getCompositePetsQuery Entry");
		Map<String, String[]> map = req.getParameterMap();

		if (map != null) {
			List<Pet> petList = petService.getByCompositeQuery(map);
			req.setAttribute("petList", petList);
		} 
		return "/petjsp/listAllPets.jsp";
	}
	//全部
	private String getAllPet(HttpServletRequest req, HttpServletResponse res,JsonNode node)throws IOException {
		List<Pet> petList = petService.getAll();
		ArrayList<Pet> petViewList = new ArrayList<>();
		for(Pet pet : petList) {
			Pet petTemp = new Pet();
			petTemp.setId(pet.getId());
			petTemp.setComeInDate(pet.getComeInDate());
			petTemp.setPetVariety(pet.getPetVariety());
			petTemp.setPetGender(pet.getPetGender());
			petTemp.setPetNum(pet.getPetNum());
			petTemp.setPetLigation(pet.getPetLigation());
			petTemp.setAdoptDate(pet.getAdoptDate());
			
		
			petViewList.add(petTemp);
		}
		req.setAttribute("petList", petList);
		return "/petjsp/listAllPets.jsp";
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
}