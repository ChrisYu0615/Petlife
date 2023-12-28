<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.petlife.shelter.entity.Shelter"%>
<!DOCTYPE html>
<html lang="zh-TW">

<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>後臺切版測試</title>

    <!-- Google Font: Source Sans Pro -->
    <link rel="stylesheet"
        href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="../plugins/fontawesome-free/css/all.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="../dist/css/adminlte.min.css">
    <!-- overlayScrollbars -->
    <link rel="stylesheet" href="../plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
    <!-- my_css -->
    <link rel="stylesheet" href="../dist/css/pet_res.css">
    <!-- 響應式表格 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" crossorigin="anonymous">
    <link rel="stylesheet" href="https://unpkg.com/bootstrap-table@1.15.5/dist/bootstrap-table.min.css">
<% 
	Integer id =null;
	Shelter shelter = (Shelter) session.getAttribute("shelter");
    if(shelter!= null){
    	id = shelter.getShelterId();
    	request.setAttribute("id", id);
}


%>

</head>

<body class="hold-transition sidebar-mini layout-fixed">
    <div class="wrapper">

     
		<!-- 上方Navbar -->
		<nav
			class="main-header navbar navbar-expand navbar-white navbar-light">
			<!-- 上方左邊Left navbar links -->
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" data-widget="pushmenu"
					href="#" role="button"><i class="fas fa-bars"></i></a></li>
				<li class="nav-item d-none d-sm-inline-block">
					<!-- <a href="test.html" class="nav-link"></a> -->
				</li>
			</ul>

			<!-- 上方右邊Right navbar links -->
			<ul class="navbar-nav ml-auto">
			</ul>
		</nav>
		</div>
		<!-- /.navbar -->

		<!-- Main Sidebar Container -->
		<%@ include file="sidebar.jsp"%>
        <!-- //這邊塞主內容文字 -->
        <!-- Main content -->
        <div class="content">
            <div class="container-fluid">

                <div class="col-lg-6">

                   
                        <h1>預覽我的客戶</h1>
                    
                    

                        <div class="res_one row">
    
                         <label for="adopt_date_start" class="date">請選擇日期 起:
                            <input type="date" class="form-control" id="search_start" name="search_start" placeholder="選擇日期">
                            <span>迄</span>
                            <input type="date" class="form-control" id="search_end" name="search_end" placeholder="選擇日期">
                        </label>
    						<input type ="hidden" name="id" value="<%=id%>" id="shelterId" >
                            <!-- <div class="button col"> -->
<!--                                 <button type="button" class="btn btn-secondary">清除</button> -->
<!--                                 <button type="button" class="btn btn-primary">搜尋</button> -->
    
                            <!-- </div> -->
                      
                       
                  
    
<!--                     <div class="container"> -->
<!--                         <span class="button"> -->
<!--                             <button type="button" class="btn btn-primary res " id="res" name="res_one" value="1">待確認</button> -->
<!--                             <button type="button" class="btn btn-primary res" id="res"  name="res_two" value="2">已確認</button> -->
<!--                             <button type="button" class="btn btn-primary res" id="res"  name="res_two" value="3">已取消</button> -->
<!--                             <button type="button" class="btn btn-primary res" id="res" name="res_the" value="4">待審核</button> -->
<!--                             <button type="button" class="btn btn-primary res" id="res" name="res_four"  value="5">成功領養</button> -->
<!--                             <button type="button" class="btn btn-primary res" id="res" name="res_five"  value="6">未成功領養</button> -->
								               
<!--                         </span> -->
                        
<!--                     </div> -->
<div class="tab_container">
  
  <div class="tab_list_block">
    <ul class="tab_list">
<!--       <li><a href="#" data-target="tab1" class="tab -on">頁籤一</a></li> -->
<!--       <li><a href="#" data-target="tab2" class="tab">頁籤二</a></li> -->
<!--       <li><a href="#" data-target="tab3" class="tab">頁籤三</a></li> -->
<li><button type="button"  class="tab -on" id="res" name="res_one" value="1"  >待確認</button></li>
<li> <button type="button" class="tab" id="res"  name="res_two" value="2">已確認</button></li>
<li><button type="button" class="tab" id="res"  name="res_two" value="3">已取消</button></li>
<li><button type="button" class="tab"  id="res" name="res_the" value="4">待審核</button></li>
<li><button type="button" class="tab"  id="res" name="res_four"  value="5">成功領養</button></li>
<li>   <button type="button" class="tab"  id="res" name="res_five"  value="6">未成功領養</button></li>

    </ul>
    <hr>
  </div>
                    
                    
    
<!--                     <div class="container"> -->
<!--                         <div class="res ">預約紀錄</div> -->
<!--                     </div>   -->
                    
                 </div>
            <div id="res_result" class="container"></div>        
<!-- ============================================================================ -->
                    
<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop" id="button_result">
  Launch static backdrop modal
</button>

<!-- Modal -->
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">未成功領養</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body" id="result">
       
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">返回</button>
      </div>
    </div>
  </div>
</div>
<!--==================================================================================== -->
					</div> 

                </div>

            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.3.1.min.js" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            crossorigin="anonymous"></script>
<!--         <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" -->
<!--             crossorigin="anonymous"></script> -->
<!--         <script src="https://unpkg.com/bootstrap-table@1.15.5/dist/bootstrap-table.min.js"></script> -->

        <!-- jQuery -->
