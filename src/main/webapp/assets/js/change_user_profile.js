$(function () {
    $(".headerPage").load("../components/header.html");
    $(".footerPage").load("../components/footer.html");

    // 密碼顯示切換功能
    var passwordInput = document.getElementById('password');
    var comfirmPasswordInput = document.getElementById('confirm-password');

    var showPasswordCheckbox = document.getElementById('showPassword');

    showPasswordCheckbox.addEventListener('change', function () {
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
    $(document).ready(function () {
        $('#birthdate').datepicker({
            format: 'yyyy-mm-dd',
            language: 'zh-TW',
            autoclose: true
        });
    });

    // 在出生年月日框框和旁邊的日曆按鈕新增點擊事件，點擊時會打開日期選擇器
    $('#birthdate + .input-group-text').on('click', function () {
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

    $("#sidebar_title ,#btn_cancel").on("click", function () {
        var targetPageURL = "./user_profile.html";

        // 使用 window.location.href 進行頁面跳轉
        window.location.href = targetPageURL;
    });
})