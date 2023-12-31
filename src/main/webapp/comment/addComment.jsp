<%@page import="com.petlife.user.entity.User"%>
<%@page import="com.petlife.forum.entity.Article"%>
<%@page import="com.petlife.forum.entity.Comment"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
    // 從session中獲取User對象
    User user = (User) session.getAttribute("user");
    if (user != null) {
        // 輸出用戶ID到服務器日誌
        System.out.println(user.getUserId());

        // 從session或request中獲取Article對象
        Article article = (Article) session.getAttribute("article");
        if (article == null) {
            article = (Article) request.getAttribute("article");
            if (article == null) {
                article = new Article(); // 如果仍然為null，則創建一個新的Article對象
            }
        }
      
    }
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>留言新增 - addComment.jsp</title>

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
                <h3>文章新增 - addComment.jsp</h3>
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




    <FORM METHOD="post" ACTION="comment.do" name="form1">
    <table>
    	
    
        <tr>
            <td>留言內文:</td>
            <td><input type="text" name="commentText" value="${comment != null ? comment.commentText : ''}" size="45" /></td>
        </tr>
       
        

    </table>
    <input type="hidden" name="action" value="insert">
    <input type="submit" value="送出新增">
</FORM>
