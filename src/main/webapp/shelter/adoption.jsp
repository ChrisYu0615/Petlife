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
<title>Adoption - Furry</title>
<!-- Bootstrap css -->
<link rel="stylesheet" href="../assets/css/bootstrap.min.css">
<!-- animate css -->
<!-- <link rel="stylesheet" href="assets/css/animate.min.css"> -->
<!-- Fontawesome css -->
<link rel="stylesheet" href="../assets/css/fontawesome.all.min.css">
<!-- owl.carousel css -->
<!-- <link rel="stylesheet" href="assets/css/owl.carousel.min.css"> -->
<!-- owl.theme.default css -->
<!-- <link rel="stylesheet" href="assets/css/owl.theme.default.min.css"> -->
<!-- Magnific popup css -->
<!-- <link rel="stylesheet" href="assets/css/magnific-popup.min.css"> -->
<!-- navber css -->
<link rel="stylesheet" href="../assets/css/navber.css">
<!-- meanmenu css -->
<!-- <link rel="stylesheet" href="assets/css/meanmenu.css"> -->
<!-- Style css -->
<!-- <link rel="stylesheet" href="assets/css/style.css"> -->
<!-- Responsive css -->
<!-- <link rel="stylesheet" href="assets/css/responsive.css"> -->
<!-- Adoption css -->
<link rel="stylesheet" href="../assets/css/adoption.css">
<!-- Favicon -->
<!-- <link rel="icon" type="image/png" href="assets/img/favicon.png"> -->
</head>
</head>

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

	<!-- Common Banner -->
	<!-- <section id="common_area_banner">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="common_banner_content">
                        <h2>Adoption</h2>
                        <ul>
                            <li><a href="index.html">Home</a></li>
                            <li><span>/</span> Adoption</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </section> -->

	<!-- Adoption Top Area -->
	<section id="adoption_top_area" class="section_padding">
		<div class="container">
			<!-- Section Heading -->
			<div class="section_heading">
				<div class="searchbar">
					<form action="/Petlife/shelter/shelter.do" method="post">
						<select class="form-select pet_type">
							<option class="pet_type">選擇種類</option>
							<option class="pet_type" name="type" id="pet_type_dog" value="狗">狗</option>
							<option class="pet_type" name="type" id="pet_type_cat" value="貓">貓</option>
						</select> <select class="form-select pet_variety" name="petVarietyId"
							id="petVarietyId">

							<option selected>請先選擇種類</option>

						</select> <br> <label for="county" class="form-label"> </label> <br>
						<select class="form-select county" id="county" name="shelterAddress">
						</select> <select class="form-select shelter" id="shelter" name="shelterAddress">
							<option>選擇收容所</option>
						</select>
						<!-- <input class="useposition" type="text" id="lng" readonly placeholder="現在位置…"> -->
						<button type="button" class="search_btn" name="action" id="search_btn"
							value="">搜尋</button>
					</form>
				</div>
			</div>

		</div>
	</section>

	<!-- Adoption Main -->
	<section id="adoption_tab_area" class="section_padding_bottom">
		<div class="container">
			<!-- Section Heading -->
			<!-- <div class="row">
                <div class="col-lg-6 offset-lg-3">
                    <div class="section_heading">
                        <h2>Adopt a pet</h2>
                        <p>Tempor aute culpa consectetur labore deserunt cupidatat voluptate. Esse adipisicing in
                            deserunt adipisicing duis.</p>
                    </div>
                </div>
            </div> -->
			<div class="row">
				<div class="col-lg-12">
					<div class="pet_price_wrapper">
						<div class="row">
							<!-- <div class="col-lg-4 offset-lg-4">
                                <nav>
                                    <div class="nav nav-tabs" id="nav-tab" role="tablist">
                                        <button class="nav-link active" id="nav-home-tab" data-bs-toggle="tab"
                                            data-bs-target="#nav-home" type="button" role="tab" aria-controls="nav-home"
                                            aria-selected="true">All</button>
                                        <button class="nav-link" id="nav-profile-tab" data-bs-toggle="tab"
                                            data-bs-target="#nav-profile" type="button" role="tab"
                                            aria-controls="nav-profile" aria-selected="false">犬</button>
                                        <button class="nav-link" id="nav-contact-tab" data-bs-toggle="tab"
                                            data-bs-target="#nav-contact" type="button" role="tab"
                                            aria-controls="nav-contact" aria-selected="false">貓</button>
                                        <button class="nav-link" id="nav-pet-tab" data-bs-toggle="tab"
                                            data-bs-target="#nav-pet" type="button" role="tab" aria-controls="nav-pet"
                                            aria-selected="false">Birds</button>
                                    </div>
                                </nav>
                            </div> -->
						</div>

						<div id="result">
						
						</div>

