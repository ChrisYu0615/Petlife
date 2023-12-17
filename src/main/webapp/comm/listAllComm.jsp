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
<style>
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
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>�޲z����O�t��</title>

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

		<!-- �W��Navbar -->
		<nav
			class="main-header navbar navbar-expand navbar-white navbar-light">
			<!-- �W�襪��Left navbar links -->
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
				<span class="brand-text font-weight-light">PetLife��O�޲z</span>
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
						<a href="#" class="d-block">OOO�޲z���A�A�n</a>
					</div>
				</div>

				<!-- Sidebar Menu -->
				<nav class="mt-2">
					<ul class="nav nav-pills nav-sidebar flex-column"
						data-widget="treeview" role="menu" data-accordion="false">
						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="fas fa-solid fa-users"></i>
								<p>�|���޲z</p>
						</a></li>
						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="fas fa-solid fa-newspaper"></i>
								<p>�峹�޲z</p>
						</a></li>

						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="fas fa-solid fa-store"></i>
								<p>�ӫ~�޲z</p>
						</a></li>


						<li class="nav-item"><a href="select_page.jsp"
							class="nav-link"> <i class="fas fa-solid fa-percent"></i>
								<p>�u�f�X�޲z</p>
						</a></li>

						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="fas fa-ad"></i>
								<p>�s�i�޲z</p>
						</a></li>

						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="fas fa-sign-out-alt"></i>
								<p>�n�X</p>
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
							<h1>�u�f�X�޲z</h1>
						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<li class="breadcrumb-item active">�u�f�X�޲z</li>
							</ol>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-12">
							<div class="card">
								<div class="card-header">
									<a href='addcomm.jsp'><button type="button"
											class="btn btn-primary" id="btn_addcomm"
											data-bs-toggle="modal" data-bs-target="#add_comm">�s�W�u�f�X</button></a>
								</div>
								<!-- /.card-header -->
								<table id="table-1">
									<tr>
										<td>
											<h3>�Ҧ��u�f����</h3>
											<h4>
												<a href="select_page.jsp"><img src="images/back1.gif"
													width="100" height="32" border="0">�^����</a>
											</h4>
										</td>
									</tr>
								</table>

								<table>
									<tr>
										<th>�ӫ~ID</th>
										<th>��aID</th>
										<th>�ӫ~�W��</th>
										<th>�ӫ~�y�z</th>
										<th>�ӫ~���A</th>
										<th>�W�[�ɶ�</th>
										<th>�ӫ~�Y��</th>
										<th>�ӫ~����ID</th>
										<th>�ӫ~�w�s�q</th>
										<th>�ӫ~����</th>
										<th>�ӫ~�u�f��</th>
										<th>�s����</th>
										<th>�ק�</th>
										<th>�R��</th>
									</tr>
									<%@ include file="page1.file"%>
									<c:forEach var="comm" items="${list}" begin="<%=pageIndex%>"
										end="<%=pageIndex+rowsPerPage-1%>">

										<tr>
											<td>${comm.commId}</td>
											<td>${comm.seller.sellerId}</td>
											<td>${comm.commName}</td>
											<td>${comm.commDesc}</td>
											<td>${comm.commState}</td>
											<td>${comm.listDatetime}</td>
											<td>${comm.commImg}</td>
											<td>${comm.commCatId}</td>
											<td>${comm.commStock}</td>
											<td>${comm.commPrice}</td>
											<td>${comm.commOnsalePrice}</td>
											<td>${comm.commViewCount}</td>
											<td>
												<FORM METHOD="post"
													ACTION="<%=request.getContextPath()%>/comm/comm.do"
													style="margin-bottom: 0px;">
													<input type="submit" value="�ק�"> <input
														type="hidden" name="commId" value="${comm.commId}">
													<input type="hidden" name="action"
														value="getOne_For_Update">
												</FORM>
											</td>
											<td>
												<FORM METHOD="post"
													ACTION="<%=request.getContextPath()%>/comm/comm.do"
													style="margin-bottom: 0px;">
													<input type="submit" value="�R��"> <input
														type="hidden" name="commId" value="${comm.commId}">
													<input type="hidden" name="action" value="delete">
												</FORM>
											</td>
										</tr>
									</c:forEach>
								</table>
								<%@ include file="page2.file"%>
								<!-- /.card-body -->
							</div>
							<!-- /.card -->
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</section>
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