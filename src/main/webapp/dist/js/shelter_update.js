		// 大頭貼預覽
			$("#s_blob").change(function() {
			    $("div#s_img").html("");
			    readURL(this);
		           function readURL(input) {
	                    if (input.files && input.files.length >= 0) {
	                        for (var i = 0; i < input.files.length; i++) {
	                            var reader = new FileReader();
	                            reader.onload = function (e) {
	                                var img = $("<img>").css({
	                                	'width': '100px',
	                                	'height': '100px',
	                                	'border-radius': '50%',
	                                	'object-fit': 'cover'
	                                }).attr('src', e.target.result);
	                                $("div#s_img").append(img);
	                                $("div#s_img").addClass("circular-image");
	                            }
	                            reader.readAsDataURL(input.files[i]);
	                        }
	                    }
	                };
			});
			
			
			//更改驗證開始
			//===================================================================
// 			//英文&中文
// 			var regex2 = /^[a-zA-Z\u4E00-\u9FA5]+$/;
			//中文與數字
			var addressregex = /^[\u4e00-\u9fa50-9\s]+$/;
			//email
			var emailregex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
			//英文,數字
			var regex = /^[a-zA-Z0-9]+$/;
			//中文
			var chregex =  /^[\u4e00-\u9fa5]{1,50}$/;
			//電話
			var phoneRegex = /^0[2-9][0-9]{7,8}$/;
			//密碼
			var pwdregex= /^(?=.*[a-z])(?=.*[A-Z])(?=.*[\W_])[A-Za-z\d\W_]{1,20}$/;
			
			
			//修改密碼checkbox 
$(document).on("click","#changPwd",function(){

			if ($("#changPwd").prop("checked")) {
				$("#pwd").css("display", "block");
			} else {
				$("#pwd").css("display", "none");
			}
	
});
			//密碼驗證
			$("#shelter_password1").on(
					"blur",
					function() {
						var verifyFlag = true;
						var shelter_password1 = $(this).val();
						if (shelter_password1 == "") {
							$("span#shelter_password1").html(
									"<font color='red'>請勿空白!!</font>")
							verifyFlag = false;
						} else if (!pwdregex.test(shelter_password1)) {
							$("span#shelter_password1").html(
									"<font color='red'>必須包含大小寫,數字及一個特殊符號!!</font>")
							verifyFlag = false;
						}
					})
			//確認密碼與上方密碼驗證
			$("#shelter_password2").on(
					"blur",
					function() {
						var verifyFlag = true;
						var shelter_password2 = $("#shelter_password2").val();
						var shelter_password1 = $("#shelter_password1").val();
						if (shelter_password2 == "") {
							$("span#shelter_password2").html(
									"<font color='red'>請勿空白!!</font>")
							verifyFlag = false;
						} else if (!pwdregex.test(shelter_password2)) {
							$("span#shelter_password2").html(
									"<font color='red'>必須包含大小寫,數字及一個特殊符號!!</font>")
							verifyFlag = false;
						} else if (shelter_password1 != shelter_password2) {
							$("span#shelter_password2").html(
									"<font color='red'>密碼有誤!!</font>")
							verifyFlag = false;
						}
					});
