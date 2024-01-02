package com.petlife.pet.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
import com.petlife.pet.service.PetVarietyService;
import com.petlife.pet.serviceimpl.PetPhotoServiceImpl;
import com.petlife.pet.serviceimpl.PetServiceImpl;
import com.petlife.pet.serviceimpl.PetVarietyServiceImpl;
import com.petlife.shelter.entity.Reservation;
import com.petlife.shelter.service.ReservationService;
import com.petlife.shelter.service.ShelterService;
import com.petlife.shelter.service.impl.ReservationServiceImpl;
import com.petlife.shelter.service.impl.ShelterServiceImpl;

@WebServlet("/project/pet.do")
@MultipartConfig(fileSizeThreshold = 0 * 1024 * 1024, maxFileSize = 1 * 1024 * 1024, maxRequestSize = 10 * 1024 * 1024)
public class PetServlet extends HttpServlet {

	public PetServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	private PetService petService;
	private PetVarietyService petVarietyService;
	private PetPhotoService petPhotoService;
	private ShelterService shelterService;
	private ReservationService reservationService;

	@Override
	public void init() throws ServletException {
		petService = new PetServiceImpl();
		petVarietyService = new PetVarietyServiceImpl();
		petPhotoService = new PetPhotoServiceImpl();
		shelterService = new ShelterServiceImpl();
		reservationService = new ReservationServiceImpl();
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
		case "getPetById":// for test
			forwardPath = getPetById(req, res, jsonNode);
			break;
		case "getPetById2":
			forwardPath = getPetById2(req, res);
			break;
		case "getPetListAsync":
			forwardPath = getPetListAsync(req, res, jsonNode);
			break;
		case "getCompositePetsQuery":
			forwardPath = getCompositePetsQuery(req, res);
//			forwardPath = "/index.jsp";
			break;
		case "getCompositePetsQuery2":
			forwardPath = getCompositePetsQuery2(req, res);
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

		case "updatePet":
			updatePet(req, res);
			break;
		default:
			forwardPath = "/index.jsp";
		}
		if (!forwardPath.isEmpty()) {
			RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
			dispatcher.forward(req, res);
		}
	}

	private void updatePet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Integer resId = Integer.valueOf(req.getParameter("resId"));
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		Reservation reservation = reservationService.getResByResId(resId);
		Integer petId = reservation.getPetId();
		Pet pet = petService.getOnePet(petId);
		Boolean adopt = Boolean.valueOf(req.getParameter("adopt"));
		System.out.println(adopt);
		pet.setAdopt(adopt);

		petService.updatePet(pet);

		out.print("<font color='red'>已將收容動物重新上架!!</font>");

	}

	private String update_put(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Integer Id = Integer.valueOf(req.getParameter("id").trim());
		System.out.println(Id);
		Pet pet = petService.getOnePet(Id);
		Integer shelterId = pet.getShelterId();
		String petGender = req.getParameter("petGender").trim();
		Integer petVarietyId = Integer.valueOf(req.getParameter("petVarietyId").trim());
		String petLigation = req.getParameter("petLigation").trim();
		String petColor = req.getParameter("petColor").trim();
		Boolean adopt = Boolean.valueOf(req.getParameter("adopt"));
		System.out.println(adopt);
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

		pet.setShelterId(shelterId);
		pet.setPetGender(petGender);
		pet.setPetVariety(petVarietyId);
		pet.setPetLigation(petLigation);
		pet.setPetColor(petColor);
		pet.setAdopt(adopt);
		pet.setPetContent(pet_content);
		pet.setComeInDate(comeInDate);
		pet.setPetCage(petCage);
		pet.setPetNum(petNum);
		pet.setAdopted(adopted);
		pet.setUserId(userId);
		pet.setAdoptDate(adoptDate);
		// 準備集合抓取照片
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
				pet.getPetPhotos().add(petPhoto);
			}
		}
		String[] photoIds = req.getParameterValues("deletePhoto");
		if (photoIds != null) {
			System.out.println("delete Photo Entry:");
			for (String photoId : photoIds) {
				Integer id = Integer.valueOf(photoId);
				System.out.println(id);
				PetPhoto petphoto = petPhotoService.getOnePetphoto(id);
				pet.getPetPhotos().remove(petphoto);
				petPhotoService.deletePetPhoto(id);
			}
		}

		System.out.println(pet.getPetPhotos().size());
		if (pet.getPetPhotos().size() != 0) {

		}
		try {
			petService.updatePet(pet);
			req.setAttribute("pet", pet);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/petjsp/petUpdate_put.jsp";
	}

	private String getPetById(HttpServletRequest req, HttpServletResponse res, JsonNode node) throws IOException {
		try {
			System.out.println("PetServlet: getById Entry");
			Integer Id = Integer.valueOf(req.getParameter("id"));
			Pet pet = petService.getOnePet(Id);
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
			res.setContentType("application/json;charset=UTF-8");
			res.getWriter().println(gson.toJson(pet));
		} catch (Exception e) {
			res.resetBuffer();
			res.setContentType("application/json;charset=UTF-8");
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			res.getWriter().write(e.getMessage());
		}

		return "";
	}

	// 收容所前端搜尋用，思涵2023/12/21新增
	private String getPetById2(HttpServletRequest req, HttpServletResponse res) throws IOException {
		try {
			System.out.println("PetServlet: getById Entry");
			Integer Id = Integer.valueOf(req.getParameter("id"));
			System.out.println("getPetById2 pet_Id = " + Id);
			Pet pet = petService.getOnePet(Id);
			System.out.println(pet.getPetPhotos().size());

			
			req.setAttribute("pet", pet);
		} catch (Exception e) {
			e.getStackTrace();
		}

		return "/shelter/adoptionDetails.jsp";
	}

	private String update(HttpServletRequest req, HttpServletResponse res) {
		Integer Id = Integer.valueOf(req.getParameter("rowId"));
		Pet pet = petService.getOnePet(Id);
		req.setAttribute("pet", pet);

		return "/petjsp/petUpdate.jsp";
	}

	// insert 資料庫
	private String insert(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		System.out.println("PetServlet :insert Entry");
		Pet pet = new Pet();
		Integer shelterId = Integer.valueOf(req.getParameter("id").trim());
		String petGender = req.getParameter("petGender").trim();
		Integer petVarietyId = Integer.valueOf(req.getParameter("petVarietyId").trim());
		System.out.println(petVarietyId);
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
		System.out.println(petVarietyId);
		pet.setVariety(petVarietyService.getOnePetVariety(petVarietyId));
		pet.setShelter(shelterService.getShelterByShelterId(shelterId));
		// 準備集合抓取照片
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
				pet.getPetPhotos().add(petPhoto);
			}
		}

		try {
			pet = petService.addPet(pet);
			req.setAttribute("pet", pet);
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
			map.put("shelterId", new String[] { shelterId });
			if (map != null) {
				List<Pet> petList = petService.getByCompositeQuery(map);
				Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
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

	// 複合查詢 (pet_search.html)
	private String getCompositePetsQuery(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("getCompositePetsQuery Entry");
		Map<String, String[]> map = req.getParameterMap();

		if (map != null) {
			List<Pet> petList = petService.getByCompositeQuery(map);
			req.setAttribute("petList", petList);
		}
		return "/petjsp/listAllPets.jsp";
	}

	// 收容所前端搜尋使用 思涵2023/12/21新增
	private String getCompositePetsQuery2(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("getCompositePetsQuery2 Entry");
		Map<String, String[]> map = req.getParameterMap();

		if (map != null) {
			List<Pet> petList = petService.getByCompositeQuery(map);
			petList = petList.stream()
							.filter(pet -> pet.getAdopt() == true)
							.collect(Collectors.toList());
			req.setAttribute("petList", petList);
		}
		return "/shelter/listAllPets.jsp";
	}

	// 全部
	private String getAllPet(HttpServletRequest req, HttpServletResponse res, JsonNode node) throws IOException {
		List<Pet> petList = petService.getAll();
		ArrayList<Pet> petViewList = new ArrayList<>();
		for (Pet pet : petList) {
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
