<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원 정보</title>
</head>
<body>
<h2>환영합니다. ${sessionScope.loginUser.userName}님</h2>
<a href="mypage.shop?id=${loginUser.userId}">mypage</a><br>
<a href="logout.shop">로그아웃</a>
</body>
</html>