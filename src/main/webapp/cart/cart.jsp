<%-- <%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="UTF-8"%> --%>

<%@ page import="java.math.BigDecimal" %>

<%@page import="com.petlife.user.entity.User"%>

<%@page import="com.petlife.mall.entity.Comm"%>
<%@page import="com.petlife.mall.entity.Cart"%>
<%@page import="com.petlife.mall.entity.Buylist"%>

<%@page import="com.petlife.mall.service.impl.CommServiceImpl"%>
<%@page import="com.petlife.mall.service.impl.CartServiceImpl"%>
<%@page import="com.petlife.mall.service.impl.BuylistServiceImpl"%>

<%@page import="com.petlife.mall.service.CommService"%>
<%@page import="com.petlife.mall.service.CartService"%>
<%@page import="com.petlife.mall.service.BuylistService"%>



<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<!-- 執行常見web任務(e.g. for迴圈), 前綴詞=c -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 處理文本,日期等等, 前綴詞=fmt -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>

<!-- ================= -->
<!-- 以下偵測使用者版本 -->

<%
// User user = (User) session.getAttribute("user");
// CartService cartSvc = new CartServiceImpl();

// List<Cart> list = cartSvc.getCartsByUser(user);
// pageContext.setAttribute("list", list);
%>

<!-- 不偵測使用者版本 -->
<%
CartService cartSvc = new CartServiceImpl();

List<Cart> list = cartSvc.getAll();
pageContext.setAttribute("list", list);
%>
<!-- ================= -->
<!DOCTYPE html>
<html lang="zh-TW">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<title>寵愛生活的購物車</title>
	<style>
	.product_count_form_two {
		padding-top: 0px !important;
	}
	</style>

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
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
</head>


<body class="hold-transition sidebar-mini layout-fixed">
<%
BigDecimal totalAmount = BigDecimal.ZERO;

//加總所以購物車中的價錢(totalAmount)
for(Cart item : list) {
    BigDecimal price = item.getComm().getCommOnsalePrice();
    int quantity = item.getPurchasingAmount();
    totalAmount = totalAmount.add(price.multiply(new BigDecimal(quantity)));
}

// coupon卷的折扣價錢(couponValue = 100)
BigDecimal couponValue = new BigDecimal("100");;

// Grand Total (totalAmount - couponValue = grandTotal)
BigDecimal grandTotal = totalAmount.subtract(couponValue);

pageContext.setAttribute("totalAmount", totalAmount);
%>
	<div id="preloader">
		<div id="status">
			<img src="../assets/img/loader.gif" alt="img">
		</div>
	</div>
	<!-- header -->
	<div class="headerPage"></div>
		<section id="cart_main_area" class="section_padding">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="cart_groomers_area_wrapper">
                        <div class="cart_tabel_area table-responsive">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th>Image</th>
                                        <th>商品名稱</th>
                                        <th>單價</th>
                                        <th>欲購買數量</th>
                                        <th>總價</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%@ include file="page1.file" %>
                                	<c:forEach var="cart" items="${list}" begin="<%=pageIndex%>"
										end="<%=pageIndex+rowsPerPage-1%>">
                                    <tr>
                                    	
                                        <td><img src="../assets/img/shop/cart-1.png" alt="img"></td>
                                        <td>${cart.comm.commName}</td>
                                        
                                        <td>NTD. ${cart.comm.commOnsalePrice}</td>
                                        <td>${cart.purchasingAmount}</td>
<!--                                         <td> -->
<!--                                             <form action="#!" class="product_count_form_two"> -->
<!--                                                 <div class="product_count_one"> -->
<!--                                                     <div class="plus-minus-input"> -->
<!--                                                         <div class="input-group-button"> -->
<!--                                                             <button type="button" class="button" data-quantity="minus" -->
<!--                                                                 data-field="quantity"> -->
<!--                                                                 <i class="fa fa-minus" aria-hidden="true"></i> -->
<!--                                                             </button> -->
<!--                                                         </div> -->
<!--                                                         <input class="form-control" type="number" name="quantity" -->
<%--                                                             value="${cart.purchasingAmount }"> --%>
<!--                                                         <div class="input-group-button"> -->
<!--                                                             <button type="button" class="button" data-quantity="plus" -->
<!--                                                                 data-field="quantity"> -->
<!--                                                                 <i class="fa fa-plus" aria-hidden="true"></i> -->
<!--                                                             </button> -->
<!--                                                         </div> -->
<!--                                                     </div> -->
<!--                                                 </div> -->
<!--                                             </form> -->
<!--                                         </td> -->
                                        <td>${cart.comm.commOnsalePrice * cart.purchasingAmount}</td>
										<!-- 之後要做成button來使用delete動作 -->
                                        <td>${cart.cartId}
                                        	<form action="<%=request.getContextPath()%>/cart/cart.do" method="post">
    											<input type="hidden" name="action" value="delete_cart_item">
    											<input type="hidden" name="cartId" value="${cart.cartId}">
    											<button type="submit" class="btn btn-danger">刪除</button>
											</form>
										</td>
                                    </tr>
                                    </c:forEach>
                                    <%@ include file="page2.file" %>
                                </tbody>
                            </table>
							
                            <div class="cart_tabel_bottom">
                            	<!-- 回商城的btn -->
                                <div class="cart_submit_btn">
                                    <a href="./listAllCommForUser.jsp" class="btn btn_theme btn_md">Continue shopping</a>
                                </div>
                                <div class="cart_right_side">
                                	<!-- coupon btn -->
                                    <form action="#!" id="subscribe_form1">
                                        <div class="input-group">
                                            <input type="text" class="form-control" placeholder="Your coupon code"
                                                required>
                                            <button class="btn btn_theme btn_md" type="submit">Enter coupon</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="cart_bottom_area">
                <div class="row">
                    <div class="col-lg-4">
                        <div class="cart_area_total_wrapper">
<!--                             <div class="cart_total_item bg_cart_item"> -->
<!--                                 <h5> <span>$1,200</span></h5> -->
<!--                             </div> -->
<!--                             <div class="cart_total_item"> -->
<!--                                 <h5>Red dog bed <span>$1,800</span></h5> -->
<!--                             </div> -->
                            <div class="cart_total_area">
                                <h4>Total amount: <span>$<%= totalAmount %></span></h4>
                                <h4 class="cart_voucher_amount">Coupon: <span><%= couponValue %></span></h4>
                            </div>
                            <div class="cart_total_area bg_cart_item">
                                <h4>Grand total: <span>$<%= grandTotal %> </span></h4>
                            </div>
                        </div>
                        <div class="cart_proce_btn">
                            <a href="checkout.html" class="btn btn_theme btn_md">為你家的寵物快樂下單</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

	<div class="footerPage"></div>

	<script src="../assets/js/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src="../assets/js/user_profile.js"></script>
	<script>
// 	$(document).ready(function() {
//         $('.delete-cart-item').click(function() {
//             var cartId = $(this).data('cartid'); // 取得 cart ID
//             $.ajax({
//                 url: '/path/to/delete/cart', // 替換為後端處理刪除請求的 URL
//                 type: 'POST',
//                 data: { cartId: cartId },
//                 success: function(response) {
//                     // 刪除成功，更新頁面
//                     if(response.status == 'success') {
//                         $('#cart-item-' + cartId).remove(); // 假設每個 cart 項目有一個唯一的 ID
//                     }
//                 },
//                 error: function(xhr, status, error) {
//                     // 處理錯誤
//                     console.log('刪除失敗: ', error);
//                 }
//             });
//         });
//     });
	</script>
	
</body>

</html>