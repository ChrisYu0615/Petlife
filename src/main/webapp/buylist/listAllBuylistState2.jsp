<%@page import="com.petlife.mall.entity.Buylist"%>
<%@page import="com.petlife.mall.service.BuylistService"%>
<%@page import="com.petlife.mall.service.impl.BuylistServiceImpl"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@page import="com.petlife.admin.*"%>

<%
BuylistService buylistSvc = new BuylistServiceImpl();
List<Buylist> list = buylistSvc.getBuylistsByState(2);
pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html lang="zh-TW">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>�Ҧ��q��s�����</title>
<!-- ��Loading, please wait���� -->
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

		<!-- �W��Navbar -->
		<nav
			class="main-header navbar navbar-expand navbar-white navbar-light">
			<!-- �W�襪��Left navbar links -->
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" data-widget="pushmenu"
					href="#" role="button"><i class="fas fa-bars"></i></a></li>
				<li class="nav-item d-none d-sm-inline-block"><a
					href="test.html" class="nav-link">Home</a></li>
				<!-- �o�Ocontact bar -->
				<!-- <li class="nav-item d-none d-sm-inline-block">
                    <a href="#" class="nav-link">Contact</a>
                </li> -->
			</ul>

			<!-- �W��k��Right navbar links -->
			<ul class="navbar-nav ml-auto">
				<!-- Navbar Search -->
				<li class="nav-item"><a class="nav-link"
					data-widget="navbar-search" href="#" role="button"> <i
						class="fas fa-search"></i>
				</a>
					<div class="navbar-search-block">
						<form class="form-inline">
							<div class="input-group input-group-sm">
								<input class="form-control form-control-navbar" type="search"
									placeholder="Search" aria-label="Search">
								<div class="input-group-append">
									<button class="btn btn-navbar" type="submit">
										<i class="fas fa-search"></i>
									</button>
									<button class="btn btn-navbar" type="button"
										data-widget="navbar-search">
										<i class="fas fa-times"></i>
									</button>
								</div>
							</div>
						</form>
					</div></li>

				<!-- �o��O�W��k���ѳq���C -->
				<!-- Messages Dropdown Menu -->
				<!-- <li class="nav-item dropdown">
                    <a class="nav-link" data-toggle="dropdown" href="#">
                        <i class="far fa-comments"></i>
                        <span class="badge badge-danger navbar-badge">3</span>
                    </a>
                    <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
                        <a href="#" class="dropdown-item"> -->
				<!-- Message Start -->
				<!-- <div class="media">
                                <img src="dist/img/user1-128x128.jpg" alt="User Avatar"
                                    class="img-size-50 mr-3 img-circle">
                                <div class="media-body">
                                    <h3 class="dropdown-item-title">
                                        Brad Diesel
                                        <span class="float-right text-sm text-danger"><i class="fas fa-star"></i></span>
                                    </h3>
                                    <p class="text-sm">Call me whenever you can...</p>
                                    <p class="text-sm text-muted"><i class="far fa-clock mr-1"></i> 4 Hours Ago</p>
                                </div>
                            </div> -->
				<!-- Message End -->
				<!-- </a>
                        <div class="dropdown-divider"></div>
                        <a href="#" class="dropdown-item"> -->
				<!-- Message Start -->
				<!-- <div class="media">
                                <img src="dist/img/user8-128x128.jpg" alt="User Avatar"
                                    class="img-size-50 img-circle mr-3">
                                <div class="media-body">
                                    <h3 class="dropdown-item-title">
                                        John Pierce
                                        <span class="float-right text-sm text-muted"><i class="fas fa-star"></i></span>
                                    </h3>
                                    <p class="text-sm">I got your message bro</p>
                                    <p class="text-sm text-muted"><i class="far fa-clock mr-1"></i> 4 Hours Ago</p>
                                </div>
                            </div> -->
				<!-- Message End -->
				<!-- </a>
                        <div class="dropdown-divider"></div>
                        <a href="#" class="dropdown-item"> -->
				<!-- Message Start -->
				<!-- <div class="media">
                                <img src="dist/img/user3-128x128.jpg" alt="User Avatar"
                                    class="img-size-50 img-circle mr-3">
                                <div class="media-body">
                                    <h3 class="dropdown-item-title">
                                        Nora Silvester
                                        <span class="float-right text-sm text-warning"><i
                                                class="fas fa-star"></i></span>
                                    </h3>
                                    <p class="text-sm">The subject goes here</p>
                                    <p class="text-sm text-muted"><i class="far fa-clock mr-1"></i> 4 Hours Ago</p>
                                </div>
                            </div> -->
				<!-- Message End -->
				<!-- </a>
                        <div class="dropdown-divider"></div>
                        <a href="#" class="dropdown-item dropdown-footer">See All Messages</a>
                    </div>
                </li> -->

				<!-- �o�̬O�W��k��T���q���C -->
				<!-- Notifications Dropdown Menu -->
				<li class="nav-item dropdown"><a class="nav-link"
					data-toggle="dropdown" href="#"> <i class="far fa-bell"></i> <span
						class="badge badge-warning navbar-badge">15</span>
				</a>
					<div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
						<span class="dropdown-item dropdown-header">15
							Notifications</span>
						<div class="dropdown-divider"></div>
						<a href="#" class="dropdown-item"> <i
							class="fas fa-envelope mr-2"></i> 4 new messages <span
							class="float-right text-muted text-sm">3 mins</span>
						</a>
						<div class="dropdown-divider"></div>
						<a href="#" class="dropdown-item"> <i
							class="fas fa-users mr-2"></i> 8 friend requests <span
							class="float-right text-muted text-sm">12 hours</span>
						</a>
						<div class="dropdown-divider"></div>
						<a href="#" class="dropdown-item"> <i class="fas fa-file mr-2"></i>
							3 new reports <span class="float-right text-muted text-sm">2
								days</span>
						</a>
						<div class="dropdown-divider"></div>
						<a href="#" class="dropdown-item dropdown-footer">See All
							Notifications</a>
					</div></li>

				<!-- �o�ӬO�W��k����ù��Ҧ� -->
				<li class="nav-item"><a class="nav-link"
					data-widget="fullscreen" href="#" role="button"> <i
						class="fas fa-expand-arrows-alt"></i>
				</a></li>

				<!-- �o�̥i�H���n�J�ק�εn�X�\�� -->
				<li class="nav-item dropdown"><a class="nav-link dropdown"
					href="#" id="navbarDropdown2" role="button" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false"> <i
						class="fas fa-solid fa-user"></i>
				</a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown2">
						<a class="dropdown-item" href="/google">�ק�b����T</a> <a
							class="dropdown-item" href="/yahoo">�n�X</a>
					</div></li>
			</ul>
		</nav>
		<!-- /.navbar -->





		<!-- Main Sidebar Container -->
		<aside class="main-sidebar sidebar-dark-primary elevation-4">
			<!-- Brand Logo -->
			<a href="test.html" class="brand-link"> <img
				src="../dist/img/main_logo.png" alt="AdminLTE Logo"
				class="brand-image img-corners elevation-3" style="opacity: .8">
				<span class="brand-text font-weight-light">�d�R�ͬ���O�޲z</span>
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
						<!-- Add icons to the links using the .nav-icon class with font-awesome or any other icon font library -->

						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="nav-icon fas fa-solid fa-id-card"></i>
								<p>�q��</p>
						</a></li>
						<!-- �H�U�O�i���Y�������� -->
						<li class="nav-item menu-open"><a href="#"
							class="nav-link active"> <i
								class="nav-icon fas fa-tachometer-alt"></i>
								<p>
									�q��޲z <i class="right fas fa-angle-left"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="listAllBuylistState0.jsp"
									class="nav-link active"> <i class="far fa-circle nav-icon"></i>
										<p>0:�ݥI��</p>
								</a></li>
								<li class="nav-item"><a href="listAllBuylistState1.jsp"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>1:�ݥX�f</p>
								</a></li>
								<li class="nav-item"><a href="listAllBuylistState2.jsp"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>2:�B�e��</p>
								</a></li>
								<li class="nav-item"><a href="listAllBuylistState3.jsp"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>3:�q��w����</p>
								</a></li>
								<li class="nav-item"><a href="listAllBuylistState4.jsp"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>4:�q��w����</p>
								</a></li>
								<li class="nav-item"><a href="listAllBuylistState5.jsp"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>5:�h�f/�h��</p>
								</a></li>
							</ul></li>
						<!-- ========================================================== -->
						<li class="nav-item"><a
							href="../buylistdetails/listAllBuylistDetails.jsp"
							class="nav-link"> <i class="nav-icon fas fa-solid fa-id-card"></i>
								<p>�q��ӫ~�Ӷ�</p>
						</a></li>
						<!-- ========================================================== -->
						<li class="nav-item menu-open"><a href="#"
							class="nav-link active"> <i class="nav-icon fas far fa-copy"></i>
								<p>
									�ӫ~�޲z <i class="right fas fa-angle-left"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="./index.html"
									class="nav-link active"> <i class="far fa-circle nav-icon"></i>
										<p>�X�⤤</p>
								</a></li>
								<li class="nav-item"><a href="./index2.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>�w�U�[</p>
								</a></li>
								<li class="nav-item"><a href="./index3.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>�ʳf��</p>
								</a></li>
								<li class="nav-item"><a href="./index3.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>�W�[�ӫ~</p>
								</a></li>
							</ul></li>
						<!-- ========================================================== -->


						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="nav-icon fas fa-solid fa-id-card"></i>
								<p>�w�����</p>
						</a></li>

						<li class="nav-item"><a href="#" class="nav-link"> <!-- <i class="nav-icon fas fa-copy"></i> -->
								<i class="nav-icon fas fa-shopping-cart"></i>
								<p>��a�ƾڤ���</p>
						</a></li>
						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="nav-icon fas fa-ad"></i>
								<p>�n�X</p>
						</a></li>

					</ul>
				</nav>
				<!-- /.sidebar-menu -->
			</div>
			<!-- /.sidebar -->
		</aside>




		<!-- �o��O�D���e�W�誺��r�ԭz -->
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1 class="m-0">�q��޲z</h1>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="test.html">Home</a></li>
								<li class="breadcrumb-item active">�q��޲z</li>
							</ol>
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- /.content-header -->


			<!-- //�o���D���e��r -->
			<!-- /.content-wrapper -->


			<table id="table-1">
				<tr>
					<td>
						<h3>�Ҧ��q��s�����</h3>
						<h4>
							<a href="select_page.jsp"><img src="images/back1.gif"
								width="100" height="32" border="0">�^����</a>
						</h4>
					</td>
				</tr>
			</table>

			<table>
				<tr>
					<th>�q��s��ID</th>
					<th>�|��ID</th>
					<th>��aID</th>
					<th>�q�檬�AID</th>
					<th>�u�f�XID</th>
					<th>��a�����P��</th>
					<th>��a�����ԭz</th>
					<th>��a�����ɶ�</th>
					<th>�q����B</th>
					<th>�q��إ߮ɶ�</th>
					<th>�ק�</th>
					<th>�R��</th>
				</tr>
				<%@ include file="page1.file"%>
				<c:forEach var="buylist" items="${list}" begin="<%=pageIndex%>"
					end="<%=pageIndex+rowsPerPage-1%>">

					<tr>
						<td>${buylist.buylistId}</td>
						<td>${buylist.user.userId}</td>
						<td>${buylist.seller.sellerId}</td>
						<td>${buylist.buylistState.buylistStateId}</td>
						<td>${buylist.coupon.couponId}</td>
						<td>${buylist.sellerRatingStars}</td>
						<td>${buylist.sellerEvaluateNarrative}</td>
						<td>${buylist.sellerEvaluateTime}</td>
						<td>${buylist.buylistAmount}</td>
						<td>${buylist.buylistDate}</td>
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/buylist/buylist.do"
								style="margin-bottom: 0px;">
								<input type="submit" value="�ק�"> <input type="hidden"
									name="buylistId" value="${buylist.buylistId}"> <input
									type="hidden" name="action" value="getOne_For_Update">
							</FORM>
						</td>
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/buylist/buylist.do"
								style="margin-bottom: 0px;">
								<input type="submit" value="�R��"> <input type="hidden"
									name="buylistId" value="${buylist.buylistId}"> <input
									type="hidden" name="action" value="delete">
							</FORM>
						</td>
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