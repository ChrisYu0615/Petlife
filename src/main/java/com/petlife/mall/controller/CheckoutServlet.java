package com.petlife.mall.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petlife.mall.entity.Buylist;
import com.petlife.mall.service.BuylistService;
import com.petlife.mall.service.BuylistStateService;
import com.petlife.mall.service.impl.BuylistServiceImpl;
import com.petlife.mall.service.impl.BuylistStateServiceImpl;

@WebServlet("/comm_for_user/checkout.do")
@MultipartConfig
public class CheckoutServlet extends HttpServlet {
	// 一個 servlet 實體對應一個 service 實體
	private BuylistService buylistService;
	private BuylistStateService buylistStateService;

	@Override
	public void init() throws ServletException {
		buylistService = new BuylistServiceImpl();
		buylistStateService = new BuylistStateServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		
		String forwardPath = "";
		
		try {
			String buylistIdString = (String) req.getParameter("buylistId");
			Integer buylistId = Integer.parseInt(buylistIdString);
			Buylist buylist = buylistService.getBuylistByBuylistId(buylistId);
			
			String yearStr = req.getParameter("creditCardExpiryYear");
		    String monthStr = req.getParameter("creditCardExpiryMonth");
		    try {
		    	// validate
		        int year = Integer.parseInt(yearStr);
		        int month = Integer.parseInt(monthStr);

		        if (year < 2020 || year > 2100 || month < 1 || month > 12) {
		            req.setAttribute("errorMessage", "年份必須在 2020 到 2100 之間，月份必須在 1 到 12 之間。");
		            forwardPath = "/comm_for_user/checkout.jsp";
		        }

		        // 如果一切正常，繼續處理
		        buylist.setBuylistState(buylistStateService.getBuylistStateByBuylistStateId(1));
		        buylistService.updateBuylist(buylist);
		        forwardPath = "/cart/cart.jsp";

		    } catch (NumberFormatException e) {
		        req.setAttribute("errorMessage", "年份或月份格式不正確。");
		        forwardPath = "/comm_for_user/checkout.jsp";
		    }

			res.setContentType("text/html; charset=UTF-8");
			RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
			dispatcher.forward(req, res);
		} catch (Exception e) {
			res.sendRedirect(req.getContextPath() + "/index.jsp");
		}
	}
	

}