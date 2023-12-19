<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.petlife.forum.service.impl.ArticleServiceImpl"%>
<%@page import="com.petlife.forum.service.impl.ArticleImgServiceImpl"%>
<%@page import="com.petlife.forum.service.ArticleService"%>
<%@page import="com.petlife.forum.service.ArticleImgService"%>
<%@page import="com.petlife.forum.entity.Article"%>
<%@page import="com.petlife.forum.entity.ArticleImg"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%
    ArticleService articleSvc = new ArticleServiceImpl();
    List<Article> list = articleSvc.getAllArticle();
    pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html lang="zh-TW">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Title -->
    <title>Blog - Furry </title>
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
            <img src="../assets/img/loader.gif" alt="img">
        </div>
    </div>

    <div class="headerPage"></div>



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



    <!--Our Blog-->
    <section id="our_blog_area" class="section_padding">
        <!-- <a href="/furry/blog-details.html" style="font-size:  50px; color: chocolate; padding: left 10px;">我要發文</a> -->
        
        <button class="btn btn-primary" type="submit" style="background-color: darkorange;">
            <a href="../article/blog-details.jsp" style="font-size:  30px; color: brown; padding: left 10px;">
            我要發文
        </a>
                

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
                                        <li class="list-group-item">最新文章</li>
                                        <li class="list-group-item">浪浪的家</li>
                                    </ul>
                                </div>
                                <!-- 狗狗 -->
                                <div class="accordion-item">
                                    <span class="sidebar_common_heading">
                                        <h3>貓貓</h3>
                                    </span>
                                    <ul class="list-group list-group-flush">
                                        <li class="list-group-item">熱門文章</li>
                                        <li class="list-group-item">最新文章</li>
                                        <li class="list-group-item">貓貓的家</li>
                                        <li class="list-group-item">貓貓保健</li>
                                    </ul>
                                </div>
                                <!-- 範例-3 -->
                                <div class="accordion-item">
                                    <span class="sidebar_common_heading">
                                        <h3>閒聊</h3>
                                    </span>
                                    <ul class="list-group list-group-flush">
                                        <li class="list-group-item">熱門文章</li>
                                        <li class="list-group-item">最新文章</li>
                                        <!-- <li class="list-group-item">貓貓保健</li> -->
                                    </ul>
                                </div>
                                <div class="accordion-item">
                                    <span class="sidebar_common_heading">
                                        <h3>特殊</h3>
                                    </span>
                                    <ul class="list-group list-group-flush">
                                        <li class="list-group-item">熱門文章</li>
                                        <li class="list-group-item">最新文章</li>
                                        <li class="list-group-item">爬蟲類</li>
                                        <li class="list-group-item">木木梟</li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                


                <div class="col-lg-9">
                    <div class="row">
                        <span><p>搜尋文章標題</p>
                        <input type="text" class="form-control" placeholder="搜尋文章標題" required=""></span>
                        <span><p>關鍵字搜尋</p>
                        <input type="text" class="form-control" placeholder="關鍵字搜尋" required=""></span>
                         <button class="btn btn-primary" type="submit" style="background-color: darkorange;">搜尋</button>
                         <%@ include file="page1.file" %> 
                         
                        <c:forEach var="article" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" >
                        <div class="col-lg-6 col-md-6 col-sm-12 col-12">
                            <div class="blog_area_wrapper">
                                <div class="blog_area_img">
                                    <a href="blog-details.html"><img src="<%=request.getContextPath()%>/art/art.do?action=getArticleImgById&articleId=${article.articleId}" alt="img"></a>
                                </div>
                                <div class="blog_area_content">
                                    <a href="<%=request.getContextPath()%>/art/art.do?action=getArticleById&articleId=${article.articleId}"><h3>${article.articleName}</h3></a>
                                    <p>
										${article.articleContent}
                                    </p>
                                    <div class="blog_area_author_wrappe">
                                        <div class="blog_area_author_img">
                                            <img src="assets/img/blog/blog-post-1.png" alt="img">
                                        </div>
                                        <div class="blog_area_author_text">
                                            <h5>${article.user.userName}</h5>
                                            <p><span>${article.updateTime}</span> <i class="fas fa-circle"></i> <span>8 min
                                                    read</span> </p>
                                        </div>
                                    </div>
                                </div>
                            </div>           
                        </div>
                        </c:forEach>
                        <%@ include file="page2.file" %>
                        
                        
                        
