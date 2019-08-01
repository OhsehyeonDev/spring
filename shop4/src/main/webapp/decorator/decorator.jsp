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
       <a href="${path}/user/login.shop">로그인</a>
       <a href="${path}/user/userEntry.shop">회원가입</a></c:if>
    <c:if test="${!empty sessionScope.loginUser }">
      ${sessionScope.loginUser.userName}님
       <a href="${path}/user/logout.shop">로그아웃</a>
    </c:if></td></tr> 
  <tr><td width="15%" valign="top"><a href=
     "${path}/user/mypage.shop?id=${sessionScope.loginUser.userId}">
         회원관리</a><br>
     <a href="${path}/item/list.shop">상품관리</a><br>
     <a href="${path}/board/list.shop">게시판</a><br>
     <a href="${path}/chat/chat.shop">채팅</a><br>
  </td><td colspan="2" style="text-align: left; vertical-align: top">
     <decorator:body /></td></tr>
  <tr><td colspan="3">구디아카데미 Since 2016</td></tr>
</table></body></html>