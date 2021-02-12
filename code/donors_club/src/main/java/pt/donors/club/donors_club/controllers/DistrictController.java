package pt.donors.club.donors_club.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.donors.club.donors_club.models.District;
import pt.donors.club.donors_club.repositories.DistrictRepository;

@RestController
@RequestMapping(path = "/api/districts")
public class DistrictController {
  private Logger logger = LoggerFactory.getLogger(MessageController.class);

  @Autowired
  private DistrictRepository districtRepository;

  @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<District> getDistricts() {
    logger.info("Sending all districts");

    return districtRepository.findAll();
  }
}
