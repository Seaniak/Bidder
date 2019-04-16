package web.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String title;
  private String body;

  @Transient
  private String imageName;

  private String imageData;

  public String getImageData() {
    return imageData;
  }

  public void setImageData(String imageData) {
    this.imageData = imageData;
  }



  public Post() {
  }

  public String getImageName() {
    return imageName;
  }

  public void setImageName(String image) {
    this.imageName = image;
  }

  private Date dateCreated;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public Date getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(Date dateCreated) {
    this.dateCreated = dateCreated;
  }


}
