<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table data-toggle="table" data-sortable="true"
	data-sort-class="table-active" data-pagination="true"
	data-search="false" data-show-search-clear-button="false"
	data-show-refresh="false" data-show-toggle="false"
	data-show-columns="false" data-show-columns-toggle-all="true"
	class="table table-sm  table-borderless" id="my_res_Table">
  					<thead class="table">
                            <tr class="tb_header">
                                <th scope="col" class="tb_header ">預約人</th>
                                <th scope="col" class="tb_header">預約人手機</th>
                                <th scope="col" class="tb_header">收容編號</th>
                                <th scope="col" class="tb_header">預約日期</th>
                                <th scope="col" class="tb_header">預約時段</th>
                                <th scope="col" class="tb_header "></th>
                            </tr>
                        </thead>
	<tbody class="tb_con">
		<c:forEach var="reservation" items="${reservationList}">
			
			<tr class="tb_con"  >
			
				<td class="tb_con">${reservation.user.userName}</td>
				<td class="tb_con">${reservation.user.phoneNum}</td>
				<td class="tb_con">${reservation.pet.petNum}</td>
				<td class="tb_con">${reservation.shelterBooking.shelterBookingDate}</td>
				<td class="tb_con">${reservation.shelterBooking.shelterBookingTime}</td>
 				<td>
 				 
 					<c:if test="${reservation.resType.resTypeId eq 1}">
						<button type="button" class="btn btn-primary res_con  put_on" id="res_ok" value="${reservation.resId}" name="resId">確認預約</button>
						 <button type="button" class="btn btn-warning res_con delete" id="res_delete" value="${reservation.resId}">取消預約</button>
					</c:if>
					
					 <c:if test="${reservation.resType.resTypeId eq 2}">
					  <button type="button" class="btn btn-warning res_con delete" id="res_delete" value="${reservation.resId}" style=" margin-left: 50px;">取消預約</button>
                  	</c:if>
                  	
                  	 <c:if test="${reservation.resType.resTypeId eq 3}">
                  	</c:if>
                  	 <c:if test="${reservation.resType.resTypeId eq 4}">
                  	 <button type="button" class="btn btn-primary res_con  put_on" id="res_ok" value="${reservation.resId}" name="resId">成功領養</button>
					<button type="button" class="btn btn-warning res_con delete" id="res_delete" value="${reservation.resId}" style=" width: 100px;">未成功領養</button>
                  	</c:if>
                     <input type="hidden" value="${reservation.resType.resTypeId}" name="resType" id="input_resType">
                    
              
                 </td>
			</tr>
		</c:forEach>
	</tbody>
</table>