<%@page import="com.petlife.forum.entity.ArticleImg"%>
<!-- 保留 ArticleImg 的引用 -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>文章图片新增 - addArticleImg.jsp</title>
<!-- 页面标题仍然是添加文章图片 -->

<!-- 页面样式 -->
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

table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>
</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>文章图片新增 - addArticleImg.jsp</h3> <!-- 标题仍然保留为文章图片 -->
			</td>
			<td>
				<h4>
					<a href="select_page.jsp"><img src="images/tomcat.png"
						width="100" height="100" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>资料新增:</h3>

	<!-- 错误提示 -->
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">请修正以下错误:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<%
	// 从请求中获取之前错误提交的 ArticleImg 对象
	ArticleImg articleImg = (ArticleImg) request.getAttribute("articleImg");
	%>

	<form method="post" action="<%=request.getContextPath()%>/articleImg/articleImg.do"
		enctype="multipart/form-data">
		<!-- 表单仍然提交到处理 ArticleImg 的逻辑 -->
		<table>
			<!-- 表单字段 -->
			<tr>
				<td>文章ID:</td>
				<td><input type="text" name="articleId"
					value="${articleImg != null ? articleImg.article.articleId : ''}" size="45" /></td>
			</tr>
			<tr>
				<td>文章圖片:</td>
				<td><input type="file" name="upFiles" size="45" /></td>
			</tr>
		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="提交新增">
	</form>
</body>
</html>
