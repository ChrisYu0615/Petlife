<%-- <%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="UTF-8"%> --%>
<%-- <%@page import="com.petlife.admin.service.impl.commServiceImpl"%> --%>
<%-- <%@page import="com.petlife.admin.service.commService"%> --%>
<%-- <%@page import="com.petlife.admin.entity.comm"%> --%>
<%@page import="com.petlife.mall.entity.Comm"%>
<%@page import="com.petlife.mall.service.impl.CommServiceImpl"%>
<%@page import="com.petlife.mall.service.CommService"%>



<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@page import="com.petlife.admin.*"%>


<%
CommService commSvc = new CommServiceImpl();
List<Comm> list = commSvc.getAll();
pageContext.setAttribute("list", list);
%>
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
</head>


<body class="hold-transition sidebar-mini layout-fixed">
	<div id="preloader">
		<div id="status">
			<img src="../assets/img/loader.gif" alt="img">
		</div>
	</div>
	<!-- 應該是header 先不管 -->
	<div class="headerPage"></div>
		    <section id="our_shop_main" class="section_padding">
        <div class="container">
            <div class="row">
                <!-- side bar -->
                <div class="col-lg-3">
                    <div class="sidebar_boxed_wrapper">
                        <div class="sidebar_common_heading">
                            <h3>Sidebar</h3>
                        </div>
                        <!-- search bar -->
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" placeholder="Search">
                            <button class="btn btn_theme btn_sm"><i class="fas fa-search"></i></button>
                        </div>

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
                                            <li class="list-group-item">貓咪飼料</li>
                                            <li class="list-group-item">貓咪主食罐</li>
                                            <li class="list-group-item">貓咪副食罐</li>
                                            <li class="list-group-item">貓咪零食</li>
                                            <li class="list-group-item">貓咪用品</li>
                                            <li class="list-group-item">貓沙</li>
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
                                            <li class="list-group-item">狗狗飼料</li>
                                            <li class="list-group-item">狗狗主食罐</li>
                                            <li class="list-group-item">狗狗副食罐</li>
                                            <li class="list-group-item">狗狗零食</li>
                                            <li class="list-group-item">狗狗用品</li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-9">
                    <div class="shop_main_area_wrapper">
                        <div class="shop_heading_sort_area">
<!--                         	說明找到幾個商品 -->
                            <div class="shop_main_area_heading">
                                <h3>We found 29 items</h3>
                            </div>
                        </div>
                        <div class="shop_item_wrapper">
                            <div class="row">
                                <!-- 一個 class="col-lg-4 col-md-6 col-sm-12 col-12" 就是一個comm_item-->
                                <%@ include file="page1.file" %>
                                <c:forEach var="comm" items="${list}" begin="<%=pageIndex%>"
										end="<%=pageIndex+rowsPerPage-1%>">
									<div class="col-lg-4 col-md-6 col-sm-12 col-12">
                                    <div class="shop_main_item">
                                        <div class="shop_item_img">
<!--                                         	找單獨一個comm的按鈕 -->
                                            <a href="shop-details.html"><img src="../assets/img/shop/shop-1.png"
                                                    alt="img"></a>
                                        </div>
                                        <div class="shop_item_content">
                                            <h3><a href="shop-details.html">${comm.commName}</a></h3>
                                            <div class="shop_item_price">
                                                <p>${comm.commPrice}</p>
                                                <h5>${comm.commOnsalePrice}</h5>
                                            </div>
                                            <div class="shop_quent_wrapper">
                                                <div class="shop_quentiy_item">
                                                    <button><i class="fas fa-minus-circle"></i></button>
                                                </div>
                                                <div class="shop_quentiy_item_shows">
                                                    <input type="number" value="1">
                                                </div>
                                                <div class="shop_quentiy_item">
                                                    <button><i class="fas fa-plus-circle"></i></button>
                                                </div>
                                            </div>
                                            <FORM METHOD="post"
													ACTION="<%=request.getContextPath()%>/comm/comm.do"
													style="margin-bottom: 0px;">
													<input type="submit" value="修改">
													<input type="hidden" name="commId" value="${comm.commId}">
													<input type="hidden" name="action" value="getOne_For_Update">
											</FORM>
                                        </div>
                                    </div>
                                </div>	
										
								</c:forEach>
                                <%@ include file="page2.file"%>
                                
<!--                                 <div class="col-lg-4 col-md-6 col-sm-12 col-12"> -->
<!--                                     <div class="shop_main_item"> -->
<!--                                         <div class="shop_item_img"> -->
<!--                                             <a href="shop-details.html"><img src="../assets/img/shop/shop-2.png" -->
<!--                                                     alt="img"></a> -->

<!--                                         </div> -->
<!--                                         <div class="shop_item_content"> -->
<!--                                             <h3><a href="shop-details.html">Cat toilet bowl</a></h3> -->
<!--                                             <div class="shop_item_price"> -->
<!--                                                 <p>Tk. 500.00/KG</p> -->
<!--                                                 <h5>Tk. 300.00/KG</h5> -->
<!--                                             </div> -->
<!--                                             <div class="shop_item_rating"> -->
<!--                                                 <i class="fas fa-star"></i> -->
<!--                                                 <i class="fas fa-star"></i> -->
<!--                                                 <i class="fas fa-star"></i> -->
<!--                                                 <i class="fas fa-star"></i> -->
<!--                                                 <i class="fas fa-star"></i> -->
<!--                                                 <span>729</span> -->
<!--                                             </div> -->
<!--                                             <div class="shop_quent_wrapper"> -->
<!--                                                 <div class="shop_quentiy_item"> -->
<!--                                                     <button><i class="fas fa-minus-circle"></i></button> -->
<!--                                                 </div> -->
<!--                                                 <div class="shop_quentiy_item_shows"> -->
<!--                                                     <input type="number" value="1"> -->
<!--                                                 </div> -->
<!--                                                 <div class="shop_quentiy_item"> -->
<!--                                                     <button><i class="fas fa-plus-circle"></i></button> -->
<!--                                                 </div> -->
<!--                                             </div> -->
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                 </div> -->
                                
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
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src="../assets/js/user_profile.js"></script>
</body>

</html>