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



$(document).on("click", "#search_btn", function() {
	var dataURL = '../project/pet.do?action=getCompositePetsQuery';
	
	var id=$("#id").val();
	if(id!=""){
		dataURL = dataURL + `&id=${id}`;
	}
	
	var petGender = $("input[name='petGender']:checked");
	if (petGender.length != 0) {
		dataURL = dataURL + `&petGender=${petGender.val()}`;
	}
	var type=$("input[name='type']:checked");
	if(type.length!=0){
			dataURL = dataURL + `&type=${type.val()}`;
	}
	
	var petVariety = $("#petVarietyId").val();
	if (petVariety != "請先選擇種類"&& petVariety!="請選擇品種") {
		dataURL = dataURL + `&petVarietyId=${petVariety}`;
	}
//	else if (petVariety === "請選擇品種") {
//		var select = $('#petVarietyId');
//		var allOptions = select.find('option');
//
//		// 在控制台中輸出所有選項的值和文本
//		allOptions.each(function(index, option) {
//			console.log('Value: ' + $(option).val() + ', Text: ' + $(option).text());
//			dataURL = dataURL + `&petVarietyId=${$(option).val()}`;
//		});
//	}
	var petNum = $("input[name='petNum']").val();
	if (petNum != "") {
		dataURL = dataURL + `&petNum=${petNum}`;
	}
	var come_in_date_start = $("#come_in_date_start").val();
	if (come_in_date_start != "") {
		dataURL = dataURL + `&come_in_date_start=${come_in_date_start}`;
	}
	var come_in_date_end = $("#come_in_date_end").val();
	if (come_in_date_end != "") {
		dataURL = dataURL + `&come_in_date_end=${come_in_date_end}`;
	}
	var adopt_date_start = $("#adopt_date_start").val();
	if (adopt_date_start != "") {
		dataURL = dataURL + `&adopt_date_start=${adopt_date_start}`;
	}
	var adopt_date_end = $("#adopt_date_end").val();
	if (adopt_date_end) {
		dataURL = dataURL + `&adopt_date_end=${adopt_date_end}`;
	}
	$.ajax({
		url: dataURL,
		method: "post",
		async: false,
		success: res => {
			var result = document.getElementById("result");
			result.innerHTML = res;
			$('#myTable').bootstrapTable({});
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
$(document).on("click", "input.pet_type", function() {
	var type = $(this).val();
	var dataURL = `../project/pet?action=getCompositePetsQuery&type=${type}`

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
$(document).on("click", "td.pet_id", function() {
	var clickedRowId = $(this).data('row-id');
	var dataURL = `../project/pet.do?action=update&rowId=${clickedRowId}`

	$.ajax({
		url: dataURL,
		method: "post",
		async: false,
		success: res => {
			$(".edit_button").empty()
			$("div.search_pet").empty()
			$("h1").empty()
			$("#myTable").empty()
			$(".pagination").empty()

			var result = document.getElementById("update_form");
			result.innerHTML = res;
		}, error: function(jqXHR, textStatus, errorThrown) {
			try {
				console.log("Error code:", jqXHR.status);
				console.log("Error message:", jqXHR.responseText);
			} catch (e) {
				console.error("Error parsing JSON response:", e);
			}
		},
	});

	if ($("input[name='adopted']:checked").val() === 'false') {
		$("tr#userId").css("display", "none")
		$("tr#adopt_date").css("display", "none")
	}

})

$(document).on("click", "input[name='adopted']", function() {
	if ($("input[name='adopted']:checked").val() === 'false') {
		$("tr#userId").css("display", "none");
		$("tr#adopt_date").css("display", "none");
	}else{
		$("tr#userId").css("display", "block");
		$("tr#adopt_date").css("display", "block");
		$("#pet_adopt_no").prop('checked', true);
	}
});
