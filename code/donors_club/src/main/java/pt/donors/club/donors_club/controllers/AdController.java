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

import pt.donors.club.donors_club.models.AdPost;
import pt.donors.club.donors_club.models.View.AdPostSimpleView;
import pt.donors.club.donors_club.repositories.AdPostRepository;

@RestController
@RequestMapping(path = "/api/ads")
public class AdController {

  @Autowired
  private AdPostRepository adRepository;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<AdPostSimpleView> simpleAds() {
    return adRepository.findAllSimpleView();
  }

  @GetMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
  public AdPost getAdPost(@PathVariable int id) {
    Optional<AdPost> ad = adRepository.findById(id);

    return ad.get();
  }

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public AdPost addUser(@RequestBody AdPost ad) {
    AdPost newAd = new AdPost(0, ad.getTitle(), ad.getDescription(), ad.getCategory(), ad.getOwner());
    return adRepository.save(newAd);
  }
}
