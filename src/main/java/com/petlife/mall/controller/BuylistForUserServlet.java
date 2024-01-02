package com.petlife.mall.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petlife.admin.entity.Coupon;
import com.petlife.admin.service.CouponService;
import com.petlife.admin.service.impl.CouponServiceImpl;
import com.petlife.mall.entity.Buylist;
import com.petlife.mall.entity.BuylistDetails;
import com.petlife.mall.entity.BuylistState;
import com.petlife.mall.entity.Cart;
import com.petlife.mall.entity.Comm;
import com.petlife.mall.service.BuylistDetailsService;
import com.petlife.mall.service.BuylistService;
import com.petlife.mall.service.BuylistStateService;
import com.petlife.mall.service.CartService;
import com.petlife.mall.service.impl.BuylistDetailsServiceImpl;
import com.petlife.mall.service.impl.BuylistServiceImpl;
import com.petlife.mall.service.impl.BuylistStateServiceImpl;
import com.petlife.mall.service.impl.CartServiceImpl;
import com.petlife.seller.entity.Seller;
import com.petlife.seller.service.SellerService;
import com.petlife.seller.service.impl.SellerServiceImpl;
import com.petlife.user.entity.CreditCard;
import com.petlife.user.entity.User;
import com.petlife.user.service.CreditCardService;
import com.petlife.user.service.impl.CreditCardServiceImpl;

@WebServlet("/buylist_for_user/buylist_for_user.do")
@MultipartConfig
public class BuylistForUserServlet extends HttpServlet {
	// 一個 servlet 實體對應一個 service 實體
	private BuylistService buylistService;
	private CartService cartService;
	private BuylistStateService buylistStateService;
	private BuylistDetailsService buylistDetailsService;
	private SellerService sellerService;
	private CouponService couponService;
	private CreditCardService creditCardService;

	@Override
	public void init() throws ServletException {
		buylistService = new BuylistServiceImpl();
		cartService = new CartServiceImpl();
		buylistStateService = new BuylistStateServiceImpl();
		buylistDetailsService = new BuylistDetailsServiceImpl();
		sellerService = new SellerServiceImpl();
		couponService = new CouponServiceImpl();
		creditCardService = new CreditCardServiceImpl();

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
		default:
			forwardPath = "/comm_for_user/checkout.jsp";
			break;
		}
		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
	}

	private String insert(HttpServletRequest req, HttpServletResponse res) {
		User currentUser = (User) req.getSession().getAttribute("user");

		// 在迴圈之前都是在設置Buylist的資料.
		Buylist buylist = new Buylist();

		String[] cartIds = req.getParameterValues("cartIds");
		if (cartIds == null || cartIds.length == 0) {

			return "/cart/cart.jsp"; // 暫時先回cart.jsp, 之後應該是到結帳頁面.

		}

		String[] sellerIdsStrings = req.getParameterValues("sellerId");
		Integer sellerId = Integer.parseInt(sellerIdsStrings[0]);
		Seller seller = sellerService.getSellerBySellerId(sellerId);

		// 訂單狀態預設給予0 來代表"待付款"
		BuylistState buylistState = buylistStateService.getBuylistStateByBuylistStateId(0);

		String couponIdString = req.getParameter("couponId");

		if (couponIdString != null) {
			Integer couponId = Integer.parseInt(couponIdString);
			Coupon coupon = null;
			coupon = couponService.getCouponByCouponId(couponId);
			buylist.setCoupon(coupon);
		}

		String grandAmountString = req.getParameter("grandAmount");
		BigDecimal grandAmount = null;

		try {
			grandAmount = new BigDecimal(grandAmountString);
		} catch (NumberFormatException e) {
			grandAmount = BigDecimal.ZERO;
		}

		buylist.setUser(currentUser);
		buylist.setSeller(seller);
		buylist.setBuylistState(buylistState);
		buylist.setSellerRatingStars(0.0); // 評論評分為0.0
		buylist.setSellerEvaluateNarrative(""); // 評論內容先為空
		buylist.setSellerEvaluateTime(null); // 評論時間先為空

		buylist.setBuylistAmount(grandAmount);
		buylist.setBuylistDate(new Timestamp(System.currentTimeMillis()));

		buylistService.addBuylist(buylist); // buylist成立
		Integer buylistId = buylist.getBuylistId();

		CreditCard creditCard = creditCardService.findByUserId(currentUser.getUserId());

		List<Buylist> buylists = new ArrayList<>();
		for (String cartIdStr : cartIds) {
			try {
				// 準備建立buylist details
				Integer cartId = Integer.parseInt(cartIdStr); // 就是只有cartId
				Cart cart = cartService.findByPK(cartId);
				Comm comm = cart.getComm();
				BigDecimal commOnsalePrice = cart.getComm().getCommOnsalePrice();
				Integer purchasingAmount = cart.getPurchasingAmount();

				BuylistDetails buylistDetails = new BuylistDetails();
				buylistDetails.setBuylist(buylist);
				buylistDetails.setComm(comm);
				buylistDetails.setBuylistDetailsPrice(commOnsalePrice);
				buylistDetails.setBuylistDetailsPurchaseAmount(purchasingAmount);

				buylistDetailsService.addBuylistDetails(buylistDetails);
				cartService.delete(cartId);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}

		// 處理信用卡日期
		Date expirationDate = creditCard.getCreditCardExpirationDate();

		Calendar cal = Calendar.getInstance();
		cal.setTime(expirationDate);

		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		
		// 處理信用卡號碼
		String[] parts = creditCard.getCreditCardNumber().split("-");

		if (parts.length == 4) {
		    req.setAttribute("creditCardPart1", parts[0]);
		    req.setAttribute("creditCardPart2", parts[1]);
		    req.setAttribute("creditCardPart3", parts[2]);
		    req.setAttribute("creditCardPart4", parts[3]);
		}
		
		req.setAttribute("grandAmount", grandAmount);
		req.setAttribute("buylistId", buylistId);
		req.setAttribute("action", "checkout");
		req.setAttribute("creditCard", creditCard);
		req.setAttribute("creditCardExpiryYear", year);
		req.setAttribute("creditCardExpiryMonth", month);
		return "/comm_for_user/checkout.jsp";

		// insert寫法邏輯 => 先建立buylist再建立buylistDetails.
	}

}
