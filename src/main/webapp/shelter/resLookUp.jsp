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
<!-- <script src="../assets/js/gallery-popup.js"></script> -->
<script src="../assets/js/custom.js"></script>
<!-- <script src="../assets/js/video.js"></script> -->
<!-- 原版預設 -->
<script src="../assets/js/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="../assets/js/adoption.js"></script>
<body>
	<!-- preloader Area -->
	<div id="preloader">
		<div id="status">
			<img src="../assets/img/loader.gif" alt="img">
		</div>
	</div>


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

		<div class="adoption_booking_content">
			<div class="adoption_detail">預約領養資訊</div>

			<div class="user_res_infos">

				<div class="data_wrap">
					<div>
						<span class="data-title">預約人：</span><span class="data-content">${user.userName}</span>
					</div>
					<div>
						<span class="data-title">預約人手機：</span><span class="data-content">${user.phoneNum}</span>
					</div>
					<div>
						<span class="data-title">收容編號：</span><span class="data-content">${reservation.pet.petNum}</span>
					</div>
					<div>
						<span class="data-title">預約日期：</span><span class="data-content">${reservation.shelterBooking.shelterBookingDate}</span>
					</div>
					<div>
						<span class="data-title">預約時間：</span><span class="data-content"  id="displayTime"></span>
					</div>
					<script>
						// 假設 reservation.shelterBooking.shelterBookingTime 是一個包含日期時間的字串
						var datetimeString = "${reservation.shelterBooking.shelterBookingTime}";

						// 使用 Date 物件解析日期時間字串
						var bookingTime = new Date(datetimeString);

						// 取得小時和分鐘
						var hours = bookingTime.getHours();
						var minutes = bookingTime.getMinutes();

						// 顯示時間
						document.getElementById("displayTime").innerText = hours
								+ ":" + (minutes < 10 ? '0' : '') + minutes;
					</script>
				</div>
			</div>
			<br>
			<div class="adoption_confirm_content">
				<div class="adoption_infos">
					<span>在您認養前，請您詳閱以下事項： <br> 1、​擁有時間、空間和經濟能力
						在領養狗狗前，必須先確定是否有合適的飼養環境、陪伴與照顧時間，以及經濟上可否負擔？
						先說每個月基本可能要支出的項目，包括飼料、美容、保健品、寵物玩具或寵物保險，甚至是昂貴的醫療費用等等。
						除此之外，同住家人是否同意，這也是很重要的考量，能避免家庭因素而中途棄養。 <br> 2、決定適合領養的浪浪
						不管是誰看到那些萌呆可愛的小毛球都很難抵抗，所以大家特別喜歡領養幼犬。
						但如果你是完全沒有飼養經驗、也沒有家人可幫忙照顧，相較於領養幼犬，建議領養成犬會更加適合！ <br>
						3、了解如何飼養和照顧
						領養狗狗雖然具備基本條件並完成領養程序後，就能順利帶浪浪回新家。但往後日子該如何照顧牠們，這是飼主們要事先做好的功課。 <br>
						4、如您預約後不克前往，請至<a
						href="<%=request.getContextPath()%>/shelter/reservation.do?action=getByUserId&memberId=<%=user.getUserId()%>"
						style="text-decoration: underline;">會員中心>預約管理>取消預約</a>將您的預約取消。
					</span>
				</div>



			</div>

		</div>
	</section>


	<%@include file="../components/footer.jsp"%>



</body>

</html>