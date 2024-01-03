package com.petlife.mall.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petlife.mall.entity.Cart;
import com.petlife.mall.entity.Comm;
import com.petlife.mall.service.CartService;
import com.petlife.mall.service.CommService;
import com.petlife.mall.service.impl.CartServiceImpl;
import com.petlife.mall.service.impl.CommServiceImpl;

@WebServlet("/comm_for_user/singleCommForUser.do")
@MultipartConfig
public class SingleCommForUserServlet extends HttpServlet {
	// 一個 servlet 實體對應一個 service 實體
	private CommService commService;
	private CartService cartService;

	@Override
	public void init() throws ServletException {
		commService = new CommServiceImpl();
		cartService = new CartServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		try {
			String commIdString = req.getParameter("commId");
			Integer commId = Integer.parseInt(commIdString);
			String forwardPath = "";
			
			String action = req.getParameter("action");
			Comm comm = commService.findByPk(commId);
			
			switch (action) {
			case "show_comm_without_customer":
				// 來自 index.jsp & listAllCommForUser.jsp
				forwardPath = showCommWithoutCustomer(req, res, comm);
				break;
			case "show_comm_with_customer": 
				// 來自 cart.jsp
				forwardPath = showCommWithCustomer(req, res, comm);
				break;
			default:
				forwardPath = "/comm_for_user/listAllCommForUser.jsp";
			}
			
			res.setContentType("text/html; charset=UTF-8");
			RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
			dispatcher.forward(req, res);
		} catch (Exception e) {
			res.sendRedirect(req.getContextPath() + "/index.jsp");
		}
	}
	
	private String showCommWithoutCustomer(HttpServletRequest req, HttpServletResponse res, Comm comm) {
		req.setAttribute("comm", comm);
		commService.updateView(comm.getCommId());
		return "/comm_for_user/singleCommForUser.jsp";
	}
	
	private String showCommWithCustomer(HttpServletRequest req, HttpServletResponse res, Comm comm) {
		Cart cart = cartService.findByCommId(comm.getCommId());
		req.setAttribute("cart", cart);
		req.setAttribute("comm", comm);
		commService.updateView(comm.getCommId());
		return "/comm_for_user/singleCommForUser.jsp";
	}
}