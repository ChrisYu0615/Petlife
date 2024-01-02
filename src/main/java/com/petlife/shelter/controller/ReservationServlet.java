package com.petlife.shelter.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.petlife.pet.entity.Pet;
import com.petlife.pet.service.PetService;
import com.petlife.pet.serviceimpl.PetServiceImpl;
import com.petlife.shelter.dao.ResTypeDAO;
import com.petlife.shelter.dao.impl.ResTypeDAOImpl;
import com.petlife.shelter.entity.ResType;
import com.petlife.shelter.entity.Reservation;
import com.petlife.shelter.entity.Shelter;
import com.petlife.shelter.service.ReservationService;
import com.petlife.shelter.service.ShelterService;
import com.petlife.shelter.service.impl.ReservationServiceImpl;
import com.petlife.shelter.service.impl.ShelterServiceImpl;
import com.petlife.user.entity.User;

@WebServlet("/shelter/reservation.do")
@MultipartConfig
public class ReservationServlet extends HttpServlet {

	private ReservationService reservationService;
	private PetService petService;

	@Override
	public void init() throws ServletException {
		reservationService = new ReservationServiceImpl();
		petService = new PetServiceImpl();
	}

	@Override
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
		switch (action) {
		case "getAll":
			forwardPath = getAllReservations(req, res);
			break;
		case "compositeQuery":
			forwardPath = getCompositeReservationQuery(req, res);
			break;
		case "insert":
			forwardPath = getAddReservation(req, res);
			break;
		case "getById":// for test
			forwardPath = getReservationById(req, res, jsonNode);
			break;
		case "update":
			forwardPath = update(req, res);
			break;
		case "getByUserId":
			forwardPath = getReservationsByUserId(req, res);
			break;
		case "getResByResId":
			forwardPath = getResByResId(req, res);
			break;
		case "cancelReservation":
			forwardPath = cancelReservation(req, res);
			break;
		case "update_resType":
				forwardPath = update_resType(req,res);
				break;
//			case "update":
//				forwardPath = getUpdateShelter(req, res);
//				break;
		case "getByShelterBookingId":
			forwardPath = getByShelterBookingId(req, res);
			break;
		default:
			forwardPath = "/index.jsp";
		}
		if(!forwardPath.isEmpty()) {
			res.setContentType("text/html; charset=UTF-8");
			RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
			dispatcher.forward(req, res);
		}
		
	}

	private String getByShelterBookingId(HttpServletRequest req, HttpServletResponse res) {
		Integer ShelterBookingId = Integer.valueOf(req.getParameter("rowId"));
		List<Reservation> reservationList = reservationService.getResByShelterBookingId(ShelterBookingId);
		
		
		req.setAttribute("reservationList", reservationList);
		return "/petjsp/booking_put.jsp";
	}

	private String update_resType(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("ReservationServlet: update_resType Entry");
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = dateFormat.format(currentDate);
		System.out.println(dateString);
//		java.sql.Date date =java.sql.Date.valueOf(dateString);
//		System.out.println(date);
		String reservationType = "2";
		 Map<String, String[]> myMap = new HashMap<>();
		 myMap.put("Date",new String[] {dateString});
		 myMap.put("resType",new String[] {reservationType});
		 if (myMap != null) {
				List<Reservation> reservationList = reservationService.getResByCompositeQuery(myMap);
//				req.setAttribute("reservationList", reservationList);
				for(Reservation reservation : reservationList) {
					reservation.setResTypeId(4);
					reservationService.updateRes(reservation);
				}
			}
	     return "";
	}

	private String cancelReservation(HttpServletRequest req, HttpServletResponse res) {
		Integer userId = Integer.valueOf(req.getParameter("memberId"));
		Integer resId = Integer.valueOf(req.getParameter("resId"));
		// 變更狀態
		Reservation reservation = reservationService.getResByResType(resId);
		ResTypeDAO dao = new ResTypeDAOImpl();
		ResType resType = dao.getById(3);
		reservation.setResType(resType);
		reservation.setResTypeId(3);
		// 讓原本預約次數+1
		reservation.getShelterBooking()
				.setShelterBookingNum(reservation.getShelterBooking().getShelterBookingNum() - 1);

		reservationService.updateRes(reservation);

		return "/shelter/reservation.do?action=getByUserId&memberId=" + userId;
	}

	private String getReservationsByUserId(HttpServletRequest req, HttpServletResponse res) {
		Integer userId = Integer.valueOf(req.getParameter("memberId"));
		List<Reservation> reservationList = reservationService.getAll(userId);

		req.setAttribute("getAllReservations", reservationList);
		return "/member_center/reservation_management.jsp";
	}
	
	// 0103思涵
	private String getResByResId(HttpServletRequest req, HttpServletResponse res) {
		Integer resId = Integer.valueOf(req.getParameter("resId"));
		System.out.println(resId);
		Reservation reservation = reservationService.getResByResId(resId);
		
		req.setAttribute("reservation", reservation);
		return "/shelter/resLookUp.jsp";
	}

	// 更新過1215詩涵
	private String getCompositeReservationQuery(HttpServletRequest req, HttpServletResponse res)throws IOException {

		System.out.println("ReservationServlet: getCompositePetsQuery Entry");

		Map<String, String[]> map = req.getParameterMap();

		if (map != null) {
			List<Reservation> reservationList = reservationService.getResByCompositeQuery(map);
			req.setAttribute("reservationList", reservationList);
		}
		return "/petjsp/listAllres.jsp";
	}

	private String getAllReservations(HttpServletRequest req, HttpServletResponse res) {
		List<Reservation> reservationList = reservationService.getAll();
		req.setAttribute("reservationList", reservationList);

		return "/shelter/listAllReservation.jsp";
	}

	// 增加1215詩涵
	private String getReservationById(HttpServletRequest req, HttpServletResponse res, JsonNode jsonNode)
			throws ServletException, IOException {

		try {
			System.out.println("ReservationServlet: getPetById Entry");
			Integer Id = Integer.valueOf(req.getParameter("id"));
			Reservation reservation = reservationService.getResByResType(Id);
			req.setAttribute("reservation", reservation);
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
			res.setContentType("application/json;charset=UTF-8");
			res.getWriter().println(gson.toJson(reservation));
		} catch (Exception e) {
			res.resetBuffer();
			res.setContentType("application/json;charset=UTF-8");
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			res.getWriter().write(e.getMessage());
		}

		return "";
	}

	// 增加1215詩涵
	private String update(HttpServletRequest req, HttpServletResponse res) {

		System.out.println("ReservationServlet: update Entry");
		Integer Id = Integer.valueOf(req.getParameter("resId"));
		Reservation reservation = reservationService.getResByResType(Id);
		Integer resType = Integer.valueOf(req.getParameter("resType"));
		
		reservation.setResId(Id);
		reservation.setResTypeId(resType);
		reservationService.updateRes(reservation);

		req.setAttribute("reservation", reservation);
		if(reservation.getResTypeId()==5) {
			Integer resPetId = reservation.getPetId();
			Pet pet = petService.getOnePet(resPetId);

			User user = reservation.getUser();
			String resUser = user.getUserAcct();

			java.sql.Date resDate = reservation.getShelterBooking().getShelterBookingDate();
			pet.setAdoptDate(resDate);
			pet.setUserId(resUser);
			pet.setAdopted(true);
			petService.updatePet(pet);
			return "";
		}else if(reservation.getResTypeId()==6) {
			Integer resPetId = reservation.getPetId();
			Pet pet = petService.getOnePet(resPetId);
			pet.setAdopt(true);
			petService.updatePet(pet);
			return "";
		}
		
		
		else {
			return "/petjsp/pet_res.jsp";
		}
			
		
		

	}

	private String getAddReservation(HttpServletRequest req, HttpServletResponse res) {

		Integer shelterId = Integer.valueOf(req.getParameter("shelter"));
		ShelterService shelterService = new ShelterServiceImpl();
		Shelter shelter = shelterService.getShelterByShelterId(shelterId);
		Integer shelterBookingId = Integer.valueOf(req.getParameter("shelterBookingId"));
		Integer userId = Integer.valueOf(req.getParameter("userId"));
		Integer petId = Integer.valueOf(req.getParameter("petId"));

		Reservation reservation = new Reservation(shelter, shelterBookingId, userId, petId);

		reservation = reservationService.addRes(reservation);
		req.setAttribute("reservation", reservation);
		return "/shelter/reservation.do?action=getAll";

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

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

}
