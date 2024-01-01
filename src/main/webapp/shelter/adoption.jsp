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

	<%@include file="../components/footer.jsp"%>


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