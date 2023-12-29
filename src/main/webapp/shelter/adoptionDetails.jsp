<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="com.petlife.pet.entity.Pet"%>
<%@ page import="com.petlife.pet.entity.PetVariety"%>
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
<link href="../assets/css/glDatePicker.default2.css" rel="stylesheet"
	type="text/css">
<!-- 此版自定 -->
<link href="../assets/css/glDatePicker.darkneon1.css" rel="stylesheet"
	type="text/css">
<!-- 原版預設 -->
<link href="../assets/css/glDatePicker.flatwhite1.css" rel="stylesheet"
	type="text/css">

<!-- 此版自定 -->
	<script	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
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
<script src="https://cdn.jsdelivr.net/gh/glad/glDatePicker/glDatePicker.min.js"></script>
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
.on{
background-color:red;
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
	<!-- Header Area -->
	<header class="main_header_arae">
		<!-- Navbar Bar -->
		<div class="navbar-area">
			<div class="main-responsive-nav">
				<div class="container">
					<div class="main-responsive-menu">
						<div class="logo">
							<img src="../assets/img/favicon.png" alt="logo"> <a
								href="index.html"> </a>
						</div>
					</div>
				</div>
			</div>
			<div class="main-navbar" style="background-color: #FFD966;">
				<div class="container">
					<div class="row">
						<nav class="navbar navbar-expand-md navbar-light">
							<a class="navbar-brand" href="#"> <img
								src="../assets/img/main_logo.png" alt="logo">
							</a>
							<div class="collapse navbar-collapse mean-menu"
								id="navbarSupportedContent">
								<ul class="navbar-nav">

									<li class="nav-item"><a href="#" class="nav-link active">首頁</a>
									</li>

									<li class="nav-item"><a href="#" class="nav-link">
											寵物商城 <i class="fas fa-angle-down"></i>
									</a>
										<ul class="dropdown-menu" style="left: -50px;">
											<li class="nav-item"><a href="#" class="nav-link">Service1</a>
											</li>
											<li class="nav-item"><a href="#" class="nav-link">Service2</a>
											</li>
											<li class="nav-item"><a href="#" class="nav-link">Service3</a>
											</li>
										</ul></li>

									<li class="nav-item"><a href="#" class="nav-link">
											寵物領養 <i class="fas fa-angle-down"></i>
									</a>
										<ul class="dropdown-menu" style="left: -50px;">
											<li class="nav-item"><a href="#" class="nav-link">Service1</a>
											</li>
											<li class="nav-item"><a href="#" class="nav-link">Service2</a>
											</li>
											<li class="nav-item"><a href="#" class="nav-link">Service3</a>
											</li>
										</ul></li>

									<li class="nav-item"><a href="#" class="nav-link">
											寵物論壇 <i class="fas fa-angle-down"></i>
									</a>
										<ul class="dropdown-menu" style="left: -50px;">
											<li class="nav-item"><a href="#" class="nav-link">Service1</a>
											</li>
											<li class="nav-item"><a href="#" class="nav-link">Service2</a>
											</li>
											<li class="nav-item"><a href="#" class="nav-link">Service3</a>
											</li>
										</ul></li>
								</ul>

								<div class="others-options d-flex align-items-center">
									<div class="option-item" style="left: -50px;">
										<a href="#" class="nav-link shopping-cart"> <i
											class="fas fa-shopping-cart">購物車(?)</i>
										</a>
									</div>
									<div class="option-item" style="left: -50px;">
										<ul class="navbar-nav" style="padding-left: 30px;">
											<li class="nav-item"><a href="#" class="nav-link"> <i
													class="fas fa-user" style="font-size: 15px;">登入</i>
											</a> <!-------!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-->
												<ul class="dropdown-menu" style="left: -50px;">
													<li class="nav-item"><a href="#" class="nav-link">Service1</a>
													</li>
													<li class="nav-item"><a href="#" class="nav-link">Service2</a>
													</li>
													<li class="nav-item"><a href="#" class="nav-link">Service3</a>
													</li>
												</ul></li>
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
				<div class="adoption_detail">動物資訊</div>
			</div>
			<div class="row gx-0">
				<div class="col-lg-4">
					<div class="adoption_details_big_img">
						<img src="../assets\img\adoption\cat1.png" alt="img">
					</div>
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
								<li><span>是否開放領養：</span>
								<script>
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
		<button type="button" class="make_reservation_btn" name="action" id="make_reservation_btn"
			value="" data-bs-toggle="modal"	data-bs-target="#exampleModal">我要預約</button>
		<input type="hidden" name="shelter_Id" value="${pet.shelter.shelterId}" id="shelter_Id">	
	</div>
	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1"  
		aria-labelledby="exampleModalLabel" aria-hidden="true" >
		<div class="modal-dialog modal-xl modal-fullscreen-xxl-down">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="exampleModalLabel">選擇預約日期/時段</h1>
					<input type="month" name="month" id="month">
					<button type="button" class="btn-close" data-bs-dismiss="modal"	aria-label="Close"></button>
				</div>
				<div class="modal-body">
				
					<input class="mydate" gldp-id="mydate" id="mydate" value="所選日期"
						style="width: 1000px; height: 30px; visibility: visible; color: gray; font-weight: bold;"
						type="text"/>
						
					<div gldp-el="mydate" id="mydateContainer">
					
					<script>
						$('#mydate').glDatePicker(
								{
									showAlways : true,       // 預設為 false
					 				cssName: 'default',      // 可用 'default' 或  'darkneon' 或  'flatwhite'
//					              format: 'yyyy-mm-dd',    // 預設
//					              dowOffset: 0,            // 預設
//					              allowMonthSelect: false, // 預設
//					              allowYearSelect: true,   // 預設
//					              prevArrow: '\u25c4',     // 預設
//					              nextArrow: '\u25ba',     // 預設
					                dowNames : [ '<font color=red>星期日</font>', '星期一', '星期二', '星期三', '星期四', '星期五', '<font color=red>星期六</font>' ], //自定
					                monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'], //自定
					                
					// ====================================================================================================              
//					              以下的'月'為陣列index，加1才為正確的幾月

					                
//					 			    selectedDate: new Date(),             // 今天的日期        (藍色-->橘色)(預設原有)
//					 			    selectedDate: new Date(2023, 10, 15), // 自選選定的日期 (藍色)


//					 			    selectableDates: // 可選的數個日期 (白色)
//					 			    [
//					 			        { date: new Date(2023, 10, 15) },
//					 			        { date: new Date(2023, 10, 20) },
//					 			        { date: new Date(2023, 10, 25) }
//					 			    ],


//					 			    selectableDateRange: // 可選的日期範圍 (白色)
//					 			    [
//					 			        { from: new Date(2023, 10, 15),  to: new Date(2023, 10, 18)  },
//					 			        { from: new Date(2023, 10, 25),  to: new Date(2023, 10, 28) }
//					 			    ],


//					 			    selectableYears:  [2022, 2023, 2024], //可選的年份
//					 			    selectableMonths: [06, 07, 08],       //可選的月份


//					 			    specialDates: [                       // 特殊日期的日期 + 信息 (綠色)
//					 			        {
//					 			            date: new Date(2023, 0, 15),
//					 			            data: { message: '每月15日會議' },
//					 			            repeatMonth: true
//					 			        },
//					 			        {
//					 			            date: new Date(0, 0, 1),
//					 			            data: { message: '新年快樂!' },
//					 			            repeatYear: true
//					 			        },
//					 			    ],


//					 			    selectableDOW: [1, 3, 5], //每週可選的星期幾 (白色)



					                onClick: function(target, cell, date, data) {
						                 target.val(date.getFullYear() + '-' +
						                		   (((date.getMonth()+1)<10)? "0"+(date.getMonth()+1):(date.getMonth()+1)) + '-' +
								                   ((date.getDate()<10)? "0"+date.getDate():date.getDate()));

						                 if(data != null) {
						                     alert(data.message + '\n' + date);
						                 }
					                }

								});
					</script>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">關閉</button>
					<button type="button" class="btn btn-primary">確認</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Footer Area -->
	<footer id="footer_area" style="background-color: #FFF2CC;">
		<div class="container">
			<div class="row">
				<div class="col-lg-4 col-md-12 col-sm-12 col-12">
					<div class="footer_area_about">
						<img src="../assets/img/main_logo.png" alt="img">
						<p>
							我們致力於提供最優質的寵物商城，讓飼主可以為寵物添加需要的物品；另外我們也提供與各地收容所的媒介服務，希望能夠讓更多想要養寵物的人可以以領養替代購買的方式，找到自己的最佳夥伴；如果有遇到相關寵物議題，也歡迎大家在論壇中踴躍發言。
						</p>
						<!-- <h6><strong>Address:</strong> 858 Walnutwood Ave. Webster, NY 14580</h6>
                    <h6><strong>Phone:</strong> <a href="tel:123-284-2554">+011 234-567-890</a></h6> -->
						<h6>
							<strong>Email:</strong> <a href="mailto:info@example.com">info@example.com</a>
						</h6>
					</div>
				</div>

				<div class="col-lg-2 col-md-6 col-sm-12 col-12">
					<div class="footer_navitem_ara">
						<h3 style="font-weight: bold;">快速連結</h3>
						<div class="nav_item_footer">
							<ul>
								<li><a href="#">會員中心</a></li>
								<li><a href="#">寵物商城</a></li>
								<li><a href="#">寵物論壇</a></li>
								<li><a href="#">寵物領養</a></li>
							</ul>
						</div>
					</div>
				</div>

				<div class="col-lg-2 col-md-6 col-sm-12 col-12">
					<div class="footer_navitem_ara">
						<h3 style="font-weight: bold;">會員權益</h3>
						<div class="nav_item_footer">
							<ul>
								<li><a href="#">常見Q&A</a></li>
								<li><a href="#">網站政策</a></li>
								<li><a href="#">防詐騙宣導</a></li>
								<li><a href="#">與我們聯繫</a></li>
								<!-- <li><a href="terms-service.html">Terms of service</a></li> -->
							</ul>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-12 col-sm-12 col-12">
					<!-- <div class="footer_navitem_ara">
                    <h3>Latest tweets</h3>
                    <div class="footer_twitter_area">
                        <a href="#!" class="footer_twit_title"><i class="fab fa-twitter"></i> #digitalmarketing</a>
                        <p>Lorem ipsum dolor sit amet consec elit sed eiusmod tempor incididunt ut labore etdolore
                            magna aliqua. Sit amet consec elit sed eiusmod tempor</p>
                        <a href="#!" class="footer_twit_two">twitter.com/i/#puredrinkingwater</a>
                        <h6>December 13, 2021 04:20 PM</h6>
                    </div>
                </div> -->
				</div>
			</div>
		</div>
	</footer>

	<!-- Copyright Area -->
	<div class="copyright_area">
		<div class="container">
			<div class="row align-items-center">
				<div class="co-lg-6 col-md-6 col-sm-12 col-12">
					<div class="copyright_left">
						<p>Copyright © 2023 All Rights Reserved</p>
					</div>
				</div>
				<div class="co-lg-6 col-md-6 col-sm-12 col-12">
					<div class="copyright_right">
						<ul>
							<li><a href="#!"><i class="fab fa-facebook"></i></a></li>
							<li><a href="#!"><i class="fab fa-twitter-square"></i></a></li>
							<li><a href="#!"><i class="fab fa-instagram"></i></a></li>
							<li><a href="#!"><i class="fab fa-linkedin"></i></a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Top To Bottom Area -->
	<div class="go-top">
		<i class="fas fa-chevron-up"></i> <i class="fas fa-chevron-up"></i>
	</div>

	<!-- Offcanvas Sidebar Area -->
	<div class="offcanvas offcanvas-end offcanvas_custom" tabindex="-1"
		id="offcanvasRight">
		<div class="offcanvas-header">
			<img src="../assets/img/logo.png" alt="img">
			<button type="button" class="btn-close text-reset"
				data-bs-dismiss="offcanvas" aria-label="Close"></button>
		</div>
		<div class="offcanvas-body">
			<div class="offcanvas_right_wrapper">
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed
					do eiusmod tempor incididunt ut tur incidunt ut labore et</p>
				<h4>Contact Us</h4>
				<div class="top_bar_left_item">
					<div class="top_Bar_left_icon">
						<i class="fas fa-envelope"></i>
					</div>
					<div class="top_Bar_left_text">
						<h5>
							<a href="mailto:donation@domain.com">donation@domain.com</a>
						</h5>
						<h6>Get free estimate</h6>
					</div>
				</div>
				<div class="top_bar_left_item">
					<div class="top_Bar_left_icon">
						<i class="fas fa-phone"></i>
					</div>
					<div class="top_Bar_left_text">
						<h5>
							<a href="tel:+011-234-567-89">+011 234 567 89</a>
						</h5>
						<h6>Sat to Fri: 8:00am to 10pm</h6>
					</div>
				</div>
				<div class="top_bar_left_item">
					<div class="top_Bar_left_icon">
						<i class="fas fa-map-marker-alt"></i>
					</div>
					<div class="top_Bar_left_text">
						<h5>32, New street road, New castle.</h5>
						<h6>Get direction</h6>
					</div>
				</div>
				<div class="offcanvas_follow_area">
					<h5>Follow Us</h5>
					<ul>
						<li><a href="#!"><img
								src="../assets/img/icon/facebook.png" alt="icon"></a></li>
						<li><a href="#!"><img
								src="../assets/img/icon/twitter.png" alt="icon"></a></li>
						<li><a href="#!"><img
								src="../assets/img/icon/instagram.png" alt="icon"></a></li>
						<li><a href="#!"><img
								src="../assets/img/icon/linkedin.png" alt="icon"></a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>



</body>

</html>