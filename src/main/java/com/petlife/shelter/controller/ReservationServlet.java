package com.petlife.shelter.controller;

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
	
	// 還沒完成(2023/12/13)
	private String getCompositeReservationQuery(HttpServletRequest req, HttpServletResponse res) {

		Map<String, String[]> map = req.getParameterMap();
				
		if (map != null) {
			List<Reservation> reservationList = reservationService.getResByCompositeQuery(map);
			System.out.println("複合查詢Controller");
			req.setAttribute("reservationList", reservationList);
		} else {
			System.out.println("查詢空值列全部");
			return"/index.jsp";
		}
		return "/shelter/listCompositeQueryReservation.jsp";
	}

	private String getAllReservations(HttpServletRequest req, HttpServletResponse res) {
		List<Reservation> reservationList = reservationService.getAll();
		req.setAttribute("reservationList", reservationList);
		
		return "/shelter/listAllReservation.jsp";
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
	



		
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	
}
