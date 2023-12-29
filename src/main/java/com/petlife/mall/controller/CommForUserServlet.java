package com.petlife.mall.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
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

import com.petlife.mall.dao.CommDAO;
import com.petlife.mall.dao.impl.CommDAOImpl;
import com.petlife.mall.entity.Buylist;
import com.petlife.mall.entity.Comm;
import com.petlife.mall.entity.CommCat;
import com.petlife.mall.service.CommService;
import com.petlife.mall.service.impl.CommServiceImpl;
import com.petlife.seller.entity.Seller;
import com.petlife.user.entity.User;

@WebServlet("/comm_for_user/listAllCommForUser.do")
@MultipartConfig
public class CommForUserServlet extends HttpServlet {
	// 一個 servlet 實體對應一個 service 實體
	private CommService commService;

	@Override
	public void init() throws ServletException {
		commService = new CommServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String searchQuery = req.getParameter("searchQuery");
		String forwardPath = "";
		
		Map<String, Integer> actionMap = new HashMap<>();
		actionMap.put("1000", 1000);
		actionMap.put("1001", 1001);
		actionMap.put("1002", 1002);
		actionMap.put("1003", 1003);
		actionMap.put("1004", 1004);
		actionMap.put("2000", 2000);
		actionMap.put("2001", 2001);
		actionMap.put("2002", 2002);
		actionMap.put("2003", 2003);
		actionMap.put("2004", 2004);
		
		List<Comm> list = null;
		
		if(action != null && !action.isEmpty()) {
			list = commService.getCommByCategoryId(actionMap.get(action));
		} else if(searchQuery != null && !searchQuery.isEmpty()){
			list = commService.getCommBySearchQuery(searchQuery);
		} else {
			list = commService.getAll();
		}
		
		req.setAttribute("list", list);
		
		forwardPath = "/comm_for_user/listAllCommForUser.jsp";
		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
	}
}