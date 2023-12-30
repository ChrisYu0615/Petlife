<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.petlife.pet.entity.Pet"%>
<%@ page import="com.petlife.pet.entity.PetPhoto"%>
<%@ page import="com.petlife.shelter.entity.Shelter"%>

<div class="tab-content" id="nav-tabContent">
	<div class="tab-pane fade show active" id="nav-home" role="tabpanel"
		aria-labelledby="nav-home-tab">
		<div class="adoption_tab_item_wrapper">
			<div class="row">
				<c:forEach var="pet" items="${petList}">
					<div class="col-lg-3 col-md-6 col-sm-12 col-12">
						<div class="adoption_card_wrapper">
							<div class="adoption_item_img img_hover">
								<c:choose>
									<c:when test="${not empty pet.petPhotos}">
										<img
											src="<%=request.getContextPath()%>/project/petphoto.do?action=getPetPhotoTest2&photoId=${pet.petPhotos[0].photoId}"
											alt="">
									</c:when>
									<c:otherwise>
										<!-- 如果 pet.petPhotos 為空，可以添加一個預設圖片或其他處理邏輯 -->
										<img
											src="../assets/img/adoption/dog_default.jpg"
											alt="">
									</c:otherwise>
								</c:choose>


							</div>
							<form id="form"
								action="${pageContext.request.contextPath}/project/pet.do"
								method="post">
								<div class="adoption_item_content">
									<div class="adoption_info_btn" id="adoption_info_btn">
										<button type="submit" id="getOnePet">更多資訊</button>
										<input type="hidden" name="id" value="${pet.id}" id="pet_id">
										<input type="hidden" name="action" value="getPetById2">
									</div>


									<br>
									<ul>
										<li><span>品種:</span> ${pet.variety.variety}</li>
										<li><span>花色:</span> ${pet.petColor}</li>
										<li><span>性別:</span> ${pet.petGender}</li>
										<li><span>我在:</span> ${pet.shelter.shelterName}</li>
									</ul>

								</div>
							</form>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</div>
