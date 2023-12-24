<%@page import="com.petlife.user.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="zh-TW">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>訂單管理</title>
<!-- Bootstrap css -->
<link rel="stylesheet" href="../assets/css/bootstrap.min.css">
<!-- animate css -->
<link rel="stylesheet" href="../assets/css/animate.min.css">
<!-- Fontawesome css -->
<link rel="stylesheet" href="../assets/css/fontawesome.all.min.css">
<!-- owl.carousel css -->
<link rel="stylesheet" href="../assets/css/owl.carousel.min.css">
<!-- owl.theme.default css -->
<link rel="stylesheet" href="../assets/css/owl.theme.default.min.css">
<!-- Magnific popup css -->
<link rel="stylesheet" href="../assets/css/magnific-popup.min.css">
<!-- Nouislider css -->
<link rel="stylesheet" href="../assets/css/nouislider.css">
<!-- navber css -->
<link rel="stylesheet" href="../assets/css/navber.css">
<!-- meanmenu css -->
<link rel="stylesheet" href="../assets/css/meanmenu.css">
<!-- Style css -->
<link rel="stylesheet" href="../assets/css/style.css">
<!-- Responsive css -->
<link rel="stylesheet" href="../assets/css/responsive.css">
<!-- Favicon -->
<link rel="icon" type="image/png" href="../assets/img/favicon.png">
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.css" />

<link rel="stylesheet" href="../assets/css/order_management.css">
</head>

