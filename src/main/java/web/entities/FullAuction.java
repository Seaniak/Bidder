package web.entities;



import java.util.List;

public class FullAuction extends Auction {

	private List<Image> images;
	private List<Bid> bids;

	public FullAuction(){

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
