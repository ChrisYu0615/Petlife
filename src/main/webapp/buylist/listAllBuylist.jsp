<%@page import="com.petlife.seller.entity.Seller"%>
<%@page import="com.petlife.mall.entity.Buylist"%>
<%@page import="com.petlife.mall.service.BuylistService"%>
<%@page import="com.petlife.mall.service.impl.BuylistServiceImpl"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@page import="com.petlife.admin.*"%>
<!-- ===測試================================================== -->
<%@page import="com.petlife.mall.entity.BuylistDetails"%>
<%@page import="com.petlife.mall.service.impl.BuylistDetailsServiceImpl"%>
<%@page import="com.petlife.mall.service.BuylistDetailsService"%>
<!-- ===/測試================================================== -->
<%
Seller seller = (Seller) session.getAttribute("seller");
BuylistService buylistSvc = new BuylistServiceImpl();
List<Buylist> list = buylistSvc.getAllBuylists(String.valueOf(seller.getSellerId()));
pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html lang="zh-TW">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>所有訂單編號資料</title>
<!-- 讓Loading, please wait消失 -->
<link rel="stylesheet" href="/buylist.css">
<!-- Bootstrap css -->
<link rel="stylesheet" href="../assets/css/bootstrap.min.css">
<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="../plugins/fontawesome-free/css/all.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="../https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="../dist/css/adminlte.min.css">
<!-- overlayScrollbars -->
<link rel="stylesheet"
	href="../plugins/overlayScrollbars/css/OverlayScrollbars.min.css">

<style>
div.star_block {
    display: inline-block;
    position: relative;
}

div.star_block>span.star {
    cursor: pointer;
    display: inline-block;
    margin-right: 3px;
}

div.star_block>span.star.-on {
    color: yellow;
}
img.brand-image {
	background-color: antiquewhite;
}

.brand-link .brand-image {
	margin-left: 0rem;
}

table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>
<style>
table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>

