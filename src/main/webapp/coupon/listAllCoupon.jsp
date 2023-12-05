<%@page import="com.petlife.coupon.entity.Coupon"%>
<%@page import="com.petlife.coupon.service.CouponServiceImpl"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.petlife.coupon.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
CouponServiceImpl couponService = new CouponServiceImpl();
    List<Coupon> list = couponService.getAllCoupons();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有優惠券資料 - listAllCoupon.jsp</title>

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
	width: 800px;
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

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有優惠券資料 - listAllCoupon.jsp</h3>
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
	<%@ include file="page1.file" %> 
	<c:forEach var="coupon" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${coupon.couponId}</td>
			<td>${coupon.couponName}</td>
			<td>${coupon.couponContent}</td>
			<td>${coupon.conditionsOfUse}</td>
			<td>${coupon.startDate}</td>
			<td>${coupon.endDate}</td> 
			<td>${coupon.discountAmount}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/coupon/coupon.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="coupon_id"  value="${coupon.couponId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/coupon/coupon.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="couponId"  value="${coupon.couponId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>