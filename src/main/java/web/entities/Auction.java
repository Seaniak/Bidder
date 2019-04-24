package web.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class Auction {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

  private String username;
  private String title;
  private String description;
  private String category;
  private String auctionCondition;
  private Timestamp createTime;
  private Timestamp endTime;
  private int startSum;
  private int reservedSum;

  @Transient
  private String frontEndCreateTime;
  @Transient
  private String frontEndEndTime;
  @Transient
  private String thumbnail;
  @Transient
  private List<String> images;
  @Transient
  private List<Bid> bids;

  public List<String> getImages() {
    return images;
  }

  public void setImages(List<String> images) {
    this.images = images;
  }

  public Auction(String username, String title, String description, String frontEndCreateTime, String frontEndEndTime, int startSum,
				 int reservedSum, String category, String auctionCondition) {
    this.username = username;
    this.title = title;
    this.description = description;
    this.frontEndCreateTime = frontEndCreateTime;
    this.frontEndEndTime = frontEndEndTime;
    this.startSum = startSum;
    this.reservedSum = reservedSum;
    this.category = category;
    this.auctionCondition = auctionCondition;
  }

  public Auction() {

  }

  public String getThumbnail() {
    return thumbnail;
  }

  public void setThumbnail(String thumbnail) {
    this.thumbnail = thumbnail;
  }

  public List<Bid> getBids() {
    return bids;
  }

  public void setBids(List<Bid> bids) {
    this.bids = bids;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

	public void setDescription(String description) {
		this.description= description;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public int getStartSum() {
		return startSum;
	}

	public void setStartSum(int startSum) {
		this.startSum = startSum;
	}

	public int getReservedSum() {
		return reservedSum;
	}

	public void setReservedSum(int reservedSum) {
		this.reservedSum = reservedSum;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAuctionCondition() {
		return auctionCondition;
	}

	public void setAuctionCondition(String auctionCondition) {
		this.auctionCondition = auctionCondition;
	}

   public String getFrontEndCreateTime() {
      return frontEndCreateTime;
   }

   public void setFrontEndCreateTime(String frontEndCreateTime) {
      this.frontEndCreateTime = frontEndCreateTime;
   }

   public String getFrontEndEndTime() {
      return frontEndEndTime;
   }

   public void setFrontEndEndTime(String frontEndEndTime) {
      this.frontEndEndTime = frontEndEndTime;
   }
}
