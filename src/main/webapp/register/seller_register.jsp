<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh-TW">

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>賣家會員註冊</title>

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
	<link rel="stylesheet" href="../assets/css/regist.css">
</head>

<body>
	<!-- preloader Area -->
	<div id="preloader">
		<div id="status">
			<img src="../assets/img/loader.gif" alt="img">
		</div>
	</div>

	<%@include file="../components/header.jsp"%>

	<!-- Main content -->
	<section class="content">
		<div class="container-fluid">
			<div class="row justify-content-around">
				<!-- left column -->
				<div class="col-md-5">
					<!-- general form elements -->
					<div class="card card-warning">
						<div class="row card-header">
							<div class="col"></div>
							<h2 class="col-auto">
								<img src="../assets/img/favicon.png" alt="">賣家會員註冊
							</h2>
							<div class="col"></div>
						</div>
						<!-- /.card-header -->
						<!-- form start -->
						<form action="/Petlife/seller/seller.do" method="post" id="regist_form">
							<div class="card-body">

								<div class="form-group mb-3">
									<label for="useraccount" class="form-label">會員帳號(Email)
										<span class="must_insert">*</span>
									</label> <input type="email" class="form-control" id="useraccount"
										name="useraccount" placeholder="請輸入Email"> <span class="verify_result"
										id="verify_useraccount"></span>
								</div>

								<div class="form-group mb-3">
									<label for="authencode" class="form-label">輸入驗證碼 <span class="must_insert">*</span>
									</label>
									<div class="input-group">
										<input type="text" class="form-control" name="authencode" id="authencode"
											placeholder="請輸入驗證碼">
										<div class="input-group-append">
											<button class="btn btn-primary" type="button"
												id="getauthencode">獲取驗證碼</button>
										</div>
									</div>
									<span class="verify_result" id="verify_authencode"></span>
								</div>

								<div class="form-group mb-3">
									<label for="password" class="form-label">密碼 <span class="must_insert">*</span>
									</label> <input type="password" class="form-control" id="password" name="password"
										placeholder="請輸入密碼"> <span class="verify_result" id="verify_password"></span>
								</div>

								<div class="form-check mb-3">
									<input class="form-check-input" type="checkbox" id="showPassword"> <label
										class="form-check-label" for="showPassword"> 顯示密碼 </label>
								</div>

								<div class="form-group mb-3">
									<label for="username" class="form-label">姓名</label> <span
										class="must_insert">*</span> <input type="text" class="form-control"
										id="username" name="username" placeholder="請輸入姓名"> <span class="verify_result"
										id="verify_username"></span>
								</div>

								<div class="form-group mb-3">
									<label for="seller_idenfication" class="form-label">身分證字號</label> <span
										class="must_insert">*</span> <input type="text" class="form-control"
										id="seller_idenfication" name="seller_idenfication" placeholder="請輸入姓名"> <span
										class="verify_result" id="verify_seller_idenfication"></span>
								</div>


								<div class="form-group mb-3">
									<label for="shopname" class="form-label">賣場名稱</label> <input type="text"
										class="form-control" id="shopname" name="shopname" placeholder="請輸入暱稱"> <span
										class="verify_result" id="verify_shopname"></span>
								</div>

								<div class="form-group mb-3">
									<label for="" class="form-label">性別 <span class="must_insert">*</span>
									</label><br>
									<div class="form-check form-check-inline">
										<input class="form-check-input" type="radio" name="gender" id="male"
											value="false"> <label class="form-check-label" for="male">男</label>
									</div>

									<div class="form-check form-check-inline">
										<input class="form-check-input" type="radio" name="gender" id="female"
											value="true"> <label class="form-check-label" for="female">女</label>
									</div>
									<div>
										<span class="verify_result" id="verify_gender"></span>
									</div>
								</div>

								<div class="form-group mb-3">
									<label for="birthdate" class="form-label">出生年月日 <span class="must_insert">*</span>
									</label>
									<div class="input-group">
										<input type="date" class="form-control" id="birthdate" name="birthdate"
											placeholder="選擇出生日期">
									</div>
									<span class="verify_result" id="verify_birthdate"></span>
								</div>

								<div class="form-group">
									<label for="phone" class="form-label">手機號碼 <span class="must_insert">*</span>
									</label> <input type="tel" class="form-control" id="phone" name="phone"
										placeholder="請輸入手機號碼"> <span class="verify_result" id="verify_phone"></span>
								</div>

								<div class="row">
									<div class="form-group col-auto">
										<label for="county" class="form-label">縣市 <span class="must_insert">*</span>
										</label> <br> <select class="form-select" id="county" name="country">
										</select>
									</div>
									<div class="form-group col-auto offset-1">
										<label for="district" class="form-label">行政區 <span class="must_insert">*</span>
										</label> <br> <select class="form-select" id="district" name="district">
											<option value="">選擇行政區</option>
										</select>
									</div>
								</div>
								<div class="mb-3">
									<span class="verify_result" id="verify_county"></span> <br>
									<span class="verify_result" id="verify_district"></span>
								</div>

								<div class="form-group mb-3">
									<label for="address" class="form-label">地址 <span class="must_insert">*</span>
									</label> <input type="text" class="form-control" id="address" name="address"
										placeholder="請輸入詳細地址"> <span class="verify_result" id="verify_address"></span>
								</div>


								<div class="row mb-3">
									<div class="col-9">
										<label for="bankcode" class="form-label">金融機構代號 <span
												class="must_insert">*</span>
										</label> <select class="form-select" id="bankcode" name="bankcode">
										</select>
									</div>
								</div>
								<div class="mb-3">
									<span class="verify_result" id="verify_bankcode"></span>
								</div>


								<div class="mb-3">
									<label for="bankaccount" class="form-label">金融帳號 <span class="must_insert">*</span>
									</label> <input type="text" class="form-control" id="bankaccount" name="bankaccount"
										placeholder="請輸入金融帳號"> <span class="verify_result"
										id="verify_bankaccount"></span>
								</div>


								<div class="row">
									<div class="form-group col-md-6">
										<label for="idcard-front" class="form-label">請上傳身分證正面
											<span class="must_insert">*</span><br> <span class="verify_result"
												id="verify_idcard-front"></span>
										</label> <input type="file" class="form-control-sm" id="idcard-front"
											name="idcard-front" accept="image/*"> <img class="image-preview"
											id="idcard-front-preview" src="" alt="" style="width: 250px;">
									</div>

									<div class="form-group col-md-6">
										<label for="idcard-back" class="form-label">請上傳身分證背面 <span
												class="must_insert">*</span><br> <span class="verify_result"
												id="verify_idcard-back"></span>
										</label> <input type="file" class="form-control-sm" id="idcard-back"
											name="idcard-back" accept="image/*"> <img class="image-preview"
											id="idcard-back-preview" src="" alt="" style="width: 250px;">
									</div>
								</div>

								<div class="row">
									<div class="form-group col-md-6">
										<label for="account-img" class="form-label">請上傳帳戶資料 <span
												class="must_insert">*</span><br> <span class="verify_result"
												id="verify_account-img"></span>
										</label> <input type="file" class="form-control-sm" id="account-img"
											name="account-img" accept="image/*"> <img class="image-preview"
											id="account-img-preview" src="" alt="" style="width: 250px;">
									</div>
								</div>

							</div>
							<!-- /.card-body -->

							<div class="row card-footer">
								<div class="col"></div>

								<div class="col-auto">
									<button type="button" class="btn btn-primary" id="btn_regist">註冊</button>
								</div>

								<div class="col-auto offset-1">
									<button type="button" class="btn btn-warning" id="btn_cancel">取消</button>
								</div>

								<div class="col"></div>
							</div>
						</form>
					</div>
				</div>
			</div>
	</section>

	<%@include file="../components/footer.jsp"%>
	<!-- /.card -->
	<!-- jQuery -->
	<script src="../assets/js/jquery.min.js"></script>
	<!-- AdminLTE App -->
	<script src="../dist/js/adminlte.min.js"></script>
	<script src="../assets/js/seller_regist.js"></script>
</body>

</html>