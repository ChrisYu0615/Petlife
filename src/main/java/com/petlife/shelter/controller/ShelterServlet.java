package com.petlife.shelter.controller;

import java.io.IOException;
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

import com.google.protobuf.InvalidProtocolBufferException.InvalidWireTypeException;

import com.petlife.shelter.entity.Shelter;
import com.petlife.shelter.service.ShelterService;
import com.petlife.shelter.service.impl.ShelterServiceImpl;

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
		default:
			forwardPath = "/index.jsp";
		}

		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
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

	private String getUpdateShelter(HttpServletRequest req, HttpServletResponse res) {

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
		System.out.println("修改1");
		shelter = shelterService.updateShelter(shelter);
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

	// 地址轉經緯度
	private Map<String, String> addressToLatitudeAndLongitude(String address) {
		Map<String, String> location = new HashMap<>();
		return location;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

}
