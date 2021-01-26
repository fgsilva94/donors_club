package pt.donors.club.donors_club.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<User> getUsers() {
    return userRepository.findAll();
  }

  @GetMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
  public User getUser(@PathVariable int id) {
    Optional<User> usr = userRepository.findById(id);

    return usr.get();
  }

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public User addUser(@RequestBody User usr) {
    return userRepository.save(usr);
  }

  @GetMapping(value = "/{email}/{password}/", produces = MediaType.APPLICATION_JSON_VALUE)
  public UserLoginView login(@PathVariable String email, @PathVariable String password) {
    return userRepository.login(email, password);
  }
}
