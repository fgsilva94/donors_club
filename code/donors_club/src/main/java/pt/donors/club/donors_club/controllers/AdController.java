package pt.donors.club.donors_club.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.donors.club.donors_club.models.AdPost;
import pt.donors.club.donors_club.repositories.AdPostRepository;

@RestController
@RequestMapping(path = "/api/ads")
public class AdController {

  @Autowired
  private AdPostRepository adRepository;

  @GetMapping
  public ResponseEntity<Iterable<AdPost>> getAds() {
    return ResponseEntity.ok().body(adRepository.findAll());
  }
}
