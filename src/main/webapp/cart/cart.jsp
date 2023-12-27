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
<!-- ����`��web����(e.g. for�j��), �e���=c -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- �B�z�奻,�������, �e���=fmt -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>

<!-- ================= -->
<!-- �H�U�����ϥΪ̪��� -->

<%
User user = (User) session.getAttribute("user");
CartService cartSvc = new CartServiceImpl();

List<Cart> list = cartSvc.getCartsByUserAndSortBySeller(user);
pageContext.setAttribute("list", list);
%>

<!-- �������ϥΪ̪��� -->
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
	
	<title>�d�R�ͬ����ʪ���</title>
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

// �[�`�ҥH�ʪ�����������(totalAmount)
for(Cart item : list) { 
    BigDecimal price = item.getComm().getCommOnsalePrice();
    int quantity = item.getPurchasingAmount();
    totalAmount = totalAmount.add(price.multiply(new BigDecimal(quantity)));
}

// coupon�����馩����(couponValue = 100)
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
	<!-- header -->
	<div class="headerPage"></div>
<!-- 		�ʶR�Ŀ�ӫ~���ǰeurl -->
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
                                        <th>�����b�ӫ~</th>
                                        <th>�ӫ~�W��</th>
                                        <th>���</th>
                                        <th>���ʶR�ƶq</th>
                                        <th>�`��</th>
                                        <th>��a�W��</th>
                                        <th>�R���ӫ~</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach var="cart" items="${list}"  varStatus="status">
                                    <tr id="cartItem-${cart.cartId}">
										<!-- �p���`�@�n���b�ӫ~���h�ֿ� & �e����P�ɹ藍�P��a���ӫ~���b -->
                                    	<td>
                                    		<input type="checkbox" name="cartIds" value="${cart.cartId}" class="cart-item-checkbox"
                                    		data-price="${cart.comm.commOnsalePrice}" data-quantity="${cart.purchasingAmount}" 
                                    		onchange="calculateTotal();handleCheckboxChange(this)"
                                    		data-seller-id="${cart.comm.seller.sellerId}" >
                                    	</td>
										<!--  <td><img src="../assets/img/shop/cart-1.png" alt="img"></td> -->
                                        <td>${cart.comm.commName}</td>

                                        <td>${cart.comm.commOnsalePrice}</td>
                                        <td>${cart.purchasingAmount}</td>
                           
                                        <td>${cart.comm.commOnsalePrice * cart.purchasingAmount}</td>
										<!-- sellerName -->
										<td>${cart.comm.seller.sellerName}</td>
										<!-- DELETE ajax version -->
										<td><button type="button" onclick="deleteCartItem('${cart.cartId}')" class="btn btn-danger">�R��${cart.cartId}</button></td>
										
                                    </tr>
                                    <input type="hidden" name="purchasingAmount_${cart.cartId}" value="${cart.purchasingAmount}">

                                    </c:forEach>
                                </tbody>
                            </table>
							
                            <div class="cart_tabel_bottom">
                            	<!-- �^�ӫ���btn -->
                                <div class="cart_submit_btn">
                                    <a href="../comm_for_user/listAllCommForUser.jsp" class="btn btn_theme btn_md">Continue shopping</a>
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
                            <div class="cart_total_area">
                                <h4 id="totalAmountDisplay">Total amount: <span>$<%= totalAmount %></span></h4>
                                <h4 class="cart_voucher_amount">Coupon: <span><%= couponValue %></span></h4>
                            </div>
                            <div class="cart_total_area bg_cart_item">
                                <h4>Grand total: <span>$<%= grandTotal %> </span></h4>
                            </div>
                        </div>
							<!-- �U��\�� -->
                        <div class="cart_proce_btn">
    						<button type="submit" class="btn btn_theme btn_md">���A�a���d���ּ֤U��</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
	</form>
	<!-- footer -->
	<div class="footerPage"></div>
	<script src="../assets/js/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src="../assets/js/user_profile.js"></script>
	<!-- dataTables -->
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.js"></script>
	<script>
	function deleteCartItem(cartId) {
	    if(confirm('�T�w�n�R�������ضܡH')) {
	        var xhr = new XMLHttpRequest();
	        xhr.open('POST', '<%=request.getContextPath()%>/cart/cart.do', true);
	        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	        xhr.onload = function () {
	            if (this.status == 200) {
	                // �A�Ⱦ���^���T���O JSON �榡
	                var response = JSON.parse(this.responseText);
	                if(response.status === 'success') {
	                    // �����w�R�����ʪ�������
	                    var elementToRemove = document.getElementById('cartItem-' + cartId);
	                    if(elementToRemove) {
	                        elementToRemove.parentNode.removeChild(elementToRemove);
	                    }
	                } else {
	                    // �B�z���~���p
	                    alert(response.message);
	                }
	            } else {
	                // �B�z�D200���T��
	                console.error('���~�o��: ' + this.status);
	            }
	        };
	        xhr.send('action=delete_cart_item&cartId=' + cartId);
	    }
	}
	
	function calculateTotal() {
	    try {
	        var totalAmount = 0;
	        var checkboxes = document.querySelectorAll('.cart-item-checkbox:checked');
			

	        checkboxes.forEach(function(checkbox) {
	            var price = parseFloat(checkbox.dataset.price);
		        console.log('price ' + price);
		        
	            var quantity = parseInt(checkbox.dataset.quantity, 10);
	            console.log('quantity ' + price);
	            totalAmount += price * quantity;
	        });

	        document.getElementById('totalAmountDisplay').innerText = '�`���B: $' + totalAmount.toFixed(2);
	    } catch (e) {
	        console.error('�p���`���B�ɵo�Ϳ��~:', e);
	    }
	}
	
	$(document).ready( function () {
	    $('#myTable').DataTable({
	        "pagingType": "simple_numbers", // �ϥ�²�檺����
	        "pageLength": 10, // �C�����10���O��
	        "language": {
	            "lengthMenu": "��� _MENU_ ���O���C��",
	            "zeroRecords": "�S�����O��",
	            "info": "��ܲ� _PAGE_ ���A�@ _PAGES_ ��",
	            "infoEmpty": "�S���i�ΰO��",
	            "infoFiltered": "(�q _MAX_ ���O���L�o)",
	            "search": "�j��:",
	            "paginate": {
	                "first":      "����",
	                "last":       "����",
	                "next":       "�U�@��",
	                "previous":   "�W�@��"
	            },
	        }
	    });
	});
	
	function handleCheckboxChange(checkbox) {
	    // ����Q��ܪ���aID
	    var selectedSellerId = checkbox.dataset.sellerId;

	    // �ˬdcheckbox�O�_�Q�襤
	    var isChecked = checkbox.checked;

	    // ����Ҧ���checkbox
	    var checkboxes = document.querySelectorAll('.cart-item-checkbox');

	    // �M���Ҧ�checkbox�A�ھڱ���ҥΩθT��
	    checkboxes.forEach(function(cb) {
	        if (isChecked && cb.dataset.sellerId !== selectedSellerId) {
	            // �p�G�襤�F�@�Ӱӫ~�A�h�T�Ψ�L��a���ӫ~
	            cb.disabled = true;
	        } else {
	            // �p�G�S���ӫ~�Q�襤�A�h�ҥΩҦ��ӫ~
	            cb.disabled = false;
	        }
	    });
	}
</script>
</body>
</html>