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
User user = (User) session.getAttribute("user");
CartService cartSvc = new CartServiceImpl();

List<Cart> list = cartSvc.getCartsByUser(user);
pageContext.setAttribute("list", list);
%>

<!-- 不偵測使用者版本 -->
<%
// CartService cartSvc = new CartServiceImpl();

// List<Cart> list = cartSvc.getAll();
// pageContext.setAttribute("list", list);
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
<!-- 		購買勾選商品的傳送url -->
		<form action="<%=request.getContextPath()%>/buylist_for_user/buylist_for_user.do" method="post">
		
		<input type="hidden" name="action" value="insert">
		
		<section id="cart_main_area" class="section_padding">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="cart_groomers_area_wrapper">
                        <div class="cart_tabel_area table-responsive">
                        	
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th>欲結帳商品</th>
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
										end="<%=pageIndex+rowsPerPage-1%>" varStatus="status">
                                    <tr id="cartItem-${cart.cartId}">
                                    	<td>  
                                    		<input type="checkbox" name="cartIds" value="${cart.cartId}">
                                    	</td>
<!--                                         <td><img src="../assets/img/shop/cart-1.png" alt="img"></td> -->
                                        <td>${cart.comm.seller.sellerId}</td>

                                        <td>NTD. ${cart.comm.commOnsalePrice}</td>
                                        <td>${cart.purchasingAmount}</td>
<!--                                         	加減按鈕 -->
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
										
										<!-- DELETE ajax version -->
										<td><button type="button" onclick="deleteCartItem('${cart.cartId}')" class="btn btn-danger">刪除${cart.cartId}</button></td>
                                    </tr>
                                    <input type="hidden" name="purchasingAmount_${cart.cartId}" value="${cart.purchasingAmount}">

                                    </c:forEach>
                                    <%@ include file="page2.file" %>
                                </tbody>
                            </table>
							
                            <div class="cart_tabel_bottom">
                            	<!-- 回商城的btn -->
                                <div class="cart_submit_btn">
                                    <a href="../comm_for_user/listAllCommForUser.jsp" class="btn btn_theme btn_md">Continue shopping</a>
                                </div>
                                <div class="cart_right_side">
                                	<!-- coupon btn -->
<!--                                     <form action="#!" id="subscribe_form1"> -->
<!--                                         <div class="input-group"> -->
<!--                                             <input type="text" class="form-control" placeholder="Your coupon code" -->
<!--                                                 required> -->
<!--                                             <button class="btn btn_theme btn_md" type="submit">Enter coupon</button> -->
<!--                                         </div> -->
<!--                                     </form> -->
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
                            <div class="cart_total_area">
                                <h4>Total amount: <span>$<%= totalAmount %></span></h4>
                                <h4 class="cart_voucher_amount">Coupon: <span><%= couponValue %></span></h4>
                            </div>
                            <div class="cart_total_area bg_cart_item">
                                <h4>Grand total: <span>$<%= grandTotal %> </span></h4>
                            </div>
                        </div>
<!--                         下單功能 -->
						
                        	<div class="cart_proce_btn">
    							<button type="submit" class="btn btn_theme btn_md">為你家的寵物快樂下單</button>
                        	</div>
                    </div>
                </div>
            </div>
        </div>
    </section>
	</form>
<!-- 	footer -->
	<div class="footerPage"></div>

	<script src="../assets/js/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src="../assets/js/user_profile.js"></script>
	<script>
	function deleteCartItem(cartId) {
	    if(confirm('確定要刪除此項目嗎？')) {
	        var xhr = new XMLHttpRequest();
	        xhr.open('POST', '<%=request.getContextPath()%>/cart/cart.do', true);
	        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	        xhr.onload = function () {
	            if (this.status == 200) {
	                // 服務器返回的響應是 JSON 格式
	                var response = JSON.parse(this.responseText);
	                if(response.status === 'success') {
	                    // 移除已刪除的購物車項目
	                    var elementToRemove = document.getElementById('cartItem-' + cartId);
	                    if(elementToRemove) {
	                        elementToRemove.parentNode.removeChild(elementToRemove);
	                    }
	                } else {
	                    // 處理錯誤情況
	                    alert(response.message);
	                }
	            } else {
	                // 處理非200的響應
	                console.error('錯誤發生: ' + this.status);
	            }
	        };
	        xhr.send('action=delete_cart_item&cartId=' + cartId);
	    }
	}

</script>
</body>
</html>