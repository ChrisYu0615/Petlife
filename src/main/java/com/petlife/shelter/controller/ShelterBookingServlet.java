package com.petlife.shelter.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oracle.wls.shaded.org.apache.bcel.generic.IF_ACMPEQ;
import com.petlife.shelter.entity.Reservation;
import com.petlife.shelter.entity.Shelter;
import com.petlife.shelter.entity.ShelterBooking;
import com.petlife.shelter.service.ReservationService;
import com.petlife.shelter.service.ShelterBookingService;
import com.petlife.shelter.service.ShelterService;
import com.petlife.shelter.service.impl.ReservationServiceImpl;
import com.petlife.shelter.service.impl.ShelterBookingServiceImpl;
import com.petlife.shelter.service.impl.ShelterServiceImpl;

@WebServlet("/project/shelterbooking.do")
public class ShelterBookingServlet extends HttpServlet {
	private ShelterBookingService shelterBookingService;

	public void init() throws ServletException {
		shelterBookingService = new ShelterBookingServiceImpl();
	}

	private ReservationService reservationService;

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
			forwardPath = insert(req, res, jsonNode);
			break;
		case "getAllShelterBooking":
			forwardPath = getAllShelterBooking(req, res, jsonNode);
			break;
		case "getCompositePetsQuery":
			forwardPath = getCompositePetsQuery(req, res);
			break;
		case "getAvalibleBookings":
			forwardPath = getAvalibleBookings(req, res);
			break;
		case "setAddBookingNum":
			forwardPath = setAddBookingNum(req, res);
			break;
		default:
			forwardPath = "/index.jsp";
		}
		if (!forwardPath.isEmpty()) {
			RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
			dispatcher.forward(req, res);
		}
	}

	// 取得可以預約的判斷
	private String getAvalibleBookings(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		System.out.println("ShelterBookingServlet1: getAvalibleBookings Entry");
		Map<String, String[]> map = req.getParameterMap();

		if (map != null) {
			List<ShelterBooking> shelterBookingList = shelterBookingService.getByCompositeQuery(map);
			shelterBookingList = shelterBookingList.stream() // 轉stream
					.filter(booking -> booking.getShelterBookingNum() != booking.getShelterBookingMax()) // ->之後是取出TRUE的資料
					.collect(Collectors.toList()); // 再轉回list
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
			res.setContentType("application/json;charset=UTF-8");
			res.getWriter().println(gson.toJson(shelterBookingList));
		}
		return "";
	}

	// 新增預約數+1
	private String setAddBookingNum(HttpServletRequest req, HttpServletResponse res) {

		String id = req.getParameter("bookingId");
		ShelterBooking idData = shelterBookingService.getOneShelterBooking(Integer.valueOf(id));
		
		shelterBookingService = new ShelterBookingServiceImpl(); // 替換成實際的實現類

		idData.setShelterBookingNum(idData.getShelterBookingNum() + 1);
		// 確認預約更新是否成功
		shelterBookingService.updateShelterBooking(idData);
		System.out.println("shelterBookingDate: " + idData.getShelterBookingDate());
		System.out.println("shelterBookingTime: " + idData.getShelterBookingTime());
		
		// 更新後insert一筆預約
		reservationService = new ReservationServiceImpl();
		Integer shelterId = Integer.valueOf(req.getParameter("shelter_Id").trim());
		ShelterService shelterService = new ShelterServiceImpl();
		Shelter shelter = shelterService.getShelterByShelterId(shelterId);
		Integer shelterBookingId = Integer.valueOf(req.getParameter("bookingId").trim());
		System.out.println("resshelterBookingId" + shelterBookingId);
		Integer userId = Integer.valueOf(req.getParameter("userId"));
		System.out.println("resuserId" + userId);
		Integer petId = Integer.valueOf(req.getParameter("pet_id"));
		System.out.println("respetId" + petId);

		Reservation reservation = new Reservation(shelter, shelterBookingId, userId, petId);

		reservation = reservationService.addRes(reservation);
		req.setAttribute("reservation", reservation);

		return "/shelter/adoptionConfirm.jsp";

	}

	private String getCompositePetsQuery(HttpServletRequest req, HttpServletResponse res) {

		System.out.println("ShelterBookingServlet: getCompositePetsQuery Entry");
		Map<String, String[]> map = req.getParameterMap();

		if (map != null) {
			List<ShelterBooking> shelterBookingList = shelterBookingService.getByCompositeQuery(map);
			req.setAttribute("shelterBookingList", shelterBookingList);
			if (shelterBookingList.size() != 0) {
				return "/petjsp/listshelterbooking.jsp";
			} else if (shelterBookingList.size() == 0) {
				return "/petjsp/pet_autodate.jsp";
			}
		}
		return "";
	}

	private String insert(HttpServletRequest req, HttpServletResponse res, JsonNode node) throws IOException {
		System.out.println("ShelterBookingServlet : insert Entry");

		JsonNode shelterBookings = node.get("shelterBookings");

		if (shelterBookings.isArray()) {
			for (JsonNode shelterBookingJson : shelterBookings) {
				ShelterBooking shelterBooking = new ShelterBooking();
				String date = shelterBookingJson.get("date").asText();
				String time = shelterBookingJson.get("time").asText();
				SimpleDateFormat inputFormat = new SimpleDateFormat("HH:mm");
				String outputTimeString = "";

				try {
					Date inputDate = inputFormat.parse(time);
					java.sql.Timestamp shelterBookingTime = new java.sql.Timestamp(inputDate.getTime());
					shelterBooking.setShelterBookingTime(shelterBookingTime);
				} catch (ParseException e) {
					e.printStackTrace();
				}

            	 System.out.println(outputTimeString);
            	 
            	 String shelter=shelterBookingJson.get("shelter").asText();
            	 String shlterBookingNum=shelterBookingJson.get("shelter_num").asText();
            	 String shlterBookingNumMax=shelterBookingJson.get("shelter_num_Max").asText();
            	 
            	 
            	Integer shelterId=Integer.valueOf(shelter);
            	Integer shelterNum=Integer.valueOf(shlterBookingNum);
            	Integer shelterNumMax=Integer.valueOf(shlterBookingNumMax);
            	java.sql.Date shelterBookingDate = null;
            	shelterBookingDate = java.sql.Date.valueOf(date);
            	System.out.println(date);
           
        		
        		
        		shelterBooking.setShelterBookingDate(shelterBookingDate);
        		shelterBooking.setShelterBookingNum(shelterNum);
        		
        		shelterBooking.setShelterId(shelterId);
        		shelterBooking.setShelterBookingMax(shelterNumMax);
        		
                shelterBookingService.addShelterBooking(shelterBooking);
             }
         }
         
		return "";
	}

