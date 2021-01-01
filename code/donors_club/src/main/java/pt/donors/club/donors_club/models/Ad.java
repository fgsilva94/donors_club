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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ads")
public class Ad implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ad_id")
  private Long id;

  @Column(name = "ad_title")
  private String title;

  @Column(name = "ad_description")
  private String description;

  @Column(name = "ad_category")
  private String category;

  @Column(name = "ad_publication_date")
  private LocalDate publicationDate;

  @Column(name = "ad_active")
  private boolean active;

  @ManyToOne
  @JoinColumn(name = "ad_owner_id")
  @JsonIgnoreProperties("ads")
  private User owner;

  public Ad() {
  }

  public Ad(Long id, String title, String description, String category, User owner) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.category = category;
    publicationDate = LocalDate.now();
    active = true;
    this.owner = owner;
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public LocalDate getPublicationDate() {
    return publicationDate;
  }

  public boolean isActive() {
    return active;
  }

  public void deactivate() {
    active = false;
  }

  public User getOwner() {
    return owner;
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
    Ad other = (Ad) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }
}
