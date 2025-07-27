package com.honda.webapp.backoffice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.honda.webapp.backoffice.model.Category;
import com.honda.webapp.backoffice.model.Moto;
import com.honda.webapp.backoffice.repository.CategoryRepository;
import com.honda.webapp.backoffice.service.MotoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryRestController {

  @Autowired
  private MotoService motoService;

  @Autowired
  private CategoryRepository categoryRepository;

  @GetMapping
  public List<Category> index() {

    List<Category> categories = categoryRepository.findAll();

    return categories;
  }

  @GetMapping("/{id}/motos")
  public ResponseEntity<List<Moto>> filterByCategory(@Valid @PathVariable Integer id) {

    List<Moto> filteredMotos = motoService.findByCategories_Id(id);
    if (filteredMotos.isEmpty()) {
      return new ResponseEntity<List<Moto>>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<List<Moto>>(filteredMotos, HttpStatus.OK);
  }
}
