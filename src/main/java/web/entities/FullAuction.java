package web.entities;

import javax.persistence.OneToMany;
import java.util.List;

public class FullAuction extends Auction {

  @OneToMany
  private List<Image> images;
  @OneToMany
  private List<Bid> bids;

  public FullAuction(List<Image> images, List<Bid> bids) {
    this.images = images;
    this.bids = bids;
  }

  public List<Image> getImages() {
    return images;
  }

  public void setImages(List<Image> images) {
    this.images = images;
  }

  public List<Bid> getBids() {
    return bids;
  }

  public void setBids(List<Bid> bids) {
    this.bids = bids;
  }
}
