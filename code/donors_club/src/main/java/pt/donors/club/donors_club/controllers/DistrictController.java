package pt.donors.club.donors_club.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.donors.club.donors_club.models.City;
import pt.donors.club.donors_club.models.District;
import pt.donors.club.donors_club.repositories.CityRepository;
import pt.donors.club.donors_club.repositories.DistrictRepository;

@RestController
@RequestMapping(path = "/api/districts")
public class DistrictController {
  private Logger logger = LoggerFactory.getLogger(DistrictController.class);
  
  @Autowired
  private DistrictRepository districtRepository;

  @Autowired
  private CityRepository cityRepository;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<District> getDistricts() {
    return districtRepository.findAll();
  }

  @GetMapping(path = "/{id}/cities", produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<City> getCities(@PathVariable int id) {
    return cityRepository.getCitiesByDistrict(id);
  }
}
