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
							<h1>文章檢舉</h1>
						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<li class="breadcrumb-item active">文章檢舉</li>
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
												<th>檢舉編號</th>
												<th>檢舉人編號(姓名)</th>
												<th>檢舉文章編號(文章名)</th>
												<th>檢舉類型</th>
												<th>內容</th>
												<th>檢舉時間</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="report" items="${getAllReports}">
												<tr>
													<td>${report.reportForumId}</td>
													<td>${report.user.userId}(${report.user.userName})</td>
													<td>${report.article.articleId}(${report.article.articleName})</td>
													<td>${report.reportType.reportTypeName}</td>
													<td>${report.reportForumReason}</td>
													<td><fmt:formatDate value="${report.reportForumTime}"
															pattern="yyyy-MM-dd HH:mm:ss" /></td>
													<td>
                                                        <c:choose>
                                                            <c:when test="${report.admin==null}">
														        <button class="btn-sm btn-primary btn_reply"
															      data-bs-toggle="modal"
															     data-bs-target="#reply_report"
															     value="${report.reportForumId}">回覆</button>
                                                            </c:when>
                                                            <c:otherwise>
														        <button class="btn-sm btn-secondary btn_check"
															      data-bs-toggle="modal"
															     data-bs-target="#check_reply"
															     value="${report.reportForumId}">查看</button>
                                                            </c:otherwise>
                                                        </c:choose>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>


									<!-- 查看檢舉並回覆(使用ajax撈資料) -->
									<form
										action="<%=request.getContextPath()%>/reportForum/reportForum.do"
										method="post" id="replyReportData_form">
										<div class="modal fade" id="reply_report" tabindex="-1"
											aria-labelledby="replyModalLabel" aria-hidden="true">
											<div
												class="modal-dialog modal-l modal-dialog-scrollable modal-dialog-centered">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title" id="replyModalLabel">查看檢舉資料</h5>
														<button type="button" class="btn-close"
															data-bs-dismiss="modal" aria-label="Close"></button>
													</div>

													<div class="row modal-body">
														<div class="form-group mb-3">
															<label for="unreply_reportId" class="form-label">檢舉編號
															</label> <input type="text" class="form-control"
																id="unreply_reportId" name="reportId"
																placeholder="請輸入檢舉編號" readonly>
														</div>

														<div class="form-group mb-3">
															<label for="unreply_memberId" class="form-label">檢舉人會員編號(姓名)
															</label> <input type="text" class="form-control"
																id="unreply_memberId" name="memberId"
																placeholder="請輸入會員編號(姓名)" readonly>
														</div>

														<div class="form-group mb-3">
															<label for="unreply_articleId" class="form-label">檢舉文章編號(文章名)
															</label> <input type="text" class="form-control"
																id="unreply_articleId" name="articleId"
																placeholder="請輸入文章編號(文章名)" readonly>
														</div>

														<div class="form-group mb-3">
															<label for="unreply_reportType" class="form-label">檢舉類型
															</label> <input type="text" class="form-control" id="unreply_reportType"
																name="reportType" placeholder="請輸入檢舉類型" readonly>
														</div>

														<div class="form-group mb-3">
															<label for="unreply_reportContent" class="form-label">內容 </label>
															<textarea class = "col-12" id="unreply_reportContent" name="reportContent" rows="4"
															cols="50" placeholder="請輸入檢舉原因..." readonly></textarea>
														</div>

														<div class="form-group mb-3">
															<label for="unreply_reportTime" class="form-label">檢舉時間 </label> <input
																type="text" class="form-control" id="unreply_reportTime"
																name="reportTime" placeholder="請輸入檢舉時間" readonly>
														</div>

														<div class="form-group mb-3">
															<label for="unreply_adminId" class="form-label">管理員編號(暱稱) </label> <input
																type="text" class="form-control" id="unreply_adminId"
																name="adminIdwithNickname" value="<%=admin.getAdminId()%>(<%=admin.getAdminNickname()%>)"placeholder="請輸入管理員編號(暱稱)" readonly>
														</div>									

														<div class="form-group mb-3">
															<label for="unreply_adminReply" class="form-label">管理員回覆內容 </label>
															<span id="verify_adminReply"></span>
															<textarea class = "col-12" id="unreply_adminReply" name="adminReply" rows="4"
															cols="50" placeholder="請輸入回覆內容..."></textarea>
														</div>														
													</div>

													<div class="row modal-footer">
														<div class="col"></div>
														<button type="submit" class="col-auto btn btn-danger">確定</button>
														<button type="button" class="col-auto btn btn-secondary"
															data-bs-dismiss="modal">返回</button>
														<input type="hidden" name="adminId" value="<%=admin.getAdminId()%>">
														<input type="hidden" name="reportId">
														<input type="hidden" name="action" value="adminReply">
														<div class="col"></div>
													</div>
												</div>
											</div>
										</div>
									</form>


									<!-- 查看已回覆檢舉(使用ajax撈資料) -->
									<form action="#" method="get">
										<div class="modal fade" id="check_reply" tabindex="-1"
											aria-labelledby="checkReplyModalLabel" aria-hidden="true">
											<div
												class="modal-dialog modal-l modal-dialog-scrollable modal-dialog-centered">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title" id="checkReplyModalLabel">查看檢舉資料</h5>
														<button type="button" class="btn-close"
															data-bs-dismiss="modal" aria-label="Close"></button>
													</div>

													<div class="row modal-body">
														<div class="form-group mb-3">
															<label for="reply_reportId" class="form-label">檢舉編號
															</label> <input type="text" class="form-control"
																id="reply_reportId" name="reportId"
																placeholder="請輸入檢舉編號" readonly>
														</div>

														<div class="form-group mb-3">
															<label for="reply_memberId" class="form-label">檢舉人會員編號(姓名)
															</label> <input type="text" class="form-control"
																id="reply_memberId" name="memberId"
																placeholder="請輸入會員編號(姓名)" readonly>
														</div>

														<div class="form-group mb-3">
															<label for="reply_articleId" class="form-label">檢舉文章編號(文章名)
															</label> <input type="text" class="form-control"
																id="reply_articleId" name="articleId"
																placeholder="請輸入文章編號(文章名)" readonly>
														</div>

														<div class="form-group mb-3">
															<label for="reply_reportType" class="form-label">檢舉類型
															</label> <input type="text" class="form-control" id="reply_reportType"
																name="reportType" placeholder="請輸入檢舉類型" readonly>
														</div>

														<div class="form-group mb-3">
															<label for="reply_reportContent" class="form-label">內容 </label>
															<textarea class = "col-12" id="reply_reportContent" name="reportContent" rows="4"
															cols="50" placeholder="請輸入檢舉原因..." readonly></textarea>
														</div>

														<div class="form-group mb-3">
															<label for="reply_reportTime" class="form-label">檢舉時間 </label> <input
																type="text" class="form-control" id="reply_reportTime"
																name="reportTime" placeholder="請輸入檢舉時間" readonly>
														</div>

														<div class="form-group mb-3">
															<label for="reply_adminId" class="form-label">管理員編號 </label> <input
																type="text" class="form-control" id="reply_adminId"
																name="adminId"  placeholder="請輸入檢舉時間" readonly>
														</div>									

														<div class="form-group mb-3">
															<label for="reply_adminReply" class="form-label">管理員回覆內容 </label>
															<textarea class = "col-12" id="reply_adminReply" name="adminReply" rows="4"
															cols="50" placeholder="請輸入回覆內容..." readonly></textarea>
														</div>		
														
														<div class="form-group mb-3">
															<label for="reply_adminReplyTime" class="form-label">管理員回覆時間 </label>
															<input type="text" class="form-control" id="reply_adminReplyTime" name="adminReplyTime" placeholder="請輸入檢舉時間" readonly>
														</div>																											
													</div>

													<div class="row modal-footer">
														<div class="col"></div>
														<button type="button" class="col-auto btn btn-secondary"
															data-bs-dismiss="modal">返回</button>
														<div class="col"></div>
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
		<script src="../dist/js/admin_forum_report_management.js"></script>
</body>

</html>