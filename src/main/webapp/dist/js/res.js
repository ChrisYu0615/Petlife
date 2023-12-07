 
 //按鈕點選變色
 $("button.res").on("click", function () {
     $("button.res").removeClass("on")
     $(this).addClass("on")
 });
 
 $(document).on("click","#search_res",function(){
	 var dataURL = '../project/shelterbooking.do?action=getAllShelterBooking';
//	var start_date = $("#start_date").val();
//		if (start_date != "") {
//			dataURL = dataURL + `&start_date=${start_date}`;
//		}
//	var end_date = $("#end_date").val();
//		if (end_date != "") {
//			dataURL = dataURL + `&end_date=${end_date}`;
//		}
	 
	 $.ajax({
		url: dataURL,
		method: "post",
		dataType: "html",
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

	 
	 
	 
	 
	 
	 
	 
 });



