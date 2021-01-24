package pt.donors.club.donors_club.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import pt.donors.club.donors_club.models.AdPost;
import pt.donors.club.donors_club.models.View.AdPostSimpleView;

public interface AdPostRepository extends CrudRepository<AdPost, Integer> {
  @Query(value = "SELECT ad_id as id, ad_title as title FROM adposts", nativeQuery = true)
  Iterable<AdPostSimpleView> findAllSimpleView();
}
