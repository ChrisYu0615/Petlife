<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/main/main.css">
<title>List Shelter</title>
</head>
<body>
	<h1>收容所列表</h1>
	<br>
	<table style="width:50%; text-align:center;">
		<tr>
			<th>收容所編號</th>
			<th>收容所帳號</th>
			<th>收容所密碼</th>
			<th>收容所名稱</th>
			<th>帳號建立時間</th>
			<th>密碼錯誤次數</th>
			<th>收容所電話</th>
			<th>收容所地址</th>
			<th>收容所大頭貼</th>
			<th>收容所簡介</th>
			<th>帳號狀態</th>
			<th>帳號種類</th>
			<th>經度</th>
			<th>緯度</th>
		</tr>
		<c:forEach var="shelter" items="${shelterList}">
			<tr>
				<td>${shelter.shelterId}</td>
				<td>${shelter.shelterAcct}</td>
				<td>${shelter.shelterPwd}</td>
				<td>${shelter.shelterName}</td>
				<td>${shelter.shelterCreateTime}</td>
				<td>${shelter.shelterPwdErrTimes}</td>
				<td>${shelter.shelterPhoneNum}</td>
				<td>${shelter.shelterAddress}</td>
				<td>${shelter.shelterPhoto}</td>
				<td>${shelter.shelterIntroduction}</td>
				<td>${AcctState.acctState}</td>
				<td>${AcctType.acctTypeId}</td>
				<td>${shelter.shelterLng}</td>
				<td>${shelter.shelterLat}</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	
	<a href="${pageContext.request.contextPath}/index.jsp">回首頁</a>	
</body>
</html>