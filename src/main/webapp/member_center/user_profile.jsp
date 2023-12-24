<%@page import="com.petlife.user.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="zh-TW">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
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
<!-- My Css -->
<link rel="stylesheet" href="../assets/css/user_data.css">
<title>會員中心</title>

</head>

<body>
	<!-- preloader Area -->
	<div id="preloader">
		<div id="status">
			<img src="../assets/img/loader.gif" alt="img">
		</div>
	</div>

	<%@include file="../components/header.jsp"%>

	<!--Our Shop-->
	<section id="our_shop_main" class="section_padding">
		<div class="container">
			<div class="row">
				<!-- side bar -->
				<div class="col-12 col-md-3">
					<div class="sidebar_boxed_wrapper">
						<div class="sidebar_common_heading">
							<h3 id="sidebar_title">
								<a href="<%=request.getContextPath()%>/member_center/user_profile.jsp">會員中心</a>
							</h3>
						</div>

						<!-- accordion -->
						<div class="accordion" id="accordionExample">
							<!-- 修改會員資料 -->
							<div class="accordion-item">
								<h2 class="accordion-header" id="headingOne">
									<div class="row sidebar_select" id="user_profile">
										<a
											href="<%=request.getContextPath()%>/member_center/change_user_profile.jsp">修改會員資料</a>
									</div>
								</h2>
							</div>

							<!-- 訂單管理 -->
							<div class="accordion-item">
								<h2 class="accordion-header" id="headingTwo">
									<div class="row sidebar_select" id="orderList_manage">
										<a href="<%=request.getContextPath()%>/buylist/buylist.do?action=getBuyListByMemberId&memberId=<%=user.getUserId()%>">訂單管理</a>
									</div>
							</div>

							<!-- 預約管理 -->
							<div class="accordion-item">
								<h2 class="accordion-header" id="headingThree">
									<div class="row sidebar_select" id="order_manage">
                                        <a href="<%=request.getContextPath()%>/shelter/reservation.do?action=getByUserId&memberId=<%=user.getUserId()%>">預約管理</a>
									</div>
								</h2>
							</div>

							<!-- 文章管理 -->
							<div class="accordion-item">
								<h2 class="accordion-header" id="headingFour">
									<div class="row sidebar_select" id="article_manage">
										<a
											href="<%=request.getContextPath()%>/art/art.do?action=getAllArticles&userId=<%=user.getUserId()%>">文章管理</a>
									</div>
								</h2>
							</div>
						</div>
					</div>
				</div>


				<div class="col-12 col-md-9">
					<div class="main_area_wrapper">
						<div class="main_area_heading">
							<h3 class="main_header border rounded bg-warning"
								id="mamber_center_title">會員中心</h3>
						</div>

						<div class="main_content_wrapper" id="main_content">
							<div class="row">
								<!-- 一個 class="col-lg-4 col-md-6 col-sm-12 col-12" 就是一個comm_item-->
								<div>
									<div class="main_item border rounded bg-light">

										<div class="row">
											<div class="main_form col-5">
												<div class="row" style="text-align: center;">
													<div id="preview">
														<span class="text"><img
															src="<%=request.getContextPath()%>/user/user.do?action=getUserHeadshot&userId=<%=user.getUserId()%>"
															alt="" class="preview_img rounded-circle"></span>
													</div>
												</div>
											</div>

											<div class="main_form col-7">

												<table id="user_profile">
													<tr>
														<th class="profile_title">會員編號</th>
														<td class="profile_content"><%=user.getUserId()%>
														</td>
													</tr>												
													<tr>
														<th class="profile_title">會員帳號</th>
														<td class="profile_content"><%=user.getUserAcct()%>
														</td>
													</tr>
													<tr>
														<th class="profile_title">姓名</th>
														<td class="profile_content"><%=user.getUserName()%>
														</td>
													</tr>
													<tr>
														<th class="profile_title">暱稱</th>
														<td class="profile_content"><%=user.getUserNickName()%>
														</td>
													</tr>
													<tr>
														<th class="profile_title">性別</th>
														<td class="profile_content"><%=user.getGender() == true ? "女" : "男"%>
														</td>
													</tr>
													<tr>
														<th class="profile_title">出生年月日</th>
														<td class="profile_content"><fmt:formatDate
																pattern="yyyy-MM-dd" value="<%=user.getBirthday()%>" />
														</td>
													</tr>
													<tr>
														<th class="profile_title">手機號碼</th>
														<td class="profile_content"><%=user.getPhoneNum()%>
														</td>
													</tr>
													<tr>
														<th class="profile_title">地址</th>
														<td class="profile_content"><%=user.getAddress()%>
														</td>
													</tr>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</section>

	<%@include file="../components/footer.jsp"%>

	<script src="../assets/js/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src="../assets/js/user_profile.js"></script>
</body>

</html>