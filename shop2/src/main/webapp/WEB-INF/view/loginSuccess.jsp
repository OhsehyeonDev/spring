<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�α��� ��� ȭ��</title>
</head>
<body>
<h2>ȯ���մϴ�. ${SessionScope.loginUser.userName}���� �α����ϼ̽��ϴ�.</h2> <!-- SessionScope : ���ǿ� ������ ��� �Ӽ��� -->
<table border="1" style="border-collapse: collapse;">
<tr><td>���̵�</td><td>${loginUser.userId}</td></tr>
<tr><td>�̸�</td><td>${loginUser.userName}</td></tr>
<tr><td>�����ȣ</td><td>${loginUser.postcode}</td></tr>
<tr><td>�ּ�</td><td>${loginUser.address}</td></tr>
<tr><td>��ȭ��ȣ</td><td>${loginUser.phoneNo}</td></tr>
<tr><td>�̸���</td><td>${loginUser.email}</td></tr>
<tr><td>�������</td><td><fmt:formatDate value="${loginUser.birthDay}" pattern="yyyy�� MM��dd��"/></td></tr>
</table>
</body>
</html>