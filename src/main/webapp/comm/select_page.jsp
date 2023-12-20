<%@page import="com.petlife.mall.entity.Comm"%>
<%@page import="com.petlife.mall.service.impl.CommServiceImpl"%>
<%@page import="com.petlife.mall.service.CommService"%>
<%@page import="com.petlife.mall.entity.Comm"%>
<%-- <%@page import="com.petlife.admin.*"%> --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-TW">

<head>
<style>
table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
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
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>管理員後臺系統</title>

<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<!-- Bootstrap css -->
<link rel="stylesheet" href="../assets/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="../plugins/fontawesome-free/css/all.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="../dist/css/adminlte.min.css">
<!-- overlayScrollbars -->
<link rel="stylesheet"
	href="../plugins/overlayScrollbars/css/OverlayScrollbars.min.css">

<link rel="stylesheet"
	href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.css" />
<!-- my_css -->
<link rel="stylesheet" href="../dist/css/comm_management.css">
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
				<li class="nav-item d-none d-sm-inline-block">
					<!-- <a href="test.html" class="nav-link"></a> -->
				</li>
			</ul>
		</nav>
		<!-- /.navbar -->





		<!-- Main Sidebar Container -->
		<aside class="main-sidebar sidebar-dark-primary elevation-4">
			<!-- Brand Logo -->
			<a href="test.html" class="brand-link"> <img
				src="../dist/img/main_logo.png" alt="AdminLTE Logo"
				class="brand-image img-corners elevation-3 logo" style="opacity: .8">
				<span class="brand-text font-weight-light">PetLife後臺管理</span>
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
						<a href="#" class="d-block">OOO管理員，你好</a>
					</div>
				</div>

				<!-- Sidebar Menu -->
				<nav class="mt-2">
					<ul class="nav nav-pills nav-sidebar flex-column"
						data-widget="treeview" role="menu" data-accordion="false">
						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="fas fa-solid fa-users"></i>
								<p>會員管理</p>
						</a></li>
						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="fas fa-solid fa-newspaper"></i>
								<p>文章管理</p>
						</a></li>

						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="fas fa-solid fa-store"></i>
								<p>商品管理</p>
						</a></li>


						<li class="nav-item"><a href="select_page.jsp"
							class="nav-link"> <i class="fas fa-solid fa-percent"></i>
								<p>商品管理</p>
						</a></li>

						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="fas fa-ad"></i>
								<p>廣告管理</p>
						</a></li>

						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="fas fa-sign-out-alt"></i>
								<p>登出</p>
						</a></li>
					</ul>
				</nav>
				<!-- /.sidebar-menu -->
			</div>
			<!-- /.sidebar -->
		</aside>

		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1>商品管理</h1>
						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<li class="breadcrumb-item active">商品管理</li>
							</ol>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
			</section>

			<!-- Main content -->
			<table id="table-1">
				<tr>
					<td><h3>商品首頁</h3>
						<h4>Comm: Home</h4></td>
				</tr>
			</table>

			<h3>資料查詢:</h3>

			<!-- 錯誤表列 -->
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>

			<ul>
				<li><a href='listAllComm.jsp'>List</a> all Comms. <br>
				<br></li>


				<li>
					<FORM METHOD="post" ACTION="comm.do">
						<b>輸入商品編號:</b> <input type="text" name="commId"> <input
							type="hidden" name="action" value="getOne_For_Display"> <input
							type="submit" value="送出">
					</FORM>
				</li>

				<jsp:useBean id="commSvc" scope="page"
					class="com.petlife.mall.service.impl.CommServiceImpl" />
				<li>
					<FORM METHOD="post" ACTION="comm.do">
						<b>選擇商品編號:</b> <select size="1" name="commId">
							<c:forEach var="comm" items="${commSvc.getAll()}">
								<option value="${comm.commId}">${comm.commId}
							</c:forEach>
						</select> <input type="hidden" name="action" value="getOne_For_Display">
						<input type="submit" value="送出">
					</FORM>
				</li>

				<li>
					<FORM METHOD="post" ACTION="comm.do">
						<b>選擇商品名稱:</b> <select size="1" name="commId">
							<c:forEach var="comm" items="${commSvc.getAll()}">
								<option value="${comm.commId}">${comm.commName}
							</c:forEach>
						</select> <input type="hidden" name="action" value="getOne_For_Display">
						<input type="submit" value="送出">
					</FORM>
				</li>
			</ul>


			<h3>商品管理</h3>

			<ul>
				<li><a href='addComm.jsp'>Add</a> a new Comm.</li>
			</ul>




			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<!-- jQuery -->
		<script src="../assets/js/jquery.min.js"></script>

		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
		<script
			src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.js"></script>
		<!-- overlayScrollbars -->
		<script
			src="../plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
		<!-- AdminLTE App -->
		<script src="../dist/js/adminlte.js"></script>
		<script src="../dist/js/admin_comm_management.js"></script>
</body>

</html>