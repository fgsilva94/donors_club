package pt.donors.club.donors_club.repositories;

import org.springframework.data.repository.CrudRepository;

import pt.donors.club.donors_club.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
