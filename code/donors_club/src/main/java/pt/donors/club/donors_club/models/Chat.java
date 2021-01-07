package pt.donors.club.donors_club.models;

import java.io.Serializable;
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
  private Long id;

  @ManyToOne
  @JoinColumn(name = "chat_customer_id")
  private User customer;

  @ManyToOne
  @JoinColumn(name = "chat_ad_owner_id")
  private User adOwner;

  @ManyToOne
  @JoinColumn(name = "chat_ad_id")
  private Ad ad;

  @OneToMany
  @JoinColumn(name = "msg_chat_id")
  private List<Message> messages;

  @Column(name = "chat_active")
  private boolean active;

  public Chat() {
  }

  public Chat(Long id, User customer, Ad ad) {
    this.id = id;
    this.customer = customer;
    this.ad = ad;
    adOwner = ad.getOwner();
    active = true;
  }

  public Long getId() {
    return id;
  }

  public User getCustomer() {
    return customer;
  }

  public User getAdOwner() {
    return adOwner;
  }

  public Ad getAd() {
    return ad;
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Chat other = (Chat) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }
}
