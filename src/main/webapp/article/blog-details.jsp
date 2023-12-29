<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import = "com.petlife.user.entity.User"%>


<!DOCTYPE html>
<html lang="zxx">


<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Title -->
    <title>寵愛生活Petlife</title>
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

<body>
    <!-- preloader Area -->
    <div id="preloader">
        <div id="status">
            <img src="assets/img/loader.gif" alt="img">
        </div>
    </div>

    <%@include file ="../components/header.jsp"%>




    <!-- search -->
    <div class="search-overlay">
        <div class="d-table">
            <div class="d-table-cell">
                <div class="search-overlay-layer"></div>
                <div class="search-overlay-layer"></div>
                <div class="search-overlay-layer"></div>
                <div class="search-overlay-close">
                    <span class="search-overlay-close-line"></span>
                    <span class="search-overlay-close-line"></span>
                </div>
                <div class="search-overlay-form">
                    <form>
                        <input type="text" class="input-search" placeholder="Search here...">
                        <button type="button"><i class="fas fa-search"></i></button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Common Banner
    <section id="common_area_banner">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="common_banner_content">
                        <h2>Blog details</h2>
                        <ul>
                            <li><a href="index.html">Home</a></li>
                            <li><span>/</span>Blog details</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </section> -->
    <!--Our Blog-->
    <section id="our_blog_area" class="section_padding">
        <!-- <a href="/furry/blog-details.html" style="font-size:  50px; color: chocolate; padding: left 10px;">我要發文</a> -->



        </button>
        <br>
        <div class="container">
            <div class="row">
                <div class="col-lg-3">
                    <div class="sidebar_boxed_wrapper">
                        <div class="sidebar_common_heading">
                            <div class="sidebar_search_wrapper">

                                <div class="input-group mb-3">
                                    <input type="text" class="form-control" placeholder="Search">
                                    <button class="btn btn_theme btn_sm"><i class="fas fa-search"></i></button>
                                </div>
                            </div>

                            <div class="accordion" id="accordionExample">
                                <!-- 貓咪 -->
                                <div class="accordion-item">
                                    <span class="sidebar_common_heading">
                                        <h3>狗狗</h3>
                                    </span>
                                    <ul class="list-group list-group-flush">
                                        <li class="list-group-item">熱門文章</li>
                                        <li class="list-group-item">狗狗的家</li>

                                    </ul>
                                </div>
                                <!-- 狗狗 -->
                                <div class="accordion-item">
                                    <span class="sidebar_common_heading">
                                        <h3>貓貓</h3>
                                    </span>
                                    <ul class="list-group list-group-flush">
                                        <li class="list-group-item">熱門文章</li>
                                        <li class="list-group-item">貓貓的家</li>
                                    </ul>
                                </div>
                                <!-- 範例-3 -->
                                <div class="accordion-item">
                                    <span class="sidebar_common_heading">
                                        <h3>閒聊</h3>
                                    </span>
                                    <ul class="list-group list-group-flush">
                                        <li class="list-group-item">熱門文章</li>
                                        <li class="list-group-item">來閒聊ㄅ</li>
                                        <!-- <li class="list-group-item">貓貓保健</li> -->
                                    </ul>
                                </div>
                                <div class="accordion-item">
                                    <span class="sidebar_common_heading">
                                        <h3>特殊</h3>
                                    </span>
                                    <ul class="list-group list-group-flush">
                                        <li class="list-group-item">熱門文章</li>
                                        <li class="list-group-item">爬蟲類</li>
                                        <li class="list-group-item">木木梟</li>
                                    </ul>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-lg-9">
                    <h3>請撰寫你的文章</h3>
                    <div class="comment_form">																	   
                       <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/art/art.do" name="form1" enctype="multipart/form-data">
                            <div class="row">
                                <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
                                    <input type="radio" class="btn-check" name="forumName" id="btnradio1" value="1"
                                        autocomplete="off" checked>
                                    <label class="btn btn-outline-primary"  for="btnradio1">狗狗</label>

                                    <input type="radio" class="btn-check" name="forumName" id="btnradio2" value="2"
                                        autocomplete="off">
                                    <label class="btn btn-outline-primary" for="btnradio2">貓貓</label>

                                    <input type="radio" class="btn-check" name="forumName" id="btnradio3" value="3"
                                        autocomplete="off">
                                    <label class="btn btn-outline-primary" for="btnradio3">閒聊</label>

                                    <input type="radio" class="btn-check" name="forumName" id="btnradio4" value="4"
                                        autocomplete="off">
                                    <label class="btn btn-outline-primary" for="btnradio4">特殊</label>
                                </div>
                                <div class="row">
                                    <div class="col-lg-6 col-md-6 col-sm-12 col-12">
                                        <div class="form-group">
                                            <input type="text" name="articleName" class="form-control" placeholder="請輸入你的文章標題"
                                                required>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-12 col-md-6 col-sm-12 col-12">
                                    <div class="col">
                                        <label for="inputGroupFile02">照片:</label>
                                        <input type="file" class="form-control" id="articlePhoto" name="articlePhoto" multiple>
                                    
