<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%-- /webapp/WEB-INF/view/admin/list.jsp --%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ȸ�� ���</title>
<script type="text/javascript">
   function allchkbox(allchk) {
	   var chks = document.getElementsByName("idchks");
	   for(var i = 0; i < chks.length;i++) {
		   chks[i].checked = allchk.checked;
	   }
   }
</script>
</head>
<body>
<form action="mailForm.shop" method="post">
  <table><tr><td colspan="7">ȸ�����</td></tr>
    <tr><th>���̵�</th><th>�̸�</th><th>��ȭ</th><th>����</th>
        <th>�̸���</th><th>&nbsp;</th>
        <th><input type="checkbox" name="allchk" 
            onchange="allchkbox(this)"></th></tr>
   <c:forEach items="${list}" var="user">
   <tr><td>${user.userId}</td><td>${user.userName}</td>
   <td>${user.phoneNo}</td>
   <td><fmt:formatDate value="${user.birthDay}" pattern="yyyy-MM-dd"/>
   </td><td>${user.email}</td>
   <td><a href="../user/update.shop?id=${user.userId}">����</a>
       <a href="../user/delete.shop?id=${user.userId}">����Ż��</a>
       <a href="../user/mypage.shop?id=${user.userId}">ȸ������</a>
   </td><td><input type="checkbox" name="idchks" 
        value="${user.userId}"></td></tr>
   </c:forEach>
   <tr><td colspan="7"><input type="submit" value="���Ϻ�����"></td></tr>         
  </table></form></body></html>