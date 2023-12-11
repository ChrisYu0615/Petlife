$(document).ready(function () {
    $(".headerPage").load("../component/header.html");
    $(".footerPage").load("../component/footer.html");

    $("#sidebar_title ,#btn_cancel").on("click", function () {
        var targetPageURL = "./user_profile.html";

        // 使用 window.location.href 進行頁面跳轉
        window.location.href = targetPageURL;
    });

    $('#myTable').DataTable({
        "ajax": {
            "url": "../assets/json/orderlist.json", // 請替換為你的JSON檔案路徑
            "dataSrc": "" // 表示JSON數據的根節點為空
        }, "columns": [
            { "data": "order_id" },
            { "data": "order_name" },
            { "data": "order_state" },
            { "data": "total_amount" },
            { "data": "pay_type" },
            { "data": "create_time" },
            {
                "data": null,
                "render": function (data, type, row) {
                    // 在這裡可以自定義操作按鈕的HTML
                    if (data.order_state === "已完成") {
                        return '<button class="btn-sm btn-secondary" id="btn_cancel" onclick = "cancelOrder(' + row.order_id + ')" data-bs-toggle="modal" data-bs-target="#cancel_order" disabled>取消</button> ' +
                            '<button class="btn-sm btn-danger" id="btn_order" onclick = "rateOrder(' + row.order_id + ')" data-bs-toggle="modal" data-bs-target="#rate">評價</button><br>';

                    } else {
                        return '<button class="btn-sm btn-primary" id="btn_cancel" onclick = "cancelOrder(' + row.order_id + ')" data-bs-toggle="modal" data-bs-target="#cancel_order">取消</button> ' +
                            '<button class="btn-sm btn-secondary" id="btn_order" onclick = "rateOrder(' + row.order_id + ')" data-bs-toggle="modal" data-bs-target="#rate" disabled>評價</button><br>';
                    }
                }
            }
        ],
        responsive: true,
        "orderClasses": false,
        "createdRow": function (row, data, dataIndex) {
            // 在每一行创建后执行的逻辑
            // 添加一个用于显示子行的标识 class
            $(row).addClass('parent-row');
        },
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
});


$('#myTable').on('click', '.parent-row', function () {
    var tr = $(this);
    var row = $('#myTable').DataTable().row(tr);

    if (row.child.isShown()) {
        // 关闭子行
        row.child.hide();
        tr.removeClass('shown');
    } else {
        // 打开子行
        row.child(format(row.data())).show();
        tr.addClass('shown');
    }
});

function format(data) {

    var productsHtml =
        '<div class="row">' +
        '<div class="col-2"><strong>商品名稱</strong></div>' +
        '<div class="col-2 order_item"><strong>購買數量</strong></div>' +
        '<div class="col-2 order_item"><strong>金額</strong></div>' +
        '</div>';

    for (var i = 0; i < data.products.length; i++) {
        var product = data.products[i];
        var btn_return;
        // if (data.order_state != "已完成") {
        //     btn_return = '<div class="col-2 offset-4 order_item"><button class="btn btn-secondary btn-sm return-btn" data-product-id="' + product.product_id + '" disabled>退貨</button></div>';
        // }else{
        //     btn_return ="";
        // }
        productsHtml +=
            '<div class="row">' +
            '<div class="col-2">' + product.product_name + '</div>' +
            '<div class="col-2 order_item">' + product.quantity + '</div>' +
            '<div class="col-2 order_item">' + product.amount + '</div>'
            + /*btn_return +*/ '</div>';

        // 添加商品之间的分隔线
        if (i < data.products.length - 1) {
            productsHtml += '<hr>';
        }
    }

    return productsHtml;
}

// 退貨按鈕
$('#myTable').on('click', '.return-btn', function () {
    var productId = $(this).data('product-id');
    console.log('Return Product with ID: ' + productId);
});



$('#myTable').on('click', '#btn_order, #btn_cancel', function (e) {
    e.stopPropagation();
});


// 取消訂單
function cancelOrder(orderId) {
    // 在這裡添加編輯文章的邏輯
    console.log('Edit Article with ID: ' + orderId);
}

// 評價訂單
function rateOrder(orderId) {
    // 在這裡添加刪除文章的邏輯
    console.log('Delete Article with ID: ' + orderId);
}