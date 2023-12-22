package com.petlife.mall.controller;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petlife.mall.dao.CommDAO;
import com.petlife.mall.dao.impl.CartDAOImpl;
import com.petlife.mall.dao.impl.CommDAOImpl;
import com.petlife.mall.entity.Cart;
import com.petlife.mall.entity.Comm;
import com.petlife.mall.entity.CommCat;
import com.petlife.mall.service.CartService;
import com.petlife.mall.service.impl.CartServiceImpl;
import com.petlife.mall.service.impl.CommServiceImpl;
import com.petlife.seller.entity.Seller;
import com.petlife.user.dao.UserDAO;
import com.petlife.user.dao.impl.UserDAOImpl2;
import com.petlife.user.entity.User;

@WebServlet("/cart/cart.do")
@MultipartConfig
public class CartServlet extends HttpServlet{
	// 一個 servlet 實體對應一個 service 實體
	private CartService cartService;

	@Override
	public void init() throws ServletException {
		cartService = new CartServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String forwardPath = "";

		switch (action) {
		case "add_comm_to_cart":
			// 來自singleCommDetail.jsp 還沒出現
			// 來自listAllCommForUser.jsp
			forwardPath = addCommToCart(req, res);
			break;
		default:
			forwardPath = "/comm/select_page.jsp";
		}

		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
	}
	
	// 1. 新增comm到cart
	private String addCommToCart(HttpServletRequest req, HttpServletResponse res) {
		// 錯誤處理
		List<String> errorMsgs = new ArrayList<>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		try {
			int userId = 100000001; // 暫時先用, 之後要從session裡面抓.
			int commId = Integer.parseInt(req.getParameter("commId"));
			int purchasingAmount = Integer.parseInt(req.getParameter("purchasing_amount"));
			
			Cart cart = new Cart();
			
			// 創建 DAOImpl 實例.
			UserDAOImpl2 userDAOImpl2 = new UserDAOImpl2();
			CommDAOImpl commDAOImpl = new CommDAOImpl();
			CartDAOImpl cartDAOImpl = new CartDAOImpl();
			
			// 得到 User & Comm 的 Object.
			User user = userDAOImpl2.findByPK(userId);
			Comm comm = commDAOImpl.findByPk(commId);
			
	        cart.setUser(user);
	        cart.setComm(comm);
	        cart.setPurchasingAmount(purchasingAmount);
	        
	        Integer id = cartDAOImpl.add(cart);
	        System.out.println("已經新增cart_id: " + id);
	        
	        return "/comm/listAllCommForUser.jsp"; // 暫時先回去listAllCommForUser.jsp
		} catch (Exception e) {
			errorMsgs.add("發生錯誤" + e.getMessage());
			e.printStackTrace();
			return "/comm/listAllCommForUser.jsp"; // 暫時先回去listAllCommForUser.jsp
		}
	}
}