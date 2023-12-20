<%@page import="com.petlife.forum.service.impl.ArticleServiceImpl"%>
<%@page import="com.petlife.forum.service.impl.ArticleImgServiceImpl"%>
<%@page import="com.petlife.forum.service.ArticleService"%>
<%@page import="com.petlife.forum.service.ArticleImgService"%>
<%@page import="com.petlife.forum.entity.Article"%>
<%@page import="com.petlife.forum.entity.ArticleImg"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%-- <%@ page import="com.emp.model.*"%> --%>
<%@page import="com.petlife.forum.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    ArticleService articleSvc = new ArticleServiceImpl();
    List<Article> list = articleSvc.getAllArticle();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有文章 - listAllArticle.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
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

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有文章資料 - listAllArticle.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>文章編號</th>
		<th>使用者ID</th>
		<th>論壇ID</th>
		<th>文章標題</th>
		<th>文章內文</th>
		<th>發文時間</th>
		<th>點閱數</th>
		<th>狀態</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
<%--   --%>
	<c:forEach var="article" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" >
		
		<tr>
			<td>${article.articleId}</td>
			<td>${article.user.userId}</td>
			<td>${article.forum}</td>
			<td>${article.articleName}</td>
			<td>${article.articleContent}</td>
			<td>${article.updateTime}</td> 
			<td>${article.ctr}</td>
			<td>${article.state}</td>
			
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/art/art.do" style="margin-bottom: 0px;">
			     <input type="submit" value="更新文章">
			     <input type="hidden" name="articleId"  value="${article.articleId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/art/art.do" style="margin-bottom: 0px;">
			     <input type="submit" value="下架文章">
			     <input type="hidden" name="articleId"  value="${article.articleId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>