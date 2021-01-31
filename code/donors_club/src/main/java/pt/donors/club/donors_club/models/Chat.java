package pt.donors.club.donors_club.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "chats")
public class Chat implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "chat_id")
  private int id;

  @ManyToOne
  @JoinColumn(name = "chat_ad_id")
  private AdPost ad;

  @ManyToOne
  @JoinColumn(name = "chat_usr_id")
  private User user;

  @OneToMany
  @JoinColumn(name = "msg_chat_id")
  private List<Message> messages;

  @Column(name = "chat_active")
  private boolean active;

  @Column(name = "chat_date")
  private LocalDate date;

  public Chat() {
  }

  public Chat(int id, AdPost ad, User user) {
    this.id = id;
    this.ad = ad;
    this.user = user;
    active = true;
    date = LocalDate.now();
  }

  public int getId() {
    return id;
  }

  public AdPost getAd() {
    return ad;
  }

  public User getUser() {
    return user;
  }

  public List<Message> getMessages() {
    return messages;
  }

  public boolean isActive() {
    return active;
  }

  public void deactivate() {
    active = false;
  }

  public LocalDate getDate() {
    return date;
  }  
}
