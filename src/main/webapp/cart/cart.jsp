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

List<Cart> list = cartSvc.getCartsByUser(user);
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
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.css" />    
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
                        	
                            <table class="table" id="myTable">
                                <thead>
                                    <tr>
                                        <th>�����b�ӫ~</th>
                                        <th>�ӫ~�W��</th>
                                        <th>���</th>
                                        <th>���ʶR�ƶq</th>
                                        <th>�`��</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
<%--                                 <%@ include file="page1.file" %> --%>
                                	<c:forEach var="cart" items="${list}"  varStatus="status">
                                    <tr id="cartItem-${cart.cartId}">
                                    	<td>
                                    		<input type="checkbox" name="cartIds" value="${cart.cartId}" class="cart-item-checkbox" data-price="${cart.comm.commOnsalePrice}" data-quantity="${cart.purchasingAmount}" onchange="calculateTotal()">
                                    	
                                    	</td>
<!--                                         <td><img src="../assets/img/shop/cart-1.png" alt="img"></td> -->
                                        <td>${cart.comm.commName}</td>

                                        <td>NTD. ${cart.comm.commOnsalePrice}</td>
                                        <td>${cart.purchasingAmount}</td>
<!--                                         	�[����s -->
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
										<td><button type="button" onclick="deleteCartItem('${cart.cartId}')" class="btn btn-danger">�R��${cart.cartId}</button></td>
                                    </tr>
                                    <input type="hidden" name="purchasingAmount_${cart.cartId}" value="${cart.purchasingAmount}">

                                    </c:forEach>
<%--                                     <%@ include file="page2.file" %> --%>
                                </tbody>
                            </table>
							
                            <div class="cart_tabel_bottom">
                            	<!-- �^�ӫ���btn -->
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
                                <h4 id="totalAmountDisplay">Total amount: <span>$<%= totalAmount %></span></h4>
                                <h4 class="cart_voucher_amount">Coupon: <span><%= couponValue %></span></h4>
                            </div>
                            <div class="cart_total_area bg_cart_item">
                                <h4>Grand total: <span>$<%= grandTotal %> </span></h4>
                            </div>
                        </div>
<!--                         �U��\�� -->
						
                        	<div class="cart_proce_btn">
    							<button type="submit" class="btn btn_theme btn_md">���A�a���d���ּ֤U��</button>
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
<!-- 	dataTables -->
	<script src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.js"></script>
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
	
	   $('#myTable').DataTable({
	        responsive: true,
	        orderClasses: false,
	        searching: false,
	        // �����
	        "language": {
	            "processing": "�B�z��...",
	            "loadingRecords": "���J��...",
	            "paginate": {
	                "first": "�Ĥ@��",
	                "previous": "�W�@��",
	                "next": "�U�@��",
	                "last": "�̫�@��"
	            },
	            "emptyTable": "�ثe�S�����",
	            "datetime": {
	                "previous": "�W�@��",
	                "next": "�U�@��",
	                "hours": "��",
	                "minutes": "��",
	                "seconds": "��",
	                "amPm": [
	                    "�W��",
	                    "�U��"
	                ],
	                "unknown": "����",
	                "weekdays": [
	                    "�g��",
	                    "�g�@",
	                    "�g�G",
	                    "�g�T",
	                    "�g�|",
	                    "�g��",
	                    "�g��"
	                ],
	                "months": [
	                    "�@��",
	                    "�G��",
	                    "�T��",
	                    "�|��",
	                    "����",
	                    "����",
	                    "�C��",
	                    "�K��",
	                    "�E��",
	                    "�Q��",
	                    "�Q�@��",
	                    "�Q�G��"
	                ]
	            },
	            "searchBuilder": {
	                "add": "�s�W����",
	                "condition": "����",
	                "deleteTitle": "�R���L�o����",
	                "button": {
	                    "_": "�ƦX�d�� (%d)",
	                    "0": "�ƦX�d��"
	                },
	                "clearAll": "�M��",
	                "conditions": {
	                    "array": {
	                        "contains": "�t��",
	                        "equals": "����",
	                        "empty": "�ŭ�",
	                        "not": "������",
	                        "notEmpty": "�D�ŭ�",
	                        "without": "���t"
	                    },
	                    "date": {
	                        "after": "�j��",
	                        "before": "�p��",
	                        "between": "�b�䤤",
	                        "empty": "����",
	                        "equals": "����",
	                        "not": "����",
	                        "notBetween": "���b�䤤",
	                        "notEmpty": "������"
	                    },
	                    "number": {
	                        "between": "�b�䤤",
	                        "empty": "����",
	                        "equals": "����",
	                        "gt": "�j��",
	                        "gte": "�j�󵥩�",
	                        "lt": "�p��",
	                        "lte": "�p�󵥩�",
	                        "not": "����",
	                        "notBetween": "���b�䤤",
	                        "notEmpty": "������"
	                    },
	                    "string": {
	                        "contains": "�t��",
	                        "empty": "����",
	                        "endsWith": "�r����",
	                        "equals": "����",
	                        "not": "����",
	                        "notEmpty": "������",
	                        "startsWith": "�r����",
	                        "notContains": "���t",
	                        "notStartsWith": "�}�Y���O",
	                        "notEndsWith": "�������O"
	                    }
	                },
	                "data": "���",
	                "leftTitle": "�s�ձ���",
	                "logicAnd": "�B",
	                "logicOr": "��",
	                "rightTitle": "�����s��",
	                "title": {
	                    "_": "�ƦX�d�� (%d)",
	                    "0": "�ƦX�d��"
	                },
	                "value": "���e"
	            },
	            "editor": {
	                "close": "����",
	                "create": {
	                    "button": "�s�W",
	                    "title": "�s�W���",
	                    "submit": "�e�X�s�W"
	                },
	                "remove": {
	                    "button": "�R��",
	                    "title": "�R�����",
	                    "submit": "�e�X�R��",
	                    "confirm": {
	                        "_": "�z�T�w�n�R���z�ҿ���� %d ����ƶܡH",
	                        "1": "�z�T�w�n�R���z�ҿ���� 1 ����ƶܡH"
	                    }
	                },
	                "error": {
	                    "system": "�t�εo�Ϳ��~(��h��T)"
	                },
	                "edit": {
	                    "button": "�ק�",
	                    "title": "�ק���",
	                    "submit": "�e�X�ק�"
	                },
	                "multi": {
	                    "title": "�h����",
	                    "info": "�z�ҿ�ܪ��h����Ƥ��A�����]�t�F���P���ȡC�Y�z�Q�n�N���̳��אּ�P�@�ӭȡA�i�H�b����J�A�n���M���̷|�O�d�U�ۭ쥻���ȡC",
	                    "restore": "�_��",
	                    "noMulti": "����J��ݳ�W��J�A���e�\�h����Ƥ@�_�ק�"
	                }
	            },
	            "autoFill": {
	                "cancel": "����"
	            },
	            "buttons": {
	                "copySuccess": {
	                    "_": "�ƻs�F %d �����",
	                    "1": "�ƻs�F 1 �����"
	                },
	                "copyTitle": "�w�g�ƻs��ŶKï",
	                "excel": "Excel",
	                "pdf": "PDF",
	                "print": "�C�L",
	                "copy": "�ƻs",
	                "colvis": "������",
	                "colvisRestore": "���m������",
	                "csv": "CSV",
	                "pageLength": {
	                    "-1": "��ܥ���",
	                    "_": "��� %d ��"
	                },
	                "createState": "�إߪ��A",
	                "removeAllStates": "�����Ҧ����A",
	                "removeState": "����",
	                "renameState": "���s�R�W",
	                "savedStates": "�x�s���A",
	                "stateRestore": "���A %d",
	                "updateState": "��s"
	            },
	            "searchPanes": {
	                "collapse": {
	                    "_": "�j�M���� (%d)",
	                    "0": "�j�M����"
	                },
	                "emptyPanes": "�S�j�M����",
	                "loadMessage": "���J�j�M������...",
	                "clearMessage": "�M��",
	                "count": "{total}",
	                "countFiltered": "{shown} ({total})",
	                "title": "�L�o���� - %d",
	                "showMessage": "��ܥ���",
	                "collapseMessage": "�P�|����"
	            },
	            "stateRestore": {
	                "emptyError": "�W�٤���ťաC",
	                "creationModal": {
	                    "button": "�إ�",
	                    "columns": {
	                        "search": "���j�M",
	                        "visible": "������"
	                    },
	                    "name": "�W�١G",
	                    "order": "�Ƨ�",
	                    "paging": "����",
	                    "scroller": "���b��m",
	                    "search": "�j�M",
	                    "searchBuilder": "�ƦX�d��",
	                    "select": "���",
	                    "title": "�إ߷s���A",
	                    "toggleLabel": "�]�t�G"
	                },
	                "duplicateError": "�����A�W�٤w�g�s�b�C",
	                "emptyStates": "�W�٤��i�ťաC",
	                "removeConfirm": "�T�w�n���� %s �ܡH",
	                "removeError": "�������A���ѡC",
	                "removeJoiner": "�M",
	                "removeSubmit": "����",
	                "removeTitle": "�������A",
	                "renameButton": "���s�R�W",
	                "renameLabel": "%s ���s�W�١G",
	                "renameTitle": "���s�R�W���A"
	            },
	            "select": {
	                "columns": {
	                    "_": "��ܤF %d ����",
	                    "1": "��ܤF 1 ����"
	                },
	                "rows": {
	                    "1": "��ܤF 1 �����",
	                    "_": "��ܤF %d �����"
	                },
	                "cells": {
	                    "1": "��ܤF 1 ����",
	                    "_": "��ܤF %d ����"
	                }
	            },
	            "zeroRecords": "�S���ŦX�����",
	            "aria": {
	                "sortAscending": "�G�ɾ��ƦC",
	                "sortDescending": "�G�����ƦC"
	            },
	            "info": "��ܲ� _START_ �� _END_ �����G�A�@ _TOTAL_ ��",
	            "infoEmpty": "��ܲ� 0 �� 0 �����G�A�@ 0 ��",
	            "infoFiltered": "(�q _MAX_ �����G���L�o)",
	            "infoThousands": ",",
	            "lengthMenu": "��� _MENU_ �����G",
	            "search": "�j�M�G",
	            "searchPlaceholder": "�п�J����r",
	            "thousands": ","
	        }
	    });

</script>

</body>
</html>