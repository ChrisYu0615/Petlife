package com.petlife.admin.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.petlife.admin.entity.Advertisement;
import com.petlife.admin.service.AdvertisementService;
import com.petlife.admin.service.impl.AdvertisementServiceImpl;

@WebServlet("/advertisement/advertisement.do")
@MultipartConfig
public class AdvertisementServlet extends HttpServlet {
	private AdvertisementService advertisementService;

	@Override
	public void init() throws ServletException {
		advertisementService = new AdvertisementServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		String forwardPath = "";
		switch (action) {
		case "addAdvertisement":
			forwardPath = addAdvertisement(req, resp);
			break;
		case "getAllAdvertisements":
			forwardPath = getAllAdvertisements(req, resp);
			break;
		case "getOne":
			getOneAdvertisement(req, resp);
			break;
		case "updateAdvertisement":
			forwardPath = updateAdvertisement(req, resp);
			break;
		case "getAdImg":
			getAdImg(req, resp);
			break;
		default:
			forwardPath = "";
			break;
		}

		if (!forwardPath.isEmpty()) {
			RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
			dispatcher.forward(req, resp);
		}

	}

	private String updateAdvertisement(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		Integer advertisementId = Integer.valueOf(req.getParameter("advertisement_Id"));
		String adTitle = req.getParameter("advertisement_name");
		String adContent = req.getParameter("advertisement_content");
		Part adImgPart = req.getPart("advertisement_img");
		Boolean adStatus = Boolean.valueOf(req.getParameter("advertisement_status"));
		Date adStartDate = java.sql.Date.valueOf(req.getParameter("advertisement_stardate"));
		Date adEndDate = java.sql.Date.valueOf(req.getParameter("advertisement_enddate"));

		Advertisement advertisement = advertisementService.getAdvertisementById(advertisementId);
		advertisement.setAdvertisementTitle(adTitle);
		advertisement.setAdvertisementContent(adContent);
		advertisement.setAdStatus(adStatus);
		advertisement.setStartDate(adStartDate);
		advertisement.setEndDate(adEndDate);

		if (adImgPart != null && adImgPart.getSize() > 0) {
			byte[] adImgBytes = getImgBytes(adImgPart);
			advertisement.setAdvertisementImg(adImgBytes);
		}

		advertisementService.updateAdvertisement(advertisement);

		return "/advertisement/advertisement.do?action=getAllAdvertisements";
	}

	private void getOneAdvertisement(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Map<String, Object> advertisementData = new HashMap<>();
		Integer advertisementId = Integer.valueOf(req.getParameter("advertisementId"));
		Advertisement advertisement = advertisementService.getAdvertisementById(advertisementId);
		String advertisementImgBase64 = Base64.getEncoder().encodeToString(advertisement.getAdvertisementImg());

		advertisementData.put("ad", advertisement);
		advertisementData.put("adImg", advertisementImgBase64);

		resp.setContentType("application/json; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		Gson gson = new GsonBuilder().setPrettyPrinting().setDateFormat("yyyy-MM-dd").create();
		String advertisementJson = gson.toJson(advertisementData);
		out.print(advertisementJson);
	}

	private String addAdvertisement(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		String adTitle = req.getParameter("advertisement_name");
		String adContent = req.getParameter("advertisement_content");
		Part adImgPart = req.getPart("advertisement_img");
		byte[] adImgBytes = getImgBytes(adImgPart);
		Date adStartDate = java.sql.Date.valueOf(req.getParameter("advertisement_stardate"));
		Date adEndDate = java.sql.Date.valueOf(req.getParameter("advertisement_enddate"));

		Advertisement advertisement = new Advertisement(adTitle, adImgBytes, adContent, adStartDate, adEndDate);
		advertisementService.addAdvertisement(advertisement);

		return "/advertisement/advertisement.do?action=getAllAdvertisements";
	}

	private String getAllAdvertisements(HttpServletRequest req, HttpServletResponse resp) {
		List<Advertisement> advertisements = advertisementService.getAll();
		req.setAttribute("getAllAdvertisements", advertisements);
		return "/admin/advertisement_management.jsp";
	}

	private void getAdImg(HttpServletRequest req, HttpServletResponse resp) {
		Integer advertisementId = Integer.valueOf(req.getParameter("adId"));
		Advertisement advertisement = advertisementService.getAdvertisementById(advertisementId);
		byte[] adImg = advertisement.getAdvertisementImg();
		System.out.println(adImg);
		resp.setContentType("image/png");
		
		try {
			ServletOutputStream out = resp.getOutputStream();
			out.write(adImg);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private byte[] getImgBytes(Part part) {
		InputStream in = null;
		byte[] buf = null;
		try {
			in = part.getInputStream();
			if (in != null && in.available() > 0) {
				buf = in.readAllBytes();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return buf;
	}

}
