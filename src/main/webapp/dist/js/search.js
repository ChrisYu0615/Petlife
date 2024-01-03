//petUPdate 修改驗證
$(document).on("click","#petUpdate",function(e){
	e.preventDefault();
	var verifyFlag = true;
	
	var regex = /^[a-zA-Z0-9]+$/; //英文,數字
	var regex2 = /^[a-zA-Z\u4E00-\u9FA5]+$/;
	
	var petColor = $("input[name='petColor']").val().trim();
	if (petColor.length === 0) {
		$("span#pet_color").html("<font color='red'>請輸入寵物毛色!!</font>")
		verifyFlag = false;
	} else {
		if (!regex2.test(petColor)) {
			$("span#pet_color").html("<font color='red'>只能輸入英文以及中文!!</font>")
			verifyFlag = false;
		}else{
			if(petColor.length > 20){
				$("span#pet_color").html("<font color='red'>請勿超過20個字!!</font>")
				verifyFlag = false;
			}
		}
	}
	var petCage=$("input[name='petCage']").val().trim();
	if (petCage.length === 0) {
		$("span#pet_cage").html("<font color='red'>請輸入籠舍編號!!</font>")
		verifyFlag = false;
	} else if (!regex.test(petCage)) {
			$("span#pet_cage").html("<font color='red'>只能輸入英文以及數字!!</font>")
			verifyFlag = false;
		}else if(petCage.length>40){
			$("span#pet_cage").html("<font color='red'>請勿超過40個字!!</font>")
			verifyFlag = false;
		}
		
		var pet_content= $("input[name='pet_content']").val().trim();
		if(pet_content.length === 0){
		$("span#pet_content").html("<font color='red'>請輸入寵物描述!!</font>")
		verifyFlag = false;
	}else if(pet_content.length > 100){
		$("span#pet_content").html("<font color='red'>請勿超過100個字!!!</font>")
		verifyFlag = false;
	}else if (!regex2.test(pet_content)){
		$("span#pet_content").html("<font color='red'>只能輸入英文以及中文!!</font>")
		verifyFlag = false;
	}
	
	if (verifyFlag == false) {
		window.document.documentElement.scrollTop=0;
//		return;
	}else{
		ReAppendPhotos();
		$("#form_update").submit();
	}
	
})
$(document).on("click","input[name='petColor']",function(){
	$("#pet_color").html("");
})
$(document).on("click","input[name='petCage']",function(){
	$("span#pet_cage").html("");
})
$(document).on("click","input[name='pet_content']",function(){
	$("#pet_content").html("");
})
//petUpdate 讀取圖片
function previewImage() {
	var fileInput = document.getElementById('addPetPhoto');

	// Check if any file is selected
	if (fileInput.files.length > 0) {
		for (var i = 0; i < fileInput.files.length; i++) {
			// Get the reference to the table body
			var tbody = document.getElementById("photoTable").getElementsByTagName('tbody')[0];
		
			// Create a new row and cells
			var newRow = tbody.insertRow();//<tr></tr>
			var td1 = newRow.insertCell(0);//<td></td>
			var td2 = newRow.insertCell(1);//<td></td>
			 
			// Set cell content (you can customize this part)
			td1.innerHTML = '<button type="button" onclick="deleteRow(this)" class="btn btn-primary put_delete_photo btn-sm">移除</button>';
			var img = document.createElement('img');//<img></img>
			img.name = "petphoto";
			// Create a FileReader
		  	var reader = new FileReader();
		
		 	// Set up the FileReader to load the image as a data URL
		 	reader.onload = function() {
		    // Set the data URL as the src attribute of the image
		    	img.src = reader.result;
			};
		
		  	// Read the selected file as a data URL
		  	reader.readAsDataURL(fileInput.files[i]);
			img.width = 100;
			td2.appendChild(img);
			
		}
	}
}

function ReAppendPhotos() {
	// 取得隱藏input 標籤
    var imageInput = document.getElementById('addPetPhoto');
	imageInput.value = '';
	var table = document.getElementById('photoTable');
	//使用迴圈取得每一row
    for (var i = 0; i < table.rows.length; i++) {
      var row = table.rows[i];

      // 取得目前<img>元素
      var imgElement = row.cells[1].querySelector('img');
		if (imgElement === null) continue;// 該行找不到照片
		if (imgElement.id != '') continue;// 既有的照片
		
      	var blob = dataURItoBlob(imgElement.src);
     
        var newFile = new File([blob], 'image.jpg', { type: 'image/jpg' });

        //將新file 加入
        var existingFileList = imageInput.files;
        var updatedFileList = appendToFileList(existingFileList, newFile);
        imageInput.files = updatedFileList.files;
    }
}
// Data URL->Blob
  function dataURItoBlob(dataURI) {
    var byteString = atob(dataURI.split(',')[1]);
    var mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0];
    var ab = new ArrayBuffer(byteString.length);
    var ia = new Uint8Array(ab);
    
    for (var i = 0; i < byteString.length; i++) {
      ia[i] = byteString.charCodeAt(i);
    }

    return new Blob([ab], { type: mimeString });
 }
function appendToFileList(existingFileList, newFile) {
  // 建立新的array，將目前有的文件加入
  var files = Array.from(existingFileList);

  // 將文件添加到newFile
  files.push(newFile);

  // 建立 FileList 
  var newFileList = new DataTransfer();
  
  //將文件添加到新的 FileList
  files.forEach(function(file) {
    newFileList.items.add(file);
  });

  return newFileList;
}
 
//刪除該tr
function addRow() {
	$('#addPetPhoto').click();
}
//刪除該tr
function deleteRow(button) {
	
	var row = button.parentNode.parentNode;
	var imgElement = row.cells[1].querySelector('img');
	
	if (imgElement.id != '') {
		var deletePhto = document.createElement('input');
		deletePhto.type="hidden";
		deletePhto.name="deletePhoto";
		deletePhto.value=imgElement.id;
		
		var petUpdate = document.getElementById('petUpdate');
		petUpdate.appendChild(deletePhto);
	
		
	}
	

	var table = document.getElementById("photoTable");

	table.deleteRow(row.rowIndex);
}



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
			var search_result = document.getElementById("search_result");
			search_result.innerHTML = res;
//			$('#myTable').bootstrapTable({});
							var tbody=$("tbody.tb_con")
	            			if(tbody.find('tr').length > 0){
	            				$('#myTable').bootstrapTable;
	            			}else{
	            				result.innerHTML="";
	            				$("#myTable").empty();
	            				$("#button_result").click();
	            				$("#staticBackdropLabel").html("查詢結果");
	            				$("#result").html("查無資料");
	            				
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

//$(document).on("click","#petUpdate",function(e){
//		e.preventDefault();
//		
//		$("#form_update").submit();
//})
