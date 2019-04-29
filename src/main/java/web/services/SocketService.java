package web.services;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import web.entities.Message;
import web.entities.SocketEvent;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class SocketService {

  @Autowired
  private MessageService messageService;

  private List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
  private Map<String, WebSocketSession> loggedInSessions = new ConcurrentHashMap<>();

  private String JSON(Object obj){
    return new Gson().toJson(obj, obj.getClass());
  }

  public void sendToOne(WebSocketSession webSocketSession, String message) throws IOException {
    webSocketSession.sendMessage(new TextMessage(message));
  }

  public void sendToOne(WebSocketSession webSocketSession, Object obj) throws IOException {
    sendToOne(webSocketSession, JSON(obj));
  }

  public void sendToUser(String username, Object obj) throws IOException {
    WebSocketSession userSession = loggedInSessions.get(username);

    if (userSession != null)
      sendToOne(userSession, JSON(obj));
  }
  public void sendToOthers(String username, Object obj){
    loggedInSessions.forEach((user, session) -> {
      if(!user.equals(username)) {
        try {
          sendToOne(session, JSON(obj));
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });
  }

  public void sendToAll(Object obj) {
    sendToAll(JSON(obj));
  }

  public void sendToAll(String message) {
    TextMessage msg = new TextMessage(message);
    for (WebSocketSession webSocketSession : sessions) {
      try {
        webSocketSession.sendMessage(msg);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public void updateUserSession(String username, WebSocketSession session) {
    if (!username.equals("anon"))
      loggedInSessions.put(username, session);
  }

  public void removeUserSession(String username) {
    if (!username.equals("anon"))
      loggedInSessions.remove(username);
  }

  public void addSession(WebSocketSession session) {
    sessions.add(session);
  }

  public void removeSession(WebSocketSession session) {
    sessions.remove(session);
  }

  public void saveMessage(Message message){
    messageService.insertMessage(message);
  }
}
