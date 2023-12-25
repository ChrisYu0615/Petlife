<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/dist/css/form_update.css">
<style>
#button{
 position: relative;
     left: 95%;
}

#res_table{
	margin:50px 0px;
	 position: relative;
     left: 60%;
     background-color: #FFF2CC;
     width:400px;
      border-radius: 30px;
}
td{
	
 	padding:5px 15px;
}
/* tr:nth-child(n+1):nth-child(-n+4) { */
/*     background-color: #FFF2CC; */
/*     border-radius: 30px; */
/*     margin:0px; */
/* } */
tr:nth-child(n+5):nth-child(-n+8) {
   background-color: lightblue;
    margin:0px;
}
tr:last-child{
	border-radius: 0px 0px 30px 30px ;
}

</style>
</head>
<body>

	
	

		<table id="res_table">
		<tbody>
			<c:forEach var="reservation" items="${reservationList}">
			<tr>
					<td class="title">預約人:</td>
					<td>${reservation.user.userName}</td>
			</tr>
			<tr>		
					<td class="title">手機:</td>
					<td>${reservation.user.phoneNum}</td>
			</tr>	
			<tr>	
					<td class="title">預約寵物收容編號:</td>
					<td>${reservation.pet.petNum}</td>
			</tr>		
			<tr>		
					<td class="title">預約日期:</td>
					<td>${reservation.shelterBooking.shelterBookingDate}</td>
			</tr>			
			</c:forEach>
			</tbody>
		</table>
		
		<a href="../emp/shelter_date.jsp" id="button">
		<button type="button" class="btn btn-warning delete btn-sm" id="rowback">返回</button>
		</a>



</body>
</html>