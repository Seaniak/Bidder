package web.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

//  TODO: fix inner join with chat
//  @ManyToOne(fetch = FetchType.LAZY)
//  @JoinColumn(name = "CHAT_ID")
//  private Chat chatId;

//  private long chatId;
  private String sender;
  private String recipient;
  private String text;
  private Timestamp time;

  public Message(long chatId, String sender, String recipient, String text, Timestamp time) {
//    this.chatId = chatId;
    this.sender = sender;
    this.recipient = recipient;
    this.text = text;
    this.time = time;
  }

  public Message(String sender, String recipient, String text) {
    this.sender = sender;
    this.recipient = recipient;
    this.text = text;
    this.time = Timestamp.valueOf(LocalDateTime.now());
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

//  public Chat getChatId() {
//    return chatId;
//  }

//  public void setChatId(Chat chatId) {
//    this.chatId = chatId;
//  }

  public String getSender() {
    return sender;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }

  public String getRecipient() {
    return recipient;
  }

  public void setRecipient(String recipient) {
    this.recipient = recipient;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Timestamp getTime() {
    return time;
  }

  public void setTime(Timestamp time) {
    this.time = time;
  }
}
