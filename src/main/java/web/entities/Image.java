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
  private String path;

  @ManyToOne
  @JoinColumn
  private FullAuction fullAuction;

  public Image() {
  }

  public FullAuction getFullAuction() {
    return fullAuction;
  }

  public void setFullAuction(FullAuction fullAuction) {
    this.fullAuction = fullAuction;
  }

  public Image(Auction auction, boolean thumbnail, String path) {
    this.auctionId = auction.getId();
    this.thumbnail = thumbnail;
    this.path = path;
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

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }
}
