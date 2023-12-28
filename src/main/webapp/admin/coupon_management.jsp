<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.petlife.admin.entity.Admin"%>
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
<link rel="stylesheet" href="../dist/css/coupon_management.css">
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
			<a href="<%=request.getContextPath()%>/user/user.do?action=getAll" class="brand-link"> <img
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
						<span class="d-block" id="admin_name"><%=admin.getAdminNickname()%>管理員，你好</span>
					</div>
				</div>

				<!-- Sidebar Menu -->
				<nav class="mt-2">
					<ul class="nav nav-pills nav-sidebar flex-column"
						data-widget="treeview" role="menu" data-accordion="false">

						<li class="nav-item"><a
							href="<%=request.getContextPath()%>/admin/modify_admin_profile.jsp"
							class="nav-link"> <i class="fas fa-solid fa-address-card"></i>
								<p>修改管理員資料</p>
						</a></li>   	

          				<li class="nav-item">
          				  <a href="#" class="nav-link">
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
							class="nav-link active"> <i class="fas fa-solid fa-percent"></i>
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
							<h1>優惠碼管理</h1>
						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/user/user.do?action=getAll">Home</a></li>
								<li class="breadcrumb-item active">優惠碼管理</li>
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
									<button type="button" class="btn btn-primary"
										id="btn_addcoupon" data-bs-toggle="modal"
										data-bs-target="#add_coupon">新增優惠碼</button>
								</div>
								<!-- /.card-header -->
								<div class="card-body table">
									<table id="myTable" class="display">
										<thead>
											<tr>
												<th>優惠碼編號</th>
												<th>優惠碼名稱</th>
												<th>優惠碼敘述</th>
												<th>使用條件</th>
												<th>折扣金額</th>
												<th>優惠碼狀態</th>
												<th>開始日期</th>
												<th>結束日期</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="coupon" items="${getAllCoupons}">
												<tr>
													<td>${coupon.couponId}</td>
													<td>${coupon.couponName}</td>
													<td>${coupon.couponContent}</td>
													<td>${coupon.conditionsOfUse}</td>
													<td>${coupon.discountAmount}</td>
													<td>${coupon.couponState==true?"上架中" : "已下架"}</td>
													<td><fmt:formatDate value="${coupon.startDate}"
															pattern="yyyy-MM-dd" /></td>
													<td><fmt:formatDate value="${coupon.endDate}"
															pattern="yyyy-MM-dd" /></td>
													<td><button class="btn-sm btn-primary btn_check"
															data-bs-toggle="modal" data-bs-target="#check_coupon"
															value="${coupon.couponId}">查看</button></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>

									<!-- 新增優惠碼 -->
									<form action="<%=request.getContextPath()%>/coupon/coupon.do"
										method="post" id="add_couponForm">
										<div class="modal fade" id="add_coupon" tabindex="-1"
											aria-labelledby="checkModalLabel" aria-hidden="true">
											<div
												class="modal-dialog modal-l modal-dialog-scrollable modal-dialog-centered">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title" id="checkModalLabel">新增優惠碼資料</h5>
														<button type="button" class="btn-close"
															data-bs-dismiss="modal" aria-label="Close"></button>
													</div>
													<div class="row modal-body">
														<div class="form-group mb-3">
															<label for="new_coupon_name" class="form-label">優惠碼名稱
															</label> <input type="text" class="form-control"
																id="new_coupon_name" name="coupon_name"
																placeholder="請輸入優惠碼名稱">
															<span id="verify_new_coupon_name"></span>
														</div>

														<div class="form-group mb-3">
															<label for="new_coupon_content" class="form-label">優惠碼敘述
															</label> <input type="text" class="form-control"
																id="new_coupon_content" name="coupon_content"
																placeholder="請輸入優惠碼敘述">
															<span id="verify_new_coupon_content"></span>
														</div>

														<div class="form-group mb-3">
															<label for="new_coupon_restrict" class="form-label">使用條件
															</label> <input type="text" class="form-control"
																id="new_coupon_restrict" name="coupon_restrict"
																placeholder="請輸入使用條件">
															<span id="verify_new_coupon_restrict"></span>
														</div>

														<div class="form-group mb-3">
															<label for="new_coupon_amount" class="form-label">折扣金額
															</label> <input type="text" class="form-control"
																id="new_coupon_amount" name="coupon_amount"
																placeholder="請輸入折扣金額">
															<span id="verify_new_coupon_amount"></span>
														</div>

														<div class="form-group mb-3">
															<label for="new_coupon_stardate" class="form-label">開始日期
															</label> <input type="date" class="form-control"
																id="new_coupon_stardate" name="coupon_stardate"
																placeholder="選擇開始日期">
															<span id="verify_new_coupon_stardate"></span>
														</div>

														<div class="form-group mb-3">
															<label for="new_coupon_enddate" class="form-label">結束日期
															</label> <input type="date" class="form-control"
																id="new_coupon_enddate" name="coupon_enddate"
																placeholder="選擇結束日期">
															<span id="verify_new_coupon_enddate"></span>
														</div>
													</div>

													<div class="row modal-footer">
														<div class="col"></div>
														<button type="submit" class="col-auto btn btn-primary"
															id="btn_save">修改</button>
														<button type="button" class="col-auto btn btn-secondary"
															data-bs-dismiss="modal">取消</button>
														<div class="col"></div>
														<input type=hidden name="action" value="addCoupon">
													</div>
												</div>
											</div>
										</div>
									</form>

									<!-- 修改優惠碼 -->
									<form action="<%=request.getContextPath()%>/coupon/coupon.do" method="post" id="modify_couponForm">
										<div class="modal fade" id="check_coupon" tabindex="-1"
											aria-labelledby="modifyModalLabel" aria-hidden="true">
											<div
												class="modal-dialog modal-l modal-dialog-scrollable modal-dialog-centered">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title" id="modifyModalLabel">修改優惠碼資料</h5>
														<button type="button" class="btn-close"
															data-bs-dismiss="modal" aria-label="Close"></button>
													</div>
													<div class="row modal-body">
														<div class="form-group mb-3">
															<label for="coupon_id" class="form-label">優惠碼編號 </label>
															<input type="text" class="form-control" id="coupon_id"
																name="coupon_id" placeholder="請輸入優惠碼編號" readonly>
														</div>

														<div class="form-group mb-3">
															<label for="coupon_name" class="form-label">優惠碼名稱
															</label> <input type="text" class="form-control" id="coupon_name"
																name="coupon_name" placeholder="請輸入優惠碼名稱">
															<span id="verify_coupon_name"></span>
														</div>

														<div class="form-group mb-3">
															<label for="coupon_content" class="form-label">優惠碼敘述
															</label> <input type="text" class="form-control"
																id="coupon_content" name="coupon_content"
																placeholder="請輸入優惠碼敘述">
															<span id="verify_coupon_content"></span>
														</div>

														<div class="form-group mb-3">
															<label for="coupon_restrict" class="form-label">使用條件
															</label> <input type="text" class="form-control"
																id="coupon_restrict" name="coupon_restrict"
																placeholder="請輸入使用條件">
															<span id="verify_coupon_restrict"></span>
														</div>

														<div class="form-group mb-3">
															<label for="coupon_amount" class="form-label">折扣金額
															</label> <input type="text" class="form-control"
																id="coupon_amount" name="coupon_amount"
																placeholder="請輸入折扣金額">
															<span id="verify_coupon_amount"></span>
														</div>

														<div class="form-group mb-3">
															<label for="" class="form-label">優惠碼狀態 </label><br>
															<div class="form-check form-check-inline">
																<input class="form-check-input" type="radio"
																	name="coupon_state" id="launched" value="true">
																<label class="form-check-label" for="launched">啟用</label>
															</div>

															<div class="form-check form-check-inline">
																<input class="form-check-input" type="radio"
																	name="coupon_state" id="unlaunched" value="false">
																<label class="form-check-label" for="unlaunched">未啟用</label>
															</div>
														</div>

														<div class="form-group mb-3">
															<label for="coupon_stardate" class="form-label">開始日期
															</label> <input type="date" class="form-control"
																id="coupon_stardate" name="coupon_stardate"
																placeholder="選擇開始日期">
															<span id="verify_coupon_stardate"></span>
														</div>

														<div class="form-group mb-3">
															<label for="coupon_enddate" class="form-label">結束日期
															</label> <input type="date" class="form-control"
																id="coupon_enddate" name="coupon_enddate"
																placeholder="選擇結束日期">
															<span id="verify_coupon_enddate"></span>
														</div>
													</div>

													<div class="row modal-footer">
														<div class="col"></div>
														<button type="submit" class="col-auto btn btn-danger"
															id="btn_delete">儲存</button>
														<button type="button" class="col-auto btn btn-secondary"
															data-bs-dismiss="modal">取消</button>
														<div class="col"></div>
                                                        <input type="hidden" name="action" value="updateCoupon">
														<input type="hidden" name="couponId" id="couponId">
													</div>
												</div>
											</div>
										</div>
									</form>

								</div>
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
		<script src="../dist/js/admin_coupon_management.js"></script>
</body>

</html>