<%@page import="com.petlife.user.entity.User"%>
<%@page import="com.petlife.forum.entity.Article"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%

	User user = (User)session.getAttribute("account");
	if(user!=null){
		System.out.print(user.getUserId());
	}
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>文章新增 - addArticle.jsp</title>

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
                <h3>文章新增 - addArticle.jsp</h3>
            </td>
            <td>
                <h4>
                    <a href="select_page.jsp"><img src="images/tomcat.png"
                        width="100" height="100" border="0">回首頁</a>
                </h4>
            </td>
        </tr>
    </table>

    <h3>資料新增:</h3>

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
    // 從請求中取得之前錯誤提交的 Article 物件
    Article article = (Article)request.getAttribute("article"); 
    %>

    <FORM METHOD="post" ACTION="article.do" name="form1">
    <table>
        <tr>
            <td>文章標題:</td>
            <td><input type="text" name="articleName" value="${article != null ? article.articleName : ''}" size="45" /></td>
        </tr>
        <tr>
            <td>文章內文:</td>
            <td><textarea name="articleContent" rows="5" cols="45">${article != null ? article.articleContent : ''}</textarea></td>
        </tr>
        <tr>
            <td>論壇分類名稱:</td>
            <td>
            <select  name="forumName">
             	<option value="貓貓" ${article != null ? article.forum : ''}  />貓貓</option>
            	<option value="狗狗" ${article != null ? article.forum : ''}  />狗狗</option>
            	<option value="特殊" ${article != null ? article.forum : ''}  />特殊</option>
            	<option value="休閒" ${article != null ? article.forum : ''}  />休閒</option>
            </select> 
        </tr>
        <tr>
            <td>點擊次數:</td>
            <td><input type="text" name="ctr" value="${article != null ? article.ctr : ''}" size="45" /></td>
        </tr>
        <tr>
            <td>文章狀態:</td>
            <td>
                <select name="state">
                    <option value="true" ${article != null && article.state ? 'selected' : ''}>啟用</option>
                    <option value="false" ${article != null && !article.state ? 'selected' : ''}>禁用</option>
                </select>
            </td>
        </tr>
    </table>
    <input type="hidden" name="action" value="insert">
    <input type="submit" value="送出新增">
</FORM>
