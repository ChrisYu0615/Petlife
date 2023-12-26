document.addEventListener("DOMContentLoaded", function() {
	$.ajax({
		url: "/Petlife/user/user.do?action=getUserRate",           // 資料請求的網址
		type: "POST",                  // GET | POST | PUT | DELETE | PATCH
		data: null,             // 將物件資料(不用雙引號) 傳送到指定的 url
		dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
		contentType: false,
		processData: false,
		cache: false,
		success: function(data) {      // request 成功取得回應後執行
			if (data == null) {
				$("#user_ratescore").html("0");

			} else {
				$("#user_ratescore").html(data);
			}
		}, error: function(error) {
			console.error(error);
		}
	});

	$("#sidebar_title ,#btn_cancel").on("click", function() {
		var targetPageURL = "./user_profile.jsp";

		// 使用 window.location.href 進行頁面跳轉
		window.location.href = targetPageURL;
	});

	var p_file_el = document.querySelector("#p_file");
	var preview_el = document.querySelector("#preview");
	var preview_img = function(file) {
		var reader = new FileReader();
		reader.readAsDataURL(file);
		reader.addEventListener("load", function() {
			let img_str = `<img src="${reader.result}" class="preview_img rounded-circle">`;
			preview_el.innerHTML = img_str;
		});
	};

	$(document).on("click", "button.btn_delete_card", function(e) {
		$(this).closest("span").remove();
	});

	$("#addCards").on("click", function() {
		$("#lightbox").fadeIn(1000);
	});

	$("button.btn_modal_close, #lightbox").on("click", function() {
		$("#lightbox").fadeOut(1000);
	});

	$("#lightbox > article").on("click", function(e) {
		e.stopPropagation();
	});

	$("button#btn_save_cards, #lightbox").on("click", function() {
		var cardNumber = $("#card_number").val();
		$("#lightbox").fadeOut(1000);
		if (cardNumber.trim() != "") {
			$("td#cardlist").append(`<span>${cardNumber} <button class="btn_delete_card">刪除</button><br></span>`);
		}
		$("#card_number").val("");
	});
})  