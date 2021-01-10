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
  private int id;

  @Column(name = "msg_text")
  private String text;

  @Column(name = "msg_time")
  private LocalDate time;

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

  public Message(int id, String text, LocalDate time, Chat chat, User sender) {
    this.id = id;
    this.text = text;
    this.time = time;
    this.chat = chat;
    this.sender = sender;
  }

  public int getId() {
    return id;
  }

  public String getText() {
    return text;
  }

  public LocalDate getTime() {
    return time;
  }

  public Chat getChat() {
    return chat;
  }

  public User getSender() {
    return sender;
  }
}
