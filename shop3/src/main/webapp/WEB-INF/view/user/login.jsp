<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<%-- /webapp/WEB-INF/view/login.jsp --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>로그인 화면</title>
</head>
<body>
<h2>사용자 로그인</h2>
<form:form modelAttribute="user" method="post" action="login.shop">
  <input type="hidden" name="userName" value="유효성 검증을 위한 의미없는 이름"/>
  <spring:hasBindErrors name="user">
    <font color="red">
      <c:forEach items="${errors.globalErrors}" var="error">
         <spring:message code="${error.code}" />
      </c:forEach>
    </font>
  </spring:hasBindErrors>
  <table border="1" style="border-collapse: collapse;">
   <tr height="40px"><td>아이디</td><td><form:input path="userId" />
    <font color="red"><form:errors path="userId" /></font></td></tr>
   <tr height="40px"><td>비밀번호</td><td><form:password path="password" />
    <font color="red"><form:errors path="password" /></font></td></tr>
   <tr height="40px"><td colspan="2" align="center">
   <input type="submit" value="로그인">
   <input type="button" value="회원가입" 
        onclick="location.href='userEntry.shop'"></td></tr></table>
</form:form></body></html>