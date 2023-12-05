<%@page import="com.petlife.coupon.entity.Coupon"%>
<%@page import="com.petlife.coupon.dao.CouponDAO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.petlife.coupon.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
Coupon couponVO = (Coupon) request.getAttribute("coupon"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>優惠券資料 - listOneCoupon.jsp</title>

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
	<tr><td>
		 <h3>員工資料 - listOneEmp.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>優惠碼ID</th>
		<th>優惠碼名稱</th>
		<th>優惠碼敘述</th>
		<th>使用條件</th>
		<th>開始時間</th>
		<th>結束時間</th>
		<th>折扣金額</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<tr>
			<td>${coupon.couponId}</td>
			<td>${coupon.couponName}</td>
			<td>${coupon.couponContent}</td>
			<td>${coupon.conditionsOfUse}</td>
			<td>${coupon.startDate}</td>
			<td>${coupon.endDate}</td> 
			<td>${coupon.discountAmount}</td>
	</tr>
</table>

</body>
</html>