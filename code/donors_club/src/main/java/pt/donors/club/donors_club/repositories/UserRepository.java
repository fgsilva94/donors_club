package pt.donors.club.donors_club.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import pt.donors.club.donors_club.models.User;
import pt.donors.club.donors_club.models.views.UserLoginView;

public interface UserRepository extends CrudRepository<User, Integer> {
  @Query(value = "SELECT usr_id as id, usr_name as name FROM users WHERE usr_email = :email AND usr_password = :password", nativeQuery = true)
  Optional<UserLoginView> login(@Param("email") String email, @Param("password") String password);
}
