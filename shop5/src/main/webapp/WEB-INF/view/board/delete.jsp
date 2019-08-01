<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp"%>
<%-- /webapp/WEB-INF/view/board/delete.jsp --%>
<!DOCTYPE html>
<html>
<head><meta charset="EUC-KR">
<title>게시판 삭제 화면</title></head>
<body>
	<form:form modelAttribute="board" action="delete.shop" method="post"
		name="f">
		<form:hidden path="num" />
		<spring:hasBindErrors name="board">
			<font color="red"><c:forEach items="${errors.globalErrors}"
					var="error">
					<spring:message code="${error.code }" />
				</c:forEach>
			</font>
		</spring:hasBindErrors>
		<input type="hidden" name="num" value="${board.num}">
		<table><caption>SPRING 게시글 삭제 화면</caption>
			<tr><td>게시글비밀번호</td>
				<td><form:password path="pass" /></td></tr>
			<tr><td><td>
			<a href="javascript:document.f.submit()">[게시글삭제]</a></td></td>
			</tr>
		</table>
	</form:form>
</body></html>