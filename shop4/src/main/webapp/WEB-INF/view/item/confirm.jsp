<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%-- /webapp/WEB-INF/view/item/confirm.jsp --%>    
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>  
<!DOCTYPE html>
<html><head>
<meta charset="EUC-KR">
<title>��ǰ ���� �� Ȯ��</title></head><body>
<h2>��ǰ ���� �� Ȯ��</h2>
<table><tr><td><img src="img/${item.pictureUrl}"></td>
     <td><table>
        <tr><td>��ǰ��</td><td>${item.name}</td></tr>
        <tr><td>����</td><td>${item.price}��</td></tr>
        <tr><td>��ǰ����</td><td>${item.description}</td></tr>
        <tr><td colspan="2">
             <input type="button" value="��ǰ����"
                  onclick="location.href='delete.shop?id=${item.id}'">
             <input type="button" value="��ǰ���"
                  onclick="location.href='list.shop'"></td></tr>
           </table>
 </table>
</body>
</html>