<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="com.petlife.shelter.entity.Shelter"%>
<% 
Integer id =null;
Shelter shelter = (Shelter) session.getAttribute("shelter");
if(shelter!= null){
	id = shelter.getShelterId();
	request.setAttribute("id", id);
}
int shelterPhotoLength = (shelter.getShelterPhoto() != null) ? shelter.getShelterPhoto().length : 0;
%>

<!DOCTYPE html>
<html lang="zh-TW">

<head>
<meta charset="UTF-8">
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
<link rel="stylesheet" href="../dist/css/pet_edit.css">




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


		<!-- Main Sidebar Container -->
		<%@ include file="sidebar.jsp"%>

		<!-- //這邊塞主內容文字 -->
		<!-- Main content -->
		<div class="content">
			<div class="container-fluid">
				<div class="col-lg-6">
					<h1>編輯基本資料</h1>
					<!-- 					第一行  -->
					<div class="material">
						<!-- 						... (rest of the form content) ... -->
						<form id="shelter_update_put" method="post" action="${pageContext.request.contextPath}/shelter/shelter.do" enctype="multipart/form-data">
							<span class="material_list"> <label for="shelter_name"
								class="shelter_name">收容所名稱:</label> <input class="form-control"
								name="shelterName" type="text"
								aria-label="default input example" id="shelter_name"
								value="${shelter.shelterName}"> <span id="shelterName"></span>
							</span> <span class="material_list"> <label
								for="shelter_password1">帳號:</label> <input class="form-control"
								type="text" name="shelterAcct"
								aria-label="default input example" id="shelterAcct"
								value="${shelter.shelterAcct}"> <span id="shelterAcct"></span>
							</span>
						
							<!-- 	=============================================準備修改密碼 -->
							<span class="material_list"><input type="checkbox"
								id="changPwd">是否修改密碼</span> <span id="pwd"
								style="display: none;"> <span class="material_list">
									<label for="shelter_password1">密碼:</label> <input
									class="form-control" type="password" name="password"
									aria-label="default input example" id="shelter_password1">
									<span id="shelter_password1"></span>
							</span> <span class="material_list"> <label
									for="shelter_password1">確認密碼:</label> <input
									class="form-control" type="password" name="shelterPwd_two"
									aria-label="default input example" id="shelter_password2">
									<span id="shelter_password2"></span>

							</span>

							</span> <span class="material_list"> <label for="shelter_phone">電話:</label>
								<input class="form-control" type="text" name="shelterPhoneNum"
								aria-label="default input example" id="shelterPhoneNum"
								value="${shelter.shelterPhoneNum}"> <span
								id="shelterPhoneNum"> </span>
							</span> 
							
							<span class="material_list"> 
								<label for="shelter_adress">詳細地址:</label>
									<input class="form-control" type="text" name="shelterAddress" aria-label="default input example" id="shelterAddress"
								value="${shelter.shelterAddress}"> 
								<span id="shelterAddress"></span>
							</span> 
							
							<span class="material_list" style="display: flex; flex-direction: column; align-items: flex-start;">
							 <label for="shelter_introduction" id="shelter_introduction">簡介:</label>
							  <textarea id="shelterIntroduction" name="shelterIntroduction" rows="4"
									cols="50">${shelter.shelterIntroduction}</textarea> 
									<span id="shelterIntroduction"></span>
							</span> 
					

							<div class="button">
								  <button type="submit" class="btn btn-primary put_on btn-sm" id="btn_shelter_update">確認修改</button>
								<input type="hidden" name="action" value="update_put">
							</div>


							<!-- 							右上角大頭貼   -->
							<div class="img">
								<span>
									<div id="s_img">
										<% if (shelterPhotoLength != 0) { %>
                               				 <img src="<%=request.getContextPath()%>/shelter/shelter.do?action=getShelterPhoto&shelterId=${id}" 
											  width="100px" height="100px" style="border-radius: 50%;" >
										<% } %>
									</div>
									
								</span> <input type="file" id="s_blob" name="shelterblob"  accept="image/*">
							</div>
						</form>
					</div>
				</div>
				<!-- 				/.container-fluid -->
			</div>
		</div>
		<!-- 		<!-- ./wrapper -->

		<!-- 		<!-- jQuery -->
		<script src="../plugins/jquery/jquery.min.js"></script>
		<!-- jQuery UI 1.11.4 -->
		<script src="../plugins/jquery-ui/jquery-ui.min.js"></script>
		<!-- 		<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
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
		<script src="../dist/js/shelter_update.js"></script>
</body>
</html>
