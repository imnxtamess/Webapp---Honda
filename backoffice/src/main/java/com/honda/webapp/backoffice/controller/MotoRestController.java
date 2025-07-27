package com.honda.webapp.backoffice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.honda.webapp.backoffice.model.Moto;
import com.honda.webapp.backoffice.service.MotoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/motos")
public class MotoRestController {

  @Autowired
  private MotoService motoService;

  @GetMapping
  public List<Moto> index() {

    List<Moto> motos = motoService.findAll();

    return motos;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Moto> show(@Valid @PathVariable Integer id) {

    Optional<Moto> motoAttempt = motoService.findById(id);

    if (motoAttempt.isEmpty()) {
      return new ResponseEntity<Moto>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<Moto>(motoAttempt.get(), HttpStatus.OK);
  }

  @GetMapping("/category/{id}")
  public ResponseEntity<List<Moto>> filterByCategory(@Valid @PathVariable Integer id) {

    List<Moto> filteredMotos = motoService.findByCategoryId(id);
    if (filteredMotos.isEmpty()) {
      return new ResponseEntity<List<Moto>>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<List<Moto>>(filteredMotos, HttpStatus.OK);
  }

}
