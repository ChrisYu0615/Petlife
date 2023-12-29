document.addEventListener("DOMContentLoaded", function () {

	// 添加獲取驗證碼按鈕的點擊事件處理程序
	var getauthencode_btn = document.getElementById('getauthencode');
	getauthencode_btn.addEventListener('click', function () {

		if ($.trim($("#useraccount").val()) == "") {
			alert("請先輸入您的帳號才能獲取驗證碼!!");
			return;
		} else {
			if ($.trim($("#verify_useraccount").text()) == "帳號重複!!") {
				alert("請先檢查帳號重複問題!!");
				return;
			} else {
				// 禁用按鈕
				getauthencode_btn.disabled = true;

				// 開始計時60秒，期間無法再次按下獲取驗證碼按鈕
				var count = 60;
				var countdown = setInterval(function () {
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

				let sellerAcct = $.trim($("#useraccount").val());
				let formData = new FormData();
				formData.append("selleraccount", sellerAcct);

				// 發送ajax到後端
				$.ajax({
					url: "/Petlife/seller/seller.do?action=getAuthenCode",           // 資料請求的網址
					type: "POST",                  // GET | POST | PUT | DELETE | PATCH
					data: formData,             // 將物件資料(不用雙引號) 傳送到指定的 url
					dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
					contentType: false,
					processData: false,
					catch: false,
					success: function (data) {      // request 成功取得回應後執行
						console.log("成功");
						console.log(data);
					}
				});
			}
		}
	});

	// 限制生日只能選到當前日期
	var nowDate = new Date();
	var year = nowDate.getFullYear();
	var month = nowDate.getMonth + 1 < 10 ? "0" + (nowDate.getMonth() + 1) : (nowDate.getMonth() + 1);
	var date = nowDate.getDate() < 10 ? "0" + nowDate.getDate() : nowDate.getDate();
	$("#birthdate").attr("max", year + "-" + month + "-" + date);

	// 密碼顯示切換功能
	var passwordInput = document.getElementById('password');

	var showPasswordCheckbox = document.getElementById('showPassword');

	showPasswordCheckbox.addEventListener('change', function () {
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
			var countySelect = document.getElementById('county');
			for (var city in data) {
				var option = document.createElement('option');
				option.value = city;
				option.textContent = city;
				countySelect.appendChild(option);
			}

			// 在選擇縣市時會自動對應到該縣市有的行政區
			countySelect.addEventListener('change', function () {
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


	// 讀取JSON檔案(銀行代碼)
	fetch('../assets/json/taiwanBankCode.json')
		.then(response => response.json())
		.then(data => {
			// 取得下拉式選單元素
			const dropdown = document.getElementById('bankcode');

			// 迭代JSON數據，並將每一個選項添加到下拉式選單中
			data.forEach(item => {
				const option = document.createElement('option');
				option.value = item.name;  // 將name作為值
				option.text = item.name;   // 使用name作為顯示文字
				dropdown.add(option);
			});
		})
		.catch(error => console.error('Error fetching JSON:', error));

	// 照片上傳預覽
	const idcardFront = document.getElementById('idcard-front');
	const idcardFrontPreview = document.getElementById('idcard-front-preview');

	idcardFront.addEventListener('change', function () {
		if (idcardFront.files && idcardFront.files[0]) {
			const reader = new FileReader();
			reader.onload = function (e) {
				idcardFrontPreview.src = e.target.result;
			};
			reader.readAsDataURL(idcardFront.files[0]);
		}
	});

	const idcardBack = document.getElementById('idcard-back');
	const idcardBackPreview = document.getElementById('idcard-back-preview');

	idcardBack.addEventListener('change', function () {
		if (idcardBack.files && idcardBack.files[0]) {
			const reader = new FileReader();
			reader.onload = function (e) {
				idcardBackPreview.src = e.target.result;
			};
			reader.readAsDataURL(idcardBack.files[0]);
		}
	});

	const accountImg = document.getElementById('account-img');
	const accountImgPreview = document.getElementById('account-img-preview');

	accountImg.addEventListener('change', function () {
		if (accountImg.files && accountImg.files[0]) {
			const reader = new FileReader();
			reader.onload = function (e) {
				accountImgPreview.src = e.target.result;
			};
			reader.readAsDataURL(accountImg.files[0]);
		}
	});

	// 按下取消按鈕(直接返回首頁)
	$("#btn_cancel").on("click", function () {
		window.location.href = "/Petlife/index.html";
	});

	// 前端驗證區塊
	var verifyFlag = true;

	// 使用ajax判斷會員帳號是否重複
	var userAccount = document.getElementById("useraccount");
	userAccount.addEventListener("blur", function () {
		document.getElementById("verify_useraccount").innerHTML = "";
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function () {
			if (xhr.readyState == 4) {
				if (xhr.status == 200) {
					let verifyResult = xhr.responseText;
					document.getElementById("verify_useraccount").innerHTML = xhr.responseText;
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
		xhr.open("POST", "/Petlife/seller/seller.do", true);
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		var userAccountVal = document.getElementById("useraccount").value;
		xhr.send("action=verify&selleraccount=" + userAccountVal);
	});


	// 針對輸入框輸入後blur事件，輸入任何字後就會把錯誤訊息消除
	$("#useraccount").blur(function () {
		if ($.trim($("#useraccount").val()) != "") {
			$("#verify_useraccount").html("");
		}
	});

	$("#authencode").blur(function () {
		if ($.trim($("#authencode").val()) != "") {
			$("#verify_authencode").html("");
		}
	});

	$("#password").blur(function () {
		if ($.trim($("#password").val()) != "") {
			$("#verify_password").html("");
		}
	});

	$("#username").blur(function () {
		if ($.trim($("#username").val()) != "") {
			$("#verify_username").html("");
		}
	});

	$("#shopname").blur(function () {
		if ($.trim($("#shopname").val()) != "") {
			$("#verify_shopname").html("");
		}
	});

	$("#male,#female").blur(function () {
		if ($("input[name='gender']:checked").length > 0) {
			$("#verify_gender").html("");
		}
	});

	$("#birthdate").blur(function () {
		if ($.trim($("#birthdate").val()) != "") {
			$("#verify_birthdate").html("");
		}
	});

	$("#phone").blur(function () {
		if ($.trim($("#phone").val()) != "") {
			$("#verify_phone").html("");
		}
	});

	$("#county").blur(function () {
		if ($.trim($("#county").val()) != "") {
			$("#verify_county").html("");
		}
	});

	$("#district").blur(function () {
		if ($.trim($("#district").val()) != "") {
			$("#verify_district").html("");
		}
	});

	$("#address").blur(function () {
		if ($.trim($("#address").val()) != "") {
			$("#verify_address").html("");
		}
	});

	$("#bankcode").blur(function () {
		if ($.trim($("#bankcode").val()) != "") {
			$("#verify_bankcode").html("");
		}
	});

	$("#bankaccount").blur(function () {
		if ($.trim($("#bankaccount").val()) != "") {
			$("#verify_bankaccount").html("");
		}
	});

	$("#idcard-front").blur(function () {
		if ($.trim($("#idcard-front").val()) != "") {
			$("#verify_idcard-front").html("");
		}
	});

	$("#idcard-back").blur(function () {
		if ($.trim($("#idcard-back").val()) != "") {
			$("#verify_idcard-back").html("");
		}
	});

	$("#account-img").blur(function () {
		if ($.trim($("#account-img").val()) != "") {
			$("#verify_account-img").html("");
		}
	});



	// 當表單提交時，驗證有無欄位沒有輸入
	$("#btn_regist").on("click", function () {
		if ($.trim($("#useraccount").val()) == "") {
			$("#verify_useraccount").html("<font color='red'>請輸入會員帳號!!</font>");
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

		if ($.trim($("#username").val()) == "") {
			$("#verify_username").html("<font color='red'>請輸入會員姓名!!</font>");
			verifyFlag = false;
		} else {
			$("#verify_username").html("");
		}

		if ($.trim($("#seller_idenfication").val()) == "") {
			$("#verify_seller_idenfication").html("<font color='red'>請輸入身分證字號!!</font>");
			verifyFlag = false;
		} else {
			$("#verify_seller_idenfication").html("");
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

		if ($.trim($("#bankcode").val()) == "") {
			$("#verify_bankcode").html("<font color='red'>請選擇金融機構代號!!</font>");
			verifyFlag = false;
		} else {
			$("#verify_bankcode").html("");
		}

		if ($.trim($("#bankaccount").val()) == "") {
			$("#verify_bankaccount").html("<font color='red'>請輸入金融帳號!!</font>");
			verifyFlag = false;
		} else {
			$("#verify_bankaccount").html("");
		}

		if ($.trim($("#idcard-front").val()) == "") {
			$("#verify_idcard-front").html("<font color='red'>請上傳身分證正面照!!</font>");
			verifyFlag = false;
		} else {
			$("#verify_idcard-front").html("");
		}

		if ($.trim($("#idcard-back").val()) == "") {
			$("#verify_idcard-back").html("<font color='red'>請上傳身分證背面照!!</font>");
			verifyFlag = false;
		} else {
			$("#verify_idcard-back").html("");
		}

		if ($.trim($("#account-img").val()) == "") {
			$("#verify_account-img").html("<font color='red'>請上傳帳戶資料照!!</font>");
			verifyFlag = false;
		} else {
			$("#verify_account-img").html("");
		}

		if (verifyFlag == false) {
			$("html, body").scrollTop(0);
		} else {
			let userAccount = $.trim($("#useraccount").val());
			let authencode = $.trim($("#authencode").val());
			let password = $.trim($("#password").val());
			let username = $.trim($("#username").val());
			let sellerIdenfication = $.trim($("#seller_idenfication").val());
			let shopname = $.trim($("#shopname").val());
			let gender = $.trim($("input[name='gender']:checked").val());
			let birthdate = $.trim($("#birthdate").val());
			let phone = $.trim($("#phone").val());
			let country = $.trim($("#county").val());
			let district = $.trim($("#district").val());
			let address = $.trim($("#address").val());
			let bankcode = $.trim($("#bankcode").val());
			let bankAccount = $.trim($("#bankaccount").val());
			let idcardFront = $("#idcard-front")[0].files[0];
			let idcardBack = $("#idcard-back")[0].files[0];
			let accountImg = $("#account-img")[0].files[0];

			let formData = new FormData();
			formData.append("selleraccount", userAccount);
			formData.append("authencode", authencode);
			formData.append("password", password);
			formData.append("sellername", username);
			formData.append("selleridenfication", sellerIdenfication);
			formData.append("shopname", shopname);
			formData.append("gender", gender);
			formData.append("birthdate", birthdate);
			formData.append("phone", phone);
			formData.append("country", country);
			formData.append("district", district);
			formData.append("address", address);
			formData.append("bankcode", bankcode);
			formData.append("bankAccount", bankAccount);
			formData.append("idcardFront", idcardFront);
			formData.append("idcardBack", idcardBack);
			formData.append("accountImg", accountImg);

			$.ajax({
				url: "/Petlife/seller/seller.do?action=sellerRegister",           // 資料請求的網址
				type: "POST",                  // GET | POST | PUT | DELETE | PATCH
				data: formData,             // 將物件資料(不用雙引號) 傳送到指定的 url
				dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
				contentType: false,
				processData: false,
				catch: false,
				success: function (data) {      // request 成功取得回應後執行
					if (data.endsWith("jsp")) {
						redirectPage(data);
						return;
					}
					else if (data != null) {
						if ($.trim(data.sellerPwdErr).length != 0) {
							$("#verify_password").html(`<font color='red'>${data.sellerPwdErr}</font>`);
						}
						if ($.trim(data.sellerNameErr).length != 0) {
							$("#verify_username").html(`<font color='red'>${data.sellerNameErr}</font>`);
						}
						if ($.trim(data.sellerAuthenCodeErr).length != 0) {
							$("#verify_authencode").html(`<font color='red'>${data.sellerAuthenCodeErr}</font>`);
						}
						if ($.trim(data.sellerIdentificationErr).length != 0) {
							$("#verify_seller_idenfication").html(`<font color='red'>${data.sellerIdentificationErr}</font>`);
						}
						if ($.trim(data.sellerBirthdayErr).length != 0) {
							$("#verify_birthdate").html(`<font color='red'>${data.sellerBirthdayErr}</font>`);
						}
						if ($.trim(data.sellerPhoneNumErr).length != 0) {
							$("#verify_phone").html(`<font color='red'>${data.sellerPhoneNumErr}</font>`);
						}
						if ($.trim(data.addressErr).length != 0) {
							$("#verify_address").html(`<font color='red'>${data.addressErr}</font>`);
						}
						if ($.trim(data.bankAccountErr).length != 0) {
							$("#verify_bankaccount").html(`<font color='red'>${data.bankAccountErr}</font>`);
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