<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%-- /webapp/WEB-INF/view/item/list.jsp --%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>상품 목록</title>
</head>
<body>
<a href="create.shop">상품등록</a>
<a href="../cart/cartView.shop" style="float:right;">장바구니</a>
<table border="1" style="border-collapse: collapse;">
  <tr><th width="80">상품ID</th>
      <th width="320">상품명</th>
      <th width="100">가격</th>
      <th width="80">수정</th>
      <th width="80">삭제</th></tr>
  <c:forEach items="${itemList}" var="item">
  <tr><td align="center">${item.id}</td>
  <td align="left">
     <a href="detail.shop?id=${item.id}">${item.name}</a></td>
  <td align="right">
  <fmt:formatNumber value="${item.price}" type="CURRENCY"
       currencySymbol=""/>원</td>
  <td align="center"><a href="edit.shop?id=${item.id}">수정</a></td>
  <td align="center"><a href="confirm.shop?id=${item.id}">삭제</a></td>
  </tr></c:forEach>    
</table></body></html>