<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/main/main.css">
<title>List Shelters</title>
</head>
<body>
	<h1>預約列表</h1>
	<c:if test="${shelterPageQty > 0}">
  	</c:if>
	<br>

	<table style="width:50%; text-align:center;">
		<tr>
			<th>預約流水號</th>
			<th>收容所編號</th>
			<th>會員編號</th>
			<th>收容所動物編號</th>
			<th>預約時段編號</th>
			<th>預約狀態</th>
			<th>建立時間</th>

		</tr>
		<c:forEach var="reservation" items="${reservationList}">
			<tr>
				<td>${reservation.resId}</td>
				<td>${reservation.shelter.shelterId}</td>
				<td>${reservation.shelterBookingId}</td>
				<td>${reservation.userId}</td>
				<td>${reservation.petId}</td>
				<td>${reservation.resType.resTypeId}</td>
				<td>${reservation.resKeyinDate}</td>

				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/shelter/shelter.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="shelterId" value="${shelter.shelterId}"> <input type="hidden"
							name="action" value="getOneToUpdate">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>

	<br>

	<br><br>
	
	<a href="${pageContext.request.contextPath}/index.jsp">回首頁</a>	
</body>
</html>