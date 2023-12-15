<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="com.petlife.*"%>

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
		-->

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
						<form id="shelter_update_put" method="post"
							action="${pageContext.request.contextPath}/shelter/shelter.do">
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
							</span> <span class="material_list"> <label
								for="shelter_password1">密碼:</label> <input class="form-control"
								type="password" name="password"
								aria-label="default input example" id="shelter_password1"
								value="${shelter.shelterPwd}"> <span
								id="shelter_password1"></span>
							</span> <span class="material_list"> <label
								for="shelter_password1">確認密碼:</label> <input
								class="form-control" type="password" name="shelterPwd_two"
								aria-label="default input example" id="shelter_password2">
								<span id="shelter_password2"></span>
							</span> <span class="material_list"> <label for="shelter_phone">電話:</label>
								<input class="form-control" type="text" name="shelterPhoneNum"
								aria-label="default input example" id="shelterPhoneNum"
								value="${shelter.shelterPhoneNum}"> <span
								id="shelterPhoneNum"></span> -->
							</span> <span class="material_list"> <label for="shelter_adress">詳細地址:</label>
								<input class="form-control" type="text" name="shelterAddress"
								aria-label="default input example" id="shelterAddress"
								value="${shelter.shelterAddress}"> <span
								id="shelterAddress"></span> -->
							</span> <span class="material_list"> <label
								for="shelter_introduction">簡介:</label> <textarea
									id="shelterIntroduction" name="shelterIntroduction" rows="4"
									cols="50">${shelter.shelterIntroduction}</textarea> <span
								id="shelterIntroduction"></span>
							</span> <span class="material_list"> <span class="e_photo">環境照片:<input
									type="file" id="e_photo" multiple></span>
								<div id="preview">
									<span class="text">預覽圖(可一次選擇多張)</span>
								</div>

							</span>

							<div class="button">
								<input type="submit" value="確認修改" id="btn_shelter_update">
								<input type="hidden" name="action" value="update_put">
							</div>


							<!-- 							右上角大頭貼   -->
							<div class="img">
								<span>
									<div class="s_img" id="s_img"></div>
								</span> <input type="file" id="s_blob">
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
		<script> 
			// 這是環境照片
			$("#e_photo").change(function() {
				// ... (rest of the script for handling environment photos) ...
			});

			// 大頭貼
			$("#s_blob").change(function() {
				// ... (rest of the script for handling avatar) ...
			});
			//===================================================================
			//英文&中文
			var regex2 = /^[a-zA-Z\u4E00-\u9FA5]+$/;
			//中文與數字
			var addressregex = /^[0-9\u4E00-\u9FA5]+$/;
			//email
			var emailregex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
			//英文,數字
			var regex = /^[a-zA-Z0-9]+$/;
			//電話
			var phoneRegex = /^09\d{8}|02\d{8}$/;
			

			//密碼驗證
			$("#shelter_password1").on(
					"blur",
					function() {
						var verifyFlag = true;
						var shelter_password1 = $(this).val();
						if (shelter_password1 == "") {
							$("span#shelter_password1").html(
									"<font color='red'>請勿空白!!</font>")
							verifyFlag = false;
						} else if (!regex.test(shelter_password1)) {
							$("span#shelter_password1").html(
									"<font color='red'>只能輸入英文及中文!!</font>")
							verifyFlag = false;
						}
					})
			//確認密碼與上方密碼驗證
			$("#shelter_password2").on(
					"blur",
					function() {
						var verifyFlag = true;
						var shelter_password2 = $("#shelter_password2").val();
						var shelter_password1 = $("#shelter_password1").val();
						if (shelter_password2 == "") {
							$("span#shelter_password2").html(
									"<font color='red'>請勿空白!!</font>")
							verifyFlag = false;
						} else if (!regex.test(shelter_password2)) {
							$("span#shelter_password2").html(
									"<font color='red'>只能輸入英文及中文!!</font>")
							verifyFlag = false;
						} else if (shelter_password1 != shelter_password2) {
							$("span#shelter_password2").html(
									"<font color='red'>密碼有誤!!</font>")
							verifyFlag = false;
						}
					})

			//收容所名稱點擊後input清空
			$("#shelter_name").on("click", function() {
				$("#shelter_name").val("")
			})
			//收容所帳號點擊後input清空
			$("#shelterAcct").on("click", function() {
				$("#shelterAcct").val("")
			})
			//收容所密碼點擊後input清空
			$(document).on("click", "#shelter_password1", function() {
				$("#shelter_password1").val("")
				$("span#shelter_password1").html("")
			})
			//收容所確認密碼點擊後input清空
			$(document).on("click", "#shelter_password2", function() {
				$("#shelter_password2").val("")
				$("span#shelter_password2").html("")
			})
			//收容所電話
			$(document).on("click", "#shelterPhoneNum", function() {
				$("#shelterPhoneNum").val("")
				$("span#shelterPhoneNum").html("")
			})

			$("#btn_shelter_update").on("click", function(e) {
					//取消送出預設
					e.preventDefault();
					//使用布林判斷是否可submit
					var verifyFlag = true;
					//抓取所有input.val()裝入var 以下使用
					var shelter_name = $("#shelter_name").val().trim();
					var shelterAcct = $("#shelterAcct").val().trim();
					var shelterPhoneNum = $("#shelterPhoneNum").val()
							.trim();
					var shelterAddress = $("#shelterAddress").val().trim();
					var shelterIntroduction = $("#shelterIntroduction")
							.val().trim();
					//收容所名稱驗證
					if (shelter_name.length === 0) {
						$("span#shelterName").html(
								"<font color='red'>請勿空白!!</font>")
						verifyFlag = false;
					} else if (!regex2.test(shelter_name)) {
						$("span#shelterName").html(
								"<font color='red'>只能輸入英文及中文!!</font>")
						verifyFlag = false;
					} else if (shelter_name.length > 50) {
						$("span#shelterName").html(
								"<font color='red'>最多只能輸入50個字!!</font>")
					}
					//收容所帳號驗證(email)
					if (shelterAcct.length === 0) {
						$("span#shelterAcct").html(
								"<font color='red'>請勿空白!!</font>")
						verifyFlag = false;
					} else if (!emailregex.test(shelterAcct)) {
						$("span#shelterAcct").html(
								"<font color='red'>請輸入emil格式!!</font>")
						verifyFlag = false;
					} else if (shelterAcct.length === 0) {
						$("span#shelterAcct").html(
								"<font color='red'>最多只能輸入40個字!!</font>")
						verifyFlag = false;
					}
					//收容所電話驗證
					if (shelterPhoneNum.length === 0) {
						$("span#shelterPhoneNum").html(
								"<font color='red'>請勿空白!!</font>")
						verifyFlag = false;
					} else if (!phoneRegex.test(shelterPhoneNum)) {
						$("span#shelterPhoneNum").html(
								"<font color='red'>請輸入電話格式(10碼)!!</font>")
						verifyFlag = false;
					} else if (shelterPhoneNum.length > 20) {
						$("span#shelterPhoneNum").html(
								"<font color='red'>最多只能輸入20個字!!</font>")
					}
					//收容所地址驗證
					if (shelterAddress.length === 0) {
						$("span#shelterAddress").html(
								"<font color='red'>請勿空白!!</font>")
						verifyFlag = false;
					} else if (!addressregex.test(shelterAddress)) {
						$("span#shelterAddress").html(
								"<font color='red'>只能輸入中文及數字!!</font>")
						verifyFlag = false;
					} else if (shelterAddress.length > 40) {
						$("span#shelterAddress").html(
								"<font color='red'>最多只能輸入40個字!!</font>")
						verifyFlag = false;
					}
					//收容所簡介驗證
					if (shelterIntroduction.length === 0) {
						$("span#shelterIntroduction").html(
								"<font color='red'>請勿空白!!</font>")
						verifyFlag = false;
					}
					

					if (verifyFlag == false) {
						window.document.documentElement.scrollTop = 0;
						return;
					} else {
						$("#shelter_update_put").submit();
					}

				})
 		</script>
</body>
</html>