<body>
	<div id="preloader">
		<div id="status">
			<img src="../assets/img/loader.gif" alt="img">
		</div>
	</div>

	<%@include file="../components/header.jsp"%>

	<!--Our Shop-->
	<section id="our_shop_main" class="section_padding">
		<div class="container">
			<div class="row">
				<!-- side bar -->
				<div class="col-12 col-md-3">
					<div class="sidebar_boxed_wrapper">
						<div class="sidebar_common_heading">
							<h3 id="sidebar_title">
								<a
									href="<%=request.getContextPath()%>/member_center/user_profile.jsp">會員中心</a>
							</h3>
						</div>

						<!-- accordion -->
						<div class="accordion" id="accordionExample">
							<!-- 修改會員資料 -->
							<div class="accordion-item">
								<h2 class="accordion-header" id="headingOne">
									<div class="row sidebar_select" id="user_profile">
										<a
											href="<%=request.getContextPath()%>/member_center/change_user_profile.jsp">修改會員資料</a>
									</div>
								</h2>
							</div>

							<!-- 訂單管理 -->
							<div class="accordion-item">
								<h2 class="accordion-header" id="headingTwo">
									<div class="row sidebar_select" id="orderList_manage">
										<a href="<%=request.getContextPath()%>/buylist/buylist.do?action=getBuyListByMemberId&memberId=<%=user.getUserId()%>">訂單管理</a>
									</div>
							</div>

							<!-- 預約管理 -->
							<div class="accordion-item">
								<h2 class="accordion-header" id="headingThree">
									<div class="row sidebar_select" id="order_manage">
                                        <a href="<%=request.getContextPath()%>/shelter/reservation.do?action=getByUserId&memberId=<%=user.getUserId()%>">預約管理</a>
									</div>
								</h2>
							</div>

							<!-- 文章管理 -->
							<div class="accordion-item">
								<h2 class="accordion-header" id="headingFour">
									<div class="row sidebar_select" id="article_manage">
										<a
											href="<%=request.getContextPath()%>/art/art.do?action=getAllArticles&userId=<%=user.getUserId()%>">文章管理</a>
									</div>
								</h2>
							</div>
						</div>
					</div>
				</div>

				<div class="col-12 col-md-9">
					<div class="main_area_wrapper">
						<div class="main_area_heading">
							<h3 class="main_header border rounded bg-warning"
								id="mamber_center_title">訂單管理</h3>
						</div>

						<div class="main_content_wrapper">
							<div class="row">
								<!-- 一個 class="col-lg-4 col-md-6 col-sm-12 col-12" 就是一個comm_item-->
								<div>
									<div class="main_item border rounded bg-light">

										<div class="container" id="main_content">
											<table id="myTable" class="display">
												<thead>
													<tr>
														<th>訂單編號</th>
														<th>賣場名稱</th>
														<th>訂單狀態</th>
														<th>訂單金額</th>
														<th>付款方式</th>
														<th>訂單成立日期</th>
														<th>操作</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="buyList" items="${getAllBuylist}">
														<td>${buyList.buylistId}</td>
														<td>${buyList.seller.sellerNickname}</td>
														<td>${buyList.buylistState.buylistStateName}</td>
														<td>${buyList.buylistAmount}</td>
														<td>付款方式</td>
														<td><fmt:formatDate value="${buyList.buylistDate}"
																pattern="yyyy-MM-dd"></fmt:formatDate></td>
														<td>
															<button class="btn-sm btn-primary btn_check"
																data-bs-toggle="modal" data-bs-target="#check_order" value="${buyList.buylistId}">查看訂單</button>
															<c:choose>

																<c:when
																	test="${buyList.buylistState.buylistStateName eq '待付款' || buyList.buylistState.buylistStateName eq '待出貨'}">
																	<button class="btn-sm btn-danger btn_cancel"
																		data-bs-toggle="modal" data-bs-target="#cancel_order" value="${buyList.buylistId}">取消</button>
																</c:when>

																<c:when
																	test="${buyList.buylistState.buylistStateName eq '訂單已完成'}">
																	<button class="btn-sm btn-warning btn_rate"
																		data-bs-toggle="modal" data-bs-target="#rate_order">評價</button>
																</c:when>
															</c:choose>
														</td>
													</c:forEach>
												</tbody>
											</table>
										</div>

										<%-- 查看訂單 --%>
										<div class="modal fade" id="check_order" tabindex="-1"
											aria-labelledby="checkModalLabel" aria-hidden="true">
											<div class="modal-dialog modal-dialog-centered">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title" id="checkModalLabel">查看訂單</h5>
														<button type="button" class="btn-close"
															data-bs-dismiss="modal" aria-label="Close"></button>
													</div>
													<div class="modal-body">
														<table class="col-md-12 buylistdetailList">
															<thead class="order_header">
																<tr>
																	<th>商品編號</th>
																	<th>商品名稱</th>
																	<th>購買數量</th>
																	<th>品項金額</th>
																<tr>
															</thead>
															<tbody class="order_items">
															</tbody>
														</table>
													</div>
													<div class="row modal-footer">
														<div class="col"></div>
														<button type="button" class="col-auto btn btn-secondary"
															data-bs-dismiss="modal">關閉</button>
														<div class="col"></div>
													</div>
												</div>
											</div>
										</div>

										<!-- 取消訂單 -->
										<form action="<%=request.getContextPath()%>/buylist/buylist.do" method="post" id="cancel_orderForm">
											<div class="modal fade" id="cancel_order" tabindex="-1"
												aria-labelledby="cancelModalLabel" aria-hidden="true">
												<div class="modal-dialog modal-dialog-centered">
													<div class="modal-content">
														<div class="modal-header">
															<h5 class="modal-title" id="cancelModalLabel">取消訂單</h5>
															<button type="button" class="btn-close"
																data-bs-dismiss="modal" aria-label="Close"></button>
														</div>
														<div class="row modal-body">
															<!-- 訂單編號 -->
															<div class="col-md-12 form-group mb-3">
																<label for="order_number" class="form-label">訂單編號</label>
																<input type="text" class="form-control"
																	id="order_number" name="order_number"
																	placeholder="訂單編號在這邊..." readonly>
															</div>
															<!-- 賣場名稱 -->
															<div class="col-md-12 form-group mb-3">
																<label for="shopname" class="form-label">賣場名稱</label> <input
																	type="text" class="form-control col-md-12" id="shopname"
																	name="shopname" placeholder="賣場名稱在這邊..." readonly>
															</div>
															<!-- 訂單金額 -->
															<div class="col-md-12 form-group mb-3">
																<label for="order_amount" class="form-label">訂單金額</label>
																<input type="text" class="form-control"
																	id="order_amount" name="order_amount"
																	placeholder="訂單金額在這邊..." readonly>
															</div>
															<!-- 取消原因 -->
															<span id="verify_cancelReason"></span>
															<div class="row" id="cancel_reason">
																<select class="form-select" id="cancelReasonResult"
																	name="cancelReasonResult"
																	aria-label="Default select example">
																	<option selected value=" ">請選擇取消訂單原因</option>
																	<option value="1">商品購買錯誤，想要重新選擇。</option>
																	<option value="2">已經不需要這些商品了，想要取消整筆訂單。</option>
																	<option value="3">有看到其他更便宜的商品，決定換購，想要取消整筆訂單。</option>
																	<option value="4">其他</option>
																</select>
															</div>
															<div class="row" id="cancelReasonTextarea"
																style="display: none;">
																<textarea id="cancelReason" name="cancelReason" rows="4"
																	cols="50" placeholder="請輸入原因..."></textarea>
															</div>
														</div>
														<div class="row modal-footer">
															<div class="col"></div>
															<button type="submit"
																class="col-auto btn btn-primary btn_comfirm">送出</button>
															<button type="button" class="col-auto btn btn-secondary"
																data-bs-dismiss="modal">取消</button>
															<div class="col"></div>
															<input type="hidden" name="action" value="cancelBuylist">
															<input type="hidden" name="memberId" value=<%=user.getUserId()%>>
															<input type="hidden" name="buylistId" value="">
															<input type="hidden" name="sellerAcct" value="">
														</div>
													</div>
												</div>
											</div>
										</form>

										<!-- 評價 -->
										<form action="" method="get">
											<div class="modal fade" id="rate_order" tabindex="-1"
												aria-labelledby="rateModalLabel" aria-hidden="true">
												<div
													class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
													<div class="modal-content">
														<div class="modal-header">
															<h5 class="modal-title" id="rateModalLabel">評價訂單</h5>
															<button type="button" class="btn-close"
																data-bs-dismiss="modal" aria-label="Close"></button>
														</div>
														<div class="row modal-body delete_box">
															<div class="col rounded star_block" id="delete_content">
																評價此訂單<br> <span class="star" data-star="1"><i
																	class="fas fa-star"></i></span> <span class="star"
																	data-star="2"><i class="fas fa-star"></i></span> <span
																	class="star" data-star="3"><i
																	class="fas fa-star"></i></span> <span class="star"
																	data-star="4"><i class="fas fa-star"></i></span> <span
																	class="star" data-star="5"><i
																	class="fas fa-star"></i></span>
															</div>
															<div class="col-auto">
																<label class="col-md-12" for="rateReason">評論：</label> <br>
																<textarea class="col-md-12" id="rateReason"
																	name="rateReason" rows="4" cols="80"
																	placeholder="請輸入原因..."></textarea>
															</div>
														</div>

														<div class="row modal-footer">
															<div class="col"></div>
															<button type="submit" class="col-auto btn btn-danger">評價</button>
															<button type="button" class="col-auto btn btn-secondary"
																data-bs-dismiss="modal">取消</button>
															<div class="col"></div>
														</div>
													</div>
												</div>
											</div>
										</form>

									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</section>

	<%@include file="../components/footer.jsp"%>

	<script src="../assets/js/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.js"></script>
	<script src="../assets/js/order_management.js"></script>
</body>

</html>