package pt.donors.club.donors_club.models;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "messages")
public class Message implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "msg_id")
  private Long id;

  @Column(name = "msg_content")
  private String content;

  @Column(name = "msg_date")
  private LocalDate date;

  @ManyToOne
  @JoinColumn(name = "msg_chat_id")
  @JsonIgnore
  private Chat chat;

  @ManyToOne
  @JoinColumn(name = "msg_sender_id")
  @JsonIgnoreProperties({ "ads", "sender", "chats" })
  private User sender;

  public Message() {
  }

  public Message(Long id, String content, LocalDate date, Chat chat, User sender) {
    this.id = id;
    this.content = content;
    this.date = date;
    this.chat = chat;
    this.sender = sender;
  }

  public Long getId() {
    return id;
  }

  public String getContent() {
    return content;
  }

  public LocalDate getDate() {
    return date;
  }

  public Chat getChat() {
    return chat;
  }

  public User getSender() {
    return sender;
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
    Message other = (Message) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }
}
