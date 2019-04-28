package web.entities;
public class SocketEvent {

  public String action;
  public Object payload;

  public SocketEvent(String action, Object payload) {
    this.action = action;
    this.payload = payload;
  }
}