<!--         <script src="../plugins/jquery/jquery.min.js"></script> -->
        <!-- jQuery UI 1.11.4 -->
<!--         <script src="../plugins/jquery-ui/jquery-ui.min.js"></script> -->
        <!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<!--         <script> -->
<!--             $.widget.bridge('uibutton', $.ui.button) -->
<!--         </script> -->
        <!-- Bootstrap 4 -->
<!--         <script src="../plugins/bootstrap/js/bootstrap.bundle.min.js"></script> -->
        <!-- overlayScrollbars -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.1/dist/umd/popper.min.js" integrity="sha384-W8fXfP3gkOKtndU4JGtKDvXbO53Wy8SZCQHczT5FMiiqmQfUpWbYdTil/SxwZgAN" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.min.js" integrity="sha384-skAcpIdS7UcVUC05LJ9Dxay8AXcDYfBJqt1CJ85S/CFujBsIzCIv+l9liuYLaMQ/" crossorigin="anonymous"></script>

		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
        <script src="../plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
        <!-- AdminLTE App -->
        <script src="../dist/js/adminlte.js"></script>

        <script>
            //在待確認畫面點擊確認預約更改狀態
            $(document).on("click","#res_ok",function(){
            	var shelterId =$("#shelterId").val();
            	var resId =$(this).val();
            	var resType=$("#input_resType").val();
            	if(resType === "1"){
            		// 當狀態是待確認(1) -> 將更改為已確認(2)
            		resType = "2";
            	}else if(resType === "4"){
            		resType = "5";
            	}
            	var dataURL = '../shelter/reservation.do?action=update&resId=' + resId + '&resType=' + resType ;
            	$.ajax({
            		url: dataURL,
            		method: "post",
            		async: false,
            		success: res => {
            			 var row = $(this).closest("tr"); // 使用 closest 方法找到最接近的 tr 元素
            	            row.remove();
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
            //再待確認or已確認畫面中點擊取消預約更改狀態
            $(document).on("click","#res_delete",function(){
            	var shelterId =$("#shelterId").val();
            	var resId =$(this).val();
            	var resType=$("#input_resType").val();
            	var adopt= true;
            	if(resType === "1" || resType === "2"){
            		// 當狀態是待確認(1) -> 將更改為已確認(2)
            		resType = "3";
            	}else if (resType === "4"){
            		resType = "6";
            		
            		//點擊未成功領養需調整寵物狀態->開放領養
            		
            		
            		var dataURL = '../project/pet.do?action=updatePet&adopt=' + adopt +'&resId='+resId;
            		
            		$.ajax({
                		url: dataURL,
                		method: "post",
                		async: false,
                		success: res => {
                			$("#button_result").click();
                			$("#result").html(res);
                		}, error: function(jqXHR, textStatus, errorThrown) {
                			try {
                				console.log("Error code:", jqXHR.status);
                				console.log("Error message:", jqXHR.responseText);
                			} catch (e) {
                				console.error("Error parsing JSON response:", e);
                			}
                		},
                	});
                	
              
            		
            		
            	}
            	var dataURL = '../shelter/reservation.do?action=update&resId=' + resId + '&resType=' + resType ;
            	$.ajax({
            		url: dataURL,
            		method: "post",
            		async: false,
            		success: res => {
            			 var row = $(this).closest("tr"); // 使用 closest 方法找到最接近的 tr 元素
            	            row.remove();
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
            
            //點擊待確認 等等.. 的ajax
            $(document).on("click","button.tab",function(){
            	var shelterId =$("#shelterId").val();
            	var res =$(this).val();
            	var dataURL = '../shelter/reservation.do?action=compositeQuery&resTypeId=' + res + '&shelterId=' + shelterId;
            	var search_start=$("#search_start").val();
            	if(search_start!=""){
            		dataURL=dataURL+'&search_start='+search_start;
            	}
            	var search_end=$("#search_end").val();
            	if(search_end!=""){
            		dataURL=dataURL+'&search_end='+search_end;
            	}
            	
	            	
            	$.ajax({
	            		url: dataURL,
	            		method: "post",
	            		async: false,
	            		success: res => {
	            			var result = document.getElementById("res_result");
	            			result.innerHTML = res;

	            			var tbody=$("tbody.tb_con")
	            			if(tbody.find('tr').length > 0){
	            				$('#my_res_Table').bootstrapTable;
	            			}else{
	            				result.innerHTML="";
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
			document.addEventListener("DOMContentLoaded", function(){
  
  var all_a_tab = document.querySelectorAll("button.tab");
  for(let i = 0; i < all_a_tab.length; i++){
    all_a_tab[i].addEventListener("click", function(e){
      e.preventDefault();
      
      // 將頁籤列表移除所有 -on，再將指定的加上 -on
      for(let i = 0; i < all_a_tab.length; i++){
        all_a_tab[i].classList.remove("-on");
      }
      this.classList.add("-on");
      
      // 找到對應的頁籤內容，加上 -on 來顯示
      var all_div = document.querySelectorAll("div.tab");
      for(let i = 0; i < all_div.length; i++){
        all_div[i].classList.remove("-on");
        if( all_div[i].classList.contains(this.getAttribute("data-target")) ){
          all_div[i].classList.add("-on");
        }
      }
      
    });
  }
  
});
         </script>