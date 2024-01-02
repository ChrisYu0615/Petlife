<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../components/header.jsp"%>
<%@ page import="com.petlife.user.entity.User"%>
<%@ page import="java.util.List"%>
<%@ page import="com.petlife.pet.entity.Pet"%>
<%@ page import="com.petlife.pet.entity.PetVariety"%>
<%
Integer id = null;
User user1 = (User) session.getAttribute("user");
if (user1 != null) {
	id = user1.getUserId();
	System.out.println(id);
	request.setAttribute("id", id);
}
%>

<!DOCTYPE html>
<html lang="zxx">

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Title -->
<title>寵愛生活Petlife</title>
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
<!-- navber css -->
<link rel="stylesheet" href="../assets/css/navber.css">
<!-- meanmenu css -->
<link rel="stylesheet" href="../assets/css/meanmenu.css">
<!-- Style css -->
<link rel="stylesheet" href="../assets/css/style.css">
<!-- Responsive css -->
<link rel="stylesheet" href="../assets/css/responsive.css">
<!-- Adoption css -->
<link rel="stylesheet" href="../assets/css/adoption.css">
<link rel="stylesheet" href="../assets/css/index.css">
<link href="../assets/css/glDatePicker.default2.css" rel="stylesheet"
	type="text/css">
<!-- 此版自定 -->
<link href="../assets/css/glDatePicker.darkneon1.css" rel="stylesheet"
	type="text/css">
<!-- 原版預設 -->
<link href="../assets/css/glDatePicker.flatwhite1.css" rel="stylesheet"
	type="text/css">

<!-- 此版自定 -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="../assets/js/jquery.min.js"></script>
<!-- Bootstrap js -->
<script src="../assets/js/bootstrap.bundle.js"></script>
<!-- Meanu js -->
<script src="../assets/js/jquery.meanmenu.js"></script>
<!-- Magnific Popup js -->
<script src="../assets/js/jquery.magnific-popup.min.js"></script>
<!-- owl carousel js -->
<script src="../assets/js/owl.carousel.min.js"></script>
<!-- wow.js -->
<script src="../assets/js/wow.min.js"></script>
<!-- waypoints.js -->
<script src="../assets/js/waypoints.min.js"></script>
<!-- counterup.js -->
<script src="../assets/js/jquery.counterup.min.js"></script>
<!-- Custom js -->
<script src="../assets/js/gallery-popup.js"></script>
<script src="../assets/js/custom.js"></script>
<script src="../assets/js/video.js"></script>
<!-- 原版預設 -->
<script src="../assets/js/jquery.min.js"></script>
<!-- <script src="../assets/js/glDatePicker2.js"></script> -->
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/gh/glad/glDatePicker/glDatePicker.min.js"></script>
<script src="../assets/js/adoption.js"></script>

<!-- lightbox 日期 -->
<!--
 <link href="https://cdn.jsdelivr.net/npm/air-datepicker@3.3.5/air-datepicker.min.css" rel="stylesheet"> -->
</head>
<style>
a:visited, a:link {
	color: inherit;
	text-decoration: none;
}

a:hover, a:active {
	color: blue;
	text-decoration: none;
}

