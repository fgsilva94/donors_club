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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pt.donors.club.donors_club.models.AdPost;
import pt.donors.club.donors_club.models.views.AdPostSearchView;
import pt.donors.club.donors_club.models.views.AdPostSimpleView;
import pt.donors.club.donors_club.repositories.AdPostRepository;

@RestController
@RequestMapping(path = "/api/ads")
public class AdController {
  private Logger logger = LoggerFactory.getLogger(AdController.class);

  @Autowired
  private AdPostRepository adRepository;

  @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<AdPostSimpleView> simpleAds() {
    return adRepository.findAllSimpleView();
  }

  @GetMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
  public AdPost getAdPost(@PathVariable int id) {
    Optional<AdPost> ad = adRepository.findById(id);

    return ad.get();
  }

  @GetMapping(path = "/my_adposts/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<AdPostSimpleView> getMyAdPosts(@PathVariable int userId) {
    return adRepository.findAllMyAdPostsSimpleView(userId);
  }

  @GetMapping(path = "/wishlist/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<AdPostSimpleView> getWishlists(@PathVariable int userId) {
    return adRepository.findWishListSimpleView(userId);
  }

  @GetMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<AdPostSearchView> searchUnits(@RequestParam(value = "title", defaultValue = "") String title,
      @RequestParam(value = "category", defaultValue = "") String category,
      @RequestParam(value = "city", defaultValue = "") String city) {
    logger.info("Finding by title: " + title + " and by city: " + city + " and category: " + category);

    return adRepository.findByTitleAndByCategoryAndByCityContaining(title, category, city);
  }

  @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public AdPost addUser(@RequestBody AdPost ad) {
    return adRepository.save(ad);
  }
}
