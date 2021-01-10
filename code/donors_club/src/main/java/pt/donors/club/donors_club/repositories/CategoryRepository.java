package pt.donors.club.donors_club.repositories;

import org.springframework.data.repository.CrudRepository;

import pt.donors.club.donors_club.models.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    
}
