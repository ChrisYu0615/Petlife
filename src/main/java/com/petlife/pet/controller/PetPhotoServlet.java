package com.petlife.pet.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petlife.pet.entity.PetPhoto;
import com.petlife.pet.service.PetPhotoService;
import com.petlife.pet.serviceimpl.PetPhotoServiceImpl;


@WebServlet("/project/petphoto.do")
public class PetPhotoServlet extends HttpServlet {

	public PetPhotoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	private PetPhotoService petPhotoService;

	public void init() throws ServletException {
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
		case "getPetPhotoTest":
			forwardPath = getPetPhotoTest(req, res);
			break;
		default:
			forwardPath = "/index.jsp";
		}

		if (!forwardPath.isEmpty()) {
			RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
			dispatcher.forward(req, res);
		}

	}

	private String getPetPhotoTest(HttpServletRequest req, HttpServletResponse res) throws IOException {
		System.out.println("com");
			Integer id=(Integer.valueOf(req.getParameter("photoId")));
			
			System.out.println(id);
			PetPhoto petPhoto = petPhotoService.getOnePet(id);

//			// Check if the pet photo is not null
			if (petPhoto != null) {
			    res.setContentType("image/gif");
			    
			    // Assuming getPhotoData() returns a byte array representing the image data
			    byte[] imageData = petPhoto.getPetPhoto();

			    try (ServletOutputStream out = res.getOutputStream()) {
			        // Write the image data to the response output stream
			        out.write(imageData);
			    } catch (IOException e) {
			        e.printStackTrace(); // Handle the exception appropriately
			    }
			}
			return "";
//		} catch (Exception e) {
//			res.resetBuffer();
//			res.setContentType("application/json;charset=UTF-8");
//			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//			res.getWriter().write(e.getMessage());
//		}
//
//		return "";

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
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
}
