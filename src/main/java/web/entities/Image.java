package web.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Image {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private long auctionId;
  private boolean thumbnail;
  private String imageData;

  public Image() {
  }

  public Image(long auctionId, boolean thumbnail, String imageData) {
    this.auctionId = auctionId;
    this.thumbnail = thumbnail;
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

  public boolean isThumbnail() {
    return thumbnail;
  }

  public void setThumbnail(boolean thumbnail) {
    this.thumbnail = thumbnail;
  }

  public String getImageData() {
    return imageData;
  }

  public void setImageData(String imageData) {
    this.imageData = imageData;
  }
}
