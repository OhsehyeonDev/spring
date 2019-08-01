<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%-- /webapp/WEB-INF/view/item/list.jsp --%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>��ǰ ���</title>
</head>
<body>
<a href="create.shop">��ǰ���</a>
<a href="../cart/cartView.shop" style="float:right;">��ٱ���</a>
<table border="1" style="border-collapse: collapse;">
  <tr><th width="80">��ǰID</th>
      <th width="320">��ǰ��</th>
      <th width="100">����</th>
      <th width="80">����</th>
      <th width="80">����</th></tr>
  <c:forEach items="${itemList}" var="item">
  <tr><td align="center">${item.id}</td>
  <td align="left">
     <a href="detail.shop?id=${item.id}">${item.name}</a></td>
  <td align="right">
  <fmt:formatNumber value="${item.price}" type="CURRENCY"
       currencySymbol=""/>��</td>
  <td align="center"><a href="edit.shop?id=${item.id}">����</a></td>
  <td align="center"><a href="confirm.shop?id=${item.id}">����</a></td>
  </tr></c:forEach>    
</table></body></html>