<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">

		<!-- 上方Navbar -->
		<nav
			class="main-header navbar navbar-expand navbar-white navbar-light">
			<!-- 上方左邊Left navbar links -->
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" data-widget="pushmenu"
					href="#" role="button"><i class="fas fa-bars"></i></a></li>
				<!-- 				<li class="nav-item d-none d-sm-inline-block"><a -->
				<!-- 					href="test.html" class="nav-link">Home</a></li> -->
				<!-- 這是contact bar -->
				<!-- <li class="nav-item d-none d-sm-inline-block">
                    <a href="#" class="nav-link">Contact</a>
                </li> -->
			</ul>

			<!-- 上方右邊Right navbar links -->
			<ul class="navbar-nav ml-auto">

				<!-- 這個是上方右邊全螢幕模式 -->
				<li class="nav-item"><a class="nav-link"
					data-widget="fullscreen" href="#" role="button"> <i
						class="fas fa-expand-arrows-alt"></i>
				</a></li>
			</ul>
		</nav>
		<!-- /.navbar -->





		<!-- Main Sidebar Container -->
		<aside class="main-sidebar sidebar-dark-primary elevation-4">
			<!-- Brand Logo -->
			<a href="listAllBuylist.jsp" class="brand-link"> <img
				src="../dist/img/main_logo.png" alt="AdminLTE Logo"
				class="brand-image img-corners elevation-3" style="opacity: .8">
				<span class="brand-text font-weight-light">寵愛生活後臺管理</span>
			</a>

			<!-- Sidebar -->
			<div class="sidebar">
				<!-- Sidebar user panel (optional) -->
				<div class="user-panel mt-3 pb-3 mb-3 d-flex">
					<div class="image">
						<img src="../dist/img/login_user1.png"
							class="img-circle elevation-2" alt="User Image">
					</div>
					<div class="info">
						<a href="#" class="d-block"><%=seller.getSellerName()%>賣家，你好</a>
					</div>
				</div>

				<!-- Sidebar Menu -->
				<nav class="mt-2">
					<ul class="nav nav-pills nav-sidebar flex-column"
						data-widget="treeview" role="menu" data-accordion="false">
						<!-- Add icons to the links using the .nav-icon class with font-awesome or any other icon font library -->

						<!-- 						<li class="nav-item"><a href="#" class="nav-link"> <i -->
						<!-- 								class="nav-icon fas fa-solid fa-id-card"></i> -->
						<!-- 								<p>通知</p> -->
						<!-- 						</a></li> -->
						<!-- 以下是可伸縮的側邊欄 -->

						<li class="nav-item"><a href="#" class="nav-link active">
								<i class="nav-icon fas fa-tachometer-alt"></i>

								<p>
									訂單管理 <i class="right fas fa-angle-left"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="listAllBuylist.jsp"
									class="nav-link active"> <i class="far fa-circle nav-icon"></i>
										<p>所有訂單</p>
								</a></li>
								<li class="nav-item"><a href="listAllBuylistState0.jsp"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>0:待付款</p>
								</a></li>
								<li class="nav-item"><a href="listAllBuylistState1.jsp"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>1:待出貨</p>
								</a></li>
								<li class="nav-item"><a href="listAllBuylistState2.jsp"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>2:運送中</p>
								</a></li>
								<li class="nav-item"><a href="listAllBuylistState3.jsp"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>3:訂單已完成</p>
								</a></li>
								<li class="nav-item"><a href="listAllBuylistState4.jsp"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>4:訂單已取消</p>
								</a></li>
								<li class="nav-item"><a href="listAllBuylistState5.jsp"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>5:退貨/退款</p>
								</a></li>
							</ul></li>
						<!-- ========================================================== -->
						<!-- 						<li class="nav-item"><a -->
						<!-- 							href="../buylistdetails/listAllBuylistDetails.jsp" -->
						<!-- 							class="nav-link"> <i class="nav-icon fas fa-solid fa-id-card"></i> -->
						<!-- 								<p>訂單商品細項</p> -->
						<!-- 						</a></li> -->
						<!-- ========================================================== -->

						<li class="nav-item"><a href="#" class="nav-link active">
								<i class="nav-icon fas far fa-copy"></i>

								<p>
									商品管理 <i class="right fas fa-angle-left"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="../comm/addComm.jsp"
									class="nav-link active"> <i class="far fa-circle nav-icon"></i>
										<p>上架商品</p>
								</a></li>
								<li class="nav-item"><a href="../comm/listAllComm.jsp"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>所有商品</p>
								</a></li>
								<li class="nav-item"><a
									href="../comm/listAllCommState0.jsp" class="nav-link"> <i
										class="far fa-circle nav-icon"></i>
										<p>0:販售中</p>
								</a></li>
								<li class="nav-item"><a
									href="../comm/listAllCommState1.jsp" class="nav-link"> <i
										class="far fa-circle nav-icon"></i>
										<p>1:缺貨中</p>
								</a></li>
								<li class="nav-item"><a
									href="../comm/listAllCommState2.jsp" class="nav-link"> <i
										class="far fa-circle nav-icon"></i>
										<p>2:下架</p>
								</a></li>
								<li class="nav-item"><a
									href="../comm/listAllCommState3.jsp" class="nav-link"> <i
										class="far fa-circle nav-icon"></i>
										<p>3:違規下架</p>
								</a></li>
							</ul></li>
						<!-- ========================================================== -->
						<li class="nav-item"><a
							href="<%=request.getContextPath()%>/logout/logout.do"
							class="nav-link" id="logout"> <i class="nav-icon fas fa-ad"></i>
								<p>登出</p>

						</a></li>

					</ul>
				</nav>
				<!-- /.sidebar-menu -->
			</div>
			<!-- /.sidebar -->
		</aside>




		<!-- 這邊是主內容上方的文字敘述 -->
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1 class="m-0">訂單管理</h1>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<!-- 								<li class="breadcrumb-item"><a href="test.html">Home</a></li> -->
								<!-- 								<li class="breadcrumb-item active">訂單管理</li> -->
							</ol>
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- /.content-header -->


			<!-- //這邊塞主內容文字 -->
			<!-- /.content-wrapper -->

			<!-- 			<div class="card-header"> -->
			<!-- 				<a href='addBuylist.jsp'><button type="button" -->
			<!-- 						class="btn btn-primary" id="btn_addcoupon" data-bs-toggle="modal" -->
			<!-- 						data-bs-target="#add_coupon">新增訂單</button></a> -->
			<!-- 			</div> -->
			<table id="table-1">
				<tr>
					<td>
						<h3>所有訂單編號資料</h3> <!-- 						<h4> --> <!-- 							<a href="select_page.jsp"><img src="images/back1.gif" -->
						<!-- 								width="100" height="32" border="0">回首頁</a> --> <!-- 						</h4> -->
					</td>
				</tr>
			</table>
			<!-- ========================================================== -->

			<!-- 			<FORM METHOD="post" ACTION="buylist.do"> -->
			<!-- 				<b>輸入訂單編號:</b> <input type="text" name="buylistId"> <input -->
			<!-- 					type="hidden" name="action" value="getOne_For_Display"> <input -->
			<!-- 					type="submit" value="送出"> -->
			<!-- 			</FORM> -->


			<%-- 			<jsp:useBean id="buylistSv" scope="page" --%>
			<%-- 				class="com.petlife.mall.service.impl.BuylistServiceImpl" /> --%>

			<!-- 			<FORM METHOD="post" ACTION="buylist.do"> -->
			<!-- 				<b>選擇訂單編號:</b> <select size="1" name="buylistId"> -->
			<%-- 					<c:forEach var="buylist" items="${buylistSv.getAllBuylists()}"> --%>
			<%-- 						<option value="${buylist.buylistId}">${buylist.buylistId} --%>
			<%-- 					</c:forEach> --%>
			<!-- 				</select> <input type="hidden" name="action" value="getOne_For_Display"> -->
			<!-- 				<input type="submit" value="送出"> -->
			<!-- 			</FORM> -->


			<!-- 			<FORM METHOD="post" ACTION="buylist.do"> -->
			<!-- 				<b>選擇會員ID:</b> <select size="1" name="buylistId"> -->
			<%-- 					<c:forEach var="buylist" items="${buylistSv.getAllBuylists()}"> --%>
			<%-- 						<option value="${buylist.buylistId}">${buylist.user.getUserId()} --%>
			<%-- 					</c:forEach> --%>
			<!-- 				</select> <input type="hidden" name="action" value="getOne_For_Display"> -->
			<!-- 				<input type="submit" value="送出"> -->
			<!-- 			</FORM> -->
			<!-- ========================================================== -->
			<table>
				<tr>
					<th>訂單編號</th>
					<th>會員ID</th>
					<th>賣家ID</th>
					<th>訂單狀態</th>
					<th>優惠碼名稱</th>
					<th>賣家評價星等</th>
					<th>賣家評價敘述</th>
					<th>賣家評價時間</th>
					<th>訂單金額</th>
					<th>訂單建立時間</th>
					<th>修改</th>
					<th>查看訂單細項</th>
