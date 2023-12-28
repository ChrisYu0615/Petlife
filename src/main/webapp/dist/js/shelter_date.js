function generateCalendar() {
		
		//抓取使用者 填寫的年月份
		var year_month = $("#monthSelector").val();
		// 抓取點選的button value("-14")
		var date = $("#first_two_weeks").val();
		//因複合查詢需要 所設變數,方便年月份+01
		var end_date="-01";
		//會等於 =使用者填寫年分+-01 (yyyy-mm-01)
		var checkbookingend=year_month + end_date;
		//會等於 = 使用者填寫年分+-14 (yyyy-mm-14)
		var checkbookingstart = year_month + date;
		
		var shelterId = $("#shelterId").val();
		var dataUrl = `../project/shelterbooking.do?action=getCompositePetsQuery&checkbookingstart=${checkbookingstart}&checkbookingend=${checkbookingend}&shelterId=${shelterId}`;
		$.ajax({
			url: dataUrl,
			method: 'POST',
			//		contentType: 'application/json',
			//		dataType: 'json',
			//		data: jsonString,
			async: false,
			success: res => {
				$("#put").empty();
				$(".row").empty();
				if(res!=""){		
					var put=document.getElementById("put");
					put.innerHTML = res;
					Automatic_generated();
				}
				
			},
			error: function(jqXHR, textStatus, errorThrown) {
				$("#button_result").click();
			$("#staticBackdropLabel").html("查詢結果");
			$("#result").html("請先選擇月份");
				try {
					console.log("Error code:", jqXHR.status);
					console.log("Error message:", jqXHR.responseText);
				} catch (e) {
					console.error("Error parsing JSON response:", e);
				}
			},
		});

	
}
//點擊下半月 檢查
function next_two_weeks_generateCalendar() {
	var getEnter = $("#monthSelector").val();
	var parts = getEnter.split('-');
	var year = parts[0];
	var month = parts[1];
	
	console.log(year);
	console.log(month);
	
	var day="";
	if(month==1||month==3||month==5||month==7||month==8||month==10||month==12){
		day= 31;
	}else if((month==2)&&(year % 4 === 0 && year % 100 !== 0) || (year % 400 === 0)){
		day= 29;
	}else if(month==4||month==6|month==9||month==11) {
		day=30;
	}else{
		day=28;
	}
	
		//抓取使用者 填寫的年月份
		var year_month = $("#monthSelector").val();
		// 抓取點選的button value("-31")
		var date = $("#next_two_weeks").val();
		//因複合查詢需要 所設變數,方便年月份+01
		var end_date="-"+day;
		//會等於 =使用者填寫年分+-01 (yyyy-mm-01)
		var checkbookingend=year_month + date;
		//會等於 = 使用者填寫年分+-14 (yyyy-mm-31)
		var checkbookingstart = year_month + end_date;
		var shelterId = $("#shelterId").val();
		var dataUrl = `../project/shelterbooking.do?action=getCompositePetsQuery&checkbookingstart=${checkbookingstart}&checkbookingend=${checkbookingend}&shelterId=${shelterId}`;
		$.ajax({
			url: dataUrl,
			method: 'POST',
			//		contentType: 'application/json',
			//		dataType: 'json',
			//		data: jsonString,
			async: false,
			success: res => {
				$("#put").empty();
				$(".row").empty();
				if(res!=""){
					var put=document.getElementById("put");
					put.innerHTML = res;
					Automatic_next_two_weeks();
				}
			},
			error: function(jqXHR, textStatus, errorThrown) {
				$("#button_result").click();
			$("#staticBackdropLabel").html("查詢結果");
			$("#result").html("請先選擇月份");
				try {
					console.log("Error code:", jqXHR.status);
					console.log("Error message:", jqXHR.responseText);
				} catch (e) {
					console.error("Error parsing JSON response:", e);
				}
			},
		});
	
}

