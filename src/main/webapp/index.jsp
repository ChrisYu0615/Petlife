<%@page import="java.util.List"%>
<%@page import="com.petlife.admin.service.impl.AdvertisementServiceImpl"%>
<%@page import="com.petlife.admin.service.AdvertisementService"%>
<%@page import="com.petlife.admin.entity.Advertisement"%>
<%@page import="com.petlife.mall.service.impl.CommServiceImpl"%>
<%@page import="com.petlife.mall.service.CommService"%>
<%@page import="com.petlife.mall.entity.Comm"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.petlife.forum.service.impl.ArticleServiceImpl"%>
<%@page import="com.petlife.forum.service.impl.ArticleImgServiceImpl"%>
<%@page import="com.petlife.forum.service.ArticleService"%>
<%@page import="com.petlife.forum.service.ArticleImgService"%>
<%@page import="com.petlife.forum.entity.Article"%>
<%@page import="com.petlife.forum.entity.ArticleImg"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<%
AdvertisementService advertisementService = new AdvertisementServiceImpl();
List<Advertisement> advertisementList = advertisementService.getAllWithActived();
pageContext.setAttribute("allActivedAd", advertisementList);

ArticleService articleSvc = new ArticleServiceImpl();
List<Article> articlesByCtr = articleSvc.getPopArticlesByCRT();
pageContext.setAttribute("articlesByCtr", articlesByCtr);

CommService commSvc = new CommServiceImpl();
List<Comm> popularCommList = commSvc.getPopularComm();
pageContext.setAttribute("popularCommList", popularCommList);
%>

<!DOCTYPE html>
<html lang="zh-TW">

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Title -->
<title>寵愛生活Petlife</title>
<!-- Bootstrap css -->
<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<!-- animate css -->
<link rel="stylesheet" href="assets/css/animate.min.css">
<!-- Fontawesome css -->
<link rel="stylesheet" href="assets/css/fontawesome.all.min.css">
<!-- owl.carousel css -->
<link rel="stylesheet" href="assets/css/owl.carousel.min.css">
<!-- owl.theme.default css -->
<link rel="stylesheet" href="assets/css/owl.theme.default.min.css">
<!-- Magnific popup css -->
<link rel="stylesheet" href="assets/css/magnific-popup.min.css">
<!-- navber css -->
<link rel="stylesheet" href="assets/css/navber.css">
<!-- meanmenu css -->
<link rel="stylesheet" href="assets/css/meanmenu.css">
<!-- Style css -->
<link rel="stylesheet" href="assets/css/style.css">
<!-- Responsive css -->
<link rel="stylesheet" href="assets/css/responsive.css">
<!-- Favicon -->
<link rel="icon" type="image/png" href="assets/img/favicon.png">
<link rel="stylesheet" href="assets/css/index.css">
<link rel="stylesheet" href="assets/css/Article.css" type="text/css">
</head>