<!-- 					<th>評價</th> -->
					<!-- 					<th>刪除</th> -->
				</tr>
				<%@ include file="page1.file"%>
				<c:forEach var="buylist" items="${list}" begin="<%=pageIndex%>"
					end="<%=pageIndex+rowsPerPage-1%>">

					<tr>
						<td>${buylist.buylistId}</td>
						<td>${buylist.user.userId}</td>
						<td>${buylist.seller.sellerId}</td>
						<%-- 						<td>${buylist.buylistState.buylistStateId}</td> --%>
						<td>${buylist.buylistState.buylistStateName}</td>
						<%-- 						<td>${buylist.coupon.couponId}</td> --%>
						<td>${buylist.coupon == null ? "未使用優惠碼" : buylist.coupon.couponName}</td>
						<td>${buylist.sellerRatingStars}</td>
						<td>${buylist.sellerEvaluateNarrative}</td>
						<td>${buylist.sellerEvaluateTime}</td>
						<td>${buylist.buylistAmount}</td>
						<td>${buylist.buylistDate}</td>
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/buylist/buylist.do"
								style="margin-bottom: 0px;">
								<input type="submit" value="修改"> <input type="hidden"
									name="buylistId" value="${buylist.buylistId}"> <input
									type="hidden" name="action" value="getOne_For_Update">
							</FORM>
						</td>
						<!-- 							測試=========== -->
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/buylist/buylist.do">
								<input type="submit" value="查看細項"><input type="hidden"
									name="buylistId" value="${buylist.buylistId}"> <input
									type="hidden" name="action" value="showBuylistDetails">
							</FORM>
						</td>
