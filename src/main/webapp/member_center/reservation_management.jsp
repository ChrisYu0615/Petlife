<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.petlife.user.entity.User"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
User user = (User) session.getAttribute("user");
%>

<!DOCTYPE html>
<html lang="zh-TW">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>預約管理</title>
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
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.css" />

    <link rel="stylesheet" href="../assets/css/reserve_management.css">
</head>

<body>
    <div id="preloader">
        <div id="status">
            <img src="../assets/img/loader.gif" alt="img">
        </div>
    </div>

    <div class="headerPage"></div>

    <!--Our Shop-->
    <section id="our_shop_main" class="section_padding">
        <div class="container">
            <div class="row">
                <!-- side bar -->
                <div class="col-12 col-md-3">
                    <div class="sidebar_boxed_wrapper">
                        <div class="sidebar_common_heading">
                            <h3 id="sidebar_title">
                                <a href="<%=request.getContextPath()%>/member_center/user_profile.jsp">會員中心</a>
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
                            <h3 class="main_header border rounded bg-warning" id="mamber_center_title">預約管理</h3>
                        </div>

                        <div class="main_content_wrapper">
                            <div class="row">
                                <!-- 一個 class="col-lg-4 col-md-6 col-sm-12 col-12" 就是一個comm_item-->
                                <div>
                                    <div class="main_item border rounded bg-light">

                                        <div class="container" id="main_content">
                                            <table id="myTable" class="compact hover row-border	stripe">
                                                <thead>
                                                    <tr>
                                                        <!-- 欄位標題 -->
                                                        <th>預約狀態</th>
                                                        <th>預約編號</th>
                                                        <th>收容所名稱</th>
                                                        <th>預約時間</th>
                                                        <th>預約地點</th>
                                                        <th>操作</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="res" items="${getAllReservations}">
                                                        <tr>
                                                            <td>
                                                                <c:choose>
                                                                    <c:when test="${res.resType.resCon eq '已取消' || res.resType.resCon eq '未成功領養'}">
                                                                        <button class="btn-sm btn-secondary" disabled>${res.resType.resCon}</button>
                                                                    </c:when>
                                                                    <c:when test="${res.resType.resCon eq '待確認' || res.resType.resCon eq '待審核'}">
                                                                        <button class="btn-sm btn-warning" disabled>${res.resType.resCon}</button>
                                                                    </c:when>     
                                                                    <c:when test="${res.resType.resCon eq '已確認' || res.resType.resCon eq '成功領養'}">
                                                                        <button class="btn-sm btn-primary" disabled>${res.resType.resCon}</button>
                                                                    </c:when>                                                                                                                                   
                                                                </c:choose>
                                                            </td>
                                                            <td>${res.resId}</td>
                                                            <td>${res.shelter.shelterName}</td>
                                                            <td>${res.shelterBooking.shelterBookingDate}&nbsp&nbsp${res.shelterBooking.shelterBookingTime}</td>
                                                            <td>${res.shelter.shelterAddress}</td>
                                                            <td>
                                                                <button class="btn-sm btn-primary btn_checkReservation" data-bs-toggle="modal" data-bs-target="#check_reservation" value="${res.resId}">查看</button>
                                                                <c:if test="${res.resType.resTypeId < 3}">
                                                                    <button class="btn-sm btn-danger btn_cancelReservation" data-bs-toggle="modal" data-bs-target="#cancel_reservation" value="${res.resId}">取消</button> 
                                                                </c:if>                                                              
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>

                                        <!-- 取消預約 -->
                                        <form action="<%=request.getContextPath()%>/shelter/reservation.do" method="post">
                                            <div class="modal fade" id="cancel_reservation" tabindex="-1"
                                                aria-labelledby="cancelModalLabel" aria-hidden="true">
                                                <div class="modal-dialog modal-dialog-centered">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="cancelModalLabel">取消預約</h5>
                                                            <button type="button" class="btn-close"
                                                                data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <div class="row modal-body delete_box">
                                                            <div class="col rounded" id="delete_content">
                                                                是否要取消該預約?
                                                            </div>
                                                        </div>
                                                        <div class="row modal-footer">
                                                            <div class="col"></div>
                                                            <button type="submit" class="col-auto btn btn-danger btn_confirm" >確認</button>
                                                            <button type="button" class="col-auto btn btn-secondary"
                                                                data-bs-dismiss="modal">返回</button>
                                                            <div class="col"></div>
                                                            <input type="hidden" name="resId">
                                                            <input type="hidden" name="memberId" value="<%=user.getUserId()%>">
                                                            <input type="hidden" name="action" value="cancelReservation">
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


    <script src="../assets/js/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.js"></script>
    <script src="../assets/js/reserve_management.js"></script>
</body>

</html>