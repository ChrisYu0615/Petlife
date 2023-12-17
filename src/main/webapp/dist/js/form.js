$(document).on("click", "input.pet_type", function() {
	var type = $(this).val();
	var dataURL = `../project/pet?action=getCompositePetVarietiesQueryAsync&type=${type}`

	$.ajax({
		url: dataURL,
		method: "post",
		async: false,
		dataType: 'json',
		success: res => {
			var select = $('#petVarietyId');
			select.empty();
			$('<option>').val('請選擇品種').text('請選擇品種').appendTo(select);
			for (var i = 0; i < res.length; i++) {
				$('<option>').val(res[i].id).text(res[i].variety).appendTo(select);
			}
		}, error: function(jqXHR, textStatus, errorThrown) {
			try {
				console.log("Error code:", jqXHR.status);
				console.log("Error message:", jqXHR.responseText);
			} catch (e) {
				console.error("Error parsing JSON response:", e);
			}
		},
	});
})
//前端驗證開始

$("#pet_adopted_yes").on("click", function() {
	$("#adoped123").css("display", "block")

});
$("#pet_adopted_no").on("click", function() {
	$("#adoped123").css("display", "none")
});

//按下送出按鈕前端驗證
$(document).on("click", "#submit_btn", function(e) {
	//停掉送出預設,先去做前端驗證
	e.preventDefault();
	
	var verifyFlag = true;
	
	var regex = /^[a-zA-Z0-9]+$/; //英文,數字
	var regex2 = /^[a-zA-Z\u4E00-\u9FA5]+$/;
	var emailregex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
	
	//選擇寵物種類
	if ($("input[name='type']:checked").length == 0) {
		$("#pet_type").html("<font color='red'>請輸入寵物種類!!</font>")
		verifyFlag = false;
	}
	
	//選擇寵物品種
	if($("#petVarietyId").val() === "請選擇品種"){
		$("span#petVarietyId").html("<font color='red'>請選擇寵物品種!!</font>")
		verifyFlag = false;
	}
	//選擇寵物性別
	if ($("input[name='petGender']:checked").length === 0) {
		$("#pet_gender").html("<font color='red'>請輸入寵物性別!!</font>")
		verifyFlag = false;
	}

	//寵物絕育
	if ($("input[name='petLigation']:checked").length === 0) {
		$("#pet_ligation").html("<font color='red'>請輸入是否絕育!!</font>")
		verifyFlag = false;
	}

	//毛色
	var pet_color = $("#pet_color").val().trim();
	if (pet_color.length === 0) {
		$("span#pet_color").html("<font color='red'>請輸入寵物毛色!!</font>")
		verifyFlag = false;
	} else {
		if (!regex2.test(pet_color)) {
			$("span#pet_color").html("<font color='red'>只能輸入英文以及中文!!</font>")
			verifyFlag = false;
		}else{
			if(pet_color.length > 20){
				$("span#pet_color").html("<font color='red'>請勿超過20個字!!</font>")
				verifyFlag = false;
			}
		}
	}

	//寵物是否開放領養
	if ($("input[name='adopt']:checked").length === 0) {
		$("#pet_adopt").html("<font color='red'>請輸入是否開放領養!!</font>")
		verifyFlag = false;
	}
	
	//寵物描述
	var pet_content = $("#pet_content").val().trim();
	if(pet_content.length === 0){
		$("span#pet_content").html("<font color='red'>請輸入寵物描述!!</font>")
		verifyFlag = false;
	}else if(pet_content.length > 100){
		$("span#pet_content").html("<font color='red'>請勿超過100個字!!!</font>")
		verifyFlag = false;
	}
	


	//寵物進所日期
	var comin_date = $("#comin_date").val();
	if (comin_date === "") {
		$("span#come_in_date").html("<font color='red'>請輸入進所日期!!</font>")
		verifyFlag = false;
	}

	//籠舍編號
	var pet_cage = $("#pet_cage").val().trim();


	if (pet_cage.length === 0) {
		$("span#pet_cage").html("<font color='red'>請輸入籠舍編號!!</font>")
		verifyFlag = false;
	} else if (!regex.test(pet_cage)) {
			$("span#pet_cage").html("<font color='red'>只能輸入英文以及數字!!</font>")
			verifyFlag = false;
		}else if(pet_cage.length>40){
			$("span#pet_cage").html("<font color='red'>請勿超過40個字!!</font>")
			verifyFlag = false;
		}
	
	
	// 收容編號
	var pet_num = $("#pet_num").val().trim();
	if (pet_num.length === 0) {
		$("span#pet_num").html("<font color='red'>請輸入收容編號編號!!</font>")
		verifyFlag = false;
	} else if (!regex.test(pet_num)) {
			$("span#pet_num").html("<font color='red'>只能輸入英文以及數字!!</font>")
			verifyFlag = false;
		}else if(pet_num.length >20){
			$("span#pet_num").html("<font color='red'>請勿超過20個字!!</font>")
		}
	

	//是否被領養
	var is_adopt = $("input[name='adopted']:checked").val();
	if (is_adopt === "true") {

		//領養者
		var userId = $("input[name='userId']").val().trim();
		if (userId.length === 0) {
			$("span#user_id").html("<font color='red'>請輸入領養者email帳號!!</font>")
			verifyFlag = false;
		} else if(!emailregex.test(userId)){
			$("span#user_id").html("<font color='red'>請輸入符合email格式!!</font>")
			verifyFlag = false;
		}

		

		//領養日期
		var adoped_date = $("#adopt_date").val();
		if (adoped_date === "") {
			$("span#adoped_date").html("<font color='red'>請輸入領養日期!!</font>")
			verifyFlag = false;
		}



	}

	if (verifyFlag == false) {
//		window.document.documentElement.scrollTop=0;
		return;
	}
	
	var shelter_id = $("#shelterId").val();
	var pet_num = $("#pet_num").val();
//	var dataUrl = `../project/pet.do?action=getPetListAsync&shelterId=${shelterid}&petNum=${pet_num}`;
	var dataUrl = '../project/pet.do?action=getPetListAsync';
	// 					var dataUrl = "../project/pet.do"
	$.ajax({
		url: dataUrl,
		method: 'POST',
		contentType: 'application/json',
		dataType: 'json',
		data: `{ "shelterId": "${shelter_id}", "petNum": "${pet_num}" }`,
		async: false,
		
		success: res => {
			if(res.length>0){
				//有重複
				$('span#pet_num').html("重複");
				
			}
			else if(res.length==0){
				$("#pet_form").submit();
			}
		},
		error: function(jqXHR, textStatus, errorThrown) {
			try {
				console.log("Error code:", jqXHR.status);
				console.log("Error message:", jqXHR.responseText);
			} catch (e) {
				console.error("Error parsing JSON response:", e);
			}
		},
	});
	
});

//點選後 警告字會消失
$("input[name='type']").on("click", function() {

	$("span#pet_type").html("");
})
$("#petVarietyId").on("click", function() {

	$("span#petVarietyId").html("");
})
$("input[name='petGender']").on("click", function() {

	$("span#pet_gender").html("");
})
$("input[name='petLigation']").on("click", function() {

	$("span#pet_ligation").html("");
})
$("#pet_color").on("click", function() {

	$("span#pet_color").html("");
})
$("input[name='adopt']").on("click", function() {

	$("span#pet_adopt").html("");
})

$("input[name='pet_content']").on("click", function() {

	$("span#pet_content").html("");
})

$("input[name='comeInDate']").on("click", function() {

	$("span#come_in_date").html("");
})
$("input[name='petCage']").on("click", function() {

	$("span#pet_cage").html("");
})
$("#pet_num").on("click", function() {

	$("span#pet_num").html("");
})
$("input[name='userId']").on("click", function() {

	$("span#user_id").html("");
})
$("#adopt_date").on("click", function() {

	$("span#adoped_date").html("");
})