<!-- ================================================================== -->
<!-- 						<td> -->
<!-- 							<button class="btn-sm btn-warning btn_rate" -->
<!-- 								data-bs-toggle="modal" data-bs-target="#rate_order" -->
<%-- 								value="${buyList.buylistId}">評價</button> --%>
<!-- 								<form -->
<%-- 											action="<%=request.getContextPath()%>/buylist/buylist.do" --%>
<!-- 											method="post" id="rate_orderForm"> -->
<!-- 											<div class="modal fade" id="rate_order" tabindex="-1" -->
<!-- 												aria-labelledby="rateModalLabel" aria-hidden="true"> -->
<!-- 												<div -->
<!-- 													class="modal-dialog modal-dialog-centered modal-dialog-scrollable"> -->
<!-- 													<div class="modal-content"> -->
<!-- 														<div class="modal-header"> -->
<!-- 															<h5 class="modal-title" id="rateModalLabel">評價訂單</h5> -->
<!-- 															<button type="button" class="btn-close" -->
<!-- 																data-bs-dismiss="modal" aria-label="Close"></button> -->
<!-- 														</div> -->
<!-- 														<div class="row modal-body delete_box"> -->
<!-- 															<span id="verify_rateStar"></span> -->
<!-- 															<div class="col rounded star_block" id="delete_content"> -->
<!-- 																評價此訂單<br> <span class="star" data-star="1"><i -->
<!-- 																	class="fas fa-star"></i></span> <span class="star" -->
<!-- 																	data-star="2"><i class="fas fa-star"></i></span> <span -->
<!-- 																	class="star" data-star="3"><i -->
<!-- 																	class="fas fa-star"></i></span> <span class="star" -->
<!-- 																	data-star="4"><i class="fas fa-star"></i></span> <span -->
<!-- 																	class="star" data-star="5"><i -->
<!-- 																	class="fas fa-star"></i></span> -->
<!-- 															</div> -->
<!-- 															<div class="col-auto"> -->
<!-- 																<span id="verify_rateComment"></span> <label -->
<!-- 																	class="col-md-12" for="sellerEvaluateNarrative">評論：</label> <br> -->
<!-- 																<textarea class="col-md-12" id="sellerEvaluateNarrative" -->
<!-- 																	name="sellerEvaluateNarrative" rows="4" cols="80" -->
<!-- 																	placeholder="請輸入原因..."></textarea> -->
<!-- 															</div> -->
<!-- 														</div> -->

