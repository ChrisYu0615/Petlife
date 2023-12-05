<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Petlife Coupon: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>IBM Coupon: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Coupon: Home</p>

<h3>��Ƭd��:</h3>
	
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllCoupon.jsp'>List</a> all Coupons.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="coupon.do" >
        <b>��J�u�f��s�� (�p1):</b>
        <input type="text" name="coupon_Id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="couponService" scope="page" class="com.petlife.coupon.service.CouponServiceImpl" />
   
  <li>
     <FORM METHOD="post" ACTION="coupon.do" >
       <b>����u�f��s��:</b>
       <select size="1" name="coupon_Id">
         <c:forEach var="coupon" items="${couponServiceImpl.all}" > 
          <option value="${coupon.couponId}">${coupon.couponId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="coupon.do" >
       <b>����u�f��W:</b>
       <select size="1" name="coupon_Id">
         <c:forEach var="coupon" items="${couponServiceImpl.getAll}" > 
          <option value="${coupon.couponId}">${coupon.couponName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>


<h3>�u�f��޲z</h3>

<ul>
  <li><a href='addCoupon.jsp'>Add</a> a new coupon.</li>
</ul>

</body>
</html>