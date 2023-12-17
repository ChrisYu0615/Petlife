<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.petlife.admin.entity.Admin"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
Admin admin = (Admin) session.getAttribute("account");
%>

<!DOCTYPE html>
<html lang="zh-TW">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>管理員後臺系統</title>

<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<!-- Bootstrap css -->
<link rel="stylesheet" href="../assets/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="../plugins/fontawesome-free/css/all.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="../dist/css/adminlte.min.css">
<!-- overlayScrollbars -->
<link rel="stylesheet"
	href="../plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
<!-- Favicon -->
<link rel="icon" type="image/png" href="../assets/img/favicon.png">
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.css" />
<!-- my_css -->
<link rel="stylesheet" href="../dist/css/admin_article_management.css">
</head>

<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">

		<!-- 上方Navbar -->
		<nav
			class="main-header navbar navbar-expand navbar-white navbar-light">
			<!-- 上方左邊Left navbar links -->
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" data-widget="pushmenu"
					href="#" role="button"><i class="fas fa-bars"></i></a></li>
				<li class="nav-item d-none d-sm-inline-block"></li>
			</ul>
		</nav>
		<!-- /.navbar -->

		<!-- Main Sidebar Container -->
		<aside class="main-sidebar sidebar-dark-primary elevation-4">
			<!-- Brand Logo -->
			<a href="test.html" class="brand-link"> <img
				src="../dist/img/main_logo.png" alt="AdminLTE Logo"
				class="brand-image img-corners elevation-3 logo" style="opacity: .8">
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
						<span class="d-block" id="admin_name"><%=admin.getAdminNickname()%>管理員，你好</span>
					</div>
				</div>

				<!-- Sidebar Menu -->
				<nav class="mt-2">
					<ul class="nav nav-pills nav-sidebar flex-column"
						data-widget="treeview" role="menu" data-accordion="false">
						<li class="nav-item"><a href="<%=request.getContextPath()%>/user/user.do?action=getAll" class="nav-link"> <i
								class="fas fa-solid fa-users"></i>
								<p>會員管理</p>
						</a></li>
						<li class="nav-item"><a href="<%=request.getContextPath()%>/art/art.do?action=getAllArticles" class="nav-link"> <!-- <i class="nav-icon fas fa-copy"></i> -->
								<i class="fas fa-solid fa-newspaper"></i>
								<p>文章管理</p>
						</a></li>

						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="fas fa-solid fa-store"></i>
								<p>商品管理</p>
						</a></li>


						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="fas fa-solid fa-percent"></i>
								<p>優惠碼管理</p>
						</a></li>

						<li class="nav-item"><a href="<%=request.getContextPath()%>/advertisement/advertisement.do?action=getAllAdvertisements" class="nav-link"> <i
								class="fas fa-ad"></i>
								<p>廣告管理</p>
						</a></li>

						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="fas fa-sign-out-alt"></i>
								<p>登出</p>
						</a></li>
					</ul>
				</nav>
				<!-- /.sidebar-menu -->
			</div>
			<!-- /.sidebar -->
		</aside>


		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1>文章管理</h1>
						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<li class="breadcrumb-item active">文章管理</li>
							</ol>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
			</section>

			<!-- Main content(複合查詢區塊) -->
			<section class="content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-12">
							<div class="card">
								<div class="card-header" id="select_card_header">
									<h5>搜尋條件</h5>
								</div>
								<!-- /.card-header -->
								<div class="card-body">
									<form action="<%=request.getContextPath()%>/art/art.do" method="post" id="select_articleForm">
										<%-- 如果全部選擇結果都沒輸入就按查詢時，會顯示此錯誤結果 --%>
										<span id="verify_select_result"></span>

										<div class="form-group mb-3 row">
											<div class="col-auto" id="div_article_name">
												<label for="article_name" class="form-label"
													id="label_article_name">文章標題：</label>
											</div>
											<div class="col-3">
												<input type="text" class="form-control"
													id="select_article_name" name="article_name"
													placeholder="請輸入文章標題">
											</div>
										</div>

										<div class="form-group mb-3 row">
											<div class="col-auto" id="div_article_category">
												<label for="select_article_category" class="form-label"
													id="label_article_category">文章分類：</label>
											</div>
											<div class="col-3">
												<select class="form-select col-12 border rounded"
													id="select_article_category" name="article_category">
													<option selected>選擇文章分類</option>
													<option value="1">狗狗</option>
													<option value="2">貓咪</option>
													<option value="3">閒聊</option>
													<option value="4">特殊</option>
												</select>
											</div>
										</div>

										<div class="form-group mb-3 row">
											<div class="col-auto" id="div_article_category">
												<label for="" class="form-label" id="label_article_category">文章區間：</label>
											</div>
											<div class="col-3">
												<input type="date" class="form-control"
													id="select_article_startdate" name="article_startdate"
													placeholder="請選擇開始區間">
											</div>
											<div class="col-auto">
												<h5>~</h5>
											</div>
											<div class="col-3">
												<input type="date" class="form-control"
													id="select_article_enddate" name="article_enddate"
													placeholder="請選擇結束區間">
											</div>
											<div class="col-auto">
												<%-- 判斷文章日期選擇輸入錯誤問題 --%>
												<span id="verify_article_date"></span>
											</div>
										</div>

										<div class="row card-footer">
											<div class="col"></div>
											<div class="col-auto">
												<button type="submit" class="btn btn-primary"
													id="btn_regist">送出查詢</button>
												<input type="hidden" name="action" value="CompositeArticleQuery">
											</div>
											<div class="col"></div>
										</div>
									</form>
								</div>
								<!-- /.card-body -->
							</div>
							<!-- /.card -->


							<div class="card">
								<div class="card-header">
									<ul class="list-group list-group-horizontal-sm">
										<li class="list-group-item"><a href="<%=request.getContextPath()%>/art/art.do?action=getAllArticles">全部文章列表</a></li>
										<li class="list-group-item"><a href="<%=request.getContextPath()%>/reportForum/reportForum.do?action=getAllReports&condition=unReply">待處理檢舉</a></li>
										<li class="list-group-item"><a href="<%=request.getContextPath()%>/reportForum/reportForum.do?action=getAllReports&condition=replied">已處理檢舉</a></li>
									</ul>
									<!-- <h3 class="card-title">DataTable with minimal features & hover style</h3> -->
								</div>
								<!-- /.card-header -->
								<div class="card-body">
									<table id="myTable" class="display">
										<thead>
											<tr>
												<th>文章編號</th>
												<th>發文帳號</th>
												<th>文章標題</th>
												<th>分類</th>
												<th>文章狀態</th>
												<th>發文時間</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="article" items="${getAllArticles}">
												<tr>
													<td>${article.articleId}</td>
													<td>${article.user.userId}</td>
													<td>${article.articleName}</td>
													<td>${article.forum.sortName}</td>
													<td>${article.state? "上架中":"已下架"}</td>
													<td><fmt:formatDate value="${article.updateTime}"
															pattern="yyyy-MM-dd HH:mm:ss" /></td>
													<td>
														<button class="btn-sm btn-primary" value="${article.articleId}">查看</button>
                                                        <c:choose>
                                                            <c:when test="${article.state}">
														        <button class="btn-sm btn-danger btn_remove"
															      data-bs-toggle="modal"
															     data-bs-target="#remove_article"
															     value="${article.articleId}">下架</button>
                                                            </c:when>

                                                            <c:otherwise>
														        <button class="btn-sm btn-warning btn_upload"
															      data-bs-toggle="modal"
															     data-bs-target="#upload_article"
															     value="${article.articleId}">上架</button>
                                                            </c:otherwise>
                                                        </c:choose>

													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>

									<!-- 下架 -->
									<form action="<%=request.getContextPath()%>/art/art.do" method="post">
										<div class="modal fade" id="remove_article" tabindex="-1"
											aria-labelledby="removeModalLabel" aria-hidden="true">
											<div class="modal-dialog modal-dialog-centered">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title" id="removeModalLabel">下架文章</h5>
													</div>
													<div class="row modal-body delete_box">
														<div class="col rounded" id="modify_content">
															是否將該文章變為下架狀態?</div>
													</div>
													<div class="row modal-footer">
														<div class="col"></div>
														<button type="submit" class="col-auto btn btn-danger"
															id="btn_modify">確認</button>
														<button type="button" class="col-auto btn btn-secondary"
															data-bs-dismiss="modal">取消</button>
														<div class="col"></div>
                                                        <input type="hidden" name="articleId" value="">
                                                        <input type="hidden" name="action" value="modifyArticleState">
                                                        <input type="hidden" name="value" value="removeArticle">                                                                                                                
													</div>
												</div>
											</div>
										</div>
									</form>

                                    <!-- 上架 -->
									<form action="<%=request.getContextPath()%>/art/art.do" method="post">
										<div class="modal fade" id="upload_article" tabindex="-1"
											aria-labelledby="uploadModalLabel" aria-hidden="true">
											<div class="modal-dialog modal-dialog-centered">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title" id="uploadModalLabel">上架文章</h5>
													</div>
													<div class="row modal-body delete_box">
														<div class="col rounded" id="modify_content">
															是否將該文章變為上架狀態?</div>
													</div>
													<div class="row modal-footer">
														<div class="col"></div>
														<button type="submit" class="col-auto btn btn-danger"
															id="btn_modify">確認</button>
														<button type="button" class="col-auto btn btn-secondary"
															data-bs-dismiss="modal">取消</button>
														<div class="col"></div>
                                                        <input type="hidden" name="articleId" value="">
                                                        <input type="hidden" name="action" value="modifyArticleState">
                                                        <input type="hidden" name="value" value="uploadArticle">                                                        
													</div>
												</div>
											</div>
										</div>
									</form>
								</div>
								<!-- /.card-body -->
							</div>
							<!-- /.card -->
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<!-- jQuery -->
		<script src="../assets/js/jquery.min.js"></script>

		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
		<script
			src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.js"></script>
		<!-- overlayScrollbars -->
		<script
			src="../plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
		<!-- AdminLTE App -->
		<script src="../dist/js/adminlte.js"></script>
		<script src="../dist/js/article_management.js"></script>
</body>

</html>