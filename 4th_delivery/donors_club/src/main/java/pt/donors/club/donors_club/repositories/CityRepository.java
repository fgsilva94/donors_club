package pt.donors.club.donors_club.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import pt.donors.club.donors_club.models.City;

public interface CityRepository extends CrudRepository<City, Integer> {
  @Query(value = "SELECT * FROM cities WHERE city_dis_id = :id", nativeQuery = true)
  Iterable<City> getCitiesByDistrict(@Param("id") int id);
}
