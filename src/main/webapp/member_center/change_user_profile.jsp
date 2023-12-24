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
<link rel="stylesheet" href="../assets/css/user_data.css">
<title>修改會員資料</title>

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
								id="mamber_center_title">修改會員資料</h3>
						</div>

						<div class="main_content_wrapper" id="main_content">
							<div class="row">
								<!-- 一個 class="col-lg-4 col-md-6 col-sm-12 col-12" 就是一個comm_item-->
								<div>
									<div class="main_item border rounded bg-light">

										<form action="/Petlife/user/user.do" method="post" enctype="multipart/form-data"
											id="regist_form">
											<div class="card-body">

												<div class="form-group col-md-6 mb-3">
													<label for="idcard-front" class="form-label">會員大頭照
														<span class="must_insert">*</span><br> <span
														class="verify_result" id="verify_idcard-front"></span>
													</label> <input type="file" class="form-control-sm" id="headshot"
														name="headshot" accept="image/*"> <img
														class="preview_img rounded-circle" id="headshot-preview"
														src="<%=request.getContextPath()%>/user/user.do?action=getUserHeadshot&userId=<%=user.getUserId()%>"
														alt="" style="width: 180px; height: 180px;">
												</div>

												<hr>

												<div class="form-group mb-3">
													<label for="useraccount" class="form-label">會員帳號(Email)
														<span class="must_insert">*</span>
													</label> <input type="email" class="form-control" id="useraccount"
														name="useraccount" value="<%=user.getUserAcct()%>"
														placeholder="請輸入Email" disabled> <span
														class="verify_result" id="verify_useraccount"></span>
												</div>

												<div class="form-group mb-3">
													<label for="password" class="form-label">修改密碼 </label> <input
														type="password" class="form-control" id="password"
														name="password" placeholder="請輸入欲修改的密碼"> <span
														class="verify_result" id="verify_password"></span>
												</div>

												<div class="form-check mb-3">
													<input class="form-check-input" type="checkbox"
														id="showPassword"> <label class="form-check-label"
														for="showPassword"> 顯示密碼 </label>
												</div>

												<div class="form-group mb-3">
													<label for="username" class="form-label">姓名</label> <span
														class="must_insert">*</span> <input type="text"
														class="form-control" id="username" name="username"
														placeholder="請輸入姓名" value="<%=user.getUserName()%>">
													<span class="verify_result" id="verify_username"></span>
												</div>

												<div class="form-group mb-3">
													<label for="nickname" class="form-label">暱稱</label> <input
														type="text" class="form-control" id="nickname"
														name="nickname" placeholder="請輸入暱稱"
														value="<%=user.getUserNickName()%>"> <span
														class="verify_result" id="verify_nickname"></span>
												</div>

												<div class="form-group mb-3">
													<label for="" class="form-label">性別 <span
														class="must_insert">*</span>
													</label><br>

													<div class="form-check form-check-inline">
														<input class="form-check-input" type="radio" name="gender"
															id="male" value="false"> <label
															class="form-check-label" for="male">男</label>
													</div>

													<div class="form-check form-check-inline">
														<input class="form-check-input" type="radio" name="gender"
															id="female" value="true"> <label
															class="form-check-label" for="female">女</label>
													</div>
													<div>
														<span class="verify_result" id="verify_gender"></span> <input
															type="hidden" id="checkGender" value="<%=user.getGender()%>">
													</div>
												</div>


												<div class="form-group mb-3">
													<label for="birthdate" class="form-label">出生年月日 <span
														class="must_insert">*</span>
													</label>
													<div class="input-group">
														<input type="date" class="form-control" id="birthdate"
															name="birthdate" placeholder="選擇出生日期"
															value="<%=user.getBirthday()%>">
													</div>
													<span class="verify_result" id="verify_birthdate"></span>
												</div>

												<div class="form-group mb-3">
													<label for="phone" class="form-label">手機號碼 <span
														class="must_insert">*</span>
													</label> <input type="tel" class="form-control" id="phone"
														name="phone" placeholder="請輸入手機號碼"
														value="<%=user.getPhoneNum()%>"> <span
														class="verify_result" id="verify_phone"></span>
												</div>

												<div class="row">
													<div class="form-group col-auto">
														<label for="country" class="form-label">縣市 <span
															class="must_insert">*</span>
														</label> <br> <select class="form-select" id="country"
															name="country">
														</select>
													</div>
													<div class="form-group col-auto offset-1">
														<label for="district" class="form-label">行政區 <span
															class="must_insert">*</span>
														</label> <br> <select class="form-select" id="district"
															name="district"></select>
													</div>
												</div>
												<div class="mb-3">
													<span class="verify_result" id="verify_country"></span> <br>
													<span class="verify_result" id="verify_district"></span>
												</div>

												<div class="form-group mb-3">
													<label for="address" class="form-label">地址 <span
														class="must_insert">*</span>
													</label> <input type="text" class="form-control" id="address"
														name="address" placeholder="請輸入詳細地址"
														value="<%=user.getAddress()%>"> <span
														class="verify_result" id="verify_address"></span>
												</div>
											</div>

											<!-- /.card-body -->

											<div class="row card-footer">
												<div class="col"></div>
												<div class="col-auto">
													<button type="submit" class="btn btn-primary"
														id="btn_regist">儲存</button>
												</div>
												<div class="col-auto offset-1">
													<button type="button" class="btn btn-warning"
														id="btn_cancel">取消</button>
												</div>
												<div class="col"></div>
												<input type="hidden" name="action" value="updateUserProfile">
												<input type="hidden" name="userId"
													value="<%=user.getUserId()%>">
											</div>
										</form>
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
		src="https://cdn.jsdelivr.net/npm/tw-city-selector@2.1.1/dist/tw-city-selector.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
	<script src="../assets/js/change_user_profile.js">
		
	</script>
</body>

</html>