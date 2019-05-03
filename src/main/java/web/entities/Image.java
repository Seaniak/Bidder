package web.entities;

import javax.persistence.*;

@Entity
public class Image {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private long auctionId;
  private String imageData;

  public Image() {
  }

  public Image(long auctionId, String imageData) {
    this.auctionId = auctionId;
    this.imageData = imageData;
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

  public String getImageData() {
    return imageData;
  }

  public void setImageData(String imageData) {
    this.imageData = imageData;
  }
}