<%--                         	     <div><input type="text" name = "user" value ="<%=user.getUserId()%>"></div> --%>
<!--                         	     <div><input type="text" name = "user">UserId</div> -->
            
                                    
                                        <div class="pet_img"><span class="pet_img">預覽圖</span></div>
                                        </div>
                                </div>
                                <div class="col-lg-12 col-md-6 col-sm-12 col-12">
                                    <div class="form-group" >
                                        <textarea rows="20" placeholder="開始撰寫文章" class="form-control" name="articleContent"
                                            required=""></textarea>
                                    </div>
                                </div>
                        	    <input type="hidden" name = "ctr" value="0" readonly />
                        	    
                        
                                <div class="col-lg-6 col-md-6 col-sm-12 col-12">
                                    <div class="submit_btn">
                                        <button class="btn btn_theme btn_md"  name="action" value="insert">新增文章</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
               
        </div> 
                </section>

                <!-- Subscribe Area -->
                <section id="subscribe_area">
                    <div class="container">
                        <div class="subscribe_wrapper">
                            <div class="row align-items-center">
                                <div class="col-lg-4">
                                    <div class="subscribe_text">
                                        <p>Newsletter</p>
                                        <h3 class="heading_main_subscribe">To get weekly & monthly news,
                                            <span>Subscribe</span> to our newsletter.
                                        </h3>
                                    </div>
                                </div>
                                <div class="col-lg-6 offset-lg-2">
                                    <div class="cta_right_side">
                                        <form action="#!" id="subscribe_form">
                                            <div class="input-group">
                                                <input type="text" class="form-control" placeholder="Your mail address"
                                                    required>
                                                <button class="btn btn_theme btn_md" type="submit">Subscribe</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>






                <%@include file ="../components/footer.jsp"%>

                <script src="../assets/js/jquery.min.js"></script>
                <!-- Bootstrap js -->
                <script src="../assets/js/bootstrap.bundle.js"></script>
                <!-- Meanu js -->
                <script src="../assets/js/jquery.meanmenu.js"></script>
                <!-- Magnific Popup js -->
                <script src="../assets/js/jquery.magnific-popup.min.js"></script>
                <!-- owl carousel js -->
                <script src="../assets/js/owl.carousel.min.js"></script>
                <!-- wow.js -->
                <script src="../assets/js/wow.min.js"></script>
                <!-- waypoints.js -->
                <script src="../assets/js/waypoints.min.js"></script>
                <!-- counterup.js -->
                <script src="../assets/js/jquery.counterup.min.js"></script>
                <!-- Custom js -->
                <script src="../assets/js/gallery-popup.js"></script>
                <script src="../assets/js/custom.js"></script>
                <script src="../assets/js/video.js"></script>
              
<script>
    // 這是動物照片
$("#petphoto").change(function () {
$("div.pet_img").html("");
readURL(this);
function readURL(input) {
    if (input.files && input.files.length >= 0) {
        for (var i = 0; i < input.files.length; i++) {
            var reader = new FileReader();
            reader.onload = function (e) {
                var img = $("<img width='100' height='100'>").attr('src', e.target.result);
                $("div.pet_img").append(img);
            }
            reader.readAsDataURL(input.files[i]);
        }
    }
};
});       

</script>
</body>

</html>