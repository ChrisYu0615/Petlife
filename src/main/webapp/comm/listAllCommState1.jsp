<%@page import="com.petlife.mall.service.impl.CommServiceImpl"%>
<%@page import="com.petlife.mall.entity.Comm"%>
<%@page import="com.petlife.seller.entity.Seller"%>
<%@page import="com.petlife.mall.service.CommService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@page import="com.petlife.admin.*"%>

<%
Seller seller = (Seller) session.getAttribute("seller");
CommService commSvc = new CommServiceImpl();
List<Comm> list = commSvc.getCommsByState(1,seller.getSellerId());
pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html lang="zh-TW">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>缺貨中商品</title>
<!-- 讓Loading, please wait消失 -->
<link rel="stylesheet" href="/buylist.css">

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
img.brand-image{
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
			<a href="<%=request.getContextPath() %>/buylist/listAllBuylist.jsp" class="brand-link"> <img
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
						<li class="nav-item"><a href="#"
							class="nav-link active"> <i
								class="nav-icon fas fa-tachometer-alt"></i>
								<p>
									訂單管理 <i class="right fas fa-angle-left"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a
									href="../buylist/listAllBuylist.jsp" class="nav-link active">
										<i class="far fa-circle nav-icon"></i>
										<p>所有訂單</p>
								</a></li>
								<li class="nav-item"><a
									href="../buylist/listAllBuylistState0.jsp" class="nav-link">
										<i class="far fa-circle nav-icon"></i>
										<p>0:待付款</p>
								</a></li>
								<li class="nav-item"><a
									href="../buylist/listAllBuylistState1.jsp" class="nav-link">
										<i class="far fa-circle nav-icon"></i>
										<p>1:待出貨</p>
								</a></li>
								<li class="nav-item"><a
									href="../buylist/listAllBuylistState2.jsp" class="nav-link">
										<i class="far fa-circle nav-icon"></i>
										<p>2:運送中</p>
								</a></li>
								<li class="nav-item"><a
									href="../buylist/listAllBuylistState3.jsp" class="nav-link">
										<i class="far fa-circle nav-icon"></i>
										<p>3:訂單已完成</p>
								</a></li>
								<li class="nav-item"><a
									href="../buylist/listAllBuylistState4.jsp" class="nav-link">
										<i class="far fa-circle nav-icon"></i>
										<p>4:訂單已取消</p>
								</a></li>
								<li class="nav-item"><a
									href="../buylist/listAllBuylistState5.jsp" class="nav-link">
										<i class="far fa-circle nav-icon"></i>
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

						<li class="nav-item menu-open"><a href="#"
							class="nav-link active"> <i class="nav-icon fas far fa-copy"></i>
								<p>
									商品管理 <i class="right fas fa-angle-left"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="addComm.jsp"
									class="nav-link active"> <i class="far fa-circle nav-icon"></i>
										<p>上架商品</p>
								</a></li>
								<li class="nav-item"><a href="listAllComm.jsp"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>所有商品</p>
								</a></li>
								<li class="nav-item"><a href="listAllCommState0.jsp"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>0:販售中</p>
								</a></li>
								<li class="nav-item"><a href="listAllCommState1.jsp"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>1:缺貨中</p>
								</a></li>
								<li class="nav-item"><a href="listAllCommState2.jsp"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>2:下架</p>
								</a></li>
								<li class="nav-item"><a href="listAllCommState3.jsp"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
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
							<h1 class="m-0">商品管理</h1>
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

			<div class="card-header">
				<a href='addComm.jsp'><button type="button"
						class="btn btn-primary" id="btn_addcoupon" data-bs-toggle="modal"
						data-bs-target="#add_coupon">上架商品</button></a>
			</div>
			<table id="table-1">
				<tr>
					<td>
						<h3>所有商品資料</h3>
						<h4>
							<!-- 							<a href="select_page.jsp"><img src="images/back1.gif" -->
							<!-- 								width="100" height="32" border="0">回首頁</a> -->
						</h4>
					</td>
				</tr>
			</table>
			<!-- ========================================================== -->

<!-- 			<FORM METHOD="post" ACTION="comm.do"> -->
<!-- 				<b>輸入商品編號:</b> <input type="text" name="commId"> <input -->
<!-- 					type="hidden" name="action" value="getOne_For_Display"> <input -->
<!-- 					type="submit" value="送出"> -->
<!-- 			</FORM> -->

<%-- 			<jsp:useBean id="commSv" scope="page" --%>
<%-- 				class="com.petlife.mall.service.impl.CommServiceImpl" /> --%>
<!-- 			<FORM METHOD="post" ACTION="comm.do"> -->
<!-- 				<b>選擇商品編號:</b> <select size="1" name="commId"> -->
<%-- 					<c:forEach var="comm" items="${commSv.getAll()}"> --%>
<%-- 						<option value="${comm.commId}">${comm.commId} --%>
<%-- 					</c:forEach> --%>
<!-- 				</select> <input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 				<input type="submit" value="送出"> -->
<!-- 			</FORM> -->

<!-- 			<FORM METHOD="post" ACTION="comm.do"> -->
<!-- 				<b>選擇商品名稱:</b> <select size="1" name="commId"> -->
<%-- 					<c:forEach var="comm" items="${commSv.getAll()}"> --%>
<%-- 						<option value="${comm.commId}">${comm.commName} --%>
<%-- 					</c:forEach> --%>
<!-- 				</select> <input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 				<input type="submit" value="送出"> -->
<!-- 			</FORM> -->
			<!-- ========================================================== -->
			<table>
				<tr>
					<th>商品ID</th>
					<th>賣家ID</th>
					<th>商品名稱</th>
					<th>商品描述</th>
					<th>商品狀態</th>
					<th>上架時間</th>
					<th>商品縮圖</th>
					<th>商品分類</th>
					<th>商品庫存量</th>
					<th>商品價格</th>
					<th>商品優惠價</th>
					<th>瀏覽數</th>
					<th>修改</th>
					<!-- 					<th>刪除</th> -->
				</tr>
				<%@ include file="page1.file"%>
				<c:forEach var="comm" items="${list}" begin="<%=pageIndex%>"
					end="<%=pageIndex+rowsPerPage-1%>">

					<tr>
						<td>${comm.commId}</td>
						<td>${comm.seller.sellerId}</td>
						<td>${comm.commName}</td>
						<td>${comm.commDesc}</td>
						<td>${
								    comm.commState == 0 ? '販售中' :
								    comm.commState == 1 ? '缺貨中' :
								    comm.commState == 2 ? '下架' :
								    comm.commState == 3 ? '違規下架' :
								    '未知狀態'
								  }
						</td>
						<%-- 						<td>${comm.commState}</td> --%>
						<td>${comm.listDatetime}</td>
						<td><img
							src="<%=request.getContextPath()%>/comm/DBJPGReader?commId=${comm.commId}"
							width="100px"></td>
						<td>${
								        comm.commCat.commCatId == 1000 ? '貓咪飼料' :
								        comm.commCat.commCatId == 1001 ? '貓咪主食罐' :
								        comm.commCat.commCatId == 1002 ? '貓咪副食罐' :
								        comm.commCat.commCatId == 1003 ? '貓咪零食' :
								        comm.commCat.commCatId == 1004 ? '貓咪用品' :
								        comm.commCat.commCatId == 2000 ? '狗狗飼料' :
								        comm.commCat.commCatId == 2001 ? '狗狗主食罐' :
								        comm.commCat.commCatId == 2002 ? '狗狗副食罐' :
								        comm.commCat.commCatId == 2003 ? '狗狗零食' :
								        comm.commCat.commCatId == 2004 ? '狗狗用品' :
								        '未知分類'
								    }
						</td>
						<%-- 						<td>${comm.commCat.commCatId}</td> --%>
						<td>${comm.commStock}</td>
						<td>${comm.commPrice}</td>
						<td>${comm.commOnsalePrice}</td>
						<td>${comm.commViewCount}</td>
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/comm/comm.do"
								style="margin-bottom: 0px;">
								<input type="submit" value="修改"> <input type="hidden"
									name="commId" value="${comm.commId}"> <input
									type="hidden" name="action" value="getOne_For_Update">
							</FORM>
						</td>
						<!-- 						<td> -->
						<!-- 							<FORM METHOD="post" -->
						<%-- 								ACTION="<%=request.getContextPath()%>/comm/comm.do" --%>
						<!-- 								style="margin-bottom: 0px;"> -->
						<!-- 								<input type="submit" value="刪除"> <input type="hidden" -->
						<%-- 									name="commId" value="${comm.commId}"> <input --%>
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
</body>

</html>