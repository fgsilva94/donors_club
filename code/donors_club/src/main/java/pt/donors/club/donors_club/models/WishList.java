package pt.donors.club.donors_club.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "wishlist")
public class WishList implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "wl_id")
  private int id;

  @ManyToOne
  @JoinColumn(name = "wl_usr_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "wl_ad_id")
  private AdPost adPost;

  @Column(name = "wl_date")
  private LocalDateTime date;

  @Column(name = "wl_active")
  private boolean active;

  public WishList() {
  }

  public WishList(int id, User user, AdPost adPost) {
    this.id = id;
    this.user = user;
    this.adPost = adPost;
    date = LocalDateTime.now();
    this.active = true;
  }

  public int getId() {
    return id;
  }

  public User getUser() {
    return user;
  }

  public AdPost getAdPost() {
    return adPost;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public boolean isActive() {
    return active;
  }

  public void deactivate() {
    active = false;
  }
}
