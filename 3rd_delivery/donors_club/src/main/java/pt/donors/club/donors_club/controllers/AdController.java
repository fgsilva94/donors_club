package pt.donors.club.donors_club.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

  @GetMapping
  public ResponseEntity<Iterable<AdPostSimpleView>> simpleAds() {
    return ResponseEntity.ok().body(adRepository.findAllSimpleView());
  }

  @GetMapping(path = "/{id:[0-9]+}")
  public ResponseEntity<AdPost> getAdPost(@PathVariable int id) {
    Optional<AdPost> ad = adRepository.findById(id);

    return ResponseEntity.ok().body(ad.get());
  }

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public AdPost addUser(@RequestBody AdPost ad) {
    return adRepository.save(ad);
  }
}
