<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@page import="com.petlife.shelter.entity.Shelter"%>

<!DOCTYPE html>
<html lang="zh-TW">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache-Control" content="no-store, no-cache, must-revalidate">
    <meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Expires" content="0">
    <title>收容所基本資料</title>

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
    <link rel="stylesheet" href="../dist/css/pet_edit.css">
     <script defer src="your-script.js"></script>
    
<% 
	Integer id =null;
	Shelter shelter = (Shelter) session.getAttribute("shelter");
    if(shelter!= null){
    	id = shelter.getShelterId();
    	request.setAttribute("id", id);
}

   
    int shelterPhotoLength = (shelter.getShelterPhoto() != null) ? shelter.getShelterPhoto().length : 0;
%>

    

</head>

<body class="hold-transition sidebar-mini layout-fixed" onload="opener.location.reload()">
    <div class="wrapper">
        <!-- 上方Navbar -->
        <nav class="main-header navbar navbar-expand navbar-white navbar-light">
            <!-- 上方左邊Left navbar links -->
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>
                </li>
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
        <%@ include file="sidebar.jsp" %>

        <!-- //這邊塞主內容文字 -->
        <!-- Main content -->
        <div class="content">
            <div class="container-fluid">
                <div class="col-lg-6">
                    <h1>編輯基本資料</h1>
                    <!-- 第一行  -->
                    <div class="material">
                        <!-- ... (rest of the form content) ... -->
                        <form id="shelter_update" method="post" action="${pageContext.request.contextPath}/shelter/shelter.do">
                        <span class="material_list">
                            <label for="shelter_name" class="shelter_name">收容所名稱:</label>
<!--                             <input class="form-control" type="text" placeholder="請輸入想更改的名稱" -->
<!--                                 aria-label="default input example" id="shelter_name"> -->
							<span>${shelter.shelterName}</span>
                        </span>
                        
                        
                        <span class="material_list">
                            <label for="shelter_password1">帳號:</label>
<!--                             <input class="form-control" type="text" placeholder="請再次輸入密碼" -->
<!--                                 aria-label="default input example" id="shelter_password1"> -->
                        			<span>${shelter.shelterAcct}</span>
                        </span>



                        <span class="material_list">
                            <label for="shelter_phone">電話:</label>
<!--                             <input class="form-control" type="text" placeholder="請輸入電話號碼" -->
<!--                                 aria-label="default input example" id="shelter_phone"> -->
                       					<span>${shelter.shelterPhoneNum}</span>
                        </span>



                        <span class="material_list">
                            <label for="shelter_adress">詳細地址:</label>
                        <span>${shelter.shelterAddress}</span>
                        </span>

                        <span class="material_list">
                            <label for="shelter_introduction">簡介:</label>
                        		<span>${shelter.shelterIntroduction}</span>
                        </span>

<!--                         <span class="material_list"> -->
<!--                             <span class="e_photo">環境照片:<input type="file" id="e_photo" multiple></span> -->
<!--                             <div id="preview"><span class="text">預覽圖(可一次選擇多張)</span></div> -->

<!--                         </span> -->

                        <div class="button">
<!--                             <button type="button" class="btn btn-primary save" onclick="redirectToOtherJSP()">編輯</button> -->
                            <input type="submit"  value="編輯" id="forward">
                            <input type="hidden" name="action" value="update_forward">
                            
                        </div>


                        <!-- 右上角大頭貼  -->
                  
                        <div class="img">
                            <span> 
                                
                              
                             <div class="s_img" id="s_img">
                             <% if (shelterPhotoLength != 0) { %>
                                <img src="<%=request.getContextPath()%>/shelter/shelter.do?action=getShelterPhoto&shelterId=${shelter.shelterId}" 
									width="100px" height="100px" style="border-radius: 50%;" >
							<% } %>
									 </div>
                            </span>
                          
                        </div>
                        </form>
                    </div>
                </div><!--/.container-fluid-->
            </div>
        </div>
        <!-- ./wrapper -->

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
        
<!--         <script> 
         window.onload = function() {
             location.reload(true);
            window.onload = null; 
         };
   
          
         </script> -->
    </body>
</html>
