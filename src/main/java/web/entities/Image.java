package web.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private long auctionId;
	private boolean thumbnail;
	private String path;

	public Image(){
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
