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
    private LocalDate date;

    @Column(name = "wl_active")
    private boolean active;

    public WishList() {}

    public WishList(int id, User user, AdPost adPost, LocalDate date) {
        this.id = id;
        this.user = user;
        this.adPost = adPost;
        this.date = date;
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

    public LocalDate getDate() {
        return date;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
