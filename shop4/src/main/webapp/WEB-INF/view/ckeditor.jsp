<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%-- /webapp/WEB-INF/view/ckeditor.jsp --%>    
<script type='text/javascript'>
   window.parent.CKEDITOR.tools.callFunction
       (${param.CKEditorFuncNum}, '${fileName}','�̹��� ���ε� �Ϸ�')
</script>;
