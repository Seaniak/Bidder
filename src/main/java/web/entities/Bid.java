package web.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Bid {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private Timestamp time;
  private long userId;
  private int sum;

  public Bid() {

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

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public int getSum() {
    return sum;
  }

  public void setSum(int sum) {
    this.sum = sum;
  }
}
