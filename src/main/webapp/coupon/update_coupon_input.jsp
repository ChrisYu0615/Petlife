<%@page import="com.petlife.admin.entity.Coupon"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%
Coupon coupon = (Coupon) request.getAttribute("coupon");
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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
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
<link rel="stylesheet" href="../dist/css/coupon_management.css">
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
			<table id="table-1">
				<tr>
					<td>
						<h3>�u�f�X��ƭק� - update_coupon_input.jsp</h3>
						<h4>
							<a href="select_page.jsp"><img src="images/back1.gif"
								width="100" height="32" border="0">�^����</a>
						</h4>
					</td>
				</tr>
			</table>

			<h3>��ƭק�:</h3>

			<%-- ���~��C --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">�Эץ��H�U���~:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>

			<FORM METHOD="post" ACTION="coupon.do" name="form1"
				onsubmit="return validateForm();">
				<table>
					<tr>
						<td>�u�f�X�s��:<font color=red><b>*</b></font></td>
						<td>${coupon.couponId}</td>
					</tr>
					<!-- 	<tr> -->
					<!-- 		<td>�u�f�X�s��:</td> -->
					<%-- 		<td><input type="HIDDEN" name="coupon_id" value="<%=coupon.getCouponId()%>" size="45"/></td> --%>
					<!-- 	</tr> -->
					<tr>
						<td>�u�f�X�W��:</td>
						<td><input type="TEXT" name="couponName"
							value="<%=coupon.getCouponName()%>" size="45" required /></td>
					</tr>
					<tr>
						<td>�u�f�X�ԭz:</td>
						<td><input type="TEXT" name="couponContent"
							value="<%=coupon.getCouponContent()%>" size="45" required /></td>
					</tr>
					<tr>
						<td>�ϥα���:</td>
						<td><input type="TEXT" name="conditionsOfUse"
							value="<%=coupon.getConditionsOfUse()%>" size="45" required /></td>
					</tr>
					<tr>
						<td>�}�l�ɶ�:</td>
						<td><input name="startDate" id="startDate" type="text"
							value="<%=coupon.getStartDate()%>" required>
							<div>YYYY-MM-DD HH:mm:ss</div></td>
					</tr>

					<tr>
						<td>�����ɶ�:</td>
						<td><input name="endDate" id="endDate" type="text"
							value="<%=coupon.getEndDate()%>" required>
							<div>YYYY-MM-DD HH:mm:ss</div></td>
					</tr>
					<tr>
						<td>�馩���B:</td>
						<td><input type="TEXT" name="discountAmount"
							value="<%=coupon.getDiscountAmount()%>" size="45" required /></td>
					</tr>

					<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
					<!-- 	<tr> -->
					<!-- 		<td>����:<font color=red><b>*</b></font></td> -->
					<!-- 		<td><select size="1" name="deptno"> -->
					<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
					<%-- 				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)?'selected':'' } >${deptVO.dname} --%>
					<%-- 			</c:forEach> --%>
					<!-- 		</select></td> -->
					<!-- 	</tr> -->

				</table>
				<br> <input type="hidden" name="action" value="update">
				<input type="hidden" name="couponId"
					value="<%=coupon.getCouponId()%>"> <input type="submit"
					value="�e�X�ק�">
			</FORM>

			<script>
				function validateForm() {
					var couponName = document.forms["form1"]["couponName"].value;
					var startDate = document.forms["form1"]["startDate"].value;
					var endDate = document.forms["form1"]["endDate"].value;

					// �ˬd�O�_����
					if (couponName.trim() == "") {
						alert("�ж�g�u�f�X�W�١C");
						return false; // ����form����
					}

					// �s�W�ˬd�u�f�X�W�٪��׬O�_�j��2�Ӧr
					if (couponName.trim().length <= 2) {
						alert("�u�f�X�W�٪��ץ����j��2�Ӧr�C");
						return false; // ����form����
					}

					// �ˬd����榡
					var dateRegex = /^\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2}(\.\d+)?|^\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2}|^\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2}.\d{3}|^\d{4}-\d{2}-\d{2}$/;

					if (!dateRegex.test(startDate) || !dateRegex.test(endDate)) {
						alert("�п�J���T������榡�]YYYY-MM-DD HH:mm:ss �� YYYY-MM-DD HH:mm:ss.S �� YYYY-MM-DD HH:mm:ss.SSS �� YYYY-MM-DD�^�C");
						return false;
					}

					// �ˬd endDate �O�_�b startDate ����
					var startTimestamp = new Date(startDate.replace(" ", "T"))
							.getTime();
					var endTimestamp = new Date(endDate.replace(" ", "T"))
							.getTime();

					if (endTimestamp <= startTimestamp) {
						alert("������������b�}�l�������C");
						return false;
					}

					return true; // ���\form����
				}
			</script>

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