<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%-- /webapp/WEB-INF/view/board/detail.jsp --%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�Խù� �� ����</title>
</head>
<body>
<table>
   <tr><td colspan="2">Spring �Խ���</td></tr>
   <tr><td width="15%">�۾���</td><td width="85%" class="leftcol">${board.name}</td></tr>
   <tr><td>����</td><td class="leftcol">${board.subject}</td></tr>
   <tr><td>����</td><td class="leftcol"><table class="lefttoptable">
     <tr><td class="leftcol lefttoptable">${board.content}</td></tr>
   </table></td></tr>
   <tr><td>÷������</td><td>&nbsp;
    <c:if test="${!empty board.fileurl}">
     <a href="file/${board.fileurl}">${board.fileurl}</a>
    </c:if></td></tr>
   <tr><td colspan="2">
     <a href="reply.shop?num=${board.num}">[�亯]</a>
     <a href="update.shop?num=${board.num}">[����]</a>
     <a href="delete.shop?num=${board.num}">[����]</a>
     <a href="list.shop">[�Խù����]</a>
   </td></tr>
</table>
</body>
</html>