<!--                         <div class="col-lg-6 col-md-6 col-sm-12 col-12"> -->
<!--                             <div class="blog_area_wrapper"> -->
<!--                                 <div class="blog_area_img"> -->
<!--                                     <a href="blog-details.html"><img src="assets/img/blog/blog-2.png" alt="img"></a> -->
<!--                                 </div> -->
<!--                                 <div class="blog_area_content"> -->
<!--                                     <h3><a href="blog-details.html">5 crazy things dogs do when -->
<!--                                             left alone at home</a></h3> -->
<!--                                     <p> -->
<!--                                         Irure enim eiusmod ipsum do Lorem sit consectetur enim consectetur. Nostrud -->
<!--                                         ipsum -->
<!--                                         eiusmod eiusmod culpa anim excepteur. -->
<!--                                     </p> -->
<!--                                     <div class="blog_area_author_wrappe"> -->
<!--                                         <div class="blog_area_author_img"> -->
<!--                                             <img src="assets/img/blog/blog-post-2.png" alt="img"> -->
<!--                                         </div> -->
<!--                                         <div class="blog_area_author_text"> -->
<!--                                             <h5>Jennifer lawrence</h5> -->
<!--                                             <p><span>26 Oct 2021</span> <i class="fas fa-circle"></i> <span>8 min -->
<!--                                                     read</span> </p> -->
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                         <div class="col-lg-6 col-md-6 col-sm-12 col-12"> -->
<!--                             <div class="blog_area_wrapper"> -->
<!--                                 <div class="blog_area_img"> -->
<!--                                     <a href="blog-details.html"><img src="assets/img/blog/blog-3.png" alt="img"></a> -->
<!--                                 </div> -->
<!--                                 <div class="blog_area_content"> -->
<!--                                     <h3><a href="blog-details.html">Your dog desperately needs -->
<!--                                             support from you</a></h3> -->
<!--                                     <p> -->
<!--                                         Irure enim eiusmod ipsum do Lorem sit consectetur enim consectetur. Nostrud -->
<!--                                         ipsum -->
<!--                                         eiusmod eiusmod culpa anim excepteur. -->
<!--                                     </p> -->
<!--                                     <div class="blog_area_author_wrappe"> -->
<!--                                         <div class="blog_area_author_img"> -->
<!--                                             <img src="assets/img/blog/blog-post-3.png" alt="img"> -->
<!--                                         </div> -->
<!--                                         <div class="blog_area_author_text"> -->
<!--                                             <h5>Jennifer lawrence</h5> -->
<!--                                             <p><span>26 Oct 2021</span> <i class="fas fa-circle"></i> <span>8 min -->
<!--                                                     read</span> </p> -->
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                         <div class="col-lg-6 col-md-6 col-sm-12 col-12"> -->
<!--                             <div class="blog_area_wrapper"> -->
<!--                                 <div class="blog_area_img"> -->
<!--                                     <a href="blog-details.html"><img src="assets/img/blog/blog-4.png" alt="img"></a> -->
<!--                                 </div> -->
<!--                                 <div class="blog_area_content"> -->
<!--                                     <h3><a href="blog-details.html">The friendliest dog breeds for the best of your -->
<!--                                             families</a></h3> -->
<!--                                     <p> -->
<!--                                         Irure enim eiusmod ipsum do Lorem sit consectetur enim consectetur. Nostrud -->
<!--                                         ipsum -->
<!--                                         eiusmod eiusmod culpa anim excepteur. -->
<!--                                     </p> -->
<!--                                     <div class="blog_area_author_wrappe"> -->
<!--                                         <div class="blog_area_author_img"> -->
<!--                                             <img src="assets/img/blog/blog-post-4.png" alt="img"> -->
<!--                                         </div> -->
<!--                                         <div class="blog_area_author_text"> -->
<!--                                             <h5>Jennifer lawrence</h5> -->
<!--                                             <p><span>26 Oct 2021</span> <i class="fas fa-circle"></i> <span>8 min -->
<!--                                                     read</span> </p> -->
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                         <div class="col-lg-6 col-md-6 col-sm-12 col-12"> -->
<!--                             <div class="blog_area_wrapper"> -->
<!--                                 <div class="blog_area_img"> -->
<!--                                     <a href="blog-details.html"><img src="assets/img/blog/blog-5.png" alt="img"></a> -->
<!--                                 </div> -->
<!--                                 <div class="blog_area_content"> -->
<!--                                     <h3><a href="blog-details.html">The exact rules of how to tour and travel with your -->
<!--                                             pets</a></h3> -->
<!--                                     <p> -->
<!--                                         Irure enim eiusmod ipsum do Lorem sit consectetur enim consectetur. Nostrud -->
<!--                                         ipsum -->
<!--                                         eiusmod eiusmod culpa anim excepteur. -->
<!--                                     </p> -->
<!--                                     <div class="blog_area_author_wrappe"> -->
<!--                                         <div class="blog_area_author_img"> -->
<!--                                             <img src="assets/img/blog/blog-post-5.png" alt="img"> -->
<!--                                         </div> -->
<!--                                         <div class="blog_area_author_text"> -->
<!--                                             <h5>Jennifer lawrence</h5> -->
<!--                                             <p><span>26 Oct 2021</span> <i class="fas fa-circle"></i> <span>8 min -->
<!--                                                     read</span> </p> -->
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                         <div class="col-lg-6 col-md-6 col-sm-12 col-12"> -->
<!--                             <div class="blog_area_wrapper"> -->
<!--                                 <div class="blog_area_img"> -->
<!--                                     <a href="blog-details.html"><img src="assets/img/blog/blog-6.png" alt="img"></a> -->
<!--                                 </div> -->
<!--                                 <div class="blog_area_content"> -->
<!--                                     <h3><a href="blog-details.html">Benefits of taking your dog to obedience -->
<!--                                             training</a></h3> -->
<!--                                     <p> -->
<!--                                         Irure enim eiusmod ipsum do Lorem sit consectetur enim consectetur. Nostrud -->
<!--                                         ipsum -->
<!--                                         eiusmod eiusmod culpa anim excepteur. -->
<!--                                     </p> -->
<!--                                     <div class="blog_area_author_wrappe"> -->
<!--                                         <div class="blog_area_author_img"> -->
<!--                                             <img src="assets/img/blog/blog-post-6.png" alt="img"> -->
<!--                                         </div> -->
<!--                                         <div class="blog_area_author_text"> -->
<!--                                             <h5>Jennifer lawrence</h5> -->
<!--                                             <p><span>26 Oct 2021</span> <i class="fas fa-circle"></i> <span>8 min -->
<!--                                                     read</span> </p> -->
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                         </div> -->
                        <div class="col-lg-12">
                            <div class="pagination_area">
                                <ul class="pagination">
                                    <li class="page-item">
                                        <a class="page-link" href="#" aria-label="Previous">
                                            <span aria-hidden="true">«</span>
                                            <span class="sr-only">Previous</span>
                                        </a>
                                    </li>
                                    <li class="page-item"><a class="page-link" href="#">1</a></li>
                                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                                    <li class="page-item">
                                        <a class="page-link" href="#" aria-label="Next">
                                            <span aria-hidden="true">»</span>
                                            <span class="sr-only">Next</span>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>


    <div class="footerPage"></div>



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
        $(".headerPage").load("../components/header.html");
        $(".footerPage").load("../components/footer.html");
    </script>
</body>

</html>