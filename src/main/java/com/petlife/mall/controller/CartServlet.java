package com.petlife.mall.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.search.IntegerComparisonTerm;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.petlife.mall.dao.impl.CartDAOImpl;
import com.petlife.mall.dao.impl.CommDAOImpl;
import com.petlife.mall.entity.Cart;
import com.petlife.mall.entity.Comm;
import com.petlife.mall.service.CartService;
import com.petlife.mall.service.impl.CartServiceImpl;
import com.petlife.user.dao.impl.UserDAOImpl;
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
		// 編號1
		case "add_comm_to_cart":
			// 來自singleCommForUser.jsp
			// 來自listAllCommForUser.jsp
			forwardPath = addCommToCart(req, res);
			break;
		// 編號2
		case "delete_cart_item":
			// 來自cart.jsp的垃圾桶icon
			deleteCartItem(req, res);
			return; // 使用return, 因為ajax請求不需要跳轉
		default:
			forwardPath = "/index.jsp";
		}

		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
	}
	
	// 編號1. 新增comm到cart
	private String addCommToCart(HttpServletRequest req, HttpServletResponse res) {

		// 錯誤處理
		List<String> errorMsgs = new ArrayList<>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		try {
			Integer userId = Integer.parseInt(req.getParameter("userId"));
			Integer commId = Integer.parseInt(req.getParameter("commId"));
			Integer purchasingAmount = Integer.parseInt(req.getParameter("purchasing_amount"));
			
			// 查詢user之前是否有加入過此商品
			
			Cart cart = new Cart();
			
			// 創建 DAOImpl 實例.
			UserDAOImpl userDAOImpl2 = new UserDAOImpl();
			CommDAOImpl commDAOImpl = new CommDAOImpl();
			CartDAOImpl cartDAOImpl = new CartDAOImpl();
			
			if(cartDAOImpl.findCartByCommIdAndUserId(commId, userId) != null) {
				// user之前加入過相同的商品
				cart = cartDAOImpl.findCartByCommIdAndUserId(commId, userId);
				cart.setPurchasingAmount(purchasingAmount);
			} else {
				// 得到 User & Comm 的 Object.
				User user = userDAOImpl2.findByPK(userId);
				Comm comm = commDAOImpl.findByPk(commId);
				
		        cart.setUser(user);
		        cart.setComm(comm);
		        cart.setPurchasingAmount(purchasingAmount);
		        
		        Integer id = cartDAOImpl.add(cart);
		        System.out.println("已經新增cart_id: " + id);
			}
			
	        return "/cart/cart.jsp";
		} catch (Exception e) {
			errorMsgs.add("發生錯誤" + e.getMessage());
			e.printStackTrace();
			return "/comm_for_user/listAllCommForUser.jsp"; 
		}
	}
		
	// 編號2
	private void deleteCartItem(HttpServletRequest req, HttpServletResponse res) {
		JSONObject responseJson = new JSONObject();
        try {
            int cartId = Integer.parseInt(req.getParameter("cartId"));
            
            CartDAOImpl cartDAOImpl = new CartDAOImpl();
            cartDAOImpl.delete(cartId);
            
            responseJson.put("status", "success");
            responseJson.put("message", "購物車項目已成功刪除");;
        } catch (Exception e)  {
            e.printStackTrace();
            responseJson.put("status", "error");
            responseJson.put("message", "刪除過程中發生錯誤");
        }
        
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        try {
            res.getWriter().write(responseJson.toString());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}