package web.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Thumbnail {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private long auctionId;
  private String image;

  public Thumbnail() {
  }

  public Thumbnail(long auctionId, String image) {
    this.auctionId = auctionId;
    this.image = image;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
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

}