<!-- 														<div class="row modal-footer"> -->
<!-- 															<div class="col"></div> -->
<!-- 															<button type="submit" class="col-auto btn btn-danger">評價</button> -->
<!-- 															<button type="button" class="col-auto btn btn-secondary" -->
<!-- 																data-bs-dismiss="modal">取消</button> -->
<!-- 															<div class="col"></div> -->
<!-- 															<input type="hidden" name="action" -->
<!-- 																value="sellerRateBuylist"> <input type="hidden" -->
<%-- 																name="ratedSellerId" value=<%=seller.getSellerId()%>> --%>
<%-- 															<input type="hidden" name="buylistId"  value="${buylist.buylistId}"> <input --%>
<!-- 																type="hidden" name="sellerRatingStars"> -->
<!-- 														</div> -->
<!-- 													</div> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 										</form> -->
<!-- 						</td> -->
<!-- ================================================================== -->
						<!-- 							測試=========== -->
						<%-- <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/buylistdetails/buylistdetails.do"> --%>
						<!-- 				<b>選擇訂單編號ID:</b> <select size="1" name="buylistDetailsId"> -->
						<%-- 					<c:forEach var="buylistDetails" --%>
						<%-- 						items="${buylistDetailsSv.getAllBuylistDetailss()}"> --%>
						<%-- 						<option value="${buylistDetails.buylistDetailsId}">${buylistDetails.buylist.getBuylistId()} --%>
						<%-- 					</c:forEach> --%>
						<!-- 				</select> <input type="hidden" name="action" value="getOne_For_Display"> -->
						<!-- 				<input type="submit" value="送出"> -->
						<!-- 			</FORM> -->
						<!-- 							測試=========== -->
						<!-- 						<td> -->
						<!-- 							<FORM METHOD="post" -->
						<%-- 								ACTION="<%=request.getContextPath()%>/buylist/buylist.do" --%>
						<!-- 								style="margin-bottom: 0px;"> -->
						<!-- 								<input type="submit" value="刪除"> <input type="hidden" -->
						<%-- 									name="buylistId" value="${buylist.buylistId}"> <input --%>
						<!-- 									type="hidden" name="action" value="delete"> -->
						<!-- 							</FORM> -->
						<!-- 						</td> -->
					</tr>
				</c:forEach>
			</table>
			<%@ include file="page2.file"%>
			<script src="https://code.jquery.com/jquery-3.3.1.min.js"
				crossorigin="anonymous"></script>
			<script
				src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
				crossorigin="anonymous"></script>
			<script
				src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
				crossorigin="anonymous"></script>
			<script
				src="https://unpkg.com/bootstrap-table@1.15.5/dist/bootstrap-table.min.js"></script>
			<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

		</div>
		<!-- ./wrapper -->


		<!-- jQuery -->
		<script src="../plugins/jquery/jquery.min.js"></script>
		<!-- jQuery UI 1.11.4 -->
		<script src="../plugins/jquery-ui/jquery-ui.min.js"></script>
		<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
		<script>
			$.widget.bridge('uibutton', $.ui.button)
		</script>
		<!-- Bootstrap 4 -->
		<script src="../plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
		<!-- overlayScrollbars -->
		<script
			src="../plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
		<!-- AdminLTE App -->
		<script src="../dist/js/adminlte.js"></script>
<!-- ================================================================== -->
<script>
$("#rate_orderForm").submit(function (event) {
    let rateFlag = true;
    let ratedComment = $("#sellerEvaluateNarrative").val();
    let Star = $('input[name="sellerRatingStars"]').val();
    console.log(Star);
    console.log(ratedComment);

    if (Star < 1) {
        rateFlag = false;
        $("#verify_rateStar").html(`<font color='red'><b>請選擇評分分數!!</font>`);
    } else {
        $("#verify_rateStar").html("");
    }

    if (ratedComment == null || ratedComment == "") {
        rateFlag = false;
        $("#verify_rateComment").html(`<font color='red'><b>請輸入評論!!</font>`);
    } else {
        $("#verify_rateComment").html("");
    }

    if (rateFlag == false) {
        event.preventDefault();
    }
})

    $('.star').click(function () {
        var rating = $(this).attr('data-star');

        $('.star').removeClass('-on');

        $(this).prevAll('.star').addClass('-on');
        $(this).addClass('-on');
        $('input[name="sellerRatingStars"]').val(rating);
    });
</script>
<!-- ================================================================== -->
</body>

</html>