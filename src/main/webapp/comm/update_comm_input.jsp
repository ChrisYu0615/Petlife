<%@page import="com.petlife.seller.entity.Seller"%>
<%@page import="com.petlife.mall.entity.CommCat"%>
<%@page import="com.petlife.mall.entity.Comm"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
Seller seller = (Seller) session.getAttribute("seller");
Comm comm = (Comm) request.getAttribute("comm");
%>
<!DOCTYPE html>
<html lang="zh-TW">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>修改商品</title>
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
body {
	font-family: 'Source Sans Pro', sans-serif;
	background-color: #f4f6f9;
}

.wrapper {
	background-color: #fff;
}

/* Header styles */
.navbar {
	background-color: #fff; /* 保留原本的白色 */
	color: #343a40;
}

.navbar a {
	color: #343a40;
}

/* Sidebar styles */
.main-sidebar {
	background-color: #343a40;
}

.brand-link {
	background-color: #343a40;
	color: #fff;
}

.brand-link:hover {
	color: #fff;
}

.nav-sidebar a {
	color: #d4d4d4;
}

.nav-sidebar a:hover {
	color: #fff !important;
}

/* Content styles */
.content-wrapper {
	background-color: #f4f6f9;
	padding: 20px;
}

h1, h3, h4 {
	color: #343a40;
}

/* Form styles */
form {
	margin-top: 20px;
}

label {
	font-weight: bold;
	color: #343a40;
}

.form-control {
	border: 1px solid #ced4da;
}

.form-control:focus {
	border-color: #007bff;
	box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
}

.btn-primary {
	background-color: #007bff;
	border-color: #007bff;
}

