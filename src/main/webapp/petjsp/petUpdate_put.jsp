<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>後臺切版測試</title>

<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
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
<!-- my_css -->
<link rel="stylesheet" href="../dist/css/form.css">

<style>
div.con {
	
	padding: 5px 0px 5px 30px;
	hight: 20px;
}

label {
	margin: 0px;
	width: 120px
}

span {
	margin-left: 10px
}

div.button {
	position: relative;
	left: 60%;
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
				<li class="nav-item d-none d-sm-inline-block"><a
					href="test.html" class="nav-link"></a></li>
			</ul>

			<!-- 上方右邊Right navbar links -->
			<ul class="navbar-nav ml-auto">
			</ul>
		</nav>
		<!-- 		<!-- /.navbar -->
		-->

		<!-- Main Sidebar Container -->
		<%@ include file="sidebar.jsp"%>


	<!-- //這邊塞主內容文字 -->
	<!-- Main content -->
	<div class="content">
		<div class="container-fluid">
			<!-- <div class="row"> -->
			<div class="col-lg-6">
				<!-- <div class="card"> -->
				<!-- <div class="card-body"> -->
				<div class="row">
					<h1>新增成功</h1>
				</div>
<form  class="from" enctype="multipart/form-data" id="pet_form">

					<!--   收容所到時后關起來,種類打開  -->
					<div class="con">
						<label>收容所:</label> <span>${pet.shelter.shelterName} </span>
					</div>


					<div class="con">
						<label>種類:</label> <span>${pet.variety.variety}</span>
					</div>





					<div class="con">
						<label>性別:</label> <span>${pet.petGender} </span>
					</div>



					<div class="con">
						<label>絕育:</label> <span>${pet.petLigation} </span>
					</div>


					<div class="con">
						<label for="pet_color">毛色:</label> <span>${pet.petColor} </span>

					</div>


					<div class="con">
						<label>是否開放領養:</label> 
<%-- 						<span><%=(pet.getAdopt())?"是":"否" %></span> --%>
						<span>${pet.adopt}</span>
						
					</div>

					<div class="con">
						<label for="pet_content">描述:</label> <span>${pet.petContent}
						</span>
					</div>

					<div class="con">
						<label for="comin_date">入所日期:</label> <span>${pet.comeInDate}
						</span>
					</div>



					<div class="con">
						<label for="pet_cage">籠舍:</label> <span>${pet.petCage} </span>
					</div>



					<div class="con">
						<label for="pet_num">收容編號:</label> <span>${pet.petNum} </span>
					</div>

					<div class="con">
<!-- 						<label>照片:</label>  -->
<!-- 						<span>  -->
<%-- 							<c:forEach var="photo" --%>
<%-- 								items="${pet.petPhotos}"> --%>
<!-- 								<img -->
<%-- 									src="<%=request.getContextPath()%>/project/petphoto.do?action=getPetPhotoTest&photoId=${photo.photoId}" --%>
<!-- 									width="100px"> -->
<%-- 							</c:forEach> --%>
<!-- 						</span> -->
					</div>

					<h2>領養者資訊</h2>

					<div class="con">
						<label>是否被領養:
						</label>
<%-- 						<span><%=(pet.getAdopted())?"是":"否" %></span> --%>
						<span>${pet.adopted}</span>
											
					</div>


					<div class="con">
						<label for="mer">領養者:</label><span>${pet.userId}</span>
					</div>

					<div class="con">
						<label for="adoped_date">領養日期:</label> <span>${pet.adoptDate}</span>
					</div>
			

			<div class="button">
			<a href="../petjsp/pet_search.jsp">
				<button type="button" class="btn btn-primary put_on btn-sm"
					>確認返回</button>
					</a>
			</div>
			</form>
		</div>
	</div>
	</div>




	<!-- jQuery -->
	<script src="../plugins/jquery/jquery.min.js"></script>
	<!-- jQuery UI 1.11.4 -->
	<script src="../plugins/jquery-ui/jquery-ui.min.js"></script>
	<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
	<!--             <script> -->
	<!--               $.widget.bridge('uibutton', $.ui.button) -->
	<!--             </script> -->
	<!-- Bootstrap 4 -->
	<script src="../plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- overlayScrollbars -->
	<script
		src="../plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
	<!-- AdminLTE App -->
	<script src="../dist/js/adminlte.js"></script>
	<script src="../dist/js/form.js"></script>
	
</body>
</html>