<!-- 						<div class="tab-content" id="nav-tabContent"> -->
<!-- 							<div class="tab-pane fade show active" id="nav-home" -->
<!-- 								role="tabpanel" aria-labelledby="nav-home-tab"> -->
<!-- 								<div class="adoption_tab_item_wrapper"> -->
<!-- 									<div class="row"> -->
<!-- 										<div class="col-lg-3 col-md-6 col-sm-12 col-12"> -->
<!-- 											<div class="adoption_card_wrapper"> -->
<!-- 												<div class="adoption_item_img img_hover"> -->
<!-- 													<a href="adoption-details.html"><img -->
<!-- 														src="../assets/img/adoption/cat1.png" alt="img"></a> -->
<!-- 												</div> -->
<!-- 												<div class="adoption_item_content"> -->
<!-- 													<div class="adoption_info_btn"> -->
<!-- 														<a href="adoption-details.html">更多資訊</a> -->
<!-- 													</div> -->
<%-- 													<c:forEach var="pet" items="${petList}"> --%>
<!-- 														<br> -->
<!-- 														<ul> -->
<%-- 															<li><span>品種:</span> ${pet.variety.variety}</li> --%>
<%-- 															<li><span>花色:</span> ${pet.color}</li> --%>
<!-- 															<li><span>種類:</span> 貓</li> -->
<!-- 															<li><span>性別:</span> 母</li> -->
<!-- 															<li><span>我在:</span> 內湖收容所</li> -->
<!-- 														</ul> -->
<%-- 													</c:forEach> --%>
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->

					</div>
				</div>
			</div>
		</div>
		</div>
	</section>


	<!-- Subscribe Area -->
	<!-- <section id="subscribe_area">
        <div class="container">
            <div class="subscribe_wrapper">
                <div class="row align-items-center">
                    <div class="col-lg-4">
                        <div class="subscribe_text">
                            <p>Newsletter</p>
                            <h3 class="heading_main_subscribe">To get weekly & monthly news,
                                <span>Subscribe</span> to our newsletter.
                            </h3>
                        </div>
                    </div>
                    <div class="col-lg-6 offset-lg-2">
                        <div class="cta_right_side">
                            <form action="#!" id="subscribe_form">
                                <div class="input-group">
                                    <input type="text" class="form-control" placeholder="Your mail address" required>
                                    <button class="btn btn_theme btn_md" type="submit">Subscribe</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section> -->

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
			<img src="assets/img/logo.png" alt="img">
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
						<li><a href="#!"><img src="assets/img/icon/facebook.png"
								alt="icon"></a></li>
						<li><a href="#!"><img src="assets/img/icon/twitter.png"
								alt="icon"></a></li>
						<li><a href="#!"><img src="assets/img/icon/instagram.png"
								alt="icon"></a></li>
						<li><a href="#!"><img src="assets/img/icon/linkedin.png"
								alt="icon"></a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>


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
	<script src="../assets/js/adoption.js"></script>

</body>

</html>