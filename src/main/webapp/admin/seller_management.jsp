<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.petlife.admin.entity.Admin"%>
<%@page import="com.petlife.user.entity.User"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
Admin admin = (Admin) session.getAttribute("admin");
%>

<!DOCTYPE html>
<html lang="zh-TW">

<head>
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
<!-- Favicon -->
<link rel="icon" type="image/png" href="../assets/img/favicon.png">
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.css" />
<!-- my_css -->
<link rel="stylesheet" href="../dist/css/admin_member.css">
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
			<a href="<%=request.getContextPath()%>/user/user.do?action=getAll"
				class="brand-link"> <img src="../dist/img/main_logo.png"
				alt="AdminLTE Logo" class="brand-image img-corners elevation-3 logo"
				style="opacity: .8"> <span
				class="brand-text font-weight-light">PetLife後臺管理</span>
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
						<span class="d-block" id="admin_name"><%=admin.getAdminNickname()%>管理員，你好</span>
					</div>
				</div>

				<!-- Sidebar Menu -->
				<nav class="mt-2">
					<ul class="nav nav-pills nav-sidebar flex-column"
						data-widget="treeview" role="menu" data-accordion="false">

          				<li class="nav-item menu-open">
          				  <a href="#" class="nav-link active">
          				    <i class="fas fa-solid fa-users"></i>
          				    <p>
          				      會員管理
          				      <i class="right fas fa-angle-left"></i>
          				    </p>
          				  </a>
          				  <ul class="nav nav-treeview">
          				    <li class="nav-item">
          				      <a href="<%=request.getContextPath()%>/user/user.do?action=getAll" class="nav-link">
          				        <i class="far fa-circle nav-icon"></i>
          				        <p>一般會員</p>
          				      </a>
          				    </li>
          				    <li class="nav-item">
          				      <a href="<%=request.getContextPath()%>/seller/seller.do?action=getAll&condition=verified" class="nav-link">
          				        <i class="far fa-circle nav-icon"></i>
          				        <p>賣家會員</p>
          				      </a>
          				    </li>
          				    <li class="nav-item">
          				      <a href="<%=request.getContextPath()%>/shelter/shelter.do?action=getAll&condition=verified" class="nav-link">
          				        <i class="far fa-circle nav-icon"></i>
          				        <p>收容所會員</p>
          				      </a>
          				    </li>
          				    <li class="nav-item">
          				      <a href="<%=request.getContextPath()%>/admin/admin.do?action=getAllMembers&condition=unverified" class="nav-link">
          				        <i class="far fa-circle nav-icon"></i>
          				        <p>待審核會員</p>
          				      </a>
          				    </li>							
          				  </ul>
          				</li>		

          				<li class="nav-item">
          				  <a href="#" class="nav-link">
          				    <i class="fas fa-solid fa-newspaper"></i>
          				    <p>
          				      文章管理
          				      <i class="right fas fa-angle-left"></i>
          				    </p>
          				  </a>
          				  <ul class="nav nav-treeview">
          				    <li class="nav-item">
          				      <a href="<%=request.getContextPath()%>/art/art.do?action=getAllArticles" class="nav-link">
          				        <i class="far fa-circle nav-icon"></i>
          				        <p>文章列表</p>
          				      </a>
          				    </li>
          				    <li class="nav-item">
          				      <a href="<%=request.getContextPath()%>/reportForum/reportForum.do?action=getAllReports&condition=unReply" class="nav-link">
          				        <i class="far fa-circle nav-icon"></i>
          				        <p>待處理檢舉</p>
          				      </a>
          				    </li>
          				    <li class="nav-item">
          				      <a href="<%=request.getContextPath()%>/reportForum/reportForum.do?action=getAllReports&condition=replied" class="nav-link">
          				        <i class="far fa-circle nav-icon"></i>
          				        <p>已處理檢舉</p>
          				      </a>
          				    </li>							
          				  </ul>
          				</li>						

						<li class="nav-item"><a
							href="<%=request.getContextPath()%>/coupon/coupon.do?action=getAllCoupons"
							class="nav-link"> <i class="fas fa-solid fa-percent"></i>
								<p>優惠碼管理</p>
						</a></li>

						<li class="nav-item"><a
							href="<%=request.getContextPath()%>/advertisement/advertisement.do?action=getAllAdvertisements"
							class="nav-link"> <i class="fas fa-ad"></i>
								<p>廣告管理</p>
						</a></li>

						<li class="nav-item"><a href="<%=request.getContextPath()%>/logout/logout.do" class="nav-link" id="logout"> <i
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
							<h1>會員管理</h1>
						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/user/user.do?action=getAll">Home</a></li>
								<li class="breadcrumb-item active">會員管理</li>
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
									<ul class="list-group list-group-horizontal-sm">
										<li class="list-group-item"><a
											href="<%=request.getContextPath()%>/user/user.do?action=getAll">一般會員</a></li>
										<li class="list-group-item"><a
											href="<%=request.getContextPath()%>/seller/seller.do?action=getAll&condition=verified">賣家會員</a></li>
										<li class="list-group-item"><a
											href="<%=request.getContextPath()%>/shelter/shelter.do?action=getAll&condition=verified">收容所會員</a></li>
										<li class="list-group-item"><a
											href="<%=request.getContextPath()%>/admin/admin.do?action=getAllMembers&condition=unverified">待審核會員</a></li>
									</ul>
									<!-- <h3 class="card-title">DataTable with minimal features & hover style</h3> -->
								</div>
								<!-- /.card-header -->
								<div class="card-body table">
									<table id="myTable" class="display">
										<thead>
											<tr>
												<th>會員編號</th>
												<th>會員帳號</th>
												<th>會員身分</th>
												<th>會員姓名</th>
												<th>會員電話</th>
												<th>帳號狀態</th>
												<th>註冊時間</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="seller" items="${getAllSellers}">
												<tr>
													<td>${seller.sellerId}</td>
													<td>${seller.sellerAcct}</td>
													<td>${seller.acctType.acctType}</td>
													<td>${seller.sellerName}</td>
													<td>${seller.phoneNum}</td>
													<td>${seller.acctState.acctStateType}</td>
													<td><fmt:formatDate value="${seller.sellerCreateTime}"
															pattern="yyyy-MM-dd HH:mm:ss" /></td>
													<td>
														<form
															action="<%=request.getContextPath()%>/seller/seller.do"
															method="post">
															<c:choose>
																<c:when test="${seller.acctState.acctStateType eq '停權'}">
																	<button class="btn-sm btn-warning btn_recover"
																		type="button" data-bs-toggle="modal"
																		data-bs-target="#resume_member"
																		value="${seller.sellerId}">解除</button>
																</c:when>
																<c:otherwise>
																	<button class="btn-sm btn-danger btn_suspend"
																		type="button" data-bs-toggle="modal"
																		data-bs-target="#suspend_member"
																		value="${seller.sellerId}">停權</button>
																</c:otherwise>
															</c:choose>
														</form>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
								<!-- /.card-body -->

									<!-- 停權 -->
									<form action="<%=request.getContextPath()%>/seller/seller.do"
										method="post">
										<div class="modal fade" id="suspend_member" tabindex="-1"
											aria-labelledby="suspendModalLabel" aria-hidden="true">
											<div class="modal-dialog modal-dialog-centered">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title" id="suspendModalLabel">停權會員</h5>
														<button type="button" class="btn-close"
															data-bs-dismiss="modal" aria-label="Close"></button>
													</div>
													<div class="row modal-body delete_box">
														<div class="col rounded" id="suspend_content">
															是否停權該會員?</div>
													</div>
													<div class="row modal-footer">
														<div class="col"></div>
														<button type="submit" class="col-auto btn btn-danger"
															id="btn_delete">確認</button>
														<button type="button" class="col-auto btn btn-secondary"
															data-bs-dismiss="modal">取消</button>
														<input type="hidden" id="suspendMemberId" name="memberId">
														<input type="hidden" name="action" value="modifySellerAcctState">
														<input type="hidden" name="modify" value="suspendSeller">
														<div class="col"></div>
													</div>
												</div>
											</div>
										</div>
									</form>

									<!-- 解除停權 -->
									<form action="<%=request.getContextPath()%>/seller/seller.do"
										method="post">
										<div class="modal fade" id="resume_member" tabindex="-1"
											aria-labelledby="resumeModalLabel" aria-hidden="true">
											<div class="modal-dialog modal-dialog-centered">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title" id="resumeModalLabel">解除停權</h5>
														<button type="button" class="btn-close"
															data-bs-dismiss="modal" aria-label="Close"></button>
													</div>
													<div class="row modal-body delete_box">
														<div class="col rounded" id="resume_content">
															是否要解除停權該會員?</div>
													</div>
													<div class="row modal-footer">
														<div class="col"></div>
														<button type="submit" class="col-auto btn btn-danger"
															id="btn_delete">確認</button>
														<button type="button" class="col-auto btn btn-secondary"
															data-bs-dismiss="modal">取消</button>
														<input type="hidden" id="recoverMemberId" name="memberId">
														<input type="hidden" name="action" value="modifySellerAcctState">
														<input type="hidden" name="modify" value="recoverSeller">
														<div class="col"></div>
													</div>
												</div>
											</div>
										</div>
									</form>

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
		<script src="../dist/js/member_management.js"></script>
</body>

</html>