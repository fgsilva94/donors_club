package pt.donors.club.donors_club.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.donors.club.donors_club.models.User;
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
}
