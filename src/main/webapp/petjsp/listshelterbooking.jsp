<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<table data-toggle="table" data-sortable="true"
	data-sort-class="table-active" data-pagination="true"
	data-search="false" data-show-search-clear-button="false"
	data-show-refresh="false" data-show-toggle="false"
	data-show-columns="false" data-show-columns-toggle-all="true"
	class="table table-sm  table-borderless" id="myTable">
	
	<thead class="table ">
		<tr class="tb_header" id="booking">
			<th scope="col" class="tb_header" id="booking"></th>
			<th scope="col" class="tb_header" id="booking">日期</th>
			<th scope="col" class="tb_header" id="booking">時間</th>
			<th scope="col" class="tb_header" id="booking">預約人數</th>
			<th scope="col" class="tb_header" id="booking">是否營業</th>

		</tr>
	</thead>

	<tbody class="tb_con">
		<c:forEach var="shelterbooking" items="${shelterBookingList}">
			
			<tr class="tb_con"  >
				<td scope="row" id="booking_list" class="tb_con shelterBooking_id" data-row-id="${shelterbooking.id}" >查看</td>
				<td class="tb_con" id="booking_list">${shelterbooking.shelterBookingDate}</td>
				<td class="tb_con" id="booking_list">
				<fmt:formatDate value="${shelterbooking.shelterBookingTime}" pattern="HH:mm"/>
				</td>
				<td class="tb_con" id="booking_list">${shelterbooking.shelterBookingNum}</td>
 			
					<c:if test="${shelterbooking.shelterBookingNum eq shelterbooking.shelterBookingMax }">	
								<td class="tb_con">否</td>
                  	</c:if>
                  	<c:if test="${shelterbooking.shelterBookingNum ne shelterbooking.shelterBookingMax }">	
								<td class="tb_con">是</td>
                  	</c:if>
                  	
			</tr>
		</c:forEach>
	</tbody>
</table>

<a href="../petjsp/shelter_date.jsp" id="button">
		<button type="button" class="btn btn-warning delete btn-sm" id="rowback">返回</button>
</a>

