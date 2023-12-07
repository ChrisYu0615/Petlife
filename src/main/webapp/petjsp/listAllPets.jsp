<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table data-toggle="table" data-sortable="true"
	data-sort-class="table-active" data-pagination="true"
	data-search="false" data-show-search-clear-button="false"
	data-show-refresh="false" data-show-toggle="false"
	data-show-columns="false" data-show-columns-toggle-all="true"
	class="table table-sm  table-borderless" id="myTable">
	<thead class="table ">
		<tr class="tb_header">
			<th scope="col" class="tb_header"></th>
			<th scope="col" class="tb_header">入所入期</th>
			<th scope="col" class="tb_header">種類</th>
			<th scope="col" class="tb_header">性別</th>
			<th scope="col" class="tb_header">收容編號</th>
			<th scope="col" class="tb_header">是否絕育</th>
			<th scope="col" class="tb_header">被領養日期</th>
		</tr>
	</thead>

	<tbody class="tb_con">
		<c:forEach var="pet" items="${petList}">
			
			<tr class="tb_con"  >
				<td scope="row" class="tb_con pet_id" data-row-id="${pet.id}" ><a href="#" >編輯</a></td>
				<td class="tb_con">${pet.comeInDate}</td>
				<td class="tb_con">${pet.variety.variety}</td>
				<td class="tb_con">${pet.petGender}</td>
				<td class="tb_con">${pet.petNum}</td>
				<td class="tb_con">${pet.petLigation}</td>
				<td class="tb_con">${pet.adoptDate}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
