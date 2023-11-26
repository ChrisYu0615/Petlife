<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.petlife.user.entity.*"%>
<%@ page import="com.petlife.acctstate.entity.*"%>
<%@ page import="com.petlife.accttype.entity.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
User user = (User) request.getAttribute("user"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>員工資料 - listOneEmp.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body bgcolor='white'>

	<h4>此頁暫練習採用 Script 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>員工資料 - listOneEmp.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>會員編號</th>
			<th>會員帳號</th>
			<th>會員密碼</th>
			<th>會員姓名</th>
			<th>會員暱稱</th>
			<th>密碼錯誤次數</th>
			<th>會員生日</th>
			<th>會員住址</th>
			<th>會員電話</th>
			<th>會員性別</th>
			<th>會員大頭照</th>
			<th>會員帳號狀態</th>
			<th>會員帳號類別</th>
			<th>帳號創建時間</th>
		</tr>
		<tr>
			<td>${user.userId}</td>
			<td>${user.userAcct}</td>
			<td>${user.userPwd}</td>
			<td>${user.userName}</td>
			<td>${user.userNickName}</td>
			<td>${user.userPwdErrTimes}</td>
			<td>${user.birthday}</td>
			<td>${user.address}</td>
			<td>${user.phoneNum}</td>
			<td>${(user.gender == true)?"男":"女"}</td>
			<td>${user.headshot}</td>
			<td>${user.acctState.acctStateType}</td>
			<td>${user.acctType.acctType}</td>
			<td>${user.userCreateTime}</td>
		</tr>
	</table>

</body>
</html>