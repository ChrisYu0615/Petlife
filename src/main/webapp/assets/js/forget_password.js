$(function() {
	$(".headerPage").load("../components/header.html");
	$(".footerPage").load("../components/footer.html");
	// 要傳送的位址
	let identity;
	let url;
	let verifyFlag;


	// 添加獲取驗證碼按鈕的點擊事件處理程序
	var getauthencode_btn = document.getElementById('getauthencode');
	getauthencode_btn.addEventListener('click', function() {
		verifyFlag = true;

		if ($("input[name='identity']:checked").length == 0) {
			$("#verify_identity").html("<font color='red'>請選擇登入身分!!</font>");
			verifyFlag = false;
		} else {
			$("#verify_identity").html("");
		}

		if ($.trim($("#useraccount").val()) == "") {
			verifyFlag = false;
			$("#verify_account").html("<font color='red'>請輸入帳號!!</font>");
		} else {
			$("#verify_account").html("");
		}


		if (verifyFlag == true) {
			// 禁用按鈕
			getauthencode_btn.disabled = true;

			// 開始計時60秒，期間無法再次按下獲取驗證碼按鈕
			var count = 60;
			var countdown = setInterval(function() {
				if (count > 0) {
					getauthencode_btn.textContent = count + ' 秒後可再次取得';
					count--;
				} else {
					// 啟用按鈕
					getauthencode_btn.disabled = false;
					getauthencode_btn.textContent = '取得驗證碼';
					clearInterval(countdown);
				}
			}, 1000);

			identity = $("input[name='identity']:checked").val();
			let key;
			switch (identity) {
				case '0':
					url = "/Petlife/admin/admin.do?action=getAuthenCode&value=newPwd";
					key = "adminaccount";
					break;
				case '1':
					url = "/Petlife/user/user.do?action=getAuthenCode&value=newPwd";
					key = "useraccount";
					break;
				case '2':
					url = "/Petlife/seller/seller.do?action=getAuthenCode&value=newPwd";
					key = "selleraccount";
					break;
				case '3':
					url = "/Petlife/shelter/shelter.do?action=getAuthenCode&value=newPwd";
					key = "shelteraccount";
					break;
			}

			let userAcct = $.trim($("#useraccount").val());
			let formData = new FormData();
			formData.append(key, userAcct);

			// 發送ajax到後端
			$.ajax({
				url: url,           // 資料請求的網址
				type: "POST",                  // GET | POST | PUT | DELETE | PATCH
				data: formData,             // 將物件資料(不用雙引號) 傳送到指定的 url
				dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
				contentType: false,
				processData: false,
				catch: false,
				success: function(data) {      // request 成功取得回應後執行
					console.log("成功");
					console.log(data);
				}
			});
		}
	});


	// 表單驗證，判斷是否有填入帳號與密碼
	$("#btn_newPwd").on("click", function() {
		verifyFlag = true;
		if ($("input[name='identity']:checked").length == 0) {
			$("#verify_identity").html("<font color='red'>請選擇登入身分!!</font>");
			verifyFlag = false;
		} else {
			$("#verify_identity").html("");
		}

		if ($.trim($("#useraccount").val()) == "") {
			verifyFlag = false;
			$("#verify_account").html("<font color='red'>請輸入帳號!!</font>");
		} else {
			$("#verify_account").html("");
		}

		if ($.trim($("#authencode").val()) == "") {
			verifyFlag = false;
			$("#verify_authencode").html("<font color='red'>請輸入驗證碼!!</font>");
		} else {
			$("#verify_authencode").html("");
		}

		if (verifyFlag == true) {
			identity = $("input[name='identity']:checked").val();
			switch (identity) {
				case '0':
					url = "/Petlife/admin/admin.do?action=forgetPwd";
					break;
				case '1':
					url = "/Petlife/user/user.do?action=forgetPwd";
					break;
				case '2':
					url = "/Petlife/seller/seller.do?action=forgetPwd";
					break;
				case '3':
					url = "/Petlife/shelter/shelter.do?action=forgetPwd";
					break;
			}

			let userAcct = $.trim($("#useraccount").val());
			let authenCode = $.trim($("#authencode").val());
			let formData = new FormData();
			formData.append("account", userAcct);
			formData.append("authencode", authenCode);

			// 發送ajax到後端
			$.ajax({
				url: url,           // 資料請求的網址
				type: "POST",                  // GET | POST | PUT | DELETE | PATCH
				data: formData,             // 將物件資料(不用雙引號) 傳送到指定的 url
				dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
				contentType: false,
				processData: false,
				catch: false,
				success: function(data) {      // request 成功取得回應後執行
					if ($.trim(data.accountErr).length != 0) {
						$("#verify_account").html(`<font color='red'>${data.accountErr}</font>`);
					}

					if ($.trim(data.authenCodeErr).length != 0) {
						$("#verify_authencode").html(`<font color='red'>${data.authenCodeErr}</font>`);
					}

					if ($.trim(data.success).length != 0) {
						alert(data.success);
					}
				}
			});

		}
	});
})