package websocket;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class EchoHandler  extends TextWebSocketHandler{
	private Set<WebSocketSession> clients = new HashSet<WebSocketSession>();

	@Override //연결된 경우
	public void afterConnectionEstablished(WebSocketSession session)
			                                       throws Exception {
		super.afterConnectionEstablished(session);
		System.out.println("클라이언트 접속됨");
		clients.add(session);
	}
	@Override //메시지 수신
	public void handleMessage(WebSocketSession session, 
			           WebSocketMessage<?> message) throws Exception {
		String loadMessage = (String)message.getPayload();
		System.out.println("서버에 도착한 메시지 :" + loadMessage);
		clients.add(session);
		//다른 클라이언트에게 전송
		for(WebSocketSession s : clients) {
			s.sendMessage(new TextMessage(loadMessage)); //다른 클라이언트에게 메시지 전송
		}
	}
	@Override //오류 발생시
	public void handleTransportError(WebSocketSession session, 
			                    Throwable exception) throws Exception {
		super.handleTransportError(session, exception);
		System.out.println("오류 발생:" + exception.getMessage());
	}
	@Override  //연결 종료
	public void afterConnectionClosed(WebSocketSession session,
			                     CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
		System.out.println("클라이언트 접속 해제:" + status.getReason());
	}	
}
