package web.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
public class Bid {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private long auctionId;
  private Timestamp time;
  private String username;
  private int sum;

  public Bid() {
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public long getAuctionId() {
    return auctionId;
  }

  public void setAuctionId(long auctionId) {
    this.auctionId = auctionId;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Timestamp getTime() {
    return time;
  }

  public void setTime(Timestamp time) {
    this.time = time;
  }

  public int getSum() {
    return sum;
  }

  public void setSum(int sum) {
    this.sum = sum;
  }
}
