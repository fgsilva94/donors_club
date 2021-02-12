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
import pt.donors.club.donors_club.models.exceptions.NotFoundException;
import pt.donors.club.donors_club.models.results.SimpleResult;
import pt.donors.club.donors_club.models.views.AdPostSimpleView;
import pt.donors.club.donors_club.models.views.AdPostView;
import pt.donors.club.donors_club.repositories.AdPostRepository;

@RestController
@RequestMapping(path = "/api/ads")
public class AdController {
  private Logger logger = LoggerFactory.getLogger(AdController.class);

  @Autowired
  private AdPostRepository adRepository;

  @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<AdPostSimpleView> simpleAds() {
    logger.info("Sending all ad posts simple view");

    return adRepository.findAllSimpleView();
  }

  @GetMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
  public AdPostView getAdPost(@PathVariable int id) {
    logger.info("Sending ad post with id " + id);

    Optional<AdPostView> _ad = adRepository.findAdPostById(id);

    if (_ad.isEmpty())
      throw new NotFoundException("" + id, "Ad Post", "id");
    else
      return _ad.get();
  }

  @GetMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<AdPostSimpleView> searchUnits(@RequestParam(value = "title", defaultValue = "") String title,
      @RequestParam(value = "category", defaultValue = "") String category,
      @RequestParam(value = "city", defaultValue = "") String city) {
    logger.info("Searching for ad posts with title like " + title + " and category " + category + " and city " + city);

    return adRepository.findByTitleAndByCategoryAndByCityContaining(title, category, city);
  }

  @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public SimpleResult addAdPost(@RequestBody AdPost ad) {
    logger.info("Adding new ad post with id " + ad.getId());

    adRepository.save(ad);

    return new SimpleResult("Create new Ad Post", null);
  }
}
