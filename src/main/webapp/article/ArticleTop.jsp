<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.petlife.forum.service.impl.ArticleServiceImpl"%>
<%@page import="com.petlife.forum.service.impl.ArticleImgServiceImpl"%>
<%@page import="com.petlife.forum.service.ArticleService"%>
<%@page import="com.petlife.forum.service.ArticleImgService"%>
<%@page import="com.petlife.forum.entity.Article"%>
<%@page import="com.petlife.forum.entity.ArticleImg"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%
//     ArticleService articleSvc = new ArticleServiceImpl();
//     List<Article> list = articleSvc.getAllArticle();
//     pageContext.setAttribute("list",list);

Article article = (Article) request.getAttribute("article");
%>

<!DOCTYPE html>
<html lang="zh-TW">

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

	<%@include file="../components/header.jsp"%>



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

		<input class="btn btn-primary" onclick="goBack()" type="submit"
			value="回上一頁"
			style="height: 50px; width: 300px; font-size: 30px; color: brown; background-color: darkorange;">


		<br>
		<div class="container">
			<div class="row">
				<div class="col-lg-3">
					<div class="sidebar_boxed_wrapper">
						<div class="sidebar_common_heading">
							<div class="sidebar_search_wrapper">
							</div>

							<div class="accordion" id="accordionExample">
								<!-- 狗狗 -->
								<div class="accordion-item">
									<span class="sidebar_common_heading">
										<h3>狗狗</h3>
									</span>
									<ul class="list-group list-group-flush">
										<li class="list-group-item"><a
											href="<%=request.getContextPath()%>/art/art.do?action=getTopArticlesByCTR&forumId=1">熱門文章</a>
										</li>
										<li class="list-group-item"><a
											href="<%=request.getContextPath()%>/art/art.do?action=getArticlesByForumId&forumId=1">狗狗的家</a>
										</li>
									</ul>
								</div>

								<!-- 貓貓 -->
								<div class="accordion-item">
									<span class="sidebar_common_heading">
										<h3>貓貓</h3>
									</span>
									<ul class="list-group list-group-flush">
										<li class="list-group-item"><a
											href="<%=request.getContextPath()%>/art/art.do?action=getTopArticlesByCTR&forumId=2">熱門文章</a>
										</li>
										<li class="list-group-item"><a
											href="<%=request.getContextPath()%>/art/art.do?action=getArticlesByForumId&forumId=2">貓貓的家</a>
										</li>
									</ul>
								</div>
								<!-- 閒聊3 -->
								<div class="accordion-item">
									<span class="sidebar_common_heading">
										<h3>閒聊</h3>
									</span>
									<ul class="list-group list-group-flush">
										<li class="list-group-item"><a
											href="<%=request.getContextPath()%>/art/art.do?action=getTopArticlesByCTR&forumId=3">熱門文章</a>
										</li>
										<li class="list-group-item"><a
											href="<%=request.getContextPath()%>/art/art.do?action=getArticlesByForumId&forumId=3">來閒聊ㄅ</a>
										</li>
									</ul>
								</div>
								<div class="accordion-item">
									<span class="sidebar_common_heading">
										<h3>特殊</h3>
									</span>
									<ul class="list-group list-group-flush">
										<li class="list-group-item"><a
											href="<%=request.getContextPath()%>/art/art.do?action=getTopArticlesByCTR&forumId=4">熱門文章</a>
										</li>
										<li class="list-group-item"><a
											href="<%=request.getContextPath()%>/art/art.do?action=getArticlesByForumId&forumId=4">特殊文章</a>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>


				<div class="col-lg-9">
					<div class="row">
						<c:forEach var="article" items="${topArticles}">
							<div class="col-lg-6 col-md-6 col-sm-12 col-12">
								<div class="blog_area_wrapper">
									<div class="blog_area_img">
										<a
											href="<%=request.getContextPath()%>/art/art.do?action=getArticleById&articleId=${article.articleId}">
											<img
											src="<%=request.getContextPath()%>/art/art.do?action=getArticleImgById&articleId=${article.articleId}"
											alt="img" style="width: 470px; height: 400px;border-radius: 10px;">
										</a>
									</div>
									<div class="blog_area_content">
										<a
											href="<%=request.getContextPath()%>/art/art.do?action=getArticleById&articleId=${article.articleId}">
											<h3>${article.articleName}</h3><div style="font-size: 20px">瀏覽數 : ${article.ctr}</div></a>
										</a>
										<p>${article.articleContent}</p>
										<a
											href="<%=request.getContextPath()%>/art/art.do?action=getArticleById&articleId=${article.articleId}">
											
										<div class="blog_area_author_wrappe">
											<div class="blog_area_author_img">
												<a href="blog-details.html"> <img
													src="<%=request.getContextPath()%>/user/user.do?action=getUserHeadshot&userId=${article.user.userId}"
													alt="img" style="width: 50px; height: 50px">
												</a>
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
			</div>
		</div>
	</section>


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
	<script>
		function goBack() {
			history.back();
		}
	</script>
</body>

</html>