<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>사용자 등록 결과 화면</title>
</head>
<body>
<h2>사용자 등록 결과 화면</h2>
<table border="1" style="border-collapse: collapse;">
<tr><td>아이디</td><td>${user.userId}</td></tr>
<tr><td>이름</td><td>${user.userName}</td></tr>
<tr><td>우편번호</td><td>${user.postcode}</td></tr>
<tr><td>주소</td><td>${user.address}</td></tr>
<tr><td>전화번호</td><td>${user.phoneNo}</td></tr>
<tr><td>이메일</td><td>${user.email}</td></tr>
<tr><td>생년월일</td><td><fmt:formatDate value="${user.birthDay}" pattern="yyyy년 MM월dd일"/></td></tr>
</table>
</body>
</html>