<%@page import="com.petlife.forum.entity.Article"%>
<%@page import="com.petlife.forum.service.impl.ForumServiceImpl"%>
<%@page import="com.petlife.forum.service.ForumService"%>
<%@page import="com.petlife.forum.entity.Forum"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
   // �q request ��� Article ����
   Article article = (Article) request.getAttribute("article");
   if (article == null) {
       article = new Article(); // �p�G�S�� article�A�h�Ыؤ@�ӪŪ����
   }
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�峹��ƭק� - update_Article_Input.jsp</title>

<!-- �z���˦���M��L�Y�����e -->

</head>
<body bgcolor='white'>

<h3>�峹��ƭק�:</h3>

<%-- ���~�T���i�� --%>
<c:if test="${not empty errorMsgs}">
    <font style="color:red">�Эץ��H�U���~:</font>
    <ul>
        <c:forEach var="message" items="${errorMsgs}">
            <li style="color:red">${message}</li>
        </c:forEach>
    </ul>
</c:if>

<FORM METHOD="post" ACTION="art.do" name="form1">
<table>
   <tr>
		<td>�峹�s��:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="articleId" value="${article.articleId}" size="45"/></td>
	</tr>
    <tr>
        <td>�峹���D:</td>
        <td><input type="TEXT" name="articleName" value="${article.articleName}" size="45"/></td>
    </tr>
    <tr>
        <td>�峹���e:</td>
        <td><input type="TEXT" name="articleContent" value="${article.articleContent}" size="45"/></td>
    </tr>
    <tr>
        <td>�׾¤���:</td>
        <td>
            <select  name="forumName">
             	<option value=1 />����</option>
            	<option value=2 />�߿�</option>
            	<option value=3 />����</option>
            	<option value=4 />�S��</option>
            </select> 
        </td>
    </tr>
    <tr>
        <td>���A:</td>
        <td><input type="checkbox" name="state" ${article.state ? 'checked' : ''}/></td>
    </tr>
</table>
<input type="hidden" name="updateTime" value="${article.updateTime}">
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="articleId" value="${article.articleId}">
<input type="submit" value="�e�X�ק�"></FORM>


<script>

<!-- �a�J�{�b�ɶ���buylistDate -->
document.addEventListener('DOMContentLoaded', function () {
    // �����e����M�ɶ�
    var currentDateTime = new Date();

    // �N����榡�Ƭ� "yyyy-MM-dd HH:mm:ss"�A�o�̨ϥΤF�۩w�q�� formatDate ���
    var formattedDateTime = formatDate(currentDateTime);

    // �N�榡�ƫ᪺����ɶ��]�m���J�ت��Ȥ�
    document.querySelector("input[name='updateTime']").value = formattedDateTime;
});

// �۩w�q������榡�ƨ��
function formatDate(date) {
    var year = date.getFullYear();
    var month = (date.getMonth() + 1).toString().padStart(2, '0');
    var day = date.getDate().toString().padStart(2, '0');
    var hours = date.getHours().toString().padStart(2, '0');
    var minutes = date.getMinutes().toString().padStart(2, '0');
    var seconds = date.getSeconds().toString().padStart(2, '0');

    return year + '-' + month + '-' + day + ' ' + hours + ':' + minutes + ':' + seconds;
}
<!-- /�a�J�{�b�ɶ���buylistDate -->
</script>


</body>
</html>
