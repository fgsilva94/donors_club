package pt.donors.club.donors_club.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import pt.donors.club.donors_club.models.Subcategory;

public interface SubcategoryRepository extends CrudRepository<Subcategory, Integer> {
  @Query(value = "SELECT * FROM subcategories WHERE subc_cat_id = :id", nativeQuery = true)
  Iterable<Subcategory> getSubCategoriesByCategory(@Param("id") int id);
}
