<%@page import="com.petlife.coupon.entity.Coupon"%>
<%@page import="com.petlife.coupon.dao.CouponDAO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.petlife.coupon.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
Coupon couponVO = (Coupon) request.getAttribute("coupon"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<title>�u�f���� - listOneCoupon.jsp</title>

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

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>���u��� - listOneEmp.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�u�f�XID</th>
		<th>�u�f�X�W��</th>
		<th>�u�f�X�ԭz</th>
		<th>�ϥα���</th>
		<th>�}�l�ɶ�</th>
		<th>�����ɶ�</th>
		<th>�馩���B</th>
		<th>�ק�</th>
		<th>�R��</th>
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