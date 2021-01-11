package pt.donors.club.donors_club.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.donors.club.donors_club.models.User;
import pt.donors.club.donors_club.models.View.UserLoginView;
import pt.donors.club.donors_club.repositories.UserRepository;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {
  @Autowired
  private UserRepository userRepository;

  @GetMapping
  public ResponseEntity<Iterable<User>> getUsers() {
    return ResponseEntity.ok().body(userRepository.findAll());
  }

  @GetMapping(path = "/{id:[0-9]+}")
  public ResponseEntity<User> getUser(@PathVariable int id) {
    Optional<User> usr = userRepository.findById(id);

    return ResponseEntity.ok().body(usr.get());
  }

  @PostMapping
  public User addUser(@RequestBody User usr) {
    return userRepository.save(usr);
  }

  @GetMapping(value = "/{email}/{password}/")
  public ResponseEntity<UserLoginView> login(@PathVariable String email, @PathVariable String password) {
    UserLoginView usr = userRepository.login(email, password);

    return ResponseEntity.ok().body(usr);
  }
}
