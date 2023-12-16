<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/main/main.css">
<title>Add Reservations</title>
</head>
<body>
	<h1>新增預約</h1>

	<br>

	<table style="width:50%; text-align:center;">
		
	<div>
		<h3><b>所有欄位皆為必填欄位</b></h3>
		<form action="<%=request.getContextPath()%>/shelter/reservation.do" method="post" enctype="multipart/form-data">			
			
			<div>
			<label for="shelter">收容所ID:</label>
			<input id ="shelter" name="shelter" type="text" value="${param.shelter}" /><br>
			<label for="shelterBookingId">預約時段ID:</label>
			<input id ="shelterBookingId" name="shelterBookingId" type="text" value="${param.shelterBookingId}" /><br>
			<label for="userId">USER ID:</label>
			<input id ="userId" name="userId" type="text" value="${param.userId}" /><br>
			<label for="petId">寵物ID:</label>
			<input id ="petId" name="petId" type="text" value="${param.shelterPhoneNum}" /><br>
			</div>
			
		
			<div>
				<button type="submit" id="submit"> 送出新增 </button>
				<input  type="hidden" name="action" value="insert">
			</div>
		</form>

	</table>

	<br>
	<br>
	<br>
	
	<a href="${pageContext.request.contextPath}/index.jsp">回首頁</a>	
</body>
</html>