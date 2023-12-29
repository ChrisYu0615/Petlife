<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.petlife.shelter.entity.Shelter"%>
	<!DOCTYPE html>
<html lang="zh-TW">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>後臺切版測試</title>
<script src="https://www.itxst.com/package/jquery-3.3.1/jquery.js"></script>

<script
	src="https://www.itxst.com/package/bootstrap-table-1.15.3/bootstrap-table.js"></script>
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
<link rel="stylesheet" href="../dist/css/pet_search.css">

	 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" crossorigin="anonymous">
    <link rel="stylesheet" href="https://unpkg.com/bootstrap-table@1.15.5/dist/bootstrap-table.min.css">
	
<% 
	Integer id =null;
	Shelter shelter = (Shelter) session.getAttribute("shelter");
    if(shelter!= null){
    	id = shelter.getShelterId();
    	request.setAttribute("id", id);
}


%>
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

			<!-- 上方右邊Right navbar links -->
			<ul class="navbar-nav ml-auto">
			</ul>
		</nav>
		</div>
		<!-- /.navbar -->

		<!-- Main Sidebar Container -->
		<%@ include file="sidebar.jsp"%>

		<!-- //這邊塞主內容文字 -->
		<!-- Main content -->
		<div class="content">
			<div class="container-fluid">
				<!-- <div class="row"> -->
				<div class="col-lg-6">
					<span id="update_form"></span>

					<h1>篩選收容動物條件</h1>

					<form id="search" action="/HelloHibernateEx/project/pet.do"
						class="from" method="post">
						<!-- 第一行  -->
						<div class="search_pet">
						
								<input type ="hidden" name="id" value="<%=id%>" id="id" >

							<span class="mar">
								<p>種類:</p>

								<div class="form-check form-check-inline">
									<input class="form-check-input pet_type" type="radio"
										name="type" id="type" value="狗"> <label
										class="form-check-label " for="pet_type_dog">狗</label>
								</div>

								<div class="form-check form-check-inline">
									<input class="form-check-input pet_type" type="radio"
										name="type" id="type" value="貓"> <label
										class="form-check-label " for="pet_type_cat">貓</label>
								</div>
							</span>

							<!--						 性別 -->
							<span class="mar">
								<p>性別:</p>
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="radio" name="petGender"
										id="pet_gender_m" value="公"> <label
										class="form-check-label" for="pet_gender_m">公</label>
								</div>
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="radio" name="petGender"
										id="pet_gender_w" value="母"> <label
										class="form-check-label" for="pet_gender_w">母</label>
								</div>
							</span>



							<!-- 						品種 -->
							<div class="search_two">
								<p>品種:</p>

								<select class="form-select form-select-sm pet_variety"
									aria-label=".form-select-sm example" name="petVarietyId"
									id="petVarietyId">
									<option selected>請先選擇種類</option>
								</select>
							</div>


							<!-- 						收容編號 -->
							<div class="search_two">
								<label for="pet_num" class="pet_num">收容編號:</label> <input
									class="form-control" type="text" placeholder="收容編號"
									aria-label="default input example" id="pet_num" name="petNum">
							</div>

							<!-- 						入所日期 -->
							<div class="search_thr">

								<label for="come_in_date_start" class="come_in_date_start">入所日期起:</label>
								<input type="date" class="form-control" id="come_in_date_start"
									name="start_comin_date" placeholder="選擇日期"> <span
									class="search_thr"> ~ </span> <label for="come_in_date_end"
									class="come_in_date_end">迄:</label> <input type="date"
									class="form-control" id="come_in_date_end"
									name="end_comin_date" placeholder="選擇日期">

							</div>

							<!-- 第四行 -->
							<div class="search_four">

								<label for="adopt_date_start" class="come_in_date_start">領養日期起:</label>
								<input type="date" class="form-control" id="adopt_date_start"
									name="start_adopt_date" placeholder="選擇日期">
								<span class="search_thr"> ~ </span> <label
									for="adopt_date_end" class="come_in_date_end">迄:</label> <input
									type="date" class="form-control" id="adopt_date_end"
									name="end_adopt_date" placeholder="選擇日期">
							</div>

							<!-- 按鈕 -->
							<div class="search_button_clear">
								<button class="btn btn-primary search_button_clear" type="reset">清除</button>
							</div>
							<!-- 搜尋 使用ajax 送進參數  -->
							<div class="search_button_search">
								<button class="btn btn-primary" type="button" id="search_btn">搜尋</button>
							</div>

							<!-- search_icon -->
							<div class="search_icon">
								<i class="fas fa-search fa-lg search_button"></i>
							</div>

						</div>



						<!-- 上架按鈕 -->
						<div class="edit_button">
							<button type="button" class="btn btn-warning btn-sm edit_button"
								id="insert">
								<a href="pet_from.jsp" class="insert"
									style="color: white; text-decoration: none;">上架</a>
							</button>
						</div>
					</form>
					<div id="search_result">

					</div>
<!-- =========================================modal區 =========================================-->
<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop" id="button_result">
  Launch static backdrop modal
</button>

<!-- Modal -->
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel"></h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body" id="result">
       
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">返回</button>
      </div>
    </div>
  </div>
</div> 
				<!-- =========================================modal區 =========================================-->	
				</div>
			</div>

		</div>
		<!-- ./wrapper -->
		<script src="https://code.jquery.com/jquery-3.3.1.min.js"
			crossorigin="anonymous"></script>
<!-- 				<script -->
<!-- 					src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" -->
<!-- 					crossorigin="anonymous"></script> -->
<!-- 				<script -->
<!-- 					src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" -->
<!-- 					crossorigin="anonymous"></script> -->
		<script
			src="https://unpkg.com/bootstrap-table@1.15.5/dist/bootstrap-table.min.js"></script>

<!-- 				<script src="../plugins/jquery/jquery.min.js"></script> -->
		<!-- jQuery UI 1.11.4 -->
<!-- 		        <script src="../plugins/jquery-ui/jquery-ui.min.js"></script> -->
		<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
		<!--         <script>
   $.widget.bridge('uibutton', $.ui.button)
        </script> -->
		<!-- Bootstrap 4 -->
		<!--         <script src="../plugins/bootstrap/js/bootstrap.bundle.min.js"></script> -->
		<!-- overlayScrollbars -->
		
		  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.1/dist/umd/popper.min.js" integrity="sha384-W8fXfP3gkOKtndU4JGtKDvXbO53Wy8SZCQHczT5FMiiqmQfUpWbYdTil/SxwZgAN" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.min.js" integrity="sha384-skAcpIdS7UcVUC05LJ9Dxay8AXcDYfBJqt1CJ85S/CFujBsIzCIv+l9liuYLaMQ/" crossorigin="anonymous"></script>
		
		<script
			src="../plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
		<!-- AdminLTE App -->
		<script src="../dist/js/adminlte.js"></script>
		<script src="../dist/js/search.js"></script>
		

</body>
</html>