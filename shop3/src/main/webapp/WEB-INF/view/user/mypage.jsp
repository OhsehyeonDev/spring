<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>    
<%-- /WEB-INF/view/user/mypage.jsp --%>    
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>MyPage</title>
<script type="text/javascript">
   $(document).ready(function(){
	 $("#minfo").show();
	 $("#oinfo").hide();
	 $(".saleLine").each(function() {  //�ֹ���ǰ ��� ����.
	     $(this).hide();
	 })
	 $("#tab1").addClass("select"); //class �Ӽ��� select ���� �߰�.
   })
   function disp_div(id,tab) {
	   $(".info").each(function() {
		   $(this).hide();
	   })
	   $(".tab").each(function() {
		   $(this).removeClass("select");
	   })	   
	   $("#"+id).show();
	   $("#" + tab).addClass("select");	   
   }
   function list_disp(id) {
	   $("#"+id).toggle(); //
   }   
</script>
<style type="text/css">
  .select {
     padding:3px;
	 background-color: #0000ff;
  }
  .select>a {  color : #ffffff;   
               text-decoration: none;
               font-weight: bold; }
</style>
</head>
<body>
<table>
   <tr><td id="tab1" class="tab">
      <a href="javascript:disp_div('minfo','tab1')">ȸ����������</a></td>
   <c:if test="${param.id != 'admin'}">
       <td id="tab2" class="tab">
      <a href="javascript:disp_div('oinfo','tab2')">�ֹ���������</a></td>
   </c:if></tr></table>
<%-- oinfo : �ֹ� ���� ��� --%>   
<div id="oinfo" class="info" style="display: none; width:100%;">
  <table>
    <tr><td colspan="3" align="center"><b>�ֹ� ���</b></td></tr>
    <tr><th>�ֹ���ȣ</th><th>�ֹ�����</th><th>���ֹ��ݾ�</th></tr>
    <c:forEach items="${salelist}" var="sale" varStatus="stat">
     <tr><td align="center">
       <a href="javascript:list_disp('saleLine${stat.index}')">
          ${sale.saleId}</a></td>
       <td align="center"><fmt:formatDate value="${sale.updatetime}" 
                              pattern="yyyy-MM-dd"/>
     </td><td align="right">${sale.totAmount}��</td></tr>
     <tr id="saleLine${stat.index }" class="saleLine">
       <td colspan="3" align="center">
       <table>
         <tr><th width="25%">��ǰ��</th><th width="25%">��ǰ����</th>
          <th width="25%">���ż���</th><th width="25%">��ǰ�Ѿ�</th></tr>
         <c:forEach items="${sale.itemList }" var="saleItem">
           <tr><td class="title">
                ${saleItem.item.name}</td>
               <td>${saleItem.item.price}��</td>
               <td>${saleItem.quantity}��</td>
            <td>${saleItem.quantity * saleItem.item.price}��</td></tr>
         </c:forEach>    
       </table></td></tr></c:forEach></table></div>
<%--minfo : ȸ�� ���� ��� --%>       
       <div id="minfo" class="info">
       <table>
        <tr><td colspan="2">ȸ�� ���� </td></tr>
        <tr><td>���̵�</td><td>${user.userId}</td></tr>
        <tr><td>�̸�</td><td>${user.userName}</td></tr>
        <tr><td>�����ȣ</td><td>${user.postcode}</td></tr>
        <tr><td>��ȭ��ȣ</td><td>${user.phoneNo}</td></tr>
        <tr><td>�ּ�</td><td>${user.address}</td></tr>
        <tr><td>�̸���</td><td>${user.email}</td></tr>
        <tr><td>�������</td><td><fmt:formatDate 
          value="${user.birthDay}" pattern="yyyy-MM-dd" /></td></tr>
       </table><br>
<%-- 1. �α��λ���, ���θ� �������� �ϵ��� �ϱ�.
     2. id�� �ش��ϴ� ������ db���� �б�
     3. update.jsp=> ��ȿ�� �����κ� ó�� ������ �̵�
  --%>       
    <a href="update.shop?id=${user.userId}">[ȸ����������]</a>&nbsp;
       <c:if test="${loginUser.userId != 'admin'}">
          <a href="delete.shop?id=${user.userId}">[ȸ��Ż��]</a>&nbsp;
       </c:if>
       <c:if test="${loginUser.userId == 'admin'}">
           <a href="../admin/list.shop">[ȸ�����]</a>&nbsp;
       </c:if>
       </div><br>
     </body></html>