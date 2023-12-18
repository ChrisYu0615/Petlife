<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@page import="com.petlife.mall.entity.Comm"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
Comm comm = (Comm) request.getAttribute("comm"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>商品資料 - listOneComm.jsp</title>

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
				<h3>商品資料 - listOneComm.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>商品ID</th>
			<th>賣家ID</th>
			<th>商品名稱</th>
			<th>商品描述</th>
			<th>商品狀態</th>
			<th>上架時間</th>
			<th>商品縮圖</th>
			<th>商品分類ID</th>
			<th>商品庫存量</th>
			<th>商品價格</th>
			<th>商品優惠價</th>
			<th>瀏覽數</th>
		</tr>
		<tr>
			<td><%=comm.getCommId()%></td>
			<td><%=comm.getSeller().getSellerId()%></td>
			<td><%=comm.getCommName()%></td>
			<td><%=comm.getCommDesc()%></td>
			<td><%=comm.getCommState()%></td>
			<td><%=comm.getListDatetime()%></td>
			<td><%=comm.getCommImg()%></td>
			<td><%=comm.getCommCat().getCommCatId()%></td>
			<td><%=comm.getCommStock()%></td>
			<td><%=comm.getCommPrice()%></td>
			<td><%=comm.getCommOnsalePrice()%></td>
			<td><%=comm.getCommViewCount()%></td>
		</tr>
	</table>

</body>
</html>