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
<!-- 		<p><label>到職日期間範圍</label></p> -->
<!-- 		<input type="date" name="starthiredate"> ～ <input type="date" name="endhiredate"><br> -->
<!-- 		<p><label>薪資範圍</label></p> -->
<!-- 		<input type="text" name="startsal"> ～ <input type="text" name="endsal"><br> -->
		<p><input type="submit" value="送出"></p>
		<input type="hidden" name="action" value="compositeQuery">
	</form>
</body>
</html>