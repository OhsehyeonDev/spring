<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" isErrorPage="true"%>
<%--
   isErrorPage="true" : exception ∞¥√º∞° ¿¸¥ﬁ.
                      exception ∞¥√º : exception.CartEmptyException  ∞¥√º
 --%>    
<%-- /webapp/WEB-INF/view/exception.jsp --%>    
<script>
   alert("${exception.message}"); //
   location.href="${exception.url}"; //getUrl() ∏ﬁº≠µÂ »£√‚
</script>