.btn-primary:hover {
	background-color: #0056b3;
	border-color: #0056b3;
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
						<li class="nav-item"><a href="#" class="nav-link active">
								<i class="nav-icon fas fa-tachometer-alt"></i>
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

						<li class="nav-item"><a href="#" class="nav-link active">
								<i class="nav-icon fas far fa-copy"></i>
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
							<h1 class="m-0">修改訂單</h1>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="test.html">Home</a></li>
								<li class="breadcrumb-item active">訂單管理</li>
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


			<table id="table-1">
				<tr>
					<td>
						<h3>商品資料修改</h3>
						<h4>
							<a href="listAllComm.jsp"> <!-- 							<img src="images/back1.gif" -->
								<!-- 								width="100" height="32" border="0"> --> 回全部商品首頁
							</a>
						</h4>
					</td>
				</tr>
			</table>

			<h3>資料修改:</h3>

			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>

			<form method="post" action="comm.do" name="form1"
				enctype="multipart/form-data" onsubmit="return validateForm()">
				<table>
					<tr>
						<td>商品編號:<font color=red><b>*</b></font></td>
						<td>${comm.commId}</td>
					</tr>
					<!-- 	<tr> -->
					<!-- 		<td>商品編號編號:</td> -->
					<%-- 		<td><input type="HIDDEN" name="commId" value="<%=comm.getCommId()%>" size="45"/></td> --%>
					<!-- 	</tr> -->
					<tr>
						<td>賣家ID:</td>
						<td><%=comm.getSeller().getSellerId()%></td>
					</tr>
					<tr>
						<td>商品名稱:</td>
						<td><input type="text" name="commName"
							value="<%=comm.getCommName()%>" size="45" /></td>
					</tr>
					<tr>
						<td>商品描述:</td>
						<td><input type="text" name="commDesc"
							value="<%=comm.getCommDesc()%>" size="45" /></td>
					</tr>
					<tr>
						<td>商品狀態:</td>
						<td><select name="commState" required>
								<option value="" selected disabled hidden>請選擇商品狀態</option>
								<option value="0"
									<%=(comm.getCommState() == 0) ? "selected" : ""%>>販售中</option>
								<option value="1"
									<%=(comm.getCommState() == 1) ? "selected" : ""%>>缺貨中</option>
								<option value="2"
									<%=(comm.getCommState() == 2) ? "selected" : ""%>>下架</option>
								<option value="3"
									<%=(comm.getCommState() == 3) ? "selected" : ""%>>違規下架</option>
						</select></td>
					</tr>
					<!-- 					<tr> -->
					<!-- 						<td>商品狀態:</td> -->
					<!-- 						<td><input type="text" name="commState" -->
					<%-- 							value="<%=comm.getCommState()%>" size="45" /></td> --%>
					<!-- 					</tr> -->
					<tr>
						<td>上架時間:</td>
						<td><%=comm.getListDatetime()%></td>
					</tr>
					<tr>
						<td>商品縮圖:</td>
						<td><input type="file" name="commImg"
							value="<%=comm.getCommImg()%>" size="45" /></td>
						<td><img
							src="<%=request.getContextPath()%>/comm/DBJPGReader?commId=${comm.commId}"
							width="110px" height="110px"></td>
					</tr>
					<td>商品分類:</td>
					<td><select name="commCat" required>
							<option value="" selected disabled hidden>請選擇商品分類</option>

							<optgroup label="貓貓">
								<option value="1000"
									<%=(comm.getCommCat().getCommCatId() == 1000) ? "selected" : ""%>>貓咪飼料</option>
								<option value="1001"
									<%=(comm.getCommCat().getCommCatId() == 1001) ? "selected" : ""%>>貓咪主食罐</option>
								<option value="1002"
									<%=(comm.getCommCat().getCommCatId() == 1002) ? "selected" : ""%>>貓咪副食罐</option>
								<option value="1003"
									<%=(comm.getCommCat().getCommCatId() == 1003) ? "selected" : ""%>>貓咪零食</option>
								<option value="1004"
									<%=(comm.getCommCat().getCommCatId() == 1004) ? "selected" : ""%>>貓咪用品</option>
							</optgroup>

							<optgroup label="狗狗">
								<option value="2000"
									<%=(comm.getCommCat().getCommCatId() == 2000) ? "selected" : ""%>>狗狗飼料</option>
								<option value="2001"
									<%=(comm.getCommCat().getCommCatId() == 2001) ? "selected" : ""%>>狗狗主食罐</option>
								<option value="2002"
									<%=(comm.getCommCat().getCommCatId() == 2002) ? "selected" : ""%>>狗狗副食罐</option>
								<option value="2003"
									<%=(comm.getCommCat().getCommCatId() == 2003) ? "selected" : ""%>>狗狗零食</option>
								<option value="2004"
									<%=(comm.getCommCat().getCommCatId() == 2004) ? "selected" : ""%>>狗狗用品</option>
							</optgroup>
					</select></td>
					</tr>
					<!-- 					<tr> -->
					<!-- 						<td>商品分類ID:</td> -->
					<!-- 						<td><input type="text" name="commCat" -->
					<%-- 							value="<%=comm.getCommCat().getCommCatId()%>" size="45" /></td> --%>
					<!-- 					</tr> -->
					<tr>
						<td>商品庫存量:</td>
						<td><input type="text" name="commStock"
							value="<%=comm.getCommStock()%>" size="45" /></td>
					</tr>
					<tr>
						<td>商品價格:</td>
						<td><input type="text" name="commPrice"
							value="<%=comm.getCommPrice()%>" size="45" /></td>
					</tr>
					<tr>
						<td>商品優惠價:</td>
						<td><input type="text" name="commOnsalePrice"
							value="<%=comm.getCommOnsalePrice()%>" size="45" /></td>
					</tr>
					<!-- 					<tr> -->
					<!-- 						<td>瀏覽數:</td> -->
					<!-- 						<td><input type="text" name="commViewCount" -->
					<%-- 							value="<%=comm.getCommViewCount()%>" size="45" /></td> --%>
					<!-- 					</tr> -->



				</table>
				<br> <input type="hidden" name="action" value="update">
				<input type="hidden" name="commId" value="<%=comm.getCommId()%>">
				<input type="submit" value="送出修改">
			</FORM>

			<script>
				function validateForm() {
					var commPrice = parseFloat(document.forms["form1"]["commPrice"].value);
					var commOnsalePrice = parseFloat(document.forms["form1"]["commOnsalePrice"].value);

					if (commOnsalePrice >= commPrice) {
						alert("商品優惠價不得大於或等於商品價格。");
						return false;
					}

					return true; // 如果所有驗證都通過，返回 true；否則返回 false
				}
			</script>
</body>

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