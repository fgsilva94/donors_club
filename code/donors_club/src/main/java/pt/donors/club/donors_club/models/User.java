package pt.donors.club.donors_club.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class User implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "usr_id")
  private int id;

  @Column(name = "usr_name")
  private String name;

  @Column(name = "usr_email")
  private String email;

  @Column(name = "usr_phone_number")
  private String phoneNumber;

  @Column(name = "usr_password")
  @JsonIgnore
  private String password;

  @Column(name = "usr_street")
  private String street;

  @ManyToOne
  @JoinColumn(name = "usr_city_id")
  private City city;

  @Column(name = "usr_active")
  @JsonIgnore
  private boolean active;

  @OneToMany
  @JoinColumn(name = "ad_owner_id")
  @JsonIgnore
  private List<AdPost> ads;

  @OneToMany
  @JoinColumns(@JoinColumn(name = "chat_ad_id"))
  @JsonIgnore
  private List<Chat> chats;

  @OneToMany
  @JoinColumns(@JoinColumn(name = "wl_usr_id"))
  @JsonIgnore
  private List<WishList> wishLists;

  public User() {
  }

  public User(int id, String name, String email, String phoneNumber, String password, String street, City city) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.password = password;
    this.street = street;
    this.city = city;
    active = true;
  }

  public int getId() {
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

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }

  public boolean isActive() {
    return active;
  }

  public void deactivate() {
    active = false;
  }

  public List<AdPost> getAds() {
    return ads;
  }

  public List<Chat> getChats() {
    return chats;
  }

  public List<WishList> getWishLists() {
    return wishLists;
  }
}
