package web.controllers;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import web.entities.Bid;
import web.entities.SocketEvent;
import web.services.SocketService;

import java.io.IOException;
import java.util.Map;

@Controller
public class SocketController extends TextWebSocketHandler {

  // NOTE: Can not use @Autowired here due to WebSocketConfig instantiating the SocketController
  private SocketService socketService;

  public void setSocketService(SocketService socketService) {
    this.socketService = socketService;
  }

  @Override
  public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
    Map clientData = new Gson().fromJson(message.getPayload(), Map.class);

    switch (clientData.get("action").toString()){
      case "connect":
        socketService.updateUserSession(clientData.get("payload").toString(), session);
        break;
        case "logout":
        socketService.removeUserSession(clientData.get("payload").toString());
        break;
      case "message":
        socketService.sendToUser("loke", new SocketEvent("message", clientData.get("payload")), SocketEvent.class);
        break;
      default:
        System.err.println("No handler for action: " +clientData.get("action"));
    }

//    for testing
    Bid bid = new Bid(46, "loke", 25);
    socketService.sendToAll(new SocketEvent("bid", bid), SocketEvent.class);
  }

  @Override
  public void afterConnectionEstablished(WebSocketSession session) {
    socketService.addSession(session);
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
    socketService.removeSession(session);
  }
}
