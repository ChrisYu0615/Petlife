<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh-TW">

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>忘記密碼</title>

	<!-- Google Font: Source Sans Pro -->
	<link rel="stylesheet"
		href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
	<!-- Bootstrap css -->
	<link rel="stylesheet" href="../assets/css/bootstrap.min.css">
	<!-- animate css -->
	<link rel="stylesheet" href="../assets/css/animate.min.css">
	<!-- Fontawesome css -->
	<link rel="stylesheet" href="../assets/css/fontawesome.all.min.css">
	<!-- owl.carousel css -->
	<link rel="stylesheet" href="../assets/css/owl.carousel.min.css">
	<!-- owl.theme.default css -->
	<link rel="stylesheet" href="../assets/css/owl.theme.default.min.css">
	<!-- Magnific popup css -->
	<link rel="stylesheet" href="../assets/css/magnific-popup.min.css">
	<!-- Nouislider css -->
	<link rel="stylesheet" href="../assets/css/nouislider.css">
	<!-- navber css -->
	<link rel="stylesheet" href="../assets/css/navber.css">
	<!-- meanmenu css -->
	<link rel="stylesheet" href="../assets/css/meanmenu.css">
	<!-- Style css -->
	<link rel="stylesheet" href="../assets/css/style.css">
	<!-- Responsive css -->
	<link rel="stylesheet" href="../assets/css/responsive.css">
	<!-- Favicon -->
	<link rel="icon" type="image/png" href="../assets/img/favicon.png">
	<!-- icheck bootstrap -->
	<link rel="stylesheet" href="../plugins/icheck-bootstrap/icheck-bootstrap.min.css">
	<!-- Theme style -->
	<link rel="stylesheet" href="../assets/css/adminlte.min.css">
	<!-- My Css -->
	<link rel="stylesheet" href="../assets/css/forget_password.css">
</head>

<body>
	<div id="preloader">
		<div id="status">
			<img src="../assets/img/loader.gif" alt="img">
		</div>
	</div>

	<%@include file="../components/header.jsp"%>

	<div class="row justify-content-around" id="forget_password_menu">
		<div class="col-4 card card-outline card-warning">
			<div class="card-header text-center">
				<img src="../assets/img/Big_logo_v4.png" alt="" class="h1" id="top_logo">
			</div>
			<div class="card-body">
				<p class="login-box-msg">
					<b>取回您的密碼</b>
				</p>
				<figure class="text-center">
					<p class="description">請提供您的信箱以取得新密碼</p>
				</figure>

				<form action="#" method="post">

					<span id="verify_identity"></span>
					<div class="identity_select">
						<div class="col-auto">
							<b>會員身分：</b>
						</div>
						<div class="form-check form-check-inline member">
							<input class="form-check-input" type="radio" name="identity" id="identity_member" value="1">
							<label class="form-check-label" for="identity_member">一般會員</label>
						</div>

						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="identity" id="identity_admin" value="0">
							<label class="form-check-label" for="identity_admin">管理員</label>
						</div>

						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="identity" id="identity_seller" value="2">
							<label class="form-check-label" for="identity_seller">賣家</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="identity" id="identity_shelter"
								value="3"> <label class="form-check-label" for="identity_shelter">收容所</label>
						</div>
					</div>

					<span id="verify_account"></span>
					<div class="input-group mb-3">
						<input type="email" class="form-control" name="useraccount" id="useraccount" placeholder="會員帳號">
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-solid fa-user"></span>
							</div>
						</div>
					</div>

					<span id="verify_authencode"></span>
					<div class="mb-3">
						<div class="input-group">
							<input type="text" class="form-control" name="authencode" id="authencode"
								placeholder="請輸入驗證碼">
							<div class="input-group-append">
								<button class="btn btn-warning" type="button" id="getauthencode">獲取驗證碼</button>
							</div>
						</div>
					</div>


					<hr>
					<div class="row">
						<div class="col-12">
							<button type="button" class="btn btn-primary btn-block" id="btn_newPwd">請求寄送新密碼</button>
						</div>
						<!-- /.col -->
					</div>
				</form>
				<p class="mt-3 mb-1">
					<a href="./member_login.html">返回會員登入</a><br> <a href="./backend_login.html">返回後台登入</a><br> <a
						href="#">返回首頁</a>
				</p>
			</div>
			<!-- /.login-card-body -->
		</div>
	</div>
	<!-- /.login-box -->
	<%@include file="../components/footer.jsp"%>
	<!-- jQuery -->
	<script src="../assets/js/jquery.min.js"></script>
	<!-- AdminLTE App -->
	<script src="../dist/js/adminlte.min.js"></script>
	<script src="../assets/js/forget_password.js"></script>
</body>

</html>