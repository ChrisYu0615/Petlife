$(function () {
    $(".headerPage").load("../components/header.html");
    $(".footerPage").load("../components/footer.html");

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

    const headshot = document.getElementById('headshot');
    const headshotPreview = document.getElementById('headshot-preview');

    headshot.addEventListener('change', function () {
        if (headshot.files && headshot.files[0]) {
            const reader = new FileReader();
            reader.onload = function (e) {
                headshotPreview.src = e.target.result;
            };
            reader.readAsDataURL(headshot.files[0]);
        }
    });


    // 選擇性別
    let checkGender = $("#checkGender").val();
    if (checkGender === "true") {
        $('#female').prop('checked', true);
    } else {
        $('#male').prop('checked', true);
    }


    // 使用Fetch API載入JSON檔案，讀取縣市的所有選項
    fetch('../assets/json/cities.json')
        .then(response => response.json())
        .then(data => {

            // 初始化所有縣市和行政區選項
            initOptions(data);
            let address = $("#address").val();
            let cityIndex;
            let districtIndex;
            if (address.indexOf("市") === -1) {
                cityIndex = address.indexOf("縣");
                if (address.indexOf("鄉") != -1) {
                    districtIndex = address.indexOf("鄉");
                } else if (address.indexOf("鎮") != -1) {
                    districtIndex = address.indexOf("鎮");
                } else {
                    districtIndex = address.indexOf("市");
                }
            } else {
                cityIndex = address.indexOf("市");
                districtIndex = address.indexOf("區");
            }

            let districtSubstring = address.substring(cityIndex + 1, districtIndex + 1).trim();
            let addressSubstring = address.substring(districtIndex + 1).trim();
            // 選擇縣市
            selectCountry(data, address);

            // 選擇行政區
            $('#district option').filter(function () {
                let optionText = $(this).text().trim();
                return optionText === districtSubstring;
            }).prop('selected', true);

            // 詳細地址
            $("#address").val(addressSubstring);
        })
        .catch(error => console.error('縣市行政區JSON檔案載入失敗：', error));

    // 初始化所有縣市和行政區選項
    function initOptions(data) {
        var countrySelect = document.getElementById('country');
        var districtSelect = document.getElementById('district');

        for (var city in data) {
            var option = document.createElement('option');
            option.value = city;
            option.textContent = city;
            countrySelect.appendChild(option);
        }

        // 在選擇縣市時會自動對應到該縣市有的行政區
        countrySelect.addEventListener('change', function () {
            var selectedCity = countrySelect.value;
            districtSelect.innerHTML = '<option value="">選擇行政區</option>';

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
    }

    // 選擇縣市
    function selectCountry(data, address) {
        let cityIndex ;
        if (address.indexOf("市") === -1) {
            cityIndex = address.indexOf("縣");
        } else {
            cityIndex = address.indexOf("市");
        }

        let citySubstring = address.substring(0, cityIndex + 1).trim();
        var districtSelect = document.getElementById('district');

        // 使用選擇器來找到對應的選項並選中它
        $('#country option').filter(function () {
            let optionText = $(this).text().trim();
            return optionText === citySubstring;
        }).prop('selected', true);


        // 在選擇縣市時會自動對應到該縣市有的行政區
        var selectedCity = citySubstring;
        districtSelect.innerHTML = '<option value="">選擇行政區</option>';

        if (selectedCity in data) {
            var districts = data[selectedCity];
            for (var i = 0; i < districts.length; i++) {
                var districtOption = document.createElement('option');
                districtOption.value = districts[i];
                districtOption.textContent = districts[i];
                districtSelect.appendChild(districtOption);
            }
        }
    }



    // 前端驗證區塊
    var verifyFlag = true;

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

    $("#nickname").blur(function () {
        if ($.trim($("#nickname").val()) != "") {
            $("#verify_nickname").html("");
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

    $("#country").blur(function () {
        if ($.trim($("#country").val()) != "") {
            $("#verify_country").html("");
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

    // 當表單提交時，驗證有無欄位沒有輸入
    $("#regist_form").submit(function (event) {
        verifyFlag = true;
        let password = $.trim($("#password").val());
        let username = $.trim($("#username").val());
        let birthday = $.trim($("#birthdate").val());
        let phoneNum = $.trim($("#phone").val());
        let country = $.trim($("#country").val());
        let district = $.trim($("#district").val());
        let address = $.trim($("#address").val());

        if (password != "") {
            let passwordReg = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+])[A-Za-z\d!@#$%^&*()_+]{1,20}$/;
            if (!passwordReg.test(password)) {
                verifyFlag = false;
                $("#verify_password").html("<font color='red'><b>密碼格式不正確，必須包含英文大小寫及特殊符號</font>");
            }
        }

        if (username == "") {
            verifyFlag = false;
            $("#verify_username").html("<font color='red'><b>請輸入姓名!!</font>");
        } else {
            let usernameReg = /^[\u4e00-\u9fa5]{1,20}$/;
            if (!usernameReg.test(username)) {
                verifyFlag = false;
                $("#verify_username").html("<font color='red'><b>姓名只能使用中文字，並限制20個字以內!!</font>");
            }
        }

        if (birthday == "") {
            verifyFlag = false;
            $("#verify_birthdate").html("<font color='red'><b>請選擇出生年月日!!</font>");
        }

        if (phoneNum == "") {
            verifyFlag = false;
            $("#verify_phone").html("<font color='red'><b>請輸入手機號碼!!</font>");
        } else {
            let phoneNumReg = /^09\d{8}$/;
            if (!phoneNumReg.test(phoneNum)) {
                verifyFlag = false;
                $("#verify_phone").html("<font color='red'><b>手機號碼只能以09開頭，並且10個數字!!</font>");
            }
        }

        if (country == "選擇縣市") {
            $("#verify_country").html("<font color='red'><b>請選擇縣市!!</font>");
            verifyFlag = false;
        }

        if (district == "選擇行政區") {
            $("#verify_district").html("<font color='red'><b>請選擇行政區!!</font>");
            verifyFlag = false;
        }

        if (address == "") {
            verifyFlag = false;
            $("#verify_address").html("<font color='red'><b>請輸入住址!!</font>");
        } else {
            let addressReg = /^[\u4e00-\u9fa50-9\s]+$/;
            if (!addressReg.test(address)) {
                verifyFlag = false;
                $("#verify_address").html("<font color='red'><b>地址只能包含中文與數字!!</font>");
            }
        }

        console.log(verifyFlag);
        if (verifyFlag == false) {
            event.preventDefault();
            $("html, body").scrollTop(0);
        }
    });

    $("#sidebar_title ,#btn_cancel").on("click", function () {
        var targetPageURL = "./user_profile.jsp";

        // 使用 window.location.href 進行頁面跳轉
        window.location.href = targetPageURL;
    });
})