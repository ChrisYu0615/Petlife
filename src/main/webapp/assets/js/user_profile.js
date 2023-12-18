document.addEventListener("DOMContentLoaded", function () {
    $(".headerPage").load("../components/header.html");
    $(".footerPage").load("../components/footer.html");

    $("#sidebar_title ,#btn_cancel").on("click", function () {
        var targetPageURL = "./user_profile.html";

        // 使用 window.location.href 進行頁面跳轉
        window.location.href = targetPageURL;
    });


    var p_file_el = document.querySelector("#p_file");
    var preview_el = document.querySelector("#preview");
    var preview_img = function (file) {
        var reader = new FileReader();
        reader.readAsDataURL(file);
        reader.addEventListener("load", function () {
            let img_str = `<img src="${reader.result}" class="preview_img rounded-circle">`;
            preview_el.innerHTML = img_str;
        });
    };

    // p_file_el.addEventListener("change", function (e) {
    //     if (this.files.length > 0) {
    //         preview_img(this.files[0]);
    //     } else {
    //         preview_el.innerHTML = '<span class="text"><img src="" alt="" class="preview_img rounded-circle"></span>';
    //     }
    // });

    $(document).on("click", "button.btn_delete_card", function (e) {
        $(this).closest("span").remove();
    });

    $("#addCards").on("click", function () {
        $("#lightbox").fadeIn(1000);
    });

    $("button.btn_modal_close, #lightbox").on("click", function () {
        $("#lightbox").fadeOut(1000);
    });

    $("#lightbox > article").on("click", function (e) {
        e.stopPropagation();
    });

    $("button#btn_save_cards, #lightbox").on("click", function () {
        var cardNumber = $("#card_number").val();
        $("#lightbox").fadeOut(1000);
        if (cardNumber.trim() != "") {
            $("td#cardlist").append(`<span>${cardNumber} <button class="btn_delete_card">刪除</button><br></span>`);
        }
        $("#card_number").val("");
    });
})  