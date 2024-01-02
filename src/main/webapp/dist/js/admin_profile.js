$(function() {
	$("#adminProfileForm").submit(function(event) {
		let verifyFlag = true;
		let adminPwd = $.trim($("#admin_pwd").val());
		let adminNickname = $.trim($("#admin_nickname").val());

		if (adminPwd != null && adminPwd != "") {
			let passwordReg = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+])[A-Za-z\d!@#$%^&*()_+]{1,20}$/;
			if (!passwordReg.test(adminPwd)) {
				verifyFlag = false;
				$("#verify_password").html("<font color='red'><b>密碼格式不正確，必須包含英文大小寫及特殊符號</font>");
			} else {
				$("#verify_password").html("");
			}
		}

		if (adminNickname == null || adminNickname == "") {
			verifyFlag = false;
			$("#verify_nickname").html("<font color='red'><b>請輸入管理員暱稱!!</font>");
		} else {
			$("#verify_nickname").html("");
		}

		if (verifyFlag == false) {
			event.preventDefault();
		}
	});

	$("#showPassword").on("change", function() {
		let password = $("#admin_pwd");
		if (this.checked) {
			password.attr("type", "text");
		} else {
			password.attr("type", "password");
		}
	});

	$("#logout").on("click", function() {
		alert("您已成功登出!!");
	});
});