//============================================================================================
			//收容所名稱點擊後input清空;提示字也清空
			$("input[name='shelterName']").on("click",function(){
					$("span#shelterName").html("");
					$("input[name='shelterName']").val("");
			})
			//收容所帳號點擊後input清空;提示字也清空
			$("input[name='shelterAcct']").on("click",function(){
					$("span#shelterAcct").html("");
					$("input[name='shelterAcct']").val("");
			})

			//收容所密碼點擊後input清空
			$(document).on("focus", "#shelter_password1", function() {
				if($(this).val()!=""){
					$("input#shelter_password1").val("")
				}	
			})
			$(document).on("click", "#shelter_password1", function() {		
					$("span#shelter_password1").html("")		
			})
			//收容所確認密碼點擊後input清空
			$(document).on("focus", "#shelter_password2", function() {
				$("span#shelter_password2").val("")
			
			})
				$(document).on("click", "#shelter_password2", function() {
		
				$("span#shelter_password2").html("")
			})
			//收容所電話點擊後input清空;提示字也清空
			$("input[name='shelterPhoneNum']").on("click",function(){
					$("span#shelterPhoneNum").html("");
					$("input[name='shelterPhoneNum']").val("");
			})
			
				//收容所地址點擊後input清空;提示字也清空
			$("input[name='shelterAddress']").on("click",function(){
					$("span#shelterAddress").html("");
					$("input[name='shelterAddress']").val("");
			})
			
			//收容所簡介點擊後input清空;提示字也清空
			$("textarea[name='shelterIntroduction']").on("click",function(){
					$("span#shelterIntroduction").html("");
					$("textarea[name='shelterIntroduction']").val("");
			})

			$("#btn_shelter_update").on("click", function(e) {
					//取消送出預設
					e.preventDefault();
					//使用布林判斷是否可submit
					var verifyFlag = true;
					//抓取所有input.val()裝入var 以下使用
					var shelter_name = $("#shelter_name").val().trim();
					var shelterAcct = $("#shelterAcct").val().trim();
					var shelterPhoneNum = $("#shelterPhoneNum").val().trim();
					var shelterAddress = $("#shelterAddress").val().trim();
					var shelterIntroduction = $("#shelterIntroduction").val().trim();
					//收容所名稱驗證
					if (shelter_name.length === 0) {
						$("span#shelterName").html(
								"<font color='red'>請勿空白!!</font>")
						verifyFlag = false;
					} else if (!chregex.test(shelter_name)) {
						$("span#shelterName").html(
								"<font color='red'>只能輸入中文!!</font>")
						verifyFlag = false;
					} else if (shelter_name.length > 50) {
						$("span#shelterName").html(
								"<font color='red'>最多只能輸入50個字!!</font>")
								verifyFlag = false;
					}
					//收容所帳號驗證(email)
					if (shelterAcct.length === 0) {
						$("span#shelterAcct").html(
								"<font color='red'>請勿空白!!</font>")
						verifyFlag = false;
					} else if (!emailregex.test(shelterAcct)) {
						$("span#shelterAcct").html(
								"<font color='red'>請輸入emil格式!!</font>")
						verifyFlag = false;
					} else if (shelterAcct.length === 0) {
						$("span#shelterAcct").html(
								"<font color='red'>最多只能輸入40個字!!</font>")
						verifyFlag = false;
					}
					//收容所電話驗證
					if (shelterPhoneNum.length === 0) {
						$("span#shelterPhoneNum").html(
								"<font color='red'>請勿空白!!</font>")
						verifyFlag = false;
					} else if (!phoneRegex.test(shelterPhoneNum)) {
						$("span#shelterPhoneNum").html(
								"<font color='red'>請輸入電話格式(10碼)!!</font>")
						verifyFlag = false;
					} else if (shelterPhoneNum.length > 20) {
						$("span#shelterPhoneNum").html(
								"<font color='red'>最多只能輸入20個字!!</font>")
					}
					//收容所地址驗證
					if (shelterAddress.length === 0) {
						$("span#shelterAddress").html(
								"<font color='red'>請勿空白!!</font>")
						verifyFlag = false;
					} else if (!addressregex.test(shelterAddress)) {
						$("span#shelterAddress").html(
								"<font color='red'>只能輸入中文及數字!!</font>")
						verifyFlag = false;
					} else if (shelterAddress.length > 40) {
						$("span#shelterAddress").html(
								"<font color='red'>最多只能輸入40個字!!</font>")
						verifyFlag = false;
					}
					//收容所簡介驗證
					if (shelterIntroduction.length === 0) {
						$("span#shelterIntroduction").html(
								"<font color='red'>請勿空白!!</font>")
						verifyFlag = false;
					}
					

					if (verifyFlag == false) {
						window.document.documentElement.scrollTop = 0;
						return;
					} else {
						$("#shelter_update_put").submit();
					}

				})