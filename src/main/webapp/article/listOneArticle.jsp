<%@page import="com.petlife.articlejdbc.ArticleDAO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.petlife.articlejdbc.*"%>
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
		 <h3>員工資料 - listOneEmp.jsp</h3>
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
			<td>${article.articleId}</td>
			<td>${article.userId}</td>
			<td>${article.forumArtId}</td>
			<td>${article.articleName}</td>
			<td>${article.articlecontent}</td>
			<td>${article.updateTime}</td>
			<td>${article.ctr}</td> 
			<td>${article.state}</td>
	</tr>
</table>

</body>
</html>