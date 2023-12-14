<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/main/main.css">
<title>Hibernate Demo</title>
</head>
<body>
	<h1>Shelter Test</h1>
	<h2>收容所系統</h2>
	<a href="${pageContext.request.contextPath}/shelter/shelter.do?action=getAll">查詢所有收容所</a>
	<br>
	<a href="${pageContext.request.contextPath}/shelter/addShelter.jsp">新增收容所</a>
	<br><br>
	<h3><b>複合查詢 (使用 Criteria Query)：</b></h3>
	<form action="${pageContext.request.contextPath}/shelter/shelter.do" method="post">
		<p><label>收容所名稱模糊查詢：</label></p>
		<input type="text" name="shelterName">
		
		<br>
		<p><label>收容所縣市：</label></p>
		<select name="shelterAddress" id="shelterAddress">
			<option value="">選取縣市</option>
			<option value="臺北市">臺北市</option>
			<option value="新北市">新北市</option>
			<option value="桃園市">桃園市</option>
			<option value="新竹市">新竹市</option>
			<option value="苗栗縣">苗栗縣</option>
		</select>
		<p><input type="submit" value="送出"></p>
		<input type="hidden" name="action" value="compositeQuery">
	</form>
	
		<a href="${pageContext.request.contextPath}/shelter/reservation.do?action=getAll">查詢所有預約</a>
		<br>
		<a href="${pageContext.request.contextPath}/shelter/addReservation.jsp">新增預約</a>
	<br>
	
	<form action="${pageContext.request.contextPath}/shelter/reservation.do" method="post">
		<p><label>預約狀態查詢：</label></p>
		<select name="reservation.resType.resTypeId" id="resTypeId">
			<option value="">選取狀態</option>
			<option value="1">待確認</option>
			<option value="2">已確認</option>
			<option value="3">已取消</option>
			<option value="4">待審核</option>
			<option value="5">成功領養</option>
			<option value="6">未成功領養</option>
		</select>
			<p><input type="submit" value="送出"></p>
		<input type="hidden" name="action" value="compositeQuery">
	</form>
</body>
</html>