.on {
	background-color: LimeGreen;
	pointer-events: visibleFill;
}
</style>
<body>

	<!-- preloader Area -->
	<div id="preloader">
		<div id="status">
			<img src="../assets/img/loader.gif" alt="img">
		</div>
	</div>


	<!-- Header Area -->


	<!-- search -->
	<div class="search-overlay">
		<div class="d-table">
			<div class="d-table-cell">
				<div class="search-overlay-layer"></div>
				<div class="search-overlay-layer"></div>
				<div class="search-overlay-layer"></div>
				<div class="search-overlay-close">
					<span class="search-overlay-close-line"></span> <span
						class="search-overlay-close-line"></span>
				</div>
				<div class="search-overlay-form">
					<form>
						<input type="text" class="input-search"
							placeholder="Search here...">
						<button type="button">
							<i class="fas fa-search"></i>
						</button>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- Adoption Details Area -->
	<section id="adoption_details_wrapper"
		class="section_padding slider_side_btn">

		<div class="adoption_details_content">
			<div class="heading_border_bottom">
				<div class="pet_detail">動物資訊</div>
			</div>
			<div class="row gx-0">
				<div class="col-lg-4">
					<!-- 在這裡嵌入輪播元素 -->
					<div id="carouselExampleSlidesOnly" class="carousel slide"
						data-bs-ride="carousel" data-interval="1">
						<div class="carousel-inner">
							<c:forEach var="photo" items="${pet.petPhotos}" varStatus="loop">
								<div class="carousel-item ${loop.first ? 'active' : ''}">
									<img
										src="<%=request.getContextPath()%>/project/petphoto.do?action=getPetPhotoTest&photoId=${photo.photoId}"
										class="d-block w-100 adImg" alt="img">
								</div>
							</c:forEach>
						</div>
						<button class="carousel-control-prev" type="button"
							data-bs-target="#carouselExampleSlidesOnly" data-bs-slide="prev">
							<span class="carousel-control-prev-icon" aria-hidden="true"></span>
							<span class="visually-hidden">Previous</span>
						</button>
						<button class="carousel-control-next" type="button"
							data-bs-target="#carouselExampleSlidesOnly" data-bs-slide="next">
							<span class="carousel-control-next-icon" aria-hidden="true"></span>
							<span class="visually-hidden">Next</span>
						</button>
					</div>
					<!-- 輪播元素結束 -->
				</div>
				<div class="col-lg-8">
					<div class="adoption_details_main_content">
						<div class="adoption_pet_infos">
							<!-- <h3>Sandy</h3> -->
							<ul>
								<li><span>入所天數：</span> <script>
									// 取得今天的日期
									var today = new Date();

									// 將 ${pet.comeInDate} 轉換為日期物件
									var comeInDate = new Date(
											"${pet.comeInDate}");

									// 計算入所天數
									var daysInShelter = Math
											.floor((today - comeInDate)
													/ (1000 * 60 * 60 * 24));

									// 輸出入所天數
									document.write(daysInShelter);
								</script></li>
								<li><span>入所日期：</span>${pet.comeInDate}</li>
								<li><span>性 別：</span>${pet.petGender}</li>
								<li><span>絕 育：</span> <script>
									// 使用布林值直接判斷
									document.write("${pet.petLigation}" ? "已絕育"
											: "未絕育");
								</script></li>
								<li><span>籠 舍：</span>${pet.petCage}</li>
								<li><span>是否開放領養：</span> <script>
									// 使用布林值直接判斷
									document
											.write("${pet.adopted}" ? "是" : "否");
								</script></li>
								<li><span>收容編號：</span>${pet.petNum}</li>
								<li><span>動物類別：</span>${pet.variety.type}</li>
								<li><span>品 種：</span>${pet.variety.variety}</li>
								<li><span>毛 色：</span>${pet.petColor}</li>
								<li><span>描 述：</span>${pet.petContent}</li>
								<li><span>收容所：</span>${pet.shelter.shelterName}</li>
								<li><span>收容所電話：</span>${pet.shelter.shelterPhoneNum}</li>
								<li><span>收容所地址：</span>${pet.shelter.shelterAddress}</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>

	</section>







	<!-- Button trigger modal -->
	<div class="row">

			<button type="button" class="make_reservation_btn" name="action"
				id="make_reservation_btn" value="" data-bs-toggle="modal"
				data-bs-target="#exampleModal">我要預約</button>
			<input type="hidden" name="shelterId" value="${pet.shelter.shelterId}" id="shelter_Id">

	</div>
	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-xl modal-fullscreen-xxl-down">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="exampleModalLabel">選擇預約日期/時段</h1>
					<input type="month" name="month" id="month">
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">

					<input class="mydate" gldp-id="mydate" id="mydate" value=""
						style="width: 1000px; height: 30px; visibility: visible; color: gray; font-weight: bold;"
						type="text" />

					<div gldp-el="mydate" id="mydateContainer"></div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">關閉</button>
					<button type="button" class="btn btn-primary" onclick="booking()">確認</button>
				</div>
				<form action="${pageContext.request.contextPath}/project/shelterbooking.do?action=setAddBookingNum" method="post" action="setAddBookingNum" id="bookingSubmit">
					<input type="hidden" name="bookingId" value="${shelter_booking.id}" id="bookingId">
					<input type="hidden" value="<%=id %>" id="userId" name="userId">
					<input type="hidden" name="shelter_Id" value="${pet.shelter.shelterId}" id="shelter_Id">
					<input type="hidden" name="pet_id" value="${pet.id}" id="pet_id">
				</form>
			</div>
		</div>
	</div>

	<%@include file="../components/footer.jsp"%>

</body>

</html>