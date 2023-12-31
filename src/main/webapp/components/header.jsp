<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.petlife.user.entity.User"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
User user = (User) session.getAttribute("user");
%>

<!DOCTYPE html>
<html lang="zh-TW">

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Title -->
<title>寵愛生活Petlife</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/header.css">
</head>

<body>
	<!-- Header Area -->
	<header class="main_header_arae">
		<!-- Navbar Bar -->
		<div class="navbar-area">
			<div class="main-responsive-nav">
				<div class="container">
					<div class="main-responsive-menu">
						<div class="logo">
							<img src="<%=request.getContextPath()%>/assets/img/favicon.png"
								alt="logo"> <a
								href="${pageContext.request.contextPath}/index.jsp"> </a>
						</div>
					</div>
				</div>
			</div>
			<div class="main-navbar" style="background-color: #FFD966;">
				<div class="container">
					<div class="row">
						<nav class="navbar navbar-expand-md">
							<a class="navbar-brand col-2"
								href="${pageContext.request.contextPath}/index.jsp"> <img
								src="<%=request.getContextPath()%>/assets/img/main_logo.png"
								alt="logo">
							</a>
							<div class="collapse navbar-collapse mean-menu"
								id="navbarSupportedContent">
								<ul class="navbar-nav">

									<li class="nav-item"><a
										href="${pageContext.request.contextPath}/index.jsp"
										class="nav-link active">首頁</a></li>

									<li class="nav-item"><a
										href="${pageContext.request.contextPath}/comm_for_user/listAllCommForUser.do"
										class="nav-link"> 寵物商城 </a></li>

									<li class="nav-item"><a href="<%=request.getContextPath()%>/shelter/adoption.jsp" class="nav-link">
											寵物領養 <i class="fas fa-angle-down"></i>
									</a>
										</li>

									<li class="nav-item"><a
										href="<%=request.getContextPath()%>/article/Articleindex.jsp"
										class="nav-link"> 寵物論壇 <i class="fas fa-angle-down"></i>
									</a>
										<ul class="dropdown-menu" style="left: -50px;">
											<li class="nav-item"><a
												href="<%=request.getContextPath()%>/art/art.do?action=getArticlesByForumId&forumId=1"
												class="nav-link">狗狗的家</a></li>
											<li class="nav-item"><a
												href="<%=request.getContextPath()%>/art/art.do?action=getArticlesByForumId&forumId=2"
												class="nav-link">貓貓的家</a></li>
											<li class="nav-item"><a
												href="<%=request.getContextPath()%>/art/art.do?action=getArticlesByForumId&forumId=3"
												class="nav-link">來閒聊ㄅ</a></li>
											<li class="nav-item"><a
												href="<%=request.getContextPath()%>/art/art.do?action=getArticlesByForumId&forumId=4"
												class="nav-link">特殊寵物</a></li>
										</ul></li>
								</ul>

								<div class="others-options d-flex align-items-center">
									<div class="option-item" style="left: -50px;">
										<a href="<%=request.getContextPath()%>/cart/cart.jsp"
											class="nav-link shopping-cart"> <i
											class="fas fa-shopping-cart">購物車</i>
										</a>
									</div>
									<div class="option-item" style="left: -50px;">
										<ul class="navbar-nav" style="padding-left: 30px;">
											<c:choose>
												<c:when test="${user eq null}">
													<li class="nav-item"><a href="#" class="nav-link">
															<i class="fas fa-user" style="font-size: 15px;">註冊/登入</i>
													</a> <!-------!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-->
														<ul class="dropdown-menu" style="left: -50px;">
															<li class="nav-item"><a
																href="<%=request.getContextPath()%>/login/member_login.jsp"
																class="nav-link">一般會員登入</a></li>
															<li class="nav-item"><a
																href="<%=request.getContextPath()%>/login/backend_login.jsp"
																class="nav-link">後臺管理登入</a></li>
														</ul></li>
												</c:when>

												<c:otherwise>
													<li class="nav-item"><a href="#" class="nav-link">
															<img
															src="<%=request.getContextPath()%>/user/user.do?action=getUserHeadshot&userId=<%=user.getUserId()%>"
															alt="" class="preview_img shadow rounded-circle"
															style="width: 35px; height: 35px; background-color: white;">
															<span style="font-size: 15px;"><%=user.getUserName()%></span>
															<i class="fas fa-angle-down"></i>
													</a> <!-------!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-->
														<ul class="dropdown-menu" style="left: -50px;">
															<li class="nav-item"><a
																href="<%=request.getContextPath()%>/member_center/user_profile.jsp"
																class="nav-link">查看個人資料</a></li>
															<li class="nav-item"><a
																href="<%=request.getContextPath()%>/buylist/buylist.do?action=getBuyListByMemberId&memberId=<%=user.getUserId()%>"
																class="nav-link">訂單管理</a></li>
															<li class="nav-item"><a
																href="<%=request.getContextPath()%>/shelter/reservation.do?action=getByUserId&memberId=<%=user.getUserId()%>"
																class="nav-link">預約管理</a></li>
															<li class="nav-item"><a
																href="<%=request.getContextPath()%>/art/art.do?action=getAllArticles&userId=<%=user.getUserId()%>"
																class="nav-link">文章管理</a></li>
															<li class="nav-item"><a
																href="<%=request.getContextPath()%>/logout/logout.do"
																class="nav-link">登出</a></li>
														</ul></li>
												</c:otherwise>
											</c:choose>
										</ul>
									</div>
								</div>
							</div>
						</nav>
					</div>
				</div>
			</div>
		</div>
	</header>

	<script src="<%=request.getContextPath()%>/assets/js/jquery.min.js"></script>
	<!-- Bootstrap js -->
	<script
		src="<%=request.getContextPath()%>/assets/js/bootstrap.bundle.js"></script>
	<!-- Meanu js -->
	<script
		src="<%=request.getContextPath()%>/assets/js/jquery.meanmenu.js"></script>
	<!-- Magnific Popup js -->
	<script
		src="<%=request.getContextPath()%>/assets/js/jquery.magnific-popup.min.js"></script>
	<!-- owl carousel js -->
	<script
		src="<%=request.getContextPath()%>/assets/js/owl.carousel.min.js"></script>
	<!-- wow.js -->
	<script src="<%=request.getContextPath()%>/assets/js/wow.min.js"></script>
	<!-- waypoints.js -->
	<script src="<%=request.getContextPath()%>/assets/js/waypoints.min.js"></script>
	<!-- counterup.js -->
	<script
		src="<%=request.getContextPath()%>/assets/js/jquery.counterup.min.js"></script>
	<!-- Custom js -->
	<!-- 	<script src="../assets/js/gallery-popup.js"></script> -->
	<script src="<%=request.getContextPath()%>/assets/js/custom.js"></script>
	<%-- <script src="assets/js/video.js"></script> --%>
</body>

</html>