// 自動生成下半月
function Automatic_next_two_weeks(){

	var getEnter = $("#monthSelector").val();
	var parts = getEnter.split('-');
	var year = parts[0];
	var month = parts[1];
	
	console.log(year);
	console.log(month);
	
	var day="";
	if(month==1||month==3||month==5||month==7||month==8||month==10||month==12){
		day= 31;
	}else if((month==2)&&(year % 4 === 0 && year % 100 !== 0) || (year % 400 === 0)){
		day= 29;
	}else if(month==4||month==6|month==9||month==11) {
		day=30;
	}else{
		day=28;
	}

	console.log(day);
//	clearCalendar();

	// 取得選擇的月份
	const selectedMonth = new Date(document.getElementById('monthSelector').value);

	// 設定起始日期為所選月份的第一天
	const startDate = new Date(selectedMonth.getFullYear(), selectedMonth.getMonth(), 1);

	// 生成表格資料
	const table = document.getElementById('calendar');
	const dateRow = document.getElementById('date');
	const weekdayRow = document.getElementById('week');
	const buttonRow = document.getElementById('button_row');


	const dateRow2 = document.getElementById('date2');
	const weekdayRow2 = document.getElementById('week2');
	const buttonRow2 = document.getElementById('button_row2');

    const dateRow3 = document.getElementById('date3');
	const weekdayRow3 = document.getElementById('week3');
	const buttonRow3 = document.getElementById('button_row3');
	for (let i = 14; i < 21 ; i++) { // 顯示上半月的15天
		const currentDate = new Date(startDate);
		currentDate.setDate(startDate.getDate() + i);

		const dateCell = document.createElement('th');
		const dateString = `${currentDate.getMonth() + 1}/${currentDate.getDate()}`;
		dateCell.textContent = dateString;
		dateCell.id = i;
		dateRow.appendChild(dateCell);


		const weekdayCell = document.createElement('td');
		weekdayCell.textContent = getWeekday(currentDate.getDay());
		weekdayCell.className = "tb_header";
		weekdayCell.id = i;
		weekdayRow.appendChild(weekdayCell);

		const buttonCell = document.createElement('td');
		const morningButton = document.createElement('button');
		morningButton.className = "morning";
		morningButton.textContent = "早";
		morningButton.value = i.toString();
		buttonCell.appendChild(morningButton);

		const afternoonButton = document.createElement('button');
		afternoonButton.className = 'afternoon';
		afternoonButton.textContent = '晚';
		afternoonButton.value = i.toString();
		buttonCell.appendChild(afternoonButton);

		buttonRow.appendChild(buttonCell);

	}
	for (let i = 21; i < 28; i++) { // 顯示上半月的15天
		const currentDate = new Date(startDate);
		currentDate.setDate(startDate.getDate() + i);

		const dateCell = document.createElement('th');
		const dateString = `${currentDate.getMonth() + 1}/${currentDate.getDate()}`;
		dateCell.textContent = dateString;
		dateCell.id = i;
		dateRow2.appendChild(dateCell);


		const weekdayCell = document.createElement('td');
		weekdayCell.textContent = getWeekday(currentDate.getDay());
		weekdayCell.className = "tb_header";
		weekdayCell.id = i;
		weekdayRow2.appendChild(weekdayCell);

		const buttonCell = document.createElement('td');
		const morningButton = document.createElement('button');
		morningButton.className = "morning";
		morningButton.textContent = "早";
		morningButton.value = i.toString();
		buttonCell.appendChild(morningButton);

		const afternoonButton = document.createElement('button');
		afternoonButton.className = 'afternoon';
		afternoonButton.textContent = '晚';
		afternoonButton.value = i.toString();
		buttonCell.appendChild(afternoonButton);

		buttonRow2.appendChild(buttonCell);
		}
			for (let i = 28; i < day; i++) { // 顯示上半月的15天
		const currentDate = new Date(startDate);
		currentDate.setDate(startDate.getDate() + i);

		const dateCell = document.createElement('th');
		const dateString = `${currentDate.getMonth() + 1}/${currentDate.getDate()}`;
		dateCell.textContent = dateString;
		dateCell.id = i;
		dateRow3.appendChild(dateCell);


		const weekdayCell = document.createElement('td');
		weekdayCell.textContent = getWeekday(currentDate.getDay());
		weekdayCell.className = "tb_header";
		weekdayCell.id = i;
		weekdayRow3.appendChild(weekdayCell);

		const buttonCell = document.createElement('td');
		const morningButton = document.createElement('button');
		morningButton.className = "morning";
		morningButton.textContent = "早";
		morningButton.value = i.toString();
		buttonCell.appendChild(morningButton);

		const afternoonButton = document.createElement('button');
		afternoonButton.className = 'afternoon';
		afternoonButton.textContent = '晚';
		afternoonButton.value = i.toString();
		buttonCell.appendChild(afternoonButton);

		buttonRow3.appendChild(buttonCell);
	}
	
}



