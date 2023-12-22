<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link rel="stylesheet" href="../dist/css/pet_date.css">
<link rel="stylesheet"
	href="https://unpkg.com/bootstrap-table@1.15.5/dist/bootstrap-table.min.css">
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
				<!-- <div class="card"> -->
				<!-- <div class="card-body"> -->

				<h1>新增&查詢可預約時間</h1>
				<span id="select_month"> <label for="monthSelector">選擇月份：</label>
					<input type="month" id="monthSelector"> <!-- 					<button type="button" id="first two weeks" onclick="generateCalendar()">上半月</button> -->
					<button type="button" id="first_two_weeks" value="-14" onclick="generateCalendar()">上半月</button>
					<button type="button" id="next_two_weeks" onclick="next_two_weeks_generateCalendar()" value="-15">下半月</button>
				</span>
				
				<div id="put"></div>
								








				<!-- </div> -->
				<!-- /.col-md-6 -->
				<!-- </div> -->
				<!-- /.row -->
			</div>
			<!--/.container-fluid-->
			<!-- </div> -->
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->


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
	<script src="../dist/js/shelter_date.js"></script>

	<script>
            $(function(){
                 $(document).on("click","button.morning",function(){
                     $(this).toggleClass("on");
 
                 })
                 $(document).on("click","button.afternoon",function(){
                     $(this).toggleClass("on");
 
                 })
 
            });
            
            
          

 
       </script>




</body>

</html>