<%-- <%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="UTF-8"%> --%>
<%-- <%@page import="com.petlife.admin.service.impl.commServiceImpl"%> --%>
<%-- <%@page import="com.petlife.admin.service.commService"%> --%>
<%-- <%@page import="com.petlife.admin.entity.comm"%> --%>
<%@page import="com.petlife.mall.entity.Comm"%>
<%@page import="com.petlife.mall.service.impl.CommServiceImpl"%>
<%@page import="com.petlife.mall.service.CommService"%>



<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@page import="com.petlife.admin.*"%>

<!DOCTYPE html>
<html lang="zh-TW">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>歡迎來到寵愛生活的商城</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Title -->
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
	<!-- datatables -->
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.css">
</head>


<body class="hold-transition sidebar-mini layout-fixed">
	<div id="preloader">
		<div id="status">
			<img src="../assets/img/loader.gif" alt="img">
		</div>
	</div>
	<!-- header -->
	<%@include file="../components/header.jsp"%>
		    <section id="our_shop_main" class="section_padding">
        <div class="container">
            <div class="row">
                <!-- side bar -->
                <div class="col-lg-3">
                    <div class="sidebar_boxed_wrapper">
                        <div class="sidebar_common_heading">
                            <h3>搜尋商城</h3>
                        </div>
                        <!-- search bar -->
                        <form action="<%=request.getContextPath()%>/comm_for_user/listAllCommForUser.do" method="post">
                       		<div class="input-group mb-3">
                           	 	<input type="text" class="form-control" name="searchQuery" placeholder="Search">
                            	<input type="submit" value="搜尋名稱" class="btn btn_theme btn_sm">
                            	
                        	</div>
                        </form>
                        <!-- accordion -->
                        <div class="accordion" id="accordionExample">
                            <!-- 貓咪 -->
                            <div class="accordion-item">
                                <h2 class="accordion-header" id="headingOne">
                                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
                                        貓咪專區
                                    </button>
                                </h2>
                                <div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
                                    <div class="accordion-body">
                                        <ul class="list-group list-group-flush">
                                            <li class="list-group-item">
                                            	<a href="<%=request.getContextPath()%>/comm_for_user/listAllCommForUser.do?action=1000">貓咪飼料</a>
                                            </li>
                                            <li class="list-group-item">
                                            	<a href="<%=request.getContextPath()%>/comm_for_user/listAllCommForUser.do?action=1001">貓咪主食罐</a>
                                            </li>
                                            <li class="list-group-item">
                                            	<a href="<%=request.getContextPath()%>/comm_for_user/listAllCommForUser.do?action=1002">貓咪副食罐</a>
                                            </li>
                                            <li class="list-group-item">
	                                            <a href="<%=request.getContextPath()%>/comm_for_user/listAllCommForUser.do?action=1003">貓咪零食</a>
                                            </li>
                                            <li class="list-group-item">
    	                                        <a href="<%=request.getContextPath()%>/comm_for_user/listAllCommForUser.do?action=1004">貓咪用品</a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <!-- 狗狗 -->
                            <div class="accordion-item">
                                <h2 class="accordion-header" id="headingTwo">
                                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                        狗狗專區
                                    </button>
                                </h2>
                                <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
                                    <div class="accordion-body">
                                        <ul class="list-group list-group-flush">
                                            <li class="list-group-item">
                                            	<a href="<%=request.getContextPath()%>/comm_for_user/listAllCommForUser.do?action=2000">狗狗飼料</a>
                                            </li>
                                            <li class="list-group-item">
                                            	<a href="<%=request.getContextPath()%>/comm_for_user/listAllCommForUser.do?action=2001">狗狗主食罐</a>
                                            </li>
                                            <li class="list-group-item">
                                            	<a href="<%=request.getContextPath()%>/comm_for_user/listAllCommForUser.do?action=2002">狗狗副食罐</a>
                                            </li>
                                            <li class="list-group-item">
	                                            <a href="<%=request.getContextPath()%>/comm_for_user/listAllCommForUser.do?action=2003">狗狗零食</a>
                                            </li>
                                            <li class="list-group-item">
    	                                        <a href="<%=request.getContextPath()%>/comm_for_user/listAllCommForUser.do?action=2004">狗狗用品</a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-9">
                    <div class="shop_main_area_wrapper">
                        <div class="shop_item_wrapper">
                            <div class="row">
                                <!-- 一個 class="col-lg-4 col-md-6 col-sm-12 col-12" 就是一個comm_item-->
                                <c:forEach var="comm" items="${list}">
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
                                    <div class="shop_main_item">
                                        <div class="shop_item_img">
<!--                                         	找單獨一個comm的按鈕 -->
                                            <a href="shop-details.html"><img src="../assets/img/shop/shop-1.png"
                                                    alt="img"></a>
                                        </div>
                                        <div class="shop_item_content">
                                            <h3><a href="#">${comm.commName}</a></h3>
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
                                            
                                            <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/cart/cart.do" style="margin-bottom: 0px;">
											    <div class="shop_quent_wrapper">
											        <div class="shop_quentiy_item_shows">
											            <input type="number" name="purchasing_amount" value="1" min="1">
											        </div>
											    </div>
											    <input type="submit" value="加入購物車">
											    <input type="hidden" name="action" value="add_comm_to_cart">
											    <input type="hidden" name="commId" value="${comm.commId}">
											</FORM>
                                        </div>
                                    </div>
                                </div>	
								</c:forEach>
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
	<!-- dataTables -->
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.js"></script>
<!-- 	<script> -->
		
<!-- 	</script> -->
	
	
</body>

</html>