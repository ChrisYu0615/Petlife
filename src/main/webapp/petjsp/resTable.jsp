<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <table data-toggle="table" data-sortable="true" data-sort-class="table-active"
                          data-pagination="true" data-search="false" data-show-search-clear-button="false"
                          data-show-refresh="false" data-show-toggle="false" data-show-columns="false"
                          data-show-columns-toggle-all="true" class="table table-sm  table-borderless" id="mytable">


                          <thead class="table">
                            <tr class="tb_header">
                                <th scope="col" class="tb_header ">預約人</th>
                                <th scope="col" class="tb_header">預約人手機</th>
                                <th scope="col" class="tb_header">收容編號</th>
                                <th scope="col" class="tb_header">預約日期</th>
                                <th scope="col" class="tb_header">預約時段</th>
                                <th scope="col" class="tb_header ">請確認狀態</th>
                            </tr>
                        </thead>

                          <tbody class="tb_con">
                            

                            <tr class="tb_con">
                            
       <c:forEach var="pet" items="${petList}">
			
						<tr class="tb_con"  >
							<td scope="row" class="tb_con pet_id" data-row-id="${pet.id}" ><a href="#" >編輯</a></td>
							<td class="tb_con">${pet.comeInDate}</td>
							<td class="tb_con">${pet.petVariety}</td>
							<td class="tb_con">${pet.petGender}</td>
							<td class="tb_con">${pet.petNum}</td>
							<td class="tb_con">${pet.petLigation}</td>
							<td class="tb_con">${pet.adoptDate}</td>
						</tr>
		</c:forEach>
                                <td class="tb_con">陳大明</td>
                                <td class="tb_con">0911111111</td>
                                <td class="tb_con">A10001</td>
                                <td class="tb_con">2023/11/11</td>
                                <td class="tb_con">早上</td>
                                <td >
                                    <button type="button" class="btn btn-primary res_con  put_on">確認預約</button>
                                    <button type="button" class="btn btn-warning res_con delete">取消預約</button>
                                </td>
                            </tr>

                            
                        </tbody>
                        
                        </table>