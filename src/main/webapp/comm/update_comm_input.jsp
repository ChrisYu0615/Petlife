<%@page import="com.petlife.seller.entity.Seller"%>
<%@page import="com.petlife.mall.entity.CommCat"%>
<%@page import="com.petlife.mall.entity.Comm"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ page import="com.emp.model.*"%> --%>

<% //見com.emp.controller.EmpServlet.java第163行存入req的empVO物件 (此為從資料庫取出的empVO, 也可以是輸入格式有錯誤時的empVO物件)
   Comm comm = (Comm) request.getAttribute("comm");
%>
<%-- --<%= empVO==null %>--${empVO.deptno}-- <!-- line 100 --> --%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>商品編號資料修改 - update_comm_input.jsp</title>

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
	<tr><td>
		 <h3>商品編號資料修改 - update_comm_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

	<form method="post" action="comm.do" name="form1"
		enctype="multipart/form-data">
<table>
	<tr>
		<td>商品編號:<font color=red><b>*</b></font></td>
		<td>${comm.commId}</td>
	</tr>
<!-- 	<tr> -->
<!-- 		<td>商品編號編號:</td> -->
<%-- 		<td><input type="HIDDEN" name="commId" value="<%=comm.getCommId()%>" size="45"/></td> --%>
<!-- 	</tr> -->
				<tr>
				<td>賣家ID:</td>
				<td><input type="text" name="seller" 
				value="<%=comm.getSeller().getSellerId()%>" size="45" /></td>
			</tr>
			<tr>
				<td>商品名稱:</td>
				<td><input type="text" name="commName" 
				value="<%=comm.getCommName()%>" size="45" /></td>
			</tr>
			<tr>
				<td>商品描述:</td>
				<td><input type="text" name="commDesc" 
				value="<%=comm.getCommDesc()%>" size="45" /></td>
			</tr>
			<tr>
				<td>商品狀態:</td>
				<td><input type="text" name="commState" 
				value="<%=comm.getCommState()%>" size="45" /></td>
			</tr>
			<tr>
				<td>上架時間:</td>
				<td><input type="text" name="listDatetime" 
				value="<%=comm.getListDatetime()%>" size="45" /></td>
			</tr>
			<tr>
				<td>商品縮圖:</td>
				<td><input type="file" name="commImg" 
				value="<%=comm.getCommImg()%>" size="45" /></td>
			</tr>
			<tr>
				<td>商品分類ID:</td>
				<td><input type="text" name="commCat" 
				value="<%=comm.getCommCat().getCommCatId()%>" size="45" /></td>
			</tr>
			<tr>
				<td>商品庫存量:</td>
				<td><input type="text" name="commStock"
				value="<%=comm.getCommStock()%>"  size="45" /></td>
			</tr>
			<tr>
				<td>商品價格:</td>
				<td><input type="text" name="commPrice" 
				value="<%=comm.getCommPrice()%>" size="45" /></td>
			</tr>
			<tr>
				<td>商品優惠價:</td>
				<td><input type="text" name="commOnsalePrice" 
				value="<%=comm.getCommOnsalePrice()%>" size="45" /></td>
			</tr>
			<tr>
				<td>瀏覽數:</td>
				<td><input type="text" name="commViewCount" 
				value="<%=comm.getCommViewCount()%>" size="45" /></td>
			</tr>


<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>部門:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="deptno"> -->
<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
<%-- 				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)?'selected':'' } >${deptVO.dname} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="commId" value="<%=comm.getCommId()%>">
<input type="submit" value="送出修改"></FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>


</html>