<%@page import="com.petlife.forum.entity.Article"%>
<%@page import="com.petlife.forum.service.impl.ForumServiceImpl"%>
<%@page import="com.petlife.forum.service.ForumService"%>
<%@page import="com.petlife.forum.entity.Forum"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
   // 從 request 獲取 Article 物件
   Article article = (Article) request.getAttribute("article");
   if (article == null) {
       article = new Article(); // 如果沒有 article，則創建一個空的實例
   }
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>文章資料修改 - update_Article_Input.jsp</title>

<!-- 您的樣式表和其他頭部內容 -->

</head>
<body bgcolor='white'>

<h3>文章資料修改:</h3>

<%-- 錯誤訊息展示 --%>
<c:if test="${not empty errorMsgs}">
    <font style="color:red">請修正以下錯誤:</font>
    <ul>
        <c:forEach var="message" items="${errorMsgs}">
            <li style="color:red">${message}</li>
        </c:forEach>
    </ul>
</c:if>

<FORM METHOD="post" ACTION="art.do" name="form1">
<table>
   <tr>
		<td>文章編號:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="articleId" value="${article.articleId}" size="45"/></td>
	</tr>
    <tr>
        <td>文章標題:</td>
        <td><input type="TEXT" name="articleName" value="${article.articleName}" size="45"/></td>
    </tr>
    <tr>
        <td>文章內容:</td>
        <td><input type="TEXT" name="articleContent" value="${article.articleContent}" size="45"/></td>
    </tr>
    <tr>
        <td>論壇分類:</td>
        <td>
            <select  name="forumName">
             	<option value=1 />狗狗</option>
            	<option value=2 />貓貓</option>
            	<option value=3 />閒聊</option>
            	<option value=4 />特殊</option>
            </select> 
        </td>
    </tr>
    <tr>
        <td>狀態:</td>
        <td><input type="checkbox" name="state" ${article.state ? 'checked' : ''}/></td>
    </tr>
</table>
<input type="hidden" name="updateTime" value="${article.updateTime}">
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="articleId" value="${article.articleId}">
<input type="submit" value="送出修改"></FORM>


<script>

<!-- 帶入現在時間到buylistDate -->
document.addEventListener('DOMContentLoaded', function () {
    // 獲取當前日期和時間
    var currentDateTime = new Date();

    // 將日期格式化為 "yyyy-MM-dd HH:mm:ss"，這裡使用了自定義的 formatDate 函數
    var formattedDateTime = formatDate(currentDateTime);

    // 將格式化後的日期時間設置到輸入框的值中
    document.querySelector("input[name='updateTime']").value = formattedDateTime;
});

// 自定義的日期格式化函數
function formatDate(date) {
    var year = date.getFullYear();
    var month = (date.getMonth() + 1).toString().padStart(2, '0');
    var day = date.getDate().toString().padStart(2, '0');
    var hours = date.getHours().toString().padStart(2, '0');
    var minutes = date.getMinutes().toString().padStart(2, '0');
    var seconds = date.getSeconds().toString().padStart(2, '0');

    return year + '-' + month + '-' + day + ' ' + hours + ':' + minutes + ':' + seconds;
}
<!-- /帶入現在時間到buylistDate -->
</script>


</body>
</html>
