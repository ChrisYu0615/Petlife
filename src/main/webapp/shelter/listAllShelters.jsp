<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/main/main.css">
<title>List Shelters</title>
</head>
<body>
	<h1>收容所列表</h1>
	<c:if test="${shelterPageQty > 0}">
  		<b><font color=red>第${currentPage}/${shelterPageQty}頁</font></b>
	</c:if>
	<br>

	<table style="width:50%; text-align:center;">
		<tr>
			<th>收容所編號</th>
			<th>收容所帳號</th>
			<th>收容所密碼</th>
			<th>收容所名稱</th>
			<th>帳號建立時間</th>
			<th>密碼修改時間</th>
			<th>電話</th>
			<th>地址</th>
			<th>大頭貼</th>
			<th>簡介</th>
			<th>帳號狀態</th>
			<th>帳號種類</th>
			<th>經度</th>
			<th>緯度</th>
			<th>修改</th>
		</tr>
		<c:forEach var="shelter" items="${shelterList}">
			<tr>
				<td>${shelter.shelterId}</td>
				<td>${shelter.shelterAcct}</td>
				<td>${shelter.shelterPwd}</td>
				<td>${shelter.shelterName}</td>
				<td>${shelter.shelterCreateTime}</td>
				<td>${shelter.shelterPwdErrTimes}</td>
				<td>${shelter.shelterPhoneNum}</td>
				<td>${shelter.shelterAddress}</td>
				<td>${shelter.shelterPhoto}</td>
				<td>${shelter.shelterIntroduction}</td>
				<td>${AcctState.acctState}</td>
				<td>${AcctType.acctTypeId}</td>
				<td>${shelter.shelterLng}</td>
				<td>${shelter.shelterLat}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/shelter/shelter.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="shelterId" value="${shelter.shelterId}"> <input type="hidden"
							name="action" value="getOneToUpdate">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${currentPage > 1}">
		<a href="${pageContext.request.contextPath}/shelter/shelter.do?action=getAll&page=1">至第一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage - 1 != 0}">
		<a href="${pageContext.request.contextPath}/shelter/shelter.do?action=getAll&page=${currentPage - 1}">上一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage + 1 <= shelterPageQty}">
		<a href="${pageContext.request.contextPath}/shelter/shelter.do?action=getAll&page=${currentPage + 1}">下一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage != shelterPageQty}">
		<a href="${pageContext.request.contextPath}/shelter/shelter.do?action=getAll&page=${empPageQty}">至最後一頁</a>&nbsp;
	</c:if>
	<br>

	<br><br>
	
	<a href="${pageContext.request.contextPath}/index.jsp">回首頁</a>	
</body>
</html>