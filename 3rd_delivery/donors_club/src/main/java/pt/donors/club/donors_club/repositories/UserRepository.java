package pt.donors.club.donors_club.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import pt.donors.club.donors_club.models.User;
import pt.donors.club.donors_club.models.View.UserLoginView;

public interface UserRepository extends CrudRepository<User, Integer> {
  @Query(value = "SELECT usr_id as userId, usr_name as userName FROM users WHERE usr_email = :email AND usr_password = :password", nativeQuery = true)
  UserLoginView login(@Param("email") String email, @Param("password") String password);
}
