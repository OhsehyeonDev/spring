<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>    
<%-- /webapp/WEB-INF/view/user/delete.jsp --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ȸ��Ż�� Ȯ��</title>
</head>
<body>
<table>
  <tr><td>���̵�</td><td>${user.userId}</td></tr>
  <tr><td>�̸�</td><td>${user.userName}</td></tr>
  <tr><td>�������</td>
  <td><fmt:formatDate value="${user.birthDay}" 
       pattern="yyyy-MM-dd" /></td></tr></table>
  <form action="delete.shop" method="post" name="deleteform">
     <input type="hidden" name="userId" value="${param.id}" >
          ��й�ȣ <input type="password" name="password">
     <a href="javascript:deleteform.submit()">[ȸ��Ż��]</a>     
  </form>       
</body></html>