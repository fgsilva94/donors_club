package pt.donors.club.donors_club.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.donors.club.donors_club.models.Ad;
import pt.donors.club.donors_club.repositories.AdRepository;

@RestController
@RequestMapping(path = "/api/ads")
public class AdController {

  @Autowired
  private AdRepository adRepository;

  @GetMapping
  public ResponseEntity<Iterable<Ad>> getAds() {
    return ResponseEntity.ok().body(adRepository.findAll());
  }
}
