package pt.donors.club.donors_club.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id")
    private int id;

    @Column(name = "cat_name")
    private String name;

    @OneToMany
    @JoinColumn(name = "subc_cat_id")
    private List<Subcategory> subcategories;

    public Category() {
    }

    public Category(int id, String name, List<Subcategory> subcategories) {
        this.id = id;
        this.name = name;
        this.subcategories = subcategories;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Subcategory> getSubcategories() {
        return subcategories;
    }
}
