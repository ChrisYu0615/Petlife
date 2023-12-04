document.addEventListener("DOMContentLoaded", function () {
    $(".headerPage").load("../header.html");
    $(".footerPage").load("../footer.html");

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
    const shelterImg = document.getElementById('shelter-img');
    const shelterImgPreview = document.getElementById('shelter-img-preview');

    shelterImg.addEventListener('change', function () {
        if (shelterImg.files && shelterImg.files[0]) {
            const reader = new FileReader();
            reader.onload = function (e) {
                shelterImgPreview.src = e.target.result;
            };
            reader.readAsDataURL(shelterImg.files[0]);
        }
    });
});