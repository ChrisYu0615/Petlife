<%@page import="com.petlife.forum.dao.ArticleDAO"%>
<%@page import="com.petlife.forum.dao.impl.ArticleDAOImpl"%>
<%@page import="com.petlife.forum.entity.Article"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>

<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  Article article = (Article) request.getAttribute("article"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>文章資料 - listOneArticle.jsp</title>

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
	width: 600px;
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

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>文章資料 - listOneEmp.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>文章ID</th>
		<th>使用者ID</th>
		<th>論壇主題</th>
		<th>文章標題</th>
		<th>文章內容</th>
		<th>上傳時間</th>
		<th>點閱數</th>
		<th>狀態</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<tr>
			<td><%=article.getArticleId()%></td>
			<td><%=article.getUser().getUserName()%></td>
			<td><%=article.getForum()%></td>
			<td><%=article.getArticleName()%></td>
			<td><%=article.getArticleContent()%></td>
			<td><%=article.getUpdateTime()%></td>
			<td><%=article.getCtr()%></td> 
			<td><%=article.getState()%></td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article/article.do" style="margin-bottom: 0px;">
			     <input type="submit" value="更新文章">
			     <input type="hidden" name="articleId"  value="${article.articleId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article/article.do" style="margin-bottom: 0px;">
			     <input type="submit" value="下架文章">
			     <input type="hidden" name="articleId"  value="${article.articleId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
	</tr>
	
</table>

</body>
</html>