// 自動生成上半月
function Automatic_generated(){
//	clearCalendar();

	// 取得選擇的月份
	const selectedMonth = new Date(document.getElementById('monthSelector').value);

	// 設定起始日期為所選月份的第一天
	const startDate = new Date(selectedMonth.getFullYear(), selectedMonth.getMonth(), 1);

	// 生成表格資料
	const table = document.getElementById('calendar');
	const dateRow = document.getElementById('date');
	const weekdayRow = document.getElementById('week');
	const buttonRow = document.getElementById('button_row');


	const dateRow2 = document.getElementById('date2');
	const weekdayRow2 = document.getElementById('week2');
	const buttonRow2 = document.getElementById('button_row2');

	for (let i = 0; i < 7; i++) { // 顯示上半月的15天
		const currentDate = new Date(startDate);
		currentDate.setDate(startDate.getDate() + i);

		const dateCell = document.createElement('th');
		const dateString = `${currentDate.getMonth() + 1}/${currentDate.getDate()}`;
		dateCell.textContent = dateString;
		dateCell.id = i;
		dateRow.appendChild(dateCell);


		const weekdayCell = document.createElement('td');
		weekdayCell.textContent = getWeekday(currentDate.getDay());
		weekdayCell.className = "tb_header";
		weekdayCell.id = i;
		weekdayRow.appendChild(weekdayCell);

		const buttonCell = document.createElement('td');
		const morningButton = document.createElement('button');
		morningButton.className = "morning";
		morningButton.textContent = "早";
		morningButton.value = i.toString();
		buttonCell.appendChild(morningButton);

		const afternoonButton = document.createElement('button');
		afternoonButton.className = 'afternoon';
		afternoonButton.textContent = '晚';
		afternoonButton.value = i.toString();
		buttonCell.appendChild(afternoonButton);

		buttonRow.appendChild(buttonCell);

	}
	for (let i = 7; i < 14; i++) { // 顯示上半月的15天
		const currentDate = new Date(startDate);
		currentDate.setDate(startDate.getDate() + i);

		const dateCell = document.createElement('th');
		const dateString = `${currentDate.getMonth() + 1}/${currentDate.getDate()}`;
		dateCell.textContent = dateString;
		dateCell.id = i;
		dateRow2.appendChild(dateCell);


		const weekdayCell = document.createElement('td');
		weekdayCell.textContent = getWeekday(currentDate.getDay());
		weekdayCell.className = "tb_header";
		weekdayCell.id = i;
		weekdayRow2.appendChild(weekdayCell);

		const buttonCell = document.createElement('td');
		const morningButton = document.createElement('button');
		morningButton.className = "morning";
		morningButton.textContent = "早";
		morningButton.value = i.toString();
		buttonCell.appendChild(morningButton);

		const afternoonButton = document.createElement('button');
		afternoonButton.className = 'afternoon';
		afternoonButton.textContent = '晚';
		afternoonButton.value = i.toString();
		buttonCell.appendChild(afternoonButton);

		buttonRow2.appendChild(buttonCell);
	}
}




