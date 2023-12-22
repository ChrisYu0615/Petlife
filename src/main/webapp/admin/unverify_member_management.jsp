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

						<li class="nav-item"><a href="<%=request.getContextPath()%>/user/user.do?action=getAll" class="nav-link"> <i
								class="fas fa-solid fa-users"></i>
								<p>會員管理</p>
						</a></li>

						<li class="nav-item"><a
							href="<%=request.getContextPath()%>/art/art.do?action=getAllArticles"
							class="nav-link">
								<i class="fas fa-solid fa-newspaper"></i>
								<p>文章管理</p>
						</a></li>

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
														<button class="btn-sm btn-primary btn_sellerCheck"
															type="button" data-bs-toggle="modal"
															data-bs-target="#verify_seller"
															value="${seller.sellerId}">查看</button>
													</td>
												</tr>
											</c:forEach>

											<c:forEach var="shelter" items="${getAllShelters}">
												<tr>
													<td>${shelter.shelterId}</td>
													<td>${shelter.shelterAcct}</td>
													<td>${shelter.acctType.acctType}</td>
													<td>${shelter.shelterName}</td>
													<td>${shelter.shelterPhoneNum}</td>
													<td>${shelter.acctState.acctStateType}</td>
													<td><fmt:formatDate
															value="${shelter.shelterCreateTime}"
															pattern="yyyy-MM-dd HH:mm:ss" /></td>
													<td>
														<button class="btn-sm btn-primary btn_shelterCheck"
															type="button" data-bs-toggle="modal"
															data-bs-target="#verify_shelter"
															value="${shelter.shelterId}">查看</button>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
								<!-- /.card-body -->

									<!-- 查看資料(賣家)，使用ajax撈資料-->
									<form
										action="<%=request.getContextPath()%>/seller/seller.do?action=verify_Seller"
										method="post" id="sellerData_form">
										<div class="modal fade" id="verify_seller" tabindex="-1"
											aria-labelledby="resumeModalLabel" aria-hidden="true">
											<div
												class="modal-dialog modal-xl modal-dialog-scrollable modal-dialog-centered">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title" id="resumeModalLabel">申請會員資料(賣家)</h5>
														<button type="button" class="btn-close"
															data-bs-dismiss="modal" aria-label="Close"></button>
													</div>
													<div class="row modal-body">
														<div class="form-group mb-3">
															<label for="useraccount" class="form-label">會員帳號(Email)
															</label> <input type="email" class="form-control"
																id="useraccount" name="useraccount"
																placeholder="請輸入Email" disabled>
														</div>

														<div class="form-group mb-3">
															<label for="account_category" class="form-label">申請身分
															</label> <input type="text" class="form-control"
																id="account_category" name="account_category"
																placeholder="請輸入身分" disabled>
														</div>

														<div class="form-group mb-3">
															<label for="username" class="form-label">姓名 </label> <input
																type="text" class="form-control" id="username"
																name="username" placeholder="請輸入姓名" disabled>
														</div>

														<div class="form-group mb-3">
															<label for="shopname" class="form-label">賣場名稱</label> <input
																type="text" class="form-control" id="shopname"
																name="shopname" placeholder="請輸入暱稱" disabled>
														</div>

														<div class="form-group mb-3">
															<label for="birthdate" class="form-label">出生年月日 </label>
															<div class="input-group">
																<input type="date" class="form-control" id="birthdate"
																	name="birthdate" placeholder="選擇出生日期" disabled>
																</span>
															</div>
														</div>

														<div class="form-group mb-3">
															<label for="phone" class="form-label">手機號碼 </label> <input
																type="tel" class="form-control" id="phone" name="phone"
																placeholder="請輸入手機號碼" disabled>
														</div>

														<div class="form-group mb-3">
															<label for="address" class="form-label">地址 </label> <input
																type="text" class="form-control" id="address"
																name="address" placeholder="請輸入詳細地址" disabled>
														</div>

														<div class="row mb-3">
															<div class="col-5">
																<label for="bankcode" class="form-label">金融機構代號
																</label> <input type="text" class="form-control" id="bankcode"
																	name="bankcode" placeholder="金融機構代號" disabled>
															</div>
														</div>

														<div class="mb-3">
															<label for="bankaccount" class="form-label">金融帳號
															</label> <input type="text" class="form-control" id="bankaccount"
																name="bankaccount" placeholder="請輸入金融帳號" disabled>
														</div>

														<div class="row">
															<div class="form-group col-md-6">
																<label for="idcard-front" class="form-label">身分證正面
																</label> <br> <img class="image-preview"
																	id="idcard-front-preview" src="" alt=""
																	style="width: 300px;">
															</div>

															<div class="form-group col-md-6">
																<label for="idcard-back" class="form-label">身分證背面
																</label> <br> <img class="image-preview"
																	id="idcard-back-preview" src="" alt=""
																	style="width: 300px;">
															</div>
														</div>

														<div class="row">
															<div class="form-group col-md-6">
																<label for="account-img" class="form-label">帳戶資料
																</label> <br> <img class="image-preview"
																	id="account-img-preview" src="" alt=""
																	style="width: 300px;">
															</div>
														</div>
													</div>
													<span id="verify_sellerConfirm"></span>
													<div class="row">
														<select class="form-select" name="sellerReviewResult"
															id="sellerReviewResult"
															aria-label="Default select example">
															<option value=" " selected>審核結果</option>
															<option value="1">通過</option>
															<option value="2">駁回(請說明原因)</option>
														</select>
													</div>
													<div class="row" id="sellerReasonTextarea"
														style="display: none;">
														<textarea id="reason" name="reason" rows="4" cols="50"
															placeholder="請輸入原因..."></textarea>
													</div>
													<div class="row modal-footer">
														<div class="col"></div>
														<button type="submit" class="col-auto btn btn-danger"
															id="btn_sellerConfirm">確定</button>
														<button type="button" class="col-auto btn btn-secondary"
															data-bs-dismiss="modal">返回</button>
														<div class="col"></div>
														<input type="hidden" name="memberId" id="memberId">
													</div>
												</div>
											</div>
										</div>
									</form>

									<!-- 查看資料(收容所) 使用ajax撈資料-->
									<form
										action="<%=request.getContextPath()%>/shelter/shelter.do?action=verify_Shelter"
										method="post" id="shelterData_form">
										<div class="modal fade" id="verify_shelter" tabindex="-1"
											aria-labelledby="resumeModalLabel" aria-hidden="true">
											<div
												class="modal-dialog modal-l modal-dialog-scrollable modal-dialog-centered">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title" id="resumeModalLabel">申請會員資料(收容所)</h5>
														<button type="button" class="btn-close"
															data-bs-dismiss="modal" aria-label="Close"></button>
													</div>
													<div class="row modal-body">
														<div class="form-group mb-3">
															<label for="useraccount" class="form-label">會員帳號(Email)
															</label> <input type="email" class="form-control"
																id="shelteraccount" name="useraccount"
																placeholder="請輸入Email" disabled>
														</div>

														<div class="form-group mb-3">
															<label for="account_category" class="form-label">申請身分
															</label> <input type="email" class="form-control"
																id="shelter_category" name="account_category"
																placeholder="請輸入身分" disabled>
														</div>

														<div class="form-group mb-3">
															<label for="sheltername" class="form-label">收容所名稱
															</label> <input type="text" class="form-control" id="sheltername"
																name="sheltername" placeholder="請輸入暱稱" disabled>
														</div>

														<div class="form-group mb-3">
															<label for="phone" class="form-label">電話號碼 </label> <input
																type="tel" class="form-control" id="shelterphone"
																name="shelterphone" placeholder="請輸入手機號碼" disabled>
														</div>

														<div class="form-group mb-3">
															<label for="address" class="form-label">地址 </label> <input
																type="text" class="form-control" id="shelteraddress"
																name="shelteraddress" placeholder="請輸入詳細地址" disabled>
														</div>
													</div>
													<span id="verify_shelterConfirm"></span>
													<div class="row">
														<select class="form-select" id="shelterReviewResult"
															name="shelterReviewResult"
															aria-label="Default select example">
															<option selected value=" ">審核結果</option>
															<option value="1">通過</option>
															<option value="2">退回(請說明原因)</option>
														</select>
													</div>
													<div class="row" id="shelterReasonTextarea"
														style="display: none;">
														<textarea id="shelterReason" name="reason" rows="4"
															cols="50" placeholder="請輸入原因..."></textarea>
													</div>
													<div class="row modal-footer">
														<div class="col"></div>
														<button type="submit" class="col-auto btn btn-danger">確定</button>
														<button type="button" class="col-auto btn btn-secondary"
															data-bs-dismiss="modal">退回</button>
														<div class="col"></div>
														<input type="hidden" name="memberId" id="sheltermemberId">
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