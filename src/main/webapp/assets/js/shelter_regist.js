document.addEventListener("DOMContentLoaded", function () {
	$(".headerPage").load("../components/header.html");
	$(".footerPage").load("../components/footer.html");

    // 添加獲取驗證碼按鈕的點擊事件處理程序
    var getauthencode_btn = document.getElementById('getauthencode');
    getauthencode_btn.addEventListener('click', function () {
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
    });

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

    // 照片上傳預覽
    // const shelterImg = document.getElementById('shelter-img');
    // const shelterImgPreview = document.getElementById('shelter-img-preview');

    // shelterImg.addEventListener('change', function () {
    //     if (shelterImg.files && shelterImg.files[0]) {
    //         const reader = new FileReader();
    //         reader.onload = function (e) {
    //             shelterImgPreview.src = e.target.result;
    //         };
    //         reader.readAsDataURL(shelterImg.files[0]);
    //     }
    // });
    
    // 前端驗證區塊
    var verifyFlag = true;
	
	// 使用ajax判斷會員帳號是否重複
	var userAccount = document.getElementById("useraccount");
	userAccount.addEventListener("blur", function() {
		document.getElementById("verify_useraccount").innerHTML = "";
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
				if (xhr.status == 200) {
					let verifyResult = xhr.responseText;
					document.getElementById("verify_useraccount").innerHTML = xhr.responseText;
					if (verifyResult.includes("帳號重複")) {
						$("#btn_regist").prop("disabled", true);
					} else {
						$("#btn_regist").prop("disabled", true);
					}
				} else {
					alert(xhr.status);
				}
			}
		}
		xhr.open("POST", "/Petlife/shelter/shelter.do", true);
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		var userAccountVal = document.getElementById("useraccount").value;
		xhr.send("action=verify&useraccount=" + userAccountVal);
	});
	
	// 針對輸入框輸入後blur事件，輸入任何字後就會把錯誤訊息消除
	$("#useraccount").blur(function() {
		if ($.trim($("#useraccount").val()) != "") {
			$("#verify_useraccount").html("");
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

	$("#user_in_charge").blur(function() {
		if ($.trim($("#user_in_charge").val()) != "") {
			$("#verify_user_in_charge").html("");
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
	
});