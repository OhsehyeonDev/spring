<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%-- /webapp/WEB-INF/view/chat/chat.jsp --%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>websocket client</title>
<c:set var="port" value="${pageContext.request.localPort}"/>
<c:set var="server" value="${pageContext.request.serverName}"/>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
  $(function() {
	  var ws = new WebSocket
	           ("ws://${server}:${port}${path}/chatting.shop");

	  //�������� ����.
	  ws.onopen = function() {
		  $("#chatStatus").text("info:connection opened")
		  //Ű�̺�Ʈ ���
		  $("input[name=chatInput]").on("keydown",function(evt) {
			  if(evt.keyCode == 13) { // ����Ű�� ��������
				  var msg = $("input[name=chatInput]").val();
				  ws.send(msg);  //�޽����� ������ ����
				  $("input[name=chatInput]").val(""); //�Է¶��� ���ڸ� ����.
			  }
		  })
	  }
	  //�޼��� ����.
	  ws.onmessage = function(event) {
		  $("textarea").eq(0).prepend(event.data+"\n");
	  }
	  //������ ����� ���
	  ws.onclose = function(event) {
		  $("#chatStatus").text("info:connection close");
	  }
  })
</script>
</head>
<body>
<p>
<div id="chatStatus"></div>
<textarea name="chatMsg" rows="15" cols="40"></textarea><br>
�޽��� �Է� : <input type="text" name="chatInput">
</body></html>