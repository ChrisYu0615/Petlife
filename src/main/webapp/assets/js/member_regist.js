$(".headerPage").load("../header.html");
$(".footerPage").load("../footer.html");

$(function() {
	// 添加獲取驗證碼按鈕的點擊事件處理程序
	var getauthencode_btn = document.getElementById('getauthencode');
	getauthencode_btn.addEventListener('click', function() {
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
				// getauthencode_btn.classList.remove("btn btn-secondary");
				// getauthencode_btn.classList.add("btn btn-primary");
				getauthencode_btn.disabled = false;
				getauthencode_btn.textContent = '取得驗證碼';
				clearInterval(countdown);
			}
		}, 1000);
	});

	// 密碼顯示切換功能
	var passwordInput = document.getElementById('password');
	var comfirmPasswordInput = document.getElementById('confirm-password');

	var showPasswordCheckbox = document.getElementById('showPassword');

	showPasswordCheckbox.addEventListener('change', function() {
		if (showPasswordCheckbox.checked) {
			// 顯示密碼
			passwordInput.type = 'text';
			comfirmPasswordInput.type = 'text';
		} else {
			// 隱藏密碼
			passwordInput.type = 'password';
			comfirmPasswordInput.type = 'password';
		}
	});

	// 初始化日期選擇器
	$(document).ready(function() {
		$('#birthdate').datepicker({
			format: 'yyyy-mm-dd',
			language: 'zh-TW',
			autoclose: true
		});
	});

	// 在出生年月日框框和旁邊的日曆按鈕新增點擊事件，點擊時會打開日期選擇器
	$('#birthdate + .input-group-text').on('click', function() {
		$('#birthdate').datepicker('show');
	});


	// 使用Fetch API載入JSON檔案，讀取縣市的所有選項
	fetch('../assets/json/cities.json')
		.then(response => response.json())
		.then(data => {
			var countySelect = document.getElementById('county');
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


	// 前端驗證區塊
	var verifyFlag = true;

	// 使用ajax判斷暱稱是否重複
	var nickname = document.getElementById("nickname");
	nickname.addEventListener("blur", function() {
		document.getElementById("verify_nickname").innerHTML = "";
		console.log("發送ajax請求");

		var xhr = new XMLHttpRequest();

		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
				if (xhr.status == 200) {
					let verifyResult = xhr.responseText;
					document.getElementById("verify_nickname").innerHTML = xhr.responseText;
					if (verifyResult.includes("暱稱重複")) {
						verifyFlag = false;
					}
					console.log(xhr.responseText);
				} else {
					alert(xhr.status);
				}
			}
		}

		xhr.open("POST", "/Petlife/user/user.do", true);

		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

		var nicknameval = document.getElementById("nickname").value;
		xhr.send("action=verify&nickname=" + nicknameval);
	});


	// 使用ajax判斷會員帳號是否重複
	var userAccount = document.getElementById("useraccount");
	userAccount.addEventListener("blur", function() {
		document.getElementById("verify_useraccount").innerHTML = "";
		console.log("發送ajax請求");

		var xhr = new XMLHttpRequest();

		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
				if (xhr.status == 200) {
					let verifyResult = xhr.responseText;
					document.getElementById("verify_useraccount").innerHTML = xhr.responseText;
					if (verifyResult.includes("帳號重複")) {
						verifyFlag = false;
					}
					console.log(xhr.responseText);
				} else {
					alert(xhr.status);
				}
			}
		}

		xhr.open("POST", "/Petlife/user/user.do", true);

		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

		var userAccountVal = document.getElementById("useraccount").value;
		xhr.send("action=verify&useraccount=" + userAccountVal);
	});

	// 針對輸入框輸入後change事件，輸入任何字後就會把錯誤訊息消除
	$("#useraccount").change(function() {
		if ($.trim($("#useraccount").val()) != "") {
			$("#verify_useraccount").html("");
		}
	});

	$("#authencode").change(function() {
		if ($.trim($("#authencode").val()) != "") {
			$("#verify_authencode").html("");
		}
	});

	$("#password").change(function() {
		if ($.trim($("#password").val()) != "") {
			$("#verify_password").html("");
		}
	});

	$("#confirm-password").change(function() {
		if ($.trim($("#password").val()) != "" && $.trim($("#password").val()) != $.trim($("#confirm-password").val())) {
			$("#verify_confirm-password").html("<font color='red'>確認密碼不一致!!</font>");
		} else {
			$("#verify_confirm-password").html("<font color='green'>確認密碼一致!!</font>");
		}
	});

	$("#username").change(function() {
		if ($.trim($("#username").val()) != "") {
			$("#verify_username").html("");
		}
	});

	$("#nickname").change(function() {
		if ($.trim($("#nickname").val()) != "") {
			$("#verify_nickname").html("");
		}
	});

	$("#male,#female").change(function() {
		if ($("input[name='gender']:checked").length > 0) {
			$("#verify_gender").html("");
		}
	});

	$("#birthdate").change(function() {
		if ($.trim($("#birthdate").val()) != "") {
			$("#verify_birthdate").html("");
		}
	});

	$("#phone").change(function() {
		if ($.trim($("#phone").val()) != "") {
			$("#verify_phone").html("");
		}
	});

	$("#county").change(function() {
		if ($.trim($("#county").val()) != "") {
			$("#verify_county").html("");
		}
	});

	$("#district").change(function() {
		if ($.trim($("#district").val()) != "") {
			$("#verify_district").html("");
		}
	});

	$("#address").change(function() {
		if ($.trim($("#address").val()) != "") {
			$("#verify_address").html("");
		}
	});

	// 當表單提交時，驗證有無欄位沒有輸入
	$("#regist_form").submit(function(event) {
		verifyFlag = true;
		if ($.trim($("#useraccount").val()) == "") {
			$("#verify_useraccount").html("<font color='red'>請輸入會員帳號!!</font>");
			verifyFlag = false;
		} else {
			$("#verify_useraccount").html("");
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

		if ($.trim($("#confirm-password").val()) == "") {
			$("#verify_confirm-password").html("<font color='red'>請輸入確認密碼!!</font>");
			verifyFlag = false;
		} else if ($.trim($("#password").val()) != "" && $.trim($("#password").val()) != $.trim($("#confirm-password").val())) {
			$("#verify_confirm-password").html("<font color='red'>確認密碼不一致!!</font>");
		} else {
			$("#verify_confirm-password").html("");
		}

		if ($.trim($("#username").val()) == "") {
			$("#verify_username").html("<font color='red'>請輸入會員姓名!!</font>");
			verifyFlag = false;
		} else {
			$("#verify_username").html("");
		}

		if ($("input[name='gender']:checked").length == 0) {
			$("#verify_gender").html("<font color='red'>請選擇性別!!</font>");
			verifyFlag = false;
		} else {
			$("#verify_gender").html("");
		}

		if ($.trim($("#birthdate").val()) == "") {
			$("#verify_birthdate").html("<font color='red'>請選擇出生年月日!!</font>");
			verifyFlag = false;
		} else {
			$("#verify_birthdate").html("");
		}

		if ($.trim($("#phone").val()) == "") {
			$("#verify_phone").html("<font color='red'>請輸入手機號碼!!</font>");
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
			event.preventDefault();
		}
	});

});

