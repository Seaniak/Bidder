package web.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Chat {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private long auctionId;

  // TODO: inner join chat and messages
//  @OneToMany(cascade = CascadeType.ALL, mappedBy = "chatId")
//  private List<Message> messages;

  public Chat() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getAuctionId() {
    return auctionId;
  }

  public void setAuctionId(long auctionId) {
    this.auctionId = auctionId;
  }

//  public List<Message> getMessages() {
//    return messages;
//  }

//  public void setMessages(List<Message> messages) {
//    this.messages = messages;
//  }
}
