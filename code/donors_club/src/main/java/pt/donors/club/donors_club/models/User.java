package pt.donors.club.donors_club.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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

  @Column(name = "usr_password")
  private String password;

  @ManyToOne
  @JoinColumn(name = "usr_city_id")
  private City city;

  @Column(name = "usr_active")
  private boolean active;

  public User() {
  }

  public User(int id, String name, String email, String password, City city) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
    this.city = city;
    active = true;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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
}
