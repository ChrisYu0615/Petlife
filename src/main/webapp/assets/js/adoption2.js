//搜尋按鈕ajax
$(document).on("click","#search_btn",function(){
	var dataURL = '../project/pet.do?action=getCompositePetsQuery2';

	var pet_type = $("select.pet_type").val();
	if(pet_type != "選擇種類"){
		dataURL = dataURL + `&type=${pet_type}`;
	}
	var pet_variety = $("select.pet_variety").val();
	if(pet_variety != "請先選擇種類" && pet_variety != "請選擇品種"){
		dataURL = dataURL + `&petVarietyId=${pet_variety}`;
	}
	var county = $("select.county").val();
	var shelter_name = $("select.shelter").val();
	if(county != "選擇縣市"){
		dataURL = dataURL + `&shelter_name=${shelter_name}`;
	}
	
		$.ajax({
		url: dataURL,
		method: "post",
		async: false,
		success: res => {
			var result = document.getElementById("result");
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
	
	

})

$(document).on("change", "select.pet_type", function() {
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

//收容所縣市搜尋
$(function() {
	$(".headerPage").load("../components/header.html");
	$(".footerPage").load("../components/footer.html");

    fetch('../assets/json/shelters_cities_final.json')
		.then(response => response.json())
		.then(data => {
			var countySelect = document.getElementById('county');
			for (var city in data) {
				var option = document.createElement('option');
				option.value = city;
				option.textContent = city;
				countySelect.appendChild(option);
			}

			// 在選擇縣市時會自動對應到該縣市有的收容所
			countySelect.addEventListener('change', function() {
				var selectedCity = countySelect.value;
				var shelterSelect = document.getElementById('shelter');
				shelterSelect.innerHTML = ''; // 清空行政區選項

				if (selectedCity in data) {
					var shelters = data[selectedCity];
					for (var i = 0; i < shelters.length; i++) {
						var shelterOption = document.createElement('option');
						shelterOption.value = shelters[i];
						shelterOption.textContent = shelters[i];
						shelterSelect.appendChild(shelterOption);
					}
				}
			});
		})

});
function redirectPage(newUrl) {
	window.location.href = newUrl;
}

$(document).on("click","#getOnePet",function(e){
	e.preventDefault();
	var id = $("#pet_id").val();
	if(true){
		$("#form").submit();
	}
	
});



