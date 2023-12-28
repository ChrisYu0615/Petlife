<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="com.petlife.shelter.entity.Shelter"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
    <link rel="stylesheet" href="../dist/css/form.css">
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
		<!-- /.navbar -->

		<!-- Main Sidebar Container -->
		<%@ include file="sidebar.jsp"%>

        <!-- //這邊塞主內容文字 -->
        <!-- Main content -->
        <div class="content">
            <div class="container-fluid">
                <!-- <div class="row"> -->
                <div class="col-lg-6">
                    <!-- <div class="card"> -->
                    <!-- <div class="card-body"> -->
                    <div class="row">
                        <h1>上架/下架收容動物</h1>
                    </div>

                 

                        <form action="${pageContext.request.contextPath}/project/pet.do" class="from" method="post" enctype="multipart/form-data"id="pet_form" >
                        
                        <!--   收容所到時后關起來,種類打開  -->
                        <span id="showForm"></span>
                        <div class="col ">
                             <input type="hidden" name="id" value="<%=id %>" id="shelterId" >
                    
                            </div>
	                        
	                   		<div class="col "> 
	                              <label >種類:</label>                
	                              <input class="form-check-input pet_type" type="radio" name="type" id="pet_type_dog" value="狗">
	                          	  <label class="form-check-label" for="pet_type_dog" >犬</label> 
	                              <input class="form-check-input pet_type" type="radio" name="type" id="pet_type_cat" value="貓">
	                              <label class="form-check-label" for="pet_type_cat">貓</label> 
	                              <span id="pet_type"></span>
	                   		</div> 
	                   
						<div class="col">
								 <label>品種:</label>

								<select class="form-select form-select-sm pet_variety"
									aria-label=".form-select-sm example" name="petVarietyId"
									id="petVarietyId">
									<option selected>請先選擇種類</option>
								</select>
								<span id="petVarietyId"></span> 
							</div>
                    
                    
                              <div class="col">
                                <label>性別:</label>
                                <input class="form-check-input" type="radio" name="petGender" id="pet_gender_m" value="公">
                                <label class="form-check-label" for="pet_gender_m">公</label>
                                <input class="form-check-input" type="radio" name="petGender" id="pet_gender_w" value="母">
                                <label class="form-check-label" for="pet_gender_w">母</label>
                            	 <span id="pet_gender"></span>
                            </div>
                    
                        
                    
                            <div class="col ">
                                  <label>絕育:</label>
                                  <input class="form-check-input" type="radio" name="petLigation" id="pet_ligation_yes" value="有絕育">
                                  <label class="form-check-label" for="pet_ligation_yes" >是</label>
                                  <input class="form-check-input" type="radio" name="petLigation" id="pet_ligation_no" value="未絕育">
                                  <label class="form-check-label" for="pet_ligation_no" >否</label>
                    			  <span id="pet_ligation"></span>
                            </div>
                    
                    
                            <div class="col">
                                <label for="pet_color">毛色:</label>
                                <input class="form-control" type="text" placeholder="請輸入寵物毛色"  id="pet_color" name="petColor" >
                                <span id="pet_color"></span>
                    		
                            </div>
                    
                    
                            <div class="col">
                                <label >是否開放領養:</label>
                                <input class="form-check-input" type="radio" name="adopt" id="pet_adopt_yes" value="true">
                                <label class="form-check-label" for="pet_adopt_yes">是</label>
                                <input class="form-check-input" type="radio" name="adopt" id="pet_adopt_no" value="false">
                                <label class="form-check-label" for="pet_adopt_no">否</label>
                            	<span id="pet_adopt"></span>
                            </div>
                    
                            <div class="col">
                               <label for="pet_content">描述:</label>
                                <input class="form-control" type="text" placeholder="請輸入寵物描述"  id="pet_content" name="pet_content" >
                            	<span id="pet_content"></span>
                            </div>
                            
                            <div class="col">
                                <label for="comin_date">入所日期:</label>
                                <input type="date" class="form-control" id="comin_date" name="comeInDate" placeholder="選擇日期">
                            	<span id="come_in_date"></span>
                            </div>
                    
                    
                    
                            <div class="col">
                                <label for="pet_cage">籠舍:</label>
                                <input class="form-control" type="text" placeholder="請輸入籠舍編號"  id="pet_cage" name="petCage">
                            	<span id="pet_cage"></span>
                            </div>
                    
                    
                    
                            <div class="col">
                            <label for="pet_num">收容編號:</label>
                            <input class="form-control" type="text" placeholder="請輸入收容編號"  id="pet_num" name="petNum">
                             <span id="pet_num"></span>
                            </div>
                    
                        <div class="col">
                            <label for="inputGroupFile02">照片:</label><span>請選擇一 ~ 三張照片</span>
                            <input type="file" class="form-control"id="petphoto" multiple name="petphoto" >
                        	<span id="petphoto"></span>

                        
                            <div class="pet_img"><span class="pet_img">預覽圖</span></div>
                        
                        </div>
                            <h2>領養者資訊</h2>

                        <div class="col">
                            <label >是否被領養:</label>
                            <input class="form-check-input" type="radio" name="adopted" id="pet_adopted_yes" value="true">
                            <label class="form-check-label" for="pet_adopted_yes">是</label>
                            <input class="form-check-input" type="radio" name="adopted" id="pet_adopted_no" checked value="false">
                            <label class="form-check-label" for="pet_adopted_no">否</label>
                        </div>

					<div id="adoped123" style = "display:none;">
                            <div class="col">
                    
                                <label for="mer">領養者:</label>
                                <input class="form-control" type="email" placeholder="請輸入領養者帳號"  id="userId" name="userId" required>
                            	<span id="user_id"></span>
                            </div>
                    
                            <div class="col">
                                <label for="adoped_date">領養日期:</label>
                                <input type="date" class="form-control" id="adopt_date"  placeholder="選擇日期" name="adopt_date">
                            	<span id="adoped_date"></span>
                            </div>
                      </div>
                      
                        <div class="button">
                            <button type="submit" class="btn btn-primary put_on btn-sm" id="submit_btn">確認上架</button>
                            <input type="hidden" name="action" value="insert">
                            <a href="pet_search.jsp">
                            <button type="button" class="btn btn-warning btn-sm delete" id="back_page">取消</button>
                       		</a>
                        </div>
                        </form>
                    </div>
                </div>
            </div>
 </div>



            <!-- jQuery -->
            <script src="../plugins/jquery/jquery.min.js"></script>
            <!-- jQuery UI 1.11.4 -->
            <script src="../plugins/jquery-ui/jquery-ui.min.js"></script>
            <!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<!--             <script> -->
<!--               $.widget.bridge('uibutton', $.ui.button) -->
<!--             </script> -->
            <!-- Bootstrap 4 -->
            <script src="../plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
            <!-- overlayScrollbars -->
            <script src="../plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
            <!-- AdminLTE App -->
            <script src="../dist/js/adminlte.js"></script>
			<script src="../dist/js/form.js"></script>
			

       <script>
                    // 這是動物照片
              $("#petphoto").change(function () {
                $("div.pet_img").html("");
                readURL(this);
                function readURL(input) {
                    if (input.files && input.files.length >= 0) {
                        for (var i = 0; i < input.files.length; i++) {
                            var reader = new FileReader();
                            reader.onload = function (e) {
                                var img = $("<img width='100' height='100'>").attr('src', e.target.result);
                                $("div.pet_img").append(img);
                            }
                            reader.readAsDataURL(input.files[i]);
                        }
                    }
                };
            });       

      
</script>
</body>
</html>