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

	  //웹소켓이 연결.
	  ws.onopen = function() {
		  $("#chatStatus").text("info:connection opened")
		  //키이벤트 등록
		  $("input[name=chatInput]").on("keydown",function(evt) {
			  if(evt.keyCode == 13) { // 엔터키가 눌려지면
				  var msg = $("input[name=chatInput]").val();
				  ws.send(msg);  //메시지를 서버로 전송
				  $("input[name=chatInput]").val(""); //입력란의 문자를 제거.
			  }
		  })
	  }
	  //메세지 수신.
	  ws.onmessage = function(event) {
		  $("textarea").eq(0).prepend(event.data+"\n");
	  }
	  //연결이 종료된 경우
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
메시지 입력 : <input type="text" name="chatInput">
</body></html>