<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.petlife.pet.entity.PetVariety"%>
<%@ page import="com.petlife.pet.entity.Pet"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/form_update.css">
</head>
<body>
<% String selectedGender =" ${pet.petGender} "; %>
<% String selectedpetLigation =" ${pet.petLigation} "; %>
<% Boolean selectedadopt = (Boolean) pageContext.getAttribute("pet.adopt"); %>




<h1 id='update_pet'>修改收容寵物資料</h1>
<form id="form_update" action="${pageContext.request.contextPath}/project/pet.do" class="from" method="post">

        <table id="mytable">
            <tbody>
<!--         <tr> -->
<!--             <td name="Id">種類:</td> -->
<%--             <td>${petVariety.type}</td> --%>
<!--         </tr> -->
  
        <tr>
            <td >性別:</td>

            <td>
                 <input class="form-check-input" type="radio" name="petGender" id="pet_gender_m" 
                 value="公" ${pet.petGender.equals("公")? "checked" : ""}>
                <label class="form-check-label" for="pet_gender_m">公</label>
                
            </td>

            <td>
                <input class="form-check-input" type="radio" name="petGender" id="pet_gender_w" 
                value="母" ${pet.petGender.equals("母")? "checked" : ""}>
                
                <label class="form-check-label" for="pet_gender_w">母</label>

            </td>
        </tr>

        <tr>
            <td >品種:
            	<input type="hidden" name="petVarietyId" value="${pet.petVariety}"  >
            </td>
            
            <td>${pet.petVariety}</td>
        </tr>

    
        <tr>
            <td>絕育:</td>
            <td>
                <input class="form-check-input" type="radio" name="petLigation" id="pet_ligation_yes"
                value="有絕育" ${pet.petLigation.equals("有絕育")? "checked" : ""}>
                <label class="form-check-label" for="pet_ligation_yes">是</label>
            </td>

            <td>
                <input class="form-check-input" type="radio" name="petLigation" id="pet_ligation_no"
                value="未絕育" ${pet.petLigation.equals("未絕育")? "checked" : ""}>
                <label class="form-check-label" for="pet_ligation_no">否</label>
            </td>
        </tr>



        <tr>
            <td >毛色:</td>
            <td>
                <input type="text" name="petColor" value="${pet.petColor}">
            </td>
        </tr>

        <tr>
            <td>開放領養:</td>
            <td>
                <input class="form-check-input" type="radio" name="adopt" id="pet_adopt_yes"
                value="true"  ${ pet.adopt ? "checked" : ""}>
                <label class="form-check-label" for="pet_adopt_yes">是</label>

            </td>

            <td>
                <input class="form-check-input" type="radio" name="adopt" id="pet_adopt_no"
                value="false" ${!pet.adopt ? "checked" : ""}>
                <label class="form-check-label" for="pet_adopt_no">否</label>
            </td>


        </tr>



        <tr>
            <td >入所日期:
            <input type="hidden" name="comeInDate" value="${pet.comeInDate}"  >
            </td>
            <td>${pet.comeInDate} </td>
        </tr>


        <tr>
            <td >籠舍:</td>
            <td>
                <input class="form-control" type="text" name="petCage"  id="pet_cage" value="${pet.petCage} ">
            </td>
        </tr>
        

        <tr>
            <td >收容編號:
             <input type="hidden" name="petNum" value="${pet.petNum}">
            </td>
            <td>${pet.petNum}</td>
        </tr>
        
        <tr>
            <td >描述:</td>
            <td>
           		 <input class="form-control" type="text"  name="pet_content"  id="petContent" value="${pet.petContent} ">
            </td>
        </tr>
    

        <tr id="pet_photo">
            <td >照片:</td>
            <td> <input type="file" class="form-control"id="inputGroupFile02"  name=""></td>
            <td class="photo"> 
            <c:forEach var="photo"
								items="${pet.petPhotos}">
								<img
									src="<%=request.getContextPath()%>/project/petphoto.do?action=getPetPhotoTest&photoId=${photo.photoId}"
									width="100px">
							</c:forEach>
            </td>

        </tr>


		<tr >
            <td >是否被領養:</td>
            <td> 
            	 <input class="form-check-input" type="radio" name="adopted" id="adopted_yes" 
                 value="true" ${pet.adopted ? "checked" : ""}>
                <label class="form-check-label" for="adopted_yes">是</label>
            </td>
            <td> 
            	 <input class="form-check-input" type="radio" name="adopted" id="adopted_no" 
                 value="false" ${!pet.adopted ? "checked" : ""}>
                <label class="form-check-label" for="adopted_no">否</label>
            </td>
            
        </tr>
        <tr id="userId">
            <td >領養者:</td>
            <td> 
            	 <input class="form-control" type="text"  name="userId"  id="userId" value="${pet.userId} ">
            </td>
            
        </tr>
        <tr id="adopt_date">
            <td >領養日期:</td>
            <td> <input type="date" class="form-control" id="adopt_date"  placeholder="選擇日期" name="adopt_date" value="${pet.adoptDate}"></td>
            
        </tr>
        
       
    </tbody>
    </table>

    <div class="col">
    	 
    	  
    	  
        <button type="submit" class="btn btn-primary put_on btn-sm" >確認修改</button>
        <input type="hidden" name="id"  value="${pet.id}">
        <input type="hidden" name="action" value="update_put">
        
        
        <button type="button" class="btn btn-warning btn-sm delete"><a href="pet_search.jsp" style="text-decoration:none" class="back">取消</a></button>
    </div>
    </form>
</body>
</html>
