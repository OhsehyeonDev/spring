<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>    
<%-- /webapp/WEB-INF/cart/cart.jsp --%>    
<!DOCTYPE html><html><head>
<meta charset="EUC-KR">
<title>장바구니</title></head>
<body><h2>장바구니</h2>
<table>
  <tr><td colspan="4">장바구니 상품 목록</td></tr>
  <tr><th>상품명</th><th>가격</th><th>수량</th><th>합계</th></tr>
  <c:set var="tot" value="${0}"/>
  <c:forEach items="${cart.itemSetList}" var="itemSet" varStatus="stat">
   <tr><td>${itemSet.item.name}</td><td>${itemSet.item.price}</td>
   <td>${itemSet.quantity}</td>
   <td>${itemSet.item.price *itemSet.quantity}
     <a href="cartDelete.shop?index=${stat.index}">ⓧ</a></td></tr>
   <c:set var="tot" value="${tot+(itemSet.item.price*itemSet.quantity)}"/>
  </c:forEach>
  <tr><td colspan="4" align="right">
  총 구입 금액 : ${tot}원</td></tr>
</table><br>
${message}<br><a href="../item/list.shop">상품목록</a>
<a href="checkout.shop">주문하기</a></body></html>