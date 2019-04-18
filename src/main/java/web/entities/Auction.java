package web.entities;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class Auction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private long userId;
	private String title;
	private String description;
	private Timestamp createTime;
	private Timestamp endTime;
	private int startSum;
	private int reservedSum;

	public String[] getImagePaths() {
		return imagePaths;
	}

	public void setImagePaths(String[] imagePaths) {
		this.imagePaths = imagePaths;
	}

	private String category;
	private String auctionCondition;
	@Transient
	private String[] imagePaths;

	public Auction(){

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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
}
