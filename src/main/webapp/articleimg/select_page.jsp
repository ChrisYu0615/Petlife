<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Article: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>IBM Article: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Article: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllArticle.jsp'>List</a> all Articles.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="article.do" >
        <b>輸入文章編號:</b>
        <input type="text" name="articleId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

<!--   <li> -->
<!--     <FORM METHOD="post" ACTION="article.do" > -->
<!--         <b>輸入關鍵字搜尋:</b> -->
<!--         <input type="text" name="keyword"> -->
<!--         <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--         <input type="submit" value="送出"> -->
<!--     </FORM> -->
<!--   </li> -->

  <jsp:useBean id="articleSvc" scope="page" class="com.petlife.forum.service.impl.ArticleServiceImpl" />
   
  <li>
     <FORM METHOD="post" ACTION="article.do" >
       <b>選擇文章編號:</b>
       <select size="1" name="articleId">
         <c:forEach var="article" items="${articleServiceImpl.all}" > 
          <option value="${article.articleId}">${article.articleId}>
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="article.do" >
       <b>選擇文章標題:</b>
       <select size="1" name="articleId">
         <c:forEach var="coupon" items="${articleService.getAll()}" > 
          <option value="${article.articleId}">${article.articleName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>優惠碼管理</h3>

<ul>
  <li><a href='addArticle.jsp'>Add</a> a new Article.</li>
</ul>

</body>
</html>