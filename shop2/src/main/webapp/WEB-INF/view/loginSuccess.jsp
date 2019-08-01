<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>로그인 결과 화면</title>
</head>
<body>
<h2>환영합니다. ${SessionScope.loginUser.userName}님이 로그인하셨습니다.</h2> <!-- SessionScope : 세션에 설정해 줬던 속성값 -->
<table border="1" style="border-collapse: collapse;">
<tr><td>아이디</td><td>${loginUser.userId}</td></tr>
<tr><td>이름</td><td>${loginUser.userName}</td></tr>
<tr><td>우편번호</td><td>${loginUser.postcode}</td></tr>
<tr><td>주소</td><td>${loginUser.address}</td></tr>
<tr><td>전화번호</td><td>${loginUser.phoneNo}</td></tr>
<tr><td>이메일</td><td>${loginUser.email}</td></tr>
<tr><td>생년월일</td><td><fmt:formatDate value="${loginUser.birthDay}" pattern="yyyy년 MM월dd일"/></td></tr>
</table>
</body>
</html>