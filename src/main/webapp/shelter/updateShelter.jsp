<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/main/main.css">
<title>Add Shelters</title>
</head>
<body>
	<h1>修改收容所資訊</h1>
<%-- 	<c:if test="${shelterPageQty > 0}"> --%>
<%--   		<b><font color=red>第${currentPage}/${shelterPageQty}頁</font></b> --%>
<%-- 	</c:if> --%>
	<br>

	<table style="width:50%; text-align:center;">
		
	<div>
		<h3><b>所有欄位皆為必填欄位</b></h3>
		<form action="<%=request.getContextPath()%>/shelter/shelter.do" method="post" enctype="multipart/form-data">			
			
			<div>
			<label for="shelterAcct">收容所帳號:</label>
			<input id ="shelterAcct" name="shelterAcct" type="text" value="${param.shelterAcct}" /><br>
			<label for="shelterPwd">收容所密碼:</label>
			<input id ="shelterPwd" name="shelterPwd" type="text" value="${param.shelterPwd}" /><br>
			<label for="shelterName">收容所名稱:</label>
			<input id ="shelterName" name="shelterName" type="text" value="${param.shelterName}" /><br>
			<label for="shelterPhoneNum">收容所電話號碼:</label>
			<input id ="shelterPhoneNum" name="shelterPhoneNum" type="text" value="${param.shelterPhoneNum}" /><br>
			<label for="shelterAddress">收容所地址:</label>
			<input id ="shelterAddress" name="shelterAddress" type="text" value="${param.shelterAddress}" /><br>
<!-- 			<label for="shelterPhoto">收容所大頭貼:</label> -->
<%-- 			<input id ="shelterPhoto" name="shelterPhoto" type="text" value="${param.shelterPhoto}" /><br> --%>
			<label for="shelterIntroduction">收容所簡介:</label>
			<input id ="shelterIntroduction" name="shelterIntroduction" type="text" value="${param.shelterIntroduction}" /><br>
			<label for="shelterLng">收容所位置(經度):</label>
			<input id ="shelterLng" name="shelterLng" type="text" value="${param.shelterLng}" /><br>
			<label for="shelterLat">收容所位置(緯度):</label>
			<input id ="shelterLat" name="shelterLat" type="text" value="${param.shelterLat}" /><br>
			</div>
			
<!--             <div> -->
<!-- 			<label for="upFiles">照片:</label> -->
<!-- 			<input id ="upFiles" name="upFiles" type="file" onclick="previewImage()" multiple="multiple" onchange="hideContent('upFiles.errors');" /> -->
<%-- 			<span  id ="upFiles.errors" class="error">${errorMsgs.upFiles}</span> --%>
<!-- 			<div id="blob_holder"></div> -->
<!-- 			</div> -->
			
			<div>
				<div></div>
				<button type="submit" id="submit"> 送出新增 </button>
				<input  type="hidden" name="action" value="insert">
				<div></div>
			</div>
		</form>

	</table>
<%-- 	<c:if test="${currentPage > 1}"> --%>
<%-- 		<a href="${pageContext.request.contextPath}/shelter/shelter.do?action=insert&page=1">至第一頁</a>&nbsp; --%>
<%-- 	</c:if> --%>
<%-- 	<c:if test="${currentPage - 1 != 0}"> --%>
<%-- 		<a href="${pageContext.request.contextPath}/shelter/shelter.do?action=insert&page=${currentPage - 1}">上一頁</a>&nbsp; --%>
<%-- 	</c:if> --%>
<%-- 	<c:if test="${currentPage + 1 <= shelterPageQty}"> --%>
<%-- 		<a href="${pageContext.request.contextPath}/shelter/shelter.do?action=insert&page=${currentPage + 1}">下一頁</a>&nbsp; --%>
<%-- 	</c:if> --%>
<%-- 	<c:if test="${currentPage != shelterPageQty}"> --%>
<%-- 		<a href="${pageContext.request.contextPath}/shelter/shelter.do?action=insert&page=${empPageQty}">至最後一頁</a>&nbsp; --%>
<%-- 	</c:if> --%>
	<br>
	<br><br>
	
	<a href="${pageContext.request.contextPath}/index.jsp">回首頁</a>	
</body>
</html>