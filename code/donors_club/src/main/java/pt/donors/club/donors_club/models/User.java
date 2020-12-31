package pt.donors.club.donors_club.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "user")
public class User implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long id;

  @Column(name = "user_name")
  private String name;

  @Column(name = "user_email")
  private String email;

  @Column(name = "user_phone_number")
  private String phoneNumber;

  @Column(name = "user_password")
  private String password;

  @Column(name = "user_address")
  private String address;

  @Column(name = "user_active")
  private boolean active;

  @OneToMany
  @JoinColumn(name = "ad_user_id")
  @JsonIgnoreProperties("author")
  private List<Ad> ads = new ArrayList<>();

  // private List<Ad> favorites = new ArrayList<>();

  public User() {
  }

  public User(Long id, String name, String email, String phoneNumber, String password, String address) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.password = password;
    this.address = address;
    active = true;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public boolean isActive() {
    return active;
  }

  public void deactivate() {
    active = false;
  }

  public List<Ad> getAds() {
    return ads;
  }

  /*
   * public List<Ad> getFavorites() { return favorites; }
   */
}
