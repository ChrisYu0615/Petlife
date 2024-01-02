<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="com.petlife.shelter.entity.Shelter"%>
<% 
	Integer shelterId =null;
	Shelter shelter_2 = (Shelter) session.getAttribute("shelter");
%>

<aside class="main-sidebar sidebar-dark-primary elevation-4">
	<!-- Brand Logo -->
	<a href="<%= request.getContextPath()%>/petjsp/shelter_update.jsp" class="brand-link"> <img
		src="../dist/img/main_logo.png" alt="AdminLTE Logo"
		class="brand-image img-corners elevation-3" style="opacity: .8">
		<span class="brand-text font-weight-light">PetLife後臺管理</span>
	</a>

	<!-- Sidebar -->
	<div class="sidebar">
		<!-- Sidebar user panel (optional) -->
		<div class="user-panel mt-3 pb-3 mb-3 d-flex">
			<div class="image">
				<img src="../dist/img/login_user1.png"
					class="img-circle elevation-2" alt="User Image">
			</div>
			<div class="info">
				<a href="#" class="d-block"><%= shelter_2.getShelterName()%></a>
			</div>
		</div>
		<!-- Sidebar Menu -->
		<nav class="mt-2">
			<ul class="nav nav-pills nav-sidebar flex-column"
				data-widget="treeview" role="menu" data-accordion="false">


				<li class="nav-item">
					<c:url var="actionUrl" value="/shelter/shelter.do">
						<c:param name="action" value="update" />
					</c:url> 
						<a href="${actionUrl}" id="shelter" class="nav-link"> 
						<i class="fas fa-pencil-alt"></i>
						<p>編輯基本資料</p>
					</a>
				</li>

				<li class="nav-item">
					<c:url var="petSearchUrl" value="/petjsp/pet_search.jsp" /> 
						<a href="${petSearchUrl}" class="nav-link">
					 		<i class="fas fa-paw"></i>
							<p>收容動物管理</p>
						</a>
				</li>
				<li class="nav-item">
				<c:url var="shelterUrl" value="/petjsp/shelter_date.jsp" /> 
				<a href="${shelterUrl}" class="nav-link"> <!-- <i class="nav-icon fas fa-copy"></i> -->
						<i class="far fa-calendar-plus"></i>
						<p>日曆功能</p>
				</a></li>

				<li class="nav-item">
				<c:url var="petresUrl" value="/petjsp/pet_res.jsp" /> 
				<a href="${petresUrl}" class="nav-link"> 
				<i class="fas fa-dog"></i>
						<p>預約管理</p>
				</a>
				</li>
				
				<li class="nav-item">
				<a href="<%= request.getContextPath()%>/logout/logout.do" class="nav-link">
				<i class="fas fa-sign-out-alt"></i>
						<p>登出</p>
				</a></li>


			</ul>
		</nav>
		<!-- /.sidebar-menu -->
	</div>
	<!-- /.sidebar -->
</aside>

