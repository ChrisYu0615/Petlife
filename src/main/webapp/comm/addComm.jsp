<%@page import="com.petlife.mall.entity.Comm"%>
<!-- 保留 Comm 的引用 -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
Comm comm = (Comm) request.getAttribute("comm");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>商品圖片新增 - addComm.jsp</title>
<!-- 页面标题仍然是添加文章图片 -->

<!-- 页面样式 -->
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

table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>
</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>商品新增 - addComm.jsp</h3> <!-- 标题仍然保留为文章图片 -->
			</td>
			<td>
				<h4>
					<a href="select_page.jsp"><img src="images/tomcat.png"
						width="100" height="100" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>商品資料新增:</h3>

	<!-- 錯誤提示 -->
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">请修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>


	<form method="post" action="comm.do" name="form1"
		enctype="multipart/form-data">
	
		<table>
			<!-- 表單字段 -->
<!-- 			<tr> -->
<!-- 				<td>商品ID:</td> -->
<!-- 				<td><input type="text" name="commId" -->
<%-- 					value="${comm != null ? comm.comm.commId : ''}" size="45" /></td> --%>
<!-- 			</tr> -->
			<tr>
				<td>賣家ID:</td>
				<td><input type="text" name="seller" size="45" /></td>
			</tr>
			<tr>
				<td>商品名稱:</td>
				<td><input type="text" name="commName" size="45" /></td>
			</tr>
			<tr>
				<td>商品描述:</td>
				<td><input type="text" name="commDesc" size="45" /></td>
			</tr>
			<tr>
				<td>商品狀態:</td>
				<td><input type="text" name="commState" size="45" /></td>
			</tr>
			<tr>
				<td>上架時間:</td>
				<td><input type="text" name="listDatetime" size="45" /></td>
			</tr>
			<tr>
				<td>商品縮圖:</td>
				<td><input type="file" name="commImg" size="45" /></td>
			</tr>
			<tr>
				<td>商品分類ID:</td>
				<td><input type="text" name="commCat" size="45" /></td>
			</tr>
			<tr>
				<td>商品庫存量:</td>
				<td><input type="text" name="commStock" size="45" /></td>
			</tr>
			<tr>
				<td>商品價格:</td>
				<td><input type="text" name="commPrice" size="45" /></td>
			</tr>
			<tr>
				<td>商品優惠價:</td>
				<td><input type="text" name="commOnsalePrice" size="45" /></td>
			</tr>
			<tr>
				<td>瀏覽數:</td>
				<td><input type="text" name="commViewCount" size="45" /></td>
			</tr>
		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="提交新增">
	</form>
</body>
</html>
