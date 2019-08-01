<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>      
<%-- /webapp/WEB-INF/view/cart/checkout.jsp --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>주문 전 상품 목록 보기</title>
</head>
<body>
<h2>배송지 정보</h2>
<table>
  <tr><td width="30%">구매자 ID</td>
      <td width="70%">${sessionScope.loginUser.userId}</td></tr>
  <tr><td width="30%">구매자이름 </td>
      <td width="70%">${sessionScope.loginUser.userName}</td></tr>
  <tr><td width="30%">우편번호</td>
      <td width="70%">${sessionScope.loginUser.postcode}</td></tr>
  <tr><td width="30%">주소</td>
      <td width="70%">${sessionScope.loginUser.address}</td></tr>
  <tr><td width="30%">전화번호</td>
      <td width="70%">${sessionScope.loginUser.phoneNo}</td></tr>
  <tr><td width="30%">이메일</td>
      <td width="70%">${sessionScope.loginUser.email}</td></tr>
</table>
<h2>구매 상품 목록</h2>
<table>
  <tr><th>상품명</th><th>가격</th><th>수량</th><th>합계</th></tr>
  <c:forEach items="${sessionScope.CART.itemSetList}" 
                                        var="itemSet" varStatus="stat">
   <tr><td>${itemSet.item.name}</td><td>${itemSet.item.price}</td>
   <td>${itemSet.quantity}</td>
   <td>${itemSet.item.price *itemSet.quantity}</td></tr>
  </c:forEach>
  <tr><td colspan="4" align="right">
          총 구입 금액 : ${sessionScope.CART.total}원</td></tr>
  <tr><td colspan="4">
     <a href="end.shop">주문확정</a>&nbsp;
     <a href="../item/list.shop">상품 목록</a>&nbsp;
  </td></tr></table></body></html>