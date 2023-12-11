$(function() {
	$(".headerPage").load("../components/header.html");
	$(".footerPage").load("../components/footer.html");

	// 表單驗證，判斷是否有填入帳號與密碼
	var loginFlag = true;

	$("#btn_login").on("click", function() {
		loginFlag = true;
		if ($.trim($("#useraccount").val()) == "") {
			loginFlag = false;
			$("#verify_account").html("<font color='red'>請輸入登入帳號!!</font>");
		} else {
			$("#verify_account").html("");
		}

		if ($.trim($("#userpassword").val()) == "") {
			loginFlag = false;
			$("#verify_password").html("<font color='red'>請輸入密碼!!</font>");
		} else {
			$("#verify_password").html("");
		}

		if (loginFlag == true) {
			console.log("正確，開始ajax");
			let userAcct = $.trim($("#useraccount").val());
			let userPwd = $.trim($("#userpassword").val());

			let formData = new FormData();
			formData.append("account", userAcct);
			formData.append("password", userPwd);

			$.ajax({
				url: "/Petlife/login/login.do?action=userLogin",           // 資料請求的網址
				type: "POST",                  // GET | POST | PUT | DELETE | PATCH
				data: formData,             // 將物件資料(不用雙引號) 傳送到指定的 url
				dataType: "html",             // 預期會接收到回傳資料的格式： json | xml | html
				contentType: false,
				processData: false,
				cache: false,
				success: function(data) {      // request 成功取得回應後執行
					switch (data) {
						case '帳號不存在':
							alert("帳號不存在!!");
							break;
						case '停權':
							alert("帳號已被停權，請和管理員聯繫!!");
							break;
						case '密碼錯誤已達5次':
							alert("密碼錯誤次數已達5次，請重新設置密碼!!");
							break;
						case '密碼錯誤未達5次':
							alert("密碼錯誤!!");
							break;
						default:
							redirectPage(data);
							break;
					}
				}, error: function(error) {
					// 處理錯誤
					console.error(error);
				}
			});
		}
	});
})
function redirectPage(newUrl) {
	window.location.href = newUrl;
}