function clearCalendar() {
	const dateRow = document.getElementById('date');
	const weekdayRow = document.getElementById('week');
	const dateRow2 = document.getElementById('date2');
	const weekdayRow2 = document.getElementById('week2');
	const dateRow3 = document.getElementById('date3');
	const weekdayRow3 = document.getElementById('week3');
	const buttonRow = document.getElementById('button_row');	
	const buttonRow2 = document.getElementById('button_row2');	
	const buttonRow3 = document.getElementById('button_row3');	
	

	// 清除日期和星期行的內容
	if(dateRow ){
		dateRow.innerHTML = '';
		weekdayRow.innerHTML = '';
		dateRow2.innerHTML = '';
		weekdayRow2.innerHTML = '';
		dateRow3.innerHTML = '';
		weekdayRow3.innerHTML = '';
		buttonRow.innerHTML='';
		buttonRow2.innerHTML='';
		buttonRow3.innerHTML='';

		
	}
	
}

function getWeekday(dayIndex) {
	const weekdays = ['日', '一', '二', '三', '四', '五', '六'];
	return weekdays[dayIndex];
}

var currentDate = new Date();
var currentYear = currentDate.getFullYear();

function uploadData() {
	var data = [];

	// 获取所有日期按钮
	var dateButtons = $("tr.button td button");
	var shelterId = $("#shelterId").val();
	dateButtons.each(function(index, button) {
		var button_value = button.value;
		var shelter_num;
		var shelter_num_Max;
		if ($(this).is(".on")) {
			// 點亮
			shelter_num = 0;// 已預約人數
			shelter_num_Max = 3;// 最大可已預約人數
		}
		else {
			// 沒點亮
			shelter_num = 0;// 已預約人數
			shelter_num_Max = 0;// 最大可已預約人數
		}
		var date = $(button).closest("tr.button").closest("table").find(`th[id=${button_value}]`).text();
		date = currentYear + "/" + date;

		date = formatDate(date);
		function formatDate(dateString) {
			var dateObject = new Date(dateString);
			var year = dateObject.getFullYear();
			var month = dateObject.getMonth() + 1; // 月份是從 0 開始的，所以需要加 1
			var day = dateObject.getDate();

			// 使用 padZero 函數補零
			return year + "-" + month + "-" + day;
		}

		var time = $(button).hasClass("morning") ? "早" : "晚";

		if (time === "早") {
			time = "10:00:00"
		} else {
			time = "13:00:00"
		}

		data.push({
			shelter: shelterId,
			date: date,
			time: time,
			shelter_num: shelter_num,
			shelter_num_Max: shelter_num_Max
		});

	});
	var jsonString = JSON.stringify({ shelterBookings: data });
	var dataUrl = '../project/shelterbooking.do?action=insert';
	$.ajax({
		url: dataUrl,
		method: 'POST',
		contentType: 'application/json',
		//		dataType: 'json',
		data: jsonString,
		async: false,
		success: function(response) {
			$("button.morning").prop("disabled",true);
			$("button.afternoon").prop("disabled",true);
			$("div.row").empty();
			$("#button_result").click();
			$("#staticBackdropLabel").html("新增結果");
			$("#result").html("新增成功");
			
		},
		error: function(jqXHR, textStatus, errorThrown) {
			$("#button_result").click();
			$("#staticBackdropLabel").html("新增結果");
			$("#result").html("新增失敗");
			try {
				console.log("Error code:", jqXHR.status);
				console.log("Error message:", jqXHR.responseText);
			} catch (e) {
				console.error("Error parsing JSON response:", e);
			}
		},
	});

	console.log(data);
}

$(function() {
	$("button.morning, button.afternoon").on("click", function() {
		$(this).toggleClass("on");
	});
});

//點擊查看
$(document).on("click","td.shelterBooking_id",function(){
	var clickedRowId = $(this).data('row-id');
	var dataURL = `../shelter/reservation.do?action=getByShelterBookingId&rowId=${clickedRowId}`
	
	$.ajax({
		url: dataURL,
		method: "post",
		async: false,
		success: res => {
			$("#myTable").empty();
			$(".row").empty();
			
			var result = document.getElementById("put");
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