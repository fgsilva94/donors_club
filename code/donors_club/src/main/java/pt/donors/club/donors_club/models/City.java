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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cities")
public class City implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "city_id")
  private int id;

  @Column(name = "city_name")
  private String name;

  @ManyToOne
  @JoinColumn(name = "city_dis_id")
  @JsonIgnore
  private District district;

  public City() {
  }

  public City(int id, String name, District district) {
    this.id = id;
    this.name = name;
    this.district = district;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public District getDistrict() {
    return district;
  }
}
