<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%-- /webapp/WEB-INF/view/board/list.jsp --%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�Խ��� ���</title>
</head>
<body>
<table>
  <tr><td colspan="5">
    <div style="display : inline;">
    <form action="list.shop" method="post" name="searchform">
     <input type="hidden" name="pageNum" value="1">
     <select name="searchtype" style = "width:100px;">
      <option value="">�����ϼ���</option>
      <option value="subject">����</option>
      <option value="name">�ۼ���</option>
      <option value="content">����</option>
     </select>
     <input type="text" name="searchcontent" 
                      value="${param.searchcontent }"
                      style = "width:250px;">
     <input type="submit" value="�˻�">             
    </form></div></td></tr>
    <c:if test="${listcount > 0}">
     <tr><td colspan="4">Spring �Խ���</td><td>�۰���:${listcount}</td>
     </tr>
     <tr><th>��ȣ</th><th>����</th><th>�۾���</th><th>��¥</th>
     <th>��ȸ��</th></tr>
     <c:forEach var="board" items="${boardlist}">
      <tr><td>${boardno}</td>
      <c:set var="boardno" value="${boardno - 1}" />
      
      <td style="text-align: left;">
      <c:if test="${! empty board.fileurl}">
        <a href="file/${board.fileurl}">@</a></c:if>
      <c:if test="${empty board.fileurl}">&nbsp;&nbsp;&nbsp;</c:if>
      <c:forEach begin="1" end="${board.reflevel}">&nbsp;&nbsp;</c:forEach>
      <c:if test="${board.reflevel > 0}">��</c:if>
      <a href="detail.shop?num=${board.num}">${board.subject}</a></td>
      
      <td>${board.name}</td><td>${board.regdate}</td>
      <td>${board.readcnt}</td></tr>
     </c:forEach>
     <tr><td colspan="5">
       <c:if test="${pageNum > 1}">
       <a href="list.shop?pageNum=${pageNum - 1}">[����]</a></c:if>
       <c:if test="${pageNum <= 1}">[����]</c:if>
       <c:forEach var="a" begin="${startpage }" end="${endpage}">
         <c:if test="${a == pageNum}">[${a}]</c:if>
         <c:if test="${a != pageNum}">
         <a href="list.shop?pageNum=${a}">[${a}]</a></c:if>
       </c:forEach>
       <c:if test="${pageNum < maxpage}">
       <a href="list.shop?pageNum=${pageNum + 1}">[����]</a></c:if>
       <c:if test="${pageNum >= maxpage}">[����]</c:if>
     </td></tr>
    </c:if>
    <c:if test="${listcount == 0}">
      <tr><td colspan="5">��ϵ� �Խù��� �����ϴ�.</td></tr>
    </c:if>
    <tr><td colspan="5" align="right">
    <a href="write.shop">[�۾���]</a></td></tr>
</table></body></html>