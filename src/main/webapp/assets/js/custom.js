(function ($) {
    "use strict";

    $(window).on('scroll', function () {
        if ($(this).scrollTop() > 10) { $('.navbar-area').addClass("is-sticky"); }
        else { $('.navbar-area').removeClass("is-sticky"); }
    });

    $(function () {
        $(window).on('scroll', function () {
            var scrolled = $(window).scrollTop();
            if (scrolled > 600) $('.go-top').addClass('active'); if (scrolled < 600) $('.go-top').removeClass('active');
        });

        $('.go-top').on('click', function () { $("html, body").animate({ scrollTop: "0" }, 500); });
    });

    jQuery('.mean-menu').meanmenu({
        meanScreenWidth: "1199"
    });

    $(".others-option-for-responsive .dot-menu").on("click", function () { $(".others-option-for-responsive .container .container").toggleClass("active"); });
    $(".others-options .search-box").on("click", function () { $(".search-overlay").toggleClass("search-overlay-active"); });
    $(".search-overlay-close").on("click", function () { $(".search-overlay").removeClass("search-overlay-active"); });


    // Testimonials Slider
    $('.testmoinal_main_slider').owlCarousel({
        loop: true,
        dots: false,
        autoplayHoverPause: true,
        // autoplay: true,
        // smartSpeed: 1000,
        margin: 50,
        center: true,
        nav: true,
        navText: [
            "<i class='fas fa-chevron-left'></i>",
            "<i class='fas fa-chevron-right'></i>"
        ],
        responsive: {
            0: {
                items: 1,
            },
            768: {
                items: 2,
            },
            992: {
                items: 2,
            },
            1200: {
                items:3
            }
        }
    });
    // Client  Slider
    $('.client_logo_slider_wrapper').owlCarousel({
        loop: true,
        dots: false,
        autoplayHoverPause: true,
        autoplay: true,
        smartSpeed: 1000,
        margin: 30,
        nav: false,
        navText: [
            "<i class='fas fa-chevron-left'></i>",
            "<i class='fas fa-chevron-right'></i>"
        ],
        responsive: {
            0: {
                items: 2,
            },
            768: {
                items: 3,
            },
            992: {
                items: 5,
            },
            1200: {
                items:7
            }
        }
    });
    // Adoption Details Slider
    $('.adoption_pet_slider').owlCarousel({
        loop: true,
        dots: false,
        autoplayHoverPause: true,
        autoplay: true,
        smartSpeed: 1000,
        margin: 30,
        nav: true,
        navText: [
            "<i class='fas fa-chevron-left'></i>",
            "<i class='fas fa-chevron-right'></i>"
        ],
        responsive: {
            0: {
                items: 1,
            },
            768: {
                items: 2,
            },
            992: {
                items: 3,
            },
            1200: {
                items:3
            }
        }
    });


    // counter
    jQuery(document).ready(function ($) {
        $('.counter').counterUp({
            delay: 10,
            time: 1000
        });
    });
    // Preeloader 
    $(window).on("load", function () {
        $("#status").fadeOut();
        $("#preloader")
            .delay(350)
            .fadeOut("slow");
    });

         // Slider For category pages / filter price
         if (typeof noUiSlider === 'object') {
            var priceSlider = document.getElementById('price-slider');
    
            // Check if #price-slider elem is exists if not return
            // to prevent error logs
            if (priceSlider == null) return;
    
            noUiSlider.create(priceSlider, {
                start: [0, 750],
                connect: true,
                step: 50,
                margin: 200,
                range: {
                    'min': 0,
                    'max': 1000
                },
                tooltips: true,
                format: wNumb({
                    decimals: 0,
                    prefix: '$'
                })
            });
        }

}(jQuery));


