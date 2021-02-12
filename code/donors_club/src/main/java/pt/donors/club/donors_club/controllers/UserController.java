package pt.donors.club.donors_club.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.donors.club.donors_club.models.User;
import pt.donors.club.donors_club.models.exceptions.NotFoundException;
import pt.donors.club.donors_club.models.results.SimpleResult;
import pt.donors.club.donors_club.models.views.AdPostSimpleView;
import pt.donors.club.donors_club.models.views.ChatSimpleView;
import pt.donors.club.donors_club.models.views.UserLoginView;
import pt.donors.club.donors_club.repositories.AdPostRepository;
import pt.donors.club.donors_club.repositories.ChatRepository;
import pt.donors.club.donors_club.repositories.UserRepository;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {
  private Logger logger = LoggerFactory.getLogger(UserController.class);

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private AdPostRepository adRepository;

  @Autowired
  private ChatRepository chatRepository;

  @GetMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
  public User getUser(@PathVariable int id) {
    logger.info("Sending user with id " + id);

    Optional<User> _user = userRepository.findById(id);

    if (_user.isEmpty())
      throw new NotFoundException("" + id, "User", "id");
    else
      return _user.get();
  }

  @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public SimpleResult addUser(@RequestBody User user) {
    logger.info("Adding new user with id " + user.getId());

    userRepository.save(user);

    return new SimpleResult("Adding new user", user);
  }

  @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
  public UserLoginView login(@RequestBody User user) {
    String email = user.getEmail();
    String password = user.getPassword();
    logger.info("Login use with email " + email);

    Optional<UserLoginView> _user = userRepository.login(email, password);

    if (_user.isEmpty())
      throw new NotFoundException("(" + email + ", " + password + ")", "User", "(email, password)");
    else
      return _user.get();
  }

  @GetMapping(path = "/{userId}/ads", produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<AdPostSimpleView> getMyAdPosts(@PathVariable int userId) {
    logger.info("Sending all ad posts simple view by user id " + userId);

    return adRepository.findAllMyAdPostsSimpleView(userId);
  }

  @GetMapping(path = "/{userId}/wishlist", produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<AdPostSimpleView> getWishlists(@PathVariable int userId) {
    logger.info("Sending all ad posts in wishlist by user id " + userId);

    return adRepository.findWishListSimpleView(userId);
  }

  @GetMapping(path = "/{userId}/chats", produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<ChatSimpleView> getChats(@PathVariable int userId) {
    logger.info("Sending all chats by user id " + userId);

    return chatRepository.findChatsByUser(userId);
  }
}
