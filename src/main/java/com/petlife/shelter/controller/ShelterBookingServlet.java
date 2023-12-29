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
import com.petlife.shelter.entity.ShelterBooking;
import com.petlife.shelter.service.ShelterBookingService;
import com.petlife.shelter.service.impl.ShelterBookingServiceImpl;



@WebServlet("/project/shelterbooking.do")
public class ShelterBookingServlet extends HttpServlet {
	private ShelterBookingService shelterBookingService;
	
	public void init() throws ServletException {
		shelterBookingService = new ShelterBookingServiceImpl();
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
		default:
			forwardPath = "/index.jsp";
		}
		if (!forwardPath.isEmpty()) {
			RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
			dispatcher.forward(req, res);
		}
	}
	
	private String getAvalibleBookings(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException  {
		System.out.println("ShelterBookingServlet1: getAvalibleBookings Entry");
		Map<String, String[]> map = req.getParameterMap();
		
		
		if (map != null) {
			List<ShelterBooking> shelterBookingList = shelterBookingService.getByCompositeQuery(map);
			shelterBookingList = shelterBookingList.stream()
							.filter(booking -> booking.getShelterBookingNum() != booking.getShelterBookingMax())
							.collect(Collectors.toList());
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
			res.setContentType("application/json;charset=UTF-8");
			res.getWriter().println(gson.toJson(shelterBookingList));
		} 
		return "";
	}

	private String getCompositePetsQuery(HttpServletRequest req, HttpServletResponse res) {
		
		System.out.println("ShelterBookingServlet: getCompositePetsQuery Entry");
		Map<String, String[]> map = req.getParameterMap();

		if (map != null) {
			List<ShelterBooking> shelterBookingList = shelterBookingService.getByCompositeQuery(map);
			req.setAttribute("shelterBookingList", shelterBookingList);
			if(shelterBookingList.size()!=0) {
			    return "/petjsp/listshelterbooking.jsp";
			}else if (shelterBookingList.size()==0) {
				  return "/petjsp/pet_autodate.jsp";
			}
		} 
		return "";
	}


	private String insert(HttpServletRequest req, HttpServletResponse res , JsonNode node)throws IOException {
		System.out.println("ShelterBookingServlet : insert Entry");
		
		 JsonNode shelterBookings = node.get("shelterBookings");
		 
       
         if (shelterBookings.isArray()) {
             for (JsonNode shelterBookingJson : shelterBookings) {
            	 ShelterBooking shelterBooking =new ShelterBooking();
            	 String date = shelterBookingJson.get("date").asText();
            	 String time=shelterBookingJson.get("time").asText();
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
		List<ShelterBooking>  shelterBookingList = shelterBookingService.getAll();
		ArrayList<ShelterBooking>  shelterBookingViewList = new ArrayList<>();
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
		for(ShelterBooking shelterBooking : shelterBookingList) {
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
