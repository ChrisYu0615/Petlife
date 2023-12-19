<%@page import="com.petlife.forum.service.impl.CommentServiceImpl"%>
<%@page import="com.petlife.forum.service.CommentService"%>
<%@page import="com.petlife.forum.entity.Article"%>
<%@page import="com.petlife.forum.entity.Comment"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%-- <%@ page import="com.emp.model.*"%> --%>
<%@page import="com.petlife.forum.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    CommentService commentSvc = new CommentServiceImpl();
    List<Comment> list = commentSvc.getAllComments();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>�Ҧ��d�� - listAllComments.jsp</title>

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

<h4>�����m�߱ĥ� EL ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ��峹��� - listAllArticle.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�d���s��</th>
		<th>�ϥΪ�ID</th>
		<th>�峹ID</th>
		<th>�d������</th>
		<th>�o��ɶ�</th>
		<th>�ק�</th>
		<th>�R��</th>
	</tr>
	<%@ include file="page1.file" %> 
<%--   --%>
	<c:forEach var="comment" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" >
		
		<tr>
			<td>${comment.commentId}</td>
			<td>${comment.user.userId}</td>
			<td>${comment.article.articleId}</td>
			<td>${comment.commentText}</td>
			<td>${comment.commentDatetime}</td> 
			
			
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article/article.do" style="margin-bottom: 0px;">
			     <input type="submit" value="��s�d��">
			     <input type="hidden" name="articleId"  value="${article.articleId}">
			     <input type="hidden" name="action"	value="update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article/article.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R���d��">
			     <input type="hidden" name="articleId"  value="${article.articleId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>