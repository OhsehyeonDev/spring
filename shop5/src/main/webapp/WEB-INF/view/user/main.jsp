<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ȸ�� ����</title>
</head>
<body>
<h2>ȯ���մϴ�. ${sessionScope.loginUser.userName}��</h2>
<a href="mypage.shop?id=${loginUser.userId}">mypage</a><br>
<a href="logout.shop">�α׾ƿ�</a>
</body>
</html>