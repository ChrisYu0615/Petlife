document.addEventListener("DOMContentLoaded", function() {

	// 添加獲取驗證碼按鈕的點擊事件處理程序
	var getauthencode_btn = document.getElementById('getauthencode');
	getauthencode_btn.addEventListener('click', function() {

		if ($.trim($("#shelteraccount").val()) == "") {
			alert("請先輸入您的帳號才能獲取驗證碼!!");
			return;
		} else {
			if ($.trim($("#verify_shelteraccount").text()) == "帳號重複!!") {
				alert("請先檢查帳號重複問題!!");
				return;
			} else {
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

				let shelterAcct = $.trim($("#shelteraccount").val());
				let formData = new FormData();
				formData.append("shelteraccount", shelterAcct);

				// 發送ajax到後端
				$.ajax({
					url: "/Petlife/shelter/shelter.do?action=getAuthenCode",           // 資料請求的網址
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
		}
	});

	// 密碼顯示切換功能
	var passwordInput = document.getElementById('password');
	var showPasswordCheckbox = document.getElementById('showPassword');

	showPasswordCheckbox.addEventListener('change', function() {
		if (showPasswordCheckbox.checked) {
			// 顯示密碼
			passwordInput.type = 'text';
		} else {
			// 隱藏密碼
			passwordInput.type = 'password';
		}
	});

	// 使用Fetch API載入JSON檔案，讀取縣市的所有選項
	fetch('../assets/json/cities.json')
		.then(response => response.json())
		.then(data => {
			var countySelect = document.getElementById('country');
			for (var city in data) {
				var option = document.createElement('option');
				option.value = city;
				option.textContent = city;
				countySelect.appendChild(option);
			}

			// 在選擇縣市時會自動對應到該縣市有的行政區
			countySelect.addEventListener('change', function() {
				var selectedCity = countySelect.value;
				var districtSelect = document.getElementById('district');
				districtSelect.innerHTML = ''; // 清空行政區選項

				if (selectedCity in data) {
					var districts = data[selectedCity];
					for (var i = 0; i < districts.length; i++) {
						var districtOption = document.createElement('option');
						districtOption.value = districts[i];
						districtOption.textContent = districts[i];
						districtSelect.appendChild(districtOption);
					}
				}
			});
		})
		.catch(error => console.error('縣市行政區JSON檔案載入失敗：', error));

	// 按下取消按鈕(直接返回首頁)
	$("#btn_cancel").on("click", function() {
		window.location.href = "/Petlife/index.jsp";
	});

	// 前端驗證區塊
	var verifyFlag = true;

	// 使用ajax判斷會員帳號是否重複
	var userAccount = document.getElementById("shelteraccount");
	userAccount.addEventListener("blur", function() {
		document.getElementById("verify_shelteraccount").innerHTML = "";
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
				if (xhr.status == 200) {
					let verifyResult = xhr.responseText;
					document.getElementById("verify_shelteraccount").innerHTML = xhr.responseText;
					if (verifyResult.includes("帳號重複")) {
						$("#btn_regist").prop("disabled", true);
					} else {
						$("#btn_regist").prop("disabled", false);
					}
				} else {
					alert(xhr.status);
				}
			}
		}
		xhr.open("POST", "/Petlife/shelter/shelter.do", true);
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		var shelterAccountVal = document.getElementById("shelteraccount").value;
		xhr.send("action=verify&shelteraccount=" + shelterAccountVal);
	});

	// 針對輸入框輸入後blur事件，輸入任何字後就會把錯誤訊息消除
	$("#shelteraccount").blur(function() {
		if ($.trim($("#shelteraccount").val()) != "") {
			$("#verify_shelteraccount").html("");
		}
	});

	$("#authencode").blur(function() {
		if ($.trim($("#authencode").val()) != "") {
			$("#verify_authencode").html("");
		}
	});

	$("#password").blur(function() {
		if ($.trim($("#password").val()) != "") {
			$("#verify_password").html("");
		}
	});

	$("#sheltername").blur(function() {
		if ($.trim($("#sheltername").val()) != "") {
			$("#verify_sheltername").html("");
		}
	});

	$("#phone").blur(function() {
		if ($.trim($("#phone").val()) != "") {
			$("#verify_phone").html("");
		}
	});

	$("#county").blur(function() {
		if ($.trim($("#county").val()) != "") {
			$("#verify_county").html("");
		}
	});

	$("#district").blur(function() {
		if ($.trim($("#district").val()) != "") {
			$("#verify_district").html("");
		}
	});

	$("#address").blur(function() {
		if ($.trim($("#address").val()) != "") {
			$("#verify_address").html("");
		}
	});


	// 當表單提交時，驗證有無欄位沒有輸入
	$("#btn_regist").on("click", function() {
		verifyFlag = true;
		if ($.trim($("#shelteraccount").val()) == "") {
			$("#verify_shelteraccount").html("<font color='red'>請輸入會員帳號!!</font>");
			verifyFlag = false;
		}

		if ($.trim($("#authencode").val()) == "") {
			$("#verify_authencode").html("<font color='red'>請輸入驗證碼!!</font>");
			verifyFlag = false;
		} else {
			$("#verify_authencode").html("");
		}

		if ($.trim($("#password").val()) == "") {
			$("#verify_password").html("<font color='red'>請輸入密碼!!</font>");
			verifyFlag = false;
		} else {
			$("#verify_password").html("");
		}

		if ($.trim($("#sheltername").val()) == "") {
			$("#verify_sheltername").html("<font color='red'>請輸入收容所名稱!</font>");
			verifyFlag = false;
		} else {
			$("#verify_sheltername").html("");
		}

		if ($.trim($("#phone").val()) == "") {
			$("#verify_phone").html("<font color='red'>請輸入電話號碼!!</font>");
			verifyFlag = false;
		} else {
			$("#verify_phone").html("");
		}

		if ($.trim($("#county").val()) == "選擇縣市") {
			$("#verify_county").html("<font color='red'>請選擇縣市!!</font>");
			verifyFlag = false;
		} else {
			$("#verify_county").html("");
		}

		if ($.trim($("#district").val()) == "") {
			$("#verify_district").html("<font color='red'>請選擇行政區!!</font>");
			verifyFlag = false;
		} else {
			$("#verify_district").html("");
		}

		if ($.trim($("#address").val()) == "") {
			$("#verify_address").html("<font color='red'>請輸入住址!!</font>");
			verifyFlag = false;
		} else {
			$("#verify_address").html("");
		}

		if (verifyFlag == false) {
			$("html, body").scrollTop(0);
		} else {
			let shelterAccount = $.trim($("#shelteraccount").val());
			let authencode = $.trim($("#authencode").val());
			let password = $.trim($("#password").val());
			let shelterName = $.trim($("#sheltername").val());
			let phone = $.trim($("#phone").val());
			let country = $.trim($("#country").val());
			let district = $.trim($("#district").val());
			let address = $.trim($("#address").val());

			let formData = new FormData();
			formData.append("shelteraccount", shelterAccount);
			formData.append("authencode", authencode);
			formData.append("password", password);
			formData.append("shelterName", shelterName);
			formData.append("phone", phone);
			formData.append("country", country);
			formData.append("district", district);
			formData.append("address", address);

			console.log($(location).attr("hash"));
			console.log($(location).attr("host"));
			console.log($(location).attr("hostname"));
			console.log($(location).attr("href"));
			console.log($(location).attr("origin"));
			console.log($(location).attr("pathname"));
			console.log($(location).attr("port"));
			console.log($(location).attr("protocol"));
			console.log($(location).attr("search"));

			$.ajax({
				url: "/Petlife/shelter/shelter.do?action=shelterRegister",           // 資料請求的網址
				type: "POST",                  // GET | POST | PUT | DELETE | PATCH
				data: formData,             // 將物件資料(不用雙引號) 傳送到指定的 url
				dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
				contentType: false,
				processData: false,
				catch: false,
				success: function(data) {      // request 成功取得回應後執行
					if (typeof data === 'string' && data.endsWith("jsp")) {
						redirectPage(data);
						return;
					}
					else if (data != null) {
						if ($.trim(data.shelterPwdErr).length != 0) {
							$("#verify_password").html(`<font color='red'>${data.shelterPwdErr}</font>`);
						}
						if ($.trim(data.shelterAuthenCodeErr).length != 0) {
							$("#verify_authencode").html(`<font color='red'>${data.shelterAuthenCodeErr}</font>`);
						}
						if ($.trim(data.shelterNameErr).length != 0) {
							$("#verify_sheltername").html(`<font color='red'>${data.shelterNameErr}</font>`);
						}
						if ($.trim(data.shelterPhoneNumErr).length != 0) {
							$("#verify_phone").html(`<font color='red'>${data.shelterPhoneNumErr}</font>`);
						}
						if ($.trim(data.addressErr).length != 0) {
							$("#verify_address").html(`<font color='red'>${data.addressErr}</font>`);
						}
						$("html, body").scrollTop(0);
					}
				}
			});
		};
	});
});
function redirectPage(newUrl) {
	window.location.href = newUrl;
}