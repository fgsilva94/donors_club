package pt.donors.club.donors_club.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.donors.club.donors_club.models.Category;
import pt.donors.club.donors_club.repositories.CategoryRepository;

@RestController
@RequestMapping(path = "/api/categories")
public class CategoryController {
  private Logger logger = LoggerFactory.getLogger(CategoryController.class);

  @Autowired
  private CategoryRepository categoryRepository;

  @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Category> getCategories() {
    logger.info("Sending all categories");

    return categoryRepository.findAll();
  }
}
