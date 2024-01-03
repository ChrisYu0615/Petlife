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
			<img src="../assets/img/loader.gif" alt="img">
		</div>
	</div>

	<!-- Checkout Area -->
	<section id="checkout_main_area" class="section_padding">
		<div class="container">
			<div class="row">
				<div class="col-lg-8">
					<div class="shipping_addres_area_main">
						<div class="shipping_addres_main_form_area">
							<h2>Shipping address</h2>
							<div class="shipping_address_form">
								<c:if test="${not empty errorMessage}">
									<div class="alert alert-danger">${errorMessage}</div>
								</c:if>

								<form name="creditCardForm"
									action="<%=request.getContextPath()%>/comm_for_user/checkout.do"
									method="post" onsubmit="return validateForm()">
									<input type="hidden" name="buylistId" value=${ buylistId }>
									
									<div class="row">
										<div class="col-lg-3">
											<div class="form-group">
												<input name="creditCardPart1" value="${creditCardPart1}"
													pattern="\d{4}" maxlength="4" type="text"
													class="form-control" placeholder="信用卡號碼" required>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<input name="creditCardPart2" value="${creditCardPart2}"
													pattern="\d{4}" maxlength="4" type="text"
													class="form-control" placeholder="信用卡號碼" required>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<input name="creditCardPart3" value="${creditCardPart3}"
													pattern="\d{4}" maxlength="4" type="text"
													class="form-control" placeholder="信用卡號碼" required>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<input name="creditCardPart4" value="${creditCardPart4}"
													pattern="\d{4}" maxlength="4" type="text"
													class="form-control" placeholder="信用卡號碼" required>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<input value="${ creditCard.creditCardHolderName}"
													type="text" class="form-control"
													name="creditCardHolderName" placeholder="持卡人名稱" required>
											</div>
										</div>
										<div class="col-lg-6"></div>
										<div class="col-lg-4">
											<div class="form-group">
												<input value="${creditCardExpiryYear}" type="number"
													class="form-control" name="creditCardExpiryYear"
													placeholder="信用卡到期年" min="2020" max="2100" required>
											</div>
										</div>
										<div class="col-lg-4">
											<div class="form-group">
												<input value="${creditCardExpiryMonth}" type="number"
													class="form-control" name="creditCardExpiryMonth"
													placeholder="信用卡到期月" min="1" max="12" required>
											</div>
										</div>
										<div class="col-lg-4"></div>
										<div class="col-lg-4">
											<button type="submit" class="btn btn_theme btn_md">提交信用卡信息</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-4">
					<!-- grand total amount  -->
					<div class="cart_area_total_wrapper">
						<div class="cart_total_area bg_cart_item">
							<h4 id="grand_amount_display">
								Grand total: <span>$ ${ grandAmount}</span>
							</h4>
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
	<script>
		function validateForm() {
    		var year = document.forms["creditCardForm"]["creditCardExpiryYear"].value;
    		var month = document.forms["creditCardForm"]["creditCardExpiryMonth"].value;

		    if (year < 2020 || year > 2100) {
		        alert("年份必須在 2020 到 2100 之間");
		        return false;
		    }
		
		    if (month < 1 || month > 12) {
		        alert("月份必須在 1 到 12 之間");
		        return false;
		    }
		}
</script>
</body>
</html>