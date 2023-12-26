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
	<!-- datatables -->
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.css" />    
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
                        	
                            <table class="table" id="myTable">
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
<%--                                     <%@ include file="page2.file" %> --%>
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
                                <h4 id="totalAmountDisplay">Total amount: <span>$<%= totalAmount %></span></h4>
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
<!-- 	dataTables -->
	<script src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.js"></script>
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

	        document.getElementById('totalAmountDisplay').innerText = '總金額: $' + totalAmount.toFixed(2);
	    } catch (e) {
	        console.error('計算總金額時發生錯誤:', e);
	    }
	}
	
	   $('#myTable').DataTable({
	        responsive: true,
	        orderClasses: false,
	        searching: false,
	        // 中文化
	        "language": {
	            "processing": "處理中...",
	            "loadingRecords": "載入中...",
	            "paginate": {
	                "first": "第一頁",
	                "previous": "上一頁",
	                "next": "下一頁",
	                "last": "最後一頁"
	            },
	            "emptyTable": "目前沒有資料",
	            "datetime": {
	                "previous": "上一頁",
	                "next": "下一頁",
	                "hours": "時",
	                "minutes": "分",
	                "seconds": "秒",
	                "amPm": [
	                    "上午",
	                    "下午"
	                ],
	                "unknown": "未知",
	                "weekdays": [
	                    "週日",
	                    "週一",
	                    "週二",
	                    "週三",
	                    "週四",
	                    "週五",
	                    "週六"
	                ],
	                "months": [
	                    "一月",
	                    "二月",
	                    "三月",
	                    "四月",
	                    "五月",
	                    "六月",
	                    "七月",
	                    "八月",
	                    "九月",
	                    "十月",
	                    "十一月",
	                    "十二月"
	                ]
	            },
	            "searchBuilder": {
	                "add": "新增條件",
	                "condition": "條件",
	                "deleteTitle": "刪除過濾條件",
	                "button": {
	                    "_": "複合查詢 (%d)",
	                    "0": "複合查詢"
	                },
	                "clearAll": "清空",
	                "conditions": {
	                    "array": {
	                        "contains": "含有",
	                        "equals": "等於",
	                        "empty": "空值",
	                        "not": "不等於",
	                        "notEmpty": "非空值",
	                        "without": "不含"
	                    },
	                    "date": {
	                        "after": "大於",
	                        "before": "小於",
	                        "between": "在其中",
	                        "empty": "為空",
	                        "equals": "等於",
	                        "not": "不為",
	                        "notBetween": "不在其中",
	                        "notEmpty": "不為空"
	                    },
	                    "number": {
	                        "between": "在其中",
	                        "empty": "為空",
	                        "equals": "等於",
	                        "gt": "大於",
	                        "gte": "大於等於",
	                        "lt": "小於",
	                        "lte": "小於等於",
	                        "not": "不為",
	                        "notBetween": "不在其中",
	                        "notEmpty": "不為空"
	                    },
	                    "string": {
	                        "contains": "含有",
	                        "empty": "為空",
	                        "endsWith": "字尾為",
	                        "equals": "等於",
	                        "not": "不為",
	                        "notEmpty": "不為空",
	                        "startsWith": "字首為",
	                        "notContains": "不含",
	                        "notStartsWith": "開頭不是",
	                        "notEndsWith": "結尾不是"
	                    }
	                },
	                "data": "欄位",
	                "leftTitle": "群組條件",
	                "logicAnd": "且",
	                "logicOr": "或",
	                "rightTitle": "取消群組",
	                "title": {
	                    "_": "複合查詢 (%d)",
	                    "0": "複合查詢"
	                },
	                "value": "內容"
	            },
	            "editor": {
	                "close": "關閉",
	                "create": {
	                    "button": "新增",
	                    "title": "新增資料",
	                    "submit": "送出新增"
	                },
	                "remove": {
	                    "button": "刪除",
	                    "title": "刪除資料",
	                    "submit": "送出刪除",
	                    "confirm": {
	                        "_": "您確定要刪除您所選取的 %d 筆資料嗎？",
	                        "1": "您確定要刪除您所選取的 1 筆資料嗎？"
	                    }
	                },
	                "error": {
	                    "system": "系統發生錯誤(更多資訊)"
	                },
	                "edit": {
	                    "button": "修改",
	                    "title": "修改資料",
	                    "submit": "送出修改"
	                },
	                "multi": {
	                    "title": "多重值",
	                    "info": "您所選擇的多筆資料中，此欄位包含了不同的值。若您想要將它們都改為同一個值，可以在此輸入，要不然它們會保留各自原本的值。",
	                    "restore": "復原",
	                    "noMulti": "此輸入欄需單獨輸入，不容許多筆資料一起修改"
	                }
	            },
	            "autoFill": {
	                "cancel": "取消"
	            },
	            "buttons": {
	                "copySuccess": {
	                    "_": "複製了 %d 筆資料",
	                    "1": "複製了 1 筆資料"
	                },
	                "copyTitle": "已經複製到剪貼簿",
	                "excel": "Excel",
	                "pdf": "PDF",
	                "print": "列印",
	                "copy": "複製",
	                "colvis": "欄位顯示",
	                "colvisRestore": "重置欄位顯示",
	                "csv": "CSV",
	                "pageLength": {
	                    "-1": "顯示全部",
	                    "_": "顯示 %d 筆"
	                },
	                "createState": "建立狀態",
	                "removeAllStates": "移除所有狀態",
	                "removeState": "移除",
	                "renameState": "重新命名",
	                "savedStates": "儲存狀態",
	                "stateRestore": "狀態 %d",
	                "updateState": "更新"
	            },
	            "searchPanes": {
	                "collapse": {
	                    "_": "搜尋面版 (%d)",
	                    "0": "搜尋面版"
	                },
	                "emptyPanes": "沒搜尋面版",
	                "loadMessage": "載入搜尋面版中...",
	                "clearMessage": "清空",
	                "count": "{total}",
	                "countFiltered": "{shown} ({total})",
	                "title": "過濾條件 - %d",
	                "showMessage": "顯示全部",
	                "collapseMessage": "摺疊全部"
	            },
	            "stateRestore": {
	                "emptyError": "名稱不能空白。",
	                "creationModal": {
	                    "button": "建立",
	                    "columns": {
	                        "search": "欄位搜尋",
	                        "visible": "欄位顯示"
	                    },
	                    "name": "名稱：",
	                    "order": "排序",
	                    "paging": "分頁",
	                    "scroller": "卷軸位置",
	                    "search": "搜尋",
	                    "searchBuilder": "複合查詢",
	                    "select": "選擇",
	                    "title": "建立新狀態",
	                    "toggleLabel": "包含："
	                },
	                "duplicateError": "此狀態名稱已經存在。",
	                "emptyStates": "名稱不可空白。",
	                "removeConfirm": "確定要移除 %s 嗎？",
	                "removeError": "移除狀態失敗。",
	                "removeJoiner": "和",
	                "removeSubmit": "移除",
	                "removeTitle": "移除狀態",
	                "renameButton": "重新命名",
	                "renameLabel": "%s 的新名稱：",
	                "renameTitle": "重新命名狀態"
	            },
	            "select": {
	                "columns": {
	                    "_": "選擇了 %d 欄資料",
	                    "1": "選擇了 1 欄資料"
	                },
	                "rows": {
	                    "1": "選擇了 1 筆資料",
	                    "_": "選擇了 %d 筆資料"
	                },
	                "cells": {
	                    "1": "選擇了 1 格資料",
	                    "_": "選擇了 %d 格資料"
	                }
	            },
	            "zeroRecords": "沒有符合的資料",
	            "aria": {
	                "sortAscending": "：升冪排列",
	                "sortDescending": "：降冪排列"
	            },
	            "info": "顯示第 _START_ 至 _END_ 筆結果，共 _TOTAL_ 筆",
	            "infoEmpty": "顯示第 0 至 0 筆結果，共 0 筆",
	            "infoFiltered": "(從 _MAX_ 筆結果中過濾)",
	            "infoThousands": ",",
	            "lengthMenu": "顯示 _MENU_ 筆結果",
	            "search": "搜尋：",
	            "searchPlaceholder": "請輸入關鍵字",
	            "thousands": ","
	        }
	    });

</script>

</body>
</html>