package com.petlife.mall.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petlife.mall.entity.Buylist;
import com.petlife.mall.entity.BuylistState;
import com.petlife.mall.entity.Cart;
import com.petlife.mall.service.BuylistService;
import com.petlife.mall.service.BuylistStateService;
import com.petlife.mall.service.CartService;
import com.petlife.mall.service.impl.BuylistServiceImpl;
import com.petlife.mall.service.impl.BuylistStateServiceImpl;
import com.petlife.mall.service.impl.CartServiceImpl;
import com.petlife.seller.entity.Seller;
import com.petlife.user.entity.User;

@WebServlet("/buylist_for_user/buylist_for_user.do")
@MultipartConfig
public class BuylistForUserServlet extends HttpServlet {
	// 一個 servlet 實體對應一個 service 實體
	private BuylistService buylistService;
	private CartService cartService;
	private BuylistStateService buylistStateService;
	
	@Override
	public void init() throws ServletException {
		
		buylistService = new BuylistServiceImpl();
		cartService = new CartServiceImpl();
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
		String action = req.getParameter("action");
		String forwardPath = "";



		switch (action) {
		
		case "insert":
			// 來自cart.jsp
			forwardPath = insert(req, res);
			break;
			
		case "getOne_For_Display":
			break;
		
		default:
			forwardPath = "/buylist/listAllBuylist.jsp";
			break;
		}
		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
	}
		
	private String insert(HttpServletRequest req, HttpServletResponse res) {
		User currentUser = (User) req.getSession().getAttribute("user");
	    if (currentUser == null) {
	        // 如果user沒有登的話(其實user不會沒有登)
	    	return "/cart/cart.jsp"; // 暫時先回cart.jsp, 之後應該是到錯誤頁面.
	    }
	    
        String[] cartIds = req.getParameterValues("cartIds");
        if (cartIds == null || cartIds.length == 0) {
            return "/cart/cart.jsp"; // 暫時先回cart.jsp, 之後應該是到結帳頁面.
        }

        List<Buylist> buylists = new ArrayList<>();
        for (String cartIdStr : cartIds) {
            try {
            	
        		Enumeration<String> parameterNames = req.getParameterNames();
        		while (parameterNames.hasMoreElements()) {
        		    String paramName = parameterNames.nextElement();
        		    System.out.println(paramName + ": " + req.getParameter(paramName));
        		}
        		
            	Integer cartId = Integer.parseInt(cartIdStr); // 就是只有cartId
            	
            	Cart cart = cartService.findByPK(cartId);
            	Seller seller = cart.getComm().getSeller();
            	String purchasingAmountStr = req.getParameter("purchasingAmount_" + cartId);
            	Integer purchasingAmount = Integer.parseInt(purchasingAmountStr);
            	BigDecimal commOnsalePrice = cart.getComm().getCommOnsalePrice();
            	BigDecimal totalAmount = commOnsalePrice.multiply(BigDecimal.valueOf(purchasingAmount));
            	          
            	// 訂單狀態預設給予0 來代表"待付款"
            	BuylistState buylistState = buylistStateService.getBuylistStateByBuylistStateId(0);
            	
                Buylist buylist = new Buylist();
                buylist.setUser(currentUser);
                buylist.setSeller(seller);
                buylist.setBuylistState(buylistState);

                buylist.setCoupon(null);
                buylist.setSellerRatingStars(0.0); // 評論評分為0.0
                buylist.setSellerEvaluateNarrative(""); // 評論內容先為空
                buylist.setSellerEvaluateTime(null); //評論時間先為空
                
                buylist.setBuylistAmount(totalAmount);
                buylist.setBuylistDate(new Timestamp(System.currentTimeMillis()));
                
                buylistService.addBuylist(buylist);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        try {
            buylistService.addMultipleBuylists(buylists);
            return "/comm_for_user/listAllCommForUser.jsp"; // 暫時先回cart.jsp, 之後應該是到結帳頁面.
        } catch (Exception e) {
            e.printStackTrace();
            return "/comm_for_user/listAllCommForUser.jsp"; // 暫時先回cart.jsp, 之後應該是到錯誤頁面.
        }
    }

}
