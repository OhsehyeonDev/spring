<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%-- /webapp/WEB-INF/view/board/reply.jsp --%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html>
<html><head><meta charset="EUC-KR">
<title>�Խ��� ��� ����</title>
</head>
<body>
<form:form modelAttribute="board" action="reply.shop" 
     method="post" name="f">
  <form:hidden  path="num" /> <form:hidden  path="ref" />
  <form:hidden  path="reflevel" /> <form:hidden  path="refstep" />
  <table><caption>Spring �Խ��� ��� ���</caption>
  <tr><td>�۾���</td><td><input type="text" name="name">
      <font color="red"><form:errors path="name" /></font></td></tr>
  <tr><td>��й�ȣ</td><td><form:password path="pass" />
      <font color="red"><form:errors path="pass" /></font></td></tr>
  <tr><td>����</td><td><form:input path="subject" 
                   value="RE:${board.subject}"/> 
    <font color="red"><form:errors path="subject" /></font></td></tr>
  <tr><td>����</td>
      <td><textarea name="content" rows="15" cols="80"></textarea>
    <font color="red"><form:errors path="content" /></font></td></tr>
     <script>CKEDITOR.replace("content", {
   filebrowserImageUploadUrl : "imgupload.shop?CKEditorFuncNum=1"
   });
   </script>
  <tr><td colspan="2">
  <a href="javascript:document.f.submit()">[�亯�۵��]</a></td></tr>    
  </table></form:form>
</body></html>