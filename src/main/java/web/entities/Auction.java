package web.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Auction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private long userId;
	private String title;
	private String description;
	private String category;
	private String condition;
	private Timestamp createTime;
	private Timestamp endTime;
	private int startSum;
	private int reservedSum;

	public Auction(long userId, String title, String description, String category, String condition, Timestamp createTime, Timestamp endTime, int startSum, int reservedSum) {
		this.userId = userId;
		this.title = title;
		this.description = description;
		this.category = category;
		this.condition = condition;
		this.createTime = createTime;
		this.endTime = endTime;
		this.startSum = startSum;
		this.reservedSum = reservedSum;
	}

	public Auction() {

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
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
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
}
