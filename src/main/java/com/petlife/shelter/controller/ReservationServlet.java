package com.petlife.shelter.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.petlife.admin.entity.Member;
import com.petlife.shelter.dao.ResTypeDAO;
import com.petlife.shelter.dao.impl.ResTypeDAOImpl;
import com.petlife.shelter.entity.ResType;
import com.petlife.shelter.entity.Reservation;
import com.petlife.shelter.entity.Shelter;
import com.petlife.shelter.service.ReservationService;
import com.petlife.shelter.service.impl.ReservationServiceImpl;
import com.petlife.shelter.service.ShelterService;
import com.petlife.shelter.service.impl.ShelterServiceImpl;

@WebServlet("/shelter/reservation.do")
@MultipartConfig
public class ReservationServlet extends HttpServlet {

	private ReservationService reservationService;

	@Override
	public void init() throws ServletException {
		reservationService = new ReservationServiceImpl();
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
		System.out.println(action);
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
		case "cancelReservation":
			forwardPath = cancelReservation(req, res);
			break;
//			case "getOneToUpdate":
//				forwardPath = getOneToUpdateShelter(req,res);
//				break;
//			case "update":
//				forwardPath = getUpdateShelter(req, res);
//				break;
		default:
			forwardPath = "/index.jsp";
		}

		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
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

	// 更新過1215詩涵
	private String getCompositeReservationQuery(HttpServletRequest req, HttpServletResponse res) {

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
		System.out.println(Id);

		Reservation reservation = reservationService.getResByResType(Id);

		Integer resType = Integer.valueOf(req.getParameter("resType"));
		reservation.setResId(Id);
		reservation.setResTypeId(resType);
		System.out.println(resType);
		reservationService.updateRes(reservation);
		req.setAttribute("reservation", reservation);
		return "/petjsp/pet_res.jsp";

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
