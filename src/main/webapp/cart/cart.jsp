<%-- <%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="UTF-8"%> --%>

<%@page import="java.math.BigDecimal" %>

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
<%@ include file="../components/header.jsp" %>

<%
CartService cartSvc = new CartServiceImpl();

List<Cart> list = cartSvc.getCartsByUserAndSortBySeller(user);
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
	<!-- datatables -->
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.css">
</head>


<body class="hold-transition sidebar-mini layout-fixed">
<%
BigDecimal totalAmount = BigDecimal.ZERO;

// 加總所以購物車中的價錢(totalAmount)
for(Cart item : list) { 
    BigDecimal price = item.getComm().getCommOnsalePrice();
    int quantity = item.getPurchasingAmount();
    totalAmount = totalAmount.add(price.multiply(new BigDecimal(quantity)));
}

// coupon卷的折扣價錢(couponValue = 100)
BigDecimal couponValue = new BigDecimal("100");

// Grand Total (totalAmount - couponValue = grandTotal)
BigDecimal grandTotal = totalAmount.subtract(couponValue);

pageContext.setAttribute("totalAmount", totalAmount);
%>
	<div id="preloader">
		<div id="status">
			<img src="../assets/img/loader.gif" alt="img">
		</div>
	</div>
		<!-- 購買勾選商品的傳送url -->
		<form action="<%=request.getContextPath()%>/buylist_for_user/buylist_for_user.do" method="post">
		
		<input type="hidden" name="action" value="insert">
		
		<section id="cart_main_area" class="section_padding">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="cart_groomers_area_wrapper">
                        <div class="cart_tabel_area table-responsive">
                        	
                            <table class="table display" id="myTable">
                                <thead>
                                    <tr>
                                        <th>欲結帳商品</th>
                                        <th>商品名稱</th>
                                        <th>單價</th>
                                        <th>欲購買數量</th>
                                        <th>總價</th>
                                        <th>賣家名稱</th>
                                        <th>刪除商品</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach var="cart" items="${list}"  varStatus="status">
                                    <tr id="cartItem-${cart.cartId}">
										<!-- 計算總共要結帳商品有多少錢 & 前端鎖同時對不同賣家的商品結帳 -->
                                    	<td>
                                    		<input type="checkbox" name="cartIds" value="${cart.cartId}" class="cart-item-checkbox"
                                    		data-price="${cart.comm.commOnsalePrice}" data-quantity="${cart.purchasingAmount}" 
                                    		onchange="handleCheckboxChange(this);resetAndCalculateTotalAmount(0)"
                                    		data-seller-id="${cart.comm.seller.sellerId}" >
                                    	</td>
										<!--  <td><img src="../assets/img/shop/cart-1.png" alt="img"></td> -->
                                        <td><a href="<%=request.getContextPath()%>/comm_for_user/singleCommForUser.do?action=show_comm_with_customer&commId=${cart.comm.commId}&cartId=${cart.cartId}">${cart.comm.commName}</a>
                                        </td>

                                        <td>${cart.comm.commOnsalePrice}</td>
                                        <td>${cart.purchasingAmount}</td>
                           
                                        <td>${cart.comm.commOnsalePrice * cart.purchasingAmount}</td>
										<!-- sellerName -->
										<td>${cart.comm.seller.sellerName}</td>
										<!-- DELETE ajax version -->
										<td>
											<button type="button" onclick="deleteCartItem('${cart.cartId}')" class="btn btn-danger">刪除${cart.cartId}</button>
											<!-- 傳送purchasingAmount -->
	                                    	<input type="hidden" name="purchasingAmount_${cart.cartId}" value="${cart.purchasingAmount}">
	                                    	<!-- 傳送sellerId -->
	                                    	<input type="hidden" name="sellerId" value="${cart.comm.seller.sellerId}">
	                                    	<!-- 傳送couponId -->
											<input type="hidden" id="coupon_id" name="couponId" value="0">
											<input type="hidden" id="grand_amount" name="grandAmount" value="">
										</td>
                                    </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
							
                            <div class="cart_tabel_bottom">
                            	<!-- 回商城的btn -->
                                <div class="cart_submit_btn">
                                    <a href="../comm_for_user/listAllCommForUser.do" class="btn btn_theme btn_md">Continue shopping</a>
                                </div>
                                <div class="cart_right_side">
								<!-- coupon btn -->
                                	<div class="input-group">
                                        <input type="text" class="form-control" placeholder="Your coupon code"
                                         id="coupon_code">
                                        <button class="btn btn_theme btn_md" type="button" onclick="applyCoupon()">Enter coupon</button>
                                    </div>
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
                                <h4 id="total_amount_display">Total amount: <span>$ 0</span></h4>
                                <h4 id="coupon_value" class="cart_voucher_amount">Coupon: <span>$ 0</span></h4>
                            </div>
                            <div class="cart_total_area bg_cart_item">
                                <h4 id="grand_amount_display">Grand total: <span>$ 0</span></h4>
                            </div>
                        </div>
							<!-- 下單功能 -->
                        <div class="cart_proce_btn">
    						<button type="submit" class="btn btn_theme btn_md">為你家的寵物快樂下單</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
	</form>
	<!-- footer -->
	<%@ include file="../components/footer.jsp" %>
	<script src="../assets/js/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src="../assets/js/user_profile.js"></script>
	<!-- dataTables -->
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.js"></script>
	<script>
	// 刪除購物車內的商品
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
	// 全局優惠卷折扣價格
	var currentCouponDiscount = 0;
	
	// Coupon卷使用計算
	function applyCoupon() {
		
    var couponCode = document.getElementById('coupon_code').value;
    var xhr = new XMLHttpRequest();
    xhr.open('POST', '<%=request.getContextPath()%>/coupon/coupon.do', true);
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.onload = function () {
        if (this.status == 200) {
            var response = JSON.parse(this.responseText);
            if(response.status === 'success') {
            	currentCouponDiscount = parseFloat(response.discount);
            	updateCouponDiscount(response.discount)
            	document.getElementById('coupon_id').value = response.couponId;
            } else {
            	currentCouponDiscount = 0;
            	updateCouponDiscount(0);
                alert(response.message);
            }
        } else {
            console.error('Error occurred: ' + this.status);
        }
    };
    xhr.send('action=validateCoupon&couponCode=' + couponCode);
	}
	
	function updateCouponDiscount(discount) {
	    document.getElementById('coupon_value').innerText = 'Coupon Discount: $' + discount;
	    resetAndCalculateTotalAmount(discount);
	}
	
	function resetAndCalculateTotalAmount(discount) {

	    var totalAmount = 0;
	    var checkboxes = document.querySelectorAll('.cart-item-checkbox:checked');
	    checkboxes.forEach(function(checkbox) {
	        var price = parseFloat(checkbox.dataset.price);
	        var quantity = parseInt(checkbox.dataset.quantity, 10);
	        totalAmount += price * quantity;
	    });
	   
	    grandAmount = totalAmount - currentCouponDiscount; 

	    if (grandAmount < 0) {
	    	grandAmount = 0;
	    }

	    document.getElementById('total_amount_display').innerText = 'Total Amount: $' + totalAmount.toFixed(2);
	    document.getElementById('grand_amount_display').innerText = 'Grand Amount: $' + grandAmount.toFixed(2);
	    document.getElementById('grand_amount').value = grandAmount;
	}
	
	function handleCheckboxChange(checkbox) {
	    var selectedSellerId = checkbox.dataset.sellerId;
	    var isChecked = checkbox.checked;
	    var checkboxes = document.querySelectorAll('.cart-item-checkbox');

	    checkboxes.forEach(function(cb) {
	        if (isChecked && cb.dataset.sellerId !== selectedSellerId) {
	            cb.disabled = true;
	        } else {
	            cb.disabled = false;
	        }
	    });
	}
	
	$(document).ready( function () {
	    $('#myTable').DataTable({
	        "pagingType": "simple_numbers", // 使用簡單的分頁
	        "pageLength": 10, // 每頁顯示10條記錄
	        "language": {
	            "lengthMenu": "顯示 _MENU_ 條記錄每頁",
	            "zeroRecords": "沒有找到記錄",
	            "info": "顯示第 _PAGE_ 頁，共 _PAGES_ 頁",
	            "infoEmpty": "沒有可用記錄",
	            "infoFiltered": "(從 _MAX_ 條記錄過濾)",
	            "search": "搜索商品名稱:",
	            "paginate": {
	                "first":      "首頁",
	                "last":       "尾頁",
	                "next":       "下一頁",
	                "previous":   "上一頁"
	            },
	        }
	    });
	});
</script>
</body>
</html>