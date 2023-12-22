<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>

<p>insert</p>
<table>
<tr>

<td id="res_date">2023-12-18</td>
<td id="res_time">13:00:00</td>

</tr>

</table>

<button type="button" id="res_submit_btn">insert</button>
<span id="result"></span>

<p>===========================================================</p>

<table>
<tr>

<td id="resupdate_date">2023-12-18</td>
<td id="resupdate_time">13:00:00</td>

</tr>

</table>
<button type="button" id="resupdate_submit_btn">update</button>
<input type="hidden" value="6" name="res_id" id="res_id">
<span id="result2"></span>




     <script src="https://code.jquery.com/jquery-3.3.1.min.js" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            crossorigin="anonymous"></script>
        <script src="https://unpkg.com/bootstrap-table@1.15.5/dist/bootstrap-table.min.js"></script>

        <!-- jQuery -->
        <script src="../plugins/jquery/jquery.min.js"></script>
        <!-- jQuery UI 1.11.4 -->
        <script src="../plugins/jquery-ui/jquery-ui.min.js"></script>
        <!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
        <script>
            $.widget.bridge('uibutton', $.ui.button)
        </script>
        <!-- Bootstrap 4 -->
        <script src="../plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
        <!-- overlayScrollbars -->
        <script src="../plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
        <!-- AdminLTE App -->
        <script src="../dist/js/adminlte.js"></script>
        
        <script>
        //點擊新增的ajax
        	$(document).on("click","#res_submit_btn",function(e){
				
        	
        		
        		var res_Date=$("#res_date").text();
        		var res_Time=$("#res_time").text();
        		
        		
        	    
        	    
        	    var dataURL = '../project/shelterbooking.do?action=insert&res_date=' + res_Date + '&res_time=' + res_Time;
        		
        		$.ajax({
        			url: dataURL,
        			method: "post",
        			async: false,
        			success: res => {
        				var result = document.getElementById("result");
        				result.innerHTML = ("新增成功");
        				
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
        
        
        //點擊更新的ajax
        
        $(document).on("click","#resupdate_submit_btn",function(){
        	var resupdate_date=$("#resupdate_date").text();
    		var resupdate_time=$("#resupdate_time").text();
    		var res_id=$("#res_id").val();
    		  var dataURL = '../project/shelterbooking.do?action=update&res_id='+ res_id + '&res_date=' + resupdate_date + '&res_time=' + resupdate_time;
        	
    		  $.ajax({
      			url: dataURL,
      			method: "post",
      			async: false,
      			success: res => {
      				var result = document.getElementById("result2");
      				result.innerHTML = ("更新成功");
      				
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
        
        
        </script>
</body>
</html>