<body>
	<!-- preloader Area -->
	<div id="preloader">
		<div id="status">
			<img src="assets/img/loader.gif" alt="img">
		</div>
	</div>

	<%@include file="components/header.jsp"%>

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

	<!-- Banner Area -->
	<section id="home_banner">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="main_banner_img_left">
						<!-- 在這裡嵌入輪播元素 -->
						<div id="carouselExampleSlidesOnly" class="carousel slide"
							data-bs-ride="carousel" data-interval="1">
							<div class="carousel-inner">
								<c:forEach var="activedAd" items="${allActivedAd}"
									varStatus="loop">
									<div class="carousel-item ${loop.first ? 'active' : ''}">
										<img
											src="<%= request.getContextPath() %>/advertisement/advertisement.do?action=getAdImg&adId=${activedAd.advertisementId}"
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
					</div>
				</div>

				<div class="col-md-12">
					<div class="main_banner_text_wrapper">
						<div class="banner_main_services">
							<div class="banner_main_service_item invisible"></div>
							<div class="banner_main_service_item invisible"></div>
							<div class="banner_main_service_item">
								<img
									src="<%=request.getContextPath()%>/assets/img/icon/banner-1.png"
									alt="img">
								<h5>寵物商城</h5>
							</div>
							<div class="banner_main_service_item">
								<img
									src="<%=request.getContextPath()%>/assets/img/icon/banner-2.png"
									alt="img">
								<h5>寵物領養</h5>
							</div>

							<a href="<%=request.getContextPath()%>/article/Articleindex.jsp">
								<div class="banner_main_service_item">
									<img
										src="<%=request.getContextPath()%>/assets/img/icon/banner-3.png"
										alt="img">
									<h5>寵物論壇</h5>
								</div>
							</a>
							<div class="banner_main_service_item invisible"></div>
							<div class="banner_main_service_item invisible"></div>							
						</div>
					</div>
				</div>
			</div>

		</div>
	</section>


	<div class="row">
		<div class="col-lg-6 offset-lg-3">
			<div class="section_heading">
				<h2 style="padding-top: 10px;">熱門商品</h2>
				<p>這裡有應有盡有的毛小孩商品!! 歡迎選購</p>
			</div>
		</div>
	</div>
	<div class="shop_item_wrapper">
		<div class="row">
			<c:forEach var="comm" items="${popularCommList}">
				<!-- 一個 class="col-lg-4 col-md-6 col-sm-12 col-12" 就是一個comm_item-->
				<span class="col-lg-4 col-md-6 col-sm-12 col-12">
					<div class="shop_main_item">
						<div class="shop_item_img">
							<a href="<%=request.getContextPath()%>/comm_for_user/singleCommForUser.do?action=show_comm_without_customer&commId=${comm.commId}"><img
								src="<%=request.getContextPath()%>/comm/DBJPGReader?commId=${comm.commId}"
								style="width: 405px; height: 300px;"></a>
						</div>
						<div class="shop_item_content">
							<h3>
								<a href="<%=request.getContextPath()%>/comm_for_user/singleCommForUser.do?action=show_comm_without_customer&commId=${comm.commId}">${comm.commName}</a>
							</h3>
							<div class="shop_item_price">
								<c:choose>
									<c:when test="${comm.commOnsalePrice == comm.commPrice}">
										<h5>${comm.commPrice}</h5>
									</c:when>
									<c:otherwise>
										<p>${comm.commPrice}</p>
										<h5>${comm.commOnsalePrice}</h5>
									</c:otherwise>
								</c:choose>
							</div>
							<!-- 其他商品屬性的顯示 -->
							<div class="shop_item_rating">
								<!-- 商品評分的顯示 -->
							</div>
						</div>
					</div>
				</span>
			</c:forEach>
		</div>
	</div>

	<br>
	<section>
		<div class="row">
			<div class="col-lg-6 offset-lg-3">
				<div class="section_heading">
					<h2 style="padding-top: 10px;">熱門文章</h2>
					<p>這裡有應有盡有的毛小孩商品!! 歡迎選購</p>
				</div>
			</div>
		</div>
		<br>
		<div class="col-lg-12">
			<div class="row">
				<c:forEach var="article" items="${articlesByCtr}">
					<div class="col-lg-3 col-md-6 col-sm-12 col-12">
						<div class="blog_area_wrapper">
							<div class="blog_area_img">
								<a
									href="<%=request.getContextPath()%>/art/art.do?action=getArticleById&articleId=${article.articleId}">
									<img
									src="<%=request.getContextPath()%>/art/art.do?action=getArticleImgById&articleId=${article.articleId}"
									alt="img"
									style="width: 200px; height: 200px; border-radius: 10px;">
								</a>
							</div>
							<div class="blog_area_content">
								<h3>
									<a
										href="<%=request.getContextPath()%>/art/art.do?action=getArticleById&articleId=${article.articleId}">
										${article.articleName}
										<div style="font-size: 20px">瀏覽數 : ${article.ctr}
									</a>
								</h3>
								<p>
									<c:choose>
										<c:when test="${fn:length(article.articleContent) > 50}">
        									${fn:substring(article.articleContent, 0, 50)}...
    								</c:when>
										<c:otherwise>
        							${article.articleContent}
    								</c:otherwise>
									</c:choose>
								</p>
								<%-- 								<p>${article.articleContent}</p> --%>
								<div class="blog_area_author_wrappe">
									<div class="blog_area_author_img">
										<a href="#"><img
											src="<%=request.getContextPath()%>/user/user.do?action=getUserHeadshot&userId=${article.user.userId}"
											alt="img" style="width: 50px; height: 50px"></a>
									</div>
									<div class="blog_area_author_text">
										<h5>${article.user.userName}</h5>
										<p>
											<span>${article.updateTime}</span> <i class="fas fa-circle"></i>
											<span>8 min read</span>
										</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>


	<!-- Testimonial Area -->
	<section id="testimonial_area"
		class="section_padding_bottom slider_side_btn">
		<div class="container">
			<!-- Section Heading -->
			<div class="row">
				<div class="col-lg-6 offset-lg-3">
					<div class="section_heading">
						<h2>遇見你的毛孩</h2>
						<p></p>
					</div>
				</div>
			</div>
			<!-- Inner Content -->
			<div class="row">
				<div class="col-lg-12">
					<div class="testmoinal_main_slider owl-theme owl-carousel">
						<div class="testimonial_area_item">
							<img
								src="<%=request.getContextPath()%>/assets/img/testimonial/test-2.png"
								class="test_main_img" alt="img">
							<p class="test_main_para">Lorem ipsum dolor sit amet,
								consectetur notted adipisicing elit sed do eiusm menos tdolore
								magna aliqua andhn.</p>
							<img
								src="<%=request.getContextPath()%>/assets/img/testimonial/quote.png"
								class="test_quote_img" alt="icon">
							<div class="test_destination">
								<h3>Adam brown</h3>
								<p>Businessman</p>
							</div>
						</div>
						<div class="testimonial_area_item">
							<img
								src="<%=request.getContextPath()%>/assets/img/testimonial/test-3.png"
								class="test_main_img" alt="img">
							<p class="test_main_para">Lorem ipsum dolor sit amet,
								consectetur notted adipisicing elit sed do eiusm menos tdolore
								magna aliqua andhn.</p>
							<img
								src="<%=request.getContextPath()%>/assets/img/testimonial/quote.png"
								class="test_quote_img" alt="icon">
							<div class="test_destination">
								<h3>Adam brown</h3>
								<p>Businessman</p>
							</div>
						</div>
						<div class="testimonial_area_item">
							<img
								src="<%=request.getContextPath()%>/assets/img/testimonial/test-2.png"
								class="test_main_img" alt="img">
							<p class="test_main_para">Lorem ipsum dolor sit amet,
								consectetur notted adipisicing elit sed do eiusm menos tdolore
								magna aliqua andhn.</p>
							<img
								src="<%=request.getContextPath()%>/assets/img/testimonial/quote.png"
								class="test_quote_img" alt="icon">
							<div class="test_destination">
								<h3>Adam brown</h3>
								<p>Businessman</p>
							</div>
						</div>
						<div class="testimonial_area_item">
							<img
								src="<%=request.getContextPath()%>/assets/img/testimonial/test-3.png"
								class="test_main_img" alt="img">
							<p class="test_main_para">Lorem ipsum dolor sit amet,
								consectetur notted adipisicing elit sed do eiusm menos tdolore
								magna aliqua andhn.</p>
							<img
								src="<%=request.getContextPath()%>/assets/img/testimonial/quote.png"
								class="test_quote_img" alt="icon">
							<div class="test_destination">
								<h3>Adam brown</h3>
								<p>Businessman</p>
							</div>
						</div>
						<div class="testimonial_area_item">
							<img
								src="<%=request.getContextPath()%>/assets/img/testimonial/test-2.png"
								class="test_main_img" alt="img">
							<p class="test_main_para">Lorem ipsum dolor sit amet,
								consectetur notted adipisicing elit sed do eiusm menos tdolore
								magna aliqua andhn.</p>
							<img
								src="<%=request.getContextPath()%>/assets/img/testimonial/quote.png"
								class="test_quote_img" alt="icon">
							<div class="test_destination">
								<h3>Adam brown</h3>
								<p>Businessman</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<%@include file="components/footer.jsp"%>

	<script src="assets/js/jquery.min.js"></script>
	<!-- Bootstrap js -->
	<script src="assets/js/bootstrap.bundle.js"></script>
	<!-- Meanu js -->
	<script src="assets/js/jquery.meanmenu.js"></script>
	<!-- Magnific Popup js -->
	<script src="assets/js/jquery.magnific-popup.min.js"></script>
	<!-- owl carousel js -->
	<script src="assets/js/owl.carousel.min.js"></script>
	<!-- wow.js -->
	<script src="assets/js/wow.min.js"></script>
	<!-- waypoints.js -->
	<script src="assets/js/waypoints.min.js"></script>
	<!-- counterup.js -->
	<script src="assets/js/jquery.counterup.min.js"></script>
	<!-- Custom js -->
	<!-- <script src="assets/js/gallery-popup.js"></script> -->
	<script src="assets/js/custom.js"></script>
	<!-- <script src="assets/js/video.js"></script> -->

	

</body>

</html>