<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%-- /webapp/WEB-INF/view/board/write.jsp --%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�Խñ� �ۼ�</title>
</head>
<body>
<form:form modelAttribute="board" action="write.shop"
   enctype="multipart/form-data" name="f">
   <table><tr><td>�۾���</td><td><form:input path="name" />
   <font color="red"><form:errors path="name" /></font></td></tr>
   <tr><td>��й�ȣ</td><td><form:password path="pass" />
   <font color="red"><form:errors path="pass" /></font></td></tr>
   <tr><td>����</td><td><form:input path="subject" />
   <font color="red"><form:errors path="subject" /></font></td></tr>
   <tr><td>����</td>
   <td><form:textarea path="content" rows="15" cols="80" />
   <script>CKEDITOR.replace("content", {
     filebrowserImageUploadUrl : "imgupload.shop"
   });
   </script>
   <font color="red"><form:errors path="content" /></font></td></tr>
   <tr><td>÷������</td><td><input type="file" name="file1"></td></tr>
   <tr><td colspan="2">
    <a href="javascript:document.f.submit()">[�Խñ۵��]</a>
    <a href="list.shop">[�Խñ۸��]</a>
    </td></tr></table></form:form></body></html>