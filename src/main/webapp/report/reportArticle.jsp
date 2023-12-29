<%@page import="com.petlife.user.entity.User"%>
<%@page import="com.petlife.forum.entity.Article"%>
<%@page import="com.petlife.forum.entity.ReportForum"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
User user = (User) session.getAttribute("user");
if (user != null) {
	System.out.print(user.getUserId());
}
%>

<html>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>檢舉文章</title>

<style>
table#table-1 {
	text-align: center;
}



table {
	width: 550px;
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

input.hidden-button{
	background-color:red;
	color: white;
    font-size: 30px;
    margin left: 5px
}

button{
	background-color:gray;
	color: white;
    font-size: 30px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>檢舉文章</h3>
			</td>
		</tr>
	</table>

	<%-- 錯誤提示 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<%
	// 從請求中取得之前錯誤提交的 ReportForum 物件
	ReportForum reportForum = (ReportForum) request.getAttribute("reportForum");
	%>

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/reportForum/reportForum.do" name="form1">
		<table>
			<tr>
				<td>檢舉類型:</td>
				<td><select name="reportTypeId">
						<option value="1" >分類錯誤</option>
						<option value="2" >內容違規</option>
						<option value="3" >其他</option>
				</select>
			</tr>

			<tr>
				<td>原因說明:</td>
				<td><textarea name="reportForumReason" rows="5" cols="45">${article != null ? article.articleContent : ''}</textarea></td>
			</tr>
		</table>
		
		<input type="hidden" name="action" value="addReport" class="hidden-button" > <input
			type="submit" value="檢舉" class="hidden-button">
		<button type="button" onclick="goBack()">取消</button>
	</FORM>