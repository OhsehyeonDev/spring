<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%-- /webapp/decorator/decorator.jsp --%>
<%@ taglib prefix="decorator" 
      uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />          
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title><decorator:title /></title>
<script type="text/javascript"   src=
"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" 
   src="http://cdn.ckeditor.com/4.5.7/full/ckeditor.js"></script>
<decorator:head />
<link rel="stylesheet" href="${path}/css/main.css">
</head>
<body>
<table>
  <tr><td colspan="3" style="text-align: right;">
    <c:if test="${empty sessionScope.loginUser }">
       <a href="${path}/user/login.shop">�α���</a>
       <a href="${path}/user/userEntry.shop">ȸ������</a></c:if>
    <c:if test="${!empty sessionScope.loginUser }">
      ${sessionScope.loginUser.userName}��
       <a href="${path}/user/logout.shop">�α׾ƿ�</a>
    </c:if></td></tr> 
  <tr><td width="15%" valign="top"><a href=
     "${path}/user/mypage.shop?id=${sessionScope.loginUser.userId}">
         ȸ������</a><br>
     <a href="${path}/item/list.shop">��ǰ����</a><br>
     <a href="${path}/board/list.shop">�Խ���</a><br>
     <a href="${path}/chat/chat.shop">ä��</a><br>
  </td><td colspan="2" style="text-align: left; vertical-align: top">
     <decorator:body /></td></tr>
  <tr><td colspan="3">�����ī���� Since 2016</td></tr>
</table></body></html>