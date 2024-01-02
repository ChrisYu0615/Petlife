$(function() {
	// 顯示密碼功能
	$("#showPassword").on("change", function() {
		let password = $("#userpassword");

		if (this.checked) {
			password.attr("type", "text");
		} else {
			password.attr("type", "password");
		}
	});

	// 表單驗證，判斷是否有填入帳號與密碼
	var loginFlag = true;

	$("#btn_login").on("click", function() {
		loginFlag = true;
		if ($("input[name='identity']:checked").length == 0) {
			$("#verify_identity").html("<font color='red'>請選擇登入身分!!</font>");
			verifyFlag = false;
		} else {
			$("#verify_identity").html("");
		}

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
			let identity = $("input[name='identity']:checked").val();
			let url;
			switch (identity) {
				case '0':
					url = "/Petlife/login/login.do?action=adminLogin";
					break;
				case '2':
					url = "/Petlife/login/login.do?action=sellerLogin";
					break;
				case '3':
					url = "/Petlife/login/login.do?action=shelterLogin";
					break;
			}

			console.log("正確，開始ajax");
			let loginAcct = $.trim($("#useraccount").val());
			let loginPwd = $.trim($("#userpassword").val());

			let formData = new FormData();
			formData.append("account", loginAcct);
			formData.append("password", loginPwd);

			$.ajax({
				url: url,           // 資料請求的網址
				type: "POST",                  // GET | POST | PUT | DELETE | PATCH
				data: formData,             // 將物件資料(不用雙引號) 傳送到指定的 url
				dataType: "html",             // 預期會接收到回傳資料的格式： json | xml | html
				contentType: false,
				processData: false,
				cache: false,
				success: function(data) {// request 成功取得回應後執行
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
						case '待補件':
							alert("當前帳號尚未遞交補件資料，無法登入!!");
							break;
						case '待審核':
							alert("當前帳號還未經管理員審核，無法登入!!");
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
});
function redirectPage(newUrl) {
	window.location.href = newUrl;
}