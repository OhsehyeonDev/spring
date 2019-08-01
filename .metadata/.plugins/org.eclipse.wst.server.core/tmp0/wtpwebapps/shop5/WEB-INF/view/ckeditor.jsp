<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%-- /webapp/WEB-INF/view/ckeditor.jsp --%>    
<script type='text/javascript'>
   window.parent.CKEDITOR.tools.callFunction
       (${param.CKEditorFuncNum}, '${fileName}','이미지 업로드 완료')
</script>;
