<%-- <%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="UTF-8"%> --%>

<%@page import="java.math.BigDecimal"%>

<%@page import="com.petlife.user.entity.User"%>

<%@page import="com.petlife.mall.entity.Comm"%>
<%@page import="com.petlife.mall.entity.Cart"%>
<%@page import="com.petlife.mall.entity.Buylist"%>

<%@page import="com.petlife.mall.service.impl.CommServiceImpl"%>
<%@page import="com.petlife.mall.service.impl.CartServiceImpl"%>
<%@page import="com.petlife.mall.service.impl.BuylistServiceImpl"%>

<%@page import="com.petlife.mall.service.CommService"%>
<%@page import="com.petlife.mall.service.CartService"%>
<%@page import="com.petlife.mall.service.BuylistService"%>



<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<!-- 執行常見web任務(e.g. for迴圈), 前綴詞=c -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 處理文本,日期等等, 前綴詞=fmt -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>

<!-- ================= -->
<!-- header -->
<%@ include file="../components/header.jsp"%>

<!DOCTYPE html>
<html lang="zh-TW">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>看看這個酷東西</title>

<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap css -->
<link rel="stylesheet" href="../assets/css/bootstrap.min.css">
<!-- animate css -->
<link rel="stylesheet" href="../assets/css/animate.min.css">
<!-- Fontawesome css -->
<link rel="stylesheet" href="../assets/css/fontawesome.all.min.css">
<!-- owl.carousel css -->
<link rel="stylesheet" href="../assets/css/owl.carousel.min.css">
<!-- owl.theme.default css -->
<link rel="stylesheet" href="../assets/css/owl.theme.default.min.css">
<!-- Magnific popup css -->
<link rel="stylesheet" href="../assets/css/magnific-popup.min.css">
<!-- Nouislider css -->
<link rel="stylesheet" href="../assets/css/nouislider.css">
<!-- navber css -->
<link rel="stylesheet" href="../assets/css/navber.css">
<!-- meanmenu css -->
<link rel="stylesheet" href="../assets/css/meanmenu.css">
<!-- Style css -->
<link rel="stylesheet" href="../assets/css/style.css">
<!-- Responsive css -->
<link rel="stylesheet" href="../assets/css/responsive.css">
<!-- Favicon -->
<link rel="icon" type="image/png" href="../assets/img/favicon.png">
</head>

<body class="hold-transition sidebar-mini layout-fixed">
	<!-- preloader Area -->
	<div id="preloader">
		<div id="status">
			<img src="assets/img/loader.gif" alt="img">
		</div>
	</div>

	<section id="shop_details_area" class="section_padding">
		<div class="container">
			<div class="row">
				<div class="col-lg-4">
					<div class="shop_details_slider_wrapper">
						<div class="slider slider-for">
							<div>
								<img src="<%=request.getContextPath()%>/comm/DBJPGReader?commId=${comm.commId}" alt="img">
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-8">
					<div class="shop_details_wrapper">
						<div class="shop_details_top_content">
							<h2>${comm.commName}</h2>
							<h3>${comm.commOnsalePrice}</h3>
							<p>${comm.commDesc}</p>
						</div>

						<div class="product_count_wrapper">
							<form action="<%=request.getContextPath()%>/cart/cart.do"
								method="post" class="product_count_form_one">
								<div class="shop_quent_wrapper">
									<div class="shop_quentiy_item_shows">
										
										<input type="number" name="purchasing_amount" value="1"
											min="1">
									</div>
								</div>
								<div class="shop_details_cart_submit_wrapper">
									<div class="product_cart_btn">
										<input type="submit" value="Add to cart" class="btn btn_theme btn_md">
										<input type="hidden" name="action" value="add_comm_to_cart"> 
										<input type="hidden" name="commId" value="${comm.commId}">
										<input type="hidden" name="userId" value="${user.userId}">
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>


	<!-- footer -->
	<%@ include file="../components/footer.jsp"%>
	<script src="../assets/js/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src="../assets/js/user_profile.js"></script>

</body>
</html>