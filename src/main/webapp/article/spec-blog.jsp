<%@page import="com.petlife.forum.entity.Article"%>
<%@page import="com.petlife.forum.service.impl.ArticleServiceImpl"%>
<%@page import="com.petlife.forum.service.impl.ArticleImgServiceImpl"%>
<%@page import="com.petlife.forum.service.ArticleService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
Article article = (Article) request.getAttribute("article");
%>


<!DOCTYPE html>
<html lang="zxx">

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Title -->
<title>Blog - Furry</title>
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
<!-- Favicon -->
<link rel="icon" type="image/png" href="../assets/img/favicon.png">
</head>

<body>
	<!-- preloader Area -->
	<div id="preloader">
		<div id="status">
			<img src="../assets/img/loader.gif" alt="img">
		</div>
	</div>

	<div class="headerPage"></div>



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



	<!--Our Blog-->
	<section id="our_blog_area" class="section_padding">
		<!-- <a href="/furry/blog-details.html" style="font-size:  50px; color: chocolate; padding: left 10px;">我要發文</a> -->

		<button class="btn btn-primary" type="submit"
			style="background-color: darkorange;">
			<a href="../article/blog-details.jsp"
				style="font-size: 30px; color: brown; padding: left 10px;"> 我要發文
			</a>


		</button>
		<br>
		<div class="container">
			<div class="row">
				<div class="col-lg-3">
					<div class="sidebar_boxed_wrapper">
						<div class="sidebar_common_heading">
							<div class="sidebar_search_wrapper">

								<div class="input-group mb-3">
									<input type="text" class="form-control" placeholder="Search">
									<button class="btn btn_theme btn_sm">
										<i class="fas fa-search"></i>
									</button>
								</div>
							</div>

							<div class="accordion" id="accordionExample">
								<!-- 貓咪 -->
								<div class="accordion-item">
									<span class="sidebar_common_heading">
										<h3>狗狗</h3>
									</span>
									<ul class="list-group list-group-flush">
										<li class="list-group-item">熱門文章</li>
										<li class="list-group-item">最新文章</li>
										<li class="list-group-item">浪浪的家</li>
									</ul>
								</div>
								<!-- 狗狗 -->
								<div class="accordion-item">
									<span class="sidebar_common_heading">
										<h3>貓貓</h3>
									</span>
									<ul class="list-group list-group-flush">
										<li class="list-group-item">熱門文章</li>
										<li class="list-group-item">最新文章</li>
										<li class="list-group-item">貓貓的家</li>
										<li class="list-group-item">貓貓保健</li>
									</ul>
								</div>
								<!-- 範例-3 -->
								<div class="accordion-item">
									<span class="sidebar_common_heading">
										<h3>閒聊</h3>
									</span>
									<ul class="list-group list-group-flush">
										<li class="list-group-item">熱門文章</li>
										<li class="list-group-item">最新文章</li>
										<!-- <li class="list-group-item">貓貓保健</li> -->
									</ul>
								</div>
								<div class="accordion-item">
									<span class="sidebar_common_heading">
										<h3>特殊</h3>
									</span>
									<ul class="list-group list-group-flush">
										<li class="list-group-item">熱門文章</li>
										<li class="list-group-item">最新文章</li>
										<li class="list-group-item">爬蟲類</li>
										<li class="list-group-item">木木梟</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>



				<div class="col-lg-9">
					<div class="row">
						<div class="col-lg-9 col-md-6 col-sm-12 col-12">
							<div class="blog_area_wrapper">
								<div class="blog_area_content">
									<h3>
										<a href="blog-details.html"> 文章分類</a>
									</h3>
									<h2>
										<a href="blog-details.html"> ${article.articleName} </a>
									</h2>
									<div class="blog_area_author_wrappe">
										<div class="blog_area_author_img">
											<img
												src="<%=request.getContextPath()%>/user/user.do?action=getUserHeadshot&userId=<%=article.getUser().getUserId()%>"
												alt="img" style="width: 50px; height: 50px">
										</div>
										<div class="blog_area_author_text">
											<h5>Jennifer lawrence</h5>
											<p>
												<span>發文時間</span> <i class="fas fa-circle"></i> <span>8
													min read</span>
											</p>
										</div>
									</div>
									<figure class="figure">
										<img
											src="<%=request.getContextPath()%>/articleImg/articleImg.do?action=??&articleId=<%=article.getArticleId()%>"
											class="figure-img img-fluid rounded" alt="...">
										<figcaption class="figure-caption text-end">文章圖片</figcaption>
									</figure>
									<h3>${article.articleContent}</h3>
								</div>

								<span class="col-lg-2 col-md-6 col-sm-12 col-12">
									<button class="btn btn-primary" type="submit"
										style="background-color: darkorange;">
										<a href="/furry/blog-details.html"
											style="font-size: 20px; color: brown; padding: left 10px;">
											我要檢舉 </a>
								</span>
							</div>
							<hr style="border:1px solid #ff8c00;Width: 765.6px;">



						</div>
						<div class="row">
							<div class="col">
								<p class="h1 my-5">
									<strong>留言區</strong>
								</p>
							</div>
						</div>
						<div class="row">
							<div class="col-10">
								<div class="col">
									<div class="col d-inline-flex">
										<p class="h5">吳康仁</p>
										<p class="h5 text-black-50">－2023年11月7號發佈</p>
									</div>
									<div class="col">感覺很棒欸</div>
									</div>
								<hr style="border:1px solid #ff8c00;Width: 765.6px;">
							</div>
							
						</div>
						
						<div class="row">
							<div class="col-10">
								<div class="col">
									<div class="col d-inline-flex">
										<p class="h5">吳康康</p>
										<p class="h5 text-black-50">－2023年11月7號發佈</p>
									</div>
									<div class="col">感覺很棒欸</div>
									</div>
								<hr style="border:1px solid #ff8c00; Width: 765.6px;">
							</div>
							
						</div>
						
						<div class="row">
							<div class="col-10">
								<div class="col">
									<div class="col d-inline-flex">
										<p class="h5">吳仁仁</p>
										<p class="h5 text-black-50">－2023年11月7號發佈</p>
									</div>
									<div class="col">感覺很棒欸</div>
									</div>
								<hr style="border:1px solid #ff8c00;Width: 765.6px;">
							</div>
							
						</div>
						<div class="row mt-3">
							<div class="col d-inline-flex p-2">
							<img 
								src="<%=request.getContextPath()%>/user/user.do?action=getUserHeadshot&userId=<%=article.getUser().getUserId()%>"
												alt="img" style="width: 50px; height: 50px">
								<p class="p-2">張震</p>
								<img src="../../static/blogimages/張震.png" class="img-fluid p-0"
									style="height: 50px" alt="" />
							</div>
						</div>
						<div class="row">
							<div class="col-12">
								<div class="form-group">
									<input type="text" class="form-control" style="height: 100px"
										id="exampleInput" placeholder="來留個言吧" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col">
								<button type="button" class="btn btn-success m-2 float-end">
									送出</button>
							</div>
						</div>
					</div>







				</div>
			</div>
		</div>
		</div>
	</section>


	<div class="footerPage"></div>



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
	<script>
		$(".headerPage").load("../components/header.html");
		$(".footerPage").load("../components/footer.html");
	</script>
</body>

</html>