//目前沒有用
	private String getAllShelterBooking(HttpServletRequest req, HttpServletResponse res, JsonNode jsonNode) {
		List<ShelterBooking> shelterBookingList = shelterBookingService.getAll();
		ArrayList<ShelterBooking> shelterBookingViewList = new ArrayList<>();
//		for(Pet pet : petList) {
//			Pet petTemp = new Pet();
//			petTemp.setId(pet.getId());
//			petTemp.setComeInDate(pet.getComeInDate());
//			petTemp.setPetVariety(pet.getPetVariety());
//			petTemp.setPetGender(pet.getPetGender());
//			petTemp.setPetNum(pet.getPetNum());
//			petTemp.setPetLigation(pet.getPetLigation());
//			petTemp.setAdoptDate(pet.getAdoptDate());
//			
//		
//			petViewList.add(petTemp);
//		}
		for (ShelterBooking shelterBooking : shelterBookingList) {
			ShelterBooking shelterBookingTemp = new ShelterBooking();

		}
		req.setAttribute("shelterBookingList", shelterBookingList);
		return "/petjsp/resTable.jsp";

	}

	private String readJsonRequest(HttpServletRequest request) throws IOException {
		System.out.println("ShelterBookingServlet : readJsonRequest Entry");
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = request.getReader();

		String line;
		while ((line = bufferedReader.readLine()) != null) {
			stringBuilder.append(line);
		}
		System.out.println(stringBuilder.toString());
		System.out.println("ShelterBookingServlet : readJsonRequest End");
		return stringBuilder.toString();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

}
