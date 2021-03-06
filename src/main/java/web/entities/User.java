package web.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String username;
  private String password;
  private String email;
  private String name;
  private String surname;

  @Transient
  private List<String> ongoingChats;

  public User() {
  }

  public User(long id, String username, String password, String email, String name, String surname) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.email = email;
    this.name = name;
    this.surname = surname;
  }

  public List<String> getOngoingChats() {
    return ongoingChats;
  }

  public void setOngoingChats(List<String> ongoingChats) {
    this.ongoingChats = ongoingChats;
  }

  public String getUsername() {
    return username;
  }

  @JsonIgnore
  public String getPassword() {
    return password;
  }

  @JsonProperty
  public void setPassword(String password) {
    this.password = password;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }


}
